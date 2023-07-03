package api.objects.meritoAPI;

public class BetSettingGroup {
    private double _minBet;
    private double _maxBet;
    private double _maxWinPerMatch;
    private double _maxBetPerMatch;

    public BetSettingGroup(Builder builder) {
        this._minBet = builder._minBet;
        this._maxBet = builder._maxBet;
        this._maxBetPerMatch = builder._maxBetPerMatch;
        this._maxWinPerMatch = builder._maxWinPerMatch;
    }

    public double getMinBet() {
        return _minBet;
    }

    public void setMinBet(double val) {
        _minBet = val;
    }

    public double getMaxBet() {
        return _maxBet;
    }

    public void setMaxBet(double val) {
        _maxBet = val;
    }

    public double getMaxMinPerMatch() {
        return _maxWinPerMatch;
    }

    public void setMaxMinPerMatch(double val) {
        _maxWinPerMatch = val;
    }

    public double getMaxBetPerMatch() {
        return _maxBetPerMatch;
    }

    public void setMaxBetPerMatch(double val) {
        _maxBetPerMatch = val;
    }

    public static class Builder {
        private double _minBet;
        private double _maxBet;
        private double _maxWinPerMatch;
        private double _maxBetPerMatch;

        public Builder() {
        }

        public Builder minBet(double val) {
            _minBet = val;
            return this;
        }

        public Builder maxBet(double val) {
            _maxBet = val;
            return this;
        }

        public Builder maxWinPerMatch(double val) {
            _maxWinPerMatch = val;
            return this;
        }

        public Builder maxBetPerMatch(double val) {
            _maxBetPerMatch = val;
            return this;
        }

        public BetSettingGroup build() {
            return new BetSettingGroup(this);
        }
    }
}
