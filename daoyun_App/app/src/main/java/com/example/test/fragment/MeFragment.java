package com.example.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test.LoginActivity;
import com.example.test.MainActivity;
import com.example.test.PrivacyPolicyActivity;
import com.example.test.R;
import com.example.test.UserInfoActivity;
import com.example.test.UserProtocolActivity;

/**
 * 我的
 */
public class MeFragment extends Fragment {

    protected  Button mBtnLogin;
    protected LinearLayout userInfoLayout;
    protected LinearLayout privacyLayout;
    protected Button logoutBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me,container,false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mBtnLogin= (Button) getView().findViewById(R.id.btn_login);
//        mBtnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //登录
//                Intent  login=new Intent(getActivity(),LoginActivity.class);
//                startActivity(login);
//            }
//        });

        userInfoLayout = getActivity().findViewById(R.id.layout_me_header);
        userInfoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserInfoActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout = getActivity().findViewById(R.id.user_protocol_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserProtocolActivity.class);
                startActivity(intent);
            }
        });

        privacyLayout = getActivity().findViewById(R.id.prvacy_layout);
        privacyLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PrivacyPolicyActivity.class));
            }
        });

        logoutBtn = getActivity().findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.userName = null;
                startActivity(new Intent(getContext(), LoginActivity.class));
            }
        });

    }
}
