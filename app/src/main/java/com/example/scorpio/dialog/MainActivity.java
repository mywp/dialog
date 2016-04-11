package com.example.scorpio.dialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View view) {
        //对话框的创建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("我是对话框");
        builder.setMessage("对话框显示的内容");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplication(), "却是被点击了", Toast.LENGTH_SHORT).show();


            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //什么都不写默认实现就是关闭对话框
            }
        });
        builder.setCancelable(false);
        builder.create().show();

    }
    
    /*单选对话框*/
    public void click2(View view) {
        //对话框的创建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择你的性别");
        final String[] items = {"男", "女", "未知"};
        builder.setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "您的性别：" + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
    
    /*对选对话框*/
    public void click3(View view) {
        //对话框的创建器
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择你爱吃的水果");
        final String[] items = {"苹果", "梨", "菠萝", "香蕉", "黄瓜"};
        final boolean[] result = new boolean[]{true, false, true, false, false};
        builder.setMultiChoiceItems(items, result, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                Toast.makeText(getApplicationContext(), items[which] + isChecked, Toast.LENGTH_SHORT).show();
                result[which] = isChecked;
            }
        });
        builder.setPositiveButton("提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < result.length; i++) {
                    if (result[i]) {
                        sb.append(items[i] + ",");
                    }
                }
                Toast.makeText(getApplicationContext(), "您选中了，" + sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //builder.create().show();
        builder.show();
    }
    
    /*进度条对话框*/
    public void click4(View view) {
        ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("提醒");
        pd.setMessage("正在加载数据。。。请稍等");
        pd.show();
    }
    
    /*带进度的进度条对话框*/
    public void click5(View view) {
        final ProgressDialog pd = new ProgressDialog(this);
        pd.setTitle("提醒");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(100);
        pd.setMessage("正在加载数据。。。请稍后");
        pd.show();
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    try {
                        Thread.sleep(40);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    pd.setProgress(i);
                }
                pd.dismiss();
            }
            
        }.start();
                
                
    }
}
