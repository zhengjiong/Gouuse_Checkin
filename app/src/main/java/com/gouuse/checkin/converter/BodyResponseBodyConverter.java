package com.gouuse.checkin.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.gouuse.checkin.bean.Body;
import com.gouuse.checkin.utils.AESCrypt;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by zhengjiong on 16/3/30.
 */
final class BodyResponseBodyConverter<T> implements Converter<ResponseBody, Body<T>> {
    private final TypeAdapter<T> adapter;
    BodyResponseBodyConverter(TypeAdapter<T> adapter){
        this.adapter = adapter;
    }

    @Override
    public Body<T> convert(ResponseBody value) throws IOException {
        try {
            String aesValue = value.string();
            try {
                if (aesValue != null) {
                    String decryptValue = AESCrypt.getInstance().decrypt(aesValue);
                    System.out.println("convert decryptValue = " + decryptValue);
                    return (Body<T>) adapter.fromJson(decryptValue);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return null;
        } finally {
            value.close();
        }
    }
}
