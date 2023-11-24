package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    UIChairman uichairman = new UIChairman();
    UICashier uicashier = new UICashier();
    UICoach uicoach = new UICoach();


    public void startProgram() {
        while (true) {
            System.out.println("""
                    Velkommen til Delfinen.
                    1. Formand
                    2. Kasserer
                    3. Træner
                    """);


            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Removes newline after nextInt()

                switch (choice) {
                    case 1 -> uichairman.chairmanMenu();
                    case 2 -> uicashier.cashierMenu();
                    case 3 -> uicoach.coachMenu();
                }
            } catch (InputMismatchException ime) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
