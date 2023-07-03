package api.common;
public class B2BAPIConstant {
    public static final String HEADER_FORM_URLENCODED_NONUTF8 = "application/x-www-form-urlencoded";
    public static String TOKEN_URL ="mwl-api/agent/token";
    public static String AUTHORIZATION_URL ="mwl-api/sandbox/authorization";
    public static String ENCRYPT_URL ="mwl-api/sandbox/encrypt";
    public static String LOGIN_URL ="mwl-api/player/login";
    public static String LOGOUT_URL ="mwl-api/player/logout";
    public static String PLAYER_INFO_URL ="mwl-api/player/info";
    public static String PLAYER_LIST_INFO_URL = "mwl-api/player/info/list";
    public static String PLAYER_STATUS_UPDATE ="mwl-api/player/status/update";
    public static String ADJUST_BALANCE_URL = "mwl-api/player/balance/adjust";
    public static String GET_SETTLED_WAGER_URL = "mwl-api/report/wagers";
    public static String GET_MATCHED_WAGER_URL = "mwl-api/report/orders";

    public static class LoginPage {
        public final static String LOGIN = "Login";
    }

}
