package api.objects.meritoAPI;

public class Order {
    private int eventId;
    private String marketId;
    private int selectionId;
    private String side;// place type back or lay
    private String orderType;
    private Double handicap;
    private double size;// means stake value
    private double price; // means odds value
    private String persistenceType;
    private double sizeMatched;
    private Double priceMatched;
    private int betId;
    private String placedDate;
    private String matchedDate;
    private String errorMessage;
    private String orderStatus;// API Status: EXCUTABLE

    public Order(Builder builder) {
        this.eventId = builder.eventId;
        this.marketId = builder.marketId;
        this.selectionId = builder.selectionId;
        this.side = builder.side;
        this.orderType = builder.orderType;
        this.handicap = builder.handicap;
        this.size = builder.size;
        this.price = builder.price;
        this.persistenceType = builder.persistenceType;
        this.sizeMatched = builder.sizeMatched;
        this.priceMatched = builder.priceMatched;
        this.betId = builder.betId;
        this.placedDate = builder.placedDate;
        this.matchedDate = builder.matchedDate;
        this.errorMessage = builder.errorMessage;
        this.orderStatus = builder.orderStatus;
    }

    public String getPlacedDate() {
        return placedDate;
    }

    public void setPlacedDate(String placedDate) {
        this.placedDate = placedDate;
    }

    public String getMatchedDate() {
        return matchedDate;
    }

    public void setMatchedDate(String matchedDate) {
        this.matchedDate = matchedDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public int getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(int selectionId) {
        this.selectionId = selectionId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Double getHandicap() {
        return handicap;
    }

    public void setHandicap(Double handicap) {
        this.handicap = handicap;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPersistenceType() {
        return persistenceType;
    }

    public void setPersistenceType(String persistenceType) {
        this.persistenceType = persistenceType;
    }

    public double getSizeMatched() {
        return sizeMatched;
    }

    public void setSizeMatched(Double sizeMatched) {
        this.sizeMatched = sizeMatched;
    }

    public Double getPriceMatched() {
        return priceMatched;
    }

    public void setPriceMatched(double priceMatched) {
        this.priceMatched = priceMatched;
    }

    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static class Builder {
        private int eventId;
        private String marketId;
        private int selectionId;
        private String side;// place type back or lay
        private String orderType;
        private Double handicap;
        private double size;// means stake value
        private double price; // means odds value
        private String persistenceType;
        private double sizeMatched;
        private Double priceMatched;
        private int betId;
        private String placedDate;
        private String matchedDate;
        private String errorMessage;
        private String orderStatus;// API Status: EXCUTABLE

        public Builder() {
        }

        public Builder eventId(int val) {
            eventId = val;
            return this;
        }

        public Builder selectionId(int val) {
            selectionId = val;
            return this;
        }

        public Builder marketId(String val) {
            marketId = val;
            return this;
        }

        public Builder side(String val) {
            side = val;
            return this;
        }

        public Builder handicap(Double val) {
            handicap = val;
            return this;
        }

        public Builder price(double val) {
            price = val;
            return this;
        }

        public Builder size(double val) {
            size = val;
            return this;
        }

        public Builder sizeMatched(double val) {
            sizeMatched = val;
            return this;
        }

        public Builder priceMatched(double val) {
            priceMatched = val;
            return this;
        }

        public Builder persistenceType(String val) {
            persistenceType = val;
            return this;
        }

        public Builder betId(int val) {
            betId = val;
            return this;
        }

        public Builder placedDate(String val) {
            placedDate = val;
            return this;
        }

        public Builder matchedDate(String val) {
            matchedDate = val;
            return this;
        }

        public Builder errorMessage(String val) {
            errorMessage = val;
            return this;
        }

        public Builder orderStatus(String val) {
            orderStatus = val;
            return this;
        }

        public Builder orderType(String val) {
            orderType = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

}
