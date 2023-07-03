package api.objects.meritoAPI;

import api.objects.meritoAPI.result.ResultObject;

import java.util.List;

public class MemberInfo extends ResultObject {
    private String _currency;
    private Double _availableBalance;
    private List<BetSettingGroup> _lstBetSetting;
    public String getCurrency() {
        return _currency;
    }
    public void setCurrency(String val) {_currency = val;}
    public Double getAvailableBalance() {
        return _availableBalance;
    }
    public void setAvailableBalance(Double val) {_availableBalance = val;}
    public List<BetSettingGroup> getLstBetSetting() {
        return _lstBetSetting;
    }
    public void setLstBetSetting(List<BetSettingGroup> val) {
        _lstBetSetting = val;
    }

    public static class Builder {
        private boolean _isSuccess;
        private String _currency;
        private Double _availableBalance;
        private List<BetSettingGroup> _lstBetSetting;

        public Builder(){}

        public Builder isSuccess(boolean val){
            _isSuccess =val;
            return this;
        }
        public Builder currency(String val){
            _currency = val;
            return this;
        }
        public Builder availableBalance(Double val){
            _availableBalance = val;
            return this;
        }
        public Builder lstBetSetting(List<BetSettingGroup> val){
            _lstBetSetting = val;
            return this;
        }
        public MemberInfo build() { return new MemberInfo(this); }
    }

    public MemberInfo(Builder builder){
        this._isSuccess = builder._isSuccess;
        this._lstBetSetting = builder._lstBetSetting;
        this._currency = builder._currency;
        this._availableBalance = builder._availableBalance;
    }
}
