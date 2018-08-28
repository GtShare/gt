package currency;
/*
 * volatile ��֤���ݵĿɼ��� ����volatile���εı����ܹ���֤ÿ���߳��ܹ���ȡ�ñ���������ֵ���Ӷ���������������������
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
