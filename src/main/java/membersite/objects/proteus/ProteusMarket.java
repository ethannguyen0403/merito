package membersite.objects.proteus;

public class ProteusMarket {
    private int _eventId;
    private double _firstOdds;
    private double _firstOriginalOdds;
    private String _firstSelectionName;
    private double _secondOdds;
    private double _secondOriginalOdds;
    private String _secondSelectionName;
    private double _thirdOdds;
    private double _thirdOriginalOdds;
    private String _thirdSelectionName;
    private String _betType;
    private String _oddsFormat;
    private double _maxBet;

    public ProteusMarket(Builder builder) {
        this._eventId = builder._eventId;
        this._firstOdds = builder._firstOdds;
        this._firstOriginalOdds = builder._firstOriginalOdds;
        this._firstSelectionName = builder._firstSelectionName;
        this._secondOdds = builder._secondOdds;
        this._secondOriginalOdds = builder._secondOriginalOdds;
        this._secondSelectionName = builder._secondSelectionName;
        this._thirdOdds = builder._thirdOdds;
        this._thirdOriginalOdds = builder._thirdOriginalOdds;
        this._thirdSelectionName = builder._thirdSelectionName;
        this._secondOdds = builder._secondOdds;
        this._thirdOdds = builder._thirdOdds;
        this._betType = builder._betType;
        this._oddsFormat = builder._oddsFormat;
        this._maxBet = builder._maxBet;
    }

    public int getEventId() {
        return _eventId;
    }

    public void setEventId(int val) {
        _eventId = val;
    }

    public double getFirstOdds() {
        return _firstOdds;
    }

    public void setFirstOdds(double val) {
        _firstOdds = val;
    }

    public double getFirstOriginalOdds() {
        return _firstOriginalOdds;
    }

    public void setFirstOriginalOdds(double val) {
        _firstOriginalOdds = val;
    }

    public String getFirstSelectionName() {
        return _firstSelectionName;
    }

    public void setFirstSelectionName(String val) {
        _firstSelectionName = val;
    }

    public double getSecondOdds() {
        return _secondOdds;
    }

    public void setSecondOdds(double val) {
        _secondOdds = val;
    }

    public double getSecondOriginalOdds() {
        return _secondOriginalOdds;
    }

    public void setSecondOriginalOdds(double val) {
        _secondOriginalOdds = val;
    }

    public String getSecondSelectionName() {
        return _secondSelectionName;
    }

    public void setSecondSelectionName(String val) {
        _secondSelectionName = val;
    }

    public double getThirdOdds() {
        return _thirdOdds;
    }

    public void setThirdOdds(double val) {
        _thirdOdds = val;
    }

    public double getThirdOriginalOdds() {
        return _thirdOriginalOdds;
    }

    public void setThirdOriginalOdds(double val) {
        _thirdOriginalOdds = val;
    }

    public String getThirdSelectionName() {
        return _thirdSelectionName;
    }

    public void setThirdSelectionName(String val) {
        _thirdSelectionName = val;
    }

    public String getBetType() {
        return _betType;
    }

    public void setBetType(String val) {
        _betType = val;
    }

    public String getOddsFormat() {
        return _oddsFormat;
    }

    public void setOddsFormat(String val) {
        _oddsFormat = val;
    }

    public double getMaxBet() {
        return _maxBet;
    }

    public void setMaxBet(double val) {
        _maxBet = val;
    }

    public static class Builder {
        // Optional parameters
        private int _eventId;
        private double _firstOdds;
        private double _secondOdds;
        private double _thirdOdds;
        private double _firstOriginalOdds;
        private double _secondOriginalOdds;
        private double _thirdOriginalOdds;
        private String _firstSelectionName;
        private String _secondSelectionName;
        private String _thirdSelectionName;
        private String _betType;
        private String _oddsFormat;
        private double _maxBet;

        public Builder() {
        }

        public Builder eventId(int val) {
            _eventId = val;
            return this;
        }

        public Builder firstOdds(double val) {
            _firstOdds = val;
            return this;
        }

        public Builder secondOdds(double val) {
            _secondOdds = val;
            return this;
        }

        public Builder thirdOdds(double val) {
            _thirdOdds = val;
            return this;
        }

        public Builder firstOriginalOdds(double val) {
            _firstOriginalOdds = val;
            return this;
        }

        public Builder secondOriginalOdds(double val) {
            _secondOriginalOdds = val;
            return this;
        }

        public Builder thirdOriginalOdds(double val) {
            _thirdOriginalOdds = val;
            return this;
        }
        public Builder maxBet(double val) {
            _maxBet = val;
            return this;
        }

        public Builder eventID(int val) {
            _eventId = val;
            return this;
        }

        public Builder firstSelectionName(String val) {
            _firstSelectionName = val;
            return this;
        }
        public Builder secondSelectionName(String val) {
            _secondSelectionName = val;
            return this;
        }
        public Builder thirdSelectionName(String val) {
            _thirdSelectionName = val;
            return this;
        }
        public Builder betType(String val) {
            _betType = val;
            return this;
        }
        public Builder oddsFormat(String val) {
            _oddsFormat = val;
            return this;
        }

        public ProteusMarket build() {
            return new ProteusMarket(this);
        }

    }
}
