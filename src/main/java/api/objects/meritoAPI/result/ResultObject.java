package api.objects.meritoAPI.result;

import java.util.List;

public class ResultObject {
    public Boolean _isSuccess;
    public String _message;
    public String _code;
    public int _itemCount = 0;
    private List<Object> _objLst;

    public Boolean getIsSuccess() {
        return _isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this._isSuccess = isSuccess;
    }

    public String getMessage() {
        return _message;
    }

    public void setMessage(String messageText) {
        this._message = messageText;
    }

    public String getCode() {
        return _code;
    }

    public void setCode(String code) {
        this._code = code;
    }

    public int getItemCount() {
        return _itemCount;
    }

    public void setItemCount(int itemCount) {
        this._itemCount = itemCount;
    }

    public List<Object> getObj(){return _objLst;}
    public void getObj(List<Object> val){_objLst = val;}
    public ResultObject() {
    }
    public static class Builder {
        // Optional parameters;
        private Boolean _isSuccess;
        private String _message;
        private String _code;
        private int _itemCount = 0;
        private List<Object> _objLst;

        public Builder(){}
        public Builder isSuccess(Boolean val){
            _isSuccess = val;
            return this;
        }
        public Builder message(String val){
            _message = val;
            return this;
        }
        public Builder code(String val){
            _code = val;
            return this;
        }
        public Builder itemCount(int val){
            _itemCount = val;
            return this;
        }
        public Builder obj(List<Object> val){
            _objLst = val;
            return this;
        }
        public ResultObject build() { return new ResultObject(this); }
    }

    public ResultObject(Builder builder){
        this._isSuccess = builder._isSuccess;
        this._message = builder._message;
        this._code = builder._code;
        this._itemCount = builder._itemCount;
        this._objLst = builder._objLst;
    }

}
