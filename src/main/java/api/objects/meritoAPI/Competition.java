package api.objects.meritoAPI;

public class Competition{
    private int _id;
    private String _name;   
    private int _marketCount;
    private String _marketRegion;

    public String getMarketRegion() {
        return _marketRegion;
    }

    public void setMarketRegion(String _marketRegion) {
        this._marketRegion = _marketRegion;
    }

    public int getMarketCount() {
        return _marketCount;
    }

    public void setMarketCount(int marketCount) {
        this._marketCount = marketCount;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }    

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }
    public static class Builder {
        private int _id;
        private String _name;
        private int _marketCount;
        private String _marketRegion;

        public Builder(){}
        public Builder id(int val){
            _id =val;
            return this;
        }
        public Builder name(String val){
            _name =val;
            return this;
        }
        public Builder marketRegion(String val){
            _marketRegion =val;
            return this;
        }
        public Builder marketCount(int val){
            _marketCount =val;
            return this;
        }
        public Competition build() { return new Competition(this); }
    }
    public Competition(Builder builder){
      this._id = builder._id;
      this._marketCount = builder._marketCount;
      this._name = builder._name;
      this._marketRegion = builder._marketRegion;
    }
}
