package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.DistanceConsider;

import java.util.List;

public class OneBtnSignInActivity extends AppCompatActivity {

    private TextView tv_location;
    private Context context;
    private static double EARTH_RADIUS = 6378.137;
    private LocationManager lManager;
    private SharedPreferences sp;
    public static double longitude = 0.0;
    public static double latitude = 0.0;
    private double teacherLongitude = 0.0;
    private double teacherLatitude = 0.0;
    private int distanceLimit = 100;
    private Button oneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_btn_sign_in);
        double value = getDistance(112.37544503, 32.72238775, 112.3752777783, 32.7222221667);
        Log.i("DistanceCalculate", String.valueOf(value));

        oneButton = findViewById(R.id.one_btn);
        oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                do{
//                    if(longitude != 0.0 && latitude != 0.0 && teacherLongitude != 0.0 && teacherLatitude != 0.0){
//                        if(getDistance(longitude, latitude, teacherLongitude, teacherLatitude) > OneBtnSignInSettingActivity.distanceLimit){
//                            Toast.makeText(OneBtnSignInActivity.this, "超出签到范围！", Toast.LENGTH_SHORT).show();
//                        }else{
//                            Toast.makeText(OneBtnSignInActivity.this, "签到成功！", Toast.LENGTH_SHORT).show();
//                            longitude = 0.0;
//                            latitude = 0.0;
//                            OneBtnSignInActivity.this.finish();
//                        }
//                    }
//                }while(longitude == 0.0 || latitude == 0.0 || teacherLongitude == 0.0 || teacherLatitude == 0.0);
                final ProgressDialog progressDialog = new ProgressDialog(OneBtnSignInActivity.this);
                progressDialog.setMessage("位置信息获取中...");
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showProgressDialog(progressDialog);
                    }
                }, 2000);
            }
        });

        context = this;
        tv_location = (TextView) findViewById(R.id.location_Tv);

        //获取LocationManager
        lManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        List<String> providers = lManager.getAllProviders();
        //获取的条件
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//获取精准位置
        criteria.setCostAllowed(true);//允许产生开销
        criteria.setPowerRequirement(Criteria.POWER_HIGH);//消耗大的话，获取的频率高
        criteria.setSpeedRequired(true);//手机位置移动
        criteria.setAltitudeRequired(true);//海拔

        //获取最佳provider: 手机或者模拟器上均为gps
        String bestProvider = lManager.getBestProvider(criteria, true);//使用GPS卫星

        System.out.println("最好的位置提供者是" + bestProvider);
        sp = getSharedPreferences("config", MODE_PRIVATE);

        //parameter: 1. provider 2. 每隔多少时间获取一次  3.每隔多少米  4.监听器触发回调函数
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        MyLocationListener myLocationListener = new MyLocationListener();
        lManager.requestLocationUpdates(bestProvider, 60000, 100, myLocationListener);

    }

    @Override
    public void onBackPressed() {
        longitude = 0.0;
        latitude = 0.0;
        finish();
    }

    public static double getDistance(double lngLocation, double latLocation, double lngTarget, double latTarget) {
        double lat1 = rad(latLocation);
        double lat2 = rad(latTarget);
        double lat = lat1 - lat2;
        double lng = rad(lngLocation) - rad(lngTarget);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(lat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(lng / 2), 2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000d) / 10000d;
        distance = distance * 1000;
        return distance;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    protected void showProgressDialog(final ProgressDialog progressDialog){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(OneBtnSignInActivity.this)
                        .setMessage("一键签到成功！")
                        .setPositiveButton("确定", null);
                builder.show();
            }
        });
    }

}
