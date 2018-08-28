package currency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore�ľ���ʹ�á�Semaphore������������Դ�Ĳ������ʿ������൱���ʵģ������ҵ�񳡾���Ҫ�����������ƣ��������ȿ���Semaphore��
 * ģ�M������
 * ��һ�죬��������Ҫ����10��ͬѧ����̨������дһ����񣬵�����ʦֻ׼����5֧�ʣ���ˣ�ֻ�ܱ�֤ͬʱֻ��5��ͬѧ�ܹ��õ��ʲ���д���
 * û�л�ȡ���ʵ�ͬѧֻ�ܹ���ǰ���ͬѧ����֮�󣬲����õ���ȥ��д���
 * @author Guo
 *
 */
public class SemaphoreDemo {
	
	private static Semaphore semaphore= new Semaphore(5);
	
	public static void main(String[] args) {
		//�������������з�����Semaphore�������������Ϊ5��Ҳ�����������󲢷�ִ�е��̸߳���Ϊ5�����Կ�����ǰ5���̣߳�ǰ5��ѧ�����Ȼ�ȡ���ʣ�Ȼ����д��񣬶�6-10��5���̣߳����ڻ�ȡ������ɣ�ֻ�������ȴ���
		//���߳�pool-1-thread-4�ͷ������֮��pool-1-thread-9�Ϳ��Ի�ȡ����ɣ���������ִ�С��������̵߳�ִ�й��̣�Ҳ��ͬ���ĵ���
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 10; i++) {
			
			service.execute(() ->{
				
				try {
					
					System.out.println(Thread.currentThread().getName()+"ͬѧ׼����ȡ�ʣ�����");
					
					semaphore.acquire();
					
					System.out.println(Thread.currentThread().getName()+"ͬѧ��ȡ���ʣ�����");
					
					System.out.println(Thread.currentThread().getName()+"��д���ing������");
					
					TimeUnit.SECONDS.sleep(3);
					
					semaphore.release();
					
					System.out.println(Thread.currentThread().getName()+"��д���񣬹黹�˱ʣ�");
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
					
				}
				
				
			});
			
		}
		
		service.shutdown();
		
	}

}
