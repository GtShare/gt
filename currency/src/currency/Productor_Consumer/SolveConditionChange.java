package currency.Productor_Consumer;

import java.util.ArrayList;
import java.util.List;

/*
 * wait��֪ͨ�����仯������߳��ڵȴ�ʱ���ܵ���֪ͨ������֮��ȴ������������˱仯����û���ٴζԵȴ����������жϣ�Ҳ�ᵼ�³�����ִ���
 * 
 * ���������
 * ͨ������ķ��������Կ���Consumer2���쳣����Ϊ�̴߳�wait�����˳�֮��û���ٴζ�wait���������жϣ���ˣ���ʱ��wait�����Ѿ������˱仯��
 * ����취���ǣ���wait�˳�֮���ٶ����������жϼ��ɡ�
 * 
 * 
 * ����Ĵ�����֮ǰ�Ĵ������ֻ�ǽ� wait ��Χ�� if ����Ϊ while ѭ�����ɣ������� list Ϊ��ʱ���̱߳������ȴ������������ȥִ��ɾ�� list ��Ԫ�صĴ��롣
 *
 * 
 */
public class SolveConditionChange {
	
	private static  List<String> lockObject = new ArrayList<>();
	
	public static void main(String[] args) {
	  Consumer consumer1 = new Consumer(lockObject);
	  Consumer consumer2 = new Consumer(lockObject);
	  Productor productor = new  Productor(lockObject);
	  consumer1.start();
	  consumer2.start();
	  productor.start();
	}
	
	static class Consumer extends Thread{
		
		private List<String> lock;
		
		public Consumer(List lock) {
			this.lock=lock;
		}
			
		@Override
		public void run() {
			
			synchronized (lock) {
				try {
					 //����ʹ��if�Ļ����ͻ����wait�����仯��ɳ�����������
					while (lock.isEmpty()) {
						System.out.println(Thread.currentThread().getName()+"list Ϊ��");
						System.out.println(Thread.currentThread().getName()+"����wait����");
						lock.wait();
						System.out.println(Thread.currentThread().getName()+"wait��������");
					}
					String element = lock.remove(0);
					System.out.println(Thread.currentThread().getName()+"ȡ����һ��Ԫ��Ϊ��"+element);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	static class Productor extends Thread{
		private List<String> lock;
		public Productor(List lock) {
			this.lock =lock;
		}
		
		@Override
		public void run() {
			synchronized (lock) {
				
				System.out.println(Thread.currentThread().getName()+"��ʼ���Ԫ��");
				
				lock.add(Thread.currentThread().getName());
				
				lock.notifyAll();
				
			}
			
		}
	}

}
