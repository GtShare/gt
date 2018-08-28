package currency;
/*
 * void interrupt() 中断该线程对象 （如果该线程被调用了Object wait/Object wait(long),
 * 				或者被用了Sleep（）、join（）/join(long)方法是会抛出interruptionException并且中断标志位将会被清除）
 * boolean isInterrupted() 测试该线程对象是否被中断,中断标志位不会被清除
 * static boolean interrupted() 测试当前线程是否被中断，中断标志位会被清除
 */
public class InterruptDemo {
	public static void main(String[] args) throws InterruptedException{
		//sleepThread 睡眠1000ms
		final Thread sleepThread = new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				super.run();
			}
			
		};
		
		//busyThread 一直执行死循环
		Thread busyThread = new Thread(){
			@Override
			public void run() {
				while(true);
			}
		};
		sleepThread.start();
		busyThread.start();
		sleepThread.interrupt();//被调用了sleep()方法，会清除中断位置
		busyThread.interrupt();
		while (sleepThread.isInterrupted());
		System.out.println("sleepThread isInterrupted:"+sleepThread.isInterrupted());
		System.out.println("busyThread isInterrupted:"+busyThread.isInterrupted());
		
		
		
	}
}
