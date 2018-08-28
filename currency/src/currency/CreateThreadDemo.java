package currency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CreateThreadDemo {

	public static void main(String[] args) {
		//�̳�thread
		Thread thread = new Thread(){
			public void run() {
				System.out.println("�̳�thread");
				super.run();
			}
		};
		
		thread.start();
		
		//ʵ��runable�ӿ�
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("ʵ��runable�ӿ�");
				
			}
		});
		
		thread1.start();
		
		// ʵ��callable�ӿ�
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<String> future =service.submit(new Callable(){
			public String call() throws Exception{
				return "ͨ��ʵ��callable �ӿ�";
			}
		});
		
		try {
			String result = future.get();
			System.out.println(result);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch (ExecutionException e) {
			e.printStackTrace();
		}
		
	}
}
