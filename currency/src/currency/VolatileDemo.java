package currency;
/*
 * volatile 保证数据的可见性 。被volatile修饰的变量能够保证每个线程能够获取该变量的最新值，从而避免出现数据脏读的现象。
 */
public class VolatileDemo {
	private static volatile boolean isOver= false;
	
	public static void main(String[] args) {
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (!isOver) ;
			}
		});
		thread.start();
		
		try {
			Thread.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		isOver=true;
	}
}
