package juc;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 1.故障现象：
 * 多线程并发对ArrayList进行写操作的时候，会报java.util.ConcurrentModificationException
 *
 * 2.故障原因：
 * 原因是为了保证并发性，add操作没有加锁
 * 一个线程正在写入，另一个线程过来抢夺，导致数据不一致异常。并发修改异常。
 *
 * 3.解决方案：
 *      3.1 new Vector<>();
 *      3.2 Collections.synchronizedList(new ArrayList<>());
 *      3.3 new CopyOnWriteArrayList<>()；
 *             写时复制
 *             CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，
 *             而是先将当前object[]进行Copy，复制出一个新的容器Object[] newElements，然后新的容器Object[] newElements
 *             里添加元素，添加完元素之后，再将原容器的引用指向新的容器setArray（newElements）;
 *             这样做的好处是可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种
 *             读写分离的思想，读和写不同的容器。
 *             **************************CopyOnWriteArrayList的add方法源码如下**************************************
 *                 public boolean add(E e) {
 *                      final ReentrantLock lock = this.lock;
 *                      lock.lock();
 *                      try {
 *                          Object[] elements = getArray();
 *                          int len = elements.length;
 *                          Object[] newElements = Arrays.copyOf(elements, len + 1);
 *                          newElements[len] = e;
 *                          setArray(newElements);
 *                          return true;
 *                      } finally {
 *                          lock.unlock();
 *                      }
 *                  }
 */

public class ContainerNotSafeDemo {

    public static void main(String[] args){

        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for(int i = 1; i <= 30; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }

    }
}
