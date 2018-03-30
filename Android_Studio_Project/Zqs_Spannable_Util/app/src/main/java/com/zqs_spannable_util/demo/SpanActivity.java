package com.zqs_spannable_util.demo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import com.zqs_spannable_util.BaseBackActivity;
import com.zqs_spannable_util.R;

/**
 * Created by zqs on 2018/3/30.
 *
 * Refer :
 *     https://github.com/Blankj/AndroidUtilCode/blob/master/app/src/main/java/com/blankj/androidutilcode/feature/core/span/SpanActivity.java
 */

public class SpanActivity extends BaseBackActivity {
    SpanUtils              mSpanUtils;
    SpannableStringBuilder animSsb;

    int           lineHeight;
    float         textSize;
    ValueAnimator valueAnimator;
    Shader mShader;
    float         mShaderWidth;
    Matrix matrix;

    BlurMaskFilterSpan mBlurMaskFilterSpan;

    ShadowSpan mShadowSpan;

    ForegroundAlphaColorSpan mForegroundAlphaColorSpan;

    ForegroundAlphaColorSpanGroup mForegroundAlphaColorSpanGroup;

    String mPrinterString;


    float    density;
    TextView tvAboutSpan;
    TextView tvAboutAnimRainbow;

    public static void start(Context context) {
        Intent starter = new Intent(context, SpanActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void initData(Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_span;
    }

    @Override
    public void initView(Bundle saveInstancestate, View view) {
        getToolBar().setTitle("SpanUtils Demo");

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(SpanActivity.this,"事件被点击了", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };

        tvAboutSpan = findViewById(R.id.tv_about_span);
        tvAboutAnimRainbow = findViewById(R.id.tv_about_anim_span);

        // 响应点击事件的话必须设置以下属性
        tvAboutSpan.setMovementMethod(LinkMovementMethod.getInstance());
        // 去掉点击事件后的高亮
        tvAboutSpan.setHighlightColor(ContextCompat.getColor(this, android.R.color.transparent));
        lineHeight = tvAboutSpan.getLineHeight();
        textSize = tvAboutSpan.getTextSize();
        density = getResources().getDisplayMetrics().density;

        //tvAboutSpan.setText("Hello Word");

        tvAboutSpan.setText(new SpanUtils()
                //.appendLine("SpanUtils").setBackgroundColor(Color.LTGRAY).setBold().setForegroundColor(Color.YELLOW).setAlign(Layout.Alignment.ALIGN_CENTER)
                .appendLine("前景色").setForegroundColor(Color.GREEN)
                .appendLine("背景色").setBackgroundColor(Color.LTGRAY)
                .appendLine("行高顶部对齐").setLineHeight(2 * lineHeight, SpanUtils.ALIGN_TOP).setBackgroundColor(Color.GREEN)
                .appendLine("行高居中对齐").setLineHeight(2 * lineHeight, SpanUtils.ALIGN_CENTER).setBackgroundColor(Color.LTGRAY)
                .appendLine("行高底部对齐").setLineHeight(2 * lineHeight, SpanUtils.ALIGN_BOTTOM).setBackgroundColor(Color.GREEN)
                .appendLine("测试段落缩，首行缩进两字，其他行不缩进").setLeadingMargin((int) textSize * 2, 10).setBackgroundColor(Color.GREEN)
                .appendLine("测试引用，后面的字是为了凑到两行的效果").setQuoteColor(Color.GREEN, 10, 10).setBackgroundColor(Color.LTGRAY)
                .appendLine("测试列表项，后面的字是为了凑到两行的效果").setBullet(Color.GREEN, 20, 10).setBackgroundColor(Color.LTGRAY).setBackgroundColor(Color.GREEN)
                .appendLine("32dp 字体").setFontSize(32, true)
                .create());
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onWidgetClick(View view) {

    }


}
