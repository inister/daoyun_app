package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.test.util.SPUtil;
import com.example.test.widget.GraphicLockView;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FinishSignInActivity extends AppCompatActivity {

    private Button finishSignInBtn;
    private TextView signInNumTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_sign_in);
        final String signin_mode = getIntent().getStringExtra("signin_mode");
        finishSignInBtn = findViewById(R.id.finish_signin_btn);
        signInNumTV = findViewById(R.id.signIn_Tv);
        finishSignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signin_mode.equals("gesture_signin_mode")){
                    GraphicLockView.mPassword = null;
//                    String path = "../../../../config.properties";
//                    Properties properties = new Properties();
//                    InputStream inputStream = null;
//                    try {
//                        inputStream = new BufferedInputStream(new FileInputStream(path));
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        properties.load(inputStream);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    properties.setProperty("gesturePassword", null);
                    SharedPreferences.Editor editor = getSharedPreferences("sigin", MODE_PRIVATE).edit();
                    editor.putString("gestureSignIn", null);
                    editor.apply();
                    AlertDialog.Builder builder = new AlertDialog.Builder(FinishSignInActivity.this)
                            .setMessage("成功结束手势签到!")
                            .setPositiveButton("确定", null);
                    builder.show();
                    PropertiesUtill.setProperties(FinishSignInActivity.this, "gesturePassword", "");
                    SPUtil.clear(FinishSignInActivity.this);
                }else if(signin_mode.equals("one_btn_mode")){
                    OneBtnSignInSettingActivity.startOrNot = false;
                    SharedPreferences.Editor editor = getSharedPreferences("sigin", MODE_PRIVATE).edit();
                    editor.putBoolean("oneBtnSignIn", false);
                    editor.putInt("distanceLimit", -1);
                    editor.apply();
                    AlertDialog.Builder builder = new AlertDialog.Builder(FinishSignInActivity.this)
                            .setMessage("成功结束一键签到!")
                            .setPositiveButton("确定", null);
                    builder.show();
                }

            }
        });
    }
}
