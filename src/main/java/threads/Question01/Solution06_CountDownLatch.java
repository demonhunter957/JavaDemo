package threads.Question01;

import java.util.concurrent.CountDownLatch;
/**
 * Solution05的进阶版
 *
 * 用wait notify实现两个线程交替打印
 * 用CountDownLatch控制哪个线程先执行
 */
public class Solution06_CountDownLatch {

    private static final CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        Object object = new Object();

        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        new Thread(() -> {
            synchronized (object) {
                for (char c : c1) {
                    System.out.print(c);
                    latch.countDown();
                    try {
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                object.notify();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                latch.await();
                synchronized (object) {
                    for (char c : c2) {
                        System.out.print(c);
                        object.notify();
                        object.wait();
                    }
                    object.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, "t2").start();
    }

}
