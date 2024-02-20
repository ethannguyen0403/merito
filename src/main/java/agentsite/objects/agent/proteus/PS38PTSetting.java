package agentsite.objects.agent.proteus;

public class PS38PTSetting {
    private String _sport;
    private String _betTime;
    private String _betType;
    private String _pos;
    private String _ps38Tab;
    private double _amountPT;

    private PS38PTSetting(Builder builder) {
        _sport = builder._sport;
        _betTime = builder._betTime;
        _betType = builder._betType;
        _pos = builder._pos;
        _amountPT = builder._amountPT;
        _ps38Tab = builder._ps38Tab;
    }

    public void setSport(String sport){
        _sport =sport;
    }

    public void setBetType(String betType){
        _betType =betType;
    }

    public String getSport() {
        return _sport;
    }

    public String getBetTime() {
        return _betTime;
    }

    public String getBetType() {
        return _betType;
    }

    public String getPos() {
        return _pos;
    }
    public String getPS38Tab() {
        return _ps38Tab;
    }

    public double getAmountPT() {
        return _amountPT;
    }

    public static class Builder {
        private String _sport;
        private String _betTime;
        private String _betType;
        private String _pos;
        private String _ps38Tab;
        private double _amountPT;

        public Builder() {
        }

        public PS38PTSetting build() {
            return new PS38PTSetting(this);
        }

        public Builder sport(String sport) {
            _sport = sport;
            return this;
        }

        public Builder betTime(String betTime) {
            _betTime = betTime;
            return this;
        }

        public Builder betType(String betType) {
            _betType = betType;
            return this;
        }

        public Builder ps38Tab(String ps38TabName) {
            _ps38Tab = ps38TabName;
            return this;
        }

        public Builder pos(String position) {
            _pos = position;
            return this;
        }

        public Builder amountPT(double amount) {
            _amountPT = amount;
            return this;
        }
    }
}
