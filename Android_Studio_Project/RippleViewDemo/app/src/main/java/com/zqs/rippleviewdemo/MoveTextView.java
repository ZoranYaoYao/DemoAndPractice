package com.zqs.rippleviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class MoveTextView extends TextView {
    public String mText;
    private int mTextcolor;
    private int mTextSize;

    private Rect mBound;
    private Paint mTextPaint;
    private Paint mBackPaint;
    private int mBackground;

    public MoveTextView(Context context) {
        this(context, null);
    }

    public MoveTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MoveTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MoveTextView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.MoveTextView_text:
                    mText = a.getString(attr);
                    break;
                case R.styleable.MoveTextView_textColor:
                    mTextcolor = a.getColor(attr, Color.BLACK);
                    break;
                case R.styleable.MoveTextView_textSize:
                    mTextSize = a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SHIFT, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.MoveTextView_backColor:
                    mBackground = a.getColor(attr,Color.BLACK);
            }
        }
        a.recycle();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mBound = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), mBound);
        mTextPaint.setColor(mTextcolor);
        mBackPaint =new Paint();
        mBackPaint.setColor(mBackground);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            mTextPaint.setTextSize(mTextSize);
            mTextPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float textWidth = mBound.width();
            width = (int) (getPaddingLeft() + textWidth + getPaddingRight());
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            mTextPaint.setTextSize(mTextSize);
            mTextPaint.getTextBounds(mText, 0, mText.length(), mBound);
            float textHeight = mBound.height();
            height = (int) (getPaddingTop() + textHeight + getPaddingBottom());
        }
        maxWidth = width;
        mHeight = height;
        setMeasuredDimension(width, height);
    }


    public int mWidth;
    public int maxWidth;
    public int mHeight;
    public int orientation = 0;
    public static final int INIT = 0;
    public static final int TO_LEFT = 1;
    public static final int TO_RIGHT =2;
    public static final int startBackGround = R.color.color_f8b442;

    /**
     * 文本字体位置基于: left + 文本距离
     *      left = maxWidth - mWidth
     *      文本距离: (mWidth - mBound.width()) / 2
     */
    @Override
    protected void onDraw(Canvas canvas) {
        switch (orientation) {
            case INIT:
                mWidth = maxWidth;
                canvas.drawRect(0, 0, maxWidth, mHeight, mBackPaint);
                canvas.drawText(mText, maxWidth/ 2 - mBound.width() / 2, mHeight / 2 + mBound.height() / 2, mTextPaint);
                break;
            case TO_RIGHT:
                canvas.drawRect(maxWidth - mWidth, 0, maxWidth, mHeight, mBackPaint);
                canvas.drawText(mText, maxWidth - mWidth + (mWidth - mBound.width()) / 2, mHeight / 2 + mBound.height() / 2, mTextPaint);
                break;
            case TO_LEFT:
                int left = maxWidth - mWidth;
                if(maxWidth - mWidth < 0)  {
                    left = 0;
                    mBackPaint.setColor(getContext().getResources().getColor(startBackGround));
                }
                canvas.drawRect(left, 0, maxWidth, mHeight, mBackPaint);
                canvas.drawText(mText, maxWidth-mWidth+(mWidth-mBound.width())/2, mHeight / 2 + mBound.height() / 2,mTextPaint);
                break;
        }


        if (orientation != INIT) {
            if(orientation == TO_RIGHT) {
                removeCallbacks(toRightRunnable);
                postDelayed(toRightRunnable, 10);
            } else {
                removeCallbacks(toLeftRunnable);
                postDelayed(toLeftRunnable,10);
            }
        }

    }

    int rightEnd;
    private Runnable toRightRunnable = new Runnable() {
        @Override
        public void run() {
            if (mWidth > rightEnd) {
                mWidth -= 40;
                postInvalidate();
            } else {
                mWidth = rightEnd;
                callback.animaOverCallback(MoveTextView.this);
            }
        }
    };

    private Runnable toLeftRunnable = new Runnable() {
        @Override
        public void run() {
            if(maxWidth - mWidth > 0) {
                mWidth += 40;
                postInvalidate();
            }else {
                mWidth = maxWidth;
            }
        }
    };


    public void start(int orientation) {
        this.orientation = orientation;
        postInvalidate();
    }
    public void setBackground(int color) {
        mBackPaint.setColor(getContext().getResources().getColor(color));
    }


    Callback callback;
    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void animaOverCallback(View view);
    }

}
