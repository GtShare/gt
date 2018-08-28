package currency;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 基于链接节点的无界线程安全队列  FIFO
 * @author Guo
 *
 */
public class ConcurrentLinkedQueueDemo {
	
	public static void main(String[] args) {
		ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue<>();
		// queue.offer()将指定元素插入此队列的尾部。
		queue.offer("德玛西亚");
		System.out.println("offer后 ，队列是否空？"+queue.isEmpty());
		//queue.poll()获取并移除此队列的头，如果此队列为空，则返回 null。
		System.out.println("从队列中poll:"+queue.poll());
		System.out.println("poll后，队列是否空:"+queue.isEmpty());
		System.out.println("从队列中poll:"+queue.poll());
		
		//ConcurrentLinkedQueue中的add() 和 offer() 完全一样，都是往队列尾部添加元素
		//queue.peek() 获取但不移除次队列的头;如果队列为空返回null
		queue.offer("艾欧尼亚");
		System.out.println("从队列中peek："+queue.peek());
		System.out.println("从队列中peek："+queue.peek());
		
		//remove(object o) 从队列中移除指定元素的单个实例;
		//remove一个已存在元素，会返回true，remove不存在元素，返回false 
		System.out.println("从队列中remove已存在元素"+queue.remove("艾欧尼亚"));
		System.out.println("从队列中remove不存在元素"+queue.remove("德玛西亚"));
		
		// size or isEmpty
		//与大多数 collection 不同，size()方法不是 一个固定时间操作。由于这些队列的异步特性，确定当前的元素数需要进行一次花费 O(n) 时间的遍历。
		//所以在需要判断队列是否为空时，尽量不要用 queue.size()>0，而是用 !queue.isEmpty()
		//当数据量越大，这种耗时差距越明显。所以这种判断用isEmpty 更加合理 具体例子TestConcurrentLinkedQueue
		
		//contains 包含指定元素返回true
		queue.offer("诺克萨斯");
		queue.add("暗影岛");
		 System.out.println(queue.contains("诺克萨斯"));
		
		//toArray返回以恰当顺序包含此队列所有元素的数组
		 Object[] objects=queue.toArray();
		 System.out.println(objects[0]+"----"+objects[1]);
		 
		//将数据存储到指定数组
		 String[] strs= new String[2];
		 queue.toArray(strs);
		 System.out.println(strs[1]+"-----"+strs[1]);
		 
		 //迭代器 iterator  返回在此队列元素上以恰当顺序进行迭代的迭代器
		 
		 Iterator<String> iterator = queue.iterator();
		 while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}


}
