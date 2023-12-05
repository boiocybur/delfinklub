package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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

    private void sort() {
        controllerMember.sortBySpeed();
        controllerMember.saveMembers();
    }

    public void coachMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    Velkommen til menuen for træneren.
                    1. Vis konkurrencesvømmere junior division
                    2. Vis konkurrencesvømmere Senior division
                    3. Sorteret liste over hurtigste svømmere
                    4. Sorteret liste over top 5 svømmere 
                    4. Opdater Konkurrence svømmerners data
                    0. Gå tilbage
                    """);
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); //bugfix

                switch (choice) {
                    case 1 -> printSwimmers(controllerMember.juniorTeam());
                    case 2 -> printSwimmers(controllerMember.seniorTeam());
                    case 3 -> sort();
                    case 4 -> editCompetitiveMember();
                    case 5 -> controllerMember.loadMembers();
                    case 0 -> exit = true;
                }
            } catch (Exception e) {
                System.out.println("Der opstod en fejl: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void editCompetitiveMember() {
        System.out.println("Indtast navnet på den konkurrence svømmer, du vil redigere: ");
        String swimmerName = scanner.nextLine().trim().toLowerCase();

        CompetitiveSwimmer swimmerToEdit = findCompetitiveSwimmerByName(swimmerName);

        if (swimmerToEdit != null) {
            System.out.println("Redigerer konkurrence svømmer: " + swimmerToEdit.getName());
            System.out.println("Indtast ny træner (tryk Enter for at beholde den nuværende træner): ");
            String newCoach = scanner.nextLine();
            if (!newCoach.trim().isEmpty()) {
                swimmerToEdit.setCoach(newCoach);
            }

            System.out.println("Indtast ny disciplin (tryk Enter for at beholde den nuværende disciplin): ");
            String newDiscipline = scanner.nextLine();
            if (!newDiscipline.trim().isEmpty()) {
                swimmerToEdit.setDiscipline(newDiscipline);
            }

            System.out.println("Indtast nyt stævne (tryk Enter for at beholde det nuværende stævne): ");
            String newMeet = scanner.nextLine();
            if (!newMeet.trim().isEmpty()) {
                swimmerToEdit.setMeet(newMeet);
            }

            System.out.println("Indtast ny placering (tryk Enter for at beholde den nuværende placering): ");
            String newPlacement = scanner.nextLine();
            if (!newPlacement.trim().isEmpty()) {
                swimmerToEdit.setPlacement(newPlacement);
            }

            System.out.println("Indtast ny bedste tid i minutter (tryk Enter for at springe over): ");
            String newMinutesInput = scanner.nextLine();
            int newMinutes;
            if (newMinutesInput.trim().isEmpty()) {
                newMinutes = swimmerToEdit.getBestTime().toMinutesPart();
            } else {
                newMinutes = Integer.parseInt(newMinutesInput);
            }

            System.out.println("Indtast ny bedste tid i sekunder (tryk Enter for at springe over): ");
            String newSecondsInput = scanner.nextLine();
            int newSeconds;
            if (newSecondsInput.trim().isEmpty()) {
                newSeconds = swimmerToEdit.getBestTime().toSecondsPart();
            } else {
                newSeconds = Integer.parseInt(newSecondsInput);
            }

            System.out.println("Indtast ny bedste tid i millisekunder (tryk Enter for at springe over): ");
            String newMillisecondsInput = scanner.nextLine();
            int newMilliseconds;
            if (newMillisecondsInput.trim().isEmpty()) {
                newMilliseconds = swimmerToEdit.getBestTime().toMillisPart();
            } else {
                newMilliseconds = Integer.parseInt(newMillisecondsInput);
            }

            swimmerToEdit.setBestTime(newMinutes, newSeconds, newMilliseconds);

            System.out.println("Indtast ny dato for opnåelse (tryk Enter for at beholde den nuværende dato): ");
            String newDateInput = scanner.nextLine();
            if (!newDateInput.trim().isEmpty()) {
                try {
                    LocalDate newDateWhenAchieved = LocalDate.parse(newDateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    swimmerToEdit.setDateWhenAchieved(newDateWhenAchieved);
                } catch (DateTimeParseException e) {
                    System.out.println("Ugyldigt datoformat. Indtast datoen i formatet yyyy-MM-dd.");
                }
            }

            controllerMember.editCompetitiveSwimmer(swimmerToEdit);
            controllerMember.saveMembers();
            System.out.println("Konkurrence svømmer opdateret succesfuldt! ");
        } else {
            System.out.println("Konkurrence svømmer ikke fundet.");
        }
    }

    private CompetitiveSwimmer findCompetitiveSwimmerByName(String name) {
        for (CompetitiveSwimmer swimmer : controllerMember.getAllCompetitiveSwimmers()) {
            if (swimmer.getName().toLowerCase().equals(name)) {
                return swimmer;
            }
        }
        return null;
    }
}

