package membersite.objects.sat;

import com.paltech.element.common.Link;

/**
 * @author isabella.huynh
 * @created 10/3/2020
 */
public class BookmakerMarket {
    private String _eventName;
    private String _eventId;
    private String _competitionName;
    private String _marketType;
    private String _marketName;
    private String _marketID;
    private String _status;
    private String _marketTime;
    private int _minBet;
    private int _maxBet;
    private Link _lnkOdds;
    private String _oddsBack;
    private String _oddsLay;
    private String _selectionName;

    public BookmakerMarket(Builder builder) {
        this._marketID = builder._marketID;
        this._eventName = builder._eventName;
        this._marketName = builder._marketName;
        this._eventId = builder._eventId;
        this._competitionName = builder._competitionName;
        this._minBet = builder._minBet;
        this._maxBet = builder._maxBet;
        this._marketType = builder._marketType;
        this._status = builder._status;
        this._marketTime = builder._marketTime;
        this._lnkOdds = builder._lnkOdds;
        this._oddsBack = builder._oddsBack;
        this._oddsLay = builder._oddsLay;
        this._selectionName = builder._selectionName;
    }

    public String getEventName() {
        return _eventName;
    }

    public String getMarketID() {
        return _marketID;
    }

    public String getEventID() {
        return _eventId;
    }

    public void setEventID(String val) {
        _eventId = val;
    }

    public String getCompetitionName() {
        return _competitionName;
    }

    public void setCompetitionName(String val) {
        _competitionName = val;
    }

    public int getMinSetting() {
        return _minBet;
    }

    public int getMaxSetting() {
        return _maxBet;
    }

    public String getMaketType() {
        return _marketType;
    }

    public String getMarketName() {
        return _marketName;
    }

    public void setMarketName(String val) {
        _marketName = val;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String val) {
        _status = val;
    }

    public String getMarketTime() {
        return _marketTime;
    }

    public void setMarketTime(String val) {
        _marketTime = val;
    }

    public Link getBtnNo() {
        return _lnkOdds;
    }

    public void setBtnNo(Link val) {
        _lnkOdds = val;
    }

    public void setEventNam(String val) {
        _eventName = val;
    }

    public void setMaretId(String val) {
        _marketID = val;
    }

    public void setMin(int val) {
        _minBet = val;
    }

    public void setMmax(int val) {
        _maxBet = val;
    }

    public void setMarketType(String val) {
        _marketType = val;
    }

    public String getOddsBack() {
        return _oddsBack;
    }

    public void setOddsBack(String _oddsBack) {
        this._oddsBack = _oddsBack;
    }

    public String getOddsLay() {
        return _oddsLay;
    }

    public void setOddsLay(String _oddsLay) {
        this._oddsBack = _oddsLay;
    }

    public String getSelectionName() {
        return _selectionName;
    }

    public void setSelectionName(String val) {
        _selectionName = val;
    }

    public static class Builder {
        // Optional parameters
        private String _marketID = "";
        private String _marketName = "";
        private String _eventName = "";
        private String _eventId = "";
        private String _competitionName = "";
        private int _minBet;
        private int _maxBet;
        private String _marketType;
        private String _status = "";
        private String _marketTime = "";
        private Link _lnkOdds;
        private String _oddsBack;
        private String _oddsLay;
        private String _selectionName = "";

        public Builder() {
        }

        public Builder marketID(String val) {
            _marketID = val;
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

        public Builder eventID(String val) {
            _eventId = val;
            return this;
        }

        public Builder competitionNAme(String val) {
            _competitionName = val;
            return this;
        }

        public Builder minBet(int val) {
            _minBet = val;
            return this;
        }

        public Builder maxBet(int val) {
            _maxBet = val;
            return this;
        }

        public Builder marketType(String val) {
            _marketType = val;
            return this;
        }

        public Builder status(String val) {
            _status = val;
            return this;
        }

        public Builder marketTime(String val) {
            _marketTime = val;
            return this;
        }

        public Builder btnNo(Link val) {
            _lnkOdds = val;
            return this;
        }

        public Builder oddsBack(String val) {
            _oddsBack = val;
            return this;
        }

        public Builder oddsLay(String val) {
            _oddsLay = val;
            return this;
        }

        public Builder selectionName(String val) {
            _selectionName = val;
            return this;
        }

        public BookmakerMarket build() {
            return new BookmakerMarket(this);
        }

    }
}
