package ui;

import cashier.ControllerFinance;
import member.Member;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class UICashier {
    Scanner scanner = new Scanner(System.in);
    private ControllerFinance CF;

    public UICashier(){
        this.CF = new ControllerFinance();
        CF.loadMember();

    }
    public void cashierMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    Velkommen til menuen for formanden.
                    1. Vis medlemmers kontingenter
                    2. Vise medlemmers restance
                    3. Ændre medlemmers restance
                    4. Vis årlig total kontingent indbetaling
                    5. Ændre junior kontingent
                    6. Ændre senior kontingent
                    7. Ændre ældre rabat
                    8. Ændre passivt kontingent
                    9. Load medlemsdata
                    0. Exit
                    """);
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); //bugfix

                switch (choice) {
                    case 1 -> memberContingent();
                    case 2 -> showMemberArrears();
                    case 3 -> setMemberArrears();
                    case 4 -> totalContingent();
                    case 5 -> changeJuniorFee();
                    case 6 -> changeSeniorFee();
                    case 7 -> changeElderFee();
                    case 8 -> changePassiveFee();
                    case 0 -> exit = true;
                }
            } catch (Exception e) {
                System.out.println("Der opstod en fejl: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void memberContingent() {
        for (Member member : CF.getAllMembers()) {
            LocalDate localDate = LocalDate.now();
            int age = Period.between(member.getBirthday(), localDate).getYears();
            boolean isActive = member.isActive();
            String name = member.getName();
            if (!isActive) {
                System.out.println(name  + " " + CF.getPassiveFee());
            } else if (age < 18) {
                System.out.println(name + " " + CF.getJuniorFee());
            } else if (age > 18 && age < 60) {
                System.out.println(name + " " + CF.getSeniorFee());
            } else {
                System.out.println(name + " " + CF.getElderFee());
            }
        }
    }
    private void showMemberArrears(){
        for (Member member : CF.getAllMembers()) {
            if (member.getArrears() > 0) {
                System.out.println(member.getName() + " " + member.getArrears());
            }
        }
    }
    private void setMemberArrears() {
        System.out.println("Skriv navnet på medlemmet du gerne vil ændre restancen for: ");
        String partialSearchCriteria = scanner.nextLine();

        ArrayList<Member> matchingMembers = new ArrayList<>();

        for (Member member : CF.getAllMembers()) {
            if (member.getName().toLowerCase().contains(partialSearchCriteria.toLowerCase())) {
                matchingMembers.add(member);
            }
        }

        if (matchingMembers.isEmpty()) {
            System.out.println("Ingen matchende medlemmer fundet");
        } else {
            System.out.println("Matchende medlemmer:");

            for (int i = 0; i < matchingMembers.size(); i++) {
                Member member = matchingMembers.get(i);
                System.out.println(i + 1 + ". " + member.getName() + " (" + member.getName() + ")");
            }

            System.out.print("Skriv nummeret på det medlem der har betalt: ");
            int selection = scanner.nextInt();

            if (selection >= 1 && selection <= matchingMembers.size()) {
                Member memberArrearToEdit = matchingMembers.get(selection - 1);

                scanner.nextLine();

                System.out.print("Skriv det betalte beløb): ");
                String newArrear = scanner.nextLine();
                if (!newArrear.isEmpty()) {
                    int newArrears = Integer.parseInt(newArrear);
                    int arrear = Math.subtractExact(memberArrearToEdit.getArrears(), newArrears);
                    memberArrearToEdit.setArrears(arrear);
                }
            } else {
                System.out.println("Dette er ikke gyldigt input. Prøv igen.");
            }
        }
    }

    private void totalContingent(){
        System.out.println("total årlig kontingent :");
        System.out.print(CF.totalContingent());
        System.out.println(" kr.");
    }
    private void changeJuniorFee() {
        System.out.println("skriv den nye junior kontingent pris");
        int newJuniorFee = scanner.nextInt();
        if (newJuniorFee <= 0) {
            System.out.println("ugyldigt tal");
        } else {
            CF.setJuniorFee(newJuniorFee);
            System.out.println("Nye junior kontingent: " + newJuniorFee + " kr.");
        }
    }
    private void changeSeniorFee() {
        System.out.println("skriv den nye senior kontingent pris");
        int newSeniorFee = scanner.nextInt();
        if (newSeniorFee <= 0) {
            System.out.println("ugyldigt tal");
        } else {
            CF.setSeniorFee(newSeniorFee);
            System.out.println("Nye senior kontingent: " + newSeniorFee + " kr.");
        }
    }
    private void changeElderFee() {
        System.out.println("skriv den nye ældre rabat i procent");
        int newElderDiscount = scanner.nextInt();
        if (newElderDiscount <= 0) {
            System.out.println("ugyldigt tal");
        } else {
            CF.setElderDiscount(newElderDiscount);
            System.out.println("Nye ældre rabat: " + newElderDiscount + " %");
        }
    }
    private void changePassiveFee() {
        System.out.println("skriv den nye passive kontingent pris");
        int newPassiveFee = scanner.nextInt();
        if (newPassiveFee <= 0) {
            System.out.println("ugyldigt tal");
        } else {
            CF.setPassiveFee(newPassiveFee);
            System.out.println("Nye passiv kontingent: " + newPassiveFee + " kr.");
        }
    }
}
