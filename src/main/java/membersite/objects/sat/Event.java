package membersite.objects.sat;

import com.paltech.element.common.Link;

import java.util.List;

/**
 * @author isabella.huynh
 * @created 10/3/2020
 */
public class Event {
    private String _id;
    private String _eventName;
    private String _competitionName;
    private String _matchScore;
    private boolean _inPlay;
    private boolean _isSuspend;
    private String _home;
    private String _homeBack;
    private String _homeLay;
    private String _draw;
    private String _drawBack;
    private String _drawLay;
    private String _away;
    private String _awayBack;
    private String _awayLay;
    private Link _lnkEvent;
    private String _startTime;
    private List<Market> _listMarket;

    public String getID() {
        return _id;
    }
    public void setID(String val) {
        _id = val;
    }

    public String getEventName() {
        return _eventName;
    }

    public List<Market> getListMarkets() {
        return _listMarket;
    }
    public void setListMarkets(List<Market> val) {
        _listMarket=val;
    }
    public String getCompetitionName() {
        return _competitionName;
    }
    public void setCompetitionName(String val) {
        _competitionName=val;
    }

    public String getMatchScore() {
        return _matchScore;
    }

    public boolean getInPlay() {
        return _inPlay;
    }

    public String getHome() {
        return _home;
    }

    public void setHome(String val) {
        _home = val;
    }

    public String getHomeBack() {
        return _homeBack;
    }

    public void setHomeBack(String val) {
        _homeBack = val;
    }

    public String getHomeLay() {
        return _homeLay;
    }

    public void setHomeLay(String val) {
        _homeLay = val;
    }

    public String getStartTime() {
        return _startTime;
    }

    public void setStartTime(String val) {
        _startTime = val;
    }

    public String getAway() {
        return _away;
    }
    public String getAwayBack() {
        return _awayBack;
    }
    public String getAwayLay() {
        return _awayLay;
    }
    public String getRaw() {
        return _draw;
    }
    public String getRawBack() {
        return _drawBack;
    }
    public String getRawLay() {
        return _drawLay;
    }
    public boolean getSuspend() {
        return _isSuspend;
    }

    public Link getLinkEvent() {
        return _lnkEvent;
    }
    public void setLinkEvent( Link val) {
       _lnkEvent = val;
    }

    public static class Builder {
        // Optional parameters
        private String _id = "";
        private String _eventName = "";
        private String _competitionName = "";
        private String _matchScore = "";
        private boolean _inPlay = false;
        private String _home = "";
        private String _homeBack = "";
        private String _homeLay = "";
        private String _away = "";
        private String _awayBack = "";
        private String _awayLay = "";
        private String _draw = "x";
        private String _drawBack = "";
        private String _drawLay = "";
        private boolean _isSuspend = false;
        private Link _lnkEvent;
        private String _startTime="";
        private List<Market> _listMarket;
        public Builder(){}

        public Builder listMarket(List<Market> val){
            _listMarket = val;
            return this;
        }

        public Link lnkEvent() {
            return _lnkEvent;
        }
        public Builder id(String val){
            _id = val;
            return this;
        }
        public Builder eventName(String val){
            _eventName = val;
            return this;
        }
        public Builder competitionName(String val){
            _competitionName = val;
            return this;
        }

        public Builder matchScore(String val){
            _matchScore = val;
            return this;
        }
        public Builder startTime(String val){
            _startTime = val;
            return this;
        }

        public Builder inPlay(boolean val){
            _inPlay = val;
            return this;
        }

        public Builder home(String val){
            _home = val;
            return this;
        }

        public Builder homeBack(String val){
            _homeBack = val;
            return this;
        }

        public Builder homeLay(String val){
            _homeLay = val;
            return this;
        }
        public Builder away(String val){
            _away = val;
            return this;
        }
        public Builder awayBack(String val){
            _awayBack = val;
            return this;
        }
        public Builder awayLay(String val){
            _awayLay = val;
            return this;
        }
        public Builder drawBack(String val){
            _drawBack = val;
            return this;
        }
        public Builder drawLay(String val){
            _drawLay = val;
            return this;
        }
        public Builder draw(String val){
            _draw = val;
            return this;
        }
        public Builder isSuspend(boolean val){
            _isSuspend = val;
            return this;
        }

        public Builder lnkEvent(Link val){
            _lnkEvent = val;
            return this;
        }
        public Event build() { return new Event(this); }

    }

    private Event(Builder builder){
        this._id = builder._id;
        this._eventName = builder._eventName;
        this._competitionName = builder._competitionName;
        this._matchScore = builder._matchScore;
        this._home = builder._home;
        this._homeBack = builder._homeBack;
        this._homeLay = builder._homeLay;
        this._away = builder._away;
        this._awayBack = builder._awayBack;
        this._awayLay = builder._awayLay;
        this._draw = builder._draw;
        this._drawBack = builder._drawBack;
        this._drawLay = builder._drawLay;
        this._isSuspend = builder._isSuspend;
        this._lnkEvent = builder._lnkEvent;
        this._startTime = builder._startTime;
        this._listMarket = builder._listMarket;
    }
}
