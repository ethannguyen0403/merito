package membersite.objects.sat;

import java.util.List;

/**
 * @author isabella.huynh
 * @created 10/3/2020
 */
public class BookmakerMarket1 {
    private String _marketName;
    private String _marketStatus;
    private int _delayBetting;
    private List<BookmakerSelection> _bmSelections;
    private int _rebateRatio;
    private String _minMaxSetting;
    private String _marketTime;
    private int _ratingExposure;
    private int _marketID;

    public String getMarketName() {
        return _marketName;
    }
    public String getMarketStatus  (){return _marketStatus;}
    public int getDelayBetting(){return _delayBetting;}
    public List<BookmakerSelection> getBMSelections(){return _bmSelections;}
    public int getRebateRatio(){return _rebateRatio;}
    public String getMinMaxSetting(){return _minMaxSetting;}
    public String getMarketTime(){return _marketTime;}
    public int getRatingExposure(){return  _ratingExposure;}
    public int getMarketID(){return  _marketID;}

    public void setMarketName(String val){_marketName = val;}
    public void setMarketStatus(String val){_marketStatus = val;}
    public void setDelayBetting(int val){_delayBetting = val;}
    public void setBMSelection(List<BookmakerSelection> val){_bmSelections = val;}
    public void setRebateRatio(int val){_rebateRatio = val;}
    public void setMinMaxSetting(String val){_minMaxSetting = val;}
    public void setMarketTime(String val){_marketTime = val;}
    public void setRatingExposure(int val){_ratingExposure = val;}
    public void setMarketId(int val){_marketID = val;}


    public static class Builder {
        // Optional parameters
        private String _marketName;
        private String _marketStatus;
        private int _delayBetting;
        private List<BookmakerSelection> _bmSelections;
        private int _rebateRatio;
        private String _minMaxSetting;
        private String _marketTime;
        private int _ratingExposure;
        private int _marketID;

        public Builder(){}
        public Builder marketName(String val){_marketName = val; return this;}
        public Builder marketStatus(String val){_marketStatus = val; return this;}
        public Builder delayBetting(int val){_delayBetting= val; return this;}
        public Builder bmSelection(List<BookmakerSelection> val){_bmSelections= val; return this;}
        public Builder rebateRatio(int val){_rebateRatio= val; return this;}
        public Builder minMaxSetting(String val){_minMaxSetting = val; return this;}
        public Builder marketTime(String val){_marketTime = val; return this;}
        public Builder ratingExposure(int val){_ratingExposure= val; return this;}
        public Builder marketID(int val){_marketID= val; return this;}
        public BookmakerMarket1 build() { return new BookmakerMarket1(this); }
    }

    public BookmakerMarket1(Builder builder) {
        this._marketName = builder._marketName;
        this._marketStatus = builder._marketStatus;
        this._delayBetting = builder._delayBetting;
        this._bmSelections = builder._bmSelections;
        this._rebateRatio = builder._rebateRatio;
        this._minMaxSetting = builder._minMaxSetting;
        this._marketTime = builder._marketTime;
        this._ratingExposure = builder._ratingExposure;
        this._marketID = builder._marketID;

    }

    public  BookmakerSelection getSelectionAvailable(String status)
    {
        List<BookmakerSelection> bmSelection = this.getBMSelections();
        for(int i = 0; i < bmSelection.size(); i++){
            if(bmSelection.get(i).getSlectionStatus().equalsIgnoreCase(status))
                return bmSelection.get(i);
        }
        System.out.println(String.format("The Market %s hav no Selection available as status %s", this.getMarketName(), status));
        return null;
    }
}
