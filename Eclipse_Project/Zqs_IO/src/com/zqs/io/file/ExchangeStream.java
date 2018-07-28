package com.zqs.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * 转换流
 * @author Zoran
 *
 */
public class ExchangeStream {

	public static void main(String[] args) throws IOException {
		File file = new File("E:/tmp/test.txt");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		
		System.out.println("==============分割===============");
		//new API: OutputStreamWriter(OutputStream out, String charsetName) 
		//设置读取字节流的编码集 , 默认UTF-8 , 乱码: GBK
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
		BufferedWriter bWriter = new BufferedWriter(writer);
		bWriter.write("第一行");bWriter.newLine();  
		bWriter.write("第二行");bWriter.newLine();
		bWriter.write("第三行");bWriter.newLine(); 
		bWriter.write("第四行");bWriter.newLine(); // new API :newLine()
		bWriter.flush();
		bWriter.close();
		
		//new API: InputStreamReader(InputStream in, String charsetName)
		//设置读取字节流的编码集 , 默认UTF-8 , 乱码: GBK
		InputStreamReader reader2 = new InputStreamReader(new FileInputStream(file),"GBK"); 
		BufferedReader reader = new BufferedReader(reader2);
		String string = new String();
		while((string=reader.readLine()) != null) {
			System.out.println(string); //new API: readLine() 
		}
		reader.close();
		
	}
}
