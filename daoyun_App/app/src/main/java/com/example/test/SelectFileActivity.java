package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.adapter.FileSelectorAdapter;
import com.example.test.GetFilesUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectFileActivity extends AppCompatActivity {

    private static final int REQUEST_WRITE_EXTERNAL_STORAGE = 0;
    private List<FileSelector> fileSelectorList = new ArrayList<>();
    private FileSelectorAdapter fileSelectorAdapter;
    private TextView selectNumTV;
    private int upload_num=0;
    private int return_upload_num;
    private String path;
    private String basePath;
    private String parentActivity;
    private List<Map<String, Object>> fList;
    public static List<String> uploadFilePaths = new ArrayList<String>();
    public static List<Integer> uploadFiletypes = new ArrayList<Integer>();
    private Button backBtn;
    private TextView doneTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_file);

        checkPermission();
        basePath = GetFilesUtils.getInstance().getBasePath();

        selectNumTV = findViewById(R.id.selected_num_Tv);
        backBtn = findViewById(R.id.toolbar_left_btn);
        doneTV = findViewById(R.id.toolbar_right_tv);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.putExtra("return_num",upload_num);
                setResult(RESULT_OK, intent2);
                if(parentActivity.equals("SubmitActivity")){
                    fileSelectorList.clear();
                }
                finish();
            }
        });
        doneTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(SelectFileActivity.this, SubmitActivity.class);
