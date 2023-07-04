package api.objects.B2B.resultObj;

import java.util.List;
import java.util.Objects;

public class WagerResultObj extends ResultB2BObj {
    private List<WagerObj> _orderList;

    public WagerResultObj(Builder builder) {
        this._result = builder._result;
        this._orderList = builder._orderList;
    }

    public List<WagerObj> getOrderList() {
        return _orderList;
    }

    public void setOrderList(List<WagerObj> val) {
        _orderList = val;
    }

    public boolean isFilterResultContainStatus(String statusExpected) {
        if (Objects.isNull(_orderList)) {
            System.out.println("PASSED as no data display after filtering");
            return true;
        }
        for (WagerObj result : _orderList
        ) {
            if (!statusExpected.contains(result.getStatus())) {
                System.out.println(String.format("Order %s display incorrect status when filtering. Current status is %s", result.getOrderId(), result.getStatus()));
                return false;
            }

        }
        System.out.println("The list result display correct status");
        return true;
    }

    public boolean isFilterResultEqualStatus(String statusExpected) {
        if (Objects.isNull(_orderList)) {
            System.out.println("PASSED as no data display after filtering");
            return true;
        }
        for (WagerObj result : _orderList
        ) {
            if (!statusExpected.equals(result.getStatus())) {
                System.out.println(String.format("Order %s display incorrect status when filtering. Current status is %s", result.getOrderId(), result.getStatus()));
                return false;
            }

        }
        System.out.println(String.format("The list result display correct status"));
        return true;
    }

    public static class Builder {
        private int _result;
        private List<WagerObj> _orderList;

        public Builder() {
        }

        public Builder result(int val) {
            _result = val;
            return this;
        }

        public Builder orderList(List<WagerObj> val) {
            _orderList = val;
            return this;
        }

        public WagerResultObj build() {
            return new WagerResultObj(this);
        }
    }
}
