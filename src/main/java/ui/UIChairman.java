package ui;

import java.util.ArrayList;
import java.util.Scanner;

import member.Member;
import member.ControllerMember;

public class UIChairman {
    Scanner scanner = new Scanner(System.in);
    private ControllerMember controllerMember;

    public UIChairman(){
        this.controllerMember = new ControllerMember();
    }

    public void registerMember (){
        System.out.print("Name of member: ");
        String name = scanner.nextLine();
        System.out.print("Address of member: ");
        String address = scanner.nextLine();
        System.out.println("Age of member: ");
        int age = scanner.nextInt();
        System.out.println("ID of member: ");
        int memberID = scanner.nextInt();
        System.out.println("Email of member: ");
        String email = scanner.nextLine();
        System.out.println("Membership type of member: ");
        boolean membershipType = false;
        boolean isActive = false;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Is member casual or competitive): ");
            String membershipInput = scanner.nextLine().trim().toLowerCase();
            if (membershipInput.equals("motionist")) {
                membershipType = true;
                break;
            } else if (membershipInput.equals("konkurrencesvømmer")) {
                membershipType = false;
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
            System.out.println("Would you like to be an active member?");
            String activeInput = scanner.nextLine().trim().toLowerCase();
            if (activeInput.equals("yes")) {
                isActive = true;
                break;
            } else if (activeInput.equals("no")) {
                isActive = false;
                break;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
        controllerMember.registerMember(name, address, age, memberID, email, membershipType, isActive);
    }

    public void editMember() {
        String partialSearchCriteria = scanner.nextLine();

        ArrayList<Member> matchingMembers = new ArrayList<>();
        for (Member members : controllerMember.getAllMembers()) {
            if (members.getName().toLowerCase().contains(partialSearchCriteria.toLowerCase())){
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

                System.out.print("Enter the new alias (press Enter to keep the current alias): ");
                String newAddress = scanner.nextLine();
                if (!newAddress.trim().isEmpty()) {
                    memberToEdit.setAddress(newAddress);
                }

                System.out.print("Is the superhero human? (ja/nej) (press Enter to keep the current value): ");
                String newAgeInput = scanner.nextLine();
                if (!newAgeInput.isEmpty()) {
                    int newAge = Integer.parseInt(newAgeInput);
                    memberToEdit.setAge(newAge);
                }

                System.out.print("Enter the new creation year (press Enter to keep the current year): ");
                String memberID = scanner.nextLine();
                if (!memberID.isEmpty()) {
                    int newID = Integer.parseInt(memberID);
                    memberToEdit.setMemberID(newID);
                }

                System.out.print("Enter the new superpower (press Enter to keep the current superpower): ");
                String newEmail = scanner.nextLine();
                if (!newEmail.trim().isEmpty()) {
                    memberToEdit.setEmail(newEmail);
                }

                System.out.print("Enter the new strength [0-100] (press Enter to keep the current strength): ");
                String newMembershipType = scanner.nextLine().trim().toLowerCase();
                if (!newMembershipType.isEmpty()) {
                    boolean newMembership = newMembershipType.equals("ja");
                    memberToEdit.setMembershipType(newMembership);
                }

                System.out.print("Enter the new strength [0-100] (press Enter to keep the current strength): ");
                String newActive = scanner.nextLine().trim().toLowerCase();
                if (!newActive.isEmpty()) {
                    boolean newisActive = newActive.equals("ja");
                    memberToEdit.setMembershipType(newisActive);
                }

                controllerMember.editMember(memberToEdit);
            }
        }
    }
    private void printMemberDetails(Member member){
        System.out.println("Name: " + member.getName());
        System.out.println("Address: " + member.getAddress());
        System.out.println("Age: " + (member.getAge()));
        System.out.println("Member ID: " + member.getMemberID());
        System.out.println("Membership type: " + member.isMembershipType());
        System.out.println("Active: " + member.isActive());
        System.out.println("----------------------");
    }

    public void chairmanMenu(){
        while (true){
            System.out.println("""
                    Velkommen til menuen for formanden.
                    1. Opret medlem
                    2. Rediger medlem
                    3. Slet medlem
                    """);
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); //bugfix

                switch (choice) {
                    case 1 -> registerMember();
                    case 2 -> editMember();
                    //case 3 -> removeMember();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}