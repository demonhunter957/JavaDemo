package designPattern.chainOfResponsibility;

public class ViceHeaderApprove extends Approve {

    public ViceHeaderApprove(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() >= 5000 && purchaseRequest.getPrice() < 10000){
            System.out.println(this.name + "处理");
        }else{
            this.approve.processRequest(purchaseRequest);
        }
    }
}
