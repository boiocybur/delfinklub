package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import member.CompetitiveSwimmer;
import member.ControllerMember;
import member.Member;

public class UICoach {
    Scanner scanner = new Scanner(System.in);
    private ControllerMember controllerMember;

    public UICoach() throws IOException {
        this.controllerMember = new ControllerMember();
        controllerMember.loadCompetitiveSwimmers();
    }
    private void printMemberDetails(CompetitiveSwimmer competitiveSwimmer) {
        System.out.println("Navn: " + competitiveSwimmer.getName());
        System.out.println("MedlemsID: " + competitiveSwimmer.getMemberID());
        System.out.println("Email: " + competitiveSwimmer.getEmail());
        System.out.println("Træner: " + competitiveSwimmer.getCoach());
        System.out.println("Aktiv disciplin: " + competitiveSwimmer.getDiscipline());
        System.out.println("---------------------------------------");
    }

    private void showSwimmers(ArrayList<CompetitiveSwimmer> Team) {
        for (CompetitiveSwimmer swimmer : Team) {
            printMemberDetails(swimmer);
        }
    }
    private void showCompetitors(ArrayList<CompetitiveSwimmer> Team){
        for (CompetitiveSwimmer swimmer : Team){
            controllerMember.sortBySpeed();
            printSwimmerDetails(swimmer);
        }
    }


    private void printSwimmerDetails(CompetitiveSwimmer competitiveSwimmer) {
        System.out.println("Navn:  " + competitiveSwimmer.getName());
        System.out.println("MedlemsID: " + competitiveSwimmer.getMemberID());
        System.out.println("Email: " + competitiveSwimmer.getEmail());
        System.out.println("Træner: " + competitiveSwimmer.getCoach());
        System.out.println("Aktiv disciplin: " + competitiveSwimmer.getDiscipline());
        System.out.println("Dato: " + competitiveSwimmer.formatDateWhenAchieved());
        System.out.println("Bedste tid: " + competitiveSwimmer.formatBestTime());
        System.out.println("Placering: " + competitiveSwimmer.getPlacement());
        System.out.println("---------------------------------------");
    }

    public void coachMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    Velkommen til menuen for træneren.
                    1. Vis konkurrencesvømmere junior division
                    2. Vis konkurrencesvømmere senior division
                    3. Sorteret liste over hurtigste svømmere
                    4. Opdater Konkurrence svømmer data
                    5. Load medlemmer
                    6. Save medlemmer
                    7. Sorteret liste over 5 hurtigste junior svømmere i hver disciplin.
                    8. Sorteret liste over 5 hurtigste senior svømmere i hver disciplin.
                    0. Gå tilbage
                    """);
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); //bugfix

                switch (choice) {
                    case 1 -> showSwimmers(controllerMember.juniorTeam());
                    case 2 -> showSwimmers(controllerMember.seniorTeam());
                    case 3 -> {
                        showCompetitors(controllerMember.juniorTeam());
                    }
                    case 4 -> editCompetitiveMember();
                    case 5 -> controllerMember.loadCompetitiveSwimmers();
                    case 6 -> controllerMember.saveCompetitiveSwimmers();
                    case 7 -> controllerMember.topFiveJunior();
                    case 8 -> controllerMember.topFiveSenior();
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
            if (!newCoach.trim().isEmpty() && newCoach.matches("[A-Za-z\\s]+")) {
                swimmerToEdit.setCoach(newCoach);
            }

            System.out.println("Indtast ny disciplin (Crawl, Bryst, Rygcrawl, Butterfly): ");
            String newDiscipline = scanner.nextLine();
            if (newDiscipline.equals("Crawl") || newDiscipline.equals("Bryst") || newDiscipline.equals("Rygcrawl") || newDiscipline.equals("Butterfly")) {
                swimmerToEdit.setDiscipline(newDiscipline);
            } else {
                System.out.println("Ugyldig disciplin. Indtast venligst en af de fire discipliner.");
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

            System.out.println("Indtast ny dato for opnåelse i formatet yyyy-MM-dd (tryk Enter for at beholde den nuværende dato): ");
            String newDateInput = scanner.nextLine();
            if (!newDateInput.trim().isEmpty()) {
                try {
                    LocalDate newDateWhenAchieved = LocalDate.parse(newDateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    swimmerToEdit.setDateWhenAchieved(newDateWhenAchieved);
                } catch (DateTimeParseException e) {
                    System.out.println("Ugyldigt datoformat. Indtast datoen i formatet yyyy-MM-dd.");
                }
            }
            controllerMember.saveMembers();
            System.out.println("Konkurrence svømmer opdateret succesfuldt! ");
        } else {
            System.out.println("Konkurrence svømmer ikke fundet.");
        }
    }

    private CompetitiveSwimmer findCompetitiveSwimmerByName(String name) {
        for (CompetitiveSwimmer swimmer : controllerMember.getAllCompetitiveSwimmers()) {
            if (swimmer.getName().trim().toLowerCase().contains(name)) {
                return swimmer;
            }
        }
        return null;
    }
}

