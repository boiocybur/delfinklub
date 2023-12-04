package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import member.CompetitiveSwimmer;
import member.Member;
import member.ControllerMember;

public class UIChairman {
    Scanner scanner = new Scanner(System.in);
    private ControllerMember controllerMember;

    public UIChairman() {
        this.controllerMember = new ControllerMember();
    }

    public void registerMember() {
        System.out.print("Medlemmets navn: ");
        String name = scanner.nextLine();
        System.out.print("Medlemmets adresse: ");
        String address = scanner.nextLine();
        LocalDate birthday = null;
        int memberID = 0;
        while (birthday == null) {
            System.out.print("Medlemmets fødselsdato (yyyy-MM-dd): ");
            String birthdayStr = scanner.nextLine();
            try {
                birthday = LocalDate.parse(birthdayStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Random random = new Random();
                int r = random.nextInt(100);
                memberID = birthday.getYear() * 10000 + birthday.getMonthValue() * 100 + birthday.getDayOfMonth() + r;
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldigt datoformat. Prøv igen.");
            }
        }
        System.out.print("Medlemmets email: ");
        String email = scanner.nextLine();
        boolean membershipType = false;
        boolean isActive = false;

        while (true) {
            System.out.println("Er medlemmet konkurrencesvømmer?");
            System.out.println("Tryk på 1 for konkurrencesvømmer, tryk på 2 for ikke konkurrencesvømmer.");
            int membershipInput = scanner.nextInt();
            if (membershipInput == 1) {
                String defaultCoach = "TBD"; // To be determined
                String defaultDiscipline = "TBD"; // To be determined
                String defaultMeet = "TBD"; // To be determined
                String defaultPlacement = "TBD"; // To be determined
                LocalDate defaultdateWhenAchieved = LocalDate.of(2000,12,29);
                int defaultMinutes = 0;
                int defaultSeconds = 0;
                int defaultHundredths = 0;
                controllerMember.registerNewCompetitiveSwimmer(name, address, birthday, memberID, email, membershipType, isActive, defaultCoach, defaultDiscipline,defaultMeet, defaultPlacement, defaultdateWhenAchieved, defaultMinutes, defaultSeconds, defaultHundredths);
            } else if (membershipInput == 2) {
                controllerMember.registerMember(name, address, birthday, memberID, email, membershipType, isActive);
            }
            System.out.println("Er medlemmet aktivt eller passivt?");
            System.out.println("Tryk på 1 for aktivt. Tryk på 2 for passivt.");
            int activeInput = scanner.nextInt();
            if (activeInput == 1) {
                isActive = true;
                break;
            } else if (activeInput == 2) {
                isActive = false;
                break;
            } else {
                System.out.println("Ugyldig input. Indtast venligst '1' eller '2'.");
            }

            System.out.println("Ugyldig input. Indtast venligst '1' eller '2'.");


            scanner.nextLine();

            controllerMember.registerMember(name, address, birthday, memberID, email, membershipType, isActive);

        }
    }
    public void editMember() {
        String partialSearchCriteria = scanner.nextLine();

        ArrayList<Member> matchingMembers = new ArrayList<>();
        for (Member members : controllerMember.getAllMembers()) {
            if (members.getName().toLowerCase().contains(partialSearchCriteria.toLowerCase())) {
                matchingMembers.add(members);
            }
        }

        if (matchingMembers.isEmpty()) {
            System.out.println("Ingen medlemmer fundet, der matcher søgekriterierne.");
        } else {
            System.out.println("Matchende medlemmer:");

            for (int i = 0; i < matchingMembers.size(); i++) {
                Member member = matchingMembers.get(i);
                System.out.println(i + 1 + ". " + member.getName());
            }

            System.out.print("Indtast navnet på det medlem, du vil redigere: ");
            int selection = scanner.nextInt();

            if (selection >= 1 && selection <= matchingMembers.size()) {
                Member memberToEdit = matchingMembers.get(selection - 1);

                System.out.println("Nuværende detaljer for medlemmet:");
                printMemberDetails(memberToEdit);
                scanner.nextLine();

                System.out.print("Indtast det nye navn (tryk på Enter for at beholde det nuværende navn): ");
                String newName = scanner.nextLine();
                if (!newName.trim().isEmpty()) {
                    memberToEdit.setName(newName);
                }

                System.out.print("Indtast den nye adresse (tryk på Enter for at beholde den nuværende adresse): ");
                String newAddress = scanner.nextLine();
                if (!newAddress.trim().isEmpty()) {
                    memberToEdit.setAddress(newAddress);
                }

                System.out.print("Indtast den nye alder (tryk på Enter for at beholde den nuværende værdi): ");
                String newAgeInput = scanner.nextLine();
                if (!newAgeInput.isEmpty()) {
                    LocalDate newAge = LocalDate.parse(newAgeInput);
                    memberToEdit.setBirthday(newAge);
                }

                System.out.print("Indtast den nye medlems-ID (tryk på Enter for at beholde den nuværende ID): ");
                String memberID = scanner.nextLine();
                if (!memberID.isEmpty()) {
                    int newID = Integer.parseInt(memberID);
                    memberToEdit.setMemberID(newID);
                }

                System.out.print("Indtast den nye e-mailtype (tryk på Enter for at beholde den nuværende e-mail): ");
                String newEmail = scanner.nextLine();
                if (!newEmail.trim().isEmpty()) {
                    memberToEdit.setEmail(newEmail);
                }

                System.out.print("Indtast den nye medlemstype (1 for konkurrencesvømmer, 2 for ikke-konkurrencesvømmer, tryk på Enter for at beholde den nuværende): ");
                String membershipInput = scanner.nextLine();
                if (!membershipInput.isEmpty()) {
                    memberToEdit.setCompetitiveSwimmer(membershipInput.equals("1"));
                }

                System.out.print("Indtast den nye aktivitetsniveau (1 for aktiv, 2 for passiv, tryk på Enter for at beholde den nuværende): ");
                String activityInput = scanner.nextLine();
                if (!activityInput.isEmpty()) {
                    memberToEdit.setActive(activityInput.equals("1"));
                }


                controllerMember.editMember(memberToEdit);
            }
        }
    }

    private void printMemberDetails(Member member) {
        System.out.println(member);

    }

    public void showMember() {
        ArrayList<Member> medlem = controllerMember.getAllMembers();

        for (Member member : medlem) {
            printMemberDetails(member);

        }

    }
    public void showCompetitiveSwimmers() {
        ArrayList<CompetitiveSwimmer> medlem = controllerMember.getAllCompetitiveSwimmers();

        for (CompetitiveSwimmer member : medlem) {
            printMemberDetails(member);

        }
    }


    public void chairmanMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    Velkommen til menuen for formanden.
                    1. Vis medlem
                    2. Opret medlem
                    3. Rediger medlem
                    4. Slet medlem
                    5. IDCreation
                    6. Load medlemmer
                    7. Save medlemmer
                    0. Gå tilbage
                    """);
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); //bugfix

                switch (choice) {
                    case 1 -> showCompetitiveSwimmers();
                    case 2 -> registerMember();
                    case 3 -> editMember();
                    case 4 -> removeMember();
                    case 5 -> controllerMember.IDCreation();
                    case 6 -> controllerMember.loadMembers();
                    case 7 -> controllerMember.loadCompetitiveSwimmers();
                    case 8 -> controllerMember.saveMembers();
                    case 9 -> controllerMember.saveCompetitiveSwimmers();
                    case 0 -> exit = true;
                }
            } catch (Exception e) {
                System.out.println("Der opstod en fejl: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void removeMember() {
        System.out.println("Indtast navnet på det medlem, du ønsker at fjerne: ");
        String memberToRemove = scanner.nextLine().trim().toLowerCase(); // Convert input to lowercase
        controllerMember.removeMember(memberToRemove);
        // Check if any members were removed
        if (controllerMember.getAllMembers().stream().noneMatch(member ->
                member.getName().trim().equalsIgnoreCase(memberToRemove))) {
            System.out.println("Medlem(mer) fjernet ");
        } else {
            System.out.println("Medlem ikke fundet i databasen.");
        }
    }
}

