package com.bw.test.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bw.test.myapplication.listview.MyListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button yes_all;
    private Button hide;
    private Button no_all;
    private boolean ishide=true;
    private String[] content;
    private MyListView listview;
    private List<String> list=new ArrayList<String>();
//    private boolean flag=false;
private int checkNum; // 记录选中的条目数量
    private MyAdapter adapter;
    private Button reverse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (MyListView) findViewById(R.id.mylistview);
        initView();
        initData();
        adapter = new MyAdapter(MainActivity.this,list);
        listview.setAdapter(adapter);
    }

    private void initData() {
        content = new String[]{"1","2","3","4","5","6","7","8","9","10"};
        int count=content.length;
        for (int i=0; i<count; i++){
            list.add(content[i]);
        }

    }

    private void initView() {
        yes_all = (Button) findViewById(R.id.yes_all);
        hide = (Button) findViewById(R.id.hide);
        no_all = (Button) findViewById(R.id.no_all);
        reverse = (Button) findViewById(R.id.reverse);
        reverse.setOnClickListener(this);
        yes_all.setOnClickListener(this);
        hide.setOnClickListener(this);
        no_all.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.yes_all:
                check(true);
                break;
            case R.id.hide:
//                ishide=ishide==true?false:true;
                hides(ishide);
                break;
            case R.id.no_all:
                check(false);
                break;
            case R.id.reverse:
                reverses();
                break;
        }
    }
    //反选
    private void reverses() {
        // 遍历list的长度，将已选的设为未选，未选的设为已选
        for (int i = 0; i < list.size(); i++) {
            if (MyAdapter.getIsSelected().get(i)) {
                MyAdapter.getIsSelected().put(i, false);
                checkNum--;
            } else {
                MyAdapter.getIsSelected().put(i, true);
                checkNum++;
            }
        }
        // 刷新listview和TextView的显示
        dataChanged();
    }
    //隐藏
    private void hides(boolean ishide) {
        if (ishide){
            hide.setText("取消隱藏");
        }else{
            hide.setText("隱藏已選");
        }
    }

    private void check(boolean flag) {
        //全选
        if (flag){
            // 遍历list的长度，将MyAdapter中的map值全部设为true
            for (int i = 0; i < list.size(); i++) {
                MyAdapter.getIsSelected().put(i, true);
            }
            // 数量设为list的长度
            checkNum = list.size();
            // 刷新listview和TextView的显示
            dataChanged();
        }
        //全不选
        else{
            // 遍历list的长度，将已选的按钮设为未选
            for (int i = 0; i < list.size(); i++) {
                if (MyAdapter.getIsSelected().get(i)) {
                    MyAdapter.getIsSelected().put(i, false);
                    checkNum--;// 数量减1
                }
            }
            // 刷新listview和TextView的显示
            dataChanged();
        }
    }
    //适配器刷新数据
    private void dataChanged() {
        adapter.notifyDataSetChanged();
    }
}
