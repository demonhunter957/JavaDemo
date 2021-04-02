package threads.Question01;

import java.util.concurrent.locks.LockSupport;

/**
 * 用LockSupport实现两个线程交替打印
 */
public class Solution01_LockSupport {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {

        char[] c1 = "1234567".toCharArray();

        char[] c2 = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for (char c : c1) {
                System.out.print(c);
                LockSupport.unpark(t2); //叫醒T2
                LockSupport.park(); //T1阻塞
            }

        }, "t1");

        t2 = new Thread(() -> {
            for (char c : c2) {
                LockSupport.park(); //t2阻塞
                System.out.print(c);
                LockSupport.unpark(t1); //叫醒t1
            }
        }, "t2");

        t1.start();
        t2.start();

    }
}
