package designPattern.chainOfResponsibility;

public class PurchaseRequest {

    private int id;
    private float price;

    public PurchaseRequest(int id, float price) {
        this.id = id;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}
