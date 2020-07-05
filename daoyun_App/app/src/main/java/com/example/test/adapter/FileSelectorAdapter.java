package com.example.test.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.test.FileSelector;
import com.example.test.R;

import java.util.List;

public class FileSelectorAdapter extends ArrayAdapter<FileSelector> {

    private int resourceId;

    public FileSelectorAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<FileSelector> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FileSelector fileSelector = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.fileTypeImg = view.findViewById(R.id.file_type_img);
            viewHolder.fileNameTV = view.findViewById(R.id.file_name_Tv);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }

//        viewHolder.selectOrNotImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(fileSelector.dirOrNot == true){
//                    Toast.makeText(getContext(), "文件夹不可上传！", Toast.LENGTH_SHORT).show();
//                    viewHolder.selectOrNotImg.setTag("unselected");
//                    viewHolder.selectOrNotImg.setImageResource(R.drawable.unselected);
//                } else {
//                    Log.i("fileLogInfo", fileSelector.getFileName());
//                    viewHolder.fileNameTV.setTextColor(Color.parseColor("#32A082"));
//                    if(viewHolder.selectOrNotImg.getDrawable().getCurrent().getConstantState()
//                            .equals(view.getResources().getDrawable(R.drawable.unselected).getConstantState())){
//                        viewHolder.selectOrNotImg.setImageResource(R.drawable.selected);
//                    }else if(viewHolder.selectOrNotImg.getDrawable().getCurrent().getConstantState()
//                            .equals(view.getResources().getDrawable(R.drawable.selected).getConstantState())){
//                        viewHolder.selectOrNotImg.setImageResource(R.drawable.unselected);
//                    }
//                    if(viewHolder.selectOrNotImg.getTag().equals("unselected")){
//                        viewHolder.selectOrNotImg.setTag("selected");
//                        viewHolder.selectOrNotImg.setImageResource(R.drawable.selected);
//                    }else if(viewHolder.selectOrNotImg.getTag().equals("selected")){
////                        viewHolder.selectOrNotImg.setTag("unselected");
////                        viewHolder.selectOrNotImg.setImageResource(R.drawable.unselected);
//                    }
//                }
//            }
//        });
        viewHolder.fileTypeImg.setImageResource(fileSelector.getImageId());
        viewHolder.fileNameTV.setText(fileSelector.getFileName());
        return view;
    }

    class ViewHolder{
        ImageView fileTypeImg;
        TextView fileNameTV;
    }
}
