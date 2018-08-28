package currency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/*
 * ԭ����atomicInteger 
 * getAndIncrement()����ԭ�ӵķ�ʽ��ʵ���е�ԭֵ��1�����ص�������ǰ�ľ�ֵ��
 * getAndSet(int newValue)����ʵ���е�ֵ����Ϊ��ֵ�������ؾ�ֵ��
 * 
 * ԭ��������atomicIntegerArrary
 * 
 * ԭ�Ӹ����������� atomicReference
 * ���Ƚ�����User1��AtomicReference���з�װ��Ȼ�����getAndSet������
 * �ӽ�����Կ������÷�����ԭ�Ӹ������õ�user���󣬱�ΪUser{userName='b', age=2}�����ص���ԭ����user����User{userName='a', age=1}��
 *
 *
 * ԭ�Ӹ����ֶ�����
 * ԭ�Ӹ����ֶ��඼�ǳ����ֻ࣬��ͨ����̬����newUpdater������һ����������������Ҫ������Ҫ���µ�������ԣ�
 * ����������Ա���ʹ��public volatile�������Σ�
 * 
 */
public class AtomicDemo {
	
	private static AtomicInteger atomicInteger = new AtomicInteger(1);
	
	private static int[] value = new int[] {1,2,3};
	
	private static AtomicIntegerArray integerArray = new AtomicIntegerArray(value);
	
	private static AtomicReference<User> atomicReference = new AtomicReference<>();
	
	
	private static AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(Cat.class, "age");
	
	public static void main(String[] args) {
		
		System.out.println(atomicInteger.getAndSet(3));
		
		System.out.println(atomicInteger.getAndIncrement());
		
		System.out.println(atomicInteger.get());
		
		
		int  result = integerArray.getAndAdd(1, 5);//����������Ϊ1��Ԫ�ؼ�5�����ؾ�ֵ��
		
		System.out.println(integerArray.get(1));
		
		System.out.println(result);
		
		
		User user1 = new User("a", 12);
		
		atomicReference.set(user1);
		
		User user2 = new User("b", 13);
		
		User user = atomicReference.getAndSet(user2);
		
		System.out.println(user);//���ؾ���ֵ
		
		System.out.println(atomicReference.get());

		
		Cat cat1 = new  Cat("red", 8);
		 
		int oldAge = updater.getAndAdd(cat1, 5); 

		System.out.println(oldAge);//���ؾ���ֵ
		
		System.out.println(updater.get(cat1));
	}
	
	
	static class User {
		
		private String nameName;
		
		private int age;

		public User(String nameName, int age) {
			this.nameName = nameName;
			this.age = age;
		}

		@Override
		public String toString() {
			return "User [nameName=" + nameName + ", age=" + age + "]";
		}
		
	}
	
	static class Cat{
		
		private String color;
		
		public volatile int age;

		@Override
		public String toString() {
			return "Cat [color=" + color + ", age=" + age + "]";
		}

		public Cat(String color, int age) {
			this.color = color;
			this.age = age;
		}
		
		
		
	}
}
