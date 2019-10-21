package juc;

import java.util.concurrent.*;


public class MyThreadPoolDemo {
    public static void main(String[] args){
        ExecutorService threadPool = new ThreadPoolExecutor(2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        try{
            for(int i=1;i<=11;i++){
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

/**
 * 线程池
 * 线程池的工作主要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务
 * 如果线程数量超过了最大数量的线程排队等候，等待其他线程执行完毕，再从队列中取出任务来执行
 * 他的主要他特点为：线程复用；控制最大并发数；管理线程
 *
 * 1.降低资源消耗。通过重复利用自己创建的线程降低线程创建和销毁的消耗
 * 2.提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行
 * 3.提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行资源的统一分配，调优和监控
 */
    //System.out.println(Runtime.getRuntime().availableProcessors());
    //Array Arrays  辅助工具类
    //Collection Collections
    //Executor Executors
    private static void threadPoolInit() {

        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(1);//一池1个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个线程

        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }
}
