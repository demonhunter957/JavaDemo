package designPattern.chainOfResponsibility;

public class Client {

    public static void main(String[] args) {
        PurchaseRequest purchaseRequest = new PurchaseRequest(1, 10000);

        DepartmentApprove departmentApprove = new DepartmentApprove("王老师");
        CollegeApprove collegeApprove = new CollegeApprove("李主任");
        ViceHeaderApprove viceHeaderApprove = new ViceHeaderApprove("张副校长");
        HeaderApprove headerApprove = new HeaderApprove("钱校长");

        departmentApprove.setApprove(collegeApprove);
        collegeApprove.setApprove(viceHeaderApprove);
        viceHeaderApprove.setApprove(headerApprove);
        headerApprove.setApprove(departmentApprove);

        departmentApprove.processRequest(purchaseRequest);
    }
}
