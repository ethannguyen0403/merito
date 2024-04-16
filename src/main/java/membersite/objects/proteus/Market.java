package membersite.objects.proteus;

import java.util.List;

import static common.ProteusConstant.*;

public class Market {
    private String _sportName;
    private String _leagueName;
    private String _eventName;
    private String _marketName;
    private String _eventStartTime;
    private int _eventId;
    private Long _lineID;
    private String _betType;
    private String _oddsKey;
    private double _handicap;
    private String _team;
    private int _totalMarket;
    private String _status;
    private String _oddsType;
    private String _oddsFormat;
    private String _marketKey;
    private String _homeName;
    private String _awayName;
    private int _periodId;
    private Long _altLineID;
    private List<Odds> _odds;

    public Market(Builder builder) {
        this._sportName = builder._sportName;
        this._leagueName = builder._leagueName;
        this._eventName = builder._eventName;
        this._marketName =builder._marketName;
        this._eventStartTime = builder._eventStartTime;
        this._eventId = builder._eventId;
        this._lineID = builder._lineID;
        this._betType = builder._betType;
        this._oddsKey = builder._oddsKey;
        this._handicap = builder._handicap;
        this._team = builder._team;
        this._totalMarket = builder._totalMarket;
        this._status = builder._status;
        this._oddsType = builder._oddsType;
        this._oddsFormat = builder._oddsFormat;
        this._marketKey = builder._marketKey;
        this._odds = builder._odds;
        this._homeName = builder._homeName;
        this._awayName = builder._awayName;
        this._periodId = builder._periodId;
        this._altLineID = builder._altLineID;

    }
    public String getSportName() {
        return _sportName;
    }

    public void setSportName(String _sportName) {
        this._sportName = _sportName;
    }

    public String getLeagueName() {
        return _leagueName;
    }

    public void setLeagueName(String _leagueName) {
        this._leagueName = _leagueName;
    }

    public String getEventName() {
        return _eventName;
    }

    public void setEventName(String _eventName) {
        this._eventName = _eventName;
    }

    public String getMarketName() {
        return _marketName;
    }

    public void setMarketName(String _marketName) {
        this._marketName = _marketName;
    }

    public String getEventStartTime() {
        return _eventStartTime;
    }

    public void setEventStartTime(String _eventStartTime) {
        this._eventStartTime = _eventStartTime;
    }

    public int getEventId() {
        return _eventId;
    }

    public void setEventId(int _eventId) {
        this._eventId = _eventId;
    }

    public Long getLineID() {
        return _lineID;
    }

    public void setLineID(Long _lineID) {
        this._lineID = _lineID;
    }

    public String getBetType() {
        return _betType;
    }

    public void setBetType(String _betType) {
        this._betType = _betType;
    }

    public String getOddsKey() {
        return _oddsKey;
    }

    public void setOddsKey(String _oddsKey) {
        this._oddsKey = _oddsKey;
    }

    public double getHandicap() {
        return _handicap;
    }

    public void setHandicap(double _handicap) {
        this._handicap = _handicap;
    }

    public String getTeam() {
        return _team;
    }

    public void setTeam(String _team) {
        this._team = _team;
    }

    public int getTotalMarket() {
        return _totalMarket;
    }

    public void setTotalMarket(int _totalMarket) {
        this._totalMarket = _totalMarket;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String _status) {
        this._status = _status;
    }

    public String getOddsType() {
        return _oddsType;
    }

    public void setOddsType(String _oddsType) {
        this._oddsType = _oddsType;
    }

    public String getOddsFormat() {
        return _oddsFormat;
    }

    public void setOddsFormat(String _oddsFormat) {
        this._oddsFormat = _oddsFormat;
    }

    public String getMarketKey() {
        return _marketKey;
    }

    public void setMarketKey(String _marketKey) {
        this._marketKey = _marketKey;
    }
    public String getHomeName() {        return _homeName;    }

    public void setHomeName(String _homeName) {
        this._homeName = _homeName;
    }

    public String getAwayName() {
        return _awayName;
    }

    public void setAwayName(String _awayName) {
        this._awayName = _awayName;
    }
    public int getPeriodId() {
        return _periodId;
    }

    public void setPeriodId(int _periodId) {
        this._periodId = _periodId;
    }
    public List<Odds> getOdds() {
        return _odds;
    }

    public void setOdds(List<Odds> _odds) {
        this._odds = _odds;
    }

    public Long getAltLineID() {
        return _altLineID;
    }

    public void setAltLineID(Long _altLineID) {
        this._altLineID = _altLineID;
    }

    public Odds getOddsInfoBySelection(String selection){
        //handle case OVER/UNDER > getSide
        if(selection.equalsIgnoreCase("OVER") || selection.equalsIgnoreCase("UNDER")) {
            for (Odds o: _odds) {
                if(o.getSide().equalsIgnoreCase(selection))
                    return o;
            }
        } else {
            for (Odds o: _odds) {
                if(o.getTeam().equalsIgnoreCase(selection))
                    return o;
            }
        }
        return null;
    }

    // Calculate to risk
    public double getToRisk(double stake, String oddsType, boolean isNegative) {
        // HongKong and Decimal odds is always positive
        if (!isNegative && oddsType.equalsIgnoreCase(HONGKONG)) {
            return stake * getOdds().get(0).getOdds();
        } else if (!isNegative && oddsType.equalsIgnoreCase(DECIMAL)) {
            return stake * (1 - getOdds().get(0).getOdds());
        } else {
            switch (oddsType) {
                case AMERICAN:
                    if (isNegative)
                        return stake;
                    return stake * getOdds().get(0).getOdds() / 100;
                case MALAY:
                    if (isNegative)
                        return stake;
                    return stake * Math.abs(getOdds().get(0).getOdds());
                default:
                    return 0;
            }
        }
    }

