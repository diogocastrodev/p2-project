package classes.addresses;

import classes.exceptions.InvalidArgumentException;

public class IP {
    /**
     * IP Address
     */
    private String ip;

    /**
     * IP Constructor
     * @param ip String IP
     * @throws InvalidArgumentException Invalid IP structure
     */
    public IP(String ip) throws InvalidArgumentException {
        this.setIP(ip);
    }

    /**
     * Get IP
     * @return String IP
     */
    public String getIP() {
        return this.ip;
    }

    /**
     * Set IP
     * @param ip String IP
     * @throws InvalidArgumentException Invalid IP structure
     */
    private void setIP(String ip) throws InvalidArgumentException {
        int[] ipParts = new int[4]; // 4 partes do IP
        String[] ipPartsStr = ip.split("\\."); // Separar o IP a cada "."
        for (int i = 0; i < ipPartsStr.length; i++) { // Converter cada parte para inteiro
            ipParts[i] = Integer.parseInt(ipPartsStr[i]); // Converter para inteiro
        }
        for (int i = 0; i < ipParts.length; i++) { // Verificar se cada parte é válida
            if (ipParts[i] < 0 || ipParts[i] > 255) { // Se não for válido
                throw new InvalidArgumentException("Endereço IP inválido", "IP"); // Inválido
            }
        }
        this.ip = ip; // Válido
    }

    /**
     * Parse IP to String Array
     * @return String[] IP
     */
    public String[] parseIP() {
        return this.getIP().split("\\.");
    }

    /**
     * Parse IP to integer Array
     * @return int[] IP
     */
    public int[] parseIPInteger() {
        String[] ipPartsStr = this.getIP().split("\\.");
        int[] ipParts = new int[4];
        for (int i = 0; i < ipPartsStr.length; i++) {
            ipParts[i] = Integer.parseInt(ipPartsStr[i]);
        }
        return ipParts;
    }

    /**
     *  IP to String
     * @return String IP
     */
    @Override
    public String toString() {
        return this.getIP();
    }
}
