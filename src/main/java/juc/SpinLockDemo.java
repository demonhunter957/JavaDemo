package juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 * 尝试获取的线程不会立即阻塞，而是采用循环的方式去尝试获取锁
 * 这样的好处是减少了线程上下文切换的消耗，缺点是循环会消耗CPU
 *
 * 线程AA首次调用myLock()方法的时候atomicReference.compareAndSet()返回true，因此不进入while循环；
 * AA线程调用完myLock()后，休息了5秒，此时BB线程开始调用myLock()；
 * 因为AA线程已经调用过，且compareAndSet返回值为true，因此atomicReference已经变成了AA线程，
 * 当BB线程调用myLock()方法时，expect不为null，因此进入了while循环。此时BB线程并没有被阻塞。
 * 5秒后AA线程调用了myUnlock()方法，将atomicReference的值变成了null，此时BB线程马上跳出了循环。
 * */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread currentThread = Thread.currentThread();
        System.out.println(currentThread.getName()+"\t comes in ");
        while(!atomicReference.compareAndSet(null, currentThread)){}
    }

    public void myUnLock(){
        Thread currentThread = Thread.currentThread();
        atomicReference.compareAndSet(currentThread,null);
        System.out.println(currentThread.getName()+"\t invoked myUnLock()");
    }

    public static void main(String[] args){

        SpinLockDemo spinLockDemo = new SpinLockDemo();

        new Thread(()->{
            spinLockDemo.myLock();
            try{ TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) {e.printStackTrace();}
            spinLockDemo.myUnLock();
        },"AA").start();

        try{ TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {e.printStackTrace();}

        new Thread(()->{
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"BB").start();
    }
}
