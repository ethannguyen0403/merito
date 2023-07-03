package api.objects.B2B.resultObj;

import java.util.List;

public class ListPlayerInfoObj extends ResultB2BObj {

    private List<PlayerInfoObj> _accountList;
    public List<PlayerInfoObj> getAccountList() {
        return _accountList;
    }

    public void setAccountList(List<PlayerInfoObj> _accountList) {
        this._accountList = _accountList;
    }
    public static class Builder {
        private int _result;
        private List<PlayerInfoObj> _accountList;
        public Builder(){}
        public Builder result(int val){
            _result =val;
            return this;
        }
        public Builder accountList(List<PlayerInfoObj> val){
            _accountList =val;
            return this;
        }
        public ListPlayerInfoObj build() { return new ListPlayerInfoObj(this); }
    }
    public ListPlayerInfoObj(Builder builder){
        this._result = builder._result;
        this._accountList = builder._accountList;
    }
}
