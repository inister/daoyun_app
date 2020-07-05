package com.example.test.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test.ClassTabActivity;
import com.example.test.Course;
import com.example.test.LoginActivity;
import com.example.test.MainActivity;
import com.example.test.R;
import com.example.test.adapter.CourseAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyJoinFragment extends Fragment {

    private List<Course> courseList = new ArrayList<>();
    private int myJoinNum = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_myjoin,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCourses();

    }

    private void initCourses(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("userName", MainActivity.userName)
                        .build();
                Request request = new Request.Builder()
                        .url("http://39.106.229.1:8080/project_training_daoyun/onCourse/findByName")
                        .post(requestBody)
                        .build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(getContext(), "Connection failed!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String responseBodyStr = response.body().string();
                        Log.i("LoginInfo", responseBodyStr);
                        courseList = parseJsonWithJsonObject(responseBodyStr);
                        afterAction();
                    }
                });
            }
        }).start();


    }

    private void afterAction(){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(courseList.size() == 0 && !MainActivity.userName.equals("teacher")){
                    Course course_1 = new Course(R.drawable.course_img_1, "工程实践", "池芝标",
                            "19级工硕");
                    courseList.add(course_1);
                    Course course_2 = new Course(R.drawable.course_img_2, "工程训练", "池芝标",
                            "19级工硕");
                    courseList.add(course_2);
                }
                CourseAdapter adapter = new CourseAdapter(getContext(), R.layout.course_item, courseList);
                ListView listView = getActivity().findViewById(R.id.list_view);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Course course = courseList.get(position);
//                Toast.makeText(getContext(), course.getCourseName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), ClassTabActivity.class);
                        intent.putExtra("courseName", course.getCourseName());
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private List<Course> parseJsonWithJsonObject(String jsonData){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            List<Course> cList = new ArrayList<Course>();
            for(int i = jsonArray.length() - 1 ; i >= 0 ; i--){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String courseName = jsonObject.getString("onCourseName");
                Log.i("LoginInfo", courseName);
                String teacherName = jsonObject.getString("teacherName");
                Log.i("LoginInfo", teacherName);
                String className = jsonObject.getString("grade");
                Log.i("LoginInfo", className);
                int img = myJoinNum % 2 == 0 ? R.drawable.course_img_1 : R.drawable.course_img_2;
                myJoinNum++;
                Course course = new Course(img, courseName, teacherName, className);
                cList.add(course);
            }
            Log.i("LoginInfo", cList.size()+"");
            return cList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
