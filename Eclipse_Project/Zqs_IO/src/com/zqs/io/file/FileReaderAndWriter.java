package com.zqs.io.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 标准的文件读写操作
 * 
 * 1.文件追加写入 , 只能是针对节点流设置的
 * 	比如new FileWriter(file,true)
 *  比如new FileOutputStream(file,true);
 * @author Zoran
 *
 */
public class FileReaderAndWriter {

	public static void main(String[] args) throws IOException {
		File file = new File("E:/tmp/test.txt");
		if (!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		
		writeToFile(file);

		BufferedReader reader = new BufferedReader(new FileReader(file));
		String string = new String();
		while((string=reader.readLine()) != null) {
			System.out.println(string); //new API: readLine() 
		}
		reader.close();
		
	}

	private static void writeToFile(File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file,true)); //FileWriter 追加形式写入,
		writer.write("第一行");writer.newLine();  
		writer.write("第二行");writer.newLine();
		writer.write("第三行");writer.newLine(); 
		writer.write("第四行");writer.newLine(); // new API :newLine()
		
		writer.flush();
		writer.close();
	}
	
}
