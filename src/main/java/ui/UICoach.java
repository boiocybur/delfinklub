package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import member.Member;
import member.CompetitiveSwimmer;
import member.ControllerMember;

public class UICoach {
    Scanner scanner = new Scanner(System.in);
    private ControllerMember controllerMember;

    public UICoach() {
        this.controllerMember = new ControllerMember();
    }
    private void printMemberDetails(Member member) {
        System.out.println(member);

    }

    private void printSwimmers(ArrayList<CompetitiveSwimmer> Team) {
        for (CompetitiveSwimmer swimmer : Team) {
            printMemberDetails(swimmer);
        }
    }
    private void sort(){
        controllerMember.sortBySpeed();
    }

    public void coachMenu() {
        while (true) {
            System.out.println("""
                        Velkommen til menuen for træneren.
                        1. Vis konkurrencesvømmere junior division
                        2. Vis konkurrencesvømmere Senior division
                        3. Sorteret liste over hurtigste svømmere
                        """);
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); //bugfix

                switch (choice) {
                   case 1 -> printSwimmers(controllerMember.juniorTeam());
                   case 2 -> printSwimmers(controllerMember.seniorTeam());
                   case 3 -> sort();
                }
            } catch (Exception e) {
                System.out.println("Der opstod en fejl: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
