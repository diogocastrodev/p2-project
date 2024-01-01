package test_tools;

/**
 * Class with tools to execute some tests (Generators, etc)
 */
public class TestTools {

    /*-----------------------------------------------------------------------*/
    /*--------------------------------- IP ----------------------------------*/
    /*-----------------------------------------------------------------------*/
    /**
     * Generates a random IP address
     * @return IP (***.***.***.***)
     */
    public String generateIP() {
        String ip = "";
        for (int i = 0; i < 4; i++) {
            String octet = generateOctet();
            while (octet.equals("0")) {
                octet = generateOctet();
            }
            ip += octet;
            if (i < 3) {
                ip += ".";
            }
        }
        return ip;
    }

    /**
     * Generates a random IP address
     * @param v1 First octet
     * @return IP (v1.***.***.***)
     */
    public String generateIP(int v1) {
        String ip = String.valueOf(v1) + ".";

        for (int i = 0; i < 3; i++) {
            ip += generateOctet();
            if (i < 2) {
                ip += ".";
            }
        }

        return ip;
    }

    /**
     * Generates a random IP address
     * @param v1 First octet
     * @param v2 Second octet
     * @return IP (v1.v2.***.***)
     */
    public String generateIP(int v1, int v2) {
        String ip = String.valueOf(v1) + "." + String.valueOf(v2) + ".";

        for (int i = 0; i < 2; i++) {
            ip += generateOctet();
            if (i < 1) {
                ip += ".";
            }
        }

        return ip;
    }

    /**
     * Generates a random IP address
     * @param v1 First octet
     * @param v2 Second octet
     * @param v3 Third octet
     * @return IP (v1.v2.v3.***)
     */
    public String generateIP(int v1, int v2, int v3) {
        String ip = String.valueOf(v1) + "." + String.valueOf(v2) + "." + String.valueOf(v3) + ".";

        ip += generateOctet();

        return ip;
    }

    /**
     * Generates an octet (0-255)
     * @return Octet
     */
    public String generateOctet() {
        int octet = (int) (Math.random() * 256); // 0 - 255
        return String.valueOf(octet);
    }


    /*-----------------------------------------------------------------------*/
    /*-------------------------------- MAC ----------------------------------*/
    /*-----------------------------------------------------------------------*/
    /**
     * Generates a random Mac address
     * @return MAC (**:**:**:**:**:**)
     */
    public String generateMac() {
        String mac = "";
        for (int i = 0; i < 6; i++) {
            mac += generateHexPair();
            if (i < 5) {
                mac += ":";
            }
        }
        return mac;
    }

    /**
     * Generates a Pair of Hex digits
     * @return Hex Pair (00-FF)
     */
    private String generateHexPair() {
        String hex = "";
        for (int i = 0; i < 2; i++) {
            hex += generateHexDigit();
        }
        return hex;
    }

    /**
     * Generates a Hex digit
     * @return Hex digit (0-F)
     */
    private String generateHexDigit() {
        int digit = (int) (Math.random() * 16);
        if (digit < 10) {
            return String.valueOf(digit);
        } else {
            switch (digit) {
                case 10:
                    return "A";
                case 11:
                    return "B";
                case 12:
                    return "C";
                case 13:
                    return "D";
                case 14:
                    return "E";
                default:
                    return "F";
            }
        }
    }
}
