package currency;
/*
 * java关键字synchronized就具有使每个线程依次排队操作共享变量的功能。很显然，这种同步机制效率很低，但synchronized是其他并发容器实现的基础
 * 用Syncnized就可能保证内存可见性，保证每个线程都是操作的最新值 如下：
 */
public class SynchronizedDemo implements Runnable{
	private static int count = 0;
	public static void main(String[] args) {
		for(int i=0; i<10;i++){
			Thread thread = new Thread(new SynchronizedDemo());
			thread.start();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("count:"+count);
	}
	
	@Override
	public void run() {
		synchronized (SynchronizedDemo.class) {
			for(int i=0;i<1000000;i++)
				count++;
		}
	}
	
}
