package com.example.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.TaskActivity;
import com.example.test.R;

import java.util.List;

public class ActivityAdapter extends ArrayAdapter<TaskActivity> {

    private int resourceId;

    public ActivityAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<TaskActivity> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TaskActivity taskActivity = getItem(position);
        final View view;
        final ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.taskName = view.findViewById(R.id.activity_name_Tv);
            viewHolder.participantNum = view.findViewById(R.id.participant_num_Tv);
            viewHolder.participateOrNot = view.findViewById(R.id.participate_or_not_Tv);
            viewHolder.activityExperience = view.findViewById(R.id.activity_experience_Tv);
            viewHolder.timeLimit = view.findViewById(R.id.time_limit_Tv);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.taskName.setText(taskActivity.getTaskName());
        viewHolder.participantNum.setText(taskActivity.getParticipantNum());
        viewHolder.participateOrNot.setText(taskActivity.getParticipateOrNot());
        viewHolder.activityExperience.setText(taskActivity.getActivityExperience());
        viewHolder.timeLimit.setText(taskActivity.getTimeLimit());
        return view;
    }

    class ViewHolder{
        TextView taskName;
        TextView participantNum;
        TextView participateOrNot;
        TextView activityExperience;
        TextView timeLimit;
    }

}
