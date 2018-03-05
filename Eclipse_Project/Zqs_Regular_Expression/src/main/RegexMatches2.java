package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 	http://www.runoob.com/java/java-regular-expressions.html
 * 
 * 	1. appendReplacement()
 * 		https://www.cnblogs.com/Hdaydayup/p/7071363.html
 *  2. appendTail
 *  	从源码可以看出 是添加的结束符
 */
public class RegexMatches2{
	
   private static String REGEX = "a*b";
   private static String INPUT = "aabfooaabfooabfoob";
   private static String REPLACE = "-";
   public static void main(String[] args) {
      Pattern p = Pattern.compile(REGEX);
      // 获取 matcher 对象
      Matcher m = p.matcher(INPUT);
      StringBuffer sb = new StringBuffer();
      while(m.find()){
         m.appendReplacement(sb,REPLACE);
      }
      m.appendTail(sb);
      System.out.println(sb.toString());
   }
}