    //Calculate to win
    public double getToWin(double stake, String oddsType, boolean isNegative){
        // HongKong and Decimal odds is always positive
        if (!isNegative && (oddsType.equalsIgnoreCase(HONGKONG) || oddsType.equalsIgnoreCase(DECIMAL))) {
            return stake;
        }
         else {
            switch (oddsType) {
                case AMERICAN:
                    if (!isNegative)
                        return stake;
                    return stake * getOdds().get(0).getOdds() / 100;
                case MALAY:
                    if (!isNegative)
                        return stake;
                    return stake * Math.abs(getOdds().get(0).getOdds());
                default:
                    return 0;
            }
        }
    }

    // Calculate Odds group here
    private double getVig(List<Odds> odds) {
        double vig = 0.00;
        for (Odds o: odds
             ) {
            vig += o.getProbability(o);
        }
        return vig;
    }

    public List<Odds> getDecimalOdds(List<Odds> odds, double vigAdjustment) {
        double vig = getVig(odds);
        for (int i = 0; i < odds.size(); i++) {
            odds.get(i).setOdds(odds.get(i).getDECOdds(odds.get(i), vig, vigAdjustment));
        }
        return odds;
    }

    public List<Odds> getHongkongOdds(List<Odds> odds, double vigAdjustment) {
        double vig = getVig(odds);
        for (int i = 0; i < odds.size(); i++) {
            odds.get(i).setOdds(odds.get(i).getHKOdds(odds.get(i), vig, vigAdjustment));
        }
        return odds;
    }

    public List<Odds> getMalayOdds(List<Odds> odds, double vigAdjustment) {
        double vig = getVig(odds);
        for (int i = 0; i < odds.size(); i++) {
            odds.get(i).setOdds(odds.get(i).getMYOdds(odds.get(i), vig, vigAdjustment));
        }
        return odds;
    }

    public List<Odds> getAmericanOdds(List<Odds> odds, double vigAdjustment) {
        double vig = getVig(odds);
        for (int i = 0; i < odds.size(); i++) {
            odds.get(i).setOdds(odds.get(i).getAMOdds(odds.get(i), vig, vigAdjustment));
        }
        return odds;
    }

    public List<Odds> getConvertedOddsByGroup(String oddsType, String oddsGroup) {
        List<Odds> odds = this.getOdds();
        double vigAdjustment = Double.parseDouble(ODDS_GROUP_ADJUSTMENT_MAPPING.get(oddsGroup));
        if(oddsGroup.equalsIgnoreCase("A") && oddsType.equalsIgnoreCase("Decimal")) {
            return odds;
        } else {
            switch (oddsType) {
                case AMERICAN:
                    return getAmericanOdds(odds, vigAdjustment);
                case HONGKONG:
                    return getHongkongOdds(odds, vigAdjustment);
                case MALAY:
                    return getMalayOdds(odds, vigAdjustment);
                default:
                    return getDecimalOdds(odds, vigAdjustment);
            }
        }
    }

    public boolean isMarketContainsNegativeOdds(){
        for (Odds o: _odds
             ) {
            if(o.getOdds() < 0)
                return true;
        }
        return false;
    }
    public static class Builder {
        private String _sportName;
        private String _leagueName;
        private String _eventName;
        private String _marketName;
        private String _eventStartTime;
        private int _eventId;
        private Long _lineID;
        private String _betType;
        private String _oddsKey;
        private double _handicap;
        private String _team;
        private int _totalMarket;
        private String _status;
        private String _oddsType;
        private String _oddsFormat;
        private String _marketKey;
        private String _homeName;
        private String _awayName;
        private int _periodId;
        private List<Odds> _odds;
        private Long _altLineID;
        public Builder() {
        }
        public Builder sportName(String val) {
            _sportName = val;
            return this;
        }
        public Builder leagueName(String val) {
            _leagueName = val;
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
        public Builder eventStartTime(String val) {
            _eventStartTime = val;
            return this;
        }
        public Builder eventId(int val) {
            _eventId = val;
            return this;
        }
        public Builder lineID(Long val) {
            _lineID = val;
            return this;
        }
        public Builder betType(String val) {
            _betType = val;
            return this;
        }
        public Builder oddsKey(String val) {
            _oddsKey = val;
            return this;
        }
        public Builder handicap(double val) {
            _handicap = val;
            return this;
        }
        public Builder team(String val) {
            _team = val;
            return this;
        }
        public Builder totalMarket(int val) {
            _totalMarket = val;
            return this;
        }
        public Builder status(String val) {
            _status = val;
            return this;
        }
        public Builder oddsType(String val) {
            _oddsType = val;
            return this;
        }
        public Builder oddsFormat(String val) {
            _oddsFormat = val;
            return this;
        }
        public Builder marketKey(String val) {
            _marketKey = val;
            return this;
        }
        public Builder homeName(String val) {
            _homeName = val;
            return this;
        }
        public Builder awayName(String val) {
            _awayName = val;
            return this;
        }
        public Builder periodId(int val) {
            _periodId = val;
            return this;
        }
        public Builder odds(List<Odds> val) {
            _odds = val;
            return this;
        }

        public Builder altLineID(Long val) {
            _altLineID = val;
            return this;
        }
        public Market build() {
            return new Market(this);
        }

    }
}
