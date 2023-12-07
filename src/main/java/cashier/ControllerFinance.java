package cashier;

import member.Member;

import java.util.ArrayList;

public class ControllerFinance {
    private MembershipFee mf;

    public ControllerFinance() {
        this.mf = new MembershipFee();
    }
    public int totalContingent(){
        return mf.totalContingent();
    }
    public ArrayList<Member> getAllMembers(){
        return mf.getAllMembers();
    }
    public int getElderFee() {
        return mf.elderFee();
    }

    public void setElderDiscount(int elderDiscount) {
        this.mf.setElderDiscount(elderDiscount);
    }
    public int getSeniorFee() {
        return mf.getSeniorFee();
    }

    public void setSeniorFee(int seniorFee) {
        this.mf.setSeniorFee(seniorFee);
    }

    public int getJuniorFee() {
        return mf.getJuniorFee();
    }
    public void loadMember(){
        mf.loadMember();
    }

    public void setJuniorFee(int juniorFee) {
        this.mf.setJuniorFee(juniorFee);
    }

    public int getPassiveFee() {
        return mf.getPassiveFee();
    }

    public void setPassiveFee(int passiveFee) {
        this.mf.setPassiveFee(passiveFee);
    }

}
