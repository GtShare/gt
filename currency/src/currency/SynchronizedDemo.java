package currency;
/*
 * java�ؼ���synchronized�;���ʹÿ���߳������ŶӲ�����������Ĺ��ܡ�����Ȼ������ͬ������Ч�ʺܵͣ���synchronized��������������ʵ�ֵĻ���
 * ��Syncnized�Ϳ��ܱ�֤�ڴ�ɼ��ԣ���֤ÿ���̶߳��ǲ���������ֵ ���£�
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
