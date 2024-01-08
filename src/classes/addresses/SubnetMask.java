package classes.addresses;

import java.io.Serializable;

public class SubnetMask implements Serializable {

    private IP subnetMask;

    public SubnetMask(IP ip) {
        this.setSubnetMask(ip);
    }

    public IP getSubnetMask() {
        return subnetMask;
    }

    public void setSubnetMask(IP subnetMask) {

        this.subnetMask = subnetMask;
    }

    public IP getNetworkAddress(IP ip) {
        String[] ipParts = ip.getIP().split("\\.");
        String[] subnetMaskParts = this.subnetMask.getIP().split("\\.");
        String networkAddress = "";
        for (int i = 0; i < ipParts.length; i++) {
            int ipPart = Integer.parseInt(ipParts[i]);
            int subnetMaskPart = Integer.parseInt(subnetMaskParts[i]);
            int networkAddressPart = ipPart & subnetMaskPart;
            networkAddress += networkAddressPart + ".";
        }
        networkAddress = networkAddress.substring(0, networkAddress.length() - 1);
        try {
            return new IP(networkAddress);
        } catch (Exception e) {
            return null;
        }
    }

    public IP generateRandomIPInsideMask(IP initial, IP end) {
        String[] initialParts = initial.getIP().split("\\.");
        String[] endParts = end.getIP().split("\\.");
        String[] subnetMaskParts = this.subnetMask.getIP().split("\\.");
        String randomIP = "";
        for (int i = 0; i < initialParts.length; i++) {
            int initialPart = Integer.parseInt(initialParts[i]);
            int endPart = Integer.parseInt(endParts[i]);
            int subnetMaskPart = Integer.parseInt(subnetMaskParts[i]);
            int randomIPPart = (int) (Math.random() * (endPart - initialPart + 1)) + initialPart;
            randomIP += randomIPPart + ".";
        }
        randomIP = randomIP.substring(0, randomIP.length() - 1);
        try {
            return new IP(randomIP);
        } catch (Exception e) {
            return null;
        }
    }
}
