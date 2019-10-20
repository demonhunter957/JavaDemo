package juc;

/**
 * 双重检测锁的单例模式
 * DCL(Double Check Lock)
 */
public class SingletonDemo {

    //多线程的情况下，只有一个线程能调用此构造方法
    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName());
    }

    private static volatile SingletonDemo instance; //这个地方的volatile是用于防止指令重排序

    public static SingletonDemo getInstance(){

        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(SingletonDemo::getInstance //lambda reference的写法
            ).start();
        }
    }

}
