package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.test.util.SPUtil;
import com.example.test.widget.GraphicLockView;

public class GestureUnlockActivity extends AppCompatActivity {

    private GraphicLockView graphicLockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_unlock);
        graphicLockView = findViewById(R.id.lock_view1);
        graphicLockView.setOnGraphicLockListener(new GraphicLockView.OnGraphicLockListener() {

            @Override
            public void LockSuccess(int what, String password) {
                AlertDialog.Builder builder = new AlertDialog.Builder(GestureUnlockActivity.this)
                        .setTitle("手势签到")
                        .setMessage("手势签到成功！")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                GestureUnlockActivity.this.finish();
                            }
                        });
                builder.show();
            }

            @Override
            public void LockFailure() {
                AlertDialog.Builder builder = new AlertDialog.Builder(GestureUnlockActivity.this)
                        .setTitle("手势签到")
                        .setMessage("手势签到失败！请重新尝试")
                        .setPositiveButton("确定", null);
                builder.show();
            }

            @Override
            public void LockShort() {
                AlertDialog.Builder builder = new AlertDialog.Builder(GestureUnlockActivity.this)
                        .setTitle("手势签到")
                        .setMessage("最少连接四个点")
                        .setPositiveButton("确定", null);
                builder.show();
            }
        });
    }
}
