package com.srtianxia.tablayoutlibs;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by srtianxia on 2016/7/25.
 */
public class HorusTabLayout extends View {
    private int mScreenWidth;
    private int mItemWidth;
    private int mItemSize;
    private List<String> mItemsTitle = new ArrayList<>();

    private Paint mTextPaint;
    private Paint mIndicatorPaint;
    private Path mPath;

    private String mColorChoose = "#ffffff";
    private String mColorUnChoose = "#000000";

    private OnTabSelectListener mOnTabSelectListener;

    private int mPosition = 0;

    private int mLastPosition = 0;

    private float mPositionOffset = 0;

    private boolean isInit = false;


    public void setOnTabSelectListener(OnTabSelectListener mOnTabSelectListener) {
        this.mOnTabSelectListener = mOnTabSelectListener;
    }


    public HorusTabLayout(Context context) {
        this(context, null);
    }


    public void setTitle(List<String> itemsTitle) {
        this.mItemsTitle = itemsTitle;
        mItemWidth = mScreenWidth / itemsTitle.size();
    }


    public HorusTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public HorusTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScreenWidth = getResources().getDisplayMetrics().widthPixels;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.HorusTabLayout);
        typedArray.recycle();

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(50);
        mIndicatorPaint = new Paint();
        mIndicatorPaint.setAntiAlias(true);
        mPath = new Path();
    }


    @Override protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int i = 0;
        for (String title : mItemsTitle) {
            if (i == mPosition) {
                drawText(canvas, i, mColorChoose, title);
                drawIndicator(canvas, i, mColorChoose);
            } else {
                drawText(canvas, i, mColorUnChoose, title);
                //drawIndicator(canvas, i, mColorUnChoose);
            }
            i++;
        }
    }


    @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    private void drawText(Canvas canvas, int index, String color, String title) {
        mTextPaint.setColor(Color.parseColor(color));
        canvas.drawText(title,
            mItemWidth * index + mItemWidth / 2f - mTextPaint.measureText(title) / 2f,
            getHeight() / 2f - (mTextPaint.descent() + mTextPaint.ascent()) / 2f,
            mTextPaint);
    }


    private void drawIndicator(Canvas canvas, int index, String color) {
        mIndicatorPaint.setColor(Color.parseColor(color));
        mPath.reset();
        mPath.moveTo(mPositionOffset * mItemWidth + mItemWidth / 2,
            getHeight() - 8);
        mPath.lineTo(mPositionOffset * mItemWidth + mItemWidth / 2 + 8,
            getHeight());
        mPath.lineTo(mPositionOffset * mItemWidth + mItemWidth / 2 - 8,
            getHeight());
        canvas.drawPath(mPath, mIndicatorPaint);
    }


    //获取Text高度
    private int getTextHeight(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return (int) (rect.height() / 33f * 29);
    }


    @Override public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastPosition = mPosition;
                mPosition = (int) Math.floor(event.getX() / mItemWidth);
                if (mLastPosition != mPosition) {
                    startMoveAnim();
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (mOnTabSelectListener != null) {
                    mOnTabSelectListener.onClick(mPosition);
                }
                break;
        }
        return true;
    }


    private void startMoveAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mLastPosition, mPosition);
        valueAnimator.setDuration(100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mPositionOffset = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }


    public interface OnTabSelectListener {
        void onClick(int position);
    }
}
