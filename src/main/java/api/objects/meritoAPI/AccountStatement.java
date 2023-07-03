package api.objects.meritoAPI;

public class AccountStatement {
    private int betId;
    private double amount;
    private double balance;
    private int marketId;
    private String settlementDate;
    private String orderPlaceTime;

    public AccountStatement(Builder builder) {
        this.betId = builder.betId;
        this.amount = builder.amount;
        this.balance = builder.balance;
        this.marketId = builder.marketId;
        this.settlementDate = builder.settlementDate;
        this.orderPlaceTime = builder.orderPlaceTime;
    }

    public int getBetId() {
        return betId;
    }

    public void setBetId(int betId) {
        this.betId = betId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getMarketId() {
        return marketId;
    }

    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getOrderPlaceTime() {
        return orderPlaceTime;
    }

    public void setOrderPlaceTime(String orderPlaceTiem) {
        this.orderPlaceTime = orderPlaceTiem;
    }

    public static class Builder {
        private int betId;
        private double amount;
        private double balance;
        private int marketId;
        private String settlementDate;
        private String orderPlaceTime;

        public Builder() {
        }

        public Builder betId(int val) {
            betId = val;
            return this;
        }

        public Builder amount(double val) {
            amount = val;
            return this;
        }

        public Builder balance(double val) {
            balance = val;
            return this;
        }

        public Builder marketId(int val) {
            marketId = val;
            return this;
        }

        public Builder settlementDate(String val) {
            settlementDate = val;
            return this;
        }

        public Builder orderPlaceTime(String val) {
            orderPlaceTime = val;
            return this;
        }

        public AccountStatement build() {
            return new AccountStatement(this);
        }
    }

}
