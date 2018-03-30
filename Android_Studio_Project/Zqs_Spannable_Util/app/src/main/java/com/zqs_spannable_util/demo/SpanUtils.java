package com.zqs_spannable_util.demo;

import android.annotation.SuppressLint;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by zqs on 2018/3/30.
 */

public class SpanUtils {
    private static final int COLOR_DEFAULT = 0xFEFFFFFF;
    private SpannableStringBuilder mBuilder;

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private int mType;
    private final int mTypeCharSequence = 0;
    private final int mTypeImage        = 1;
    private final int mTypeSpace        = 2;

    public static final int ALIGN_BOTTOM   = 0;
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_CENTER   = 2;
    public static final int ALIGN_TOP      = 3;

    private CharSequence  mText;
    private int           flag;
    private int           foregroundColor;
    private int           backgroundColor;
    private int           lineHeight;
    private int           alignLine;
    private int           quoteColor;
    private int           stripeWidth;
    private int           quoteGapWidth;
    private int           first;
    private int           rest;
    private int           bulletColor;
    private int           bulletRadius;
    private int           bulletGapWidth;
    private int           fontSize;
    private boolean       fontSizeIsDp;
    private float         proportion;
    private float         xProportion;
    private boolean       isStrikethrough;
    private boolean       isUnderline;
    private boolean       isSuperscript;
    private boolean       isSubscript;
    private boolean       isBold;
    private boolean       isItalic;
    private boolean       isBoldItalic;
    private String        fontFamily;
    private Typeface      typeface;
    private Layout.Alignment alignment;
    private ClickableSpan clickSpan;
    private String        url;
    private float         blurRadius;
    private BlurMaskFilter.Blur style;
    private Shader shader;
    private float         shadowRadius;
    private float         shadowDx;
    private float         shadowDy;
    private int           shadowColor;
    private Object[]      spans;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Align {
    }

    public SpanUtils() {
        mBuilder = new SpannableStringBuilder();
        mText = "";
        setDefault();
    }

    public SpannableStringBuilder create() {
        applyLast();
        return mBuilder;
    }

    private void setDefault() {
        flag = Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
        foregroundColor = COLOR_DEFAULT;
        backgroundColor = COLOR_DEFAULT;
        lineHeight = -1;
        quoteColor = COLOR_DEFAULT;
        first = -1;
        bulletColor = COLOR_DEFAULT;
        fontSize = -1;
        proportion = -1;
        xProportion = -1;
        isStrikethrough = false;
        isUnderline = false;
        isSuperscript = false;
        isSubscript = false;
        isBold = false;
        isItalic = false;
        isBoldItalic = false;
        fontFamily = null;
        typeface = null;
        alignment = null;
        clickSpan = null;
        url = null;
        blurRadius = -1;
        shader = null;
        shadowRadius = -1;
        spans = null;

//        imageBitmap = null;
//        imageDrawable = null;
//        imageUri = null;
//        imageResourceId = -1;
//
//        spaceSize = -1;
    }

    /**
     * Set the span of background's color.
     *
     * @param color The color of background
     * @return the single {@link SpanUtils} instance
     */
    public SpanUtils setBackgroundColor(@ColorInt final int color) {
        this.backgroundColor = color;
        return this;
    }

    public SpanUtils setLeadingMargin(@IntRange(from = 0) final int first,
                                      @IntRange(from = 0) final int rest) {
        this.first = first;
        this.rest = rest;
        return this;
    }

    public SpanUtils setQuoteColor(@ColorInt final int color) {
        return setQuoteColor(color, 2, 2);
    }

    public SpanUtils setBullet(@IntRange(from = 0) final int gapWidth) {
        return setBullet(0, 3, gapWidth);
    }

    public SpanUtils setBullet(@ColorInt final int color,
                               @IntRange(from = 0) final int radius,
                               @IntRange(from = 0) final int gapWidth) {
        this.bulletColor = color;
        this.bulletRadius = radius;
        this.bulletGapWidth = gapWidth;
        return this;
    }

