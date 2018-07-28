package com.zqs.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Receiver implements Runnable{

	private Socket socket;
	private DataInputStream is;
	private boolean isRunning;
	
	public Receiver(Socket socket) {
		this.socket = socket;
		try {
			is = new DataInputStream(socket.getInputStream());
			isRunning = true;
		} catch (IOException e) {
			CloseUtil.closeAll(is);
		}
	}
	
	private String receive() {
		try {
			String str =  is.readUTF();
			return str;
		} catch (IOException e) {
			isRunning = false;
			CloseUtil.closeAll(is);
		}
		return "";
		
	}

	@Override
	public void run() {
		while(isRunning) {
			System.out.println(receive());
		}
	}
	
}
