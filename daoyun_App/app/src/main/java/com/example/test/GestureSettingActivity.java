package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.util.SPUtil;
import com.example.test.widget.GraphicLockView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GestureSettingActivity extends AppCompatActivity {

    private GraphicLockView graphicLockView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_setting);
        graphicLockView = findViewById(R.id.lock_view);
        Log.i("settingException", "screenshot fail4");
        SharedPreferences preferences = getSharedPreferences("sigin", MODE_PRIVATE);
        GraphicLockView.mPassword = preferences.getString("gestureSignIn", null);
        OneBtnSignInSettingActivity.startOrNot = preferences.getBoolean("oneBtnSignIn", false);
        if(GraphicLockView.mPassword != null){
            AlertDialog.Builder dialog = new AlertDialog.Builder(GestureSettingActivity.this)
                    .setTitle("签到手势设置")
                    .setMessage("签到手势已设置，在手势签到结束前不能重新设置！")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GestureSettingActivity.this.finish();
                        }
                    });
                    dialog.show();
        }else if(OneBtnSignInSettingActivity.startOrNot == true){
            AlertDialog.Builder dialog = new AlertDialog.Builder(GestureSettingActivity.this)
                    .setTitle("签到手势设置")
                    .setMessage("设置失败！已有一个签到活动正在进行中！")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GestureSettingActivity.this.finish();
                        }
                    });
            dialog.show();
        }

        graphicLockView.setOnGraphicLockListener(new GraphicLockView.OnGraphicLockListener() {

            @Override
            public void LockSuccess(int what, final String password) {
                if (GraphicLockView.mPassword == null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(GestureSettingActivity.this)
                            .setTitle("签到手势设置")
                            .setMessage("手势解锁顺序为" + password + ",点击确定开始签到")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GraphicLockView.mPassword = password;
                            SharedPreferences.Editor editor = getSharedPreferences("sigin", MODE_PRIVATE).edit();
                            editor.putString("gestureSignIn", password);
                            editor.apply();
//                            String path = "../../../../config.properties";
//                            Properties properties = new Properties();
//                            InputStream inputStream = null;
//                            try {
//                                inputStream = new BufferedInputStream(new FileInputStream(path));
//                            } catch (FileNotFoundException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                properties.load(inputStream);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            properties.setProperty("gesturePassword", password);
                            SPUtil.put(GestureSettingActivity.this, "PASSWORD", password);
                            PropertiesUtill.setProperties(GestureSettingActivity.this, "gesturePassword", password);
                            startActivity(new Intent(GestureSettingActivity.this, FinishSignInActivity.class)
                            .putExtra("signin_mode","gesture_signin_mode"));
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                }else{
                    Toast.makeText(GestureSettingActivity.this, "手势密码已存在！", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void LockFailure() {
//                text.setText("与上次绘制的不一致" + GraphicLockView.mPassword);
                Toast.makeText(GestureSettingActivity.this, "设置失败！手势密码已存在！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void LockShort() {
                AlertDialog.Builder dialog = new AlertDialog.Builder(GestureSettingActivity.this)
                        .setTitle("签到手势设置")
                        .setMessage("签到手势至少四个点！")
                        .setPositiveButton("确定", null);
                dialog.show();
            }
        });
    }


}
