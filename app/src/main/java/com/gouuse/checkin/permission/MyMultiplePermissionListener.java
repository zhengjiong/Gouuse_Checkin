/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gouuse.checkin.permission;

import android.Manifest;

import com.gouuse.checkin.fragment.CheckInFragment;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class MyMultiplePermissionListener implements MultiplePermissionsListener {

    private final CheckInFragment fragment;

    public MyMultiplePermissionListener(CheckInFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onPermissionsChecked(MultiplePermissionsReport report) {
        for (PermissionGrantedResponse response : report.getGrantedPermissionResponses()) {
            //授权通过
            fragment.showPermissionGranted(response.getPermissionName());
        }

        for (PermissionDeniedResponse response : report.getDeniedPermissionResponses()) {
            //授权未通过
            fragment.showPermissionDenied(response.getPermissionName(), response.isPermanentlyDenied());
        }
    }

    @Override
    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
        boolean locationPermission = false;
        boolean readPhoneStatePermission = false;

        for (PermissionRequest permission : permissions) {
            if (permission.getName().equals(Manifest.permission.ACCESS_FINE_LOCATION)) {
                //显示需要定位权限的dialog
                fragment.showPermissionRationale(token);
                locationPermission = true;
            }
            if (permission.getName().equals(Manifest.permission.READ_PHONE_STATE)) {
                //显示需要定位权限的dialog
                fragment.showPermissionRationale(token);
                readPhoneStatePermission = true;
            }
        }
        if (!locationPermission && !readPhoneStatePermission) {
            //如果没有提示需要定位权限,则定位权限已获得
            fragment.showPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }
}
