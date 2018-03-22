package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;

/**
 *  rebase:
 *  	https://www.cnblogs.com/haodawang/p/5967219.html
 * 		http://www.runoob.com/java/java-regular-expressions.html
 * 	1.JAVA 
 *   字符转义  \加上特殊字母转换成另外一种意思 , 或者\加上[{之类的字符,表示匹配该字符
 * 	 转义字符	Invalid escape sequence (valid ones are  \b  \t  \n  \f  \r  \"  \'  \\ )
 *   \n: 换行符    \b: 边界符
 * */
public class Main {
	
	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("\\.");
		Matcher matcher = pattern.matcher("a.b");
		while(matcher.find()) {
			System.out.println(matcher.group());
		}
		
	}
}
