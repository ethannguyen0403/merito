package membersite.objects.funsport;

/**
 * @author Isabella.Huynh
 * @created Nov/26/2019
 */
public class Event {
    private String _eventName;
    private String _matchScore;
    private String _inPlay;
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

    private Event(Builder builder) {
        this._eventName = builder._eventName;
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
    }

    public String getEventName() {
        return _eventName;
    }

    public String getMatchScore() {
        return _matchScore;
    }

    public String getInPlay() {
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

    public static class Builder {
        // Optional parameters
        private String _eventName = "";
        private String _matchScore = "";
        private String _inPlay = "";
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

        public Builder() {
        }

        public Builder eventName(String val) {
            _eventName = val;
            return this;
        }

        public Builder matchScore(String val) {
            _matchScore = val;
            return this;
        }

        public Builder inPlay(String val) {
            _inPlay = val;
            return this;
        }

        public Builder home(String val) {
            _home = val;
            return this;
        }

        public Builder homeBack(String val) {
            _homeBack = val;
            return this;
        }

        public Builder homeLay(String val) {
            _homeLay = val;
            return this;
        }

        public Builder away(String val) {
            _away = val;
            return this;
        }

        public Builder awayBack(String val) {
            _awayBack = val;
            return this;
        }

        public Builder awayLay(String val) {
            _awayLay = val;
            return this;
        }

        public Builder drawBack(String val) {
            _drawBack = val;
            return this;
        }

        public Builder drawLay(String val) {
            _drawLay = val;
            return this;
        }

        public Builder draw(String val) {
            _draw = val;
            return this;
        }

        public Builder isSuspend(boolean val) {
            _isSuspend = val;
            return this;
        }

        public Event build() {
            return new Event(this);
        }

    }
}
