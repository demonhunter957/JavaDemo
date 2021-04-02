package threads.Question01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Solution07的进阶版
 * 用ReentrantLock + 2个Condition实现两个线程交替打印
 */
public class Solution08_LockCondition2 {

    static ReentrantLock lock = new ReentrantLock();

    // Condition的本质就是等待队列
    static Condition condition1 = lock.newCondition();
    static Condition condition2 = lock.newCondition();

    public static void main(String[] args) {

        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        new Thread(()->{
            try {
                lock.lock();
                for(char c : c1) {
                    System.out.print(c);
                    condition2.signal(); // 唤醒第二个队列里等待的线程
                    condition1.await(); // 自己进入第一个队列里等待
                }
                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1").start();


        new Thread(()->{
            try {
                lock.lock();
                for(char c : c2) {
                    System.out.print(c);
                    condition1.signal(); // 唤醒第一个队列里等待的线程
                    condition2.await(); // 自己进入第二个队列里等待
                }
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t2").start();


    }
}
