package api.objects.B2B.resultObj;

public class BalanceObj extends ResultB2BObj {
    private String _userId;
    private double _amount;
    private double _balanceBefore;
    private double _balanceAfter;
    private long _transactionId;
    private String _date;
    private String _userName;
    public String getUserId() {
        return _userId;
    }
    public void setUserId(String val) {
        _userId = val;}
    public double getAmount() {
        return _amount;
    }
    public void setAmount(double val) {
        _amount = val;}
    public String getDate() {
        return _date;
    }
    public void setDate(String val) {
        _date = val;}
    public double getBalanceBefore() {
        return _balanceBefore;
    }
    public void setBalanceBefore(double val) {
        _balanceBefore = val;}
    public double getBalanceAfter() {
        return _balanceAfter;
    }
    public void setBalanceAfter(double val) {
        _balanceAfter = val;}
    public String getUserName() {
        return _userName;
    }
    public void setUserName(String val) {
        _userName = val;}
    public long getTransactionId() {
        return _transactionId;
    }
    public void setTransactionId(long val) {
        _transactionId = val;}

    public static class Builder {
        private int _result;
        private String _userId;
        private String _date;
        private double _balanceBefore;
        private double _balanceAfter;
        private double _amount;
        private Long _transactionId;

        public Builder(){}

        public Builder result(int val){
            _result =val;
            return this;
        }
        public Builder userId(String val){
            _userId = val;
            return this;
        }

        public Builder transactionId(long val){
            _transactionId = val;
            return this;
        }
        public Builder date(String val){
            _date = val;
            return this;
        }
        public Builder balanceBefore(double val){
            _balanceBefore = val;
            return this;
        }
        public Builder balanceAfter(double val){
            _balanceAfter = val;
            return this;
        }
        public Builder amount(double val){
            _amount = val;
            return this;
        }
        public BalanceObj build() { return new BalanceObj(this); }
    }
    public BalanceObj(Builder builder){
        this._result = builder._result;
        this._userId = builder._userId;
        this._amount = builder._amount;
        this._balanceBefore = builder._balanceBefore;
        this._balanceAfter = builder._balanceAfter;
        this._date = builder._date;
        this._transactionId = builder._transactionId;
    }
}
