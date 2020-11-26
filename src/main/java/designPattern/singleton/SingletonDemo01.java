package designPattern.singleton;

/**
 * 双重检查单例模式
 * @author Chris
 * @create 2019-12-11 13:29
 */
public class SingletonDemo01 {

    //构造器私有化
    private SingletonDemo01(){}

    //创建一个初始值为空的私有静态属性
    private static volatile SingletonDemo01 instance;

    //对外提供构造方法
    public static SingletonDemo01 getInstance(){
        if (null == instance){
            synchronized (SingletonDemo01.class){
                if (null == instance){
                    instance = new SingletonDemo01();
                }
            }
        }
        return instance;
    }

}

