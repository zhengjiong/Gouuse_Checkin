package com.gouuse.checkin.ui;

import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.gouuse.checkin.R;
import com.gouuse.checkin.fragment.CheckInFragment;
import com.gouuse.checkin.fragment.SettingsFragment;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationView = (NavigationView) findViewById(R.id.navigationviews);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);

        initToolbar();
        initDrawerToggle();
        initNavigationView();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(
                            R.id.content_frame,
                            CheckInFragment.newInstance(),
                            String.valueOf(R.id.item1))
                    .commit();
        }
    }

    private void initNavigationView() {
        //NavigationView側滑菜單的點擊事件
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item1:
                        //Snackbar.make(mNavigationView, "item1 hasSubMenu " + menuItem.hasSubMenu(), Snackbar.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(
                                        R.id.content_frame,
                                        CheckInFragment.newInstance(),
                                        String.valueOf(menuItem.getItemId()))
                                .commit();
                        mToolbar.setTitle("手机打卡");
                        break;
                    case R.id.item2:
                        //Snackbar.make(mNavigationView, "item1 hasSubMenu " + menuItem.hasSubMenu(), Snackbar.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(
                                        R.id.content_frame,
                                        SettingsFragment.newInstance(),
                                        String.valueOf(menuItem.getItemId()))
                                .commit();
                        mToolbar.setTitle("设置");
                        break;
                }

                //Log.i("zj", "getGroupId()=" + menuItem.getGroupId());
                menuItem.setChecked(true);//設置item為選中狀態
                mDrawerLayout.closeDrawers();//關閉drawer

                return true;
            }
        });

        //設置進入的時候,默認選中第一個,下面兩種方式都可以
        mNavigationView.getMenu().getItem(0).setChecked(true);
//        mNavigationView.getMenu().findItem(R.id.item1).setChecked(true);

    }

    private void initToolbar() {
        mToolbar.setTitle("手机打卡");
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用

        /*if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }*/
    }

    private void initDrawerToggle() {
        //設置ActionBarDrawerToggle,和打開關閉listener
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.openRes, R.string.closeRes){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //drawer關閉
                Log.i("zj", "onDrawerClosed");
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //drawer打開
                Log.i("zj", "onDrawerOpened");
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    /**
     * onPostCreate是activity創建完成以後執行的
     *
     * 設置這個才可以顯示出左上角3條橫線的圖標
     * @param savedInstanceState
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }
}
