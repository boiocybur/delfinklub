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
    public int getJuniorFee(){
        return mf.getElderFee();
    }
    public int getSeniorFee(){
        return mf.getSeniorFee();
    }
    public int getElderFee(){
        return mf.getElderFee();
    }
    public int getPassiveFee(){
        return mf.getPassiveFee();
    }
}
