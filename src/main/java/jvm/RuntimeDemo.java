package jvm;


public class RuntimeDemo {

    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.availableProcessors()); //获取cpu核数
        /**
         * 堆内存调优
         * -Xmx:最大分配内存，默认为物理内存的1/4
         * -Xms:初始分配大小，默认为物理机内存的1/64
         * 注：生产环境下-Xmx和-Xms大小必须一致！避免GC跟应用程序争抢内存，产生停顿
         *
         * VM参数：   -Xms1024m -Xmx1024m -XX:+PrintGCDetails
         */
        System.out.println(runtime.maxMemory()/(double)1024/1024); // -Xmx java虚拟机可以使用最大内存量
        System.out.println(runtime.totalMemory()/(double)1024/1024); // -Xms java虚拟机内存总量

    }
}
