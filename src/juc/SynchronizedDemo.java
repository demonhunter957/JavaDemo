package juc;

import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @create 2019-10-30 15:45
 */

class CellPhone{

    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName() + "\t comes in");
        try{ TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) {e.printStackTrace();}
        System.out.println(Thread.currentThread().getName() + "\t ----调用了sendEmail完成");
    }

    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName() + "\t comes in");
        System.out.println(Thread.currentThread().getName() + "\t ---调用了sendSMS完成");
    }

    public void hello(){
        System.out.println(Thread.currentThread().getName() + "\t ---调用了hello完成");
    }

}

/**
 * synchronized的8锁问题
 * 1. 标准访问，先打印email还是sms?
 * 2. email方法暂停四秒，先打印哪个？
 * 3. 新增一个普通方法hello(),先打印email还是hello?
 * 4. 两部手机，先打印email还是sms?
 * 5. 两个静态同步方法，同一部手机，先打印email还是sms?
 * 6. 两个静态同步方法，两部部手机，先打印email还是sms?
 * 7. 1个普通同步方法，1个静态同步方法，1部手机，先打印email还是sms?
 * 8. 1个普通同步方法，1个静态同步方法，2部手机，先打印email还是sms?
 *
 * 解释：
 * 一个对象里面如果有多个synchronized方法，某一时刻内，只要有一个线程去调用其中的一个synchronized方法，那么其他线程只能等待
 * 换句话说，它锁的是当前对象this
 */
public class SynchronizedDemo {

    public static void main(String[] args) throws Exception{

        CellPhone phone = new CellPhone();
        CellPhone phone2 = new CellPhone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        //为了让AA线程先启动
        try{ TimeUnit.MILLISECONDS.sleep(200); } catch (InterruptedException e) {e.printStackTrace();}

        new Thread(() -> {
            try {
                phone.sendSMS();
                //phone.hello();
                //phone2.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }

}
