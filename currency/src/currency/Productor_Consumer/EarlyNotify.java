package currency.Productor_Consumer;
/**
 * notify早期通知
 * 
 * notify 通知的遗漏很容易理解，即 threadA 还没开始 wait 的时候，threadB 已经 notify 了，这样，threadB 
 * 通知是没有任何响应的，当 threadB 退出 synchronized 代码块后，threadA 再开始 wait，
 * 便会一直阻塞等待，直到被别的线程打断。比如在下面的示例代码中，就模拟出notify早期通知带来的问题：
 * 
 * 示例中开启了**两个线程，一个是WaitThread，另一个是NotifyThread。NotifyThread会先启动，先调用notify方法。
 * 然后WaitThread线程才启动，调用wait方法，但是由于通知过了，wait方法就无法再获取到相应的通知，
 * 因此WaitThread会一直在wait方法出阻塞，这种现象就是通知过早的现象
 * @author Guo
 *
 */
public class EarlyNotify {
	
	private static String lockObject="";
	
	public static void main(String[] args) {
		WaitThread waitThread  = new WaitThread(lockObject);
		NotifyThread notifyThread  = new NotifyThread(lockObject);
		notifyThread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		waitThread.start();
	}
	
	static class WaitThread extends Thread{
		
		private String lock;
		
		public WaitThread(String lock){
			
			this.lock=lock;
		
		}
		
		@Override
		public void run() {
			
			synchronized (lock) {
				try {
					System.out.println(Thread.currentThread().getName()+"进去代码块 ");
					System.out.println(Thread.currentThread().getName()+"开始wait ");
					lock.wait();
					System.out.println(Thread.currentThread().getName()+"结束wait ");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
static class NotifyThread extends Thread{
		
		private String lock;
		
		public NotifyThread(String lock){
			
			this.lock=lock;
		
		}
		
		@Override
		public void run() {
			
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName()+"进去代码块 ");
				System.out.println(Thread.currentThread().getName()+"开始wait ");
				lock.notify();
				System.out.println(Thread.currentThread().getName()+"结束wait ");
			}
			
		}
		
	}
	
	
}
