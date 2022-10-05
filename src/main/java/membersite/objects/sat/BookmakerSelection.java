package membersite.objects.sat;


/**
 * @author isabella.huynh
 * @created 10/3/2020
 */
public class BookmakerSelection {
    private int _backOdds;
    private String _selectionStatus;
    private int _selectionId;
    private String _selectionName;
    private int _layOdds;


    public String getSlectionStatus() {
        return _selectionStatus;
    }
    public String getSelectionName(){return _selectionName;}
    public int getSelectionId(){return  _selectionId;}
    public int getBackOdds(){return _backOdds;}
    public int getLayOdds(){return  _layOdds;}

    public void setSelectionStatus(String val){ _selectionStatus = val;}
    public void setSelectionName(String val){_selectionName = val;}
    public void setSelectinID(int val){_selectionId = val;}
    public void setBackOdds(int val){_backOdds = val;}
    public void setLayOdds(int val){_layOdds = val;}

    public static class Builder {
        // Optional parameters
        private int _backOdds;
        private String _selectionStatus;
        private int _selectionId;
        private String _selectionName;
        private int _layOdds;

        public Builder(){}
        public Builder selectionName(String val){_selectionName = val; return this;}
        public Builder selectionStatus(String val){_selectionStatus = val; return this;}
        public Builder backOdds(int val){_backOdds= val; return this;}
        public Builder layOdds(int val){_layOdds= val; return this;}
        public Builder selectionID(int val){_selectionId= val; return this;}
        public BookmakerSelection build() { return new BookmakerSelection(this); }
    }

    public BookmakerSelection(Builder builder) {
        this._selectionId = builder._selectionId;
        this._selectionName = builder._selectionName;
        this._backOdds =  builder._backOdds;
        this._layOdds = builder._layOdds;
        this._selectionStatus =builder._selectionStatus;
    }
}
