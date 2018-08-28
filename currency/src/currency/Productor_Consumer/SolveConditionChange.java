package currency.Productor_Consumer;

import java.util.ArrayList;
import java.util.List;

/*
 * wait的通知条件变化：如果线程在等待时接受到了通知，但是之后等待的条件发生了变化，并没有再次对等待条件进行判断，也会导致程序出现错误。
 * 
 * 解决方案：
 * 通过上面的分析，可以看出Consumer2报异常是因为线程从wait方法退出之后没有再次对wait条件进行判断，因此，此时的wait条件已经发生了变化。
 * 解决办法就是，在wait退出之后再对条件进行判断即可。
 * 
 * 
 * 上面的代码与之前的代码仅仅只是将 wait 外围的 if 语句改为 while 循环即可，这样当 list 为空时，线程便会继续等待，而不会继续去执行删除 list 中元素的代码。
 *
 * 
 */
public class SolveConditionChange {
	
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
					while (lock.isEmpty()) {
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
