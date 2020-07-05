package com.example.test.fragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.test.ClassTabActivity;
import com.example.test.fragment.AllActivityFragment;
import com.example.test.fragment.DoingActivityFragment;
import com.example.test.fragment.FinishActivityFragment;

import com.example.test.R;

public class ActivityFragment extends Fragment {

    private LinearLayout allActivityLayout;
    private LinearLayout doingActivityLayout;
    private LinearLayout finishActivityLayout;

    private TextView allActivityTV;
    private TextView doingActivityTV;
    private TextView finishActivityTV;

    private TextView allActivityNumTV;
    private TextView doingActivityNumTV;
    private TextView finishActivityNumTV;

    private View allActivityView;
    private View doingActivityView;
    private View finishActivityView;

    private AllActivityFragment allActivityFragment = new AllActivityFragment();
    private DoingActivityFragment doingActivityFragment = new DoingActivityFragment();
    private FinishActivityFragment finishActivityFragment = new FinishActivityFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AppCompatActivity activity = (AppCompatActivity) getActivity();

        Button btn = getActivity().findViewById(R.id.toolbar_left_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        allActivityLayout = activity.findViewById(R.id.activity_all_layout);
        doingActivityLayout = activity.findViewById(R.id.activity_doing_layout);
        finishActivityLayout = activity.findViewById(R.id.activity_finish_layout);

        allActivityTV = activity.findViewById(R.id.activity_all_Tv);
        doingActivityTV = activity.findViewById(R.id.activity_doing_Tv);
        finishActivityTV = activity.findViewById(R.id.activity_finish_Tv);

        allActivityNumTV = activity.findViewById(R.id.activity_all_num_Tv);
        doingActivityNumTV = activity.findViewById(R.id.activity_doing_num_Tv);
        finishActivityNumTV = activity.findViewById(R.id.activity_finish_num_Tv);

        allActivityView = activity.findViewById(R.id.activity_all_view);
        doingActivityView = activity.findViewById(R.id.activity_doing_view);
        finishActivityView = activity.findViewById(R.id.activity_finish_view);

        if(ClassTabActivity.courseName.equals("工程实践")){
            doingActivityNumTV.setText("12");
            allActivityNumTV.setText("12");
        }else if(ClassTabActivity.courseName.equals("工程训练")){
            doingActivityNumTV.setText("9");
            allActivityNumTV.setText("9");
        }

        doingActivityTV.setTextColor(Color.parseColor("#ff00bfff"));
        doingActivityNumTV.setTextColor(Color.parseColor("#ff00bfff"));
        doingActivityView.setBackgroundColor(Color.parseColor("#ff00bfff"));

        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_content_activity_layout,allActivityFragment)
                .add(R.id.container_content_activity_layout,doingActivityFragment)
                .add(R.id.container_content_activity_layout,finishActivityFragment)
                .hide(allActivityFragment)
                .hide(finishActivityFragment)
                .commit();

        allActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allActivityTV.setTextColor(Color.parseColor("#ff00bfff"));
                allActivityNumTV.setTextColor(Color.parseColor("#ff00bfff"));
                allActivityView.setBackgroundColor(Color.parseColor("#ff00bfff"));
                doingActivityTV.setTextColor(Color.parseColor("#80000000"));
                doingActivityNumTV.setTextColor(Color.parseColor("#80000000"));
                doingActivityView.setBackgroundColor(Color.parseColor("#D8DDE1"));
                finishActivityTV.setTextColor(Color.parseColor("#80000000"));
                finishActivityNumTV.setTextColor(Color.parseColor("#80000000"));
                finishActivityView.setBackgroundColor(Color.parseColor("#D8DDE1"));
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .show(allActivityFragment)
                        .hide(doingActivityFragment)
                        .hide(finishActivityFragment)
                        .commit();
            }
        });

        doingActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allActivityTV.setTextColor(Color.parseColor("#80000000"));
                allActivityNumTV.setTextColor(Color.parseColor("#80000000"));
                allActivityView.setBackgroundColor(Color.parseColor("#D8DDE1"));
                doingActivityTV.setTextColor(Color.parseColor("#ff00bfff"));
                doingActivityNumTV.setTextColor(Color.parseColor("#ff00bfff"));
                doingActivityView.setBackgroundColor(Color.parseColor("#ff00bfff"));
                finishActivityTV.setTextColor(Color.parseColor("#80000000"));
                finishActivityNumTV.setTextColor(Color.parseColor("#80000000"));
                finishActivityView.setBackgroundColor(Color.parseColor("#D8DDE1"));
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .show(doingActivityFragment)
                        .hide(allActivityFragment)
                        .hide(finishActivityFragment)
                        .commit();
            }
        });

        finishActivityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allActivityTV.setTextColor(Color.parseColor("#80000000"));
                allActivityNumTV.setTextColor(Color.parseColor("#80000000"));
                allActivityView.setBackgroundColor(Color.parseColor("#D8DDE1"));
                doingActivityTV.setTextColor(Color.parseColor("#80000000"));
                doingActivityNumTV.setTextColor(Color.parseColor("#80000000"));
                doingActivityView.setBackgroundColor(Color.parseColor("#D8DDE1"));
                finishActivityTV.setTextColor(Color.parseColor("#ff00bfff"));
                finishActivityNumTV.setTextColor(Color.parseColor("#ff00bfff"));
                finishActivityView.setBackgroundColor(Color.parseColor("#ff00bfff"));
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .show(finishActivityFragment)
                        .hide(allActivityFragment)
                        .hide(doingActivityFragment)
                        .commit();
            }
        });

    }



}
