package juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列的基本概念：当阻塞队列为空的时候，从队列中获取元素的操作会被阻塞；当队列满的时候，往队列添加元素的操作会被阻塞
 * 阻塞队列的好处：我们不需要关心什么时候阻塞线程，什么时候唤醒线程，因为这一切BlockingQueue都给包办了
 *
 * BlockingQueue是个接口，他下面有7个实现类
 *      **ArrayBlockingQueue:是一个基于数组结构的有界阻塞队列，此队列按FIFO原则对元素进行排序
 *      **LinkedBlockingQueue:是一个基于链表结构的有界阻塞队列（但默认大小为Integer.MAX_VALUE），此队列按FIFO排序元素，吞吐量高于ArrayBlockingQueue
 *      PriorityBlockingQueue:支持优先级排序的无界阻塞队列
 *      DelayBlockingQueue:使用优先级队列实现的无阻塞队列
 *      **SynchronousQueue：一个不存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移出操作，否则插入操作一直处于阻塞状态，吞吐量通常要高
 *      LinkedTransferQueue:由链表结构组成的无界阻塞队列
 *      LinkedBlockingDeque:由链表结构组成的双向阻塞队列
 * 注：带**的三个实现类为线程池底层用到的
 *
 * BlockingQueue的常用的4组方法
 * 方法类型    抛出异常     特殊值       阻塞       超时
 * 插入        add(e)     offer(e)     put(e)     offer(e,time,unit)
 * 移除        remove(e)   poll()      take()     poll(time,unit)
 * 检查        element()   peek()      不可用     不可用
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws Exception{

        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(3);
    }

    private static void testOverTime(BlockingQueue<Integer> blockingQueue) throws InterruptedException {
        //消息队列没满的时候，无需等待，offer的时候秒offer
        System.out.println(blockingQueue.offer(0, 2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer(0, 2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer(0, 2l, TimeUnit.SECONDS));
        //当队列满的时候，如果再往里offer(e,time,unit)，阻塞队列会阻塞生产线程一段时间，超过时限后生产者线程会退出并返回false
        System.out.println(blockingQueue.poll(2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2l, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2l, TimeUnit.SECONDS));
        //当队列为空的时候，如果再poll(time,unit)，阻塞队列会阻塞生产线程一段时间，超过时限后生产者线程会退出并返回null
    }

    private static void testBlock(BlockingQueue<Integer> blockingQueue) throws InterruptedException {
        blockingQueue.put(0);
        blockingQueue.put(0);
        blockingQueue.put(0);
        blockingQueue.put(0);
        //当队列满的时候，如果生产者线程继续往队列里put元素，队列会一直阻塞生产线程直到响应中断
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        //当队列为空的时候，如果消费者线程继续从队列里take元素，队列会一直阻塞消费者线程直到队列可用
    }

    //offer() poll() peek()方法不会抛异常
    private static void testBoolean(BlockingQueue<Integer> blockingQueue) {

        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(2));
        System.out.println(blockingQueue.offer(2));
//        arrayBlockingQueue初始capacity为3，再offer第四个元素的时候不会抛异常，但是会返回false
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
//        当arrayBlockingQueue里没有元素，再poll会返回null
//        System.out.println(blockingQueue.peek()); 当arrayBlockingQueue里没有元素，再peek会返回null
    }

    //add() remove() element()方法一言不合就抛异常
    private static void testException(BlockingQueue<Integer> blockingQueue) {
        System.out.println(blockingQueue.add(3));
        System.out.println(blockingQueue.add(3));
        System.out.println(blockingQueue.add(3));
//        arrayBlockingQueue初始capacity为3，再add第四个元素的时候会抛java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        当arrayBlockingQueue里没有元素，再remove会抛java.util.NoSuchElementException
//        blockingQueue.element(); 阻塞队列里没有元素时会抛java.util.NoSuchElementException
    }
}
