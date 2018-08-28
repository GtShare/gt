package currency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CreateThreadDemo {

	public static void main(String[] args) {
		//继承thread
		Thread thread = new Thread(){
			public void run() {
				System.out.println("继承thread");
				super.run();
			}
		};
		
		thread.start();
		
		//实现runable接口
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("实现runable接口");
				
			}
		});
		
		thread1.start();
		
		// 实现callable接口
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future<String> future =service.submit(new Callable(){
			public String call() throws Exception{
				return "通过实现callable 接口";
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
