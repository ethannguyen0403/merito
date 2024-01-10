package common;

import java.util.HashMap;
import java.util.Map;

public class ProteusConstant {
    public static final String EURO_VIEW = "Euro View";
    public static final String ASIAN_VIEW = "Asian View";
    public static final String DECIMAL = "Decimal";
    public static final String HONGKONG = "HongKong";
    public static final String MALAY = "Malay";
    public static final String AMERICAN = "American";
    public static final String HDP_TEXT = "Handicap";
    public static final String MONEYLINE_TEXT = "1X2";
    public static final String OVER_UNDER_TEXT = "Over Under";
    public static final String EARLY_PERIOD = "Early";
    public static final String LIVE_PERIOD = "Live";
    public static final String TODAY_PERIOD = "Today";
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
            put("HDP", "SPREAD");
            put("1X2", "MONEYLINE");
            put("Over Under", "TOTAL_POINTS");
        }
    };

    public static final Map<String, String> ODDS_TYPE_MAPPING = new HashMap<String, String>() {
        {
            put("Decimal", "DEC");
            put("Hongkong", "HK");
            put("Malay", "MY");
            put("America", "AM");
            put("Indo", "ID");
        }
    };
}
