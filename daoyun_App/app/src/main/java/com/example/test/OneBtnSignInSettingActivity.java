package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test.widget.GraphicLockView;

public class OneBtnSignInSettingActivity extends AppCompatActivity {

    private EditText distanceLimitET;
    private Button startOneBtn;
    public static boolean startOrNot = false;
    public static int distanceLimit = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences preferences = getSharedPreferences("sigin", MODE_PRIVATE);


        setContentView(R.layout.activity_one_btn_sign_in_setting);
        distanceLimitET = findViewById(R.id.diatance_limit_Et);
        startOneBtn = findViewById(R.id.start_one_btn);
        startOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GraphicLockView.mPassword == null && OneBtnSignInSettingActivity.startOrNot == false){
                    startOrNot = true;
                    distanceLimit = Integer.valueOf(distanceLimitET.getText().toString());
                    SharedPreferences.Editor editor = getSharedPreferences("sigin", MODE_PRIVATE).edit();
                    editor.putBoolean("oneBtnSignIn", true);
                    editor.putInt("distanceLimit", distanceLimit);
                    editor.apply();
                    final ProgressDialog progressDialog = new ProgressDialog(OneBtnSignInSettingActivity.this);
                    progressDialog.setMessage("位置信息获取中...");
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showProgressDialog(progressDialog);

                        }
                    }, 2000);
                }else if(OneBtnSignInSettingActivity.startOrNot == true){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(OneBtnSignInSettingActivity.this)
                            .setTitle("一键签到设置")
                            .setMessage("一键签到已设置，在本次签到结束前不能重新设置！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    OneBtnSignInSettingActivity.this.finish();
                                }
                            });
                    dialog.show();
                }else if(GraphicLockView.mPassword != null){
                    AlertDialog.Builder dialog = new AlertDialog.Builder(OneBtnSignInSettingActivity.this)
                            .setTitle("一键签到设置")
                            .setMessage("设置失败！已有一个签到活动正在进行中！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    OneBtnSignInSettingActivity.this.finish();
                                }
                            });
                    dialog.show();
                }

            }
        });
    }

    protected void showProgressDialog(final ProgressDialog progressDialog){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                startActivity(new Intent(OneBtnSignInSettingActivity.this, FinishSignInActivity.class)
                        .putExtra("signin_mode", "one_btn_mode"));
            }
        });
    }

}
