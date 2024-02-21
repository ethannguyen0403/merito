package membersite.pages.casino;

public enum CasinoProduct {
    QTECH(""), EVOLUTION_WHITE_CLIFF(""), SUPERNOWA_CASINO("Supernowa Casino"), EVOLUTION("Evolution"), LOTTERY_SLOTS("Lottery & Slots"), LIVE_DEALER_ASIAN("Asian Room"),
    LIVE_DEALER_EUROPEAN(""), GAME_HALL(""), VIVO(""), ION(""), PRAGMATIC("Pragmatic");

    private final String val;

    private CasinoProduct(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
