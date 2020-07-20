package designPattern.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BuyHouseDynamicProxy implements InvocationHandler {

    private Object object;

    public BuyHouseDynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("买房前准备");
        if (null != args){
            for (Object arg : args) {
                System.out.println(arg);
            }
        }

        Object result = method.invoke(object, args);
        System.out.println("method name = " + method.getName());
        System.out.println("买房后装修");
        return result;
    }
}
