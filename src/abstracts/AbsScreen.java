package abstracts;

import classes.exceptions.InvalidOptionException;
import enums.Status;

import java.util.Scanner;

public abstract class AbsScreen {

    /**
     * Draws the menu of the screen.
     */
    public abstract void drawMenu();

    /**
     * Draws the header of the screen.
     */
    public void drawHeader() {
        System.out.println("-~-x-~- MENU -~-x-~-");
    }

    /**
     * Constructor for AbsScreen
     */
    public AbsScreen() {
        while (true) {
            clearScreen();
            this.drawHeader();
            this.drawMenu();
            Scanner scanner = new Scanner(System.in);
            System.out.println("");
            String option = scanner.nextLine();
            int optionInt = 0;
            try {
                optionInt = Integer.parseInt(option);
                this.handleOption(optionInt);
            } catch (Exception e) {
                System.out.println("Opção inválida");
            }
            if (optionInt == 0) {
                break;
            }
        }
    }

    /**
     * Handles the option selected by the user.
     * @param option the option selected by the user
     */
    public abstract void handleOption(int option) throws InvalidOptionException;

    /**
     * Converts a status to an emoji of the status
     * @param status the status to convert
     * @return the emoji of the status
     */
    public static String convertStatus (Status status) {
        switch (status) {
            case UP:
                return "🟢";
            case DOWN:
                return "⚫️";
            case ERROR:
            default:
                return "🔴";
        }
    }

    /**
     * Clears the screen.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
