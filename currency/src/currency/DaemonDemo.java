package currency;
/*
 * �ػ��̣߳�
 * �ػ��߳���һ��������̣߳��ͺ���������һ��������ϵͳ���ػ��ߣ��ں�̨ĬĬ���ػ�һЩϵͳ����
 * �������������̣߳�JIT�߳̾Ϳ�������ػ��̡߳�
 * ��֮��Ӧ�ľ����û��̣߳��û��߳̾Ϳ�����Ϊ��ϵͳ�Ĺ����̣߳������������ϵͳ��ҵ�������
 * �û��߳���ȫ���������ζ������ϵͳ��ҵ������ȫ�������ˣ����ϵͳ��û�ж�����Ҫ�ػ����ˣ��ػ��߳���Ȼ��Ȼ�ͻ��ˡ�
 * ��һ��JavaӦ�ã�ֻ���ػ��̵߳�ʱ��������ͻ���Ȼ�˳���
 * 
 * �ػ��߳����˳���ʱ�򲢲���ִ��finnaly���еĴ��룬���Խ��ͷ���Դ�Ȳ�����Ҫ����finnaly����ִ�У����ֲ����ǲ���ȫ��
 * 
 * �߳̿���ͨ��setDaemon(true)�ķ������߳�����Ϊ�ػ��̡߳�������Ҫע����������ػ��߳�Ҫ����start()����������ᱨ�쳣�����Ǹ��̻߳��ǻ�ִ�У�ֻ�����ᵱ���������û��߳�ִ��
 */
public class DaemonDemo {
	public static void main(String[] args) {
		Thread daemonThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						System.out.println("I am alive");
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}finally {
						System.out.println("finally block");
					}
				}
			}
		});
		
		daemonThread.setDaemon(true);
		daemonThread.start();
		//ȷ��main�߳̽���ǰ�ܸ�daemonThread�ܹ��ֵ�ʱ��Ƭ
		try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
