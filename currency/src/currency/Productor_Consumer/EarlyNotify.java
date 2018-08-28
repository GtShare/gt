package currency.Productor_Consumer;
/**
 * notify����֪ͨ
 * 
 * notify ֪ͨ����©��������⣬�� threadA ��û��ʼ wait ��ʱ��threadB �Ѿ� notify �ˣ�������threadB 
 * ֪ͨ��û���κ���Ӧ�ģ��� threadB �˳� synchronized ������threadA �ٿ�ʼ wait��
 * ���һֱ�����ȴ���ֱ��������̴߳�ϡ������������ʾ�������У���ģ���notify����֪ͨ���������⣺
 * 
 * ʾ���п�����**�����̣߳�һ����WaitThread����һ����NotifyThread��NotifyThread�����������ȵ���notify������
 * Ȼ��WaitThread�̲߳�����������wait��������������֪ͨ���ˣ�wait�������޷��ٻ�ȡ����Ӧ��֪ͨ��
 * ���WaitThread��һֱ��wait�����������������������֪ͨ���������
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
					System.out.println(Thread.currentThread().getName()+"��ȥ����� ");
					System.out.println(Thread.currentThread().getName()+"��ʼwait ");
					lock.wait();
					System.out.println(Thread.currentThread().getName()+"����wait ");
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
				System.out.println(Thread.currentThread().getName()+"��ȥ����� ");
				System.out.println(Thread.currentThread().getName()+"��ʼwait ");
				lock.notify();
				System.out.println(Thread.currentThread().getName()+"����wait ");
			}
			
		}
		
	}
	
	
}
