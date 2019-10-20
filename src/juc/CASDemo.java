package juc;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * 1、CAS是什么？ ==>compareAndSet 比较并交换
 *
 * */
public class CASDemo {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(); //atomicInteger初始值为0
        System.out.println(atomicInteger.compareAndSet(0, 2019) + "\t current data: " + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(0, 1024) + "\t current data: " + atomicInteger.get());
    }
}
