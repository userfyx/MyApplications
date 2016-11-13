package com.bw.test.myapplication;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private RadioButton mRadioButton5;
    private float mCurrentCheckedRadioLeft;// 当前被选中的RadioButton距离左侧的距离
    private HorizontalScrollView mHorizontalScrollView;// 上面的水平滚动控件
    private ViewPager mViewPager; // 下方的可横向拖动的控件
    private ArrayList<View> mViews;// 用来存放下方滚动的layout(layout_1,layout_2,layout_3)

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        iniController();
        iniListener();
        iniVariable();

        mRadioButton1.setChecked(true);// 默认让第一个按钮被选中
        mViewPager.setCurrentItem(1);// 默认让第一个ViewPager被选中
        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();// 得到当前选中的按钮距离最左边的距离

    }

    private void iniVariable() {
        // TODO Auto-generated method stub
        mViews = new ArrayList<View>();
//      这里设置了第0和第7页为空界面，是为了实现划到最左或者最右，有一个可以划出空白页面但是不会停留的效果，其实不要这种效果也行
//        mViews.add(getLayoutInflater().inflate(R.layout.layout_0, null));
//        mViews.add(getLayoutInflater().inflate(R.layout.layout_1, null));
//        mViews.add(getLayoutInflater().inflate(R.layout.layout_2, null));
//        mViews.add(getLayoutInflater().inflate(R.layout.layout_3, null));
//        mViews.add(getLayoutInflater().inflate(R.layout.layout_4, null));
//        mViews.add(getLayoutInflater().inflate(R.layout.layout_5, null));
//        mViews.add(getLayoutInflater().inflate(R.layout.layout_0, null));

        mViewPager.setAdapter(new MyPagerAdapter());// 设置ViewPager的适配器
    }

    /**
     * RadioGroup点击CheckedChanged监听
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        AnimationSet _AnimationSet = new AnimationSet(true);
        TranslateAnimation _TranslateAnimation;

        Log.i("zj", "checkedid=" + checkedId);
        if (checkedId == R.id.btn1) {
            // 让蓝色横条从点击前的位置滚动到点击后的位置，记得滚动完后要把当前位置设为mCurrentCheckedRadioLeft的值！！！
            _TranslateAnimation = new TranslateAnimation(
                    mCurrentCheckedRadioLeft, getResources().getDimension(
                    R.dimen.rdo1), 0f, 0f);
            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);// 不让动画结束时回到初始状态
            _AnimationSet.setFillAfter(true);// 让动画结束时保留在结束状态
            _AnimationSet.setDuration(100);
            /*
             * LayoutParams _LayoutParams1 = new LayoutParams(100, 4);
             * _LayoutParams1.setMargins(0, 0, 0, 0);
             * _LayoutParams1.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
             */
            // mImageView.bringToFront();


            // mImageView.setLayoutParams(_LayoutParams1);
            mViewPager.setCurrentItem(1);// 让下方ViewPager跟随上面的HorizontalScrollView切换
        } else if (checkedId == R.id.btn2) {
            _TranslateAnimation = new TranslateAnimation(
                    mCurrentCheckedRadioLeft, getResources().getDimension(
                    R.dimen.rdo2), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            mViewPager.setCurrentItem(2);
        } else if (checkedId == R.id.btn3) {
            _TranslateAnimation = new TranslateAnimation(
                    mCurrentCheckedRadioLeft, getResources().getDimension(
                    R.dimen.rdo3), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            mViewPager.setCurrentItem(3);
        } else if (checkedId == R.id.btn4) {
            _TranslateAnimation = new TranslateAnimation(
                    mCurrentCheckedRadioLeft, getResources().getDimension(
                    R.dimen.rdo4), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            mViewPager.setCurrentItem(4);
        } else if (checkedId == R.id.btn5) {
            _TranslateAnimation = new TranslateAnimation(
                    mCurrentCheckedRadioLeft, getResources().getDimension(
                    R.dimen.rdo5), 0f, 0f);

            _AnimationSet.addAnimation(_TranslateAnimation);
            _AnimationSet.setFillBefore(false);
            _AnimationSet.setFillAfter(true);
            _AnimationSet.setDuration(100);

            mViewPager.setCurrentItem(5);
        }

        mCurrentCheckedRadioLeft = getCurrentCheckedRadioLeft();// 更新当前蓝色横条距离左边的距离

        Log.i("zj", "getCurrentCheckedRadioLeft="
                + getCurrentCheckedRadioLeft());
        Log.i("zj", "getDimension=" + getResources().getDimension(R.dimen.rdo2));

        // 让ScrollBar自动跟随点击的button位置而变动，否则你点击到最右侧了，右侧的条目不会自动显示出来，还要手动滑动ScrollBar
        // 后面跟的x，y坐标，可以自己测试来给出，比如这里当你点击第1、2个按钮时x=-1、0，这里不可能存在负数，-1按0计，ScrollBar在最左侧即可，当你点击第3/4/5按钮时，ScrollBar适当右移100/200/300
        mHorizontalScrollView.smoothScrollTo((int) mCurrentCheckedRadioLeft
                - (int) getResources().getDimension(R.dimen.rdo2), 0);
    }

    /**
     * 获得当前被选中的RadioButton距离左侧的距离
     */
    private float getCurrentCheckedRadioLeft() {
        // TODO Auto-generated method stub
        if (mRadioButton1.isChecked()) {
            // Log.i("zj",
            // "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo1));
            return getResources().getDimension(R.dimen.rdo1);
        } else if (mRadioButton2.isChecked()) {
            // Log.i("zj",
            // "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo2));
            return getResources().getDimension(R.dimen.rdo2);
        } else if (mRadioButton3.isChecked()) {
            // Log.i("zj",
            // "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo3));
            return getResources().getDimension(R.dimen.rdo3);
        } else if (mRadioButton4.isChecked()) {
            // Log.i("zj",
            // "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo4));
            return getResources().getDimension(R.dimen.rdo4);
        } else if (mRadioButton5.isChecked()) {
            // Log.i("zj",
            // "currentCheckedRadioLeft="+getResources().getDimension(R.dimen.rdo5));
            return getResources().getDimension(R.dimen.rdo5);
        }
        return 0f;
    }

    private void iniListener() {
        // TODO Auto-generated method stub

        mRadioGroup.setOnCheckedChangeListener(this);

        mViewPager.setOnPageChangeListener(new MyPagerOnPageChangeListener());
    }

    private void iniController() {
        // TODO Auto-generated method stub
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mRadioButton1 = (RadioButton) findViewById(R.id.btn1);
        mRadioButton2 = (RadioButton) findViewById(R.id.btn2);
        mRadioButton3 = (RadioButton) findViewById(R.id.btn3);
        mRadioButton4 = (RadioButton) findViewById(R.id.btn4);
        mRadioButton5 = (RadioButton) findViewById(R.id.btn5);
        mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
        mViewPager = (ViewPager) findViewById(R.id.pager);
    }

    /**
     * ViewPager的适配器
     *
     * @author zj 2012-5-24 下午2:26:57
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void destroyItem(View v, int position, Object obj) {
            // TODO Auto-generated method stub
            ((ViewPager) v).removeView(mViews.get(position));
        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return mViews.size();
        }

        @Override
        public Object instantiateItem(View v, int position) {
            ((ViewPager) v).addView(mViews.get(position));
            return mViews.get(position);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

    }

    /**
     * ViewPager的PageChangeListener(页面改变的监听器)
     *
     * @author zj 2012-5-24 下午3:14:27
     */
    private class MyPagerOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        /**
         * ★别忘了！！！滑动ViewPager的时候,让上方的HorizontalScrollView自动切换
         */
        @Override
        public void onPageSelected(int position) {
            // TODO Auto-generated method stub
            // Log.i("zj", "position="+position);

            if (position == 0) {
                // 必须是写setCurrentItem(1)，上边设置的是radiogroup的选择改变监听事件，而不是点击事件
                // 你预先点击performClick，并不会实现把页面切回到A页面的操作
                mViewPager.setCurrentItem(1);
                // mRadioButton1.performClick();
            } else if (position == 1) {
                mRadioButton1.performClick();
            } else if (position == 2) {
                mRadioButton2.performClick();
            } else if (position == 3) {
                mRadioButton3.performClick();
            } else if (position == 4) {
                mRadioButton4.performClick();
            } else if (position == 5) {
                mRadioButton5.performClick();
            } else if (position == 6) {
                mViewPager.setCurrentItem(5);
                // mRadioButton5.performClick();
            }
        }

    }
}
