package designPattern.proxy.staticProxy;

import designPattern.proxy.BuyHouse;

public class BuyHouseStaticProxy implements BuyHouse {

    private final BuyHouse buyHouse;

    public BuyHouseStaticProxy(BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }

    @Override
    public void buy() {
        System.out.println("买前准备...");
        buyHouse.buy();
        System.out.println("买后装修...");
    }

    @Override
    public void anotherBuy(int price) {
        System.out.println("买前准备...");
        buyHouse.buy();
        System.out.println("买后装修...");
    }
}
