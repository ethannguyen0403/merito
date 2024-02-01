package membersite.pages.casino;

public enum CasinoProduct {
    QTECH(""), EVOLUTION_WHITE_CLIFF(""), SUPERNOWA_CASINO("Supernowa Casino"), EVOLUTION(""), LOTTERY_SLOTS(""), LIVE_DEALER_ASIAN(""),
    LIVE_DEALER_EUROPEAN(""), GAME_HALL(""), VIVO(""), ION(""), PRAGMATIC("");

    private final String val;

    private CasinoProduct(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
