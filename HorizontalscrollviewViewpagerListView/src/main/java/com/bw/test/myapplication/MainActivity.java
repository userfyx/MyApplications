package com.bw.test.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private HorizontalScrollView hsv;
    private ViewPager vp;
    private LinearLayout mGallery;
    private String[] name = new String[] { "推荐", "热点", "娱乐", "科技", "汽车", "体育", "财经",
            "军事", "国际", "时尚", "视屏", "社会" };
    private int mScreenWidth;
    private int mItemWidth = 0;
    private ArrayList<String> list;
    private int columnSelectIndex = 0;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initTabColumn();
        initFragment();
    }
    private void initView() {
        //水平
        hsv = (HorizontalScrollView) findViewById(R.id.hsv);
        //viewpager
        vp = (ViewPager) findViewById(R.id.vp);
        //LinearLayout
        mGallery = (LinearLayout) findViewById(R.id.id_gallery);
    }
    //把数组添加到list集合中
    private void initData() {
        list = new ArrayList<String>();
        for (int i = 0; i < name.length; i++) {
            list.add(name[i]);

        }
    }

    private void initTabColumn() {
        // TODO Auto-generated method stub
        initScrollView();
        int count = list.size();
        for (int i = 0; i < count; i++) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    mItemWidth, ViewPager.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            TextView columnTextView = new TextView(MainActivity.this);
            columnTextView.setGravity(Gravity.CENTER);
            columnTextView.setPadding(5, 5, 5, 5);
            columnTextView.setId(i);
            columnTextView.setText(list.get(i));
            // 设置默认标签为选中状态
            if (columnSelectIndex == i) {
                columnTextView.setSelected(true);
                columnTextView.setTextColor(Color.RED);
            }
            /*
             * 设置点击事件监听
             */
            columnTextView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    for (int j = 0; j < mGallery.getChildCount(); j++) {
                        TextView localView = (TextView) mGallery.getChildAt(j);
                        if (localView != v) {
                            localView.setSelected(false);
                        } else {
                            localView.setSelected(true);
                            vp.setCurrentItem(j);
                        }
                    }
                    int index = mGallery.indexOfChild(v);
                }
            });
            mGallery.addView(columnTextView, i, params);
        }
    }
    public void initFragment() {
        fragments.clear();
        int count = list.size();
        for (int i = 0; i < count; i++) {//根据list集合的长度创建fragment
            Bundle data = new Bundle();   //传值给每个fragment
            data.putString("text", list.get(i));
            data.putInt("id", i);
//            MyFragment f = new MyFragment();
//            f.setArguments(data);
//            fragments.add(f);
        }
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragments);
        vp.setAdapter(adapter);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                vp.setCurrentItem(arg0);

                selectTab(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }
    private void selectTab(int tab_position) {
        // TODO Auto-generated method stub
        columnSelectIndex = tab_position;
        View currTextView = mGallery.getChildAt(tab_position);
        int w = currTextView.getMeasuredWidth();
        int l = currTextView.getLeft();
        int i2 = l + w / 2 - mScreenWidth / 2;
        hsv.smoothScrollTo(i2, 0);
        // 判断是否选中
        for (int i = 0; i < mGallery.getChildCount(); i++) {
            TextView eveTxetView = (TextView) mGallery.getChildAt(i);
            boolean ischeck;
            if (i == tab_position) {
                ischeck = true;
                eveTxetView.setTextColor(Color.RED);
            } else {
                ischeck = false;
                eveTxetView.setTextColor(Color.BLACK);
            }
            eveTxetView.setSelected(ischeck);
        }
    }

    private void initScrollView() {
        WindowManager ww=this.getWindowManager();
        mScreenWidth = ww.getDefaultDisplay().getWidth();
        mItemWidth = mScreenWidth / 7;//计算HorizontalScrollView可以展示几个
        mGallery.removeAllViews();
    }

//NewsUtils中的计算

    public final static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }
}
