package designPattern.proxy.staticProxy;

import designPattern.proxy.BuyHouse;

public class BuyHouseStaticProxy implements BuyHouse {

    private final BuyHouse buyHouse;

    public BuyHouseStaticProxy(BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }

    public void buy() {
        System.out.println("买前准备...");
        buyHouse.buy();
        System.out.println("买后装修...");
    }

    public void anotherBuy(int price) {
        System.out.println("买前准备...");
        buyHouse.buy();
        System.out.println("买后装修...");
    }
}
