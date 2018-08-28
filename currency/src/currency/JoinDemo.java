package currency;
/*
 * threadB.join(),�京���ǣ���ǰ�߳�A��ȴ�threadB�߳���ֹ��threadA�Ż����ִ��
 * 
 * Thread������ṩjoin()�����⣬���⻹�ṩ�˳�ʱ�ȴ��ķ���������߳�threadB�ڵȴ���ʱ���ڻ�û�н����Ļ���threadA���ڳ�ʱ֮�����ִ�С�join����Դ��ؼ��ǣ�
 * 
 *  while (isAlive()) {
    	wait(0);
 	}
	���Կ�������ǰ�ȴ�����threadA��һֱ������ֱ�����ȴ�����threadB������isAlive()����false��ʱ��Ż����whileѭ����
	��threadB�˳�ʱ�����notifyAll()����֪ͨ���еĵȴ��߳�

 */
public class JoinDemo {
    public static void main(String[] args) {
        Thread previousThread = Thread.currentThread();
        for (int i = 1; i <= 10; i++) {
            Thread curThread = new JoinThread(previousThread);
            curThread.start();
            previousThread = curThread;
        }
    }

    static class JoinThread extends Thread {
        private Thread thread;

        public JoinThread(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();//ÿ���̶߳���ȴ�ǰһ���߳̽����Ż�������� ���� thread0�ȴ�main�߳� thread1�ȴ�thred0
                System.out.println(thread.getName() + " terminated.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
