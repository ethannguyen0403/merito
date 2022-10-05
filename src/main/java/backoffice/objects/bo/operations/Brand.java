package backoffice.objects.bo.operations;

/**
 * @author Liam.Ho
 * @created Jan/1/2020
 */
public class Brand {
    private String _brandName;
    private String _displayName;
    private int _brandId;

    public String getBrandName() {
        return _brandName;
    }

    public String getDisplayName() {
        return _displayName;
    }

    public int getBrandId() {
        return _brandId;
    }

    public static class Builder {
        // Optional parameters
        private String _brandName = "";
        private String _displayName = "";
        private int _brandId = 0;

        public Builder(){}

        public Builder brandName(String val){
            _brandName = val;
            return this;
        }

        public Builder displayName(String val){
            _displayName = val;
            return this;
        }

        public Builder brandId(int val){
            _brandId = val;
            return this;
        }

        public Brand build() { return new Brand(this); }
    }

    private Brand(Builder builder){
        this._brandName = builder._brandName;
        this._displayName = builder._displayName;
        this._brandId = builder._brandId;
    }
}
