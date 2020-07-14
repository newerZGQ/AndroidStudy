package study.zgq.com.androidstudy.activity;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

import study.zgq.com.androidstudy.Person;
import study.zgq.com.androidstudy.R;
import study.zgq.com.androidstudy.Util;
import study.zgq.com.androidstudy.view.MyTextView;
import study.zgq.com.androidstudy.view.MyViewGroup;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
//        findViewById(R.id.v1).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//            }
//        });
        findViewById(R.id.v1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("t","");
            }
        });
//        findViewById(R.id.v1).setEnabled(false);
//        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MyTextView textView = findViewById(R.id.tv);
//                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) textView.getLayoutParams();
//                params.height = params.height * 2;
//                textView.setLayoutParams(params);
////                textView.setTranslationX(400);
////                findViewById(R.id.tv).invalidate();
//            }
//        });
//        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                new AsyncTask<Object, Object, Object>(){
//////
//////                    @Override
//////                    protected Object doInBackground(Object[] objects) {
//////                        return null;
//////                    }
//////                }.execute();
//                ((MyTextView)findViewById(R.id.tv1)).setText("yyyyy");
//            }
//        });
//        findViewById(R.id.image).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Glide.with(Main5Activity.this).load("https://gw.alicdn.com/tfs/TB188cnvQT2gK0jSZFkXXcIQFXa-80-96.png")
//                        .into((ImageView) Main5Activity.this.findViewById(R.id.image));
//            }
//        });
//        findViewById(R.id.image1).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Glide.with(Main5Activity.this).load("https://gw.alicdn.com/tfs/TB188cnvQT2gK0jSZFkXXcIQFXa-80-96.png")
//                        .into((ImageView) Main5Activity.this.findViewById(R.id.image1));
//            }
//        });
//        findViewById(R.id.testBtn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final View view = findViewById(R.id.testBtn);
//                ObjectAnimator animator = ObjectAnimator.ofFloat(view, "translationY", 0, 200);
//
//                animator.setDuration(500);
//                animator.start();

//                ValueAnimator animator = ValueAnimator.ofFloat(0,200);
//                animator.setDuration(500);
//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        float value = (Float) animation.getAnimatedValue();
//                        view.setTranslationY(value);
//                    }
//                });
//                animator.start();

//                ValueAnimator animator = ValueAnimator.ofObject(new TypeEvaluator<Person>() {
//                    @Override
//                    public Person evaluate(float fraction, Person startValue, Person endValue) {
//                        Util.print("" + fraction + "\n");
//                        Util.print("" + startValue.getName());
//                        view.setTranslationY(endValue.getAge() * fraction);
////
//                        return new Person("y", 6);
//                    }
//                }, new Person("q",0),new Person("w",400));
//                animator.setDuration(200);
//                animator.setInterpolator(new Interpolator() {
//                    @Override
//                    public float getInterpolation(float input) {
//                        Util.print("Interpolator" + input);
//                        return input;
//                    }
//                });
////                animator.setInterpolator(new AccelerateDecelerateInterpolator());
//                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                    @Override
//                    public void onAnimationUpdate(ValueAnimator animation) {
//                        Person person = (Person) animation.getAnimatedValue();
//                        Util.print("" + person.getName());
//                    }
//                });
//                animator.start();
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Util.print("event test: activity dispatchTouchEvent"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Util.print("event test: activity onTouchEvent"+event.getAction());
        return super.onTouchEvent(event);
    }
}