    public SpanUtils setQuoteColor(@ColorInt final int color,
                                   @IntRange(from = 1) final int stripeWidth,
                                   @IntRange(from = 0) final int gapWidth) {
        this.quoteColor = color;
        this.stripeWidth = stripeWidth;
        this.quoteGapWidth = gapWidth;
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

    public SpanUtils setAlign(@NonNull final Layout.Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public SpanUtils setLineHeight(@IntRange(from = 0) final int lineHeight) {
        return setLineHeight(lineHeight, ALIGN_CENTER);
    }

    public SpanUtils setLineHeight(@IntRange(from = 0) final int lineHeight,
                                   @Align final int align) {
        this.lineHeight = lineHeight;
        this.alignLine = align;
        return this;
    }

    public SpanUtils setFontSize(@IntRange(from = 0) final int size) {
        return setFontSize(size, false);
    }

    public SpanUtils setFontSize(@IntRange(from = 0) final int size, final boolean isSp) {
        this.fontSize = size;
        this.fontSizeIsDp = isSp;
        return this;
    }

    public SpanUtils setForegroundColor(@ColorInt final int color) {
        this.foregroundColor = color;
        return this;
    }

    public SpanUtils appendLine(@NonNull final CharSequence text) {
        apply(mTypeCharSequence);
        mText = text + LINE_SEPARATOR;
        return this;
    }

    private void apply(final int mTypeCharSequence) {
        applyLast();
    }

    private void applyLast() {
        if (mType == mTypeCharSequence) {
            updateCharSequence();
        }
    }

    private void updateCharSequence() {
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
        if (first != -1) {
            mBuilder.setSpan(new LeadingMarginSpan.Standard(first, rest), start, end, flag);
        }
        if (quoteColor != COLOR_DEFAULT) {
            mBuilder.setSpan(
                    new CustomQuoteSpan(quoteColor, stripeWidth, quoteGapWidth),
                    start,
                    end,
                    flag
            );
        }
        if (bulletColor != COLOR_DEFAULT) {
            mBuilder.setSpan(
                    new CustomBulletSpan(bulletColor, bulletRadius, bulletGapWidth),
                    start,
                    end,
                    flag
            );
        }
        //        if (imGapWidth != -1) {
        //            if (imBitmap != null) {
        //                mBuilder.setSpan(
        //                        new CustomIconMarginSpan(imBitmap, imGapWidth, imAlign),
        //                        start,
        //                        end,
        //                        flag
        //                );
        //            } else if (imDrawable != null) {
        //                mBuilder.setSpan(
        //                        new CustomIconMarginSpan(imDrawable, imGapWidth, imAlign),
        //                        start,
        //                        end,
        //                        flag
        //                );
        //            } else if (imUri != null) {
        //                mBuilder.setSpan(
        //                        new CustomIconMarginSpan(imUri, imGapWidth, imAlign),
        //                        start,
        //                        end,
        //                        flag
        //                );
        //            } else if (imResourceId != -1) {
        //                mBuilder.setSpan(
        //                        new CustomIconMarginSpan(imResourceId, imGapWidth, imAlign),
        //                        start,
        //                        end,
        //                        flag
        //                );
        //            }
        //        }
        if (fontSize != -1) {
            mBuilder.setSpan(new AbsoluteSizeSpan(fontSize, fontSizeIsDp), start, end, flag);
        }
        if (proportion != -1) {
            mBuilder.setSpan(new RelativeSizeSpan(proportion), start, end, flag);
        }
        if (xProportion != -1) {
            mBuilder.setSpan(new ScaleXSpan(xProportion), start, end, flag);
        }
        if (lineHeight != -1) {
            mBuilder.setSpan(new CustomLineHeightSpan(lineHeight, alignLine), start, end, flag);
        }
        if (isStrikethrough) {
            mBuilder.setSpan(new StrikethroughSpan(), start, end, flag);
        }
        if (isUnderline) {
            mBuilder.setSpan(new UnderlineSpan(), start, end, flag);
        }
        if (isSuperscript) {
            mBuilder.setSpan(new SuperscriptSpan(), start, end, flag);
        }
        if (isSubscript) {
            mBuilder.setSpan(new SubscriptSpan(), start, end, flag);
        }
        if (isBold) {
            mBuilder.setSpan(new StyleSpan(Typeface.BOLD), start, end, flag);
        }
        if (isItalic) {
            mBuilder.setSpan(new StyleSpan(Typeface.ITALIC), start, end, flag);
        }
        if (isBoldItalic) {
            mBuilder.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), start, end, flag);
        }
        if (fontFamily != null) {
            mBuilder.setSpan(new TypefaceSpan(fontFamily), start, end, flag);
        }
        if (typeface != null) {
            mBuilder.setSpan(new CustomTypefaceSpan(typeface), start, end, flag);
        }
        if (alignment != null) {
            mBuilder.setSpan(new AlignmentSpan.Standard(alignment), start, end, flag);
        }
        if (clickSpan != null) {
            mBuilder.setSpan(clickSpan, start, end, flag);
        }
        if (url != null) {
            mBuilder.setSpan(new URLSpan(url), start, end, flag);
        }
//        if (blurRadius != -1) {
//            mBuilder.setSpan(
//                    new MaskFilterSpan(new BlurMaskFilter(blurRadius, style)),
//                    start,
//                    end,
//                    flag
//            );
//        }
        if (shader != null) {
            mBuilder.setSpan(new ShaderSpan(shader), start, end, flag);
        }
        if (shadowRadius != -1) {
            mBuilder.setSpan(
                    new ShadowSpan(shadowRadius, shadowDx, shadowDy, shadowColor),
                    start,
                    end,
                    flag
            );
        }
        if (spans != null) {
            for (Object span : spans) {
                mBuilder.setSpan(span, start, end, flag);
            }
        }
    }

    class CustomQuoteSpan implements LeadingMarginSpan {

        private final int color;
        private final int stripeWidth;
        private final int gapWidth;

        private CustomQuoteSpan(final int color, final int stripeWidth, final int gapWidth) {
            super();
            this.color = color;
            this.stripeWidth = stripeWidth;
            this.gapWidth = gapWidth;
        }

