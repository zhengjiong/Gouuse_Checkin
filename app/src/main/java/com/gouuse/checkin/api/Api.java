package com.gouuse.checkin.api;

import com.gouuse.checkin.bean.Address;
import com.gouuse.checkin.bean.Body;
import com.gouuse.checkin.bean.LoginBean;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by zhengjiong on 16/3/31.
 */
public interface Api {

    @POST("api/account/login")
    Call<Body<LoginBean>> login(@Query("info") String info);

    @POST("api/attendence/mobile_checkin/checkin_place")
    Call<Body<Address>> loadCheckinAddress(@Query("info") String info);

    @POST("api/system/get_auth_code")
    Call<Body<HashMap<String, String>>> getCheckinAuthCode(@Query("info") String info);

    @POST("api/attendence/mobile_checkin")
    Call<Body<HashMap<String, String>>> checkin(@Query("info") String info);

    @POST("api/attendence/mobile_checkin")
    Call<Body<HashMap<String, String>>> checkin(@Query("info") String info, @Part("imgFile") File file);
}
