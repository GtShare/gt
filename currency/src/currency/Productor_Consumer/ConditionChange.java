package currency.Productor_Consumer;

import java.util.ArrayList;
import java.util.List;

/*
 * wait��֪ͨ�����仯������߳��ڵȴ�ʱ���ܵ���֪ͨ������֮��ȴ������������˱仯����û���ٴζԵȴ����������жϣ�Ҳ�ᵼ�³�����ִ���
 * 
 * �����������һ��������3���̣߳�Consumer1,Consumer2�Լ�Productor������Consumer1������wait�������̴߳�����WAITTING״̬��
 * ���ҽ��������ͷų�������ˣ�Consumer2�ܹ���ȡ���������Ӷ����뵽ͬ�������У���ִ�е�wait����ʱ��ͬ����Ҳ���ͷŶ�������
 * ��ˣ�productor�ܹ���ȡ�������������뵽ͬ��������У���list�в������ݺ�ͨ��notifyAll����֪ͨ����WAITING״̬��Consumer1��Consumer2�̡߳�
 * consumer1�õ��������󣬴�wait�������˳���ɾ����һ��Ԫ����ListΪ�գ�����ִ�н������˳�ͬ���飬�ͷŵ���������
 * ���ʱ��Consumer2��ȡ���������󣬴�wait�����˳�����������ִ�У����ʱ��Consumer2��ִ��lock.remove(0);�ͻ����
 * ��ΪList����Consumer1ɾ��һ��Ԫ��֮���Ѿ�Ϊ���ˡ�
 * 
 */
public class ConditionChange {
	
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
					if (lock.isEmpty()) {
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