//                intent.putExtra("activity","SelectActivity");
//                startActivity(intent);
                Intent intent = getIntent();
                intent.putExtra("return_num", -1);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        final Intent intent = getIntent();
        upload_num = intent.getIntExtra("num",0);
        selectNumTV.setText("已选：" + upload_num + "/7");
        path = intent.getStringExtra("path");
        parentActivity = intent.getStringExtra("activity");

        initFileSelector();
        fileSelectorAdapter = new FileSelectorAdapter(SelectFileActivity.this,
                R.layout.file_item, fileSelectorList);
        ListView listView = findViewById(R.id.file_listview);
        listView.setAdapter(fileSelectorAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final FileSelector fileSelector = fileSelectorList.get(position);
                if(fileSelector.dirOrNot == true){
                    String temp_path = path + "/" + fileSelector.getFileName();
                    Intent intent1 = new Intent(SelectFileActivity.this, SelectFileActivity.class);
                    intent1.putExtra("num", upload_num);
                    intent1.putExtra("path", temp_path);
                    intent1.putExtra("activity", "SelectActivity");
                    startActivityForResult(intent1, 1);
//                    Log.i("returnedNum", "upload_num " + upload_num);
//                    Toast.makeText(SelectFileActivity.this, "不可上传文件夹！", Toast.LENGTH_SHORT).show();
                }else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(SelectFileActivity.this)
                            .setTitle("上传文件")
                            .setMessage("确定上传" + fileSelector.getFileName() + "？");
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(uploadFilePaths.size() < 7){
                                uploadFilePaths.add(basePath + path + fileSelector.getFileName());
                                uploadFiletypes.add(fileIcon(GetFilesUtils.getInstance().getFileType(fileSelector.getFileName())));
                                upload_num++;
                                selectNumTV.setText("已选：" + upload_num + "/7");
                                Log.i("returnedNum", basePath + path + fileSelector.getFileName());
                            }else{
                                Toast.makeText(SelectFileActivity.this, "最多上传7个文件", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    dialog.setNegativeButton("取消", null);
                    dialog.show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if(data.getIntExtra("return_num", 0) >= 0){
                return_upload_num = data.getIntExtra("return_num", 0);
                Log.i("returnedNum", Integer.toString(return_upload_num));
                upload_num = return_upload_num;
                selectNumTV.setText("已选：" + upload_num + "/7");
            }else{
                Intent intent = getIntent();
                intent.putExtra("return_num", -1);
                setResult(RESULT_OK, intent);
                finish();
            }

//            if(data.getStringExtra("tag").equals("done")){
//                Intent intent = new Intent();
//                intent.putExtra("tag","done");
//                setResult(RESULT_OK, intent);
//                finish();
//            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent2 = new Intent();
        intent2.putExtra("return_num",upload_num);
        setResult(RESULT_OK, intent2);
//        if(parentActivity.equals("SubmitActivity")){
//            uploadFilePaths.clear();
//        }
        finish();
    }

    private void initFileSelector(){
        fList = GetFilesUtils.getInstance().getSonNode(basePath + path);
//        Map<String,Object> one = new HashMap<String,Object>();
//        FileSelector fileSelector;
//        one = fList.get(0);
//        String fileType = one.get(GetFilesUtils.FILE_INFO_TYPE).toString();
//        String fname = one.get(GetFilesUtils.FILE_INFO_NAME).toString();
//        boolean isFolder = (boolean) one.get(GetFilesUtils.FILE_INFO_ISFOLDER);
//        if(isFolder == true){
//            fileSelector = new FileSelector(R.drawable.icon_file, fname, true);
//        }else{
//            fileSelector = new FileSelector(fileIcon(fileType), fname, true);
//        }
//        fileSelectorList.add(fileSelector);
        for(int i = 0 ; i < fList.size(); i++){
            Map<String,Object> one = new HashMap<String,Object>();
            FileSelector fileSelector;
            one = fList.get(i);
            String fileType = one.get(GetFilesUtils.FILE_INFO_TYPE).toString();
            String fname = one.get(GetFilesUtils.FILE_INFO_NAME).toString();
            boolean isFolder = (boolean) one.get(GetFilesUtils.FILE_INFO_ISFOLDER);
            if(isFolder == true){
                fileSelector = new FileSelector(R.drawable.icon_file, fname, true);
            }else{
                fileSelector = new FileSelector(fileIcon(fileType), fname, false);
            }
            fileSelectorList.add(fileSelector);
            Log.i("rootDirectory", fname + "  " + fileType);
       }
    }

    private int fileIcon(String fileType){
        if(fileType.equalsIgnoreCase("cad")){
            return R.drawable.icon_file_cad;
        }else if(fileType.equalsIgnoreCase("rar") || fileType.equalsIgnoreCase("zip")
        || fileType.equalsIgnoreCase("7z") || fileType.equalsIgnoreCase("jar")){
            return R.drawable.icon_file_compress;
        }else if(fileType.equalsIgnoreCase("exe") || fileType.equalsIgnoreCase("apk")){
            return R.drawable.icon_file_exe;
        } else if(fileType.equalsIgnoreCase("jpg") || fileType.equalsIgnoreCase("jpeg")
        || fileType.equalsIgnoreCase("bmp") || fileType.equalsIgnoreCase("png")){
            return R.drawable.icon_file_image;
        }else if(fileType.equalsIgnoreCase("mp3") || fileType.equalsIgnoreCase("wav")
        || fileType.equalsIgnoreCase("cda")){
            return R.drawable.icon_file_music;
        }else if(fileType.equalsIgnoreCase("iso")){
            return R.drawable.icon_file_iso;
        }else if(fileType.equalsIgnoreCase("pdf")){
            return R.drawable.icon_file_pdf;
        }else if(fileType.equalsIgnoreCase("ppt") || fileType.equalsIgnoreCase("pptx")){
            return R.drawable.icon_file_ppt;
        }else if(fileType.equalsIgnoreCase("doc") || fileType.equalsIgnoreCase("docx")){
            return R.drawable.icon_file_word;
        }else if(fileType.equalsIgnoreCase("mp4") || fileType.equalsIgnoreCase("avi")
        || fileType.equalsIgnoreCase("wmv") || fileType.equalsIgnoreCase("mkv") ||
                fileType.equalsIgnoreCase("flv")){
            return R.drawable.icon_file_video;
        }else if(fileType.equalsIgnoreCase("txt")){
            return R.drawable.icon_file_txt;
        }else if(fileType.equalsIgnoreCase("psd")){
            return R.drawable.icon_file_psd;
        }else if(fileType.equalsIgnoreCase("xls") || fileType.equalsIgnoreCase("xlsx")){
            return R.drawable.icon_file_xls;
        }else{
            return R.drawable.icon_file_none;
        }
    }

    private void checkPermission() {
        //检查权限（NEED_PERMISSION）是否被授权 PackageManager.PERMISSION_GRANTED表示同意授权
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //用户已经拒绝过一次，再次弹出权限申请对话框需要给用户一个解释
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请开通相关权限，否则无法正常使用本应用！", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_EXTERNAL_STORAGE);

        } else {
            Toast.makeText(this, "授权成功！", Toast.LENGTH_SHORT).show();
            Log.i("rootDirectory", "checkPermission: 已经授权！");
        }
    }

}
