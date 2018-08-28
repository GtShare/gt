package currency.Productor_Consumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * ʹ��BlockingQueueʵ��������-������
 * @author Guo
 *
 */
public class BlockQueueProductorConsumer {
	
	private static LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>();
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(15);
		
        for (int i = 0; i < 5; i++) {
        	service.submit(new Productor(queue));
		}
        
        for (int i = 0; i < 10; i++) {
        	service.submit(new Consumer(queue));
		}
        
	}
	
	static class Productor implements Runnable{

		private  BlockingQueue queue;
		public Productor(BlockingQueue queue) {
			this.queue=queue;
			
		}
		
		@Override
		public void run() {
			try {
				while (true) {
					Random random = new Random();
					int i = random.nextInt();
					System.out.println("������"+Thread.currentThread().getName()+"��������"+i);
					queue.put(i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	static class Consumer implements Runnable{
		private BlockingQueue  queue;
		
		public Consumer(BlockingQueue queue) {
			this.queue=queue;
		}
		
		@Override
		public void run() {
			try {
				while (true) {
					Integer element = (Integer) queue.take();
					
					System.out.println("������"+Thread.currentThread().getName()+"������������"+element);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
