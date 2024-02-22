package membersite.pages.casino;

public enum CasinoProduct {
    QTECH(""), EVOLUTION_WHITE_CLIFF(""), SUPERNOWA_CASINO("Supernowa Casino"), EVOLUTION("Evolution"), LOTTERY_SLOTS("Lottery & Slots"), LIVE_DEALER_ASIAN("Asian Room"),
    LIVE_DEALER_EUROPEAN("European Room"), GAME_HALL(""), VIVO(""), ION(""), PRAGMATIC("Pragmatic");

    private final String val;

    private CasinoProduct(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }

    public Object mappingCasinoProductObject() {
        if(val.equals(CasinoProduct.LIVE_DEALER_ASIAN.val)) {
            return new LiveDealerAsian();
        } else if (val.equals(CasinoProduct.LIVE_DEALER_EUROPEAN.val)) {
            return new LiveDealerEuropean();
        } else if (val.equals(CasinoProduct.EVOLUTION.val)) {
            return new Evolution();
        }
        return null;
    }

}
