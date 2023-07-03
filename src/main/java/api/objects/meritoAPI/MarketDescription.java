package api.objects.meritoAPI;


public class MarketDescription {
    private boolean _persistenceEnabled;
    private String _marketTime;
    private String _suspendTime;
    private String _bettingType;
    private boolean _turnInPlayEnabled;
    private String _marketType;
    private String _regulator;
    private int _marketBaseRate;
    private boolean _discountAllowed;
    private String _wallet;
    private String _rules;
    private boolean _rulesHasDate;
    private int _eachWayDivisor;

    public MarketDescription(Builder builder) {
        this._persistenceEnabled = builder._persistenceEnabled;
        this._marketTime = builder._marketTime;
        this._suspendTime = builder._suspendTime;
        this._bettingType = builder._bettingType;
        this._turnInPlayEnabled = builder._turnInPlayEnabled;
        this._marketType = builder._marketType;
        this._regulator = builder._regulator;
        this._marketBaseRate = builder._marketBaseRate;
        this._discountAllowed = builder._discountAllowed;
        this._wallet = builder._wallet;
        this._rules = builder._rules;
        this._rulesHasDate = builder._rulesHasDate;
        this._eachWayDivisor = builder._eachWayDivisor;
    }

    public String getmarketTime() {
        return _marketTime;
    }

    public void setmarketTime(String _marketTime) {
        this._marketTime = _marketTime;
    }

    public String getsuspendTime() {
        return _suspendTime;
    }

    public void setsuspendTime(String _suspendTime) {
        this._suspendTime = _suspendTime;
    }

    public String getbettingType() {
        return _bettingType;
    }

    public void setbettingType(String _bettingType) {
        this._bettingType = _bettingType;
    }

    public boolean is_turnInPlayEnabled() {
        return _turnInPlayEnabled;
    }

    public void setturnInPlayEnabled(boolean _turnInPlayEnabled) {
        this._turnInPlayEnabled = _turnInPlayEnabled;
    }

    public String getmarketType() {
        return _marketType;
    }

    public void setmarketType(String _marketType) {
        this._marketType = _marketType;
    }

    public String getregulator() {
        return _regulator;
    }

    public void setregulator(String _regulator) {
        this._regulator = _regulator;
    }

    public int getmarketBaseRate() {
        return _marketBaseRate;
    }

    public void setmarketBaseRate(int _marketBaseRate) {
        this._marketBaseRate = _marketBaseRate;
    }

    public boolean is_discountAllowed() {
        return _discountAllowed;
    }

    public void setdiscountAllowed(boolean _discountAllowed) {
        this._discountAllowed = _discountAllowed;
    }

    public String getwallet() {
        return _wallet;
    }

    public void setwallet(String _wallet) {
        this._wallet = _wallet;
    }

    public String getrules() {
        return _rules;
    }

    public void setrules(String _rules) {
        this._rules = _rules;
    }

    public boolean is_rulesHasDate() {
        return _rulesHasDate;
    }

    public void setrulesHasDate(boolean _rulesHasDate) {
        this._rulesHasDate = _rulesHasDate;
    }

    public int geteachWayDivisor() {
        return _eachWayDivisor;
    }

    public void seteachWayDivisor(int _eachWayDivisor) {
        this._eachWayDivisor = _eachWayDivisor;
    }

    public boolean is_persistenceEnabled() {
        return _persistenceEnabled;
    }

    public void setpersistenceEnabled(boolean _persistenceEnabled) {
        this._persistenceEnabled = _persistenceEnabled;
    }

    public static class Builder {
        private boolean _persistenceEnabled;
        private String _marketTime;
        private String _suspendTime;
        private String _bettingType;
        private boolean _turnInPlayEnabled;
        private String _marketType;
        private String _regulator;
        private int _marketBaseRate;
        private boolean _discountAllowed;
        private String _wallet;
        private String _rules;
        private boolean _rulesHasDate;
        private int _eachWayDivisor;

        public Builder() {
        }

        public Builder persistenceEnabled(boolean val) {
            _persistenceEnabled = val;
            return this;
        }

        public Builder marketTime(String val) {
            _marketTime = val;
            return this;
        }

        public Builder suspendTime(String val) {
            _suspendTime = val;
            return this;
        }

        public Builder bettingType(String val) {
            _bettingType = val;
            return this;
        }

        public Builder turnInPlayEnabled(boolean val) {
            _turnInPlayEnabled = val;
            return this;
        }

        public Builder marketType(String val) {
            _marketType = val;
            return this;
        }

        public Builder regulator(String val) {
            _regulator = val;
            return this;
        }

        public Builder marketBaseRate(int val) {
            _marketBaseRate = val;
            return this;
        }

        public Builder discountAllowed(boolean val) {
            _discountAllowed = val;
            return this;
        }

        public Builder wallet(String val) {
            _wallet = val;
            return this;
        }

        public Builder rules(String val) {
            _rules = val;
            return this;
        }

        public Builder rulesHasDate(boolean val) {
            _rulesHasDate = val;
            return this;
        }

        public Builder eachWayDivisor(int val) {
            _eachWayDivisor = val;
            return this;
        }

        public MarketDescription build() {
            return new MarketDescription(this);
        }
    }
}
