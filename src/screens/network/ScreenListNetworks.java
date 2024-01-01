package screens.network;

import abstracts.AbsScreen;
import classes.exceptions.InvalidOptionException;

public class ScreenListNetworks extends AbsScreen {

    /**
     * Constructor for AbsScreen
     */
    public ScreenListNetworks() throws InvalidOptionException {
    }

    @Override
    public void drawMenu() {
        System.out.println("1 - Criar um dispostivo");
        System.out.println("2 - Listar dispostivos");
        System.out.println("3 - Aceder a um dispostivo");
        System.out.println("0 - Sair"); 
    }

    @Override
    public void handleOption(int option) {
        switch(option) {
            case 1:
                    break;
            case 2:
                    break;
            case 3:
                    break;
            case 0:
                    break;
            default:
                System.out.println(" Opção inválida");
        }

    }


}
