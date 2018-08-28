package currency;
import java.text.ParseException;
/*
 * SimpleDateFormat.parse方法会有线程安全的问题
 * 使用threadLocal包装SimpleDateFormat，将该实例不被多线程共享
 */
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo {
	
	private  static ThreadLocal<SimpleDateFormat> sdf =  new ThreadLocal<>();
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100; i++) {
			executorService.submit(new DateUtil("2019-11-25 09:00:"+i%60));
		}
		
	}
	
	static class DateUtil implements Runnable{
		
		private String date;
		
		public DateUtil(String date) {
			this.date= date;
		}
		
		@Override
		public void run() {
			if (sdf.get()==null) {
				sdf.set(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
			}else{
				try {
					sdf.get().parse(this.date);
					System.out.println(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
