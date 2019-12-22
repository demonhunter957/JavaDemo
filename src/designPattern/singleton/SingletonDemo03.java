package designPattern.singleton;


/**
 * 懒汉式单例模式
 * 1.可以实现lazy loading，但是线程不安全，只能在单线程的环境下使用：
 *  在多线程下，如果一个线程进入了if (null == instance)但还未来得及往下执行，这时另一个线程也通过了这个判断语句，便会产生多个实例
 * @author Chris
 * @create 2019-12-11 13:42
 */
public class SingletonDemo03 {

    private SingletonDemo03(){}

    private static SingletonDemo03 instance;

    public static SingletonDemo03 getInstance(){
        if (null == instance){
            instance = new SingletonDemo03();
        }
        return instance;
    }
}
