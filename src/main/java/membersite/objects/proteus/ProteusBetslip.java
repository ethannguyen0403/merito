package membersite.objects.proteus;

//TODO: This object will be replace by proteus > Order Object and will be deleted
public class ProteusBetslip {
    private String _eventName;
    private String _summaryEventInfo;
    private String _hdpPoint;
    private String _odds;
    private String _stake;
    private String _minBet;
    private String _maxBet;
    private String _maxMatch;
    private String _toRisk;
    private String _toWin;
    public ProteusBetslip(Builder builder) {
        this._eventName = builder._eventName;
        this._summaryEventInfo = builder._summaryEventInfo;
        this._odds = builder._odds;
        this._stake = builder._stake;
        this._minBet = builder._minBet;
        this._maxBet = builder._maxBet;
        this._maxMatch = builder._maxMatch;
        this._hdpPoint = builder._hdpPoint;
        this._toRisk = builder._toRisk;
        this._toWin = builder._toWin;
    }

    public void setEventName(String val) {
        _eventName = val;
    }

    public String getEventName() {
        return _eventName;
    }
    public void setSummaryEventInfo(String val) {
        _summaryEventInfo = val;
    }

    public String getSummaryEventInfo() {
        return _summaryEventInfo;
    }
    public void setOdds(String val) {
        _odds = val;
    }

    public String getOdds() {
        return _odds;
    }
    public void setStake(String val) {
        _stake = val;
    }

    public String getStake() {
        return _stake;
    }
    public void setMinBet(String val) {
        _minBet = val;
    }

    public String getMinBet() {
        return _minBet;
    }
    public void setMaxBet(String val) {
        _maxBet = val;
    }

    public String getMaxBet() {
        return _maxBet;
    }
    public void setMaxMatch(String val) {
        _maxMatch = val;
    }

    public String getMaxMatch() {
        return _maxMatch;
    }
    public void setHDPPoint(String val) {
        _hdpPoint = val;
    }

    public String getHDPPoint() {
        return _hdpPoint;
    }
    public void setToRisk(String toRisk) {
        _toRisk = toRisk;
    }

    public String getToRisk() {
        return _toRisk;
    }
    public void setToWin(String toWin) {
        _toWin = toWin;
    }

    public String getToWin() {
        return _toWin;
    }
    public static class Builder {
        // Optional parameters
        private String _eventName;
        private String _summaryEventInfo;
        private String _hdpPoint;
        private String _odds;
        private String _stake;
        private String _minBet;
        private String _maxBet;
        private String _maxMatch;
        private String _toRisk;
        private String _toWin;
        public Builder() {
        }

        public Builder eventName(String val) {
            _eventName = val;
            return this;
        }

        public Builder summaryEventInfo(String val) {
            _summaryEventInfo = val;
            return this;
        }

        public Builder hdpPoint(String val) {
            _hdpPoint = val;
            return this;
        }

        public Builder odds(String val) {
            _odds = val;
            return this;
        }

        public Builder stake(String val) {
            _stake = val;
            return this;
        }
        public Builder minBet(String val) {
            _minBet = val;
            return this;
        }
        public Builder maxBet(String val) {
            _maxBet = val;
            return this;
        }
        public Builder maxMatch(String val) {
            _maxMatch = val;
            return this;
        }

        public Builder toRisk(String val) {
            _toRisk = val;
            return this;
        }

        public Builder toWin(String val) {
            _toWin = val;
            return this;
        }

        public ProteusBetslip build() {
            return new ProteusBetslip(this);
        }

    }
}
