package api.objects.B2B.resultObj;

public class PlayerInfoObj extends ResultB2BObj {
    private double _balance;
    private String _createdDate;
    private double _exposure;
    private String _status;
    private String _userId;
    private String _userName;
    public String getUserId() {
        return _userId;
    }
    public void setUserId(String val) {
        _userId = val;}
    public double getBalance() {
        return _balance;
    }
    public void setBalance(double val) {
        _balance = val;}
    public String getStatus() {
        return _status;
    }
    public void setStatus(String val) {
        _status = val;}
    public double getExposure() {
        return _exposure;
    }
    public void setExposure(double val) {
        _exposure = val;}
    public String getUserName() {
        return _userName;
    }
    public void setUserName(String val) {
        _userName = val;}
    public String getCreateDate() {
        return _createdDate;
    }
    public void setCreateDate(String val) {
        _createdDate = val;}

    public static class Builder {
        private int _result;
        private String _userId;
        private String _userName;
        private String _status;
        private double _exposure;
        private double _balance;
        private String _createDate;

        public Builder(){}

        public Builder result(int val){
            _result =val;
            return this;
        }
        public Builder userId(String val){
            _userId = val;
            return this;
        }
        public Builder userName(String val){
            _userName = val;
            return this;
        }
        public Builder createDate(String val){
            _createDate = val;
            return this;
        }
        public Builder status(String val){
            _status = val;
            return this;
        }
        public Builder exposure(double val){
            _exposure = val;
            return this;
        }
        public Builder balance(double val){
            _balance = val;
            return this;
        }
        public PlayerInfoObj build() { return new PlayerInfoObj(this); }
    }
    public PlayerInfoObj(Builder builder){
        this._result = builder._result;
        this._userId = builder._userId;
        this._balance = builder._balance;
        this._exposure = builder._exposure;
        this._userName = builder._userName;
        this._status = builder._status;
        this._createdDate = builder._createDate;
    }
}
