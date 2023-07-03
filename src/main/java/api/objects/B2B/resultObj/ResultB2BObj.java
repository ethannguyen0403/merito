package api.objects.B2B.resultObj;

import java.util.List;

public class ResultB2BObj {
    public int _result;
    public String _message;
    public int _code;
    public int _itemCount = 0;
    private List<Object> _objLst;

    public ResultB2BObj() {
    }

    public ResultB2BObj(Builder builder) {
        this._result = builder._result;
        this._code = builder._code;
        this._message = builder._message;
    }

    public int getResult() {
        return _result;
    }

    public void setResult(int result) {
        this._result = result;
    }

    public int getCode() {
        return _code;
    }

    public void setCode(int code) {
        this._code = code;
    }

    public String getMessage() {
        return _message;
    }

    public void setMessage(String message) {
        this._message = message;
    }

    public static class Builder {
        // Optional parameters;
        private int _result;
        private int _code;
        private String _message;

        public Builder() {
        }

        public Builder result(int val) {
            _result = val;
            return this;
        }

        public Builder code(int val) {
            _code = val;
            return this;
        }

        public Builder message(String val) {
            _message = val;
            return this;
        }

        public ResultB2BObj build() {
            return new ResultB2BObj(this);
        }
    }
}
