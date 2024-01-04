package screens.create;

import abstracts.AbsScreen;
import classes.exceptions.InvalidOptionException;

public class CreateDeviceScreen extends AbsScreen {

    @Override
    public void drawMenu() {
        System.out.println("1 - Criar um Dispositivo de fim (Computador)");
        System.out.println("2 - Router");
        System.out.println("3 - Switch");
        System.out.println("4 - Hub");
        System.out.println("0 - Sair"); 
    }

    @Override
    public void handleOption(int option) throws InvalidOptionException {
        switch(option) {
            case 1:
                    break;
            case 2:
                    break;
            case 3:
                    break;
            case 4:
                    break;
            case 0:
                    break;
            default:
                throw new InvalidOptionException();
        }

    }


}
