package common;

import java.util.HashMap;
import java.util.Map;

public class MeritoConstant {

    public static final String FAIREXCHANGE = "fairexchange";
    public static final String SATSPORT = "satsport";
    public static final String FUNSPORT = "funsport";
    public static final String FAIRENTER = "fairenter";
    public static final String LASER365 = "laser365";
    public static final Map<String, String> MEMBER_URL_SUFFIX = new HashMap<String, String>() {
        {
            put("fairexchange", "/x");
            put("fairexchangeplus", "/plus");
            put("satsport", "/x");
            put("funsport", "/xch");
            put("fairenter", "/xch");
            put("laser365", "/plus");
            put("betclub", "/plus");
            put("fairexchange_plus", "/plus");
        }
    };
    public static final Map<String, String> AGENT_SECURITY_CODE_URL_SUFFIX = new HashMap<String, String>() {
        {
            put("fairexchange", "/agent/#/1/code");
            put("satsport", "/agent/#/4/code");
            put("funsport", "/agent/#/1/code");
            put("fairenter", "/agent/#/1/code");
            put("laser365", "/agent/#/1/code");
            put("betclub", "/agent/#/1/code");
        }
    };
    public static final Map<String, String> LOGIN_NEW_ACC_AGENT_URL_SUFFIX = new HashMap<String, String>() {
        {
            put("fairexchange", "/agent/#/1/update");
            put("satsport", "/agent/#/2/update");
            put("funsport", "/agent/#/1/update");
            put("laser365", "/agent/#/1/update");
            put("fairenter", "/agent/#/1/update");
            put("betclub", "/agent/#/1/update");
        }
    };
    public static final Map<String, String> APP_NAME = new HashMap<String, String>() {
        {
            put("fairexchange", "Fair Exchange");
            put("satsport", "SAT Sport");
            put("funsport", "FunSport101");
            put("fairenter", "Fairenter");
            put("laser365", "Laser365");
            put("backoffice", "Backoffice");
            put("betclub", "BetCub");
        }
    };
    public static final Map<String, String> MEMBER_CAMOUFLAGE_URL_SUFFIX = new HashMap<String, String>() {
        {
            put("alpicfox", "/alpicfox/#/home");
            put("atlantic", "/atlantic/#/home");
            put("crypto", "/forex/#/home");
            put("eu1010", "/plus/#/1/home/exchange/main/all");
            put("powderpin", "/landing/#/d");
        }
    };
    public static final String MEMBER_SOS_URL_SUFFIX = "/member-service/login/login";
    public static final String AGENT_SOS_URL_SUFFIX = "/agent/login/sos-authentication.sv";
    // qat use AGENT_SOS_URL_SUFFIX = agent-service/v1/api/login/login/sos-authentication.sv
    public static final String AGENT_SOS_BY_PASS_CAPTCHA_URL_SUFFIX = "/agent/code/validate.sv";
    public static final String AGENT_FOLLOW_BETS_URL_SUFFIX = "/agent-management-ui/#/home/follow-bet";
    public static final String BACKOFFICE_SOS_URL = "/system-manager/web/sv/login/sos-doLogin.sv";
    public static final String BACKOFFICE_DASHBOARD_URL = "/system-manager-ui/#/modules";
    public static final String CRYPTO_URL_SUFFIX = "/forex/#/own-bitcoin";
    public static final String ATLANTIC_URL_SUFFIX = "";
    public enum BetType {BACK, LAY}
    public enum Sports {SOCCER, BASKETBALL, TENNIS, CRICKET, OTHER, HORSERACING}


}
