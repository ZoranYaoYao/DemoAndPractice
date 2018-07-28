package com.zqs.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Sender implements Runnable{
	private BufferedReader consle;
	private DataOutputStream dos;
	private Socket socket;
	private boolean isRunning = false;  //线程标识符, Socket异常情况下用于控制线程的停止,

	public Sender() {
		consle = new BufferedReader(new InputStreamReader(System.in));
		isRunning = true;
	}



	public Sender(Socket socket,String name) {
		this();
		this.socket = socket;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			send(name);
		} catch (IOException e) {
			isRunning= false;
			CloseUtil.closeAll(consle, dos);
		}
	}

	String getStringFromConsle() {
		String str;
		try {
			str = consle.readLine();
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	private void send(String content) {
		try {
			dos.writeUTF(content);
			dos.flush();
		} catch (IOException e) {
			isRunning = false;
			CloseUtil.closeAll(consle, dos);
		}
	}


	@Override
	public void run() {
		while (isRunning) {
			send(getStringFromConsle());
		}
	}

}
