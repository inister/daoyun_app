package com.example.test.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.test.R;

/**
 * 主界面视图
 */
public class MainFragment extends Fragment {

    protected Button add_btn;
    protected TextView addTV;
    protected TextView myCreateTV;
    protected TextView myJoinTV;
    protected View myCreateView;
    protected View myJoinView;
    protected MyCreateFragment myCreateFragment = new MyCreateFragment();
    protected MyJoinFragment myJoinFragment = new MyJoinFragment();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find,null);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final AppCompatActivity activity = (AppCompatActivity) getActivity();

        myCreateView = activity.findViewById(R.id.view_mycreate);
        myJoinView = activity.findViewById(R.id.view_myjoin);
        myCreateTV = activity.findViewById(R.id.myCreateTv);
        myJoinTV = activity.findViewById(R.id.joinedClassTv);
        addTV = activity.findViewById(R.id.toolbar_right_tv);
        myJoinTV.setTextColor(Color.parseColor("#ff00bfff"));
        myCreateView.setVisibility(View.INVISIBLE);
//        myJoinTV.setTextColor(Color.parseColor("#80000000"));

        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container_content_layout,myCreateFragment)
                .add(R.id.container_content_layout,myJoinFragment)
                .hide(myCreateFragment)
                .commit();

        addTV.setEnabled(true);
        addTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "添加被按下", Toast.LENGTH_SHORT).show();
                showPopupMenu(add_btn);
            }
        });

//        add_btn = (Button) activity.findViewById(R.id.toolbar_right_btn);
//        add_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showPopupMenu(add_btn);
//            }
//        });
        myJoinTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myJoinTV.setTextColor(Color.parseColor("#ff00bfff"));
                myCreateTV.setTextColor(Color.parseColor("#80000000"));
                myJoinView.setVisibility(View.VISIBLE);
                myCreateView.setVisibility(View.INVISIBLE);
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .show(myJoinFragment)
                        .hide(myCreateFragment)
                        .commit();
            }
        });
        myCreateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCreateTV.setTextColor(Color.parseColor("#ff00bfff"));
                myJoinTV.setTextColor(Color.parseColor("#80000000"));
                myCreateView.setVisibility(View.VISIBLE);
                myJoinView.setVisibility(View.INVISIBLE);
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .show(myCreateFragment)
                        .hide(myJoinFragment)
                        .commit();
            }
        });
    }

    private void showPopupMenu(View view) {
        // 这里的view代表popupMenu需要依附的view
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        // 获取布局文件
        popupMenu.getMenuInflater().inflate(R.menu.class_menu, popupMenu.getMenu());
        popupMenu.show();
        // 通过上面这几行代码，就可以把控件显示出来了
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                // 控件每一个item的点击事件
                switch (item.getItemId()){
                    case R.id.myCreate:
                        Toast.makeText(getActivity(), "you clocked myCreate", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.joinClass:
                        Toast.makeText(getActivity(), "you clocked joinClass", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                // 控件消失时的事件
            }
        });

    }


}
