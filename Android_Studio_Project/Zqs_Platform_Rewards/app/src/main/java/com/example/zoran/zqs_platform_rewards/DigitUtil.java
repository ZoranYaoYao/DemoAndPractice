package com.example.zoran.zqs_platform_rewards;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zqs on 2018/1/29.
 * https://www.cnblogs.com/renxiaoren/p/6780707.html
 */

public class DigitUtil {

    // 判断一个字符串是否含有数字
    public static boolean ContainDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }


    //截取数字 【读取字符串中第一个连续的字符串，不包含后面不连续的数字】
    public static String getNumbers(String content) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(0);
        }
        return "";
    }

    //获取字符串第一个数字所在字符串的索引
    public static int getNumbersStartIndex(String content,String numberStr) {
        return content.indexOf(numberStr);
    }
}
