package classes.addresses;

import classes.exceptions.InvalidArgumentException;

public class IP {
    /**
     * IP Address
     */
    private String ip;

    private int port;

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
        String[] ipPartsStr = ip.trim().replace(" ", "").split("\\."); // Separar o IP a cada "."
        if (ipPartsStr.length != 4) { // Se não tiver 4 partes
            throw new InvalidArgumentException("Endereço IP inválido", "IP"); // Inválido
        }
        if (ipPartsStr[4].contains(":")) { // Se tiver ":" (porta)
            String[] ipPartsStrPort = ipPartsStr[4].split(":"); // Separar o IP a cada ":"
            ipPartsStr[4] = ipPartsStrPort[0]; // IP sem a porta
            try {
                this.setPort(Integer.parseInt(ipPartsStrPort[1]));
            } catch (Exception e) {
                throw new InvalidArgumentException("Porta inválida", "IP");
            }
        }
        for (int i = 0; i < ipPartsStr.length; i++) { // Converter cada parte para inteiro
            ipParts[i] = Integer.parseInt(ipPartsStr[i]); // Converter para inteiro
        }
        if (ipParts[0] == 0) { // Se a primeira parte for 0
            throw new InvalidArgumentException("Endereço IP inválido", "IP"); // Inválido
        }
        for (int i = 0; i < ipParts.length; i++) { // Verificar se cada parte é válida
            if (ipParts[i] < 0 || ipParts[i] > 255) { // Se não for válido
                throw new InvalidArgumentException("Endereço IP inválido", "IP"); // Inválido
            }
        }
        this.ip = ip; // Válido
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) throws InvalidArgumentException {
        if (port < 0 || port > 65535) {
            throw new InvalidArgumentException("Porta inválida", "IP");
        }
        this.port = port;
    }

    /**
     * Parse IP to String Array
     * @return String[] IP
     */
    public String[] parseIP() {
        return this.getIP().split("\\."); // Separar o IP a cada "."
    }

    /**
     * Parse IP to integer Array
     * @return int[] IP
     */
    public int[] parseIPInteger() {
        String[] ipPartsStr = this.getIP().split("\\."); // Separar o IP a cada "."
        int[] ipParts = new int[4]; // Array de 4 partes do IP
        for (int i = 0; i < ipPartsStr.length; i++) { // Converter cada parte para inteiro
            ipParts[i] = Integer.parseInt(ipPartsStr[i]); // Converter para inteiro
        }
        return ipParts; // IP em partes inteiras
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
