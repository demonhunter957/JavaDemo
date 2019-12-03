package juc;

import java.util.concurrent.CompletableFuture;

/**
 * 异步调用
 */
public class CompleteFutureDemo {

    public static void main(String[] args) throws Exception {

        //异步调用没有返回值的情况
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + "没有返回值");
        });
        completableFuture.get();

        //异步调用有返回值的情况
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "\t completableFuture2");
            int i = 1/0; //模拟一个异常
            return 1024;
        });
        Integer result = completableFuture2.whenComplete((t, u) -> {
            System.out.println("********t: " + t); //如果没有异常，则第一个参数被打印出来，第二个参数为null
            System.out.println("********u: " + u); //如果有异常，则第二个参数被打印出来，第一个参数为null
        }).exceptionally(f -> {
            System.out.println("exception: " + f.getMessage());
            return 444;
        }).get();
        System.out.println(result);
    }
}
