package com.example.test;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class MyLocationListener implements LocationListener {
    /**
     *          * 手机位置发生变动
     *         
     */
    public void onLocationChanged(Location location) {
//        location.getAccuracy();//精确度
//        String latitude = location.getLatitude()+"";//经度
//        String longitude = location.getLongitude()+"";//纬度
//
//
//        //将获取到的经纬度信息存入SharedPreferences
//        SharedPreferences.Editor editor = sp.edit();
//        editor.putString("lastloaction", latitude + "-" + longitude);
//        editor.commit();
        //经度
        OneBtnSignInActivity.longitude = location.getLongitude();
        //纬度
        OneBtnSignInActivity.latitude = location.getLatitude();

//            tv_location.setText("经度:==>" + longitude + " \n 纬度==>" + latitude + "\n" + "海拔==>" + altitude);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
