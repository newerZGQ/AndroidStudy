package study.zgq.com.androidstudy.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import study.zgq.com.androidstudy.R;
import study.zgq.com.androidstudy.Util;

public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int rows = 0;
        int maxWidth = getMeasuredWidth();
        int lineWidth = 0;
        for (int i = 0; i < getChildCount(); i++){
            View child = getChildAt(i);
            lineWidth += child.getMeasuredWidth();
            if (lineWidth > maxWidth){
                rows++;
                lineWidth = child.getMeasuredWidth();
            }
            child.layout(lineWidth - child.getMeasuredWidth(),
                    rows * (child.getMeasuredHeight()),
                    lineWidth,
                    rows * (child.getMeasuredHeight()) + child.getMeasuredHeight() + 100);
        }
        for(int i = 0; i < getChildCount(); i++){
            LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
        }
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Util.print("event test: viewgroup onInterceptTouchEvent"+ev.getAction());
//        if (ev.getAction() == MotionEvent.ACTION_MOVE){
//            return true;
//        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Util.print("event test: viewgroup dispatchTouchEvent"+ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Util.print("event test: viewgroup onTouchEvent"+event.getAction());
        return super.onTouchEvent(event);
    }

    public static class LayoutParams extends ViewGroup.LayoutParams{

        private int ttcolor;

        @SuppressLint("ResourceType")
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray styledAttrs =
                    c.obtainStyledAttributes(attrs, R.styleable.MyViewGroup_Layout);
            ttcolor = styledAttrs.getColor(R.styleable.MyViewGroup_Layout_layout_ttcolor,R.color.colorAccent);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
