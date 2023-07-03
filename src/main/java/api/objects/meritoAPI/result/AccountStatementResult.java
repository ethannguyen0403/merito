package api.objects.meritoAPI.result;

import api.objects.meritoAPI.AccountStatement;

import java.util.List;

public class AccountStatementResult extends ResultObject {
    private List<AccountStatement> _accountStatementList;

    public List<AccountStatement> getAccountStatementList() {return _accountStatementList;}

    public void setAccountStatementList(List<AccountStatement> val) {
        _accountStatementList = val;}

    public static class Builder {
        private boolean _isSuccess;
        private List<AccountStatement> _accountStatementList;


        public Builder(){}
        public Builder isSuccess(boolean val){
            _isSuccess =val;
            return this;
        }
        public Builder orderList(List<AccountStatement> val){
            _accountStatementList =val;
            return this;
        }

        public AccountStatementResult build() { return new AccountStatementResult(this); }
    }

    public AccountStatementResult(Builder builder){
        this._isSuccess = builder._isSuccess;
        this._accountStatementList = builder._accountStatementList;
    }
}
