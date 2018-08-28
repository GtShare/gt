package currency;
/**
 * ����ģʽ��˫����
 * volatile������ָֹ������������壬����������ԡ�
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
					//1�����ڴ桢2��ʼ����3ָ���ڴ�ռ� ������һ��ԭ�Ӳ��� �п���jvmָ���������ʱ������132˳��
					//��ִ�е�3��ʱ���ִ��store-writeЭ�����ڴ棬����һ������ȫ�Ķ���
					//�����߳����жϵ�һ��ʵ���Ƿ�Ϊnullʱ ����false���ͻ᷵�����������ɳ�����쳣
					//��volatile������ָֹ������������壬����������ԡ�
					instance = new VolatileSingleInstance();
				}
			}
		}
		return instance;
	}

}
