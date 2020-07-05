package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SubmitActivity extends AppCompatActivity {

    private TextView taskRequirementTV;
    private TextView taskRequirementTitleTV;
    private ImageView addDocImg;
    private TextView submitTV;
    private Button backBtn;
    private EditText answerET;
    private String courseName;
    private String taskName;
    private int fileNum = 0;
    private int[] imgId = new int[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        imgId[0] = R.id.doc_img_0;
        imgId[1] = R.id.doc_img_1;
        imgId[2] = R.id.doc_img_2;
        imgId[3] = R.id.doc_img_3;
        imgId[4] = R.id.doc_img_4;
        imgId[5] = R.id.doc_img_5;
        imgId[6] = R.id.doc_img_6;
        taskRequirementTV = findViewById(R.id.task_requirement_Tv);
        taskRequirementTitleTV = findViewById(R.id.task_requirement_title_Tv);

        final Intent intent = getIntent();
        courseName = intent.getStringExtra("courseName");
        taskName = intent.getStringExtra("taskName");

        if(courseName.equals("工程实践")){
            if(taskName.equals("项目提交")){
                taskRequirementTitleTV.setText("项目提交");
                taskRequirementTV.setText("把Web项目部署到服务器上，并按要求提交项目相关文件。\n" +
                        "1、部署Web项目\n" +
                        "1）把单页面应用程序部署到服务器上。\n" +
                        "2）把后台管理应用程序（含数据库）部署到服务器上。含以下账号：\n" +
                        "角色\t用户名\t手机\t密码\n" +
                        "管理员\tadmin\t15900000001\t123456\n" +
                        "教师\tteacher\t15900000002\t123456\n" +
                        "学生\tstudent1\t15900000003\t123456\n" +
                        "学生\tstudent2\t15900000004\t123456\n" +
                        "3）使用浏览器通过域名或者IP地址访问应用程序，保证项目提交一个月内都能正常使用。\n" +
                        "2、提交项目相关文件\n" +
                        "1）需求相关，命名规范：需求_小组序号（不足两位，前面补零）.rar，例如：需求_01.rar\n" +
                        "（1）产品需求文档\n" +
                        "（2）Axure软件原型源文件\n" +
                        "（3）Axure软件发布的HTML文件。\n" +
                        "2）设计相关，命名规范：设计_小组序号（不足两位，前面补零）.rar，例如：设计_01.rar\n" +
                        "（1）PowerDesigner数据库物理模型文件，\n" +
                        "（2）PowerDesigner导出建表的SQL文件，\n" +
                        "（3）数据库插入初始化数据的SQL文件（如果有）\n" +
                        "3）总结文档，命名规范：总结_小组序号（不足两位，前面补零）.rar，例如：总结_01.rar\n" +
                        "（1）小组总结文档：组长和组员姓名和学号、及小组成员完成任务的百分比，Git链接（如有多个，请全部列出链接），单页面应用程序访问链接，项目中用到的技术说明，软件使用说明（如果有）。\n" +
                        "（2）个人总结文档：个人完成的任务清单。\n" +
                        "4）源代码相关，命名规范：源代码_小组序号（不足两位，前面补零）.rar，例如：源代码_01.rar\n" +
                        "（1）移动端工程、\n" +
                        "（2）单页面应用程序工程\n" +
                        "（3）后台管理系统工程\n" +
                        "5）部署相关，命名规范：部署_小组序号（不足两位，前面补零）.rar，例如：部署_01.rar\n" +
                        "（1）移动端APK\n" +
                        "（2）单页面应用程序部署文件\n" +
                        "（3）后台管理系统部署文件\n" +
                        "备注：除了提交以上5个压缩包之外，为了方便标哥的测试，请单独再提交小组总结文档。命名规范：小组总结_小组序号（不足两位，前面补零）.docx，例如：小组总结_01.docx");
            }else if(taskName.equals("第十周项目任务")){
                taskRequirementTitleTV.setText("第十周项目任务");
                taskRequirementTV.setText("1、使用Token实现用户的身份验证\n" +
                        "1）登录成功后从服务器获得Token，保存在本地存储中\n" +
                        "2）每次向服务器请求时，带上Token\n" +
                        "3）Token为null或者不正确时，跳转到登录页\n" +
                        "2、实现自动登录\n" +
                        "1）默认30天，过期时间的刷新参考Token的做法\n" +
                        "2）密码服务器端通过密钥加密后保存在本地存储中\n" +
                        "3、实现注销功能\n" +
                        "4、实现修改密码功能\n" +
                        "备注：在周报中放上以上代码的GitHub链接");
            }else if(taskName.equals("第九周项目任务")){
                taskRequirementTitleTV.setText("第九周项目任务");
                taskRequirementTV.setText("1、实现系统参数设置功能\n" +
                        "（1）事先在表中插入一条记录\n" +
                        "（2）实现修改功能\n" +
                        "2、实现学校、学院、系和专业等组织结构的管理相关功能\n" +
                        "（1）使用树控件或者表格展示树形数据，应能展现数据之间的层级关系\n" +
                        "3、实现菜单管理相关功能\n" +
                        "（1）使用树控件或者表格展示树形数据，应能展现数据之间的层级关系\n" +
                        "4、实现角色管理相关功能\n" +
                        "（1）创建角色时，输入角色基本信息并且分配权限，最后提交保存\n" +
                        "5、实现用户管理相关功能\n" +
                        "备注：除了提交周报，实现的功能请录制成 gif 文件并提交。");
            }else if(taskName.equals("第八周项目任务")){
                taskRequirementTitleTV.setText("第八周项目任务");
                taskRequirementTV.setText("1、实现数据字典相关功能：\n" +
                        "（1）列表页：根据中文或者英文标识进行查询。\n" +
                        "（2）编辑页：也可以分成创建和修改两个页面。在编辑字典的数据项时，只能有一项数据设置为默认值，key和value在该项数据字典中值要求唯一，key是整型。其他数据的有效性验证自行实现。\n" +
                        "（3）详情页：弹出对话框（Modal）显示数据字典的详细信息。\n" +
                        "2、继续完善产品需求文档。\n" +
                        "3、相关功能的需求确定后，开始移动端开发，各小组自行安排。\n" +
                        "备注：除了提交周报，实现的功能请录制成 gif 文件并提交。");
            }else if(taskName.equals("第七周项目任务")){
                taskRequirementTitleTV.setText("第七周项目任务");
                taskRequirementTV.setText("12 领域层仓储和业务\n" +
                        "https://github.com/chizhibiao/present-cloud-task/blob/master/12_%E9%A2%86%E5%9F%9F%E5%B1%82%E4%BB%93%E5%82%A8%E5%92%8C%E4%B8%9A%E5%8A%A1.md\n" +
                        "13 应用层应用服务和DTO\n" +
                        "https://github.com/chizhibiao/present-cloud-task/blob/master/13_%E5%BA%94%E7%94%A8%E5%B1%82%E5%BA%94%E7%94%A8%E6%9C%8D%E5%8A%A1%E5%92%8CDTO.md");
            }else if(taskName.equals("第六周项目任务")){
                taskRequirementTitleTV.setText("第六周项目任务");
                taskRequirementTV.setText("10 基础设施层\n" +
                        "https://github.com/chizhibiao/present-cloud-task/blob/master/10_%E5%9F%BA%E7%A1%80%E8%AE%BE%E6%96%BD%E5%B1%82.md\n" +
                        "11 领域层Entity\n" +
                        "https://github.com/chizhibiao/present-cloud-task/blob/master/11_%E9%A2%86%E5%9F%9F%E5%B1%82Entity.md\n" +
                        "提交要求参考第五周项目任务");
            }else if(taskName.equals("第五周项目任务")){
                taskRequirementTitleTV.setText("第五周项目任务");
                taskRequirementTV.setText("9 数据库设计规范和数据库建模\n" +
                        "https://github.com/chizhibiao/present-cloud-task/blob/master/09_%E6%95%B0%E6%8D%AE%E5%BA%93%E8%AE%BE%E8%AE%A1%E8%A7%84%E8%8C%83%E5%92%8C%E6%95%B0%E6%8D%AE%E5%BA%93%E5%BB%BA%E6%A8%A1.md\n" +
                        "所有的任务完成后都要提交到GitHub中仓库中（包括原型、需求文档等）。每个组员编写周工作日志，可以使用teambition截图。整合成一份文档，文档中列出项目在GitHub中的链接，如果有多个仓库分别列出。文档命名规范：小组序号_第N周周报.docx 例如：01_第5周周报.docx。");
            }else if(taskName.equals("第五周课堂练习")){
                taskRequirementTitleTV.setText("第五周课堂练习");
                taskRequirementTV.setText("安装PowerDesigner，创建数据库物理数据模型");
            }else if(taskName.equals("第四周项目任务")){
                taskRequirementTitleTV.setText("第四周项目任务");
                taskRequirementTV.setText("7 源代码版本管理\n" +
                        "https://github.com/chizhibiao/present-cloud-task/blob/master/07_%E6%BA%90%E4%BB%A3%E7%A0%81%E7%89%88%E6%9C%AC%E7%AE%A1%E7%90%86.md\n" +
                        "8 前端UI框架和标准页面设计\n" +
                        "https://github.com/chizhibiao/present-cloud-task/blob/master/08_%E5%89%8D%E7%AB%AFUI%E6%A1%86%E6%9E%B6%E5%92%8C%E6%A0%87%E5%87%86%E9%A1%B5%E9%9D%A2%E8%AE%BE%E8%AE%A1.md");
            }else if(taskName.equals("第三周项目任务")){
                taskRequirementTitleTV.setText("第三周项目任务");
                taskRequirementTV.setText("5 数据字典需求分析\n" +
                        "https://github.com/chizhibiao/present-cloud-task/blob/master/05_%E6%95%B0%E6%8D%AE%E5%AD%97%E5%85%B8%E9%9C%80%E6%B1%82%E5%88%86%E6%9E%90.md\n" +
                        "6 工程目录结构规范\n" +
                        "https://github.com/chizhibiao/present-cloud-task/blob/master/06_%E5%B7%A5%E7%A8%8B%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84%E8%A7%84%E8%8C%83.md\n" +
                        "不要把所有文件压缩成一个文件上传，分成多个附件上传：\n" +
                        "1 移动端产品需求文档\n" +
                        "2 后台管理产品需求文档\n" +
                        "3 原型设计\n" +
                        "4 源代码压缩包，小组序号_项目名称源代码.rar，例如：01_到云源代码.rar\n" +
                        "5 简单概要设计文档：应用程序架构图和开发技术说明，小组序号_项目名称概要设计.docx，例如：01_到云概要设计.docx");
            }else if(taskName.equals("第二周项目任务")){
                taskRequirementTitleTV.setText("第二周项目任务");
                taskRequirementTV.setText("任务详情参考下面两篇文档：\n" +
                        "3 交互式原型设计\n" +
                        "https://github.com/chizhibiao/present-cloud-task/blob/master/03_%E4%BA%A4%E4%BA%92%E5%BC%8F%E5%8E%9F%E5%9E%8B%E8%AE%BE%E8%AE%A1.md\n" +
                        "4 权限需求分析\n" +
                        "https://github.com/chizhibiao/present-cloud-task/blob/master/04_%E6%9D%83%E9%99%90%E9%9C%80%E6%B1%82%E5%88%86%E6%9E%90.md\n" +
                        "分别提交移动端APP产品需求文档、后台管理系统产品需求文档，原型设计源文件。文件名以小组序号打头，不足两位补零。相关文件分别上传，不要提交压缩包。");
            }else if(taskName.equals("02编写产品需求说明文档")){
                taskRequirementTitleTV.setText("02编写产品需求说明文档");
                taskRequirementTV.setText("确定产品需求文档的结构。\n" +
                        "编写移动端APP产品需求文档\n" +
                        "        - 使用思维导图相关软件制作移动端APP的产品结构图和产品信息结构图。\n" +
                        "        - 使用StarUML画用例图。\n" +
                        "        - 为移动端APP产品需求文档编写登录、注册、班课、创建班课、我的、用户信息等详细功能需求，暂时不用画原型。");
            }

        }else if(courseName.equals("工程训练")){
            if(taskName.equals("最终版二次提交")){
                taskRequirementTitleTV.setText("最终版二次提交");
                taskRequirementTV.setText("同之前的任务");
            }else if(taskName.equals("apk提交")){
                taskRequirementTitleTV.setText("apk提交");
                taskRequirementTV.setText("生成apk，把文件名改成你的学号.apk，存放在项目的根目录中。请在真机或模拟器上测试，保证能注册登录。提交apk。");
            }else if(taskName.equals("最终版提交")){
                taskRequirementTitleTV.setText("最终版提交");
                taskRequirementTV.setText("项目压缩并命令为:你的学号_最终版.rar,不要包含node-modules文件夹。提交压缩包。");
            }else if(taskName.equals("任务8选择商品类别的实现")){
                taskRequirementTitleTV.setText("任务8选择商品类别的实现");
                taskRequirementTV.setText("https://github.com/chizhibiao/sheng-yi-zhuan-jia-ionic4/blob/master/08_%E5%95%86%E5%93%81%E5%88%86%E7%B1%BB%E6%B5%8F%E8%A7%88%E7%9A%84%E5%AE%9E%E7%8E%B0.md");
            }else if(taskName.equals("任务7店铺设置的实现")){
                taskRequirementTitleTV.setText("任务7店铺设置的实现");
                taskRequirementTV.setText("https://github.com/chizhibiao/sheng-yi-zhuan-jia-ionic4/blob/master/07_%E5%BA%97%E9%93%BA%E8%AE%BE%E7%BD%AE%E7%9A%84%E5%AE%9E%E7%8E%B0.md");
            }else if(taskName.equals("任务6首页的实现")){
                taskRequirementTitleTV.setText("任务6首页的实现");
                taskRequirementTV.setText("https://github.com/chizhibiao/sheng-yi-zhuan-jia-ionic4/blob/master/06_%E9%A6%96%E9%A1%B5%E7%9A%84%E5%AE%9E%E7%8E%B0.md");
            }else if(taskName.equals("任务5登录的实现")){
                taskRequirementTitleTV.setText("任务5登录的实现");
                taskRequirementTV.setText("https://github.com/chizhibiao/sheng-yi-zhuan-jia-ionic4/blob/master/05_%E7%99%BB%E5%BD%95%E7%9A%84%E5%AE%9E%E7%8E%B0.md");
            }else if(taskName.equals("任务4注册的实现")){
                taskRequirementTitleTV.setText("任务4注册的实现");
                taskRequirementTV.setText("https://github.com/chizhibiao/sheng-yi-zhuan-jia-ionic4/blob/master/04_%E6%B3%A8%E5%86%8C%E7%9A%84%E5%AE%9E%E7%8E%B0.md");
            }else if(taskName.equals("任务3程序第一次运行的实现")){
                taskRequirementTitleTV.setText("任务3程序第一次运行的实现");
                taskRequirementTV.setText("https://github.com/chizhibiao/sheng-yi-zhuan-jia-ionic4/blob/master/03_%E7%A8%8B%E5%BA%8F%E7%AC%AC%E4%B8%80%E6%AC%A1%E8%BF%90%E8%A1%8C%E7%9A%84%E5%AE%9E%E7%8E%B0.md");
            }
        }

        answerET = findViewById(R.id.answer_Et);
        taskRequirementTV.setMovementMethod(ScrollingMovementMethod.getInstance());

        addDocImg = findViewById(R.id.add_doc_Iv);
        fileNum = SelectFileActivity.uploadFilePaths.size();
        for(int i = 0 ; i < fileNum ; i++){
            Log.i("submitBackResult", SelectFileActivity.uploadFilePaths.get(i));
            ImageView img = findViewById(imgId[i]);
            img.setVisibility(View.VISIBLE);
            img.setImageResource(SelectFileActivity.uploadFiletypes.get(i));
        }

        submitTV = findViewById(R.id.toolbar_right_tv);
        if(intent.getBooleanExtra("participate_or_not", false) == true){
            submitTV.setText("已提交");
            submitTV.setEnabled(false);
        }
        submitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectFileActivity.uploadFilePaths.clear();
                SelectFileActivity.uploadFiletypes.clear();
                answerET.getText();
                finish();
            }
        });

        backBtn = findViewById(R.id.toolbar_left_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addDocImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubmitActivity.this, SelectFileActivity.class);
                intent.putExtra("path", "/");
                intent.putExtra("num", fileNum);
                intent.putExtra("activity", "SubmitActivity");
                startActivityForResult(intent, 222);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 222 && resultCode == RESULT_OK){
            if(data.getIntExtra("return_num", 0) >= 0){
                if(SelectFileActivity.uploadFilePaths.size() != 0){
                    SelectFileActivity.uploadFilePaths.clear();
                }
                if(SelectFileActivity.uploadFiletypes.size() != 0){
                    SelectFileActivity.uploadFiletypes.clear();
                }
                fileNum = 0;
            }else {
                fileNum = SelectFileActivity.uploadFilePaths.size();
                for(int i = 0 ; i < fileNum ; i++){
                    Log.i("submitBackResult", SelectFileActivity.uploadFilePaths.get(i));
                    ImageView img = findViewById(imgId[i]);
                    img.setVisibility(View.VISIBLE);
                    img.setImageResource(SelectFileActivity.uploadFiletypes.get(i));
                }
            }

        }
    }
}
