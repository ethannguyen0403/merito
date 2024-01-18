package membersite.objects.proteus;

public class ProteusTeamTotalEvent {
    private int _eventId;
    private String _leagueName;
    private String _homeName;
    private String _awayName;
    private double _homeGoals;
    private double _awayGoals;
    private double _homeOver;
    private double _homeUnder;
    private double _awayOver;
    private double _awayUnder;
    private double _homeHdp;
    private double _awayHdp;
    public ProteusTeamTotalEvent(Builder builder) {
        this._eventId = builder._eventId;
        this._leagueName = builder._leagueName;
        this._homeName = builder._homeName;
        this._awayName = builder._awayName;
        this._homeGoals = builder._homeGoals;
        this._awayGoals = builder._awayGoals;
        this._homeOver = builder._homeOver;
        this._homeUnder = builder._homeUnder;
        this._awayOver = builder._awayOver;
        this._awayUnder = builder._awayUnder;
        this._homeHdp = builder._homeHdp;
        this._awayHdp = builder._awayHdp;
    }
    public int getEventId() {
        return _eventId;
    }

    public void setEventId(int val) {
        _eventId = val;
    }
    public void setLeagueName(String val) {
        _leagueName = val;
    }

    public String getLeagueName() {
        return _leagueName;
    }
    public void setHomeName(String val) {
        _homeName = val;
    }

    public String getHomeName() {
        return _homeName;
    }
    public void setAwayName(String val) {
        _awayName = val;
    }

    public String getAwayName() {
        return _awayName;
    }

    public double getHomeGoals() {
        return _homeGoals;
    }
    public void setHomeGoals(double val) {
        _homeGoals = val;
    }

    public double getAwayGoals() {
        return _awayGoals;
    }
    public void setAwayGoals(double val) {
        _awayGoals = val;
    }
    public double getHomeOver() {
        return _homeOver;
    }
    public void setHomeOver(double val) {
        _homeOver = val;
    }
    public double getHomeUnder() {
        return _homeUnder;
    }
    public void setHomeUnder(double val) {
        _homeUnder = val;
    }

    public double getAwayOver() {
        return _awayOver;
    }
    public void setAwayOver(double val) {
        _awayOver = val;
    }

    public double getAwayUnder() {
        return _awayUnder;
    }
    public void setAwayUnder(double val) {
        _awayUnder = val;
    }
    public double getHomeHdp() {
        return _homeHdp;
    }
    public void setHomeHdp(double val) {
        _homeHdp = val;
    }
    public double getAwayHdp() {
        return _awayHdp;
    }
    public void setAwayHdp(double val) {
        _awayHdp = val;
    }
    public static class Builder {
        // Optional parameters
        private int _eventId;
        private int _leagueId;
        private String _leagueName;
        private String _homeName;
        private String _awayName;
        private double _homeGoals;
        private double _awayGoals;
        private double _homeOver;
        private double _homeUnder;
        private double _awayOver;
        private double _awayUnder;
        private double _homeHdp;
        private double _awayHdp;
        public Builder() {
        }

        public Builder eventId(int val) {
            _eventId = val;
            return this;
        }

        public Builder leagueId(int val) {
            _leagueId = val;
            return this;
        }

        public Builder leagueName(String val) {
            _leagueName = val;
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
        public Builder homeGoals(double val) {
            _homeGoals = val;
            return this;
        }
        public Builder awayGoals(double val) {
            _awayGoals = val;
            return this;
        }
        public Builder homeOver(double val) {
            _homeOver = val;
            return this;
        }
        public Builder homeUnder(double val) {
            _homeUnder = val;
            return this;
        }
        public Builder awayOver(double val) {
            _awayOver = val;
            return this;
        }
        public Builder awayUnder(double val) {
            _awayUnder = val;
            return this;
        }
        public Builder homeHdp(double val) {
            _homeHdp = val;
            return this;
        }
        public Builder awayHdp(double val) {
            _awayHdp = val;
            return this;
        }
        public ProteusTeamTotalEvent build() {
            return new ProteusTeamTotalEvent(this);
        }

    }
}
