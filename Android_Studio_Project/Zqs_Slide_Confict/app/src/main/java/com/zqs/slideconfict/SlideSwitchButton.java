package com.zqs.slideconfict;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import java.util.ArrayList;
import java.util.List;

public class SlideSwitchButton extends View {
    private static final int DEFAULT_BACKGROUND_COLOR = 0xff514d57;
    private static final int DEFAULT_TAB_TEXT_COLOR = 0xffffffff;
    private static final int DEFAULT_SLIDE_COLOR = 0xffff7a3f;
    private List<String> tabs;
    private int mWidth, mHeight;
    private int mTouchSlop;

    private int mBackgroundColor;
    private int mSliderColor;
    private int mTabTextColor;
    private int mTabSelectedTextColor;
    private float mTabTextSize;
    private float mCornerRadius;

    private float mStrokeWidth;
    private int mStrokeColor;

    private int mTabIndex;
    private int tabWidth;

    private Paint paint;
    private Paint textPaint;

    private float xOffset;

    private OnSlideCallback onSlideCallback;


    public SlideSwitchButton(Context context) {
        this(context, null);
    }

    public SlideSwitchButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideSwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SlideSwitchButton);
        mBackgroundColor = typedArray.getColor(R.styleable.SlideSwitchButton_background_color, DEFAULT_BACKGROUND_COLOR);
        mSliderColor = typedArray.getColor(R.styleable.SlideSwitchButton_slide_color, DEFAULT_SLIDE_COLOR);
        mTabTextColor = typedArray.getColor(R.styleable.SlideSwitchButton_tab_text_color, DEFAULT_TAB_TEXT_COLOR);
        mTabSelectedTextColor = typedArray.getColor(R.styleable.SlideSwitchButton_tab_selected_text_color, DEFAULT_TAB_TEXT_COLOR);
        mTabTextSize = typedArray.getDimension(R.styleable.SlideSwitchButton_tab_text_size, 14.0f);
        mCornerRadius = typedArray.getDimension(R.styleable.SlideSwitchButton_cornor_radius, 6.0f);
        mTabIndex = typedArray.getInt(R.styleable.SlideSwitchButton_tab_index, 0);
        mStrokeWidth = typedArray.getFloat(R.styleable.SlideSwitchButton_stroke_width, 0);
        if (mStrokeWidth > 0) {
            mStrokeColor = typedArray.getColor(R.styleable.SlideSwitchButton_stroke_color, DEFAULT_BACKGROUND_COLOR);
        }
        typedArray.recycle();
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        tabs = new ArrayList<>();
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setColor(mTabTextColor);
        textPaint.setTextSize(mTabTextSize);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
    }

    /**
     * call before onDraw()
     * instant refresh not supported for now.
     *
     * @param tabs
     */
    public void setTabs(List<String> tabs) {
        this.tabs = tabs;
    }

    /**
     * call before #setTabs
     *
     * @param tabIndex
     */
    public void setTabIndex(int tabIndex) {
        mTabIndex = tabIndex;
    }

    public void setOnSlideCallback(OnSlideCallback onSlideCallback) {
        this.onSlideCallback = onSlideCallback;
    }

    /**
     * setIndex and refresh now
     */
    public void setIndexInvalidate(int index) {
        if (index < 0 || tabs == null || index >= this.tabs.size()) {
            return;
        }
        this.mTabIndex = index;
        if (tabWidth == 0) {
            tabWidth = mWidth / tabs.size();
        }
        xOffset = tabWidth * mTabIndex;
        invalidate();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        if (this.tabs != null && this.tabs.size() > 0) {
            tabWidth = mWidth / this.tabs.size();
            //check the index valid or not
            if (mTabIndex < 0 || mTabIndex >= this.tabs.size()) {
                mTabIndex = 0;
            }
            xOffset = tabWidth * mTabIndex;
        }
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    /**
     * TODO background and text will re-draw many times when scroll, is there any way to draw text on the top layer???
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw background
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mBackgroundColor);
        RectF rectf = new RectF(0, 0, mWidth, mHeight);
        canvas.drawRoundRect(rectf, mCornerRadius, mCornerRadius, paint);

        //draw stroke
        if (mStrokeWidth > 0) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(mStrokeWidth);
            paint.setColor(mStrokeColor);
            canvas.drawRoundRect(rectf, mCornerRadius, mCornerRadius, paint);
        }

        if (tabs.size() > 0) {
            tabWidth = mWidth / tabs.size();
        } else {
            tabWidth = mWidth / 2;
        }

        //draw slide
        paint.setColor(mSliderColor);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
        RectF slideRect = new RectF(xOffset, 0, tabWidth + xOffset, mHeight);
        canvas.drawRoundRect(slideRect, mCornerRadius, mCornerRadius, paint);

        //draw text
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        int baseY = (int) (mHeight / 2 - fm.top / 2 - fm.bottom / 2);
        for (int i = 0; i < tabs.size(); i++) {
            if (mTabIndex == i) {
                textPaint.setColor(mTabSelectedTextColor);
                canvas.drawText(tabs.get(i), tabWidth * i + tabWidth / 2, baseY, textPaint);
            } else {
                textPaint.setColor(mTabTextColor);
                canvas.drawText(tabs.get(i), tabWidth * i + tabWidth / 2, baseY, textPaint);
            }
        }

    }

    float dx, lastX, lastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //解决首次点击乱跳问题
                lastX = event.getX();
                lastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                dx = event.getX() - lastX;
                lastX = event.getX();
                xOffset += dx;
                if (xOffset < 0) {
                    xOffset = 0;
                }
                if (xOffset > mWidth - tabWidth) {
                    xOffset = mWidth - tabWidth;
                }
                if (Math.abs(xOffset) > mTouchSlop) {
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                determineIndex(event.getX() > mWidth - tabWidth ? mWidth - tabWidth : event.getX());
                break;

        }
        return true;
    }

    private void determineIndex(float finalPos) {
        int curTab = (int) (finalPos / tabWidth);
        if (mTabIndex != curTab) {
            mTabIndex = curTab;
            if (onSlideCallback != null) {
                onSlideCallback.onTabChanged(mTabIndex);
            }
        }
        scroll2Tab();
    }

    private void scroll2Tab() {
        final float tempOffsetX = xOffset;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(tempOffsetX, mTabIndex * tabWidth);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                xOffset = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(200);
        valueAnimator.start();
    }

    public interface OnSlideCallback {
        void onTabChanged(int index);
    }

}
