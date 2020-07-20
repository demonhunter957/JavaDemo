package designPattern.proxy.dynamicProxy.jdkProxy;

import designPattern.proxy.BuyHouse;
import designPattern.proxy.BuyHouseImpl;

import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 * 原理：根据目标对象的类加载器和接口创建代理类（此代理类是接口的实现类，所以必须使用接口）
 */
public class DynamicProxyTest {

    public static void main(String[] args) {
        //创建目标对象，即被代理的对象
        BuyHouse buyHouse = new BuyHouseImpl();

        //获取目标对象的类加载器
        ClassLoader classLoader = buyHouse.getClass().getClassLoader();
        //获取目标对象实现的接口的类型
        Class<?>[] interfaces = buyHouse.getClass().getInterfaces();
        //指定动态处理器，执行目标对象的方法时，会触发事件处理器的invoke()方法
        BuyHouseDynamicProxy invocationHandler = new BuyHouseDynamicProxy(buyHouse);

        //获取代理对象
        BuyHouse buyHouseProxy = (BuyHouse) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);

        buyHouseProxy.buy();
        buyHouseProxy.anotherBuy(1000);
    }
}
