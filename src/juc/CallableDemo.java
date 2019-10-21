package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(".........come in callable");
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        Thread thread = new Thread(futureTask, "t1");
        thread.start();

//        以上三行等同于：new Thread(new FutureTask<Integer>(new MyThread())).start();
        System.out.println(futureTask.get()); //获得Callable线程的计算结果，如果没有计算完成就要强求，会导致阻塞，直到计算完成，建议放在最后
    }
}
