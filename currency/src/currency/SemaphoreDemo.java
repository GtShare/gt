package currency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore的具体使用。Semaphore用来做特殊资源的并发访问控制是相当合适的，如果有业务场景需要进行流量控制，可以优先考虑Semaphore。
 * 模擬場景：
 * 有一天，班主任需要班上10个同学到讲台上来填写一个表格，但是老师只准备了5支笔，因此，只能保证同时只有5个同学能够拿到笔并填写表格，
 * 没有获取到笔的同学只能够等前面的同学用完之后，才能拿到笔去填写表格
 * @author Guo
 *
 */
public class SemaphoreDemo {
	
	private static Semaphore semaphore= new Semaphore(5);
	
	public static void main(String[] args) {
		//根据输出结果进行分析，Semaphore允许的最大许可数为5，也就是允许的最大并发执行的线程个数为5，可以看出，前5个线程（前5个学生）先获取到笔，然后填写表格，而6-10这5个线程，由于获取不到许可，只能阻塞等待。
		//当线程pool-1-thread-4释放了许可之后，pool-1-thread-9就可以获取到许可，继续往下执行。对其他线程的执行过程，也是同样的道理。
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 10; i++) {
			
			service.execute(() ->{
				
				try {
					
					System.out.println(Thread.currentThread().getName()+"同学准备获取笔；；；");
					
					semaphore.acquire();
					
					System.out.println(Thread.currentThread().getName()+"同学获取到笔；；；");
					
					System.out.println(Thread.currentThread().getName()+"填写表格ing；；；");
					
					TimeUnit.SECONDS.sleep(3);
					
					semaphore.release();
					
					System.out.println(Thread.currentThread().getName()+"填写完表格，归还了笔！");
					
				} catch (InterruptedException e) {
					
					e.printStackTrace();
					
				}
				
				
			});
			
		}
		
		service.shutdown();
		
	}

}
