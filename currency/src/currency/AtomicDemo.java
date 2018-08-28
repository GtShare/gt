package currency;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/*
 * 原子类atomicInteger 
 * getAndIncrement()：以原子的方式将实例中的原值加1，返回的是自增前的旧值；
 * getAndSet(int newValue)：将实例中的值更新为新值，并返回旧值；
 * 
 * 原子类数组atomicIntegerArrary
 * 
 * 原子更新引用类型 atomicReference
 * 首先将对象User1用AtomicReference进行封装，然后调用getAndSet方法，
 * 从结果可以看出，该方法会原子更新引用的user对象，变为User{userName='b', age=2}，返回的是原来的user对象User{userName='a', age=1}。
 *
 *
 * 原子更新字段类型
 * 原子更新字段类都是抽象类，只能通过静态方法newUpdater来创建一个更新器，并且需要设置想要更新的类和属性；
 * 更新类的属性必须使用public volatile进行修饰；
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
		
		
		int  result = integerArray.getAndAdd(1, 5);//数组中索引为1的元素加5，返回旧值；
		
		System.out.println(integerArray.get(1));
		
		System.out.println(result);
		
		
		User user1 = new User("a", 12);
		
		atomicReference.set(user1);
		
		User user2 = new User("b", 13);
		
		User user = atomicReference.getAndSet(user2);
		
		System.out.println(user);//返回旧数值
		
		System.out.println(atomicReference.get());

		
		Cat cat1 = new  Cat("red", 8);
		 
		int oldAge = updater.getAndAdd(cat1, 5); 

		System.out.println(oldAge);//返回旧数值
		
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
