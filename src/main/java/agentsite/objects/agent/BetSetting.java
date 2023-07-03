package agentsite.objects.agent;

public class BetSetting {
    private String _userID;
    private String _userCode;
    private String _loginID;
    private double _minBet;
    private double _maxBet;
    private double _maxBetPerMatch;
    private double _maxWinPerMatch;
    private String _totalRecord;
    private String _currency;
    private String _level;
    private String _sportGroup;
    private String _status;
    private String _statusExchange;

    private BetSetting(Builder builder) {
        this._userID = builder._userID;
        this._userCode = builder._userCode;
        this._loginID = builder._loginID;
        this._minBet = builder._minBet;
        this._maxBet = builder._maxBet;
        this._maxBetPerMatch = builder._maxBetPerMatch;
        this._maxWinPerMatch = builder._maxWinPerMatch;
        this._totalRecord = builder._totalRecord;
        this._currency = builder._currency;
        this._level = builder._level;
        this._sportGroup = builder._sportGroup;
        this._status = builder._status;
        this._statusExchange = builder._statusExchange;
    }

    public String get_sportGroup() {
        return _sportGroup;
    }

    public void set_sportGroup(String _sportGroup) {
        this._sportGroup = _sportGroup;
    }

    public String get_status() {
        return _status;
    }

    public void set_status(String _status) {
        this._status = _status;
    }

    public String get_statusExchange() {
        return _statusExchange;
    }

    public void set_statusExchange(String _statusExchange) {
        this._statusExchange = _statusExchange;
    }

    public String getUserID() {
        return _userID;
    }

    public String getUserCode() {
        return _userCode;
    }

    public String getLoginID() {
        return _loginID;
    }

    public double getMinBet() {
        return _minBet;
    }

    public double getMaxBet() {
        return _maxBet;
    }

    public double getMaxBetPerMatch() {
        return _maxBetPerMatch;
    }

    public double getMaxWinPerMatch() {
        return _maxWinPerMatch;
    }

    public String getTotalRecord() {
        return _totalRecord;
    }

    public String getCurrency() {
        return _currency;
    }

    public String getLevel() {
        return _level;
    }

    public static class Builder {
        // Optional parameters
        private String _userID = "";
        private String _userCode = "";
        private String _loginID = "";
        private double _minBet;
        private double _maxBet;
        private double _maxBetPerMatch;
        private double _maxWinPerMatch;
        private String _totalRecord = "";
        private String _currency = "";
        private String _level = "";
        private String _sportGroup;
        private String _status;
        private String _statusExchange;

        public Builder() {
        }

        public Builder userID(String val) {
            _userID = val;
            return this;
        }

        public Builder userCode(String val) {
            _userCode = val;
            return this;
        }

        public Builder loginID(String val) {
            _loginID = val;
            return this;
        }

        public Builder minBet(double val) {
            _minBet = val;
            return this;
        }

        public Builder maxBet(double val) {
            _maxBet = val;
            return this;
        }

        public Builder maxBetPerMatch(double val) {
            _maxBetPerMatch = val;
            return this;
        }

        public Builder maxWinPerMatch(double val) {
            _maxWinPerMatch = val;
            return this;
        }

        public Builder totalRecord(String val) {
            _totalRecord = val;
            return this;
        }

        public Builder currency(String val) {
            _currency = val;
            return this;
        }

        public Builder level(String val) {
            _level = val;
            return this;
        }

        public Builder sportGroup(String val) {
            _sportGroup = val;
            return this;
        }

        public Builder status(String val) {
            _status = val;
            return this;
        }

        public Builder statusExchange(String val) {
            _statusExchange = val;
            return this;
        }

        public BetSetting build() {
            return new BetSetting(this);
        }

    }


}
