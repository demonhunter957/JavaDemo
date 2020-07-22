package designPattern.proxy.dynamicProxy.cglibProxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {

    public Object getInstance(final Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] arg, MethodProxy methodProxy) throws Throwable {
        System.out.println("a");
        //fixme: 下面这个方法为啥会导致死循环？？？
        Object result = methodProxy.invoke(o, arg);
        System.out.println("b");
        return result;
    }
}