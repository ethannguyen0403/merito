package objects;

public class Environment {
    public static String satURL;
    public static String satCashURL;
    public static String funsportURL;
    public static String fairenterURL;
    public static String laser365URL;
    public static String betclubURL;
    public static String backofficeURL;
    public static String domainULR;
    public static String directusURL;
    private String fairURL;
    public String fairCashURL;
    private String securityCode;
    private String apiURL;
    private String proteusAPIProviderDomain;
    private String proteusProviderAPIURL;
    public static String cryptoURL;
    public static String atlanticURL;

    public String getproteusAPIProviderDomain() {
        return proteusAPIProviderDomain;
    }

    public void setProteusAPIProviderDomain(String proteusAPIDomain) {
        this.proteusAPIProviderDomain = proteusAPIDomain;
    }

    public static String getSatURL() {
        return satURL;
    }

    public static void setSatURL(String satURL) {
        Environment.satURL = satURL;
    }

    public String getSatCashURL() {
        return satCashURL;
    }

    public void setSatCashURL(String satCashURL) {
        Environment.satCashURL = satCashURL;
    }

    public static String getFunsportURL() {
        return funsportURL;
    }

    public static void setFunsportURL(String funsportURL) {
        Environment.funsportURL = funsportURL;
    }

    public static String getFairenterURL() {
        return fairenterURL;
    }

    public static void setFairenterURL(String fairenterURL) {
        Environment.fairenterURL = fairenterURL;
    }

    public static String getLaser365URL() {
        return laser365URL;
    }

    public static void setLaser365URL(String laser365URL) {
        Environment.laser365URL = laser365URL;
    }

    public static String getBetclubURL() {
        return betclubURL;
    }

    public static void setBetclubURL(String betclubURL) {
        Environment.betclubURL = betclubURL;
    }

    public String getDomainURL() {
        return domainULR;
    }

    public void setDomainURL(String url) {
        this.domainULR = url;
    }

    public String getFairURL() {
        return fairURL;
    }

    public void setFairURL(String fairURL) {
        this.fairURL = fairURL;
    }
    public String getFairCashURL() {
        return fairCashURL;
    }

    public void setFairCashURL(String fairCashURL) {
        this.fairCashURL = fairCashURL;
    }

    public String getBackofficeURL() {
        return backofficeURL;
    }

    public void setBackofficeURL(String backofficeURL) {
        this.backofficeURL = backofficeURL;
    }

    public String getApiURL() {
        return apiURL;
    }

    public void setApiURL(String apiURL) {
        this.apiURL = apiURL;
    }

    public String getDirectusURL() {
        return directusURL;
    }

    public void setDirectusURL(String val) {
        directusURL = val;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String val) {
        this.securityCode = val;
    }
    public String getProteusProviderAPIURL() {
        return proteusProviderAPIURL;
    }

    public String getCryptoURL() {
        return cryptoURL;
    }
    public static void setCryptoURL(String cryptoURL) {
        Environment.cryptoURL = cryptoURL;
    }
    public String getAtlanticURL() {
        return atlanticURL;
    }
    public static void setAtlanticURL(String atlanticURL) {
        Environment.atlanticURL = atlanticURL;
    }

    public void setProteusProviderAPIURL(String proteusProviderAPIURL) {
        this.proteusProviderAPIURL = proteusProviderAPIURL;
    }

}
