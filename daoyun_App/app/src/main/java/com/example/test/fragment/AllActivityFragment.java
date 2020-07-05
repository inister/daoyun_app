package com.example.test.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.test.ClassTabActivity;
import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.SubmitActivity;
import com.example.test.TaskActivity;
import com.example.test.TaskResultActivity;
import com.example.test.adapter.ActivityAdapter;

import java.util.ArrayList;
import java.util.List;

public class AllActivityFragment extends Fragment {

    private List<TaskActivity> activityList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activity_all,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initTaskActivity();
        ActivityAdapter activityAdapter = new ActivityAdapter(getContext(), R.layout.activity_item, activityList);
        ListView listView = getActivity().findViewById(R.id.activity_all_list_view);
        listView.setAdapter(activityAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TaskActivity taskActivity = activityList.get(position);
//                Toast.makeText(getContext(), taskActivity.getTaskName(), Toast.LENGTH_SHORT).show();
                if(!MainActivity.userName.equals("teacher")){
                    Intent intent = new Intent(getContext(), SubmitActivity.class);
                    intent.putExtra("activity", "doingFragment");
                    intent.putExtra("courseName", ClassTabActivity.courseName);
                    intent.putExtra("taskName", taskActivity.getTaskName());
                    if(taskActivity.getParticipateOrNot().equals("已参与")){
                        intent.putExtra("participate_or_not", true);
                    }else{
                        intent.putExtra("participate_or_not", false);
                    }
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getContext(), TaskResultActivity.class);
                    intent.putExtra("taskName", taskActivity.getTaskName());
                    startActivity(intent);
                }
            }
        });
    }

    private void initTaskActivity(){
        if(ClassTabActivity.courseName.equals("工程实践")){
            TaskActivity taskActivity_1 = new TaskActivity("项目提交", "30组参与",
                    "已参与", "100经验", "已超时");
            activityList.add(taskActivity_1);
            TaskActivity taskActivity_2 = new TaskActivity("第十周项目任务", "30组参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_2);
            TaskActivity taskActivity_3 = new TaskActivity("第九周项目任务", "30组参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_3);
            TaskActivity taskActivity_4 = new TaskActivity("第八周项目任务", "30组参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_4);
            TaskActivity taskActivity_5 = new TaskActivity("第七周项目任务", "30组参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_5);
            TaskActivity taskActivity_6 = new TaskActivity("第六周项目任务", "30组参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_6);
            TaskActivity taskActivity_7 = new TaskActivity("第五周项目任务", "30组参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_7);
            TaskActivity taskActivity_8 = new TaskActivity("第五周课堂练习", "30组参与",
                    "已参与", "5经验", "已超时");
            activityList.add(taskActivity_8);
            TaskActivity taskActivity_9 = new TaskActivity("第四周项目任务", "30组参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_9);
            TaskActivity taskActivity_10 = new TaskActivity("第三周项目任务", "30组参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_10);
            TaskActivity taskActivity_11 = new TaskActivity("第二周项目任务", "30组参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_11);
            TaskActivity taskActivity_12 = new TaskActivity("02编写产品需求说明文档", "30组参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_12);
        }else if(ClassTabActivity.courseName.equals("工程训练")){
            TaskActivity taskActivity_1 = new TaskActivity("最终版二次提交", "38人参与参与",
                    "", "10经验", "已超时");
            activityList.add(taskActivity_1);
            TaskActivity taskActivity_2 = new TaskActivity("apk提交", "134人参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_2);
            TaskActivity taskActivity_3 = new TaskActivity("最终版提交", "133人参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_3);
            TaskActivity taskActivity_4 = new TaskActivity("任务8选择商品类别的实现", "143人参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_4);
            TaskActivity taskActivity_5 = new TaskActivity("任务7店铺设置的实现", "143人参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_5);
            TaskActivity taskActivity_6 = new TaskActivity("任务6首页的实现", "142人参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_6);
            TaskActivity taskActivity_7 = new TaskActivity("任务5登录的实现", "143人参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_7);
            TaskActivity taskActivity_8 = new TaskActivity("任务4注册的实现", "143人参与",
                    "已参与", "5经验", "已超时");
            activityList.add(taskActivity_8);
            TaskActivity taskActivity_9 = new TaskActivity("任务3程序第一次运行的实现", "144人参与",
                    "已参与", "10经验", "已超时");
            activityList.add(taskActivity_9);
        }
    }

}
