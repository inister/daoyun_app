package com.example.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.ClassTabActivity;
import com.example.test.Course;

import java.util.List;

import com.example.test.FinishSignInActivity;
import com.example.test.GestureSettingActivity;
import com.example.test.GestureUnlockActivity;
import com.example.test.MainActivity;
import com.example.test.OneBtnSignInActivity;
import com.example.test.OneBtnSignInSettingActivity;
import com.example.test.PropertiesUtill;
import com.example.test.R;
import com.example.test.SignInTypeActivity;
import com.example.test.widget.GraphicLockView;

public class CourseAdapter extends ArrayAdapter<Course> {

    private int resourceId;
    private int flag = 1;

    public CourseAdapter(@NonNull Context context,  int textViewResourceId, @NonNull List<Course> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public CourseAdapter(@NonNull Context context,  int textViewResourceId, @NonNull List<Course> objects, int flag) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
        this.flag = flag;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Course course = getItem(position);
        final View view;
        final ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.courseImage = view.findViewById(R.id.course_image);
            viewHolder.courseName = view.findViewById(R.id.course_name);
            viewHolder.teacherName = view.findViewById(R.id.teacher_name);
            viewHolder.className = view.findViewById(R.id.class_name);
            viewHolder.signInImg = view.findViewById(R.id.signIn_Iv);
            viewHolder.signInTv = view.findViewById(R.id.signIn_Tv);
//            if(flag != 1){
                viewHolder.signInImg.setVisibility(View.INVISIBLE);
                viewHolder.signInTv.setVisibility(View.INVISIBLE);
//            }
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.courseImage.setImageResource(course.getImageId());
        viewHolder.courseName.setText(course.getCourseName());
        viewHolder.teacherName.setText(course.getTeacherName());
        viewHolder.className.setText(course.getClassName());
        if(flag == 1){
            viewHolder.signInImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getContext(), "viewHolder.courseName.getText()", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(v.getContext(), GestureSettingActivity.class);
//                    v.getContext().startActivity(intent);

//                    SharedPreferences preferences = getSharedPreferences("sigin", Context.MODE_PRIVATE);
//                    GraphicLockView.mPassword = preferences.getString("gestureSignIn", null);
//                    OneBtnSignInSettingActivity.startOrNot = preferences.getBoolean("oneBtnSignIn", false);
//                    OneBtnSignInSettingActivity.distanceLimit = preferences.getInt("distanceLimit", -1);
//                    if(MainActivity.userName.equals("teacher")){
//
//                        if(GraphicLockView.mPassword != null){
//                            startActivity(new Intent(getContext(), FinishSignInActivity.class)
//                                    .putExtra("signin_mode","gesture_signin_mode"));
//                        }else if(OneBtnSignInSettingActivity.startOrNot == true){
//                            startActivity(new Intent(getContext(), FinishSignInActivity.class)
//                                    .putExtra("signin_mode","one_btn_mode"));
//                        }else{
//                            Intent intent = new Intent(getContext(), SignInTypeActivity.class);
//                            startActivity(intent);
//                        }
//                    }else if(GraphicLockView.mPassword != null){
//                        Intent intent = new Intent(getContext(), GestureUnlockActivity.class);
//                        startActivity(intent);
//                    }else if(OneBtnSignInSettingActivity.startOrNot == true){
//                        Intent intent = new Intent(getContext(), OneBtnSignInActivity.class);
//                        startActivity(intent);
//                    }else{
//                        Log.i("memberInfo", PropertiesUtill.getProperties(getContext(), "gesturePassword"));
//                        Toast.makeText(getContext(), "教师尚未发起签到或签到已结束", Toast.LENGTH_SHORT).show();
//                    }
                }
            });
            viewHolder.signInTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getContext(), viewHolder.courseName.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        return view;
    }

    class ViewHolder{
        ImageView courseImage;
        TextView courseName;
        TextView teacherName;
        TextView className;

        ImageView signInImg;
        TextView signInTv;
    }
}
