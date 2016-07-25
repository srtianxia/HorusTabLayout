package com.srtianxia.tablayoutlibs;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by srtianxia on 2016/7/25.
 */
public class HorusTabLayout extends View {
    private int mScreenWidth;


    public HorusTabLayout(Context context) {
        this(context, null);
    }


    public HorusTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public HorusTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }


    @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
