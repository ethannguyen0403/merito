package common;

import java.util.HashMap;
import java.util.Map;

public class CasinoConstant {
    public static String LIVE_DEALER_TEXT = "Live Dealer";
    public static final Map<String, String> MAPPING_CASINO_PRODUCT_UI = new HashMap<String, String>() {
        {
            put("QTECH", "");
            put("EVOLUTION_WHITE_CLIFF", "");
            put("SUPERNOWA_CASINO", "Supernowa Casino");
            put("EVOLUTION", "Evolution");
            put("LOTTERY_SLOTS", "Lottery & Slots");
            put("LIVE_DEALER_ASIAN", "Asian Room");
            put("LIVE_DEALER_EUROPEAN", "European Room");
            put("GAME_HALL", "");
            put("VIVO", "");
            put("ION", "");
            put("PRAGMATIC", "Pragmatic");
        }
    };

}
