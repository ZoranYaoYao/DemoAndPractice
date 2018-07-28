package com.zqs.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;




/**
 * 当客户端关闭后,服务器dis.readUTF() -> 报异常 java.net.SocketException: Connection reset
 * 问题一:
 * https://blog.csdn.net/u013766398/article/details/51381852
 * 
 * 问题二: 每个socket的发送,接受在哪个线程里执行 ? 
 * System.out.println(Thread.currentThread().getName() + " excute sendOthers()");
 * 
 * 问题三: 服务器的管道
 *         	 接受管道通信 : 一定是在子线程的阻塞线程中!
 *           发送管道通信: 不一定时在子线程中完成!
 *           
 * 1.多客户端
 * 2.进入聊天室,提示语 : 
 * 		对自己: XXX->欢迎您进入聊天室
 * 		对其他: XXX加入了聊天室
 * 3.支持群聊
 * 4.支持私聊 @XXX:
 * 
 * 思路:
 *    服务器就是一个中转站,转发消息的
 *    
 * 2. 服务器端 解决 多客户端连接
 * 		 建立连接的每个sokect都有一个Thread读取,写入.
 * 				(1)读取信息 : 在自己的run() 线程体运行
 * 				(2)转发消息 : 在别人的MyChannel的run()线程体执行转发操作 [注意] 写入过程也是在子线程中实现的
 */
public class Server {
	
	private List<Mychannel> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start();


	}
	
	public void start() {
		ServerSocket server;
		try {
			server = new ServerSocket(8888);
			while(true) {
				Socket client = server.accept();  
				Mychannel channel = new Mychannel(client);
				new Thread(channel).start();
				list.add(channel);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	class Mychannel implements Runnable {
		private Socket client;
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRunning = true;
		private String name; //client端名称
		
		public Mychannel(Socket client) {
			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
				String name = dis.readUTF();
				this.name = name;
				send(name + "->欢迎您进入聊天室");
				sendOthers(name+"进入了聊天室", true);
			} catch (IOException e) {
				isRunning =false;
				e.printStackTrace();
			}
		}

		/**
		 * 做消息转发处理
		 */
		@Override
		public void run() {
			while(isRunning) {
				sendOthers(receive(),false);
			}
		}


		/**
		 * 发送给其他客户端
		 */
		private void sendOthers(String msg, boolean sys) {
			System.out.println(Thread.currentThread().getName() + " excute sendOthers()");
			//是否为私聊 约定
			if (msg.startsWith("@") && msg.indexOf(':') != -1) {
				String name = msg.substring(1,msg.indexOf(":"));
				String content = msg.substring(msg.indexOf(":")+1);
				for(Mychannel other:list){
					if(other.name.equals(name)){
						other.send(this.name+"对您悄悄地说:"+content);
					}
				}
			} else {
				//遍历容器
				for(Mychannel other: list) {
					if(other == this) {
						continue;
					}
					if(sys) {//系统信息
						other.send("系统 消息:" + msg);
					}else {
						//发送其他客户端
						other.send(this.name + "对所有人说:" + msg);
					}
				}
			}

		}
		
		/**
		 * 读取数据
		 * @return
		 */
		private String receive(){
			System.out.println(Thread.currentThread().getName() + " excute receive()");
			String msg ="";
			try {
				msg=dis.readUTF(); //阻塞式获取对象,断开则异常
			} catch (IOException e) {
				//e.printStackTrace();
				CloseUtil.closeAll(dis);
				isRunning =false;
				list.remove(this); //移除自身
			}
			return msg;
		}



		private void send(String content) {
				try {
					System.out.println(Thread.currentThread().getName() + " excute send()");
					dos.writeUTF(content);
					dos.flush();
					//dos.close();  长时间连接时, 不要手动关闭连接
				} catch (IOException e) {
					CloseUtil.closeAll(dis,dos);
					isRunning =false;
					list.remove(this);
					e.printStackTrace();
				}

		}
		
	}
	
}
