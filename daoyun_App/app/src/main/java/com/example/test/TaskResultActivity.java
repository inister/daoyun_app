package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.adapter.MemberAdapter;

import java.util.ArrayList;
import java.util.List;

public class TaskResultActivity extends AppCompatActivity {

    private List<Member> memberList = new ArrayList<>();
    private TextView titleTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_result);
        initMember();
        titleTV = findViewById(R.id.toolbar_title_tv);
        Intent intent = getIntent();
        titleTV.setText(intent.getStringExtra("taskName"));

        MemberAdapter memberAdapter = new MemberAdapter(TaskResultActivity.this, R.layout.member_item, memberList);
        ListView listView = findViewById(R.id.submit_list_view);
        listView.setAdapter(memberAdapter);
        Utility.setListViewHeightBasedOnChildren(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Member member = memberList.get(position);
                Toast.makeText(TaskResultActivity.this, member.getMemberName(), Toast.LENGTH_SHORT).show();
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
