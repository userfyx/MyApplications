package com.bw.test.myapplication;

/**
 * Created by a on 2016/11/5.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by wangjitao on 2016/10/14 0014.
 * 慢慢的绘制一个圆
 */
public class CircleViewOne extends View {
    /**
     * 第一种颜色
     *//*
    private int mFirstColor;
    *//**
     * 圆弧的宽度
     *//*
    private int mCircleWidth;
    *//**
     * 画笔
     *//*
    private Paint mPaint;
    *//**
     * 圆弧的度数
     *//*
    private int mProgress;
    *//**
     * 圆弧绘制的速度
     *//*
    private int mSpeed;

    *//**
     * 是否继续绘制
     *//*
    private boolean isDrawCircle = true;


    public CircleViewOne(Context context) {
        this(context, null);
    }

    public CircleViewOne(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    *//**
     * 获取自定义控件的一些值
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     *//*
    public CircleViewOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleViewOne, defStyleAttr, 0);

        for (int i = 0; i < a.getIndexCount(); i++) {

            switch (a.getIndex(i)) {
                case R.styleable.CircleViewOne_color:
                    mFirstColor = a.getColor(a.getIndex(i), Color.WHITE);
                    break;
                case R.styleable.CircleViewOne_drawSpeed:
                    mSpeed = a.getInt(a.getIndex(i), 20);
                    break;
                case R.styleable.CircleViewOne_circleWidthOne:
                    mCircleWidth = a.getDimensionPixelOffset(a.getIndex(i), (int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_PX, 20, getResources().getDisplayMetrics()));
                    break;
            }
        }
        a.recycle();
        mPaint = new Paint();

        //绘图线程
        new Thread() {
            @Override
            public void run() {
                while (isDrawCircle) {
                    mProgress++;
                    if (mProgress == 360) {
                        isDrawCircle = false;
                    } else {
                        isDrawCircle = true;
                        postInvalidate();
                        try {
                            Thread.sleep(mSpeed); //通过传递过来的速度参数来决定线程休眠的时间从而达到绘制速度的快慢
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }
            }
        }.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int center = getWidth() / 2;
        int radius = center - mCircleWidth / 2;
        mPaint.setStrokeWidth(mCircleWidth); // 设置圆环的宽度
        mPaint.setAntiAlias(true); // 消除锯齿
        mPaint.setStyle(Paint.Style.STROKE); // 设置空心
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius); // 用于定义的圆弧的形状和大小的界限

        mPaint.setColor(mFirstColor); // 设置圆环的颜色
        canvas.drawArc(oval, -90, mProgress, false, mPaint); // 根据进度画圆弧
    }*/




        private final  Paint paint;
        private final Context context;

        public CircleViewOne(Context context) {

            // TODO Auto-generated constructor stub
            this(context, null);
        }

        public CircleViewOne(Context context, AttributeSet attrs) {
            super(context, attrs);
            // TODO Auto-generated constructor stub
            this.context = context;
            this.paint = new Paint();
            this.paint.setAntiAlias(true); //消除锯齿
            this.paint.setStyle(Paint.Style.STROKE); //绘制空心圆
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            int center = getWidth()/2;
            int innerCircle = dip2px(context, 83); //设置内圆半径
            int ringWidth = dip2px(context, 5); //设置圆环宽度

            //绘制内圆
            this.paint.setARGB(155, 167, 190, 206);
            this.paint.setStrokeWidth(2);
            canvas.drawCircle(center,center, innerCircle, this.paint);

            //绘制圆环
            this.paint.setARGB(255, 212 ,225, 233);
            this.paint.setStrokeWidth(ringWidth);
            canvas.drawCircle(center,center, innerCircle+1+ringWidth/2, this.paint);

            //绘制外圆
            this.paint.setARGB(155, 167, 190, 206);
            this.paint.setStrokeWidth(2);
            canvas.drawCircle(center,center, innerCircle+ringWidth, this.paint);


            super.onDraw(canvas);
        }


        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        public static int dip2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }

}
