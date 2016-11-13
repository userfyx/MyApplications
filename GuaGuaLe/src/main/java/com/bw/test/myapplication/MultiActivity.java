package com.bw.test.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;

public class MultiActivity extends AppCompatActivity {
    private int imageX=0;
    private int imageY=0;
    private SurfaceHolder holder=null;
    private int screenWidth=0;
    private int screenHeight=0;
    private int imageWidth=0;
    private int imageHeight=0;
    private Bitmap bitmap=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.screenWidth=super.getWindowManager().getDefaultDisplay().getWidth();
        this.screenHeight=super.getWindowManager().getDefaultDisplay().getHeight();
        this.bitmap= BitmapFactory.decodeResource(super.getResources(),R.mipmap.ic_launcher);
        this.imageWidth=this.bitmap.getWidth();
        this.imageHeight=this.bitmap.getHeight();
        this.imageX=(screenWidth-imageWidth);
        this.imageY=(screenHeight-imageHeight);
        setContentView(new MySurfaceView(this));
    }
    private class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

        public MySurfaceView(Context context) {
            super(context);
            MultiActivity.this.holder=super.getHolder();
            MultiActivity.this.holder.addCallback(this);
            super.setFocusable(true);
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            MultiActivity.this.setImage(1,350,500);
        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }


    }

    private void setImage(float scale,int width,int height){
        Canvas canvas=MultiActivity.this.holder.lockCanvas();
        Paint paint=new Paint();
        canvas.drawRect(0,0,MultiActivity.this.screenWidth,MultiActivity.this.screenHeight,paint);
        Matrix matrix=new Matrix();
        matrix.postScale(scale,scale);
        Bitmap target=Bitmap.createBitmap(MultiActivity.this.bitmap,0,0,width,height,matrix,true);
        imageWidth=target.getWidth();
        imageHeight=target.getHeight();
        imageX=(screenWidth-imageWidth)/2;
        imageY=(screenHeight-imageHeight)/2;
        canvas.translate(imageX,imageY);
        canvas.drawBitmap(bitmap,matrix,paint);
        MultiActivity.this.holder.unlockCanvasAndPost(canvas);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointCount=event.getPointerCount();
        if(pointCount==2){
            float pointA=event.getY(0);
            float pointB=event.getY(1);
            if (pointA<pointB){
                float temp=pointA;
                pointA=pointB;
                pointB=temp;
            }
            if (!(event.getAction()== MotionEvent.ACTION_UP)){
                float scale=this.getScale(pointA,pointB);
                MultiActivity.this.setImage(scale,350,500);
            }

        }
        return super.onTouchEvent(event);
    }

    private float getScale(float pointA, float pointB) {
        return 0;
    }


}
