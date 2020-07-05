package com.example.test;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.test.util.StreamUtils;
import com.google.gson.JsonParser;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    protected Button mBtnRegister;
    protected Button mBtnLogin;
    protected EditText userNameET;
    protected EditText passwordET;
    protected CheckBox rememberUserCB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences.Editor editor = getSharedPreferences("teacher", MODE_PRIVATE).edit();
        SharedPreferences.Editor editor1 = getSharedPreferences("admin", MODE_PRIVATE).edit();
        SharedPreferences.Editor editor2 = getSharedPreferences("student1", MODE_PRIVATE).edit();
        SharedPreferences.Editor editor3 = getSharedPreferences("student2", MODE_PRIVATE).edit();
        editor.putString("beCreated", "created");
        editor1.putString("beCreated", "created");
        editor2.putString("beCreated", "created");
        editor3.putString("beCreated", "created");
        editor.apply();
        editor1.apply();
        editor2.apply();
        editor3.apply();
        PropertiesUtill.initProperties(LoginActivity.this);
        userNameET = findViewById(R.id.et_login_username);
        passwordET = findViewById(R.id.et_login_pwd);
        rememberUserCB = findViewById(R.id.cb_remember_login);

        SharedPreferences preferences = getSharedPreferences("remember_user", MODE_PRIVATE);
        if(!preferences.getString("userName","").equals("") && !preferences.getString("password","").equals("")){
            userNameET.setText(preferences.getString("userName",""));
            passwordET.setText(preferences.getString("password",""));
        }

        mBtnRegister= (Button) this.findViewById(R.id.bt_login_register);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));

            }
        });
        mBtnLogin = findViewById(R.id.bt_login_submit);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequestWithHttpURLConnection(userNameET.getText().toString(), passwordET.getText().toString());
            }
        });

        int checkStorePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int checkInternetPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET);
        int checkLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        int checkFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int checkReadStorePermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);
        int checkReadMountPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS);
        if(checkStorePermission != PackageManager.PERMISSION_GRANTED
                || checkInternetPermission != PackageManager.PERMISSION_GRANTED
                || checkLocationPermission != PackageManager.PERMISSION_GRANTED
                || checkFineLocationPermission != PackageManager.PERMISSION_GRANTED
                || checkReadStorePermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.INTERNET, Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, 0);
        }

        Properties properties = PropertiesUtill.getProperties();
//        InputStream inputStream = null;
//        try {
//            Context context = this;
////            inputStream = context.getAssets().open("config.properties");
//            inputStream = PropertiesUtill.class.getResourceAsStream("/assets/config");
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.i("LoginInfo", "inputstream fail");
//        }
//        try {
//            properties.load(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//            Log.i("LoginInfo", "load fail");
//        }
        PropertiesUtill.setProperties(LoginActivity.this,"gesturePassword", "");
//        Log.i("LoginInfo", PropertiesUtill.getProperties(LoginActivity.this, "gesturePassword"));
    }

    private void sendRequestWithHttpURLConnection(final String username, final String password){
        new Thread(new Runnable() {
            @Override
            public void run() {
//                HttpURLConnection connection = null;
//                try{
//                    URL url = new URL("http://www.baidu.com");
//                    connection = (HttpURLConnection) url.openConnection();
//                    connection.setRequestMethod("POST");
//                    connection.setConnectTimeout(8000);
//                    connection.setReadTimeout(8000);
//                    connection.connect();
//                    DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//                    out.writeBytes("userName=15900000003&password=123456");
//                    out.close();
//                    int responseCode = connection.getResponseCode();
//                    if(responseCode == 200){
//                        InputStream inputStream = connection.getInputStream();
//                        data = StreamUtils.inputSteam2String(inputStream);
//                        Log.i("LoginInfo", data);
//                        handler.obtainMessage(RESULT_OK, data).sendToTarget();
//                    }else {
//                        handler.obtainMessage(RESULT_CANCELED, responseCode).sendToTarget();
//                    }
//                }catch (Exception e){
//                    handler.obtainMessage(RESULT_CANCELED, e.getMessage()).sendToTarget();
//                    e.printStackTrace();
//                }finally {
//                    if(connection != null){
//                        connection.disconnect();
//                    }
//                }
                if(username.equals("admin") || username.equals("teacher") || username.equals("student1")
                        || username.equals("student2")){
                    OkHttpClient okHttpClient = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()
                            .add("userName", username)
                            .add("password", password)
                            .build();
                    Request request = new Request.Builder()
                            .url("http://39.106.229.1:8080/project_training_daoyun/user/alogin")
                            .post(requestBody)
                            .build();
                    okHttpClient.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                            Toast.makeText(LoginActivity.this, "Connection failed!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            String responseBodyStr = response.body().string();
                            Log.i("LoginInfo", responseBodyStr);
                            if(responseBodyStr.equals("\"success\"")){
                                SharedPreferences.Editor editor = getSharedPreferences("remember_user", MODE_PRIVATE).edit();
                                if(rememberUserCB.isChecked()){
                                    editor.putString("userName", username);
                                    editor.putString("password", password);
                                    editor.apply();
                                }else{
                                    editor.putString("userName", "");
                                    editor.putString("password", "");
                                    editor.apply();
                                }
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("username", username);
                                startActivity(intent);
                            }else if(responseBodyStr.equals("\"password_error\"")){
                                showAlertDialog("密码错误！");
                            }else if(responseBodyStr.equals("\"username_not_exist\"")){
                                showAlertDialog("用户名不存在！");
                            }
                        }
                    });
                }else {
                    SharedPreferences preferences;
                    if(!new File("/data/data/" + getPackageName().toString() + "/shared_prefs/",
                            username + ".xml").exists()){
                        showAlertDialog("用户名不存在！");
                    }else{
                        preferences = getSharedPreferences(username, MODE_PRIVATE);
                        if(!preferences.getString("password", "").equals(password)){
                            showAlertDialog("密码错误！");
                        }else {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("username", username);
                            startActivity(intent);
                        }
                    }

                }

            }
        }).start();
    }

    protected void showAlertDialog(final String msg){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                        .setMessage(msg)
                        .setPositiveButton("确定", null);
                builder.show();
            }
        });
    }

//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler(){
//        public void handleMessage(Message msg){
//            switch (msg.what){
//                case RESULT_OK:
//                    JSONObject object = null;
//                    try{
//                        object = new JSONObject(data);
//                        int code = object.getInt("code");
//                        if(code == 1){
//                            Toast.makeText(getBaseContext(), "code=1", Toast.LENGTH_SHORT).show();
//                        }else if(code == 0){
//                            Toast.makeText(getBaseContext(), "code=0", Toast.LENGTH_SHORT).show();
//                        }else if(code == -1){
//                            Toast.makeText(getBaseContext(), "code=-1", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getBaseContext(), "Not match", Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case RESULT_CANCELED:
//                    Toast.makeText(getBaseContext(), "服务器繁忙...", Toast.LENGTH_SHORT).show();
//                    break;
//            }
//        }
//    };

}
