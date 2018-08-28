package currency;
/*
 * threadB.join(),其含义是：当前线程A会等待threadB线程终止后threadA才会继续执行
 * 
 * Thread类除了提供join()方法外，另外还提供了超时等待的方法，如果线程threadB在等待的时间内还没有结束的话，threadA会在超时之后继续执行。join方法源码关键是：
 * 
 *  while (isAlive()) {
    	wait(0);
 	}
	可以看出来当前等待对象threadA会一直阻塞，直到被等待对象threadB结束后即isAlive()返回false的时候才会结束while循环，
	当threadB退出时会调用notifyAll()方法通知所有的等待线程

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
                thread.join();//每个线程都会等待前一个线程结束才会继续运行 比如 thread0等待main线程 thread1等待thred0
                System.out.println(thread.getName() + " terminated.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
