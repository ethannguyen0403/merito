package common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CasinoConstant {
    public static String LIVE_DEALER_TEXT = "Live Dealer";
    public static String LIVE_DEALER_EURO = "Live Dealer European";
    public static String LIVE_DEALER_ASIAN = "Live Dealer Asian";
    public static String EVOLUTION = "Evolution";
    public static String SUPERNOWA = "Supernowa Casino";
    public static String EVOLUTION_WHITE_CLIFF = "Evoltuion Whitecliff";
    public static String PRAGMATIC = "Pragmatic";
    public static String LOTTERY_AND_SLOTS = "Lottery & Slots";
    public static String GAME_HALL = "Game Hall";
    public static String VIVO = "ViVo";
    public static String CASINO = "membersite/casino";
    public static final List<String> ERROR_CODE_LIST = Arrays.asList("403", "405");
    public static final List<String> LOTTERY_SLOTS_HEADER_MENU = Arrays.asList("", "Slots games", "Table games", "Draw games", "Sport games", "Roulette games");
    public static final List<String> LIVE_DEALER_ASIAN_PRODUCTS_MENU =
            Arrays.asList("Andar Bahar", "Multi Table", "Dragon Tiger", "Baccarat", "NC Baccarat 2", "NC Baccarat 3", "NC Baccarat",
                    "Roulette", "Baccarat 2", "NC Andar Bahar", "Amar Akbar Antony", "Lucky7", "Teenpatti 20-20", "Baccarat 3",
                    "Golden Roulette", "Super Teen-Patti 20-20");
    public static final List<String> LIVE_DEALER_EUROPEAN_PRODUCTS_MENU =
            Arrays.asList("Teen Patti", "Baccarat", "Black Jack", "Sicbo", "Lucky 7", "32 Cards", "Roulette", "Bet On Games", "Evolution Black Jack", "Evolution Baccarat & Sic Bo",
                    "Evolution Roulette", "Evolution Poker", "Evolution Game Shows");
    public static final List<String> VIVO_PRODUCTS_MENU =
            Arrays.asList("ALL", "ROULETTE", "BLACKJACK", "LIMITLESS BLACKJACK","BACCARAT", "CASINO HOLD'EM","TEEN PATTI", "ANDAR BAHAR");
    public static final List<String> ION_PRODUCTS_MENU =
            Arrays.asList("Baccarat", "Dragon Tiger", "Roulette", "Sicbo","Baccarat VIP", "P2P Baccarat");
    public static final List<String> PRAGMATIC_HEADER_MENU = Arrays.asList("", "Video Slots", "Blackjack", "Classic Slots", "Baccarat", "Baccarat New", "Roulette", "Scratch card", "Live games", "RGS - VSB");
    public static final List<String> EVOLUTION_PRODUCTS_MENU = Arrays.asList("Evolution Black Jack", "Evolution Roulette", "Evolution Poker", "Evolution Game Shows", "Evolution Baccarat & Sic Bo");
    public static final Map<String, String> MAPPING_CASINO_PRODUCT_UI = new HashMap<String, String>() {
        {
            put("QTECH", "Q-tech");
            put("EVOLUTION_WHITE_CLIFF", "Evolution");
            put("SUPERNOWA_CASINO", "Supernowa Casino");
            put("EVOLUTION", "Evolution");
            put("LOTTERY_SLOTS", "Lottery & Slots");
            put("LIVE_DEALER_ASIAN", "Asian Room");
            put("LIVE_DEALER_EUROPEAN", "European Room");
            put("GAME_HALL", "Game Hall");
            put("VIVO", "Vivo");
            put("ION", "Ion");
            put("PRAGMATIC", "Pragmatic");
        }
    };

    public static final Map<String, String> MAPPING_CASINO_PRODUCT_SUFFIX_URL = new HashMap<String, String>() {
        {
            put("Live Dealer European", "/home/live-dealer/ezugi");
            put("Live Dealer Asian", "/home/live-dealer/super-spade");
            put("Evolution", "/home/live-dealer/ezugi");
            put("Supernowa Casino", "/home/veronica/supernowa");
            put("Evolution Whitecliff", "/home/custom?code=WHITECLIFF");
            put("Pragmatic", "/home/veronica/pragmatic");
            put("Lottery & Slots", "/home/lottery-slots");
            put("Game Hall", "/home/custom?code=GAME_HALL");
            put("ViVo", "/home/custom?code=VIVO");
        }
    };
}
