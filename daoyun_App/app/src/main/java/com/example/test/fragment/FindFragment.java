package com.example.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.test.R;

import com.example.test.adapter.MainHeaderAdAdapter;
import com.example.test.adapter.MainMenuAdapter;
import com.example.test.util.DataUtil;

public class FindFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayout circleLayout = getActivity().findViewById(R.id.course_circle_layout);
        LinearLayout anotherLayout = getActivity().findViewById(R.id.another_class_layout);
        circleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "暂不支持课程圈功能", Toast.LENGTH_SHORT).show();
            }
        });
        anotherLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "暂不支持第二课堂功能", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
