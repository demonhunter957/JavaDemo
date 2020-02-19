package designPattern.chainOfResponsibility;

public class CollegeApprove extends Approve{

    public CollegeApprove(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() >= 3000 && purchaseRequest.getPrice() < 5000){
            System.out.println(this.name + "处理");
        }else{
            this.approve.processRequest(purchaseRequest);
        }
    }
}
