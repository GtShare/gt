package currency;
/*
 * void interrupt() �жϸ��̶߳��� ��������̱߳�������Object wait/Object wait(long),
 * 				���߱�����Sleep������join����/join(long)�����ǻ��׳�interruptionException�����жϱ�־λ���ᱻ�����
 * boolean isInterrupted() ���Ը��̶߳����Ƿ��ж�,�жϱ�־λ���ᱻ���
 * static boolean interrupted() ���Ե�ǰ�߳��Ƿ��жϣ��жϱ�־λ�ᱻ���
 */
public class InterruptDemo {
	public static void main(String[] args) throws InterruptedException{
		//sleepThread ˯��1000ms
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
		
		//busyThread һֱִ����ѭ��
		Thread busyThread = new Thread(){
			@Override
			public void run() {
				while(true);
			}
		};
		sleepThread.start();
		busyThread.start();
		sleepThread.interrupt();//��������sleep()������������ж�λ��
		busyThread.interrupt();
		while (sleepThread.isInterrupted());
		System.out.println("sleepThread isInterrupted:"+sleepThread.isInterrupted());
		System.out.println("busyThread isInterrupted:"+busyThread.isInterrupted());
		
		
		
	}
}
