package ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import member.Member;
import member.ControllerMember;

public class UIChairman {
    Scanner scanner = new Scanner(System.in);
    private ControllerMember controllerMember;

    public UIChairman() throws IOException {
        this.controllerMember = new ControllerMember();
        controllerMember.loadMembers();
        controllerMember.loadCompetitiveSwimmers();
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
                    5. Save medlemmer
                    0. Gå tilbage
                    """);
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); //bugfix

                switch (choice) {
                    case 1 -> showMember();
                    case 2 -> registerMember();
                    case 3 -> editMember();
                    case 4 -> removeMember();
                    case 5 -> saveMembers();
                    case 0 -> exit = true;
                }
            } catch (Exception e) {
                System.out.println("Der opstod en fejl: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    public void registerMember() {
        String name = "";
        while (name.isEmpty() || !name.matches("^[A-Za-zæøåÆØÅ\\s]+$")) {
            System.out.print("Medlemmets navn: ");
            name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Navn må ikke være tom.");
            } else if (!name.matches("^[A-Za-zæøåÆØÅ\\s]+$")) {
                System.out.println("Navnet indeholder ugyldige tegn.");
                name = ""; // Nulstil navn for at fortsætte løkken
            }
        }
        String address = "";
        while (address.isEmpty() || !address.matches("^[A-Za-zæøåÆØÅ\\s]+$")) {
            System.out.print("Medlemmets adresse: ");
            address = scanner.nextLine();
            if (address.isEmpty()) {
                System.out.println("Addressen må ikke være tom.");
            } else if (!address.matches("^[A-Za-zæøåÆØÅ\\s]+$")) {
                System.out.println("Addressen indeholder ugyldige tegn.");
                address = ""; // Nulstil addresse for at fortsætte løkken
            }
        }
        LocalDate birthday = null;
        int memberID = 0;
        while (birthday == null) {
            System.out.print("Medlemmets fødselsdato (dd-MM-yyyy): ");
            String birthdayStr = scanner.nextLine();
            try {
                birthday = LocalDate.parse(birthdayStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                Random random = new Random();
                int r = random.nextInt(99);
                memberID = birthday.getMonthValue() * 10000 + birthday.getDayOfMonth() * 100 + r;
            } catch (DateTimeParseException e) {
                System.out.println("Ugyldigt datoformat. Prøv igen.");
            }
        }
        String email = null;
        while (email == null) {
            System.out.print("Medlemmets email: ");
            email = scanner.nextLine();
            if (email.isEmpty()) {
                System.out.println("Email må ikke være tom.");
            } else if (!email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) {
                System.out.println("Ugyldig email-adresse. Email skal indeholde '@' og ende med et domæne efter '.'");
                email = null; // Sæt email til null for at fortsætte loopet
            }
        }

        boolean membershipType = false;
        boolean isActive = false;
        int arrears = 0;

        while (true) {
            System.out.println("Er medlemmet konkurrencesvømmer? (1 for ja, 2 for nej): ");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                membershipType = true;
                break;
            } else if (input.equals("2")) {
                membershipType = false;
                break;
            }
            System.out.println("Ugyldig input. Indtast venligst '1' eller '2'.");
        }

        while (true) {
            System.out.println("Er medlemmet aktivt eller passivt? (1 for aktivt, 2 for passivt): ");
            String input = scanner.nextLine();
            if (input.equals("1")) {
                isActive = true;
                break;
            } else if (input.equals("2")) {
                isActive = false;
                break;
            }
            System.out.println("Ugyldig input. Indtast venligst '1' eller '2'.");
        }
        scanner.nextLine();

            controllerMember.registerMember(name, address, birthday, memberID, email, membershipType, isActive, arrears);

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
        System.out.println("Navn: " + member.getName());
        System.out.println("MedlemsID: " + member.getMemberID());
        System.out.println("Email: " + member.getEmail());
        System.out.println("--------------------------");
    }

    private void showMember() {
        ArrayList<Member> medlem = controllerMember.getAllMembers();

        for (Member member : medlem) {
            printMemberDetails(member);
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
    private void saveMembers(){
       controllerMember.saveMembers();
       controllerMember.saveCompetitiveSwimmers();
    }
}

