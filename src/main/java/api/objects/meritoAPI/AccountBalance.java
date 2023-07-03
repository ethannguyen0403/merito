package api.objects.meritoAPI;

import api.objects.meritoAPI.result.ResultObject;

public class AccountBalance extends ResultObject {

    private double _availableBalance;
    private double _exposure;
    private double _retainedTax;
    private String _currency;

    public AccountBalance(Builder builder) {
        this._isSuccess = builder._isSuccess;
        this._exposure = builder._exposure;
        this._retainedTax = builder._retainedTax;
        this._currency = builder._currency;
        this._availableBalance = builder._availableBalance;
    }

    public String getCurrency() {
        return _currency;
    }

    public void setCurrency(String val) {
        _currency = val;
    }

    public double getExposure() {
        return _exposure;
    }

    public void setExposure(double val) {
        _exposure = val;
    }

    public double getRetainedTax() {
        return _retainedTax;
    }

    public void setRetainedTax(double val) {
        _retainedTax = val;
    }

    public double getAvailableBalance() {
        return _availableBalance;
    }

    public void setAvailableBalance(double val) {
        _availableBalance = val;
    }

    public static class Builder {
        private boolean _isSuccess;
        private double _availableBalance;
        private double _exposure;
        private double _retainedTax;
        private String _currency;

        public Builder() {
        }

        public Builder isSuccess(boolean val) {
            _isSuccess = val;
            return this;
        }

        public Builder currency(String val) {
            _currency = val;
            return this;
        }

        public Builder availableBalance(double val) {
            _availableBalance = val;
            return this;
        }

        public Builder exposure(double val) {
            _exposure = val;
            return this;
        }

        public Builder retainedTax(double val) {
            _retainedTax = val;
            return this;
        }

        public AccountBalance build() {
            return new AccountBalance(this);
        }
    }
}
