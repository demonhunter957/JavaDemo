package designPattern.proxy;


public class BuyHouseImpl implements BuyHouse {
    @Override
    public void buy() {
        System.out.println("我要买房");
    }

    @Override
    public void anotherBuy(int price) {
        System.out.println("我再买一套房花了：" + price);
    }
}
