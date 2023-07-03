package api.objects.meritoAPI;

import java.util.ArrayList;
import java.util.List;


public class Market {
    private String _marketId;
    private String _marketName;
    private String _marketStartTime;
    private String _status;
    private int _betDelay;
    private boolean _inplay;
    private int _numberOfWinners;
    private int _numberOfRunners;
    private int _numberOfActiveRunners;
    private double _totalMatch;
    private double _totalAvailable;
    private MarketDescription _marketDescription;
    private SportType _eventType; // sport name and id
    private Competition _competition;
    private Event _event;
    private List<Selection> _selectionList = new ArrayList<Selection>();

    public String getMarketId() {
        return _marketId;
    }

    public void setMarketId(String _marketId) {
        this._marketId = _marketId;
    }

    public String getMarketName() {
        return _marketName;
    }

    public void setMarketName(String _marketName) {
        this._marketName = _marketName;
    }

    public String getMarketStartTime() {
        return _marketStartTime;
    }

    public void setMarketStartTime(String _marketStartTime) {
        this._marketStartTime = _marketStartTime;
    }
    
    public String getStatus(){return _status;}
    public void setStatus(String val){_status = val;}
    public int getbetDelay() {
        return _betDelay;
    }

    public void setbetDelay(int _betDelay) {
        this._betDelay = _betDelay;
    }
    public boolean getInplay(){return _inplay;}
    public void setInPlay(boolean val){_inplay = val;}
    
    public int getNumberOfWinners(){return _numberOfWinners;}
    public void setNumberOfWinners(int val){_numberOfWinners = val;}

    public double getTotalMatch(){return _totalMatch;}
    public void setTotalMatch(double val){_totalMatch = val;}
    
    public int getNumberOfRunner(){return _numberOfRunners;}
    public void setNumbreOfRunner(int val){_numberOfRunners= val;}
    public int getNumberOfActiveRunners(){return _numberOfActiveRunners;}
    public void setNumberOfActiveRunners(int val){_numberOfActiveRunners= val;}

    public double getTotalAvailable(){return _totalAvailable;}
    public void gstTotalAvailable(double val){_totalAvailable = val;}
    
    public SportType getSportType() {
        return _eventType;
    }

    public void setSportType(SportType _eventType) {
        this._eventType = _eventType;
    }

    public Competition getCompetition() {
        return _competition;
    }

    public void setCompetition(Competition _competition) {
        this._competition = _competition;
    }

    public Event getEvent() {
        return _event;
    }

    public void setEvent(Event _event) {
        this._event = _event;
    }

    public MarketDescription getMarketDescription() {
        return _marketDescription;
    }

    public void setMarketDescription(MarketDescription _event) {
        this._marketDescription = _event;
    }

    public List<Selection> getSelectionList() {
        return _selectionList;
    }

    public void setSelectionList(List<Selection> _selectionList) {
        this._selectionList = _selectionList;
    }

    public static class Builder {
        private String _marketId;
        private String _marketName;
        private String _marketStartTime;
        private String _status;
        private int _betDelay;
        private boolean _inplay;
        private int _numberOfWinners;
        private int _numberOfRunners;
        private int _numberOfActiveRunners;
        private double _totalMatch;
        private double _totalAvailable;
        private MarketDescription _marketDescription;
        private SportType _eventType; // sport name and id
        private Competition _competition;
        private Event _event;
        private List<Selection> _selectionList;
        public Builder(){}

        public Builder selectionList(List<Selection> val){
            _selectionList =val;
            return this;
        }
        public Builder marketId(String val){
            _marketId =val;
            return this;
        }
        public Builder marketName(String val){
            _marketName =val;
            return this;
        }
        public Builder marketStartTime(String val){
            _marketStartTime =val;
            return this;
        }
        public Builder status(String val){
            _status =val;
            return this;
        }
        public Builder betDelay(int val){
            _betDelay=val;
            return this;
        }
        public Builder inPlay(boolean val){
            _inplay =val;
            return this;
        }
        public Builder numberOfActiveRunners(int val){
            _numberOfActiveRunners =val;
            return this;
        }
        public Builder numberOfWinners(int val){
            _numberOfWinners =val;
            return this;
        }
        public Builder numberOfRunners(int val){
            _numberOfRunners =val;
            return this;
        }
        public Builder totalMatch(double val){
            _totalMatch =val;
            return this;
        }
        public Builder totalAvailable(double val){
            _totalAvailable =val;
            return this;
        }
        public Builder eventType(SportType val){
            _eventType =val;
            return this;
        }
        public Builder competition(Competition val){
            _competition =val;
            return this;
        }
        public Builder event(Event val){
            _event =val;
            return this;
        }
        public Builder marketDescription(MarketDescription val) {
            _marketDescription = val;
            return this;
        }

        public Market build() { return new Market(this); }
    }

    public Market(Builder builder){
      this._marketId =builder._marketId;
      this._marketName = builder._marketName;
      this._status = builder._status;
      this._betDelay = builder._betDelay;
      this._inplay = builder._inplay;
      this._marketStartTime = builder._marketStartTime;
      this._totalMatch = builder._totalMatch;
      this._numberOfRunners = builder._numberOfRunners;
      this._numberOfWinners = builder._numberOfWinners;
      this._numberOfActiveRunners = builder._numberOfActiveRunners;
      this._competition = builder._competition;
      this._event = builder._event;
      this._eventType = builder._eventType;
      this._totalAvailable = builder._totalAvailable;
      this._marketDescription = builder._marketDescription;
      this._selectionList = builder._selectionList;
    }
}
