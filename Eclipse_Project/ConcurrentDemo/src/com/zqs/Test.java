package com.zqs;

/**
 * 3������ͬʱ��Ʊ, ��Ʊ��ͬ������
 * 
 * ���̶߳Թ�����Դ��ͬ������
 * 	(1) synchronizated
 *  (2) lock
 */
public class Test {

	public static void main(String[] args) {
		TicketSellControl sellControl = new TicketSellControl();
		SellRunnable sellRunnable = new SellRunnable(sellControl); 
		Thread thread1 = new Thread(sellRunnable,"����1");
		Thread thread2 = new Thread(sellRunnable,"����2");
		Thread thread3 = new Thread(sellRunnable,"����3");
		
		thread1.setPriority(1);thread1.start(); 
		thread2.setPriority(9);thread2.start(); 
		thread3.setPriority(9);thread3.start();
	}
}

class SellRunnable implements Runnable {
	TicketSellControl sellControl;

	public SellRunnable(TicketSellControl sellControl) {
		this.sellControl = sellControl;
	}

	volatile boolean flag = true; 
	@Override
	public void run() {

		while(flag || sellControl.list.size() > 0) { //volatile,����ʱ,ͣ��runnble
			try {
//				sellControl.sell();
//				sellControl.sell_optimization();
				sellControl.sell_Use_ReentLock();
				
				if(sellControl.list.size() <= 0) return;
			} catch (InterruptedException e) {
				e.printStackTrace();
				flag = false;
			}
		}
	}

}
