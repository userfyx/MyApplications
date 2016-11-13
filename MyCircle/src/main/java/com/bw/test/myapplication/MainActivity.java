package com.bw.test.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取布局文件中LinearLayout容器
        LinearLayout root = (LinearLayout)findViewById(R.id.root);
        //创建DrawView组件
        final DrawView drawView = new DrawView(this);
        //设置自定义组件的最小宽度、高度
        drawView.setMinimumWidth(10);
        drawView.setMinimumHeight(10);
        root.addView(drawView);
    }
}
