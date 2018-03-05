package com.example.zoran.text_style;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    static List<BaseStringEntity> list = new ArrayList<BaseStringEntity>();
    //        {add("你好，[[定位准确吗]]？我准备出发了。");
    //        add("马上就到了，[[请稍等一会]]，谢谢。");
    //        add("好的，[[我知道了]]");}
    //    };
    static {
        BaseStringEntity one = new BaseStringEntity();
        one.rich_string = "好的，[[我知道了]]";
        BaseStringEntity two = new BaseStringEntity();
        two.rich_string = "马上就到了，[[请稍等一会]]，谢谢。";
        BaseStringEntity three = new BaseStringEntity();
        three.rich_string = "好的，[[我知道了。";
        BaseStringEntity four = new BaseStringEntity();
        four.rich_string = "好的，我知道了]]。";
        BaseStringEntity five = new BaseStringEntity();
        five.rich_string = "好的，[[我知道了]]";
        list.add(null);list.add(one);list.add(two);list.add(three);list.add(four);list.add(five);
    }


    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.linearlayout);
        fillMessage();
        //getRichString2();
    }

    /**
     * 富文本（使用"[ [ 高亮string ] ]"格式，例如：“你好，[[定位准确吗]]？我准备出发了。”）
     * */
    private List<SpannableStringBuilder> getRichString() {
        List<SpannableStringBuilder> exchangeList = new ArrayList<>();
        String[] forwardBrackets;String[] afterBrackets;String highColorStr = null;

        for (BaseStringEntity item:list) {
            if (item == null || item.rich_string == null || item.rich_string.isEmpty()) {
                continue;
            }

            SpannableStringBuilder ssb = new SpannableStringBuilder();
            forwardBrackets = item.rich_string.split("\\[\\[");
            if(forwardBrackets != null && forwardBrackets.length > 1) {
                afterBrackets = forwardBrackets[1].split("]]");
                if(afterBrackets.length >= 1 ){
                    ssb.append(forwardBrackets[0]);
                    highColorStr = afterBrackets[0];
                    ssb.append(highColorStr);
                    if(afterBrackets.length > 1) {
                        ssb.append(afterBrackets[1]);
                    }

                    if(!TextUtils.isEmpty(highColorStr)){
                        ssb.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_ffb843)),
                                forwardBrackets[0].length(), forwardBrackets[0].length()+highColorStr.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                        exchangeList.add(ssb);
                    }
                    continue;
                }
            }
            ssb.append(item.rich_string);
            exchangeList.add(ssb);
        }

        return exchangeList;
    }

    private void fillMessage(){
        SpannableStringBuilder ssb = getRichString();
        if(ssb != null){
            TextView tvMessage = new TextView(this);
            tvMessage.setText(ssb);
            tvMessage.setPadding(0, DisplayUtil.dip2px(this, 17), 0, DisplayUtil.dip2px(this, 15));
            tvMessage.setTextSize(18);
            tvMessage.setTextColor(getResources().getColor(R.color.color_333333));
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.gravity = Gravity.CENTER_HORIZONTAL;
            tvMessage.setLayoutParams(lp);
            linearLayout.addView(tvMessage);
        }
    }

    private SpannableStringBuilder getRichString() {
        String str = "你好，[[定位准确吗]]？我准备[[出发]]了。";
        String rexg = "\\[\\[([\\s\\S]*?)\\]\\]";  //+
        Pattern pattern = Pattern.compile(rexg);
        Matcher matcher =pattern.matcher(str);
        List<String> HighColorStrList = new ArrayList<>();
        while (matcher.find()) {
            String HighColorStr = ((String) matcher.group())
                    .replaceAll("\\[\\[","").replaceAll("\\]\\]","");
            HighColorStrList.add(HighColorStr);
        }

        String normalStr = str.replaceAll("\\[\\[","").replaceAll("\\]\\]","");
        SpannableStringBuilder ssb = new SpannableStringBuilder(normalStr);
        for (String item:HighColorStrList) {
            if(ssb.toString().contains(item)) {
                ssb.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_ffb843)),
                        normalStr.indexOf(item), normalStr.indexOf(item) + item.length()
                        , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }

        return  ssb;
    }

    private SpannableStringBuilder getRichString2() {
        String str = "你好，[[定位准确吗]]？我准备[[出发]]了。";
        StringBuilder stringBuilder = new StringBuilder();
        String rexg = "\\[\\[([\\s\\S]*?)\\]\\]";  //+
        Pattern pattern = Pattern.compile(rexg);
        Matcher matcher =pattern.matcher(str);
        List<HighColorIndex> indexList = new ArrayList<>();
        List<String> HighColorStrList = new ArrayList<>();
        while (matcher.find()) {
            HighColorIndex highColorIndex = new HighColorIndex(matcher.start(),matcher.end());
            indexList.add(highColorIndex);

            String HighColorStr = matcher.group().replace("\\[\\[","").replace("\\]\\]","");
        }

        str = str.replaceAll("\\[\\[","");
        str = str.replaceAll("\\]\\]","");
        SpannableStringBuilder ssb = new SpannableStringBuilder(str);
        if(indexList.size() > 0) {
            for(int i = 0; i < indexList.size(); i++) {
                ssb.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.color_ffb843)),
                        (indexList.get(i).startIndex - 2*(i+1)), (indexList.get(i).endIndex - 2*(i+1))
                        , Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            }
        }
        return  ssb;
    }

    // 格式: [[定位准确吗]]的位置
    class HighColorIndex {
        public int startIndex;
        public int endIndex;

        HighColorIndex(int start,int end) {
            startIndex = start;
            endIndex = end;
        }
    }



}
