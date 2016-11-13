package com.hhzmy.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by a on 2016/11/11.
 */
public class DarwCircle extends View{


    public DarwCircle(Context context) {
        super(context);
    }

    public DarwCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DarwCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int x= (int) getX()+getWidth()/2;
        int y= (int) getY()+getHeight()/2;
        int r=20;
        Paint paint=new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(x,y,r,paint);
    }
}
