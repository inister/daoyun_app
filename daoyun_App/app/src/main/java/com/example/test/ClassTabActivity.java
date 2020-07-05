package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test.fragment.ActivityFragment;
import com.example.test.fragment.MemberFragment;
import com.example.test.fragment.ResFragment;
import com.example.test.fragment.MessageFragment;
import com.example.test.fragment.MoreFragment;

public class ClassTabActivity extends AppCompatActivity implements View.OnClickListener{

    protected LinearLayout mMenuRes;
    protected LinearLayout mMenuMember;
    protected LinearLayout mMenuActivity;
    protected LinearLayout mMenuMessage;
    protected LinearLayout mMenuMore;
    protected ImageView resImageView;
    protected ImageView memberImageView;
    protected ImageView activityImageView;
    protected ImageView messageImageView;
    protected ImageView moreImageView;
    protected ResFragment mResFragment = new ResFragment();
    protected MemberFragment mMemberFragment = new MemberFragment();
    protected ActivityFragment mActivityFragment = new ActivityFragment();
    protected MessageFragment mMessageFragment = new MessageFragment();
    protected MoreFragment mMoreFragment = new MoreFragment();

    public static String courseName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_tab);
        Intent intent = getIntent();
        courseName = intent.getStringExtra("courseName");
        initView();
        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_class_fragment,mResFragment)
                .add(R.id.container_class_fragment,mMemberFragment)
                .add(R.id.container_class_fragment,mActivityFragment)
                .add(R.id.container_class_fragment,mMessageFragment)
                .add(R.id.container_class_fragment,mMoreFragment)
                .hide(mResFragment)
                .hide(mMemberFragment)
                .hide(mMessageFragment)
                .hide(mMoreFragment)
                //事物添加  默认：显示首页  其他页面：隐藏
                //提交
                .commit();
    }

    public void initView(){
        mMenuRes = findViewById(R.id.menu_res);
        mMenuMember = findViewById(R.id.menu_member);
        mMenuActivity = findViewById(R.id.menu_activity);
        mMenuMessage = findViewById(R.id.menu_message);
        mMenuMore = findViewById(R.id.menu_more);

        resImageView = findViewById(R.id.Iv_res);
        memberImageView = findViewById(R.id.Iv_member);
        activityImageView = findViewById(R.id.Iv_activity);
        messageImageView = findViewById(R.id.Iv_message);
        moreImageView = findViewById(R.id.Iv_more);

        mMenuRes.setOnClickListener(this);
        mMenuMember.setOnClickListener(this);
        mMenuActivity.setOnClickListener(this);
        mMenuMessage.setOnClickListener(this);
        mMenuMore.setOnClickListener(this);

        activityImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_activity_pressed));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.menu_res:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(mResFragment)
                        .hide(mMemberFragment)
                        .hide(mActivityFragment)
                        .hide(mMessageFragment)
                        .hide(mMoreFragment)
                        .commit();
                resImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_res_pressed));
                memberImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_member_normal));
                activityImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_activity_normal));
                messageImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_message_normal));
                moreImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_more_normal));
                break;
            case R.id.menu_member:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(mMemberFragment)
                        .hide(mResFragment)
                        .hide(mActivityFragment)
                        .hide(mMessageFragment)
                        .hide(mMoreFragment)
                        .commit();
                resImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_res_normal));
                memberImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_member_pressed));
                activityImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_activity_normal));
                messageImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_message_normal));
                moreImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_more_normal));
                break;
            case R.id.menu_activity:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(mActivityFragment)
                        .hide(mResFragment)
                        .hide(mMemberFragment)
                        .hide(mMessageFragment)
                        .hide(mMoreFragment)
                        .commit();
                resImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_res_normal));
                memberImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_member_normal));
                activityImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_activity_pressed));
                messageImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_message_normal));
                moreImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_more_normal));
                break;
            case R.id.menu_message:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(mMessageFragment)
                        .hide(mResFragment)
                        .hide(mActivityFragment)
                        .hide(mMemberFragment)
                        .hide(mMoreFragment)
                        .commit();
                resImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_res_normal));
                memberImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_member_normal));
                activityImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_activity_normal));
                messageImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_message_pressed));
                moreImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_more_normal));
                break;
            case R.id.menu_more:
                this.getSupportFragmentManager()
                        .beginTransaction()
                        .show(mMoreFragment)
                        .hide(mResFragment)
                        .hide(mActivityFragment)
                        .hide(mMessageFragment)
                        .hide(mMemberFragment)
                        .commit();
                resImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_res_normal));
                memberImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_member_normal));
                activityImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_activity_normal));
                messageImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_message_normal));
                moreImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.nav_more_pressed));
                break;
        }
    }
}
