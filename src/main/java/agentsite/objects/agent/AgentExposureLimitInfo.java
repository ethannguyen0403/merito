package agentsite.objects.agent;

/**
 * @author Liam.Ho
 * @created Nov/26/2019
 */
public class AgentExposureLimitInfo {
    private String _userID;
    private String _levelName;
    private String _loginID;
    private String _parentID;
    private double _riskAvailable;
    private double _riskBalance;
    private double _riskLimit;
    private String _username;

    public String getUserID() {
        return _userID;
    }

    public String getLevelName() {
        return _levelName;
    }

    public String getLoginID(){ return _loginID;}

    public String getParentID() {
        return _parentID;
    }

    public double getRiskAvailableBalance() {
        return _riskAvailable;
    }

    public double getRiskBalance() {
        return _riskBalance;
    }

    public double getRiskLimit() {
        return _riskLimit;
    }

    public String getUsername() {
        return _username;
    }

    public static class Builder {
        // Optional parameters
        private String _userID = "";
        private String _levelName = "";
        private String _loginID= "";
        private String _parentID = "";
        private double _riskAvailable = 0;
        private double _riskBalance;
        private double _riskLimit;
        private String _username = "";

        public Builder(){}

        public Builder userID(String val){
            _userID = val;
            return this;
        }

        public Builder levelName(String val){
            _levelName = val;
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

        public Builder riskAvailable(double val){
            _riskAvailable = val;
            return this;
        }

        public Builder riskBalance(double val){
            _riskBalance = val;
            return this;
        }

        public Builder riskLimit(double val){
            _riskLimit = val;
            return this;
        }


        public AgentExposureLimitInfo build() { return new AgentExposureLimitInfo(this); }

    }

    private AgentExposureLimitInfo(Builder builder){
        this._userID = builder._userID;
        this._levelName = builder._levelName;
        this._loginID= builder._loginID;
        this._parentID = builder._parentID;
        this._riskAvailable = builder._riskAvailable;
        this._riskBalance = builder._riskBalance;
        this._riskLimit = builder._riskLimit;
        this._username = builder._username;
    }




}
