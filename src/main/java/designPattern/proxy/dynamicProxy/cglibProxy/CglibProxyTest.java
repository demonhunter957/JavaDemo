package designPattern.proxy.dynamicProxy.cglibProxy;


public class CglibProxyTest {

    public static void main(String[] args) {
        Dog dog = new Dog();
        CglibProxy cglibProxy = new CglibProxy();
        Dog dogProxy = (Dog) cglibProxy.getInstance(dog);
        dogProxy.run();

    }
}
