package designPattern.proxy.staticProxy;

public class BuyHouseImpl implements BuyHouse{
    @Override
    public void buy() {
        System.out.println("我要买房");
    }
}
