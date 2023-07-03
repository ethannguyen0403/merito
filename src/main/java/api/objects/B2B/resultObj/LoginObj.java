package api.objects.B2B.resultObj;

public class LoginObj extends ResultB2BObj {
    private String _userId;
    private String _userName;
    private String _loginUrl;

    public LoginObj(Builder builder) {
        this._result = builder._result;
        this._userId = builder._userId;
        this._userName = builder._userName;
        this._loginUrl = builder._loginUrl;
    }

    public String getUserId() {
        return _userId;
    }

    public void setUserId(String val) {
        _userId = val;
    }

    public String getLoginUrl() {
        return _loginUrl;
    }

    public void setLoginUrl(String val) {
        _loginUrl = val;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String val) {
        _userName = val;
    }

    public static class Builder {
        private int _result;
        private String _userId;
        private String _userName;
        private String _loginUrl;

        public Builder() {
        }

        public Builder result(int val) {
            _result = val;
            return this;
        }

        public Builder userId(String val) {
            _userId = val;
            return this;
        }

        public Builder userName(String val) {
            _userName = val;
            return this;
        }

        public Builder loginUrl(String val) {
            _loginUrl = val;
            return this;
        }

        public LoginObj build() {
            return new LoginObj(this);
        }
    }
}
