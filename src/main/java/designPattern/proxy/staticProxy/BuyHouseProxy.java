package designPattern.proxy.staticProxy;

public class BuyHouseProxy implements BuyHouse{

    private BuyHouse buyHouse;

    public BuyHouseProxy(BuyHouse buyHouse) {
        this.buyHouse = buyHouse;
    }

    @Override
    public void buy() {
        System.out.println("买前准备...");
        buyHouse.buy();
        System.out.println("买后装修...");
    }
}
