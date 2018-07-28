package com.zqs.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 1. 客服端 解决-> 发送, 接受线程彼此独立线程
 *
 */
public class Client {
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("请输入用户名称:");
		String name = new Scanner(System.in).next();
		if (name ==null && name.length() == 0) {
			return;
		}
		
		
		Socket socket = new Socket("localhost", 8888);
		//封装发送线程一条路
		new Thread(new Sender(socket,name)).start();
		//封装接受线程一条路
		new Thread(new Receiver(socket)).start();
	}
}
