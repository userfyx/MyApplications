package com.hhzmy.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DarwCircle circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circle=(DarwCircle)findViewById(R.id.circle);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float cx = circle.getX();
                ObjectAnimator anim1 = ObjectAnimator.ofFloat(circle, "scaleX",
                       1f, 2f);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(circle, "scaleY",
                        2f, 1f);
                //x位移
                ObjectAnimator anim3 = ObjectAnimator.ofFloat(circle,
                        "x",  cx ,  0f);
                ObjectAnimator anim4 = ObjectAnimator.ofFloat(circle,
                        "x", cx);
//                AnimatorSet animSet = new AnimatorSet();
//                animSet.setDuration(2000);
//                animSet.setInterpolator(new LinearInterpolator());
//                //两个动画同时执行
//                animSet.playTogether(anim1, anim2);
//                animSet.start();
                /**
                 * anim1，anim2,anim3同时执行
                 * anim4接着执行
                 */
                AnimatorSet animSet = new AnimatorSet();
                animSet.play(anim1).with(anim2);
                animSet.play(anim2).with(anim4);
                animSet.play(anim3).after(anim4);
                animSet.setDuration(1000);
                animSet.start();

            }
        });
    }
}
