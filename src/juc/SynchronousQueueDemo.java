package juc;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue没有容量，是一个不储存元素的BlockingQueue
 * 每一个put操作必须等待一个take操作，否则不能添加元素，反之亦然
 */
public class SynchronousQueueDemo {
    public static void main(String[] args){

        BlockingQueue<Integer> blockingQueue = new SynchronousQueue<>();

        new Thread(()->{
            try{
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put(1);
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                blockingQueue.put(2);
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                blockingQueue.put(3);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        },"AAA").start();

        try{TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        new Thread(()->{
            try{
                try{ TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e){ e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t take "+blockingQueue.take());

                try{ TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e){ e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t take "+blockingQueue.take());


                try{ TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e){ e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t take "+blockingQueue.take());
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        },"BBB").start();
    }
}
