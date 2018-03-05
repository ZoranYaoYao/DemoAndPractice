package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 	http://www.runoob.com/java/java-regular-expressions.html
 * 
 * 1.Matcher lookingAt() API
 *     String 全匹配 Regex表达式,并且必须从String头开始
 * 2.Matcher mathces()
 * 	   String 全匹配 Regex表达式,必须一模一样.
 * */
public class RegexMatches
{
    private static final String REGEX = "foo";
    private static final String INPUT = "fooooooooooooooooo";
    private static final String INPUT2 = "ooooofoooooooooooo";
    private static final String INPUT3 = "foo";
    private static Pattern pattern;
    private static Matcher matcher;
    private static Matcher matcher2;
    private static Matcher matcher3;
 
    public static void main( String args[] ){
       pattern = Pattern.compile(REGEX);
       matcher = pattern.matcher(INPUT);
       matcher2 = pattern.matcher(INPUT2);
       matcher3 = pattern.matcher(INPUT3);
       
       System.out.println("Current REGEX is: "+REGEX);
       System.out.println("Current INPUT is: "+INPUT);
       System.out.println("Current INPUT2 is: "+INPUT2);
 
 
       System.out.println("lookingAt(): "+matcher.lookingAt());
       System.out.println("matches(): "+matcher.matches());
       //matches 全匹配
       System.out.println("matcher3 matches():" + matcher3.matches());
       System.out.println("lookingAt(): "+matcher2.lookingAt());
   }
}