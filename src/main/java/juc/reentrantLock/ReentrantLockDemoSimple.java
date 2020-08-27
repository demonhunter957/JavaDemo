package juc.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemoSimple {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(ReentrantLockDemoSimple::test, "AA").start();

        new Thread(ReentrantLockDemoSimple::test, "BB").start();

    }

    private static void test(){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + "获得了锁:" + lock.hashCode());
            TimeUnit.SECONDS.sleep(2);
        }catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + "释放了锁:" + lock.hashCode());
        }
    }
}
