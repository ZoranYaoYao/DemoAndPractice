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
	 * synchronized ͬ��������Ʊ����Ϊ 
	 */
	public synchronized void sell() throws InterruptedException {   //synchronized, �ڶ��߳���,ͬ���̲߳���
		if(list.size() > 0) {
			Thread.sleep(100); //���������ӳ�����
			
			Ticket ticket = list.remove(0);
			System.out.println(Thread.currentThread().getName() + "������Ʊ ticket.id=" + ticket.id);
		}
	}
	
	/**
	 * synchronized (lock) {} �������
	 * ����С��,����TicketSellControl��
	 */
	Object lock = new Object();
	public void sell_optimization() throws InterruptedException {
		synchronized (lock) {
			if (list.size() > 0) {
				Thread.sleep(100);
				
				Ticket ticket = list.remove(0);
				System.out.println(Thread.currentThread().getName() + "������Ʊ ticket.id=" + ticket.id);
			}
		}
	}
	
	/**
	 * ReentrantLock 
	 * JDK 1.5������ʵ�� 
	 */
	Lock reentLock = new ReentrantLock();
	public void sell_Use_ReentLock() throws InterruptedException {
		reentLock.lock();
		try {
			if (list.size() > 0) {
				Thread.sleep(100);
				
				Ticket ticket = list.remove(0);
				System.out.println(Thread.currentThread().getName() + "������Ʊ ticket.id=" + ticket.id);
			}
		} finally {
			reentLock.unlock();
		}
	}
	
}
