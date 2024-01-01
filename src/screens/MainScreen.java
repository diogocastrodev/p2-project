package screens;

import abstracts.AbsScreen;
import classes.exceptions.InvalidOptionException;

public class MainScreen extends AbsScreen {

    public void drawMenu() {
        System.out.println("1. Criar uma nova rede");
        System.out.println("2. Selecionar uma rede");
        System.out.println("3. Listar redes existentes");
        System.out.println("0. Sair");
    }

    @Override
    public void handleOption(int option) throws InvalidOptionException {
        switch (option) {
            case 1:
                // this.createNewNetwork();
                break;
            case 2:
                // this.selectNetwork();
                break;
            case 3:
                // this.listNetworks();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                throw new InvalidOptionException();
        }
    }
}
