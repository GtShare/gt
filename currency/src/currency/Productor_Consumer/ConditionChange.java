package currency.Productor_Consumer;

import java.util.ArrayList;
import java.util.List;

/*
 * wait的通知条件变化：如果线程在等待时接受到了通知，但是之后等待的条件发生了变化，并没有再次对等待条件进行判断，也会导致程序出现错误。
 * 
 * 在这个例子中一共开启了3个线程，Consumer1,Consumer2以及Productor。首先Consumer1调用了wait方法后，线程处于了WAITTING状态，
 * 并且将对象锁释放出来。因此，Consumer2能够获取对象锁，从而进入到同步代块中，当执行到wait方法时，同样的也会释放对象锁。
 * 因此，productor能够获取到对象锁，进入到同步代码块中，向list中插入数据后，通过notifyAll方法通知处于WAITING状态的Consumer1和Consumer2线程。
 * consumer1得到对象锁后，从wait方法出退出，删除了一个元素让List为空，方法执行结束，退出同步块，释放掉对象锁。
 * 这个时候Consumer2获取到对象锁后，从wait方法退出，继续往下执行，这个时候Consumer2再执行lock.remove(0);就会出错，
 * 因为List由于Consumer1删除一个元素之后已经为空了。
 * 
 */
public class ConditionChange {
	
	private static  List<String> lockObject = new ArrayList<>();
	
	public static void main(String[] args) {
	  Consumer consumer1 = new Consumer(lockObject);
	  Consumer consumer2 = new Consumer(lockObject);
	  Productor productor = new  Productor(lockObject);
	  consumer1.start();
	  consumer2.start();
	  productor.start();
	}
	
	static class Consumer extends Thread{
		
		private List<String> lock;
		
		public Consumer(List lock) {
			this.lock=lock;
		}
			
		@Override
		public void run() {
			
			synchronized (lock) {
				try {
					 //这里使用if的话，就会存在wait条件变化造成程序错误的问题
					if (lock.isEmpty()) {
						System.out.println(Thread.currentThread().getName()+"list 为空");
						System.out.println(Thread.currentThread().getName()+"调用wait方法");
						lock.wait();
						System.out.println(Thread.currentThread().getName()+"wait方法结束");
					}
					String element = lock.remove(0);
					System.out.println(Thread.currentThread().getName()+"取出第一个元素为："+element);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	static class Productor extends Thread{
		private List<String> lock;
		public Productor(List lock) {
			this.lock =lock;
		}
		
		@Override
		public void run() {
			synchronized (lock) {
				
				System.out.println(Thread.currentThread().getName()+"开始添加元素");
				
				lock.add(Thread.currentThread().getName());
				
				lock.notifyAll();
				
			}
			
		}
	}

}
