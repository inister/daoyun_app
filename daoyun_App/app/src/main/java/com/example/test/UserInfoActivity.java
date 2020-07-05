package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.tv.TvContentRating;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class UserInfoActivity extends AppCompatActivity {

    protected RadioButton maleRadioButton;
    protected RadioButton femaleRadioButton;
    protected RadioButton teacherRadioButton;
    protected RadioButton studentRadioButton;
    protected RadioButton elseRadioButton;
    protected Button backButton;
    protected TextView userNameTV;
    protected TextView userPhoneTV;
    protected LinearLayout birthYearLayout;
    protected TextView birthYearTV;
    protected LinearLayout schoolLayout;
    protected TextView schoolTV;
    protected Button saveBtn;
    protected EditText nameET;
    protected EditText idET;
    protected String schoolStr;
    protected String nameStr;
    protected String sex;
    protected String role;
    protected String idNum;
    protected String selectedYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        maleRadioButton = findViewById(R.id.rbtn_male);
        femaleRadioButton = findViewById(R.id.rbtn_female);
        teacherRadioButton = findViewById(R.id.rbtn_teacher);
        studentRadioButton = findViewById(R.id.rbtn_student);
        elseRadioButton = findViewById(R.id.rbtn_else);
        userNameTV = findViewById(R.id.user_name_Tv);
        userPhoneTV = findViewById(R.id.user_phone_Tv);
        birthYearLayout = findViewById(R.id.birth_year_layout);
        birthYearTV = findViewById(R.id.birth_year_Tv);
        schoolLayout = findViewById(R.id.school_layout);
        schoolTV = findViewById(R.id.school_Tv);
        saveBtn = findViewById(R.id.save_info_btn);
        nameET = findViewById(R.id.name_Et);
        idET = findViewById(R.id.id_Et);
//        userNameTV.setText(MainActivity.userName);
        if(MainActivity.userName.equals("admin")){
            userPhoneTV.setText("15900000001");
        }else if(MainActivity.userName.equals("teacher")){
            userPhoneTV.setText("15900000002");
        }else if(MainActivity.userName.equals("student1")){
            userPhoneTV.setText("15900000003");
        }else  if(MainActivity.userName.equals("student2")){
            userPhoneTV.setText("15900000004");
        }else{
            userPhoneTV.setText(MainActivity.userName);
        }
        initUi();

        final String[] year = new String[]{"1987年", "1988年", "1989年", "1990年", "1991年", "1992年", "1993年",
                "1994年", "1995年", "1996年", "1997年", "1998年", "1999年", "2000年", "2001年", "2002年", "2003年",
                "2004年", "2005年"};
        birthYearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoActivity.this)
                        .setTitle("选择出生年份")
                        .setSingleChoiceItems(year, 1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedYear = year[which];
                            }
                        });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        birthYearTV.setText(selectedYear);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });

        schoolLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(UserInfoActivity.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoActivity.this)
                        .setTitle("请输入学校及院系")
                        .setView(editText);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        schoolStr = editText.getText().toString();
                        schoolTV.setText(schoolStr);
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });

        maleRadioButton.setOnCheckedChangeListener(mChangeListener);
        femaleRadioButton.setOnCheckedChangeListener(mChangeListener);
        teacherRadioButton.setOnCheckedChangeListener(mChangeListener_1);
        studentRadioButton.setOnCheckedChangeListener(mChangeListener_1);
        elseRadioButton.setOnCheckedChangeListener(mChangeListener_1);

        backButton = findViewById(R.id.toolbar_left_btn);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        nameStr = nameET.getText().toString();
