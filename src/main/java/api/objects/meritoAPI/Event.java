package api.objects.meritoAPI;


public class Event {
    private int _id;
    private String _name;
    private String _timeZone;
    private String _countryCode;
    private String _openDate;
    private int _marketCount;

    public Event(Builder builder) {
        this._id = builder._id;
        this._name = builder._name;
        this._countryCode = builder._countryCode;
        this._openDate = builder._openDate;
        this._timeZone = builder._timeZone;
        this._marketCount = builder._marketCount;
    }

    public String getTimeZone() {
        return _timeZone;
    }

    public void setTimeZone(String _timeZone) {
        this._timeZone = _timeZone;
    }

    public String getOpenDate() {
        return _openDate;
    }

    public void setOpenDate(String _openDate) {
        this._openDate = _openDate;
    }

    public String getCountryCode() {
        return _countryCode;
    }

    public void setCountryCode(String val) {
        this._countryCode = val;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public int getMarketCount() {
        return _marketCount;
    }

    public void setMarketCount(int val) {
        this._marketCount = val;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public static class Builder {
        private int _id;
        private String _name;
        private String _timeZone;
        private String _countryCode;
        private String _openDate;
        private int _marketCount;

        public Builder() {
        }

        public Builder id(int val) {
            _id = val;
            return this;
        }

        public Builder marketCount(int val) {
            _marketCount = val;
            return this;
        }

        public Builder name(String val) {
            _name = val;
            return this;
        }

        public Builder timezone(String val) {
            _timeZone = val;
            return this;
        }

        public Builder countryCode(String val) {
            _countryCode = val;
            return this;
        }

        public Builder openDate(String val) {
            _openDate = val;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
