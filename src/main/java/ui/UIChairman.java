package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import member.Member;
import member.ControllerMember;

public class UIChairman {
    Scanner scanner = new Scanner(System.in);
    private ControllerMember controllerMember;

    public UIChairman() {
        this.controllerMember = new ControllerMember();
    }

    public void registerMember() {
        System.out.print("Name of member: ");
        String name = scanner.nextLine();
        System.out.print("Address of member: ");
        String address = scanner.nextLine();
        LocalDate birthday = null;
        int memberID = 0;
        while (birthday == null) {
            System.out.print("Birthday of member (yyyy-MM-dd): ");
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
        System.out.print("Email of member: ");
        scanner.nextLine();
        String email = scanner.nextLine();
        boolean membershipType = false;
        boolean isActive = false;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Is member active or passive?");
            System.out.println("Press 1 for active. Press 2 for passive");
            int activeInput = scanner.nextInt();
            if (activeInput == 1) {
                isActive = true;
                break;
            } else if (activeInput == 2) {
                isActive = false;
                break;
            } else {
                System.out.println("Invalid input. Please enter '1' or '2'.");
            }
            System.out.println("Is member competitive?");
            System.out.println("press 1 for competitive, press 2 for non competitive");
            int membershipInput = scanner.nextInt();
            if (membershipInput == 1) {
                membershipType = true;

            } else if (membershipInput == 2) {
                membershipType = false;
                break;
            } else {
                System.out.println("Invalid input. Please enter '1' or '2'.");
            }
            scanner.nextLine();


        }
        controllerMember.registerMember(name, address, birthday, memberID, email, membershipType, isActive);
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
            System.out.println("No members found matching the search criteria.");
        } else {
            System.out.println("Matching members:");

            for (int i = 0; i < matchingMembers.size(); i++) {
                Member member = matchingMembers.get(i);
                System.out.println(i + 1 + ". " + member.getName());
            }

            System.out.print("Enter the name of the member you want to edit: ");
            int selection = scanner.nextInt();

            if (selection >= 1 && selection <= matchingMembers.size()) {
                Member memberToEdit = matchingMembers.get(selection - 1);

                System.out.println("Current details of the member:");
                printMemberDetails(memberToEdit);
                scanner.nextLine();

                System.out.print("Enter the new name (press Enter to keep the current name): ");
                String newName = scanner.nextLine();
                if (!newName.trim().isEmpty()) {
                    memberToEdit.setName(newName);
                }

                System.out.print("Enter the new address (press Enter to keep the current address): ");
                String newAddress = scanner.nextLine();
                if (!newAddress.trim().isEmpty()) {
                    memberToEdit.setAddress(newAddress);
                }

                System.out.print("Enter the new age (press Enter to keep the current value): ");
                String newAgeInput = scanner.nextLine();
                if (!newAgeInput.isEmpty()) {
                    int newAge = Integer.parseInt(newAgeInput);
                    memberToEdit.setAge(newAge);
                }

                System.out.print("Enter the new member ID (press Enter to keep the current ID): ");
                String memberID = scanner.nextLine();
                if (!memberID.isEmpty()) {
                    int newID = Integer.parseInt(memberID);
                    memberToEdit.setMemberID(newID);
                }

                System.out.print("Enter the new email type (press Enter to keep the current email): ");
                String newEmail = scanner.nextLine();
                if (!newEmail.trim().isEmpty()) {
                    memberToEdit.setEmail(newEmail);
                }

                System.out.print("Enter the new membership type (press Enter to keep the current membership): ");
                String newMembershipType = scanner.nextLine().trim().toLowerCase();
                if (!newMembershipType.isEmpty()) {
                    boolean newMembership = newMembershipType.equals("ja");
                    memberToEdit.setCompetitiveSwimmer(newMembership);
                }

                System.out.print("Enter the new activity level (press Enter to keep the current activity level): ");
                String newActive = scanner.nextLine().trim().toLowerCase();
                if (!newActive.isEmpty()) {
                    boolean newisActive = newActive.equals("ja");
                    memberToEdit.setCompetitiveSwimmer(newisActive);
                }

                controllerMember.editMember(memberToEdit);
            }
        }
    }

    private void printMemberDetails(Member member) {
        controllerMember.IDCreation();
        System.out.println(controllerMember.getAge());
        System.out.println(member);

    }

    public void showMember() {
        ArrayList<Member> medlem = controllerMember.getAllMembers();

        for (Member member : medlem) {
            printMemberDetails(member);

        }

    }
        public void chairmanMenu() {
            while (true) {
                System.out.println("""
                        Velkommen til menuen for formanden.
                        1. Vis medlem
                        2. Opret medlem
                        3. Rediger medlem
                        4. Slet medlem
                        """);
                try {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); //bugfix

                    switch (choice) {
                        case 1 -> showMember();
                        case 2 -> registerMember();
                        case 3 -> editMember();
                        //case 4 -> removeMember();
                        case 5 -> controllerMember.IDCreation();
                        case 6 -> printSwimmers(controllerMember.juniorTeam());
                        case 7 -> printSwimmers(controllerMember.seniorTeam());
                    }
                } catch (Exception e) {
                    System.out.println("Der opstod en fejl: " + e.getMessage());
                    scanner.nextLine();
                }
            }
        }
    }
