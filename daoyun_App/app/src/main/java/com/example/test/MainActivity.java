package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.test.fragment.FindFragment;
import com.example.test.fragment.MainFragment;
import com.example.test.fragment.MeFragment;

import java.util.Properties;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    protected LinearLayout mMenuMain;
    protected LinearLayout mMenuFind;
    protected LinearLayout mMenuMe;
    protected ImageView mainImageView;
    protected ImageView findImageView;
    protected ImageView meImageView;
    protected MainFragment mMainFragment=new MainFragment();//首页
    protected FindFragment mFindFragment=new FindFragment();//发现
    protected MeFragment mMeFragment=new MeFragment();//我的
    public static String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        userName = intent.getStringExtra("username");
        if(userName.equals("15900000001")){
            userName = "admin";
        }else if(userName.equals("15900000002")){
            userName = "teacher";
        }else if(userName.equals("15900000003")){
            userName = "student1";
        }else  if(userName.equals("15900000004")){
            userName = "student2";
        }
        Log.i("MainActivityInfo", userName);
//        Log.i("LoginInfo", PropertiesUtill.getProperties(MainActivity.this, "gesturePassword"));
        initView();
        //获取管理类
        this.getSupportFragmentManager()
                .beginTransaction()
            .add(R.id.container_content,mMainFragment)
            .add(R.id.container_content,mFindFragment)
                .hide(mFindFragment)
                .add(R.id.container_content,mMeFragment)
                .hide(mMeFragment)
        //事物添加  默认：显示首页  其他页面：隐藏
        //提交
        .commit();
    }


    /**
     * 初始化视图
     */
    public void initView(){
        mMenuMain= (LinearLayout) this.findViewById(R.id.menu_main);
        mMenuFind= (LinearLayout) this.findViewById(R.id.menu_find);
        mMenuMe= (LinearLayout) this.findViewById(R.id.menu_me);
        mainImageView = this.findViewById(R.id.Iv_main);
        findImageView = this.findViewById(R.id.Iv_find);
        meImageView = this.findViewById(R.id.Iv_me);

        mMenuMain.setOnClickListener(this);
        mMenuFind.setOnClickListener(this);
        mMenuMe.setOnClickListener(this);

        mainImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.nav_main_click));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.menu_main://首页
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(mMainFragment)
                        .hide(mFindFragment)
                        .hide(mMeFragment)
                        .commit();
                mainImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.nav_main_click));
                findImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.nav_find_normal));
                meImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.nav_me_normal));
                break;
            case  R.id.menu_find://发现
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mMainFragment)
                        .show(mFindFragment)
                        .hide(mMeFragment)
                        .commit();
                mainImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.nav_main_normal));
                findImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.nav_find_click));
                meImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.nav_me_normal));
                break;
            case  R.id.menu_me://我的
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .hide(mMainFragment)
                        .hide(mFindFragment)
                        .show(mMeFragment)
                        .commit();
                mainImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.nav_main_normal));
                findImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.nav_find_normal));
                meImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.nav_me_click));
                break;
        }
    }
}
