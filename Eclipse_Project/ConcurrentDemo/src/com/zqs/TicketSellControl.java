package com.zqs;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Control
public class TicketSellControl {

	public List<Ticket> list = new ArrayList<>();
	
	public TicketSellControl() {
		for(int i=1; i<=100; i++) {
			Ticket ticket = new Ticket(i);
			list.add(ticket);
		}
	}
	
	/**
	 * synchronized 同步处理卖票的行为 
	 */
	public synchronized void sell() throws InterruptedException {   //synchronized, 在多线程下,同步线程操作
		if(list.size() > 0) {
			Thread.sleep(100); //虚拟网络延迟请求
			
			Ticket ticket = list.remove(0);
			System.out.println(Thread.currentThread().getName() + "卖出了票 ticket.id=" + ticket.id);
		}
	}
	
	/**
	 * synchronized (lock) {} 分离机制
	 * 锁定小锁,分离TicketSellControl锁
	 */
	Object lock = new Object();
	public void sell_optimization() throws InterruptedException {
		synchronized (lock) {
			if (list.size() > 0) {
				Thread.sleep(100);
				
				Ticket ticket = list.remove(0);
				System.out.println(Thread.currentThread().getName() + "卖出了票 ticket.id=" + ticket.id);
			}
		}
	}
	
	/**
	 * ReentrantLock 
	 * JDK 1.5锁机制实现 
	 */
	Lock reentLock = new ReentrantLock();
	public void sell_Use_ReentLock() throws InterruptedException {
		reentLock.lock();
		try {
			if (list.size() > 0) {
				Thread.sleep(100);
				
				Ticket ticket = list.remove(0);
				System.out.println(Thread.currentThread().getName() + "卖出了票 ticket.id=" + ticket.id);
			}
		} finally {
			reentLock.unlock();
		}
	}
	
}
