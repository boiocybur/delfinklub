package ui;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    UIChairman uichairman = new UIChairman();
    UICashier uicashier = new UICashier();
    UICoach uicoach = new UICoach();

    public UserInterface() throws IOException {
    }


    public void startProgram() {
        while (true) {
            System.out.println("""
                    Velkommen til Delfinen.
                    1. Formand
                    2. Kasserer
                    3. Træner
                    0. Sluk programmet
                    """);


            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Removes newline after nextInt()

                switch (choice) {
                    case 1 -> uichairman.chairmanMenu();
                    case 2 -> uicashier.cashierMenu();
                    case 3 -> uicoach.coachMenu();
                    case 0 -> {
                        System.out.println("Slukker programmet!");
                        System.exit(0);
                    }
                }
            } catch (InputMismatchException ime) {
                System.out.println("Ugyldig input. Indtast venligst et gyldigt heltal.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }
}
