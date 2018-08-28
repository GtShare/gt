package currency.Productor_Consumer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * ����lock��Conditon����Ϣ֪ͨԭ����ʵ��������-����������
 * @author Guo
 *
 */
public class AwaitSignalProductorConsumer {
	
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition full = lock.newCondition();
	private static Condition empty = lock.newCondition();
	
	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList<>();
		ExecutorService service = Executors.newFixedThreadPool(15);
		
        for (int i = 0; i < 5; i++) {
        	service.submit(new Productor(linkedList, 8,lock));
		}
        
        for (int i = 0; i < 10; i++) {
        	service.submit(new Consumer(linkedList,lock));
		}
        
	}
	
	static class Productor implements Runnable{
		private List<Integer> list;
		private int maxLentgth;
		private Lock lock;
		
		public Productor(List list,int maxLength,Lock lock) {
			this.list=list;
			this.maxLentgth=maxLength;
			this.lock=lock;
		}
		
		@Override
		public void run() {
			while(true){
				lock.lock();
						try {
							while(list.size()==maxLentgth){
								System.out.println("������"+Thread.currentThread().getName()+"list�Դﵽ�������������wait");
								full.await();
								System.out.println("������" + Thread.currentThread().getName() + "  �˳�wait");
							}
							
							Random random = new Random();
							int i= random.nextInt();
							System.out.println("������" + Thread.currentThread().getName() + " ��������" + i);
							list.add(i);
							empty.signalAll();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}finally {
							lock.unlock();
						}
			}
		}
	}
	
	static class Consumer implements Runnable{
		private List<Integer> list;
		private Lock lock;
		
		public Consumer(List list,Lock lock) {
			this.list=list;
			this.lock=lock;
		}
		
		@Override
		public void run() {
			while (true) {
				lock.lock();
						try {
							while (list.isEmpty()) {
								System.out.println("������"+ Thread.currentThread().getName()+"listΪ�գ�����wait");
								empty.await();
								System.out.println("������" + Thread.currentThread().getName() + "  �˳�wait");
							}
							Integer element = list.remove(0);
							System.out.println("������" + Thread.currentThread().getName() + "  �������ݣ�" + element);
							full.signalAll();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}finally {
							lock.unlock();
						}
			}
		}
		
	}
	
	
}
