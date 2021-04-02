package threads.Question01;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用BlockingQueue实现两个线程交替打印
 */
public class Solution03_BlockingQueue {

    static BlockingQueue<String> q1 = new ArrayBlockingQueue<>(1);

    static BlockingQueue<String> q2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) {

        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c : c1) {
                try {
                    System.out.print(c);
                    q1.put("ok");
                    q2.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();

        new Thread(() -> {
            for (char c : c2) {
                try {
                    q1.take();
                    System.out.print(c);
                    q2.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}
