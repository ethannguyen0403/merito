package membersite.objects;

/**
 * @author isabella.huynh
 * @created 10/13/2020
 */
public class AccountBalance {
    private String _balance;
    private String _exposure;
    private String _creditRefer;


    private AccountBalance(Builder builder) {
        this._balance = builder._balance;
        this._exposure = builder._exposure;
        this._creditRefer = builder._creditRefer;
    }

    public String getBalance() {
        return _balance;
    }

    public void setBalance(String balance) {
        this._balance = balance;
    }

    public String getExposure() {
        return _exposure;
    }

    public void setExposure(String exposure) {
        this._exposure = exposure;
    }

    public String getCreditRefer() {
        return _creditRefer;
    }

    public void setCreditRefer(String creditRefer) {
        this._creditRefer = creditRefer;
    }

    public static class Builder {
        // Optional parameters
        private String _exposure = "";
        private String _balance = "";
        private String _creditRefer = "";
        /*outstanding(exposure)
        retainTax
        cashBalance
        walletCurrency
        currencySymbol
        giveCredit
        walletCode
        betCredit
        status
        */

        public Builder() {
        }

        public Builder exposure(String val) {
            _exposure = val;
            return this;
        }

        public Builder balance(String val) {
            _balance = val;
            return this;
        }

        public Builder creditRefer(String val) {
            _creditRefer = val;
            return this;
        }

        public AccountBalance build() {
            return new AccountBalance(this);
        }

    }
}
