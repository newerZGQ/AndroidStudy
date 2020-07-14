package study.zgq.com.androidstudy.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import study.zgq.com.androidstudy.Util;

public class MyView extends View {
    public MyView(Context context) {
        super(context);
        this.setClickable(true);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setClickable(true);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setClickable(true);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setClickable(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Util.print("event test: view dispatchTouchEvent"+event.getAction());
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Util.print("event test: view onTouchEvent"+event.getAction());
//        if (event.getAction() == MotionEvent.ACTION_DOWN){
//            return true;
//        }
        boolean res = super.onTouchEvent(event);
        Util.print("event test: view onTouchEvent"+ res);
        return res;
    }
}
