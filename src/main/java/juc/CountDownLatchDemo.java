package juc;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.CountDownLatch;

/**
 * countdownLatch让一些线程阻塞知道另一些线程完成一些列操作后才被唤醒
 * countdownLatch.await()：当一个或多个线程调用此方法时，调用线程会被阻塞
 * countdownLatch.countDown()：其他线程调用此方法时，会将计数器减1（调用此方法的线程不会阻塞）
 * 当计数器的值变为0时，调用await()方法被阻塞的线程会被唤醒，继续执行
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 被灭了");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getName()).start();
        }
        countDownLatch.await();
        Thread.currentThread().setName("秦国");
        System.out.println(Thread.currentThread().getName()+"\t *****一统天下******");
    }
}


//lombok使用：https://projectlombok.org/
@AllArgsConstructor
enum CountryEnum{

    QI(0,"齐国"),CHU(1,"楚国"),YAN(2,"燕国"),
    ZHAO(3,"赵国"),WEI(4,"魏国"),HAN(5,"韩国");

    @Getter
    private Integer index;
    @Getter
    private String name;


    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum countryEnum : myArray) {
            if (index == countryEnum.getIndex()){
                return countryEnum;
            }
        }
        return null;
    }
}

