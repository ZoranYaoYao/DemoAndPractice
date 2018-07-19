package com.zqs.rippleviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by herr.wang on 2017/10/16.
 * TODO any heavy operation on main thread will interrupt the animation.
 */
public class RippleUpdateView extends View {

    private static final int STEP = 16;

    private int mRippleCount;
    private int mMaxRadius;
    private int mColor;

    private int mWidth, mHeight;
    private boolean mRunning;
    private List<Circle> list = new ArrayList<>();

    public RippleUpdateView(Context context) {
        this(context, null);
    }

    public RippleUpdateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleUpdateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Ripple);
        mRippleCount = typedArray.getInt(R.styleable.Ripple_count, 1);
        mColor = typedArray.getColor(R.styleable.Ripple_color, Color.parseColor("#9DA3B4"));
        typedArray.recycle();


        for (int i = 1; i <= mRippleCount; i++) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(mColor);
            paint.setAlpha(Circle.DEFAULT_ALPHA);
            Circle circle = new Circle("Circle-" + i, 0, false, paint);
            list.add(circle);
        }
    }

    private class Circle {
        public static final int DEFAULT_ALPHA = 100; //默认半透明开始

        String name;
        int radius;
        boolean isRunning;
        Paint paint;

        public Circle(String name, int radius, boolean isRunning, Paint paint) {
            this.name = name;
            this.radius = radius;
            this.isRunning = isRunning;
            this.paint = paint;
        }

        public void setRunning(boolean running) {
            isRunning = running;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (widthMeasureSpec != MeasureSpec.EXACTLY) {
            mWidth = resolveSize(mMaxRadius * 2, widthMeasureSpec);
            mHeight = resolveSize(mMaxRadius * 2, heightMeasureSpec);
            setMeasuredDimension(mWidth, mHeight);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mMaxRadius = w / 2;
    }

    int count = 0;
    @Override
    protected void onDraw(Canvas canvas) {
        Log.e("zqs11", "onDraw() count = " + (++count));
        if (mRunning) {
            list.get(0).setRunning(true);
            drawRipple(canvas);
//            postInvalidateDelayed(40);
            //update
            removeCallbacks(refreshRunnable);
            postDelayed(refreshRunnable,40);
        }
    }

    private Runnable refreshRunnable = new Runnable() {
        @Override
        public void run() {
            postInvalidate();
        }
    };

    public void reset() {
        mRunning = false;
        for (Circle circle : list) {
            circle.radius = 0;
            circle.setRunning(false);
            circle.paint.setAlpha(Circle.DEFAULT_ALPHA);
        }
        removeCallbacks(refreshRunnable);
    }

    private void drawRipple(Canvas canvas) {
        for (int i = 0; i < list.size(); i++) {
            Circle circle = list.get(i);
            if (circle.radius >= mMaxRadius / 3) {
                list.get((i + 1) % mRippleCount).setRunning(true); //下一个圆圈开始
            }

            if (circle.isRunning && mRunning) {
                if (circle.radius > mMaxRadius || circle.radius > mMaxRadius - STEP) {
                    if (i == 2) {
                        reset();
                        UIHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mRunning = true;
                                RippleUpdateView.this.postInvalidate();
                            }
                        }, 2000);
                    }
                } else {
                    Log.e("zqs1", circle.name + ": alpha = " + circle.paint.getAlpha() + ": radius = " + circle.radius);
                    circle.paint.setAlpha(circle.paint.getAlpha());
                    canvas.drawCircle(mWidth / 2, mHeight / 2, circle.radius, circle.paint);

                    if (circle.radius < mMaxRadius) {
                        circle.radius += STEP;
                        if (circle.paint.getAlpha() > 0) {
                            float cur = (float) circle.radius / mMaxRadius > 1.0f ? 1.0f : (float) circle.radius / mMaxRadius;
                            circle.paint.setAlpha((int) ((1 - cur) * Circle.DEFAULT_ALPHA));
                        } else {
                            circle.paint.setAlpha(0);
                        }
                    }
                }
            }
        }

    }

    public void start() {
        if (!mRunning) {
            reset();
            mRunning = true;
            postInvalidate();
        }
    }

    public void stop() {
        reset();
    }

    public boolean isRunning() {
        return mRunning;
    }


}
