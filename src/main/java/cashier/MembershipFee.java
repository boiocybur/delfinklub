package cashier;

import data.Database;
import member.Member;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class MembershipFee {
    private Database db;
    private int juniorFee;
    private int seniorFee;
    private int elderDiscount;
    private int elderFee;
    private int passiveFee;

    public MembershipFee(){
        this.db = new Database();
        this.juniorFee = 1000;
        this.seniorFee = 1600;
        this.elderDiscount = 25;
        this.elderFee = 0;
        this.passiveFee = 500;
    }
    public int getSeniorFee() {
        return seniorFee;
    }

    public void setSeniorFee(int seniorFee) {
        this.seniorFee = seniorFee;
    }

    public int getJuniorFee() {
        return juniorFee;
    }

    public void setJuniorFee(int juniorFee) {
        this.juniorFee = juniorFee;
    }

    public int getPassiveFee() {
        return passiveFee;
    }

    public void setPassiveFee(int passiveFee) {
        this.passiveFee = passiveFee;
    }
    public ArrayList<Member> getAllMembers(){
        return db.getAllMembers();
    }

    public int getElderFee() {
        return elderFee;
    }

    public void setElderDiscount(int elderDiscount) {
        this.elderDiscount = elderDiscount;
    }
    public int elderFee(){
        int elderCalculation1 = Math.multiplyExact(seniorFee, elderDiscount);
        int elderCalculation2 = Math.divideExact(elderCalculation1, 100);
        return Math.subtractExact(seniorFee, elderCalculation2);
    }

    public int totalContingent() {
        int totalAmount = 0;
        for (Member member : db.getAllMembers()) {
            LocalDate localDate = LocalDate.now();
            int age = Period.between(member.getBirthday(), localDate).getYears();
            boolean isActive = member.isActive();
            if (age < 18 && isActive) {
                totalAmount += juniorFee;
            } else if (age >= 18 && age < 60 && isActive) {
                totalAmount += seniorFee;
            } else if (age >= 60 && isActive) {
                totalAmount += elderFee;
            } else {
                totalAmount+= passiveFee;
            }
        } return totalAmount;
    }
}


