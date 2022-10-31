package membersite.objects.sat;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;

/**
 * @author isabella.huynh
 * @created 10/3/2020
 */
public class Market {
    private String _eventName;
    private String _eventID;
    private String _marketName;
    private String _marketID;
    private String _selectionName;
    private boolean _isBack;
    private String _odds;
    private Label _btnOdds;
    private String _marketType;
    public String getMarketType() {
        return _marketType;
    }
    private String _marketStartTime;
    private String _marketStatus;

    public void setMarketType(String _marketType) {
        this._marketType = _marketType;
    }

    public String getMarketStartTime() {
        return _marketStartTime;
    }

    public void setMarketStartTime(String _marketStartTime) {
        this._marketStartTime = _marketStartTime;
    }

    public String getMarketStatus() {
        return _marketStatus;
    }

    public void setMarketStatus(String _marketStatus) {
        this._marketStatus = _marketStatus;
    }

    public String getMarketID() {
        return _marketID;
    }

    public void setMarketID(String _marketID) {
        this._marketID = _marketID;
    }

    public String getOdds() {
        return _odds;
    }

    public void setOdds(String _odds) {
        this._odds = _odds;
    }
    public Label getBtnOdd() {
        return _btnOdds;
    }
    public void setBtnOdd(Label btnOdd) {
        _btnOdds = btnOdd;
    }
    public String getEventName() {
        return _eventName;
    }

    public void setEventNamE(String val){ _eventName = val;}

    public String getEventID() {
        return _eventID;
    }

    public void setEventID(String val){ _eventID = val;}

    public String getMarketName(){return _marketName;}
    public void setMarketName(String val){ _marketName = val;}

    public String getSelectionName() {
        return _selectionName;
    }
    public void setselectionName(String val) {
        _selectionName = val;
    }

    public boolean getIsBack() {
        return _isBack;
    }
    public void setIsBAck(boolean val) {
        _isBack = val;
    }

    public static class Builder {
        // Optional parameters
        private String _eventName = "";
        private String _eventID;
        private String _marketName = "";
        private String _selectionName = "";
        private boolean _isBack;
        private String _odds;
        private Label _btnOdds;
        private String _marketType;
        private String _marketID;
        private String _marketStartTime;
        private String _marketStatus;


        public Builder(){}

        public Builder eventName(String val){
            _eventName = val;
            return this;
        }
        public Builder eventID(String val){
            _eventID = val;
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

        public Builder selectionName(String val){
            _selectionName = val;
            return this;
        }
        public Builder odds(String val){
            _odds = val;
            return this;
        }

        public Builder isBack(boolean val){
            _isBack = val;
            return this;
        }

        public Builder marketType(String val) {
            _marketType = val;
            return this;
        }
        public Market.Builder btnOdds(Label val){
            _btnOdds = val;
            return this;
        }
        public Builder marketStartTime(String val) {
            _marketStartTime = val;
            return this;
        }
        public Builder marketStatus(String val) {
            _marketStatus = val;
            return this;
        }
        public Market build() { return new Market(this); }

    }

    private Market(Builder builder){
        this._eventName = builder._eventName;
        this._eventID =builder._eventID;
        this._marketName = builder._marketName;
        this._selectionName = builder._selectionName;
        this._isBack = builder._isBack;
        this._btnOdds = builder._btnOdds;
        this._odds =builder._odds;
        this._marketType = builder._marketType;
        this._marketID = builder._marketID;
        this._marketStatus =builder._marketStatus;
        this._marketStartTime = builder._marketStartTime;
    }
}
