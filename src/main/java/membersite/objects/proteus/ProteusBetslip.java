package membersite.objects.proteus;

public class ProteusBetslip {
    private String _eventName;
    private String _summaryEventInfo;
    private String _hdpPoint;
    private String _odds;
    private String _stake;
    public ProteusBetslip(Builder builder) {
        this._eventName = builder._eventName;
        this._summaryEventInfo = builder._summaryEventInfo;
        this._odds = builder._odds;
        this._stake = builder._stake;
        this._hdpPoint = builder._hdpPoint;
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
    public void setHDPPoint(String val) {
        _hdpPoint = val;
    }

    public String getHDPPoint() {
        return _hdpPoint;
    }

    public static class Builder {
        // Optional parameters
        private String _eventName;
        private String _summaryEventInfo;
        private String _hdpPoint;
        private String _odds;
        private String _stake;
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

        public ProteusBetslip build() {
            return new ProteusBetslip(this);
        }

    }
}
