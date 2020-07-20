package designPattern.proxy.staticProxy;

import designPattern.proxy.BuyHouse;
import designPattern.proxy.BuyHouseImpl;

public class ProxyTest {

    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        BuyHouseStaticProxy buyHouseProxy = new BuyHouseStaticProxy(buyHouse);
        buyHouseProxy.buy();
    }
}
