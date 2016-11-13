package com.bw.test.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button view;
    private Button touch;
    private Button view_touch;
    private Button multi_touch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (Button)findViewById(R.id.view_click);
        touch = (Button)findViewById(R.id.touch_click);
        view_touch = (Button) findViewById(R.id.view_touch);
        multi_touch = (Button) findViewById(R.id.Multi_Touch);
        view.setOnClickListener(this);
        touch.setOnClickListener(this);
        view_touch.setOnClickListener(this);
        multi_touch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.view_click:
                Intent intent=new Intent(MainActivity.this,ViewActivity.class);
                startActivity(intent);
                break;
            case R.id.touch_click:
                Intent intent1=new Intent(MainActivity.this,TouchActivity.class);
                startActivity(intent1);
                break;
            case R.id.view_touch:
                Intent intent2=new Intent(MainActivity.this,ViewTouchActivity.class);
                startActivity(intent2);
                break;
            case R.id.Multi_Touch:
                Intent intent3=new Intent(MainActivity.this,MultiActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
