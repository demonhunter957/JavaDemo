package threads.Question01;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * 用TransferQueue实现两个线程交替打印
 *
 * 原理：两个线程打印对方数组里的东西
 */
public class Solution09_TransferQueue {

    static TransferQueue<Character> queue = new LinkedTransferQueue<>();

    public static void main(String[] args) {

        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c : c1) {
                try {
                    queue.transfer(c);
                    System.out.print(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();

        new Thread(() -> {
            for (char c : c2) {
                try {
                    System.out.print(queue.take());
                    queue.transfer(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();
    }
}
