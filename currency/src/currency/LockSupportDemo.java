package currency;

import java.util.concurrent.locks.LockSupport;

/**
 * lockSupport ������
 * 
 * thread�̵߳���LockSupport.park()��ʹthread������
 * ��mian�߳�˯��3�������ͨ��LockSupport.unpark(thread)��������thread�߳�,thread�̱߳�����ִ�к�������
 * @author Guo
 *
 */
public class LockSupportDemo {
	public static void main(String[] args) {
		Thread thread = new Thread(()->{
			LockSupport.park();
			System.out.println(Thread.currentThread().getName()+"�̱߳�����");
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
