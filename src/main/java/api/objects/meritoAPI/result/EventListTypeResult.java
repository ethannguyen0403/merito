package api.objects.meritoAPI.result;

import api.objects.meritoAPI.SportType;

public class EventListTypeResult extends ResultObject {
    private SportType _sportType;
    private int _marketCount;
    public int getMarketCount () {
        return _marketCount;
    }
    public void setMarketCount(int val) {_marketCount = val;}

    public SportType getSportType () {
        return _sportType;
    }
    public void setSportType(SportType val) {_sportType = val;}

    public static class Builder {
        private boolean _isSuccess;
        private SportType _sportType;
        private int _marketCount;

        public Builder(){}

        public Builder isSuccess(boolean val){
            _isSuccess =val;
            return this;
        }
        public Builder sportType(SportType val){
            _sportType = val;
            return this;
        }
        public Builder marketCount(int val){
            _marketCount = val;
            return this;
        }

        public EventListTypeResult build() { return new EventListTypeResult(this); }
    }

    public EventListTypeResult(Builder builder){
        this._isSuccess = builder._isSuccess;
        this._marketCount = builder._marketCount;
        this._sportType = builder._sportType;
    }
}
