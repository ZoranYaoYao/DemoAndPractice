package main;

import java.io.UnsupportedEncodingException;

/**
 * java 8种基本数据结构 占用字节大小
 * 	 	http://blog.csdn.net/witsmakemen/article/details/8974200/
 * java 8种基本数据结构 默认值
 * 		https://www.cnblogs.com/yjh123/p/5694341.html
 * Java中字符编码和字符串所占字节数
 * 		http://blog.csdn.net/xiaoyu714543065/article/details/7380191
 * 
 * */
public class Test {
	//默认值
	public static byte byteData = 0;  //defalut 1个字节
	public static short shortData = 0; //defalut 2个字节
	public static int intData = 0; //defalut 4个字节
	public static long longData = 0L; //defalut 8个字节
	public static boolean booleanData = false; //defalut 1个字节
	public static char charData = '\u0000'; //defalut 2个字节
	public static float floatData = 0.0f; //defalut 4个字节
	public static double doubleData = 0.0d; //defalut 8个字节
	
	public static void main(String[] args) throws UnsupportedEncodingException {
//		//1.基本数据结构 是没有方法的
//		char a = '萨';
//		System.out.println("char " + a.);
		
//		//2.不同编码集,字符串字节所占的字节位数
//		String str = "中国abc";
//		//获取的是平台的编码集
//		String csn = Charset.defaultCharset().name();
//		System.out.println("默认编码字符集" +csn);
//		byte[] byte2 = str.getBytes(); // [-28, -72, -83, -27, -101, -67, 97, 98, 99]
//		System.out.println("String 字节长度= "+ str.getBytes().length);
//		System.out.println("---------");
//		
//		byte[] byte3 = str.getBytes("GBK"); //[-42, -48, -71, -6, 97, 98, 99]
//		System.out.println("String GBK 字节长度= "+ str.getBytes("GBK").length);
		
		byte a = -100; byte b = 127;
		byte c = (byte) (a - b);
		System.out.println(c);
	}
}