//                        idNum = idET.getText().toString();
//                        Log.i("returnLoginInfo",MainActivity.userName + " " + nameStr + ""
//                        + idNum + " " + selectedYear + " " + sex + " " + schoolStr + " " + role);
//                        OkHttpClient okHttpClient = new OkHttpClient();
//                        RequestBody requestBody = new FormBody.Builder()
//                                .add("userName", MainActivity.userName)
//                                .add("realName", nameStr)
//                                .add("studentId", idNum)
//                                .add("birthday", selectedYear)
//                                .add("sex", sex)
//                                .add("school", schoolStr)
//                                .add("role", role)
//                                .build();
//                        Request request = new Request.Builder()
//                                .url("http://39.106.229.1:8080/project_training_daoyun/user/asave")
//                                .post(requestBody)
//                                .build();
//                        okHttpClient.newCall(request).enqueue(new Callback() {
//                            @Override
//                            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                                Toast.makeText(UserInfoActivity.this, "Connection failed!", Toast.LENGTH_SHORT).show();
//                            }
//
//                            @Override
//                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                                String responseBodyStr = response.body().string();
//                                Log.i("returnLoginInfo", responseBodyStr);
//
//                            }
//                        });
//                    }
//                }).start();
                nameStr = nameET.getText().toString();
                idNum = idET.getText().toString();
                Log.i("returnLoginInfo",MainActivity.userName + " " + nameStr + " "
                        + idNum + " " + selectedYear + " " + sex + " " + schoolStr + " " + role);
                SharedPreferences.Editor editor = getSharedPreferences(MainActivity.userName, MODE_PRIVATE).edit();
                editor.putString("name", nameStr);
                editor.putString("id", idNum);
                if(selectedYear != null){
                    editor.putString("birthYear", selectedYear);
                }
                if(sex != null){
                    editor.putString("sex", sex);
                }
                if(schoolStr != null){
                    editor.putString("school", schoolStr);
                }
                if(role != null){
                    editor.putString("role", role);
                }
                editor.apply();
                AlertDialog.Builder builder = new AlertDialog.Builder(UserInfoActivity.this)
                        .setMessage("用户信息保存成功！")
                        .setPositiveButton("确定", null);
                builder.show();
            }
        });

    }

    private void initUi(){
        SharedPreferences preferences = getSharedPreferences(MainActivity.userName, MODE_PRIVATE);
        if(preferences != null){
            String name = preferences.getString("name", "");
            String id = preferences.getString("id", "");
            String year = preferences.getString("birthYear", "未设置");
            String sex = preferences.getString("sex", "0");
            String school = preferences.getString("school", "请选择学校及院系");
            String role = preferences.getString("role", "teacher");
            if(!name.equals("")){
                userNameTV.setText(name);
            }
            nameET.setText(name);
            idET.setText(id);
            birthYearTV.setText(year);
            if(sex.equals("0")){
                maleRadioButton.setChecked(true);
                femaleRadioButton.setChecked(false);
            }else{
                maleRadioButton.setChecked(false);
                femaleRadioButton.setChecked(true);
            }
            schoolTV.setText(school);
            if(role.equals("teacher")){
                teacherRadioButton.setChecked(true);
                studentRadioButton.setChecked(false);
                elseRadioButton.setChecked(false);
            }else if(role.equals("student")){
                teacherRadioButton.setChecked(false);
                studentRadioButton.setChecked(true);
                elseRadioButton.setChecked(false);
            }else if(role.equals("else")){
                teacherRadioButton.setChecked(false);
                studentRadioButton.setChecked(false);
                elseRadioButton.setChecked(true);
            }
        }
    }

    OnCheckedChangeListener mChangeListener= new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(buttonView.getId()==R.id.rbtn_male && isChecked){
                sex = "0";
                femaleRadioButton.setChecked(false);
            }else if(buttonView.getId()==R.id.rbtn_female && isChecked){
                sex = "1";
                maleRadioButton.setChecked(false);
            }
        }
    };

    OnCheckedChangeListener mChangeListener_1 = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if(buttonView.getId()==R.id.rbtn_teacher && isChecked){
                role = "teacher";
                studentRadioButton.setChecked(false);
                elseRadioButton.setChecked(false);
            }else if(buttonView.getId()==R.id.rbtn_student && isChecked){
                role = "student";
                teacherRadioButton.setChecked(false);
                elseRadioButton.setChecked(false);
            }else if(buttonView.getId()==R.id.rbtn_else && isChecked){
                role = "else";
                teacherRadioButton.setChecked(false);
                studentRadioButton.setChecked(false);
            }
        }
    };



}
