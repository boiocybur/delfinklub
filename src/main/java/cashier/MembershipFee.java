package cashier;

import data.Database;
import member.Member;

import java.time.LocalDate;
import java.time.Period;

public class MembershipFee {
    private Database db;
    private int juniorFee;
    private int seniorFee;
    private int elderFee;
    private int passiveFee;

    public MembershipFee(){
        this.db = new Database();
        this.juniorFee = 1000;
        this.seniorFee = 1600;
        this.elderFee = 1200;
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


    /*public int memberContingent() {
        int age = 0;
        for (Member member : db.getAllMembers()) {
            LocalDate localDate = LocalDate.now();
            int age = Period.between(member.getBirthday(), localDate).getYears();
            return age;
        }


    }*/
            /*boolean isActive = member.isActive();

            if (age < 17 && isActive) {
                return juniorFee;
            } else if (age > 17 && isActive) {
                return seniorFee;
            } else if (age < 59 && isActive) {
                return elderFee;

                //int intermediary = Math.multiplyExact(1600, 25);
                //return Math.divideExact(intermediary, 100);

            } else {
                return passiveFee;
            }
        } return 0;
    }

    public int totalContingent() {
        int totalAmount = 0;
        for (Member member : db.getAllMembers()) {
            LocalDate localDate = LocalDate.now();
            int age = Period.between(member.getBirthday(), localDate).getYears();
            boolean isActive = member.isActive();
            if (age < 18 && isActive) {
                totalAmount += juniorFee;
            } else if (age > 17 && isActive) {
                totalAmount += seniorFee;
            } else if (age < 59 && isActive) {
                totalAmount += elderFee;
            } else {
                return passiveFee;
            }
        } return totalAmount;
    }*/
}


