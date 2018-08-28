package currency;

import java.util.concurrent.locks.LockSupport;

/**
 * lockSupport 工具类
 * 
 * thread线程调用LockSupport.park()致使thread阻塞，
 * 当mian线程睡眠3秒结束后通过LockSupport.unpark(thread)方法唤醒thread线程,thread线程被唤醒执行后续操作
 * @author Guo
 *
 */
public class LockSupportDemo {
	public static void main(String[] args) {
		Thread thread = new Thread(()->{
			LockSupport.park();
			System.out.println(Thread.currentThread().getName()+"线程被唤醒");
		});
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		LockSupport.unpark(thread);
	}
}
