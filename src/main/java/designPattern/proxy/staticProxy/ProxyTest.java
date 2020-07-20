package designPattern.proxy.staticProxy;

public class ProxyTest {

    public static void main(String[] args) {
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buy();
        System.out.println("------------------------------");
        BuyHouseProxy buyHouseProxy = new BuyHouseProxy(buyHouse);
        buyHouseProxy.buy();
    }
}
