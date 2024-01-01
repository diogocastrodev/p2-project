package classes.addresses;

import classes.exceptions.InvalidArgumentException;

public class Mac {

    /**
     * Mac Address
     */
    private String mac;

    /**
     * Mac Constructor
     * @param mac String Mac Address
     * @throws InvalidArgumentException Invalid Mac Address structure
     */
    public Mac(String mac) throws InvalidArgumentException {
        this.setMac(mac);
    }

    /**
     * Get Mac Address
     * @return String Mac Address
     */
    public String getMac() {
        return this.mac;
    }

    /**
     * Set Mac Address
     * @param mac String Mac Address
     * @throws InvalidArgumentException Invalid Mac Address structure
     */
    private void setMac(String mac) throws InvalidArgumentException {
        // Verificar se o Mac Address é valido (e.g. 00:00:00:00:00:00 | 00-00-00-00-00-00 | 000000000000
        mac = mac.trim().toUpperCase(); // Tirar espaços e colocar em maiúsculo
        String[] macParts = null; // Partes do Mac Address
        if (mac.length() == 17) { // Mac Address com ":" ou "-"
            if (mac.contains("-")) { // Mac Address com "-"
                macParts = mac.split("-"); // Dividir em partes
            } else if (mac.contains(":")) { // Mac Address com ":"
                macParts = mac.split(":"); // Dividir em partes
            } else { // Inválido
                throw new InvalidArgumentException("Inválido", "Mac"); // Inválido
            }
        } else if (mac.length() == 12) {
            // Mac Adress but without ":" or "-"
            mac.split("(?<=\\G..)"); // Dividir em partes de 2 em 2
        } else // Inválido
            throw new InvalidArgumentException("Inválido", "Mac"); // Inválido

        if (macParts.length != 6) { // Se não tiver 6 partes
            throw new InvalidArgumentException("Mal estruturado", "Mac"); // Inválido
        }

        for (int i = 0; i < macParts.length; i++) { // Verificar cada parte
            if (macParts[i].length() != 2) { // Se não tiver 2 caracteres (00)
                throw new InvalidArgumentException("Tamanho inválido", "Mac"); // Inválido
            }
            for (int j = 0; j < macParts[i].length(); j++) { // Verificar cada caracter da parte
                if (macParts[i].charAt(j) < '0' || macParts[i].charAt(j) > 'F') { // Se não for hexadecimal
                    throw new InvalidArgumentException("Inválido", "Mac"); // Inválido
                }
            }
        }
        // Forçar padrão 00:00:00:00:00:00 no programa
        mac = String.join(":", macParts); // Para cada parte, adicionar ":"
        // Válido
        this.mac = mac;
    }

    /**
     * Parse Mac Address to String Array
     * @return String[] Mac Address
     */
    public String[] parseMac() {
        return this.getMac().split(":"); // Dividir em partes a cada ":"
    }

    /**
     * Mac Address
     * @return String Mac Address
     */
    @Override
    public String toString() {
        return this.getMac();
    }
}
