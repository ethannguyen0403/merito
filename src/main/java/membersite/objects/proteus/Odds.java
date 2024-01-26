package membersite.objects.proteus;

public class Odds {
    private double _odds;
    private double _hdp;
    private String _team;
    private String _side;
    private double _originalOdds;
    public Odds(Builder builder) {
        this._odds = builder._odds;
        this._team = builder._team;
        this._side = builder._side;
        this._originalOdds = builder._originalOdds;
    }

    public double getOdds() {
        return _odds;
    }

    public void setOdds(double _odds) {
        this._odds = _odds;
    }

    public double getHdp() {
        return _hdp;
    }

    public void setHdp(double _hdp) {
        this._hdp = _hdp;
    }

    public String getTeam() {
        return _team;
    }

    public void setTeam(String _team) {
        this._team = _team;
    }

    public String getSide() {
        return _side;
    }

    public void setSide(String _side) {
        this._side = _side;
    }

    public double getOriginalOdds() {
        return _originalOdds;
    }

    public void setOriginalOdds(double _originalOdds) {
        this._originalOdds = _originalOdds;
    }


    public static class Builder {
        private double _odds;
        private double _hdp;
        private String _team;
        private String _side;
        private double _originalOdds;
        public Builder() {
        }

        public Builder odds(double val) {
            _odds = val;
            return this;
        }

        public Builder hdp(double val) {
            _hdp = val;
            return this;
        }

        public Builder team(String val) {
            _team = val;
            return this;
        }

        public Builder side(String val) {
            _side = val;
            return this;
        }

        public Builder originalOdds(double val) {
            _originalOdds = val;
            return this;
        }

        public Odds build() {
            return new Odds(this);
        }

    }
}
