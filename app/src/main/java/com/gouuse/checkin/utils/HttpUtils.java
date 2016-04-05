package com.gouuse.checkin.utils;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

/**
 * Created by zhengjiong on 15/12/14.
 */
public class HttpUtils {

    public static void getRequest(String url, RequestBody requestBody, Callback callback) {

        OkHttpClient okHttpClient = new OkHttpClient();

        //RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), );
        /*RequestBody requestBody = new FormEncodingBuilder()
                .add("token", "1")
                .build();*/

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
