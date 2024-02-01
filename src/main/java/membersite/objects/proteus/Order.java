package membersite.objects.proteus;

import java.util.List;

import static common.ProteusConstant.*;

public class Order {
    private Market _market;
    private String _placeDate;
    private Odds _odds;
    private double _stake;
    private double _oddsAccept;// odds match by provider
    private String _status;
    private int _orderID;

    public Market getMarket() {
        return _market;
    }

    public void setMarket(Market _market) {
        this._market = _market;
    }

    public String getPlaceDate() {
        return _placeDate;
    }

    public void setPlaceDate(String _placeDate) {
        this._placeDate = _placeDate;
    }

    public Odds getOdds() {
        return _odds;
    }

    public void setOdds(Odds _odds) {
        this._odds = _odds;
    }

    public double getStake() {
        return _stake;
    }

    public void setStake(double _stake) {
        this._stake = _stake;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String _status) {
        this._status = _status;
    }

    public int getOrderID() {
        return _orderID;
    }

    public void setOrderID(int _orderID) {
        this._orderID = _orderID;
    }
    public double getOddsAccept() {
        return _oddsAccept;
    }

    public void setOddsAccept(double _oddsAccept) {
        this._oddsAccept = _oddsAccept;
    }

    public String getOddsSign(){
        String oddsSign = ODDS_SIGN_MAPPING.get(_market.getOddsType().toUpperCase());
        String marketType = _market.getBetType();
        // handle odds display is decimal if the order is place on 1X2/Moneylin market (match, 1st Haft)
        if(marketType.equals("MONEYLINE"))
            oddsSign =ODDS_SIGN_MAPPING.get(DECIMAL.toUpperCase());
        return oddsSign;
    }

    public double getRisk(){
        double risk = _stake;
        if(_market.getOddsType().equalsIgnoreCase(MALAY))
        {
            // Malay odds, except for moneyline market, Negative odds is has other formular
            if(!_market.getBetType().equalsIgnoreCase("MONEYLINE")){
                if(_odds.getOdds()<0)
                    risk = (_stake * Math.abs(_odds.getOdds()));
            }

        }
        if(_market.getOddsType().equalsIgnoreCase(AMERICAN))
        {
            if(_odds.getOdds()<0)
                risk = (_stake * Math.abs(_odds.getOdds()))/100;
        }
        //handle to get Risk for the other odds type at here
        return risk;
    }
    public double getWin(){
        double win = _stake;
        if(_market.getOddsType().equalsIgnoreCase(DECIMAL))
            win = _stake * (_odds.getOdds()-1);

        if(_market.getOddsType().equalsIgnoreCase(HONGKONG))
            win = _stake * _odds.getOdds();

        if(_market.getOddsType().equalsIgnoreCase(MALAY))
        {
            // Malay odds, except for moneyline market, positive odds has Malay odds formular to calculate Win
            if(!_market.getBetType().equalsIgnoreCase("MONEYLINE")){
                if(_odds.getOdds()>=0)
                    win = _stake * _odds.getOdds();
            }

        }
        if(_market.getOddsType().equalsIgnoreCase(AMERICAN))
        {
            if(_odds.getOdds()>=0)
                win = (_stake * 100)/Math.abs(_odds.getOdds());
        }
        //handle to get Risk for the orther odds type
        return win;
    }

    public Order(Builder builder) {
            this._market = builder._market;
            this._placeDate = builder._placeDate;
            this._odds = builder._odds;
            this._stake = builder._stake;
            this._status = builder._status;
            this._orderID = builder._orderID;
            this._oddsAccept = builder._oddsAccept;
    }
    public static class Builder {
        private Market _market;
        private String _placeDate;
        private Odds _odds;
        private double _stake;
        private String _status;
        private int _orderID;
        private double _oddsAccept;
        public Builder() {
        }
        public Builder oddsAccept(double val) {
            _oddsAccept = val;
            return this;
        }
        public Builder market(Market val) {
            _market = val;
            return this;
        }
        public Builder placeDate(String val) {
            _placeDate = val;
            return this;
        }
        public Builder odds(Odds val) {
            _odds = val;
            return this;
        }
        public Builder stake(double val) {
            _stake = val;
            return this;
        }
        public Builder status(String val) {
            _status = val;
            return this;
        }
        public Builder orderID(int val) {
            _orderID = val;
            return this;
        }
        public Order build() {
            return new Order(this);
        }

    }
}
