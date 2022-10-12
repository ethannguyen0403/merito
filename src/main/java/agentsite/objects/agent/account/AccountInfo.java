package agentsite.objects.agent.account;

/**
 * @author isabella.huynh
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
    private  int _creditGiven;
    private int _maxCredit;
    private int _memberMaxCredit;
    private double _availableBalance;
    private double _transferableBalance;
    private double _retainAmount;
    private double _totalBalance;
    private double _downlineBalance;
    private double _totalPlayerOutstanding;
    private double _creditUsed;
    private double _yesterdayDownlineBalance;
    private double _myOutstanding;
    private double _totalOustanding;
    private double _todayWinLoss;
    private double _yesterdayWinLoss;

    public double getMyOutstanding() {
        return _myOutstanding;
    }

    public void setMyOutstanding(double _myOutstanding) {
        this._myOutstanding = _myOutstanding;
    }

    public double getTotalOustanding() {
        return _totalOustanding;
    }

    public void setTotalOustanding(double _totalOustanding) {
        this._totalOustanding = _totalOustanding;
    }

    public double getTodayWinLoss() {
        return _todayWinLoss;
    }

    public void setTodayWinLoss(double _todayWinLoss) {
        this._todayWinLoss = _todayWinLoss;
    }

    public double getYesterdayWinLoss() {
        return _yesterdayWinLoss;
    }

    public void setYesterdayWinLoss(double _yesterdayWinLoss) {
        this._yesterdayWinLoss = _yesterdayWinLoss;
    }

    public double getYesterdayDownlineBalance() {
        return _yesterdayDownlineBalance;
    }

    public void setYesterdayDownlineBalance(double val) {
        this._yesterdayDownlineBalance =val;
    }



    public double getTransferableBalance() {
        return _transferableBalance;
    }

    public void setTransferableBalance(double val) {
        this._transferableBalance = val;
    }

    public double getRetainAmount() {
        return _retainAmount;
    }

    public void setRetainAmount(double val) {
        this._retainAmount = val;
    }

    public double getTotalBalance() {
        return _totalBalance;
    }

    public void setTotalBalance(double val) {
        this._totalBalance = val;
    }

    public double getDownlineBalance() {
        return _downlineBalance;
    }

    public void setDownlineBalance(double val) {
        this._downlineBalance = val;
    }

    public double getTotalPlayerOutstanding() {
        return _totalPlayerOutstanding;
    }

    public void setTotalPlayerOutstanding(double val) {
        this._totalPlayerOutstanding = val;
    }

    public double getCreditUsed() {
        return _creditUsed;
    }

    public void setCreditUsed(double val) {
        this._creditUsed = val;
    }

    public int getCreditGiven() {
        return _creditGiven;
    }

    public void setCreditGiven(int val) {
        this._creditGiven = val;
    }

    public int getMaxCredit() {
        return _maxCredit;
    }

    public void setMaxCredit(int val) {
        this._maxCredit = val;
    }

    public int getMemberMaxCredit() {
        return _memberMaxCredit;
    }

    public void setMemberMaxCredit(int val) {
        this._memberMaxCredit = val;
    }

    public double getAvailableBalance() {
        return _availableBalance;
    }

    public void setAvailableBalance(double val) {
        this._availableBalance = val;
    }


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
        private  int _creditGiven;
        private int _maxCredit;
        private int _memberMaxCredit;
        private double _availableBalance;
        private double _transferableBalance;
        private double _retainAmount;
        private double _totalBalance;
        private double _downlineBalance;
        private double _totalPlayerOutstanding;
        private double _creditUsed;
        private double _yesterdayDownlineBalance;
        private double _myOutstanding;
        private double _totalOustanding;
        private double _todayWinLoss;
        private double _yesterdayWinLoss;


        public Builder(){}
        public Builder myOutstanding(double val){
            _myOutstanding = val;
            return this;
        }
        public Builder totalOustanding(double val){
            _totalOustanding = val;
            return this;
        }
        public Builder todayWinLoss(double val){
            _todayWinLoss = val;
            return this;
        }
        public Builder yesterdayWinLoss(double val){
            _yesterdayWinLoss = val;
            return this;
        }
        public Builder yesterdayDownlineBalance(double val){
            _yesterdayDownlineBalance = val;
            return this;
        }
        public Builder transferableBalance(double val){
            _transferableBalance = val;
            return this;
        }
        public Builder retainAmount(double val){
            _retainAmount = val;
            return this;
        }
        public Builder totalBalance(double val){
            _totalBalance = val;
            return this;
        }
        public Builder downlineBalance(double val){
            _downlineBalance = val;
            return this;
        }
        public Builder totalPlayerOutstanding(double val){
            _totalPlayerOutstanding = val;
            return this;
        }
        public Builder creditUsed(double val){
            _creditUsed = val;
            return this;
        }

        public Builder creditGiven(int val){
            _creditGiven = val;
            return this;
        }
        public Builder maxCredit(int val){
            _maxCredit = val;
            return this;
        }
        public Builder memberMaxCredit(int val){
            _memberMaxCredit = val;
            return this;
        }
        public Builder availableBalance(int val){
            _availableBalance = val;
            return this;
        }

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
        this._creditGiven = builder._creditGiven;
        this._maxCredit = builder._maxCredit;
        this._availableBalance = builder._availableBalance;
        this._memberMaxCredit = builder._memberMaxCredit;
        this._creditUsed= builder._creditUsed;
        this._transferableBalance = builder._transferableBalance;
        this._retainAmount = builder._retainAmount;
        this._totalBalance = builder._totalBalance;
        this._yesterdayDownlineBalance = builder._yesterdayDownlineBalance;
        this._downlineBalance = builder._downlineBalance;
        this._myOutstanding = builder._myOutstanding;
        this._totalOustanding = builder._totalOustanding;
        this._yesterdayWinLoss = builder._yesterdayWinLoss;
        this._todayWinLoss = builder._todayWinLoss;

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
