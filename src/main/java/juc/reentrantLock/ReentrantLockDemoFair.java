package juc.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁演示：谁等待的时间长，谁就获得锁
 */
public class ReentrantLockDemoFair {
    private static final Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        new Thread(ReentrantLockDemoFair::test, "AA").start();
        new Thread(ReentrantLockDemoFair::test, "BB").start();
        new Thread(ReentrantLockDemoFair::test, "CC").start();
        new Thread(ReentrantLockDemoFair::test, "DD").start();
        new Thread(ReentrantLockDemoFair::test, "EE").start();

    }

    private static void test(){
        for (int i = 0; i < 2; i++) {
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName() + "获得了锁:" + lock.hashCode());
                TimeUnit.SECONDS.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }finally{
                lock.unlock();
            }
        }

    }
}
