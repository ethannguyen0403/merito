package api.objects.B2B.resultObj;

public class WagerObj {
    private String _userId;
    private int _orderID;
    private String _sportName;
    private String _competitionName;
    private String _eventName;
    private String _marketName;
    private String _side;
    private double _odds;
    private double _stake;
    private double _pnl;
    private String _status;
    private String _betOutcome;
    private String _placeDate;
    private String _matchedDate;
    private String _settledDate;
    private String _selectionName;
    private double _handicap;
    private String _oddsType;
    private String _eventStartDate;

    public WagerObj(Builder builder) {
        this._userId = builder._userId;
        this._orderID = builder._orderID;
        this._sportName = builder._sportName;
        this._competitionName = builder._competitionName;
        this._eventName = builder._eventName;
        this._marketName = builder._marketName;
        this._side = builder._side;
        this._odds = builder._odds;
        this._stake = builder._stake;
        this._pnl = builder._pnl;
        this._betOutcome = builder._betOutcome;
        this._placeDate = builder._placeDate;
        this._matchedDate = builder._matchedDate;
        this._settledDate = builder._settledDate;
        this._selectionName = builder._selectionName;
        this._handicap = builder._handicap;
        this._oddsType = builder._oddsType;
        this._status = builder._status;
        this._eventStartDate = builder._eventStartDate;

    }

    public String getUserId() {
        return _userId;
    }

    public void setUserId(String val) {
        _userId = val;
    }

    public double getOrderId() {
        return _orderID;
    }

    public void setOrderId(int val) {
        _orderID = val;
    }

    public String getCompetitionName() {
        return _competitionName;
    }

    public void setCompetitionName(String val) {
        _competitionName = val;
    }

    public double getOdds() {
        return _odds;
    }

    public void setOdds(double val) {
        _odds = val;
    }

    public String getEventName() {
        return _eventName;
    }

    public void setEventName(String val) {
        _eventName = val;
    }

    public String getSportName() {
        return _sportName;
    }

    public void setSportName(String val) {
        _sportName = val;
    }

    public String getMarketName() {
        return _marketName;
    }

    public void setMarketName(String _marketName) {
        this._marketName = _marketName;
    }

    public String getSide() {
        return _side;
    }

    public void setSide(String _side) {
        this._side = _side;
    }

    public double getStake() {
        return _stake;
    }

    public void setStake(double _stake) {
        this._stake = _stake;
    }

    public double getPnl() {
        return _pnl;
    }

    public void setPnl(double _pnl) {
        this._pnl = _pnl;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String _status) {
        this._status = _status;
    }

    public String getBetOutcome() {
        return _betOutcome;
    }

    public void setBetOutcome(String _betOutcome) {
        this._betOutcome = _betOutcome;
    }

    public String getPlaceDate() {
        return _placeDate;
    }

    public void setPlaceDate(String _placeDate) {
        this._placeDate = _placeDate;
    }

    public String getMatchedDate() {
        return _matchedDate;
    }

    public void setMatchedDate(String _matchedDate) {
        this._matchedDate = _matchedDate;
    }

    public String getSettledDate() {
        return _settledDate;
    }

    public void setSettledDate(String _settledDate) {
        this._settledDate = _settledDate;
    }

    public String getSelectionName() {
        return _selectionName;
    }

    public void setSelectionName(String _selectionName) {
        this._selectionName = _selectionName;
    }

    public double getHandicap() {
        return _handicap;
    }

    public void setHandicap(double _handicap) {
        this._handicap = _handicap;
    }

    public String getOddsType() {
        return _oddsType;
    }

    public void setOddsType(String _oddsType) {
        this._oddsType = _oddsType;
    }

    public String getEventStartDate() {
        return _eventStartDate;
    }

    public void setEventStartDate(String _eventStartDate) {
        this._eventStartDate = _eventStartDate;
    }

    public static class Builder {
        private int _result;
        private String _userId;
        private int _orderID;
        private String _sportName;
        private String _competitionName;
        private String _eventName;
        private String _marketName;
        private String _side;
        private double _odds;
        private double _stake;
        private double _pnl;
        private String _status;
        private String _betOutcome;
        private String _placeDate;
        private String _matchedDate;
        private String _settledDate;
        private String _selectionName;
        private double _handicap;
        private String _oddsType;
        private String _eventStartDate;

        public Builder() {
        }

        public Builder result(int val) {
            _result = val;
            return this;
        }

        public Builder userId(String val) {
            _userId = val;
            return this;
        }

        public Builder orderId(int val) {
            _orderID = val;
            return this;
        }

        public Builder sportName(String val) {
            _sportName = val;
            return this;
        }

        public Builder competitionName(String val) {
            _competitionName = val;
            return this;
        }

        public Builder eventName(String val) {
            _eventName = val;
            return this;
        }

        public Builder marketName(String val) {
            _marketName = val;
            return this;
        }

        public Builder side(String val) {
            _side = val;
            return this;
        }

        public Builder status(String val) {
            _status = val;
            return this;
        }

        public Builder odds(double val) {
            _odds = val;
            return this;
        }

        public Builder stake(double val) {
            _stake = val;
            return this;
        }

        public Builder pnl(double val) {
            _pnl = val;
            return this;
        }

        public Builder betOutcome(String val) {
            _betOutcome = val;
            return this;
        }

        public Builder placeDate(String val) {
            _placeDate = val;
            return this;
        }

        public Builder matchedDate(String val) {
            _matchedDate = val;
            return this;
        }

        public Builder settledDate(String val) {
            _settledDate = val;
            return this;
        }

        public Builder selectionName(String val) {
            _selectionName = val;
            return this;
        }

        public Builder eventStartDate(String val) {
            _eventStartDate = val;
            return this;
        }

        public Builder handicap(double val) {
            _handicap = val;
            return this;
        }

        public Builder oddsType(String val) {
            _oddsType = val;
            return this;
        }

        public WagerObj build() {
            return new WagerObj(this);
        }
    }
}
