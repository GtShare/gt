package currency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
	
	 private CountDownLatch cdl = new CountDownLatch(2); 
	    private Random rnd = new Random();
	     
	    class FirstTask implements Runnable{
	        private String id;
	         
	        public FirstTask(String id){
	            this.id = id;
	        }
	         
	        @Override
	        public void run(){
	            System.out.println("Thread "+ id + " is start");
	            try {
	                Thread.sleep(rnd.nextInt(1000));
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("Thread "+ id + " is over");
	            cdl.countDown();
	        }
	    }
	     
	    class SecondTask implements Runnable{
	        private String id;
	         
	        public SecondTask(String id){
	            this.id = id;
	        }
	         
	        @Override
	        public void run(){
	            try {
	                cdl.await();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("----------Thread "+ id + " is start");
	            try {
	                Thread.sleep(rnd.nextInt(1000));
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println("----------Thread "+ id + " is over");
	        }
	    }
	     
	    public static void main(String[] args){
	        ExecutorService es = Executors.newCachedThreadPool();
	        CountDownLatchDemo cdld = new CountDownLatchDemo();
	        es.submit(cdld.new SecondTask("c"));
	        es.submit(cdld.new SecondTask("d"));
	        es.submit(cdld.new FirstTask("a"));
	        es.submit(cdld.new FirstTask("b"));
	        es.shutdown();
	    }
	
}
