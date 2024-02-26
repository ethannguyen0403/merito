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
        this._hdp = builder._hdp;
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

    public double getProbability(Odds odds) {
        return 1 / odds.getOdds();
    }

    private double getFairProbability(Odds odds, double vig) {
        return getProbability(odds) / vig;
    }

    private double getFattenedProbability(Odds odds, double vig, double vigAdjustment) {
        return getFairProbability(odds, vig) * (vig + (vigAdjustment /100));
    }

    public double getDECOdds(Odds odds, double vig, double vigAdjustment) {
        double beforeRoundingOdds = 1/ getFattenedProbability(odds, vig, vigAdjustment);
        if (beforeRoundingOdds > 2) {
            return Math.floor(beforeRoundingOdds * 100) / 100;
        } else {
            return Math.floor(beforeRoundingOdds * 1000) / 1000;
        }
    }

    public double getHKOdds(Odds odds, double vig, double vigAdjustment) {
        return getDECOdds(odds, vig, vigAdjustment) - 1;
    }

    public double getMYOdds(Odds odds, double vig, double vigAdjustment) {
        double decOdds = getDECOdds(odds, vig, vigAdjustment);
        if(decOdds <= 2) {
            return decOdds - 1;
        } else {
            return Math.floor((-1 / (decOdds - 1)) * 1000) / 1000;
        }
    }

    public double getAMOdds(Odds odds, double vig, double vigAdjustment) {
        double decOdds = getDECOdds(odds, vig, vigAdjustment);
        if(decOdds < 2) {
            return Math.ceil(100 / (decOdds - 1)) * (-1);
        } else {
            return (decOdds - 1) * 100;
        }
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
