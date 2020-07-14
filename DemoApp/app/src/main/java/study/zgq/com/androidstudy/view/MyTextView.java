package study.zgq.com.androidstudy.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;

import study.zgq.com.androidstudy.R;
import study.zgq.com.androidstudy.Util;

public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        array.getColor(R.styleable.MyTextView_testColor, CalendarContract.Colors.TYPE_CALENDAR);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        array.getColor(R.styleable.MyTextView_testColor, CalendarContract.Colors.TYPE_CALENDAR);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        array.getColor(R.styleable.MyTextView_testColor, CalendarContract.Colors.TYPE_CALENDAR);
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
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
