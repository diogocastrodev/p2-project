package abstracts;

/**
 * Abstract class for a common device
 */
public abstract class AbsDevice {
    /**
     * IP address of the device
     */
    private String ip;
    /**
     * MAC address of the device
     */
    private String mac;
    /**
     * Device connected to this device
     * This is used to create a network
     * Can be null
     */
    private AbsDevice connectedDevice;

    /**
     * Constructor
     * @param ip IP address
     * @param mac MAC address
     */
    public AbsDevice(String ip, String mac) {
        this.setIp(ip);
        this.setMac(mac);
    }

    /**
     * Constructor
     * @param ip IP address
     * @param mac MAC address
     * @param connectedDevice Device connected to this device
     */
    public AbsDevice(String ip, String mac, AbsDevice connectedDevice) {
        this.setIp(ip);
        this.setMac(mac);
        this.setConnectedDevice(connectedDevice);
    }

    /**
     * Get the device connected to this device
     * @return Device connected to this device
     */
    public AbsDevice getConnectedDevice() {
        return connectedDevice;
    }

    /**
     * Set the device connected to this device
     * @param connectedDevice Device connected to this device
     */
    public void setConnectedDevice(AbsDevice connectedDevice) {
        this.connectedDevice = connectedDevice;
    }

    /**
     * Get the IP address of the device
     * @return IP address of the device
     */
    public String getIp() {
        return ip;
    }

    /**
     * Set the IP address of the device (***.***.***.***)
     * @param ip IP address of the device
     */
    public void setIp(String ip) {
        int[] ipParts = new int[4]; // 4 partes do IP
        String[] ipPartsStr = ip.split("\\."); // Separar o IP a cada "."
        for (int i = 0; i < ipPartsStr.length; i++) { // Converter cada parte para inteiro
            ipParts[i] = Integer.parseInt(ipPartsStr[i]); // Converter para inteiro
        }
        for (int i = 0; i < ipParts.length; i++) { // Verificar se cada parte é válida
            if (ipParts[i] < 0 || ipParts[i] > 255) { // Se não for válido
                throw new IllegalArgumentException("Invalid IP address"); // Inválido
            }
        }
        this.ip = ip; // Válido
    }

    /**
     * Get the MAC address of the device
     * @return MAC address of the device
     */
    public String getMac() {
        return mac;
    }

    /**
     * Set the MAC address of the device (00:00:00:00:00:00 | 00-00-00-00-00-00 | 000000000000)
     * @param mac MAC address of the device
     */
    public void setMac(String mac) {
        // Verificar se o Mac Address é valido (e.g. 00:00:00:00:00:00 | 00-00-00-00-00-00 | 000000000000
        mac = mac.trim().toUpperCase(); // Tirar espaços e colocar em maiúsculo
        String[] macParts = null; // Partes do Mac Address
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

        for (int i = 0; i < macParts.length; i++) { // Verificar cada parte
            if (macParts[i].length() != 2) { // Se não tiver 2 caracteres (00)
                throw new IllegalArgumentException("Invalid MAC address"); // Inválido
            }
            for (int j = 0; j < macParts[i].length(); j++) { // Verificar cada caracter da parte
                if (macParts[i].charAt(j) < '0' || macParts[i].charAt(j) > 'F') { // Se não for hexadecimal
                    throw new IllegalArgumentException("Invalid MAC address"); // Inválido
                }
            }
        }
        mac = String.join(":", macParts);
        // Válido
        this.mac = mac;
    }

    /**
     * Get the device type
     * @return Device type
     */
    @Override
    public String toString() {
        return "IP: " + this.getIp() + "\nMAC: " + this.getMac();
    }
}
