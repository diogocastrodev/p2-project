package screens.list;

import abstracts.AbsScreen;

public class ListDevicesScreen extends AbsScreen {

    @Override
    public void drawMenu() {
        System.out.println("1. Listar dispositivos únicos");
        System.out.println("2. Listar dispositivos formato rede");
        System.out.println("0. Voltar");
    }

    @Override
    public void handleOption(int option) {
        switch (option) {
            case 1:
                // this.listDevices();
                break;
            case 2:
                // this.listPackets();
                break;
            case 0:
                // this.goBack();
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }
}
