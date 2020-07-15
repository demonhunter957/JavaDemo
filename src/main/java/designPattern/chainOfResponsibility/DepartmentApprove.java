package designPattern.chainOfResponsibility;

public class DepartmentApprove extends Approve {

    public DepartmentApprove(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() < 3000){
            System.out.println(this.name + "处理");
        }else{
            this.approve.processRequest(purchaseRequest);
        }
    }


}
