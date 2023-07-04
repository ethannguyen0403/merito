package api.objects.meritoAPI.result;

import api.objects.meritoAPI.Order;

import java.util.List;

public class OrderResult extends ResultObject {
    private List<Order> _orderList;

    public OrderResult(Builder builder) {
        this._isSuccess = builder._isSuccess;
        this._orderList = builder._orderList;
    }

    public List<Order> getOrderList() {
        return _orderList;
    }

    public void setOrderList(List<Order> val) {
        _orderList = val;
    }

    public static class Builder {
        private boolean _isSuccess;
        private List<Order> _orderList;


        public Builder() {
        }

        public Builder isSuccess(boolean val) {
            _isSuccess = val;
            return this;
        }

        public Builder orderList(List<Order> val) {
            _orderList = val;
            return this;
        }

        public OrderResult build() {
            return new OrderResult(this);
        }
    }
}
