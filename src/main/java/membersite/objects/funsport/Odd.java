package membersite.objects.funsport;

import com.paltech.element.common.Icon;
import com.paltech.element.common.Link;

/**
 * @author Isabella.Huynh
 * @created Nov/26/2019
 */
public class Odd {
    private String _eventName;
    private String _matchScore;
    private String _inPlay;
    private boolean _isInPlay;
    private String _selectedTeam;
    private String _oddRate;
    private Link _lnkOdd;
    private String _stake;
    private String _liability;
    private String _profit;
    private String _currency;
    private int _eventIndex;
    private Icon _icFavorite;
    private String _dataEventName;

    public String getEventName() {
        return _eventName;
    }
    public String getDataEventName() {
        return _dataEventName;
    }

    public String getMatchScore() {
        return _matchScore;
    }

    public String getInPlay() {
        return _inPlay;
    }

    public boolean getIsInPlay() {
        return _isInPlay;
    }

    public String getSelectedTeam() {
        return _selectedTeam;
    }

    public String getOddRate() {
        return _oddRate;
    }

    public Link getOdd() {
        return _lnkOdd;
    }
    public Icon getCcFavorite() {
        return _icFavorite;
    }

    public String getStake() {
        return _stake;
    }
    public String getProfit() {
        return _profit;
    }

    public void setStake(String val) {
        _stake = val;
    }

    public String getLiability() {
        return _liability;
    }

    public void setProfit(String val) {
        _profit = val;
    }

    public String getCurrency() {
        return _currency;
    }
    public int getEventIndex() {
        return _eventIndex;
    }

    public static class Builder {
        // Optional parameters
        private String _eventName = "";
        private String _matchScore = "";
        private String _inPlay = "";
        private boolean _isInPlay = false;
        private String _selectedTeam = "";
        private String _oddRate = "";
        private Link _lnkOdd;
        private Icon _icFavorite;
        private String _stake = "";
        private String _liability = "";
        private String _profit = "";
        private String _currency = "";
        private int _eventIndex = 0;
        private String _dataEventName = "";
        public Builder(){}

        public Builder eventName(String val){
            _eventName = val;
            return this;
        }

        public Builder dataEventName(String val){
            _dataEventName = val;
            return this;
        }

        public Builder matchScore(String val){
            _matchScore = val;
            return this;
        }

        public Builder inPlay(String val){
            _inPlay = val;
            return this;
        }

        public Builder isInPlay(boolean val){
            _isInPlay = val;
            return this;
        }

        public Builder selectedTeam(String val){
            _selectedTeam = val;
            return this;
        }

        public Builder oddRate(String val){
            _oddRate = val;
            return this;
        }

        public Builder lnkOdd(Link val){
            _lnkOdd = val;
            return this;
        }
        public Builder icFavorite(Icon val){
            _icFavorite = val;
            return this;
        }

        public Builder stake(String val){
            _stake = val;
            return this;
        }
        public Builder liability(String val){
            _liability = val;
            return this;
        }
        public Builder profit(String val){
            _profit = val;
            return this;
        }
        public Builder currency(String val){
            _currency = val;
            return this;
        }
        public Builder eventIndex(int val){
            _eventIndex = val;
            return this;
        }

        public Odd build() { return new Odd(this); }

    }

    private Odd(Builder builder){
        this._eventName = builder._eventName;
        this._dataEventName = builder._dataEventName;
        this._matchScore = builder._matchScore;
        this._inPlay = builder._inPlay;
        this._isInPlay = builder._isInPlay;
        this._selectedTeam = builder._selectedTeam;
        this._oddRate = builder._oddRate;
        this._lnkOdd = builder._lnkOdd;
        this._stake = builder._stake;
        this._liability = builder._liability;
        this._profit = builder._profit;
        this._currency = builder._currency;
        this._eventIndex = builder._eventIndex;
        this._icFavorite = builder._icFavorite;
    }
}
