package com.gouuse.checkin;

import android.app.Application;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.gouuse.checkin.api.Api;
import com.gouuse.checkin.converter.BodyFactory;
import com.karumi.dexter.Dexter;


import net.danlew.android.joda.JodaTimeAndroid;

import retrofit2.Retrofit;

/**
 * Created by zhengjiong on 15/12/14.
 */
public class AppContext extends Application {
    private static AppContext mAppContext;
    private static AMapLocationClient mLocationClient;
    private static Retrofit mRetrofit;
    private static Api mApi;

    public static AppContext getInstance() {
        return mAppContext;
    }
    public static Retrofit getRetrofit(){
        return mRetrofit;
    }
    public static Api getApi(){
        return mApi;
    }
    public static AMapLocationClient getLocationClient(){
        return mLocationClient;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
        Dexter.initialize(this);
        JodaTimeAndroid.init(this);
        initRetrofit();
        initGaoDeLocationOption();
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.ADDRESS)
                .addConverterFactory(BodyFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mApi = mRetrofit.create(Api.class);

    }

    /**
     * 初始化高德定位参数
     */
    private void initGaoDeLocationOption() {
        mLocationClient = new AMapLocationClient(getApplicationContext());
        AMapLocationClientOption mLocationOption = null;
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(5000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
    }
}
