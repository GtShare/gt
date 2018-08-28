package currency;
/**
 * ���ഺ�������ѧʱ�����¿��ڼ䣬�����������������Ϊ�Լ�ϲ����Ů���������飬���Ŵ�Ҷ���������������� ��)���к����ȵ�Ů�������ſڣ�Ȼ���Ů�������������������һ��ͬ���㣬Ȼ��˴˽������Ҳ���Ǳ˴˽��������ݡ����ڣ�����ģ������龰
 * 
 * ������Ӻܼ򵥣�Ҳ����˵��Exchanger�Ļ���ʹ�á��������̶߳��������exchange������ͬ�����ʱ�������߳̾��ܽ����˴˵�����
 */

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo {
	
	private static Exchanger<String> exchanger = new Exchanger<>();
	
	public static void main(String[] args) {
		
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		service.execute(() ->{
			String toGirl;
			try {
				toGirl = exchanger.exchange("����ʵ������ܾ���");
				System.out.println("Ů��˵"+toGirl);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		service.execute(() ->{
			try {
				System.out.println("Ů�������Ĵӽ������߳���");
				String toBoy = exchanger.exchange("��Ҳ��ϲ����--");
				System.out.println("�к�˵"+toBoy);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
}
