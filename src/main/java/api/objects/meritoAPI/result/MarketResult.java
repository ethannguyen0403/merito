package api.objects.meritoAPI.result;

import api.objects.meritoAPI.Market;

import java.util.List;

public class MarketResult extends ResultObject {
    private List<Market> _marketList;

    public MarketResult(Builder builder) {
        this._isSuccess = builder._isSuccess;
        this._marketList = builder._marketList;
    }

    public List<Market> getMarketList() {
        return _marketList;
    }

    public void setMarketList(List<Market> val) {
        _marketList = val;
    }

    public static class Builder {
        private boolean _isSuccess;
        private List<Market> _marketList;

        public Builder() {
        }

        public Builder isSuccess(boolean val) {
            _isSuccess = val;
            return this;
        }

        public Builder marketList(List<Market> val) {
            _marketList = val;
            return this;
        }

        public MarketResult build() {
            return new MarketResult(this);
        }
    }
}
