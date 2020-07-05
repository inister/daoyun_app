package com.example.test.fragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.FinishSignInActivity;
import com.example.test.GestureSettingActivity;
import com.example.test.GestureUnlockActivity;
import com.example.test.LoginActivity;
import com.example.test.MainActivity;
import com.example.test.Member;
import com.example.test.OneBtnSignInActivity;
import com.example.test.OneBtnSignInSettingActivity;
import com.example.test.PropertiesUtill;
import com.example.test.R;
import com.example.test.SignInTypeActivity;
import com.example.test.Utility;
import com.example.test.adapter.MemberAdapter;
import com.example.test.widget.GraphicLockView;

import java.util.ArrayList;
import java.util.List;

public class MemberFragment extends Fragment {

    private List<Member> memberList = new ArrayList<>();
    private LinearLayout linearLayout;
    private TextView signInTV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_member,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initMember();

        Button btn = getActivity().findViewById(R.id.toolbar_left_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        TextView rankTV = getActivity().findViewById(R.id.ranking_Tv);
        TextView experTV = getActivity().findViewById(R.id.experience_temp_Tv);
        if(MainActivity.userName.equals("teacher") || MainActivity.userName.equals("admin")){
            rankTV.setVisibility(View.GONE);
            experTV.setVisibility(View.GONE);
        }

        MemberAdapter memberAdapter = new MemberAdapter(getContext(), R.layout.member_item, memberList);
        ListView listView = getActivity().findViewById(R.id.member_list_view);
        listView.setAdapter(memberAdapter);
        Utility.setListViewHeightBasedOnChildren(listView);
        signInTV = getActivity().findViewById(R.id.signin_Tv);
        if(MainActivity.userName.equals("teacher")){
            signInTV.setText("发起签到");
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Member member = memberList.get(position);
                Toast.makeText(getContext(), member.getMemberName(), Toast.LENGTH_SHORT).show();
            }
        });
        linearLayout = getActivity().findViewById(R.id.signin_layout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), OneBtnSignInActivity.class);
                SharedPreferences preferences = getActivity().getSharedPreferences("sigin", Context.MODE_PRIVATE);
                GraphicLockView.mPassword = preferences.getString("gestureSignIn", null);
                OneBtnSignInSettingActivity.startOrNot = preferences.getBoolean("oneBtnSignIn", false);
                OneBtnSignInSettingActivity.distanceLimit = preferences.getInt("distanceLimit", -1);
                if(MainActivity.userName.equals("teacher")){

                    if(GraphicLockView.mPassword != null){
                        startActivity(new Intent(getContext(), FinishSignInActivity.class)
                                .putExtra("signin_mode","gesture_signin_mode"));
                    }else if(OneBtnSignInSettingActivity.startOrNot == true){
                        startActivity(new Intent(getContext(), FinishSignInActivity.class)
                                .putExtra("signin_mode","one_btn_mode"));
                    }else{
                        Intent intent = new Intent(getContext(), SignInTypeActivity.class);
                        startActivity(intent);
                    }
                }else if(GraphicLockView.mPassword != null){
                    Intent intent = new Intent(getContext(), GestureUnlockActivity.class);
                    startActivity(intent);
                }else if(OneBtnSignInSettingActivity.startOrNot == true){
                    Intent intent = new Intent(getContext(), OneBtnSignInActivity.class);
                    startActivity(intent);
                }else{
                    Log.i("memberInfo", PropertiesUtill.getProperties(getContext(), "gesturePassword"));
                    Toast.makeText(getContext(), "教师尚未发起签到或签到已结束", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initMember(){
        Member member_1 = new Member("1", R.drawable.course_img_1, "student1",
                "190327051","64经验值");
        memberList.add(member_1);
        Member member_2 = new Member("2", R.drawable.course_img_2, "student2",
                "190327090","62经验值");
        memberList.add(member_2);

    }

}
