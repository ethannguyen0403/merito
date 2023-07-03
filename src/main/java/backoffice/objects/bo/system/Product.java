package backoffice.objects.bo.system;

/**
 * @author Liam.Ho
 * @created Jan/1/2020
 */
public class Product {
    private String _productName;
    private String _productCode;
    private String _status;
    private int _productId;

    private Product(Builder builder) {
        this._productName = builder._productName;
        this._productCode = builder._productCode;
        this._status = builder._status;
        this._productId = builder._productId;
    }

    public String getProductName() {
        return _productName;
    }

    public String getProductCode() {
        return _productCode;
    }

    public String getStatus() {
        return _status;
    }

    public int getProductId() {
        return _productId;
    }

    public static class Builder {
        // Optional parameters
        private String _productName = "";
        private String _productCode = "";
        private String _status = "";
        private int _productId = 0;

        public Builder() {
        }

        public Builder productName(String val) {
            _productName = val;
            return this;
        }

        public Builder productCode(String val) {
            _productCode = val;
            return this;
        }

        public Builder status(String val) {
            _status = val;
            return this;
        }

        public Builder productId(int val) {
            _productId = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }

    }
}
