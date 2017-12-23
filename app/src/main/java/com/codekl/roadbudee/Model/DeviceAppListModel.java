package com.codekl.roadbudee.Model;

import java.util.HashMap;

/**
 * Created by officemobile on 2017-11-22.
 */

public class DeviceAppListModel {

    private static HashMap<String,String> deviceInfo;
    private String packageName;


    public HashMap<String, String> getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(HashMap<String, String> deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
