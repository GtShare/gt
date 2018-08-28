package currency.Productor_Consumer;
/**
 * 解决notify早期通知
 * 解决方法，一般是添加一个状态标志，让waitThread调用wait方法前先判断状态是否已经改变了没，如果通知早已发出的话，WaitThread就不再去wait。
 * 
 * 这段代码只是增加了一个isWait状态变量，NotifyThread调用notify方法后会对状态变量进行更新，在WaitThread中调用wait方法之前会先对状态变量进行判断，在该示例中，调用notify后将状态变量isWait改变为false，
 * 因此，在WaitThread中while对isWait判断后就不会执行wait方法，从而避免了Notify过早通知造成遗漏的情况。
 * @author Guo
 *
 */
public class SolveEarlyNotify {
	
	private static String lockObject="";
	private static boolean  isWait= true;
	
	
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
					
				while (isWait) {
						System.out.println(Thread.currentThread().getName()+"进去代码块 ");
						System.out.println(Thread.currentThread().getName()+"开始wait ");
						
							lock.wait();
						
						System.out.println(Thread.currentThread().getName()+"结束wait ");
					}
				
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
				isWait = false;
				System.out.println(Thread.currentThread().getName()+"结束wait ");
			}
			
		}
		
	}
	
	
}
