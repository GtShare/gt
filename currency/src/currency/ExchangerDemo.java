package currency;
/**
 * 在青春洋溢的中学时代，下课期间，男生经常会给走廊里为自己喜欢的女孩子送情书，相信大家都做过这样的事情吧 ：)。男孩会先到女孩教室门口，然后等女孩出来，教室那里就是一个同步点，然后彼此交换信物，也就是彼此交换了数据。现在，就来模拟这个情景
 * 
 * 这个例子很简单，也很能说明Exchanger的基本使用。当两个线程都到达调用exchange方法的同步点的时候，两个线程就能交换彼此的数据
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
				toGirl = exchanger.exchange("我其实暗恋你很久了");
				System.out.println("女孩说"+toGirl);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		service.execute(() ->{
			try {
				System.out.println("女生慢慢的从教室里走出来");
				String toBoy = exchanger.exchange("我也很喜欢你--");
				System.out.println("男孩说"+toBoy);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
	
}
