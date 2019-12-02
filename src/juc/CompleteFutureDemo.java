package juc;

import java.util.concurrent.CompletableFuture;

public class CompleteFutureDemo {

    public static void main(String[] args) throws Exception {

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
            System.out.println(Thread.currentThread().getName() + "没有返回值");
        });
        completableFuture.get();

        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName() + "\t completableFuture2");
            return 1024;
        });
        completableFuture2.whenComplete((t, u) -> {
            System.out.println("********t");
            System.out.println("********u");
        }).exceptionally(f -> {
            System.out.println("exception: " + f.getMessage());
            return 444;
        }).get();
    }
}
