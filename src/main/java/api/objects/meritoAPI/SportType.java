package api.objects.meritoAPI;

public class SportType {
    private int _id;
    private String _name;   

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


        public Builder(){}

        public Builder id(int val){
            _id =val;
            return this;
        }
        public Builder name(String val){
            _name =val;
            return this;
        }

        public SportType build() { return new SportType(this); }
    }

    public SportType(Builder builder){
        this._id = builder._id;
        this._name = builder._name;
    }
}
