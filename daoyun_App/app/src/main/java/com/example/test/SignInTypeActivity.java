package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignInTypeActivity extends AppCompatActivity {

    private Button setOneBtn;
    private Button setGestureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_type);
        setOneBtn = findViewById(R.id.set_one_btn);
        setOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInTypeActivity.this, OneBtnSignInSettingActivity.class));
            }
        });
        setGestureBtn = findViewById(R.id.set_gesture_btn);
        setGestureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInTypeActivity.this, GestureSettingActivity.class));
            }
        });
    }
}
