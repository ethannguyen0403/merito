package membersite.objects.sat;

import com.paltech.element.common.Link;

/**
 * @author isabella.huynh
 * @created 10/3/2020
 */
public class FancyMarket {
    private String _eventName;
    private String _eventId;
    private String _competitionName;
    private int _rateNo;
    private int _rateYes;
    private double _oddsYes;
    private double _oddsNo;
    private String _marketType;
    private String _marketName;
    private String _marketID;
    private String _status;
    private int _delayBetting;
    private  int _rebateRaito;
    private String _marketTime;
    private int _ratingExposure;
    private int _minBet;
    private int _maxBet;
    private double _marketLiability;
    private Link _btnYes;
    private Link _btnNo;

    public String getEventName() {
        return _eventName;
    }
    public String getMarketID() {
        return _marketID;
    }
    public String getEventID(){return _eventId;}
    public String getCompetitionName(){return _competitionName;}
    public int getRateNo(){return _rateNo;}
    public int getRateYes(){return _rateYes;}
    public int getMinSetting(){return _minBet;}
    public int getMaxSetting(){return _maxBet;}
    public double getOddsYes(){return _oddsYes;}
    public String getMaketType(){return _marketType;}
    public String getMarketName(){return _marketName;}
    public String getStatus(){
        return _status;
    }
    public int getDelayBetting(){return _delayBetting;}
    public int getRebateRaito(){return _rebateRaito;}
    public String getMarketTime(){return _marketTime;}
    public int getRatingExposure(){return _ratingExposure;}
    public double getOddsNo(){return _oddsNo;}
    public double getMarketLiability(){return _marketLiability;}
    public Link getBtnYes(){return _btnYes;}
    public Link getBtnNo(){return _btnNo;}
    public void setEventNam(String val){ _eventName = val;}
    public void setMaretId(String val){ _marketID = val;}
    public void setEventID(String val){_eventId = val;}
    public void setCompetitionName(String val){_competitionName = val;}
    public void setMin(int val){
        _minBet = val;}
    public void setMmax(int val){
        _maxBet = val;}
    public void setMarketName(String val){ _marketName = val;}
    public void setStatus(String val){_status = val;}
    public void setMarketTime(String val){_marketTime = val;}
    public void setRateNo(int val){_rateNo = val;}
    public void setRateYes(int val){_rateYes = val;}
    public void setMarketType(String val){_marketType = val;}
    public void setDelayBetting(int val){_delayBetting = val;}
    public void setRebateRaito(int val){_rebateRaito = val;}
    public void setOddsYes(double val){_oddsYes = val;}
    public void set_oddsNo(double val){_oddsNo= val;}
    public void setMarketLiability(double val){_marketLiability= val;}
    public void setBtnYes(Link val){_btnYes = val;}
    public void setBtnNo(Link val){_btnNo = val;}

    public static class Builder {
        // Optional parameters
        private String _marketID = "";
        private String _marketName = "";
        private String _eventName = "";
        private String _eventId= "";
        private String _competitionName ="";
        private int _rateNo;
        private int _runYes;
        private int _minBet ;
        private int _maxBet ;
        private double _oddsYes;
        private double _oddsNo;
        private String _marketType;
        private String _status="";
        private int _delayBetting;
        private  int _rebateRaito;
        private String _marketTime="";
        private int _ratingExposure;
        private double _marketLiability;
        private Link _btnYes;
        private Link _btnNo;

        public Builder(){}
        public Builder marketID(String val){
            _marketID = val;
            return this;
        }
        public Builder eventName(String val){
            _eventName = val;
            return this;
        }
        public Builder marketName(String val){
            _marketName = val;
            return this;
        }
        public Builder eventID(String val){_eventId = val; return this;}
        public Builder competitionNAme(String val){_competitionName = val; return this;}
        public Builder rateNo(int val){_rateNo= val; return this;}
        public Builder rateYes(int val){_runYes= val; return this;}
        public Builder minBet(int val){
            _minBet = val; return this;}
        public Builder maxBet(int val){
            _maxBet = val; return this;}
        public Builder oddsYes(double val){_oddsYes= val; return this;}
        public Builder marketType(String val){_marketType =val; return this;}
        public Builder status(String val){_status =val; return this;}
        public Builder delayBetting(int val){_delayBetting = val; return this;}
        public Builder rebateRaito(int val){_rebateRaito = val; return this;}
        public Builder marketTime(String val){_marketTime = val; return this;}
        public Builder oddsNo(double val){_oddsNo = val; return this;}
        public Builder marketLiability(double val){_marketLiability = val; return this;}
        public Builder ratingExposure(int val){_ratingExposure=val; return this;}
        public Builder btnYes(Link val){_btnYes=val; return this;}
        public Builder btnNo(Link val){_btnNo=val; return this;}
        public FancyMarket build() { return new FancyMarket(this); }

    }

    public FancyMarket(Builder builder){
        this._marketID = builder._marketID;
        this._eventName = builder._eventName;
        this._marketName = builder._marketName;
        this._eventId = builder._eventId;
        this._competitionName = builder._competitionName;
        this._rateNo = builder._rateNo;
        this._rateYes = builder._runYes;
        this._minBet = builder._minBet;
        this._maxBet = builder._maxBet;
        this._oddsYes= builder._oddsYes;
        this._marketType= builder._marketType;
        this._status= builder._status;
        this._delayBetting = builder._delayBetting;
        this._rebateRaito = builder._rebateRaito;
        this._marketTime = builder._marketTime;
        this._oddsNo= builder._oddsNo;
        this._ratingExposure = builder._ratingExposure;
        this._marketLiability= builder._marketLiability;
        this._btnNo = builder._btnNo;
        this._btnYes = builder._btnYes;
    }
}
