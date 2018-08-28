package currency;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试concurrentLinkedQueue的 比较size()和isEmpty() 效率
 * 场景：10000个人去饭店吃饭，10张桌子供饭，分别比较size() 和 isEmpty() 的耗时
 * @author Guo
 *
 */
public class TestConcurrentLinkedQueue {
	public static void main(String[] args) throws InterruptedException {
		int peopleNum =10000;
		int tableNum =10;
		
		ConcurrentLinkedQueue<String> queue= new ConcurrentLinkedQueue<>();
		CountDownLatch count = new  CountDownLatch(tableNum);//计数器 栅栏
		
		//讲吃饭人数放在队列
		for (int i = 0; i < peopleNum; i++) {
			queue.offer("消费者_"+i);
		}
		
		// 执行10个线程从队列取出元素（10个桌子开始供饭）
		System.out.println("-------------开饭");
		long start = System.currentTimeMillis();
		
		ExecutorService executorService = Executors.newFixedThreadPool(tableNum);
		
		for (int i = 0; i < tableNum; i++) {
			executorService.submit(new Dinner("00"+(i+1),queue,count));
		}
		//计数器等待，直到队列为空（所有人吃完）
		count.await();
		
		long time=System.currentTimeMillis()-start;
		System.out.println("---------------所有人已经吃完");
		System.out.println("共耗时：" + time);
		
		executorService.shutdown();
	}
	
	private static class Dinner implements Runnable{
		
		private String name;
		private ConcurrentLinkedQueue<String> queue;
		private CountDownLatch count;

		public Dinner(String name,ConcurrentLinkedQueue<String>queue,CountDownLatch count){
			this.name=name;
			this.queue=queue;
			this.count=count;
		}
		
		@Override
		public void run() {
			//!queue.isEmpty()126m；而queue.size() 420ms
			//while (queue.size()>0) 
			while(!queue.isEmpty()){
				//从队列取出一个元素 排队的人少一个
				System.out.println(queue.poll()+"已吃完---- 饭桌编号："+name);
			}
			count.countDown();
		}
		
	}
	
}
