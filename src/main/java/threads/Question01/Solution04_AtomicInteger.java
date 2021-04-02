package threads.Question01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用AtomicInteger实现两个线程交替打印
 * 也是一种自旋的思想
 */
public class Solution04_AtomicInteger {

    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        new Thread(() -> {
            for (char c : c1) {
                while (atomicInteger.get() != 1) {
                }
                System.out.print(c);
                atomicInteger.set(0);
            }

        }).start();

        new Thread(() -> {
            for (char c : c2) {
                while (atomicInteger.get() != 0) {
                }
                System.out.print(c);
                atomicInteger.set(1);
            }
        }).start();
    }
}
