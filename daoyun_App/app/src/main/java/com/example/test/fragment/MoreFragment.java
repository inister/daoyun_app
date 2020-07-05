package com.example.test.fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test.MainActivity;
import com.example.test.R;

public class MoreFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button btn1 = getActivity().findViewById(R.id.toolbar_left_btn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        Button btn = getActivity().findViewById(R.id.save_btn);
        if(MainActivity.userName.equals("teacher")){
            btn.setText("解散班课");
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.userName.equals("teacher")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                            .setMessage("暂不支持解散班课")
                            .setPositiveButton("确定", null);
                    builder.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                            .setMessage("暂不支持退出班课")
                            .setPositiveButton("确定", null);
                    builder.show();
                }
            }
        });
    }
}
