package currency;
/**
 * 单例模式的双检索
 * volatile包含禁止指令重排序的语义，其具有有序性。
 * @author Guo
 *
 */
public class VolatileSingleInstance {
	private VolatileSingleInstance(){};
	private volatile static VolatileSingleInstance instance;
	public VolatileSingleInstance getInstance(){
		if (instance==null) {
			synchronized (VolatileSingleInstance.class) {
				if (instance==null) {
					//1分配内存、2初始化、3指向内存空间 ，不是一个原子操作 有可能jvm指令重排序的时候会产生132顺序，
					//当执行到3的时候会执行store-write协会主内存，返回一个不安全的对象，
					//其他线程在判断第一个实例是否为null时 返回false，就会返回这个对象，造成程序的异常
					//而volatile包含禁止指令重排序的语义，其具有有序性。
					instance = new VolatileSingleInstance();
				}
			}
		}
		return instance;
	}

}
