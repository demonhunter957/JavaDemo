package designPattern.chainOfResponsibility;

public abstract class Approve {

    protected Approve approve;
    protected String name;

    public abstract void processRequest(PurchaseRequest purchaseRequest);

    public Approve(String name) {
        this.name = name;
    }

    public void setApprove(Approve approve) {
        this.approve = approve;
    }
}
