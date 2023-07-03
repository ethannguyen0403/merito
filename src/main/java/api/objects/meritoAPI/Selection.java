package api.objects.meritoAPI;


public class Selection{
    private int _id;
    private String _name;   
    private double _handicap;
    private int _sortPriority;  
    private String _status;
    private Double _availableBack;
    private Double _availableLay;

    public Double get_availableBack() {
        return _availableBack;
    }

    public void set_availableBack(Double _availableBack) {
        this._availableBack = _availableBack;
    }

    public Double get_availableLay() {
        return _availableLay;
    }

    public void set_availableLay(Double _availableLay) {
        this._availableLay = _availableLay;
    }


    public int getid() {
        return _id;
    }

    public void setid(int _id) {
        this._id = _id;
    }

    public String getname() {
        return _name;
    }

    public void setname(String _name) {
        this._name = _name;
    }

    public double gethandicap() {
        return _handicap;
    }

    public void sethandicap(double _handicap) {
        this._handicap = _handicap;
    }

    public int getsortPriority() {
        return _sortPriority;
    }

    public void setsortPriority(int _sortPriority) {
        this._sortPriority = _sortPriority;
    }

    public String getstatus() {
        return _status;
    }

    public void setstatus(String _status) {
        this._status = _status;
    }
    public static class Builder {
        private int _id;
        private String _name;
        private double _handicap;
        private int _sortPriority;
        private String _status;
        private Double _availableBack;
        private Double _availableLay;

        public Builder(){}

        public Builder id(int val){
            _id =val;
            return this;
        }
        public Builder name(String val){
            _name =val;
            return this;
        }
        public Builder handicap(double val){
            _handicap =val;
            return this;
        }
        public Builder sortPriority(int val){
            _sortPriority =val;
            return this;
        }

        public Builder status(String val){
            _status =val;
            return this;
        }
        public Builder availableLay(Double val){
            _availableLay =val;
            return this;
        }
        public Builder availableBack(Double val){
            _availableBack =val;
            return this;
        }

        public Selection build() { return new Selection(this); }
    }

    public Selection(Builder builder){
        this._id = builder._id;
        this._name = builder._name;
        this._handicap = builder._handicap;
        this._sortPriority = builder._sortPriority;
        this._status = builder._status;
        this._availableBack = builder._availableBack;
        this._availableLay = builder._availableLay;

    }
    
}
