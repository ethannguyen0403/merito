package membersite.objects.sat;

import com.paltech.element.common.Link;

/**
 * @author isabella.huynh
 * @created 12/3/2020
 */
public class Order {
    private String _eventID;
    private String _eventName;
    private String _marketId;
    private String _marketName;
    private String _selectionName;
    private String _selectionId;
    private boolean _isBack;
    private String _stake;
    private String _odds;
    private String _persistenceType;
    private String _stakeMatched;
    private String _newOdds;
    private String _orderID;
    private String _placedDate;
    private String _matchedDate;
    private String _profit;
    private String _liability;
    private String _errorMessage;

    public Order() {

    }

    public String getEventID(){return  _eventID;}
    public String getMarketID(){return  _marketId;}
    public String get_selectionID(){return  _selectionId;}
    public String getEventName(){return  _eventName;}
    public String getMarketName(){return  _marketName;}
    public String getSelectionName(){return  _selectionName;}
    public boolean getIsBack(){return _isBack;}
    public String getStake(){return _stake;}
    public String getOdds(){return _odds;}
    public String getPersistenceType(){return _persistenceType;}
    public String getStakeMatched(){return _stakeMatched;}
    public String getNewOdds(){return _newOdds;}
    public String getOrderID(){return _orderID;}
    public String getPlaceDate(){return _placedDate;}
    public String getMatchedDate(){return _matchedDate;}
    public String getErrorMessage(){return _errorMessage;}
    public String getProfit(){return  _profit;}
    public String getLiability(){return  _liability;}

    public void setEventID(String val){_eventID = val;}
    public void setMarketID(String val){_marketId = val;}
    public void setSelectionId(String val){_selectionId = val;}
    public void setEventName(String val){_eventName = val;}
    public void setMarketName(String val){_marketName = val;}
    public void setSelectionName(String val){_selectionName = val;}
    public void setStake(String val){_stake = val;}
    public void setIsBack(boolean val){_isBack = val;}
    public void setOdds(String val){_odds=val;}
    public void setPersistenceType(String val){_persistenceType = val;}
    public void setStakeMatched(String val){_stakeMatched = val;}
    public void setNewOdds(String val){_newOdds = val;}
    public void setOdrerID(String val){_orderID =val;}
    public void setPlacedDate(String val){_placedDate = val;}
    public void setMatchedDate(String val){_matchedDate = val;}
    public void setErrorMessage(String val){_errorMessage =val;}
    public void setProfit(String val){_profit =val;}
    public void setLiability(String val){_liability =val;}



    public static class Builder {
        // Optional parameters;
        private String _eventID ;
        private String _marketID;
        private String _selectionID;
        private String _eventName;
        private String _marketName;
        private String _selectionName;
        private boolean _isBack;
        private String _stake;
        private String _odds;
        private String _persistenceType;
        private String _stakeMatched;
        private String _newOdds;
        private String _orderID;
        private String _placedDate;
        private String _matchedDate;
        private String _errorMessage;
        private String _profit;
        private String _liabilty;

        public Builder(){}

        public Builder eventID(String val){
            _eventID = val;
            return this;
        }
        public Builder marketID(String val){
            _marketID = val;
            return this;
        }
        public Builder selectionId(String val){
            _selectionID = val;
            return this;
        }
        public Builder eventName(String val){
            _eventName = val;
            return this;
        }
        public Builder selectionName(String val){
            _selectionName = val;
            return this;
        }
        public Builder marketName(String val){
            _marketName = val;
            return this;
        }
        public Builder isBack(boolean val){
            _isBack = val;
            return this;
        }
        public Builder stake(String val){
            _stake = val;
            return this;
        }
        public Builder odds(String val){
            _odds = val;
            return this;
        }
        public Builder persistenceType(String val){
            _persistenceType = val;
            return this;
        }
        public Builder orderID(String val){
            _orderID = val;
            return this;
        }
        public Builder stakeMatched(String val){
            _stakeMatched = val;
            return this;
        }
        public Builder matchedDate(String val){
            _matchedDate = val;
            return this;
        }
        public Builder placeDate(String val){
            _placedDate = val;
            return this;
        }
        public Builder errorMessage(String val){
            _errorMessage = val;
            return this;
        }
        public Builder newOdds(String val){
            _newOdds = val;
            return this;
        }
        public Builder profit(String val){
            _profit = val;
            return this;
        }
        public Builder liablity(String val){
            _liabilty = val;
            return this;
        }
        public Order build() { return new Order(this); }

    }

    public Order(Builder builder){
        this._eventID = builder._eventID;
        this._marketId = builder._marketID;
        this._selectionId = builder._selectionID;
        this._eventName = builder._eventName;
        this._marketName =builder._marketName;
        this._selectionName=builder._selectionName;
        this._isBack = builder._isBack;
        this._stake = builder._stake;
        this._odds = builder._odds;
        this._newOdds = builder._newOdds;
        this._persistenceType=builder._persistenceType;
        this._orderID=builder._orderID;
        this._stakeMatched=builder._stakeMatched;
        this._matchedDate= builder._matchedDate;
        this._placedDate=builder._placedDate;
        this._errorMessage = builder._errorMessage;
        this._profit = builder._profit;
        this._liability = builder._liabilty;

    }

    public Double getProfit(boolean isBack, double odds, double stake)
    {
        return isBack?stake*(odds-1):stake;
    }
    public Double getLiablity(boolean isBack, double odds, double stake)
    {
        return isBack?stake:stake*(odds-1);
    }
}
