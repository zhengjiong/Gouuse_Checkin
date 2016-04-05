package com.gouuse.checkin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.gouuse.checkin.AppContext;
import com.gouuse.checkin.R;
import com.gouuse.checkin.bean.Body;
import com.gouuse.checkin.bean.LoginBean;
import com.gouuse.checkin.utils.AESCrypt;
import com.gouuse.checkin.utils.PreferencesUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登陆
 * Created by zhengjiong on 15/12/14.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int CHOOSE_COMPANY = 1;
    private MaterialDialog mLoginDialog;
    private MaterialDialog mChoiceDialog;
    private Button mBtnLogin;
    private EditText mEditUsername;
    private EditText mEditPassword;

    private List<JSONObject> mCompanyObjects = new ArrayList<>();//选择的公司


    public void chooseCompany(String[] items){
        mLoginDialog.cancel();
        new MaterialDialog.Builder(LoginActivity.this)
                .title("选择公司")
                .items(items)
                .itemsCallbackSingleChoice(-1, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        /**
                         * If you use alwaysCallSingleChoiceCallback(), which is discussed below,
                         * returning false here won't allow the newly selected radio button to actually be selected.
                         **/
                        try {
                            JSONObject companyJsonObject = mCompanyObjects.get(which);
                            String companyId = companyJsonObject.names().get(0).toString();

                            mLoginDialog.show();
                            loginByRetrofit(companyId);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                })
                .positiveText("确定")
                .show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (mLoginDialog == null) {
                    mLoginDialog = new MaterialDialog.Builder(this)
                            //.title("")
                            .cancelable(false)
                            .content("登录中")
                            .progress(true, 0)
                            .show();
                } else {
                    mLoginDialog.show();
                }
                loginByRetrofit("");
                break;
        }
    }

    private void loginByRetrofit(String companyId){
        try {
            final AESCrypt aesCrypt = AESCrypt.getInstance();

            JSONObject infoJSONObject = new JSONObject();
            infoJSONObject.put("email", mEditUsername.getText().toString());
            infoJSONObject.put("password", mEditPassword.getText().toString());
            infoJSONObject.put("company_id", companyId);
            infoJSONObject.put("source", 3);
            infoJSONObject.put("version", "1.2.7");


            System.out.println("info=" + aesCrypt.encrypt(infoJSONObject.toString()));
            Call<Body<LoginBean>> call = AppContext.getApi().login(aesCrypt.encrypt(infoJSONObject.toString()));
            call.enqueue(new Callback<Body<LoginBean>>() {
                @Override
                public void onResponse(Call<Body<LoginBean>> call, Response<Body<LoginBean>> response) {
                    try {
                        if (response.code() != 200) {
                            loginToast("服务器忙, 请稍后再试!");
                            return;
                        }
                        Body<LoginBean> body = response.body();
                        if (body.code.equals("0")) {

                            PreferencesUtils.putString(LoginActivity.this, "token", body.data.token);
                            loginToast("登陆成功!");
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }else if (body.code.equals("10007")) {
                            //选择公司
                            HashMap<String, String> companyMap = new Gson().fromJson(body.data.company_info, HashMap.class);

                            final String[] items = new String[companyMap.size()];
                            Iterator i = companyMap.entrySet().iterator();

                            int j = 0;
                            while (i.hasNext()) {
                                Map.Entry<String, String> entry = (Map.Entry<String, String>) i.next();
                                String key = entry.getKey();
                                String value = entry.getValue();

                                items[j] = value;

                                JSONObject companyJsonObject = new JSONObject();
                                companyJsonObject.put(key, value);

                                mCompanyObjects.add(companyJsonObject);

                                j++;
                            }
                            new Handler(getMainLooper()).postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mLoginDialog.dismiss();
                                    chooseCompany(items);
                                }
                            }, 500);
                        } else {
                            loginToast("登陆失败!");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        loginToast("登陆失败!");
                    }
                }

                @Override
                public void onFailure(Call<Body<LoginBean>> call, Throwable t) {
                    loginToast("登陆失败!");
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginToast(final String message){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoginDialog.dismiss();
                if (message != null) {
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        }, 1000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        mBtnLogin = (Button) findViewById(R.id.btn_login);
        mEditUsername = (EditText) findViewById(R.id.edit_username);
        mEditPassword = (EditText) findViewById(R.id.edit_password);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitle("登录");
        setSupportActionBar(toolbar);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用

        mBtnLogin.setOnClickListener(this);
    }


}
