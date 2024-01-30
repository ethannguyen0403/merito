package common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProteusConstant {
    public static final String EURO_VIEW = "Euro View";
    public static final String ASIAN_VIEW = "Asian View";
    public static final String DECIMAL = " Decimal ";
    public static final String HONGKONG = " Hongkong ";
    public static final String MALAY = " Malay ";
    public static final String AMERICAN = " American ";
    public static final String TEXT_HDP = "Handicap";
    public static final String TEXT_1X2 = "1X2";
    public static final String TEXT_OVER_UNDER = "Over Under";
    public static final String TEXT_MATCH_TOTAL = "Match - Team Totals";
    public static final String EARLY_PERIOD = "Early";
    public static final String LIVE_PERIOD = "Live";
    public static final String TODAY_PERIOD = "Today";
    public static final String ASIAN_DECIMAL_ODDS = " Decimal Odds ";
    public static final String ASIAN_HONGKONG_ODDS = " Hong Kong Odds ";
    public static final String ASIAN_MALAY_ODDS = " Malay Odds ";
    public static final String ASIAN_AMERICAN_ODDS = " American Odds ";
    public static final String PENDING_BETS_TAB = "PENDING BETS";
    public static final String BET_SLIP_TAB = "BET SLIP";
    public static final String NO_RECORDS_FOUND = "No records found.";
    public static final String MIN_STAKE_ERROR_MSG = "Your stake cannot be lower than the minimum bet. It has been automatically adjusted to the minimum bet amount.";
    public static final String MAX_STAKE_ERROR_MSG = "Your stake cannot be greater than the maximum bet. It has been automatically adjusted to the maximum bet amount.";
    public static final Map<String, String> ODDS_GROUP_ADJUSTMENT_MAPPING = new HashMap<String, String>() {
        {
            put("B", "0.28");
            put("C", "0.56");
            put("D", "0.84");
            put("E", "2.00");
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

    public static final Map<String, String> ODDS_TYPE_MAPPING = new HashMap<String, String>() {
        {
            put("Decimal", "DEC");
            put("Hongkong", "HK");
            put("Malay", "MY");
            put("American", "AM");
            put("Indo", "ID");
        }
    };
}
