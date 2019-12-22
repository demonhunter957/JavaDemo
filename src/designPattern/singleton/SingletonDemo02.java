package designPattern.singleton;

/**
 * 饿汉式单例模式
 * 优点：
 *  1.线程安全，因为instance的实例化是在类加载的时候完成的，JVM机制保证类加载的时候一定线程安全
 * 缺点：
 *  1.由于在类加载时就完成了实例化，不能实现lazy loading，可能造成内存浪费
 * @author Chris
 * @create 2019-12-11 13:34
 */
public class SingletonDemo02 {

    private SingletonDemo02(){}

    private static SingletonDemo02 instance = new SingletonDemo02();

//    static {
//        instance = new SingletonDemo02();
//    }

    public static SingletonDemo02 getInstance(){
        return instance;
    }
}
