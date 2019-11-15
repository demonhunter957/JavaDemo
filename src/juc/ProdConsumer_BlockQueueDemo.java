package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{

    private volatile boolean FLAG = true;// 默认开启，进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();// 默认初始值为0

    private BlockingQueue<Integer> blockingQueue;

    public MyResource(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProduce() throws InterruptedException{
        Integer data;
        boolean retValue;
        while(FLAG){
            data = atomicInteger.incrementAndGet();
            retValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(retValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列: "+data+"成功");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 插入队列: "+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 生产停止");
    }

    public void myConsume() throws InterruptedException{
        Integer result;
        while(FLAG){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(null == result){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒无法从队列获取值，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
        }
    }

    public void stop(){
        this.FLAG = false;
    }
}

/**
 * 用BlockingQueue实现的生产者消费者模式
 *
 * 一个初始值为0的变量，两个线程对其交替操作，一个加1，一个减1，来5轮
 *
 * 1.线程 操作 资源类
 * 2.判断 干活 通知
 * 3.防止虚假唤醒！！判断的时候一定要用while（jdk里的规定）
 *
 * */

public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) throws Exception{

        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
            System.out.println();
            try{
                myResource.myProduce();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
            try{
                myResource.myConsume();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"Consumer").start();

        try{TimeUnit.SECONDS.sleep(5);}catch (InterruptedException e){e.printStackTrace();}

        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("5秒钟到，main停止");
        myResource.stop();
    }
}
