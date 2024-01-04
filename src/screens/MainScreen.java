package screens;

import abstracts.AbsScreen;
import cache.DevicesCache;
import classes.exceptions.InvalidOptionException;
import screens.create.CreateDeviceScreen;
import screens.list.ListDevicesScreen;
import screens.select.SelectDeviceScreen;

public class MainScreen extends AbsScreen {

    public void drawMenu() {
        System.out.println("1. Criar uma novo dispositivo");
        System.out.println("2. Selecionar um dispositivo");
        System.out.println("3. Listar dispositivos");
        System.out.println("4. Mostrar último log guardado");
        System.out.println("0. Sair");
    }

    @Override
    public void handleOption(int option) throws InvalidOptionException {
        switch (option) {
            case 1:
                new CreateDeviceScreen();
                break;
            case 2:
                if (new DevicesCache().size() == 0) {
                    System.out.println("Não existem dispositivos criados!");
                    break;
                }
                new SelectDeviceScreen();
                break;
            case 3:
                if (new DevicesCache().size() == 0) {
                    System.out.println("Não existem dispositivos criados!");
                    break;
                }
                new ListDevicesScreen();
                break;
            case 4:
                //
                break;
            case 0:
                // System.exit(0); -> This causes error in IntelliJ
                break;
            default:
                throw new InvalidOptionException();
        }
    }
}
