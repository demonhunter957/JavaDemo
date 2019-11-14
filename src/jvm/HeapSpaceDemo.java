package jvm;

import java.util.Random;

/**
 * @author Chris
 * @create 2019-11-11 18:23
 *
 * jvm参数调整
 * -Xms3m -Xmx3m
 */
public class HeapSpaceDemo {

    public static void main(String[] args) {

        String str = "Chris";

        while (true){
            str += str + new Random().nextInt(11111111)+new Random().nextInt(22222222);
            str.intern();
        }
    }
}
