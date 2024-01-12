package abstracts;

import classes.exceptions.InvalidOptionException;
import enums.Status;

import java.util.Scanner;

public abstract class AbsScreen {

    public static final Scanner scanner = new Scanner(System.in);

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
    }

    public void screen(boolean header) {
        while (true) {
            clearScreen();
            if (header) {
                this.drawHeader();
            }
            this.drawMenu();
            Scanner scanner = new Scanner(System.in);
            // FLush the buffer
            System.out.println("");
            String option = scanner.next();
            if (option.equals("")) {
                scanner.nextLine();
            }
            int optionInt = 0;
            try {
                optionInt = Integer.parseInt(option);
                this.handleOption(optionInt);
            } catch (Exception e) {
                optionInt = -1;
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
    public String convertStatus (Status status) {
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
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Press ENTER to continue.
     */
    public void pressEnterToContinue() {
        System.out.println("Pressione ENTER para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
