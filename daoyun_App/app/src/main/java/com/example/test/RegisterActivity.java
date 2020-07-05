package com.example.test;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    protected EditText userNameET;
    protected EditText verificationET;
    protected Button sendCodeBtn;
    protected EditText passwordET;
    protected EditText passwordConfirmET;
    protected Button registerBtn;
    private int verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        File file = new File("/data/data/" + getPackageName().toString() + "/shared_prefs/","15606935509.xml");
        if(file.exists()){
            file.delete();
        }
        File file1 = new File("/data/data/" + getPackageName().toString() + "/shared_prefs/","15606935508.xml");
        if(file1.exists()){
            file1.delete();
        }
        userNameET = findViewById(R.id.et_login_username);
        verificationET = findViewById(R.id.et_reg_vericode);
        sendCodeBtn = findViewById(R.id.bt_veri_submit);
        passwordET = findViewById(R.id.et_login_pwd);
        passwordConfirmET = findViewById(R.id.et_reg_conf_pwd);
        registerBtn = findViewById(R.id.bt_login_submit);
        sendCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                verificationCode = r.nextInt(899999) + 100000;
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                        .setTitle("验证码")
                        .setMessage("验证码为：" + verificationCode)
                        .setPositiveButton("确定", null);
                builder.show();
                sendCodeBtn.setText("已发送");
                sendCodeBtn.setEnabled(false);
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verificationET.getText().toString().equals(verificationCode+"")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                            .setMessage("验证码错误！")
                            .setPositiveButton("确定", null);
                    builder.show();
                    sendCodeBtn.setText("发送验证码");
                    sendCodeBtn.setEnabled(true);
                }else if(!passwordET.getText().toString().equals(passwordConfirmET.getText().toString())){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                            .setMessage("两次密码输入不一致！")
                            .setPositiveButton("确定", null);
                    builder.show();
                }else if(passwordET.getText().toString().length() < 6){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                            .setMessage("密码最低为6位！")
                            .setPositiveButton("确定", null);
                    builder.show();
                }else if(new File("/data/data/" + getPackageName().toString() + "/shared_prefs/",
                        userNameET.getText().toString() + ".xml").exists()){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                            .setMessage("该账号已被注册！")
                            .setPositiveButton("确定", null);
                    builder.show();
                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpClient okHttpClient = new OkHttpClient();
                            RequestBody requestBody = new FormBody.Builder()
                                    .add("userName", userNameET.getText().toString())
                                    .add("password", passwordET.getText().toString())
                                    .build();
                            Request request = new Request.Builder()
                                    .url("http://39.106.229.1:8080/project_training_daoyun/user/aregister")
                                    .post(requestBody)
                                    .build();
                            okHttpClient.newCall(request).enqueue(new Callback() {
                                @Override
                                public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                                    Toast.makeText(RegisterActivity.this, "Connection failed!", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                    String responseBodyStr = response.body().string();
                                    Log.i("RegisterInfo", responseBodyStr);
                                    SharedPreferences.Editor editor = getSharedPreferences(userNameET.getText().toString(), MODE_PRIVATE).edit();
                                    editor.putString("password", passwordET.getText().toString());
                                    editor.apply();
                                    showAlertDialog();
                                }
                            });
                        }
                    }).start();
                }
            }
        });
    }

    protected void showAlertDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                        .setMessage("注册成功！")
                        .setPositiveButton("确定", null);
                builder.show();
            }
        });
    }
}
