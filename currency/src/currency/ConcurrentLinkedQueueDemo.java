package currency;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * �������ӽڵ���޽��̰߳�ȫ����  FIFO
 * @author Guo
 *
 */
public class ConcurrentLinkedQueueDemo {
	
	public static void main(String[] args) {
		ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue<>();
		// queue.offer()��ָ��Ԫ�ز���˶��е�β����
		queue.offer("��������");
		System.out.println("offer�� �������Ƿ�գ�"+queue.isEmpty());
		//queue.poll()��ȡ���Ƴ��˶��е�ͷ������˶���Ϊ�գ��򷵻� null��
		System.out.println("�Ӷ�����poll:"+queue.poll());
		System.out.println("poll�󣬶����Ƿ��:"+queue.isEmpty());
		System.out.println("�Ӷ�����poll:"+queue.poll());
		
		//ConcurrentLinkedQueue�е�add() �� offer() ��ȫһ��������������β�����Ԫ��
		//queue.peek() ��ȡ�����Ƴ��ζ��е�ͷ;�������Ϊ�շ���null
		queue.offer("��ŷ����");
		System.out.println("�Ӷ�����peek��"+queue.peek());
		System.out.println("�Ӷ�����peek��"+queue.peek());
		
		//remove(object o) �Ӷ������Ƴ�ָ��Ԫ�صĵ���ʵ��;
		//removeһ���Ѵ���Ԫ�أ��᷵��true��remove������Ԫ�أ�����false 
		System.out.println("�Ӷ�����remove�Ѵ���Ԫ��"+queue.remove("��ŷ����"));
		System.out.println("�Ӷ�����remove������Ԫ��"+queue.remove("��������"));
		
		// size or isEmpty
		//������ collection ��ͬ��size()�������� һ���̶�ʱ�������������Щ���е��첽���ԣ�ȷ����ǰ��Ԫ������Ҫ����һ�λ��� O(n) ʱ��ı�����
		//��������Ҫ�ж϶����Ƿ�Ϊ��ʱ��������Ҫ�� queue.size()>0�������� !queue.isEmpty()
		//��������Խ�����ֺ�ʱ���Խ���ԡ����������ж���isEmpty ���Ӻ��� ��������TestConcurrentLinkedQueue
		
		//contains ����ָ��Ԫ�ط���true
		queue.offer("ŵ����˹");
		queue.add("��Ӱ��");
		 System.out.println(queue.contains("ŵ����˹"));
		
		//toArray������ǡ��˳������˶�������Ԫ�ص�����
		 Object[] objects=queue.toArray();
		 System.out.println(objects[0]+"----"+objects[1]);
		 
		//�����ݴ洢��ָ������
		 String[] strs= new String[2];
		 queue.toArray(strs);
		 System.out.println(strs[1]+"-----"+strs[1]);
		 
		 //������ iterator  �����ڴ˶���Ԫ������ǡ��˳����е����ĵ�����
		 
		 Iterator<String> iterator = queue.iterator();
		 while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}


}
