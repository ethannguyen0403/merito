package backoffice.objects.bo.marketmanagement;


public class Market {
    private String _marketId;
    private String _marketName;
    private String _marketStartTime;
    private String _marketType;
    private String _marketStatus;
    private int _min;
    private int _max;

    public Event event;
    private String _competitionId;
    private String _competitionName;
    private Market(Builder builder) {
        this.event = builder.event;
        this._marketId = builder._marketId;
        this._marketName = builder._marketName;
        this._marketStartTime = builder._marketStartTime;
        this._marketType = builder._marketType;
        this._marketStatus = builder._marketStatus;
        this._min = builder._min;
        this._max = builder._max;
        this._competitionId = builder._competitionId;
        this._competitionName = builder._competitionName;
    }
    public void setEvent(Event _event) {
        this.event = _event;
    }
    public Event getEvent() {
        return event;
    }
    public String getMarketId() {
        return _marketId;
    }

    public String getMarketName() {
        return _marketName;
    }

    public String getMarketStartTime() {
        return _marketStartTime;
    }
    public String getMarketType() {
        return _marketType;
    }
    public String getMarketStatus() {
        return _marketStatus;
    }
    public int getMin() {
        return _min;
    }
    public int getMax() {
        return _max;
    }
    public String getCompetitionId() {
        return _competitionId;
    }
    public String getCompetitionName() {
        return _competitionName;
    }
    public static class Builder {
        // Optional parameters
        private String _marketId = "";
        private String _marketName = "";
        private String _marketStartTime = "";
        private String _marketType = "";
        private String _marketStatus = "";
        private int _min = 0;
        private int _max = 0;
        private String _competitionId = "";
        private String _competitionName = "";
        public Event event;

        public Builder() {
        }

        public Builder event(Event val) {
            event = val;
            return this;
        }

        public Builder marketId(String val) {
            _marketId = val;
            return this;
        }

        public Builder marketName(String val) {
            _marketName = val;
            return this;
        }

        public Builder marketStartTime(String val) {
            _marketStartTime = val;
            return this;
        }
        public Builder marketType(String val) {
            _marketType = val;
            return this;
        }
        public Builder marketStatus(String val) {
            _marketStatus = val;
            return this;
        }
        public Builder min(int val) {
            _min = val;
            return this;
        }
        public Builder max(int val) {
            _max = val;
            return this;
        }
        public Builder competitionName(String val) {
            _competitionName = val;
            return this;
        }
        public Builder competitionId(String val) {
            _competitionId = val;
            return this;
        }
        public Market build() {
            return new Market(this);
        }

    }
}
