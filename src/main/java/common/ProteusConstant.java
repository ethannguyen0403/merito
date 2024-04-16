package common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProteusConstant {
    // The value use for API and added to Header. Contact dev when this value is changed
    public static final String AUTHORIZATION_API = "Basic UFpBMDIwMDAwMTpuaWNlMjQzNTE=";
    public static final String EURO_VIEW = "Euro View";
    public static final String ASIAN_VIEW = "Asian View";
    public static final String ASIAN = "Asian";
    public static final String EURO = "Euro";
    public static final String DECIMAL = "Decimal";
    public static final String HONGKONG = "Hongkong";
    public static final String MALAY = "Malay";
    public static final String AMERICAN = "American";
    public static final String TEXT_MONEYLINE = "Moneyline";
    public static final String TEXT_HDP = "Handicap";
    public static final String TEXT_1X2 = "1X2";
    public static final String TEXT_OVER_UNDER = "Over Under";
    public static final String TEXT_MATCH_TOTAL = "Match - Team Totals";
    public static final String EARLY_PERIOD = "Early";
    public static final String LIVE_PERIOD = "Live";
    public static final String TODAY_PERIOD = "Today";
    public static final String ASIAN_DECIMAL_ODDS = "Decimal Odds";
    public static final String ASIAN_HONGKONG_ODDS = "Hong Kong Odds";
    public static final String ASIAN_MALAY_ODDS = "Malay Odds";
    public static final String ASIAN_AMERICAN_ODDS = "American Odds";
    public static final String PENDING_BETS_TAB = "PENDING BETS";
    public static final String BET_SLIP_TAB = "BET SLIP";
    public static final String PLACE_BET_BUTTON_TEXT = "PLACE %d BET";
    public static final String SOCCER = "Soccer";
    public static final String TENNIS = "Tennis";
    public static final String PREGAME = "Pregame";
    public static final String INPLAY = "InPlay";
    public static final String NO_RECORDS_FOUND = "No records found.";
    public static final String MIN_STAKE_ERROR_MSG = "Your stake cannot be lower than the minimum bet. It has been automatically adjusted to the minimum bet amount.";
    public static final String MAX_STAKE_ERROR_MSG = "Your stake cannot be greater than the maximum bet. It has been automatically adjusted to the maximum bet amount.";
    public static final String MAX_SELECTIONS_MSG = "Maximum is 10 selections.";
    public static final String REMOVE_ALL_MSG = "Do you want to empty your Bet Slip?";
    public static final String BETSLIP_NO_BETS_MSG = "No bets selected yet.";
    public static final String BETSLIP_CLICK_ODDS_MSG = "Click on the respective odds to place a new bet.";
    public static final String BETSLIP_OVER_BALANCE_MSG = "Insufficient balance for placing bet! Order ID:";
    public static final String CONFIRM_PLACE_BET_MSG = "Are you sure you want to risk %s %s to win %s %s?";
    public static final Map<String, String> ODDS_GROUP_ADJUSTMENT_MAPPING = new HashMap<String, String>() {
        {
            put("B", "0.28");
            put("C", "0.56");
            put("D", "0.84");
            put("E", "2.00");
            put("A", "0.00");
        }
    };
    public static final Map<String, String> ODDS_SIGN_MAPPING = new HashMap<String, String>() {
        {
            put("DECIMAL", "D");
            put("AMERICAN", "A");
            put("HONGKONG", "H");
            put("MALAY", "M");
        }
    };

    public static final Map<String, String> MARKET_TYPE_MAPPING = new HashMap<String, String>() {
        {
            put("Handicap", "SPREAD");
            put("1X2", "MONEYLINE");
            put("Over Under", "TOTAL_POINTS");
            put("Match - Team Totals", "TEAM_TOTAL_POINTS");
        }
    };

    public static final Map<String, String> MARKET_CODE_MAPPING = new HashMap<String, String>() {
        {
            put("SPREAD", "Handicap");
            put("MONEYLINE", "Moneyline");
            put("TOTAL_POINTS", "Over Under");
        }
    };

    public static final Map<String, String> ODDS_TYPE_LABEL_MAPPING = new HashMap<String, String>() {
        {
            put("Decimal", ".00 Odds");
            put("Hongkong", "HK Odds");
            put("Malay", "MY Odds");
            put("American", "AM Odds");
            put("Indo", "ID");
        }
    };

    public static final Map<String, String> ODDS_TYPE_MAPPING = new HashMap<String, String>() {
        {
            put("Decimal", "DEC");
            put("Hongkong", "HK");
            put("Malay", "MY");
            put("American", "AM");
            put("Indo", "ID");
        }
    };
    public static final Map<String, String> SPORTBOOK_SPORT_ID = new HashMap<String, String>() {
        {
            put("SOCCER", "29");
            put("CRICKET", "4");
            put("TENNIS", "2");
        }
    };
}
