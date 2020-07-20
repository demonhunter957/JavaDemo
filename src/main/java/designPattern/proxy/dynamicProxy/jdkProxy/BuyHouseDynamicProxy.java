package designPattern.proxy.dynamicProxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BuyHouseDynamicProxy implements InvocationHandler {

    private final Object object;

    public BuyHouseDynamicProxy(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (null != args){
            for (Object arg : args) {
                System.out.println(arg);
            }
        }

        Object result = method.invoke(object, args);
        System.out.println("method name = " + method.getName());
        return result;
    }
}
