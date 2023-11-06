package membersite.objects;

/**
 * @author isabella.huynh
 * @created 12/3/2020
 */
public class Wager {
    private int _betID;
    private String _betType;
    private String _bettingType;
    private double _handicap;
    private double _liability;
    private String _marketID;
    private String _marketName;
    private String _marketType;
    private double _odds;
    private int _orderID;
    private double _payout;
    private String _persistenceType;
    private double _profit;
    private String _runnerName;
    private String _selectionId;
    private double _stake;

    public Wager(Builder builder) {
        this._betID = builder._betID;
        this._betType = builder._bettingType;
        this._bettingType = builder._marketID;
        this._handicap = builder._handicap;
        this._liability = builder._liabilty;
        this._marketID = builder._marketID;
        this._marketName = builder._marketName;
        this._odds = builder._odds;
        this._stake = builder._stake;
        this._orderID = builder._orderID;
        this._payout = builder._payout;
        this._persistenceType = builder._persistenceType;
        this._profit = builder._profit;
        this._runnerName = builder._runnerName;
        this._selectionId = builder._selectionID;
        this._betType = builder._betType;
    }

    public int getBetID() {
        return _betID;
    }

    public void setBetID(int val) {
        _betID = val;
    }

    public String getBetType() {
        return _betType;
    }

    public void setBetType(String val) {
        _betType = val;
    }

    public String getBettingType() {
        return _bettingType;
    }

    public void setBettingType(String val) {
        _bettingType = val;
    }

    public double getLiability() {
        return _liability;
    }

    public void setLiability(double val) {
        _liability = val;
    }

    public double getHandicap() {
        return _handicap;
    }

    public void setHandicap(int val) {
        _handicap = val;
    }

    public void setHandicap(double val) {
        _handicap = val;
    }

    public String getMarketID() {
        return _marketID;
    }

    public void setMarketID(String val) {
        _marketID = val;
    }

    public String getMarketName() {
        return _marketName;
    }

    public void setMarketName(String val) {
        _marketName = val;
    }

    public String getMarketType() {
        return _marketType;
    }

    public void setMarketType(String val) {
        _marketType = val;
    }

    public double getOdds() {
        return _odds;
    }

    public void setOdds(double val) {
        _odds = val;
    }

    public int getOrderID() {
        return _orderID;
    }

    public double getPayout() {
        return _payout;
    }

    public void setPayout(double val) {
        _payout = val;
    }

    public String getPersistenceType() {
        return _persistenceType;
    }

    public void setPersistenceType(String val) {
        _persistenceType = val;
    }

    public double getProfit() {
        return _profit;
    }

    public void setProfit(double val) {
        _profit = val;
    }

    public String getRunnerName() {
        return _runnerName;
    }

    public void setRunnerName(String val) {
        _runnerName = val;
    }

    public String getNewOdds() {
        return _selectionId;
    }

    public double getStake() {
        return _stake;
    }

    public void setStake(double val) {
        _stake = val;
    }

    public void setOdrerID(int val) {
        _orderID = val;
    }

    public void setSelectionId(String val) {
        _selectionId = val;
    }

    public void setSelectionID(String val) {
        _selectionId = val;
    }

    public double getPnlatHandicapPoint(int handicap) {
        double _handicap = this.getHandicap();
        String _betType = this.getBetType();
        if (_betType.equalsIgnoreCase("BACK")) {
            if (handicap >= _handicap)
                return this.getProfit();
            else
                return (this.getLiability()) * (-1);
        } else {
            if (handicap >= _handicap)
                return (this.getLiability()) * (-1);
            else
                return this.getProfit();
        }

    }

    public double getProfitNormalWager() {
        if (_bettingType == "BACK")
            return _stake * (_odds - 1);
        return _stake;
    }

    public double getLiabilityNormalWager() {
        if (_bettingType == "BACK")
            return _stake;
        return _stake * (_odds - 1);

    }

    public double getProfitWicketBookmakerWager() {
        if (_betType == "BACK")
            return _stake * (_odds / 100);
        return _stake;
    }

    public double getLiabilityWicketBookmakerWager() {
        if (_betType == "BACK")
            return _stake;
        return _stake * (_odds / 100);

    }

    public String displayFancyOdds() {

        if (_payout == 0 || _payout == 100) {
            return String.format("%d", (int) _odds);
        }
        return String.format("%d:%,.1f", (int) _odds, _payout);

    }

    public String displa27yFancyOdds() {

        if (_payout == 0 || _payout == 100) {
            return String.format("%d", (int) _odds);
        }
        return String.format("%d : %d", (int) _odds, (int) _payout);

    }

    public double getProfitFancyWager() {
        double profit;
        if (_bettingType == "BACK") {
            if (_payout != 0)
                profit = _stake * (_payout / 100);
            else
                profit = _stake;
        } else {
            if (_payout != 0)
                profit = _stake;
            else
                profit = _stake * (_payout / 100);
        }
        return profit;
    }

    public double getLiabilityFancyWager() {
        double liability;
        if (_bettingType == "BACK") {
            if (_payout != 0)
                liability = _stake;
            else
                liability = _stake * (_payout / 100);
        } else {
            if (_payout != 0)
                liability = _stake * (_payout / 100);
            else
                liability = _stake;
        }
        return liability;
    }

    public static class Builder {
        // Optional parameters;
        private int _betID;
        private String _betType;
        private String _bettingType;
        private double _handicap;
        private double _liabilty;
        private String _marketID;
        private String _marketName;
        private String _marketType;
        private double _odds;
        private int _orderID;
        private double _payout;
        private String _persistenceType;
        private double _profit;
        private String _runnerName;
        private String _selectionID;
        private double _stake;


        public Builder() {
        }

        public Builder betID(int val) {
            _betID = val;
            return this;
        }

        public Builder bettingType(String val) {
            _bettingType = val;
            return this;
        }

        public Builder betType(String val) {
            _betType = val;
            return this;
        }

        public Builder eventID(int val) {
            _betID = val;
            return this;
        }

        public Builder marketID(String val) {
            _marketID = val;
            return this;
        }

        public Builder payout(double val) {
            _payout = val;
            return this;
        }

        public Builder handicap(double val) {
            _handicap = val;
            return this;
        }

        public Builder runnerName(String val) {
            _runnerName = val;
            return this;
        }

        public Builder marketName(String val) {
            _marketName = val;
            return this;
        }

        public Builder stake(double val) {
            _stake = val;
            return this;
        }

        public Builder odds(double val) {
            _odds = val;
            return this;
        }

        public Builder persistenceType(String val) {
            _persistenceType = val;
            return this;
        }

        public Builder orderID(int val) {
            _orderID = val;
            return this;
        }

        public Builder marketType(String val) {
            _marketType = val;
            return this;
        }

        public Builder selectionID(String val) {
            _selectionID = val;
            return this;
        }


        public Builder profit(double val) {
            _profit = val;
            return this;
        }

        public Builder liablity(double val) {
            _liabilty = val;
            return this;
        }

        public Wager build() {
            return new Wager(this);
        }

    }
}
