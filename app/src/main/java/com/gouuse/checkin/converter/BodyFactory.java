package com.gouuse.checkin.converter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by zhengjiong on 16/3/29.
 */
public final class BodyFactory extends Converter.Factory {
    private final Gson gson;
    private BodyFactory(){
        gson = new Gson();
    }

    public static BodyFactory create(){
        return new BodyFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new BodyResponseBodyConverter(adapter);
    }
}
