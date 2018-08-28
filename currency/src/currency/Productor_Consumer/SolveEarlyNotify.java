package currency.Productor_Consumer;
/**
 * ���notify����֪ͨ
 * ���������һ�������һ��״̬��־����waitThread����wait����ǰ���ж�״̬�Ƿ��Ѿ��ı���û�����֪ͨ���ѷ����Ļ���WaitThread�Ͳ���ȥwait��
 * 
 * ��δ���ֻ��������һ��isWait״̬������NotifyThread����notify��������״̬�������и��£���WaitThread�е���wait����֮ǰ���ȶ�״̬���������жϣ��ڸ�ʾ���У�����notify��״̬����isWait�ı�Ϊfalse��
 * ��ˣ���WaitThread��while��isWait�жϺ�Ͳ���ִ��wait�������Ӷ�������Notify����֪ͨ�����©�������
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
						System.out.println(Thread.currentThread().getName()+"��ȥ����� ");
						System.out.println(Thread.currentThread().getName()+"��ʼwait ");
						
							lock.wait();
						
						System.out.println(Thread.currentThread().getName()+"����wait ");
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
				System.out.println(Thread.currentThread().getName()+"��ȥ����� ");
				System.out.println(Thread.currentThread().getName()+"��ʼwait ");
				lock.notify();
				isWait = false;
				System.out.println(Thread.currentThread().getName()+"����wait ");
			}
			
		}
		
	}
	
	
}
