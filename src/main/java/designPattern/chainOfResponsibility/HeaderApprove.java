package designPattern.chainOfResponsibility;

public class HeaderApprove extends Approve {

    public HeaderApprove(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() >= 10000){
            System.out.println(this.name + "处理");
        }else{
            this.approve.processRequest(purchaseRequest);
        }
    }
}
