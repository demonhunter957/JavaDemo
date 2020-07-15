package juc;

import java.util.concurrent.TimeUnit;

class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t 获取了" + lockA + " 尝试获取" + lockB);
            //暂停一下
            try{ TimeUnit.SECONDS.sleep(2); }catch (InterruptedException e){e.printStackTrace();}
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName() + "\t 获取了" + lockB + " 尝试获取" + lockA);
            }
        }
    }
}
public class DeadLockDemo {

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB)).start();
        new Thread(new HoldLockThread(lockB, lockA)).start();

        /*
         * linux  ps -ef|grep xxxx    ls -l查看当前进程的命令
         * windows下的java运行程序，也有类似ps的查看进程的命令，但是目前我们需要查看的只是java
         *           jps = java ps      jps -l
         *           jstack 进程id
         * */
    }
}
