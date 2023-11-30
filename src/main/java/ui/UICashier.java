package ui;

import cashier.ControllerFinance;
import java.util.Scanner;

public class UICashier {
    Scanner scanner = new Scanner(System.in);
    private ControllerFinance controllerFinance;

    public UICashier(){
        this.controllerFinance = new ControllerFinance();
    }
    public void cashierMenu() {
        while (true) {
            System.out.println("""
                    Velkommen til menuen for formanden.
                    1. Vis medlemmers kontingenter
                    2. Vise medlemmers restance
                    3. Vis årlig total kontingent indbetaling
                    4. Ændrer junior kontingent
                    5. Ændrer senior kontingent
                    5. Ændrer ældre kontingent
                    7. Ændrer passivt kontingent
                    """);
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); //bugfix

                switch (choice) {
                    //case 1 -> controllerFinance.memberContingent();
                    //case 2 ->
                    //case 3 -> controllerFinance.totalContingent();
                }
            } catch (Exception e) {
                System.out.println("Der opstod en fejl: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
}
