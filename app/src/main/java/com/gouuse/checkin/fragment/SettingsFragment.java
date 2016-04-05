package com.gouuse.checkin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.gouuse.checkin.R;
import com.gouuse.checkin.ui.LoginActivity;
import com.gouuse.checkin.utils.PreferencesUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhengjiong on 15/12/13.
 */
public class SettingsFragment extends Fragment {

    @Bind(R.id.btn_exit)
    Button btnExit;

    @OnClick({R.id.btn_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_exit:
                PreferencesUtils.clearAll(getActivity());
                Toast.makeText(getActivity(), "退出成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    public static SettingsFragment newInstance() {

        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings_layout, container, false);

        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
