package agentsite.objects.agent;

public class PaymentChannel {

    private int _paymentId;
    private String _paymentCode;
    private String _paymentName;
    private String _paymentType;
    private String _paymentStatus;
    private String _paymentTransactionType;
    private String _paymentBankName;
    private String _paymentAccountNumber;
    private String _paymentAccountHolderName;
    private String _paymentIFSCCode;
    private String _paymentBranch;
    private String _paymentAccountType;
    private String _paymentWalletAddress;
    private int _paymentPriority;
    private String _paymentImgChannel;

    private PaymentChannel(Builder builder) {
        this._paymentId = builder._paymentId;
        this._paymentCode = builder._paymentCode;
        this._paymentName = builder._paymentName;
        this._paymentType = builder._paymentType;
        this._paymentStatus = builder._paymentStatus;
        this._paymentTransactionType = builder._paymentTransactionType;
        this._paymentBankName = builder._paymentBankName;
        this._paymentAccountNumber = builder._paymentAccountNumber;
        this._paymentAccountHolderName = builder._paymentAccountHolderName;
        this._paymentIFSCCode = builder._paymentIFSCCode;
        this._paymentBranch = builder._paymentBranch;
        this._paymentAccountType = builder._paymentAccountType;
        this._paymentWalletAddress = builder._paymentWalletAddress;
        this._paymentPriority = builder._paymentPriority;
        this._paymentImgChannel = builder._paymentImgChannel;
    }

    public int getPaymentId() {
        return _paymentId;
    }

    public void setPaymentId(int _paymentId) {
        this._paymentId = _paymentId;
    }

    public int getPaymentPriority() {
        return _paymentPriority;
    }

    public void setPaymentPriority(int _paymentPriority) {
        this._paymentPriority = _paymentPriority;
    }
    public String getPaymentCode() {
        return _paymentCode;
    }
    public String getPaymentName() {
        return _paymentName;
    }
    public String getPaymentType() {
        return _paymentType;
    }
    public String getPaymentStatus() {
        return _paymentStatus;
    }
    public String getPaymentTransactionType() {
        return _paymentTransactionType;
    }
    public String getPaymentBankName() {
        return _paymentBankName;
    }
    public String getPaymentAccountNumber() {
        return _paymentAccountNumber;
    }
    public String getPaymentAccountHolderName() {
        return _paymentAccountHolderName;
    }
    public String getPaymentIFSCCode() {
        return _paymentIFSCCode;
    }
    public String getPaymentBranch() {
        return _paymentBranch;
    }
    public String getPaymentAccountType() {
        return _paymentAccountType;
    }
    public String getPaymentWalletAddress() {
        return _paymentWalletAddress;
    }
    public String getPaymentImgChannel() {
        return _paymentImgChannel;
    }


    public static class Builder {
        // Optional parameters
        private int _paymentId = 0;
        private String _paymentCode = "";
        private String _paymentName = "";
        private String _paymentType = "";
        private String _paymentStatus = "";
        private String _paymentTransactionType = "";
        private String _paymentBankName = "";
        private String _paymentAccountNumber = "";
        private String _paymentAccountHolderName = "";
        private String _paymentIFSCCode = "";
        private String _paymentBranch = "";
        private String _paymentAccountType = "";
        private String _paymentWalletAddress = "";
        private int _paymentPriority = 0;
        private String _paymentImgChannel = "";


        public Builder() {
        }

        public Builder id(int val) {
            _paymentId = val;
            return this;
        }

        public Builder priority(int val) {
            _paymentPriority = val;
            return this;
        }

        public Builder code(String val) {
            _paymentCode = val;
            return this;
        }

        public Builder name(String val) {
            _paymentName = val;
            return this;
        }

        public Builder type(String val) {
            _paymentType = val;
            return this;
        }

        public Builder status(String val) {
            _paymentStatus = val;
            return this;
        }

        public Builder transactionType(String val) {
            _paymentTransactionType = val;
            return this;
        }

        public Builder bankName(String val) {
            _paymentBankName = val;
            return this;
        }

        public Builder accountNumber(String val) {
            _paymentAccountNumber = val;
            return this;
        }

        public Builder accountHolderName(String val) {
            _paymentAccountHolderName = val;
            return this;
        }

        public Builder IFSCCode(String val) {
            _paymentIFSCCode = val;
            return this;
        }

        public Builder branch(String val) {
            _paymentBranch = val;
            return this;
        }

        public Builder accountType(String val) {
            _paymentAccountType = val;
            return this;
        }

        public Builder walletAddress(String val) {
            _paymentWalletAddress = val;
            return this;
        }

        public Builder imgChannel(String val) {
            _paymentImgChannel = val;
            return this;
        }

        public PaymentChannel build() {
            return new PaymentChannel(this);
        }

    }
}
