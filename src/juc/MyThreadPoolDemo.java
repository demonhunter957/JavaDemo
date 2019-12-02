package juc;

import java.util.concurrent.*;


public class MyThreadPoolDemo {

    public static void main(String[] args){
        selfDefinedThreadPool();
    }

    /**
     * 线程池的7大参数
     * 注：线程池能处理的线程数为 maximumPoolSize + 阻塞队列的长度
     *
     * 4种拒绝策略：
     *   (1)AbortPolicy 直接抛异常RejectedExecutionException
     *   (2)CallerRunsPolicy “调用者运行”一种调节机制，不会抛弃任务也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量
     *   (3)DiscardOldestPolicy 抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务
     *   (4)DiscardPolicy 丢弃无法处理的任务，不予任何处理也不抛异常。如果任务允许丢失，这是最好的一种策略。
     */
    private static void selfDefinedThreadPool() {
        ExecutorService threadPool = new ThreadPoolExecutor(2, //核心常驻线程数
                5, //最大线程数
                1L, //等待缩容的时间
                TimeUnit.SECONDS, //等待缩容的时间单位
                new LinkedBlockingQueue<>(3), //等待的阻塞队列
                Executors.defaultThreadFactory(), //线程生产工厂，一般用这个默认值
                new ThreadPoolExecutor.AbortPolicy()); //拒绝策略，默认值为AbortPolicy。

        try{
            for(int i = 1; i <= 11; i++){
                final int temp = i;
                threadPool.execute(()-> System.out.println(Thread.currentThread().getName()+"\t 办理业务" + temp));
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
    private static void threadPoolInit() {

        //ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个处理线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor(); //一池1个处理线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个线程

        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程

        try {
            for (int i = 1; i <= 10; i++) {
                final int temp = i;
                threadPool.execute(() -> System.out.println(Thread.currentThread().getName()+"\t 办理业务"+ temp));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }
}
