package com.gouuse.checkin.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.amap.api.maps2d.model.LatLng;
import com.gouuse.checkin.AppContext;
import com.gouuse.checkin.R;
import com.gouuse.checkin.bean.Address;
import com.gouuse.checkin.bean.Body;
import com.gouuse.checkin.bean.CheckInResult;
import com.gouuse.checkin.bean.LoginBean;
import com.gouuse.checkin.permission.MyMultiplePermissionListener;
import com.gouuse.checkin.ui.MainActivity;
import com.gouuse.checkin.utils.AESCrypt;
import com.gouuse.checkin.utils.AMapUtil;
import com.gouuse.checkin.utils.Basic_Util;
import com.gouuse.checkin.utils.FileUtils;
import com.gouuse.checkin.utils.PreferencesUtils;
import com.gouuse.checkin.view.CheckinTypeView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener;

import org.joda.time.LocalDateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengjiong on 15/12/13.
 */
public class CheckInFragment extends Fragment implements AMapLocationListener {
    public static final int REQUEST_LOCATION = 9;       //选择地址
    public static final int REQUEST_CAMERA = 8;
    public static final int REQUEST_CODE_LOCATION_SETTINGS = 0;

    private static final double MAX_IMAGE_FILE_SIZE = 300;
    private static final int MAX_WIDTH = 720;
    private static final int MAX_HEIGHT = 1280;

    @Bind(R.id.rootview)
    ViewGroup mRootView;

    @Bind(R.id.retry_wrapper)
    View mRetryWrapper;

    @Bind(R.id.txt_tips)
    TextView mTxtTips;

    @Bind(R.id.progressbar_wrapper)
    RelativeLayout mProgressbarWrapper;

    @Bind(R.id.nodata_wrapper)
    View mNoDataWrapper;

    @Bind(R.id.txt_nodata_msg)
    TextView mTxtNoData;

    @Bind(R.id.txt_member_name)
    TextView mTxtMemberName;

    @Bind(R.id.txt_location_address)
    TextView mTxtLocationAddress;

    @Bind(R.id.txt_secondary)
    TextView mTxtAddress;

    @Bind(R.id.address_wrapper)
    LinearLayout mAddressWrapper;

    @Bind(R.id.txt_distance)
    TextView mTxtDistance;

    @Bind(R.id.txt_distance_tips)
    TextView mTxtDistanceTips;

    @Bind(R.id.img_photo)
    ImageView mImgPhoto;

    @Bind(R.id.photo_wrapper)
    LinearLayout mPhotoWrapper;

    @Bind(R.id.current_address_wrapper)
    View mCurrentAddressWrapper;

    @Bind(R.id.checkintype_shangban)
    CheckinTypeView mCheckinTypeShangBan;

    @Bind(R.id.checkintype_xiaban)
    CheckinTypeView mCheckinTypeXiaBan;

    private MaterialDialog mProgressDialog;

    public Body<Address> mBody;//获取到的打卡地址列表
    private boolean mWhosYourDaddy;
    private int mDaddyCount;
    private double mLatitude;
    private double mLongitude;
    private boolean IS_LOCATION_SUCCESS; //是否定位成功
    private String mAddressValue;       //当前定位的地址
    private long mLocationTime;       //当前定位的时间
    private boolean mIsAutoSelect;          //是否已经自动选择
    private Address.Place mPlace;                //当前选择的地点
    private File mTmpFile;              //拍照的图片

//    ConfirmDialog mConfirmDialog;
//    ClockConfirmDialog mCheckinTypeConfirmDialog;

    private int mCheckinType = 1;       //打卡类型 1-上班 2-下班
    private int mIsYesterdayEnd;        //是否为昨日下班卡 1-是，0-不是
    private int mIsConver = 1;          //是否覆盖上次打卡记录 1-覆盖，0-不覆盖
    private boolean mIsCheckOutWork;

    private MultiplePermissionsListener mAllPermissionsListener;

