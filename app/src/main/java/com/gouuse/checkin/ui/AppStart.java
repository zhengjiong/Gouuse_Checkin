package com.gouuse.checkin.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gouuse.checkin.utils.PreferencesUtils;

/**
 * Created by zhengjiong on 15/12/14.
 */
public class AppStart extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        String token = PreferencesUtils.getString(this, "token", null);
        if (token == null || "".equals(token)) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }
        finish();
    }
}
