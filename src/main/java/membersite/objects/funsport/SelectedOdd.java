package membersite.objects.funsport;

import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

/**
 * @author Isabella.Huynh
 * @created Nov/26/2019
 */
public class SelectedOdd {
    private String _eventName;
    private String _matchScore;
    private String _inPlay;
    private boolean _isInPlay;
    private boolean _isBack;
    private String _selectedTeam;
    private String _marketName;
    private double _oddRate;
    private Icon _iconRemove;
    private TextBox _txtOdd;
    private String _stake;
    private TextBox _txtStake;
    private double _liability;
    private Label _lblLiability;
    private Label _lblProfit;
    private double _profit;
    private String _currency;
    private boolean _isUnmatched;
    private String _errorMessage;

    private SelectedOdd(Builder builder) {
        this._eventName = builder._eventName;
        this._matchScore = builder._matchScore;
        this._inPlay = builder._inPlay;
        this._isInPlay = builder._isInPlay;
        this._isBack = builder._isBack;
        this._isUnmatched = builder._isUnmatched;
        this._selectedTeam = builder._selectedTeam;
        this._marketName = builder._marketName;
        this._oddRate = builder._oddRate;
        this._iconRemove = builder._iconRemove;
        this._txtOdd = builder._txtOdd;
        this._stake = builder._stake;
        this._txtStake = builder._txtStake;
        this._lblProfit = builder._lblProfit;
        this._lblLiability = builder._lblLiability;
        this._liability = builder._liability;
        this._profit = builder._profit;
        this._currency = builder._currency;
        this._errorMessage = builder._errorMessage;
    }

    public String getEventName() {
        return _eventName;
    }

    public String getErrorMessage() {
        return _errorMessage;
    }

    public void setErrorMessage(String val) {
        _errorMessage = val;
    }

    public String getMatchScore() {
        return _matchScore;
    }

    public String getInPlay() {
        return _inPlay;
    }

    public boolean getIsInPlay() {
        return _isInPlay;
    }

    public boolean getIsBack() {
        return _isBack;
    }

    public boolean getIsUnmatched() {
        return _isUnmatched;
    }

    public String getSelectedTeam() {
        return _selectedTeam;
    }

    public String getMarketName() {
        return _marketName;
    }

    public double getOddRate() {
        return _oddRate;
    }

    public void setOddRate(double val) {
        _oddRate = val;
    }

    public Icon getIconRemove() {
        return _iconRemove;
    }

    public TextBox getTxtOdd() {
        return _txtOdd;
    }

    public String getStake() {
        return _stake;
    }

    public void setStake(String val) {
        _stake = val;
    }

    public TextBox getTxtStake() {
        return _txtStake;
    }

    public double getProfit() {
        return _profit;
    }

    public void setProfit(double val) {
        _profit = val;
    }

    public Label getLblLiability() {
        return _lblLiability;
    }

    public void setLblLiability(Label val) {
        _lblLiability = val;
    }

    public Label getLblProfit() {
        return _lblProfit;
    }

    public void setLblProfit(Label val) {
        _lblProfit = val;
    }

    public void setTxtOdd() {
        TextBox _txtOdd;
    }

    public double getLiability() {
        return _liability;
    }

    public void setLiability(double val) {
        _liability = val;
    }

    public String getCurrency() {
        return _currency;
    }

    public static class Builder {
        // Optional parameters
        private String _eventName = "";
        private String _matchScore = "";
        private String _inPlay = "";
        private boolean _isInPlay = false;
        private boolean _isBack = true;
        private boolean _isUnmatched = false;
        private String _selectedTeam = "";
        private String _marketName = "";
        private double _oddRate = -1;
        private TextBox _txtOdd;
        private Icon _iconRemove;
        private String _stake = "";
        private TextBox _txtStake;
        private double _liability = -1;
        private Label _lblLiability;
        private Label _lblProfit;
        private double _profit = -1;
        private String _currency = "";
        private String _errorMessage;

        public Builder() {
        }

        public Builder eventName(String val) {
            _eventName = val;
            return this;
        }

        public Builder matchScore(String val) {
            _matchScore = val;
            return this;
        }

        public Builder inPlay(String val) {
            _inPlay = val;
            return this;
        }

        public Builder isInPlay(boolean val) {
            _isInPlay = val;
            return this;
        }

        public Builder isUnmatched(boolean val) {
            _isUnmatched = val;
            return this;
        }

        public Builder isBack(boolean val) {
            _isBack = val;
            return this;
        }

        public Builder selectedTeam(String val) {
            _selectedTeam = val;
            return this;
        }

        public Builder marketName(String val) {
            _marketName = val;
            return this;
        }

        public Builder oddRate(double val) {
            _oddRate = val;
            return this;
        }

        public Builder txtOdd(TextBox val) {
            _txtOdd = val;
            return this;
        }

        public Builder lblLiability(Label val) {
            _lblLiability = val;
            return this;
        }

        public Builder lblProfit(Label val) {
            _lblProfit = val;
            return this;
        }

        public Builder iconRemove(Icon val) {
            _iconRemove = val;
            return this;
        }

        public Builder stake(String val) {
            _stake = val;
            return this;
        }

        public Builder errorMessage(String val) {
            _errorMessage = val;
            return this;
        }

        public Builder txtStake(TextBox val) {
            _txtStake = val;
            return this;
        }

        public Builder liability(double val) {
            _liability = val;
            return this;
        }

        public Builder profit(double val) {
            _profit = val;
            return this;
        }

        public Builder currency(String val) {
            _currency = val;
            return this;
        }

        public SelectedOdd build() {
            return new SelectedOdd(this);
        }

    }
}