        public int getLeadingMargin(final boolean first) {
            return stripeWidth + gapWidth;
        }

        public void drawLeadingMargin(final Canvas c, final Paint p, final int x, final int dir,
                                      final int top, final int baseline, final int bottom,
                                      final CharSequence text, final int start, final int end,
                                      final boolean first, final Layout layout) {
            Paint.Style style = p.getStyle();
            int color = p.getColor();

            p.setStyle(Paint.Style.FILL);
            p.setColor(this.color);

            c.drawRect(x, top, x + dir * stripeWidth, bottom, p);

            p.setStyle(style);
            p.setColor(color);
        }
    }

    class CustomBulletSpan implements LeadingMarginSpan {

        private final int color;
        private final int radius;
        private final int gapWidth;

        private Path sBulletPath = null;

        private CustomBulletSpan(final int color, final int radius, final int gapWidth) {
            this.color = color;
            this.radius = radius;
            this.gapWidth = gapWidth;
        }

        public int getLeadingMargin(final boolean first) {
            return 2 * radius + gapWidth;
        }

        public void drawLeadingMargin(final Canvas c, final Paint p, final int x, final int dir,
                                      final int top, final int baseline, final int bottom,
                                      final CharSequence text, final int start, final int end,
                                      final boolean first, final Layout l) {
            if (((Spanned) text).getSpanStart(this) == start) {
                Paint.Style style = p.getStyle();
                int oldColor = 0;
                oldColor = p.getColor();
                p.setColor(color);
                p.setStyle(Paint.Style.FILL);
                if (c.isHardwareAccelerated()) {
                    if (sBulletPath == null) {
                        sBulletPath = new Path();
                        // Bullet is slightly better to avoid aliasing artifacts on mdpi devices.
                        sBulletPath.addCircle(0.0f, 0.0f, radius, Path.Direction.CW);
                    }
                    c.save();
                    c.translate(x + dir * radius, (top + bottom) / 2.0f);
                    c.drawPath(sBulletPath, p);
                    c.restore();
                } else {
                    c.drawCircle(x + dir * radius, (top + bottom) / 2.0f, radius, p);
                }
                p.setColor(oldColor);
                p.setStyle(style);
            }
        }
    }

    class CustomLineHeightSpan extends CharacterStyle
            implements LineHeightSpan {

        private final int height;

        static final int ALIGN_CENTER = 2;

        static final int ALIGN_TOP = 3;

        final int mVerticalAlignment;

        CustomLineHeightSpan(int height, int verticalAlignment) {
            this.height = height;
            mVerticalAlignment = verticalAlignment;
        }

        @Override
        public void chooseHeight(final CharSequence text, final int start, final int end,
                                 final int spanstartv, final int v, final Paint.FontMetricsInt fm) {
            int need = height - (v + fm.descent - fm.ascent - spanstartv);
            //            if (need > 0) {
            if (mVerticalAlignment == ALIGN_TOP) {
                fm.descent += need;
            } else if (mVerticalAlignment == ALIGN_CENTER) {
                fm.descent += need / 2;
                fm.ascent -= need / 2;
            } else {
                fm.ascent -= need;
            }
            //            }
            need = height - (v + fm.bottom - fm.top - spanstartv);
            //            if (need > 0) {
            if (mVerticalAlignment == ALIGN_TOP) {
                fm.top += need;
            } else if (mVerticalAlignment == ALIGN_CENTER) {
                fm.bottom += need / 2;
                fm.top -= need / 2;
            } else {
                fm.top -= need;
            }
            //            }
        }

        @Override
        public void updateDrawState(final TextPaint tp) {

        }
    }

    @SuppressLint("ParcelCreator")
    class CustomTypefaceSpan extends TypefaceSpan {

        private final Typeface newType;

        private CustomTypefaceSpan(final Typeface type) {
            super("");
            newType = type;
        }

        @Override
        public void updateDrawState(final TextPaint textPaint) {
            apply(textPaint, newType);
        }

        @Override
        public void updateMeasureState(final TextPaint paint) {
            apply(paint, newType);
        }

        private void apply(final Paint paint, final Typeface tf) {
            int oldStyle;
            Typeface old = paint.getTypeface();
            if (old == null) {
                oldStyle = 0;
            } else {
                oldStyle = old.getStyle();
            }

            int fake = oldStyle & ~tf.getStyle();
            if ((fake & Typeface.BOLD) != 0) {
                paint.setFakeBoldText(true);
            }

            if ((fake & Typeface.ITALIC) != 0) {
                paint.setTextSkewX(-0.25f);
            }

            paint.getShader();

            paint.setTypeface(tf);
        }
    }

    class ShaderSpan extends CharacterStyle implements UpdateAppearance {
        private Shader mShader;

        private ShaderSpan(final Shader shader) {
            this.mShader = shader;
        }

        @Override
        public void updateDrawState(final TextPaint tp) {
            tp.setShader(mShader);
        }
    }
}
