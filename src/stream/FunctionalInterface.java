package stream;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * java内置核心四大函数式接口
 */
public class FunctionalInterface {

    public static void main(String[] args) {

        // 1. 函数型接口：Function<T, R>，接受一个参数T返回一个参数R
        // new这个接口的时候重写了apply(String s) 方法
        Function<String , Integer> function = s -> {return s.length();}; // 也可以写成s -> s.length()
        System.out.println(function.apply("Chris"));

        // 2. 断定型接口：Predicate<T>，接受一个参数T返回一个boolean
        // new这个借口的时候重写了 test(String s)方法
        Predicate<String> predicate = s -> s.isEmpty();
        System.out.println(predicate.test("Chris"));


        // 3. 消费型接口：Consumer<T>，接受一个参数T没有返回值
        // new这个接口的时候重写了accept(String s)方法
        Consumer<String> consumer = s ->  {System.out.println(s);};
        consumer.accept("Chris");

        // 4. 供给型接口：Supplier<T>，不接受参数返回一个参数T
        // new这个接口的时候重写了get()方法
        Supplier<String> supplier = () -> "Chris";
        System.out.println(supplier.get());


    }
}
