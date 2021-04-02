package threads.Question01;

/**
 * 用wait notify实现两个线程交替打印
 */
public class Solution05_WaitNotify {

    public static void main(String[] args) {
        char[] c1 = "1234567".toCharArray();
        char[] c2 = "ABCDEFG".toCharArray();

        Object object = new Object();

        new Thread(() -> {
            synchronized (object){
                for (char c : c1) {
                    try {
                        System.out.print(c);
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        }).start();

        new Thread(() -> {
            synchronized (object){{
                for (char c : c2) {
                    try {
                        System.out.print(c);
                        object.notify();
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }}

        }).start();
    }
}
