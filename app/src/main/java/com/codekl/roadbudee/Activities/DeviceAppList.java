package com.codekl.roadbudee.Activities;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.codekl.roadbudee.Model.DeviceAppListModel;

import java.util.List;

/**
 * Created by MonsieurPetion on 2017-11-16.
 */

public class DeviceAppList extends AppCompatActivity {
    public DeviceAppList activity;
    private static final String TAG = "LockOtherApp";
    DeviceAppListModel deviceAppListModel =  new DeviceAppListModel();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public void findOtherPackageName(Context context){

        final PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo packageInfo : packages) {
            deviceAppListModel.getDeviceInfo().put(packageInfo.packageName,packageInfo.sourceDir);
            Log.d(TAG, "Installed package :" + packageInfo.packageName);
            Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
            Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));

        }
    }




}

