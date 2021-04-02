package threads.Question01;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用ReentrantLock + Condition实现两个线程交替打印
 */
public class Solution07_LockCondition {

    static ReentrantLock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    public static void main(String[] args) {

        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        new Thread(()->{
            try {
                lock.lock();
                for(char c : c1) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
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
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t2").start();


    }
}
