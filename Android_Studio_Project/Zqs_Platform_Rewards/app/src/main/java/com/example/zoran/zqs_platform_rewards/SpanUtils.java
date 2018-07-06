package com.example.zoran.zqs_platform_rewards;

import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

/**
 * Created by zqs on 2018/5/14.
 */
public class SpanUtils {
    private static final int COLOR_DEFAULT = 0x00000000; //black color
    private int           foregroundColor;
    private int           backgroundColor;
    private boolean       isBold;
    private int           flag;

    private SpannableStringBuilder mBuilder;
    private CharSequence mText;

    private int mType;
    private final int mTypeCharSequence = 0;

    public SpanUtils() {
        mBuilder = new SpannableStringBuilder();
        mText = "";
        setDefault();
    }

    private void setDefault() {
        flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
        foregroundColor = COLOR_DEFAULT;
        backgroundColor = COLOR_DEFAULT;
        isBold = false;
    }

    /**
     * Create the span string.
     *
     * @return the span string
     */
    public SpannableStringBuilder create() {
        applyLast();
        return mBuilder;
    }

    /**
     * Append the text text.
     *
     * @param text The text.
     */
    public SpanUtils append(@NonNull final CharSequence text) {
        apply(mTypeCharSequence);
        mText = text;
        return this;
    }

    private void apply(final int type) {
        applyLast();
        mType = type;
    }

    private void applyLast() {
        if (mType == mTypeCharSequence) {
            updateCharCharSequence();
        }
        setDefault();
    }

    private void updateCharCharSequence() {
        if (mText.length() == 0) return;
        int start = mBuilder.length();
        mBuilder.append(mText);
        int end = mBuilder.length();
        if (foregroundColor != COLOR_DEFAULT) {
            mBuilder.setSpan(new ForegroundColorSpan(foregroundColor), start, end, flag);
        }
        if (backgroundColor != COLOR_DEFAULT) {
            mBuilder.setSpan(new BackgroundColorSpan(backgroundColor), start, end, flag);
        }
        if (isBold) {
            mBuilder.setSpan(new StyleSpan(Typeface.BOLD), start, end, flag);
        }
    }

    /**
     * Set the span of foreground's color.
     *
     * @param color The color of foreground
     */
    public SpanUtils setForegroundColor(@ColorInt final int color) {
        this.foregroundColor = color;
        return this;
    }

    /**
     * Set the span of background's color.
     *
     * @param color The color of background
     */
    public SpanUtils setBackgroundColor(@ColorInt final int color) {
        this.backgroundColor = color;
        return this;
    }

    /**
     * Set the span of bold.
     *
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setBold() {
        isBold = true;
        return this;
    }
}
