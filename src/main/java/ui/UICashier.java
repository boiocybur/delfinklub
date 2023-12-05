package ui;

import cashier.ControllerFinance;
import member.Member;

import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class UICashier {
    Scanner scanner = new Scanner(System.in);
    private ControllerFinance controllerFinance;

    public UICashier(){
        this.controllerFinance = new ControllerFinance();
    }


    public void cashierMenu() {
        while (true) {
            System.out.println("""
                    Velkommen til menuen for formanden.
                    1. Vis medlemmers kontingenter
                    2. Vise medlemmers restance
                    3. Vis årlig total kontingent indbetaling
                    4. Ændrer junior kontingent
                    5. Ændrer senior kontingent
                    5. Ændrer ældre kontingent
                    7. Ændrer passivt kontingent
                    """);
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); //bugfix

                switch (choice) {
                    case 1 -> memberContingent();
                    //case 2 ->
                    case 3 -> System.out.println(controllerFinance.totalContingent());
                }
            } catch (Exception e) {
                System.out.println("Der opstod en fejl: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void totalContingent(){
        System.out.println(controllerFinance.totalContingent());
    }
    private void memberContingent() {
        for (Member member : controllerFinance.getAllMembers()) {
            LocalDate localDate = LocalDate.now();
            int age = Period.between(member.getBirthday(), localDate).getYears();
            boolean isActive = member.isActive();
            String name = member.getName();
            int memberID = member.getMemberID();
            if (age < 17 && isActive) {
                System.out.println(name + " " + memberID + " " + controllerFinance.getJuniorFee());
            } else if (age > 17 && isActive) {
                System.out.println(name + " " + memberID + " " + controllerFinance.getSeniorFee());
            } else if (age < 59 && isActive) {
                System.out.println(name + " " + memberID + " " + controllerFinance.getElderFee());
            } else {
                System.out.println(name + " " + memberID + " " + controllerFinance.getPassiveFee());
            }
        }
    }

}
