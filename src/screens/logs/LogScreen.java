package screens.logs;

import abstracts.AbsScreen;
import classes.exceptions.InvalidOptionException;
import classes.logger.Logger;

import java.util.List;

public class LogScreen extends AbsScreen {

    @Override
    public void drawMenu() {
        System.out.println("1. Mostrar último log guardado");
        System.out.println("2. Abrir último log guardado");
        System.out.println("3. Abrir a pasta dos logs");
        System.out.println("0. Voltar");
    }

    public LogScreen() {
        this.screen(true);
    }

    @Override
    public void handleOption(int option) throws InvalidOptionException {
        switch (option) {
            case 1:
                Logger l = new Logger();
                try {
                    List<String> logs = l.getLastLogLines();
                    if (logs.size() == 0) {
                        new Exception("Não existem logs guardados!");
                        break;
                    }
                    for (String log : logs) {
                        System.out.println(log);
                    }
                } catch (Exception e) {
                    System.out.println("Não existem logs guardados!");
                }
                super.pressEnterToContinue();
                clearScreen();
                break;
            case 2:
                try {
                    new Logger().openLastLogFile();
                } catch (Exception e) {
                    System.out.println("Não existem logs guardados!");
                }
                break;
            case 3:
                // Open folder
                new Logger().openLogFolder();
                break;
            case 0:
                // System.exit(0); -> This causes error in IntelliJ
                break;
            default:
                throw new InvalidOptionException();
        }
    }
}
