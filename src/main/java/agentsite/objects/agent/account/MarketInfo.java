package agentsite.objects.agent.account;

/**
 * @author isabella.huynh
 * @created Nov/26/2019
 */
public class MarketInfo {
    private String _sportID;
    private String _sportName;
    private String _competitionID;
    private String _competitionName;
    private String _eventID;
    private String _eventName;
    private String _marketID;
    private String _marketName;

    public String getSportID() {
        return _sportID;
    }

    public String getSportName() {
        return _sportName;
    }

    public String getCompetitionID(){ return _competitionID;}

    public String getCompetitionName() {
        return _competitionName;
    }

    public String getEventID() {
        return _eventID;
    }

    public String getEventName() {
        return _eventName;
    }

    public String getMarketID() {
        return _marketID;
    }

    public String getMarketName() {
        return _marketName;
    }

    public static class Builder {
        private String _sportID;
        private String _sportName;
        private String _competitionID;
        private String _competitionName;
        private String _eventID;
        private String _eventName;
        private String _marketID;
        private String _marketName;

        public Builder(){}

        public Builder sportID(String val){
            _sportID = val;
            return this;
        }

        public Builder sportName(String val){
            _sportName = val;
            return this;
        }

        public Builder competitionID (String val){
            _competitionID = val;
            return this;
        }

        public Builder competitionName(String val){
            _competitionName = val;
            return this;
        }

        public Builder eventID(String val){
            _eventID = val;
            return this;
        }

        public Builder eventName(String val){
            _eventName = val;
            return this;
        }

        public Builder marketID(String val){
            _marketID = val;
            return this;
        }

        public Builder marketName(String val){
            _marketName = val;
            return this;
        }

        public MarketInfo build() { return new MarketInfo(this); }

    }

    private MarketInfo(Builder builder){
        this._sportID = builder._sportID;
        this._sportName = builder._sportName;
        this._competitionID = builder._competitionID;
        this._competitionName = builder._competitionName;
        this._eventID = builder._eventID;
        this._eventName = builder._eventName;
        this._marketID = builder._marketID;
        this._marketName = builder._marketName;
    }


}