    public static CheckInFragment newInstance() {

        Bundle args = new Bundle();

        CheckInFragment fragment = new CheckInFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @OnClick({R.id.address_wrapper, R.id.photo_wrapper, R.id.btn_retry, R.id.checkintype_shangban, R.id.checkintype_xiaban})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.address_wrapper:
                //选择打卡地址
                /*Intent intent = new Intent(this, AddressListActivity.class);
                intent.putExtra("place", mBody.getPlace_list());
                startActivityForResult(intent, REQUEST_LOCATION);*/
                break;
            case R.id.photo_wrapper:
                if (mBody == null) {
                    Toast.makeText(getActivity(), "请等待地址加载完成", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mBody.data.need_take_photo == 0) {
                    Toast.makeText(getActivity(), "管理员设置不需要上传照片,可以直接提交", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    Toast.makeText(getActivity(), "沒有找到sd卡,不能上传图片", Toast.LENGTH_SHORT).show();
                    return;
                }
                takePicture();
                break;
            case R.id.btn_retry:
                mProgressbarWrapper.setVisibility(View.VISIBLE);
                mRetryWrapper.setVisibility(View.GONE);
                Dexter.checkPermissions(mAllPermissionsListener, Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE);
                break;
            case R.id.img_current_location:
                mDaddyCount++;
                if (mDaddyCount >= 9) {
                    mWhosYourDaddy = true;
                }
                break;
            case R.id.checkintype_shangban:
                mCheckinType = 1;   //上班
                mCheckinTypeShangBan.setCheckinType(true);
                mCheckinTypeXiaBan.setCheckinType(false);

                break;
            case R.id.checkintype_xiaban:
                mCheckinType = 2;   //下班
                mCheckinTypeShangBan.setCheckinType(false);
                mCheckinTypeXiaBan.setCheckinType(true);
                break;
        }
    }

    private void launchCheckinFailed(String json) {
        CheckInResult checkInResult = CheckInResult.json2Obj(json);
        //LocalDateTime now = new LocalDateTime(System.currentTimeMillis(), DateTimeZone.forOffsetHours(8));
        //checkInResult.setCheckin_time(now.toString("yyyy-MM-dd  HH:mm:ss"));
        checkInResult.setCheckin_type(1);
        checkInResult.setStatus(1);

        /*Intent intent = new Intent(this, CheckInResultActivity.class);
        intent.putExtra("checkin_result", checkInResult);
        startActivity(intent);
        finish();*/
    }

    /**
     * 获取AuthCode
     */
    void getAuthCode() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token", PreferencesUtils.getString(getActivity(), "token", ""));
            Call<Body<HashMap<String, String>>> call = AppContext.getApi().getCheckinAuthCode(AESCrypt.getInstance().encrypt(jsonObject.toString()));

            call.enqueue(new Callback<Body<HashMap<String, String>>>() {
                @Override
                public void onResponse(Call<Body<HashMap<String, String>>> call, Response<Body<HashMap<String, String>>> response) {
                    try {
                        if (response.code() != 200) {
                            mProgressDialog.dismiss();
                            toast("服务器忙, 请稍后再试!");
                            return;
                        }
                        Body<HashMap<String, String>> body = response.body();
                        if (body.code.equals("0")) {
                            if (body.data != null) {
                                submit(body.data.get("auth_code"));
                            } else {
                                mProgressDialog.dismiss();
                                toast("验证码为空");
                            }
                        } else {
                            mProgressDialog.dismiss();
                            toast("获取验证码失败");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        mProgressDialog.dismiss();
                        toast("获取验证码失败");
                    }
                }

                @Override
                public void onFailure(Call<Body<HashMap<String, String>>> call, Throwable t) {
                    mProgressDialog.dismiss();
                    toast("连接服务器失败");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*HashMap<String, Object> params = new HashMap<>();
        params.put("token", Login_util.getInstance().getToken(this));

        HttpHelper.xutilsPostRequest(HttpAddress.GET_AUTH_CODE, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    Log.i("zj", "GET_AUTH_CODE response=" + responseInfo.result);
                    JSONObject jsonObject = new JSONObject(AESCrypt.getInstance().decrypt(responseInfo.result));

                    if (jsonObject.getInt("code") != 0) {
                        ShowServiceMessage.Show(CheckInActivity3.this, jsonObject.getString("code"));
                        return;
                    }

                    String authCode = jsonObject.getJSONObject("data").getString("auth_code");
                    if (authCode != null) {
                        submit(authCode);
                    } else {
                        ProgressDialogUtils.hideProgress();
                        Toast.makeText(CheckInActivity3.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ProgressDialogUtils.hideProgress();
                    Toast.makeText(CheckInActivity3.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ProgressDialogUtils.hideProgress();
                Toast.makeText(CheckInActivity3.this, R.string.network_request_failed, Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    void submit(String authCode) {
        JSONObject params = new JSONObject();
        try {
            buildParams(authCode, params);

            Call<Body<HashMap<String, String>>> call;
            if (mTmpFile != null && mTmpFile.length() != 0 && mBody.data.need_take_photo == 1) {
                params.put("imgFile", mTmpFile);
                call = AppContext.getApi().checkin(AESCrypt.getInstance().encrypt(params.toString()), mTmpFile);
            } else {
                call = AppContext.getApi().checkin(AESCrypt.getInstance().encrypt(params.toString()));
            }

            call.enqueue(new Callback<Body<HashMap<String, String>>>() {
                @Override
                public void onResponse(Call<Body<HashMap<String, String>>> call, Response<Body<HashMap<String, String>>> response) {
                    mProgressDialog.dismiss();
                    if (response.code() != 200) {
                        toast("服务器忙, 请稍后再试!");
                        return;
                    }
                    Body<HashMap<String, String>> body = response.body();
                    if (body.code.equals("0")) {
                        toast("打卡成功");
                    }
                }

                @Override
                public void onFailure(Call<Body<HashMap<String, String>>> call, Throwable t) {
                    mProgressDialog.dismiss();
                    toast("打卡失败");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*HttpHelper.xutilsPostRequest(HttpAddress.CLOCKING_CHECK_IN, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                ProgressDialogUtils.hideProgress();
                try {
                    JSONObject jsonObject = new JSONObject(AESCrypt.getInstance().decrypt(responseInfo.result));
                    if (jsonObject.getInt("code") == 1230010 || jsonObject.getInt("code") == 1230007) {
                        launchCheckinFailed(jsonObject.getJSONObject("data").getString("last_checkin_info"));
                        finish();
                        return;
                    }
                    if (jsonObject != null && jsonObject.getInt("code") == 0) {
                        if (mCheckinType == 1) {//上班
                            PreferencesUtils.putLong(CheckInActivity3.this, "checkin_start", System.currentTimeMillis());
                        } else {
                            PreferencesUtils.putLong(CheckInActivity3.this, "checkin_end", System.currentTimeMillis());
                        }
                        RemindUtils.startAlarm(CheckInActivity3.this, mBody);

                        String dataJson = jsonObject.getString("data");
                        CheckInResult checkInResult = CheckInResult.json2Obj(dataJson);

                        if (dataJson != null && !dataJson.equals("") && !dataJson.equals("[]")) {
                            EventBus.getDefault().post("");
                            Intent intent = new Intent(CheckInActivity3.this, CheckInResultActivity.class);
                            intent.putExtra("checkin_result", checkInResult);
                            intent.putExtra("is_yesterday_end", mIsYesterdayEnd);
                            //produceChangeEvent();
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        ShowServiceMessage.Show(CheckInActivity3.this, jsonObject.getString("code"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(CheckInActivity3.this, getResources().getString(R.string.toast_servers_busy), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                ProgressDialogUtils.hideProgress();
                Toast.makeText(CheckInActivity3.this, R.string.network_request_failed, Toast.LENGTH_SHORT).show();
            }
        });*/

        /*OkHttpRequest.Builder builder = new OkHttpRequest.Builder();
        builder.url(HttpAddress.CLOCKING_CHECK_IN);
        builder.params(params);

        if (mTmpFile != null && mTmpFile.length() != 0 && mBody.getNeed_take_photo() == 1) {
            builder.files(new Pair<String, File>("imgFile", mTmpFile));
        }
        builder.upload(new ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {


            }
        });*/

    }

    private void buildParams(String authCode, JSONObject params) throws JSONException {
        params.put("checkin_place_id", mPlace.checkin_place_id);
        params.put("token", PreferencesUtils.getString(getActivity(), "token", ""));

        if (mWhosYourDaddy) {
            params.put("longitude", 104.101529);
        } else {
            params.put("longitude", mPlace.longitude);
        }
        if (mWhosYourDaddy) {
            params.put("latitude", 30.663008);
        } else {
            params.put("latitude", mPlace.latitude);
        }
        if (mWhosYourDaddy) {
            params.put("address", "四川省成都市成华区一环路东二段靠近中电·信谊商务楼");
        } else {
            params.put("address", mAddressValue);
        }
        params.put("is_yearterday_end", mIsYesterdayEnd);
        params.put("checkin_type", mCheckinType);
        params.put("is_cover", mIsConver);

        params.put("sn", Basic_Util.getSN(getActivity()));
        params.put("auth_code", authCode);
    }

    public void takePicture() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            mTmpFile = FileUtils.createTmpFile(getActivity());
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mTmpFile));
            startActivityForResult(cameraIntent, REQUEST_CAMERA);
        } else {
            //Toast.makeText(this, R.string.msg_no_camera, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_LOCATION:  //地址
                if (resultCode != Activity.RESULT_OK) {
                    return;
                }
                Address.Place place = (Address.Place) data.getSerializableExtra("place");
                mPlace = place;

                Spanned html;
                if (mPlace.distance > mBody.data.checkin_distance) {
                    html = Html.fromHtml("距离指定打卡位置:  <font color='#D0021B'>" + (int) mPlace.distance + "米</font>");
                } else {
                    html = Html.fromHtml("距离指定打卡位置: " + (int) mPlace.distance + "米");
                }
                mTxtDistance.setText(html);

                mTxtAddress.setText(mPlace.address);
                break;
            case REQUEST_CAMERA:
                if (resultCode != Activity.RESULT_OK) {
                    return;
                }
                if (mTmpFile != null) {
                    String path = mTmpFile.getAbsolutePath();
                    //ImUtils.compressImage(path, path);

                    mImgPhoto.setImageURI(Uri.parse(path));
                    mImgPhoto.setVisibility(View.VISIBLE);
                }
                break;
            case REQUEST_CODE_LOCATION_SETTINGS:
                try {
                    loadCheckinAddress();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private static int calculateScaleSize(String filePath, BitmapFactory.Options options) {
        int scaleSize = 1;
        /*double fileSize = SDCardUtils.getFileKBSize(filePath);

        if (fileSize >= MAX_IMAGE_FILE_SIZE) {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);

            int width = options.outWidth;
            int height = options.outHeight;

            if (width > height && width > MAX_WIDTH) {
                scaleSize = (int) (width / MAX_WIDTH);
            } else if (width < height && height > MAX_HEIGHT) {
                scaleSize = (int) (height / MAX_HEIGHT);
            }
            if (scaleSize <= 0) {
                scaleSize = 1;
            }
        }*/
        return scaleSize;
    }

    public static void compressImage(String filePath, String saveFilePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        int scaleSize = calculateScaleSize(filePath, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleSize;

        Bitmap scaleBitmap = BitmapFactory.decodeFile(filePath, options);

        int newWidth = scaleBitmap.getWidth();
        int newHeight = scaleBitmap.getHeight();

        try {
            File saveFile = new File(saveFilePath);
            FileOutputStream fos = new FileOutputStream(saveFile);
            scaleBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void autoSelectPlace() {
        if (mIsAutoSelect) {
            Spanned html;
            if (mPlace.distance > mBody.data.checkin_distance) {
                html = Html.fromHtml("距离指定打卡位置:  <font color='#D0021B'>" + (int) mPlace.distance + "米</font>");
            } else {
                html = Html.fromHtml("距离指定打卡位置: " + (int) mPlace.distance + "米");
            }
            mTxtDistance.setText(html);
            mTxtAddress.setText(mPlace.address);
        } else {
            if (mBody.data.place_list.size() != 0) {
                mIsAutoSelect = true;
                float distance = 0f;
                for (Address.Place place : mBody.data.place_list) {
                    if (distance == 0) {
                        distance = place.distance;
                        mPlace = place;
                    } else {
                        if (place.distance >= distance) {
                            continue;
                        }
                        mPlace = place;

                        distance = place.distance;
                    }
                }
                Spanned html;
                if (mPlace.distance > mBody.data.checkin_distance) {
                    html = Html.fromHtml("距离指定打卡位置:  <font color='#D0021B'>" + (int) mPlace.distance + "米</font>");
                } else {
                    html = Html.fromHtml("距离指定打卡位置: " + (int) mPlace.distance + "米");
                }
                mTxtDistance.setText(html);
                mTxtAddress.setText(mPlace.address);

            }
        }
    }

    /**
     * 计算距离
     */
    private void calculateDistance() {

        if (mBody != null && mBody.data.place_list != null) {
            for (int i = 0; i < mBody.data.place_list.size(); i++) {
                try {
                    Address.Place place = mBody.data.place_list.get(i);

                    DPoint sourceLatLng = new DPoint(place.latitude, place.longitude);

                    CoordinateConverter converter = new CoordinateConverter(getActivity());
                    // CoordType.GPS 待转换坐标类型
                    converter.from(CoordinateConverter.CoordType.BAIDU);
                    // sourceLatLng待转换坐标点 DPoint类型
                    converter.coord(sourceLatLng);
                    // 执行转换操作
                    DPoint desLatLng = converter.convert();

                    float distance = AMapUtil.computeDistanceAndBearing(mLatitude, mLongitude, desLatLng.getLatitude(), desLatLng.getLongitude());
                    if (distance > 300 && distance < 400) {
                        distance -= 100;
                    } else if (distance >= 400 && distance < 500) {
                        distance -= 200;
                    }
                    place.distance = ((float) distance);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            if (amapLocation.getErrorCode() == 0) {
                IS_LOCATION_SUCCESS = true;

                //定位成功回调信息，设置相关消息
                amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.getLatitude();//获取纬度
                amapLocation.getLongitude();//获取经度
                amapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(amapLocation.getTime());
                String locationDate = df.format(date);//定位时间
                amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果
                amapLocation.getCountry();//国家信息
                amapLocation.getProvince();//省信息
                amapLocation.getCity();//城市信息
                amapLocation.getDistrict();//城区信息
                amapLocation.getRoad();//街道信息
                amapLocation.getCityCode();//城市编码
                amapLocation.getAdCode();//地区编码

                mLatitude = amapLocation.getLatitude();
                mLongitude = amapLocation.getLongitude();

                mAddressValue = amapLocation.getAddress();
                mLocationTime = amapLocation.getTime();

                //设置地址显示
                mTxtLocationAddress.setText(mAddressValue);
                mProgressbarWrapper.setVisibility(View.GONE);

                /**
                 * 1	GPS定位结果
                 2	返回上次定位结果
                 4	缓存定位结果
                 5	Wifi定位结果
                 6	基站定位结果
                 */
                Log.i("zj", "getLocationType =" + amapLocation.getLocationType() + " ,getAccuracy=" + amapLocation.getAccuracy() + " ,mLatitude=" + mLatitude + " ,mLongitude=" + mLongitude + " ,address=" + mAddressValue);

                calculateDistance();
                autoSelectPlace();
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.i("zj", "location Error, ErrCode:" + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());
                //ErrCode:12, errInfo:缺少定位权限

                //if (amapLocation.getErrorCode() == 12) {
                Toast.makeText(getActivity(), amapLocation.getErrorInfo(), Toast.LENGTH_SHORT).show();
                //}
            }
        }
    }

    /*@Override
    public void onReceiveLocation(BDLocation location) {
        if (location == null) {
            Log.i("zj", "location = null");
            return;
        }
        Log.i("zj", "locType = " + location.getLocType() + " ,location.getLatitude()=" + location.getLatitude() + " ,");

        //if (location.getLocType() == 161 || location.getLocType() == 61) {
        if (location.getLocType() == BDLocation.TypeServerError) {
            Toast.makeText(CheckInActivity2.this, "定位失败，请您检查是否禁用获取位置信息权限。", Toast.LENGTH_SHORT).show();
        }else if (location.getLocType() == BDLocation.TypeGpsLocation || location.getLocType() == BDLocation.TypeNetWorkLocation) {
            IS_LOCATION_SUCCESS = true;

            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();

            mAddressValue = location.getAddrStr();
            mLocationTime = location.getTime();

            //设置地址显示
            mTxtLocationAddress.setText(mAddressValue);
            mProgressbarWrapper.setVisibility(View.GONE);



            calculateDistance();
            autoSelectPlace();
        }
    }*/

    void location() {
        if (mBody.data.need_take_photo == 0) {
            //可以不用拍照
            //Toast.makeText(CheckInActivity.this, "不需要拍照", 1).show();
        } else {
            //Toast.makeText(CheckInActivity.this, "要拍照", 1).show();
        }
        AppContext.getInstance().getLocationClient().startLocation();
    }

    void toast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取打卡地址
     */
    private void loadCheckinAddress() throws Exception {
        JSONObject infoJSONObject = new JSONObject();
        infoJSONObject.put("token", PreferencesUtils.getString(getActivity(), "token", ""));
        infoJSONObject.put("source", 3);
        infoJSONObject.put("version", "1.2.7");


        System.out.println("info=" + AESCrypt.getInstance().encrypt(infoJSONObject.toString()));
        Call<Body<Address>> call = AppContext.getApi().loadCheckinAddress(AESCrypt.getInstance().encrypt(infoJSONObject.toString()));

        call.enqueue(new Callback<Body<Address>>() {
            @Override
            public void onResponse(Call<Body<Address>> call, Response<Body<Address>> response) {
                if (response.code() != 200) {
                    toast("服务器忙, 请稍后再试!");
                    return;
                }
                mBody = response.body();
                if (mBody.code.equals("0")) {
                    if (mBody.data != null) {

                        if (mBody.data.close_mobile_checkin == 0) {
                            if (mBody.data.need_take_photo == 0) {
                                mPhotoWrapper.setVisibility(View.GONE);
                            }
                            if (mBody.data.place_list.size() == 0) {
                                mTxtDistanceTips.setText("管理员未设置打卡地点");
                                mTxtDistance.setText("管理员未设置打卡地点");
                                mAddressWrapper.setVisibility(View.GONE);
                                mCurrentAddressWrapper.setVisibility(View.GONE);
                                mNoDataWrapper.setVisibility(View.VISIBLE);
                                mProgressbarWrapper.setVisibility(View.GONE);
                                mRetryWrapper.setVisibility(View.GONE);
                                //mTxtAdress.setEnabled(false);
                                //mTxtAdress.setText(R.string.toast_check_prompt_1);
                            } else {
                                mTxtDistanceTips.setText(
                                        Html.fromHtml(
                                                "注: 打卡需在距指定打卡位置"//<font color='#CF1C1B'>
                                                        + (int) mBody.data.checkin_distance
                                                        + "米内有效"));//</b>
                            }
                            //mTxtMemberName.setText(GlobalVar.UserInfo.member_name + "");
                            location();
                        } else {
                            mNoDataWrapper.setVisibility(View.VISIBLE);
                            mProgressbarWrapper.setVisibility(View.GONE);
                            mTxtNoData.setText("管理员未开启手机打卡功能");
                        }
                    }
                } else {
                    toast("获取数据失败, 请稍后再试!");
                }
            }

            @Override
            public void onFailure(Call<Body<Address>> call, Throwable t) {
                toast("网络连接失败");
            }
        });

    }

    protected void initListener() {

    }

    protected void initData() {

    }

    private void initGPS() {
        /*LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 判断GPS模块是否开启，如果没有则开启
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            mConfirmDialog = ConfirmDialog.newInstance(R.layout.dialog_location_settings, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.btn_confirm:
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivityForResult(intent, REQUEST_CODE_LOCATION_SETTINGS);
                            mConfirmDialog.dismiss();
                            break;
                        case R.id.btn_cancel:
                            loadCheckinAddress();
                            break;
                    }
                }

            });

            mConfirmDialog.show(getSupportFragmentManager(), "edit_group_name");

        } else {
            loadCheckinAddress();
        }*/
        try {
            loadCheckinAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initView() {
        /*SpannableString spannableString = new SpannableString("注: 如果您是外勤工作状态, 请选择外勤打卡");
        //spannableString.setSpan(new ForegroundColorSpan(0xff1fac89), 9, 13, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                mIsCheckOutWork = true;
                Intent intent = new Intent(, LegWorkCheckInActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(0xff7BA749);
                ds.setUnderlineText(false);
                ds.setTextSize(DisplayUtil.sp2px(CheckInActivity3.this, 14));
            }
        }, spannableString.length() - 4, spannableString.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        mTxtTips.setText(spannableString);
        mTxtTips.setMovementMethod(LinkMovementMethod.getInstance());*/
    }

    protected void initVariable() {
        AppContext.getLocationClient().setLocationListener(this);

        LocalDateTime now = LocalDateTime.now();
        if (now.getHourOfDay() > 12) {
            mCheckinType = 2;
            mCheckinTypeShangBan.setCheckinType(false);
            mCheckinTypeXiaBan.setCheckinType(true);
        }
        MultiplePermissionsListener feedbackViewMultiplePermissionListener = new MyMultiplePermissionListener(this);
        mAllPermissionsListener = new CompositeMultiplePermissionsListener(feedbackViewMultiplePermissionListener,
                SnackbarOnAnyDeniedMultiplePermissionsListener.Builder.with(mRootView,
                        "需要开启权限, 才可以开启打卡功能")
                        .withOpenSettingsButton("设置")
                        .build());

        Dexter.continuePendingRequestsIfPossible(mAllPermissionsListener);
        if (Dexter.isRequestOngoing()) {
            return;
        }
        Dexter.checkPermissions(mAllPermissionsListener, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


    protected void onBtnClick() {
        /*if (mWhosYourDaddy) {
            if ((mCheckinType == 1 && mBody.ask_cover_today_start == 1)
                    || (mCheckinType == 2 && mBody.ask_cover_today_end == 1)
                    || (mBody.ask_chose_yesterday_end == 1)) {
                checkSettings();
            } else {
                getAuthCode();
            }
        } else {
            if (checkForm()) {
                if ((mCheckinType == 1 && mBody.ask_cover_today_start == 1)
                        || (mCheckinType == 2 && mBody.ask_cover_today_end == 1)
                        || (mBody.ask_chose_yesterday_end == 1)) {
                    checkSettings();
                } else {
                    mIsConver = 1;
                    getAuthCode();
                }
            }
        }*/
    }

    /**
     * 跨夜第一个判断：用户昨天是否有下班卡跨夜打卡时，用户选择作为今天的下班卡后，
     * 需要作第二个判断：今天是否有下班卡，若有，需要询问用户是否覆盖之前的下班卡
     */
    private void checkSettings() {
        /*if (mBody != null) {
            if (mCheckinType == 2 && mBody.ask_chose_yesterday_end == 1) {
                //弹窗询问是否作为前日的下班卡
                mCheckinTypeConfirmDialog = ClockConfirmDialog.newInstance(R.layout.clock_confirm_dialog_step1_layout, R.layout.dialog_checkin_type, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.btn_confirm://今天的下班卡
                                mIsYesterdayEnd = 0;
                                //mIsConver = 1;
                                break;
                            case R.id.btn_cancel://昨天的下班卡
                                mIsYesterdayEnd = 1;
                                //mIsConver = 1;
                                break;
                        }
                        mCheckinTypeConfirmDialog.dismiss();
                        if (mCheckinType == 1 && mBody.ask_cover_today_start == 1&& mIsYesterdayEnd == 0) {
                            isCover();
                        } else if (mCheckinType == 2 && mBody.ask_cover_today_end == 1&& mIsYesterdayEnd == 0) {
                            isCover();
                        } else {
                            getAuthCode();
                        }

                    }
                });

                mCheckinTypeConfirmDialog.show(getSupportFragmentManager(), "checkin_type");
            } else if (mCheckinType == 1 && mBody.ask_chose_yesterday_end == 1) {
                //弹窗询问是否作为前日的下班卡
                mCheckinTypeConfirmDialog = ClockConfirmDialog.newInstance(R.layout.clock_confirm_dialog_step1_layout_2, R.layout.dialog_checkin_type_2, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.btn_confirm://今天的上班卡
                                mIsYesterdayEnd = 0;
                                break;
                            case R.id.btn_cancel://昨天的下班卡
                                mIsYesterdayEnd = 1;
                                //mIsConver = 1;
                                break;
                        }
                        mCheckinTypeConfirmDialog.dismiss();
                        if (mCheckinType == 1 && mBody.ask_cover_today_start == 1 && mIsYesterdayEnd == 0) {
                            isCover();
                        } else if (mCheckinType == 2 && mBody.ask_cover_today_end == 1 && mIsYesterdayEnd == 0) {
                            isCover();
                        } else {
                            getAuthCode();
                        }
                    }
                });
                mCheckinTypeConfirmDialog.show(getSupportFragmentManager(), "checkin_type");
            } else if (mCheckinType == 1 && mBody.ask_cover_today_start == 1) {
                //询问是否覆盖今天早上的打卡
                mCheckinTypeConfirmDialog = ClockConfirmDialog.newInstance(R.layout.clock_confirm_cover_dialog_step2_layout, R.layout.dialog_checkin_start_conver, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCheckinTypeConfirmDialog.dismiss();
                        switch (v.getId()) {
                            case R.id.btn_confirm://覆盖
                                mIsConver = 1;
                                getAuthCode();
                                break;
                            case R.id.btn_cancel://放弃
                                mIsConver = 0;
                                getAuthCode();
                                break;
                        }

                    }
                });

                mCheckinTypeConfirmDialog.show(getSupportFragmentManager(), "conver_start");
            } else if (mCheckinType == 2 && mBody.ask_cover_today_end == 1) {
                //询问是否覆盖今天晚上的打卡
                mCheckinTypeConfirmDialog = ClockConfirmDialog.newInstance(R.layout.clock_confirm_cover_dialog_step2_layout, R.layout.dialog_checkin_end_conver, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCheckinTypeConfirmDialog.dismiss();
                        switch (v.getId()) {
                            case R.id.btn_confirm://覆盖
                                mIsConver = 1;
                                getAuthCode();
                                break;
                            case R.id.btn_cancel://放弃
                                mIsConver = 0;
                                getAuthCode();
                                //finish();
                                break;
                        }
                    }
                });

                mCheckinTypeConfirmDialog.show(getSupportFragmentManager(), "conver_end");
            }
        }*/
    }

    private void isCover() {
        /*if (mCheckinType == 1 && mBody.ask_cover_today_start == 1) {
            //询问是否覆盖今天早上的打卡
            mCheckinTypeConfirmDialog = ClockConfirmDialog.newInstance(R.layout.clock_confirm_cover_dialog_step2_layout, R.layout.dialog_checkin_start_conver, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCheckinTypeConfirmDialog.dismiss();
                    switch (v.getId()) {
                        case R.id.btn_confirm://覆盖
                            mIsConver = 1;
                            getAuthCode();
                            break;
                        case R.id.btn_cancel://放弃
                            mIsConver = 0;
                            getAuthCode();
                            //finish();
                            break;
                    }

                }
            });

            mCheckinTypeConfirmDialog.show(getSupportFragmentManager(), "conver_start");
        } else if (mCheckinType == 2 && mBody.ask_cover_today_end == 1) {
            //询问是否覆盖今天晚上的打卡
            mCheckinTypeConfirmDialog = ClockConfirmDialog.newInstance(R.layout.clock_confirm_cover_dialog_step2_layout, R.layout.dialog_checkin_end_conver, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCheckinTypeConfirmDialog.dismiss();
                    switch (v.getId()) {
                        case R.id.btn_confirm://覆盖
                            mIsConver = 1;
                            getAuthCode();
                            break;
                        case R.id.btn_cancel://放弃
                            mIsConver = 0;
                            getAuthCode();
                            //finish();
                            break;
                    }
                }
            });

            mCheckinTypeConfirmDialog.show(getChildFragmentManager(), "conver_end");
        }*/
    }

    private boolean checkForm() {
        if (!IS_LOCATION_SUCCESS) {
            Toast.makeText(getActivity(), "亲，定位成功后才能打卡", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mBody == null) {
            Toast.makeText(getActivity(), "亲，请等待加载完成", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mPlace == null) {
            Toast.makeText(getActivity(), "管理员未设置打卡地点,不能打卡", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mBody.data.place_list != null && mBody.data.place_list.size() != 0) {
            if (mPlace == null) {
                Toast.makeText(getActivity(), "亲，请选择一个打卡位置再打卡", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (mPlace.distance > mBody.data.checkin_distance) {
                Toast.makeText(getActivity(), "当前地址距离打卡位置超出有效范围", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        if (mBody.data.need_take_photo == 1 && (mTmpFile == null || mTmpFile.length() == 0)) {
            Toast.makeText(getActivity(), "亲，请点击拍照按钮拍照后再打卡", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onDestroy() {
        try {
            if (AppContext.getLocationClient().isStarted()) {
                AppContext.getLocationClient().stopLocation();
            }

            AppContext.getLocationClient().unRegisterLocationListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariable();
        initView();
        initData();
        initListener();
    }

    public void showPermissionGranted(String permission) {
        System.out.println("showPermissionGranted permission=" + permission);
        if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
            mRetryWrapper.setVisibility(View.GONE);
            mProgressbarWrapper.setVisibility(View.VISIBLE);
            initGPS();
        }
    }


    public void showPermissionDenied(String permission, boolean isPermanentlyDenied) {
        System.out.println("showPermissionDenied permission=" + permission + " ,isPermanentlyDenied=" + isPermanentlyDenied);
        if (permission.equals(Manifest.permission.ACCESS_FINE_LOCATION) || permission.equals(Manifest.permission.READ_PHONE_STATE)) {
            mProgressbarWrapper.setVisibility(View.GONE);
            mRetryWrapper.setVisibility(View.VISIBLE);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showPermissionRationale(final PermissionToken token) {
        new AlertDialog.Builder(getActivity()).setTitle("需要定位权限")
                .setMessage("打卡功能需要定位权限才可以开启, 请允许它!")
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.cancelPermissionRequest();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        token.continuePermissionRequest();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        token.cancelPermissionRequest();
                    }
                })
                .show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.checkin_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (checkForm()) {
            if (mProgressDialog == null) {
                mProgressDialog = new MaterialDialog.Builder(getActivity())
                        .cancelable(false)
                        .content("打卡中...")
                        .progress(true, 0)
                        .show();
            } else {
                mProgressDialog.show();
            }
            getAuthCode();
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.checkin_gaode_layout2, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
