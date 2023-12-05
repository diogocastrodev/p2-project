package abstracts;

public abstract class AbsDevice {
    private String ip;
    private String mac;

    public AbsDevice(String ip, String mac) {
        this.setIp(ip);
        this.setMac(mac);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        int[] ipParts = new int[4];
        String[] ipPartsStr = ip.split("\\.");
        for (int i = 0; i < ipPartsStr.length; i++) {
            ipParts[i] = Integer.parseInt(ipPartsStr[i]);
        }
        for (int i = 0; i < ipParts.length; i++) {
            if (ipParts[i] < 0 || ipParts[i] > 255) {
                throw new IllegalArgumentException("Invalid IP address");
            }
        }
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        // Verificar se o Mac Address é valido (e.g. 00:00:00:00:00:00 | 00-00-00-00-00-00 | 000000000000
        mac = mac.trim().toUpperCase(); // Tirar espaços e colocar em maiúsculo
        String[] macParts = null;
        if (mac.length() == 17) { // Mac Address com ":" ou "-"
            if (mac.contains("-")) { // Mac Address com "-"
                macParts = mac.split("-"); // Dividir em partes
            } else if (mac.contains(":")) { // Mac Address com ":"
                macParts = mac.split(":"); // Dividir em partes
            } else { // Inválido
                throw new IllegalArgumentException("Invalid MAC address");
            }
        } else if (mac.length() == 12) {
            // Mac Adress but without ":" or "-"
            mac.split("(?<=\\G..)");
        } else // Inválido
            throw new IllegalArgumentException("Invalid MAC address");

        if (macParts.length != 6) { // Se não tiver 6 partes
            throw new IllegalArgumentException("Invalid MAC address");
        }

        // Verificar se cada parte tem 2 caracteres
        for (int i = 0; i < macParts.length; i++) {
            if (macParts[i].length() != 2) { // Se não tiver 2 caracteres
                throw new IllegalArgumentException("Invalid MAC address"); // Inválido
            }
        }

        for (int i = 0; i < macParts.length; i++) { // Verificar se cada parte é hexadecimal
            for (int j = 0; j < macParts[i].length(); j++) { // Verificar cada caracter
                if (macParts[i].charAt(j) < '0' || macParts[i].charAt(j) > 'F') { // Se não for hexadecimal
                    throw new IllegalArgumentException("Invalid MAC address"); // Inválido
                }
            }
        }

        this.mac = mac;
    }

    @Override
    public String toString() {
        return "IP: " + this.getIp() + "\nMAC: " + this.getMac();
    }
}
