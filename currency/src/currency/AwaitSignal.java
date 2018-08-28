package currency;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitSignal {
	private  static ReentrantLock lock = new ReentrantLock();
	private  static Condition condition = lock.newCondition();
	private static volatile boolean flag = false;
	
	public static void main(String[] args) {
		Thread waiter = new Thread(new waiter());
		waiter.start();
		Thread sinaler = new Thread(new sinaler());
		sinaler.start();
	}
	
	static class waiter implements Runnable{

		@Override
		public void run() {
			lock.lock();
			try {
				while (!flag) {
					System.out.println(Thread.currentThread().getName() + "��ǰ����������ȴ�");
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				} 
				System.out.println(Thread.currentThread().getName() + "���յ�֪ͨ��������");
			} finally {
				lock.unlock();
			}
		}
		
	}
	
	static class sinaler implements Runnable{

		@Override
		public void run() {
			lock.lock();
			try {
				flag=true;
				condition.signalAll();
			} finally {
				lock.unlock();
			}
			
		}
		
	}

}
