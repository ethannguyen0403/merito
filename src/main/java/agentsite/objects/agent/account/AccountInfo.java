package agentsite.objects.agent.account;

/**
 * @author Liam.Ho
 * @created Nov/26/2019
 */
public class AccountInfo {
    private String _userID;
    private String _userCode;
    private String _loginID;
    private String _parentID;
    private double _cashBalance;
    private String _totalRecord;
    private String _currencyCode;
    private String _status;
    private String _level;

    public String getUserID() {
        return _userID;
    }

    public String getUserCode() {
        return _userCode;
    }

    public String getLoginID(){ return _loginID;}

    public String getParentID() {
        return _parentID;
    }

    public double getCashBalance() {
        return _cashBalance;
    }

    public String getTotalRecord() {
        return _totalRecord;
    }

    public String getCurrencyCode() {
        return _currencyCode;
    }

    public String getStatus() {
        return _status;
    }

    public String getLevel() {
        return _level;
    }

    public static class Builder {
        // Optional parameters
        private String _userID = "";
        private String _userCode = "";
        private String _loginID= "";
        private String _parentID = "";
        private double _cashBalance = 0;
        private String _totalRecord = "";
        private String _currencyCode = "";
        private String _status = "";
        private String _level = "";

        public Builder(){}

        public Builder userID(String val){
            _userID = val;
            return this;
        }

        public Builder userCode(String val){
            _userCode = val;
            return this;
        }

        public Builder loginID (String val){
            _loginID = val;
            return this;
        }

        public Builder parentID(String val){
            _parentID = val;
            return this;
        }

        public Builder cashBalance(double val){
            _cashBalance = val;
            return this;
        }

        public Builder totalRecord(String val){
            _totalRecord = val;
            return this;
        }

        public Builder currencyCode(String val){
            _currencyCode = val;
            return this;
        }

        public Builder status(String val){
            _status = val;
            return this;
        }

        public Builder level(String val){
            _level = val;
            return this;
        }

        public AccountInfo build() { return new AccountInfo (this); }

    }

    private AccountInfo(Builder builder){
        this._userID = builder._userID;
        this._userCode = builder._userCode;
        this._loginID= builder._loginID;
        this._parentID = builder._parentID;
        this._cashBalance = builder._cashBalance;
        this._totalRecord = builder._totalRecord;
        this._currencyCode = builder._currencyCode;
        this._status = builder._status;
        this._level = builder._level;
    }

    public String getUserCodeAndLoginID(){
        if(getUserCode().equalsIgnoreCase(getLoginID())){
            return getUserCode();
        }
        if(getLoginID().equals(""))
            return getUserCode();
        return String.format("%s (%s)",getUserCode(),getLoginID());
    }

    public String getUserCodeAndLoginID(String template){
        if(getUserCode().equalsIgnoreCase(getLoginID())){
            return getUserCode();
        }
        if(getLoginID().equals(""))
            return getUserCode();
        return String.format(template,getUserCode(),getLoginID());
    }

    public String getMemberSiteLoginID() {
        String [] userCodeAndLoginID = getUserCodeAndLoginID("%s %s").split("\\s+");
        if (userCodeAndLoginID.length > 1) {
            return userCodeAndLoginID[1];
        } else {
            return userCodeAndLoginID[0];
        }
    }

}
