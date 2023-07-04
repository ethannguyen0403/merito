package api.objects.meritoAPI;

public class Wager {
    private String _sportName;
    private String _eventId;
    private String _eventName;
    private String _marketId;
    private String _marketName;
    private String _selectionId;
    private String _selectionName;
    private String _odd; // change odd to String due to has Fancy odds has special e.g. 55:78
    private double _stake;
    private String _betId;
    private String _placeDate;
    private String _placeType; //Back or Lay  

    public Wager() {
    }

    public Wager(Wager wager) {
        this._eventId = wager.getEventId();
        this._eventName = wager.getMarketName();
        this._marketId = wager.getMarketId();
        this._marketName = wager.getMarketName();
        this._selectionId = wager.getSelectionId();
        this._selectionName = wager.getSelectionName();
        this._odd = wager.getOdd();
        this._stake = wager.getStake();
        this._betId = wager.getBetId();
        this._placeDate = wager.getPlacedDate();
        this._placeType = wager.getPlaceType();
    }

    public Wager(String eventId, String eventName, String marketId, String marketName, String selectionId, String selectionName, String odd, double stake, String betId, String placeDate, String placeType) {
        this._eventId = eventId;
        this._eventName = eventName;
        this._marketId = marketId;
        this._marketName = marketName;
        this._selectionId = selectionId;
        this._selectionName = selectionName;
        this._odd = odd;
        this._stake = stake;
        this._betId = betId;
        this._placeDate = placeDate;
        this._placeType = placeType;
    }

    public String getPlaceType() {
        return _placeType;
    }

    public void setPlaceType(String placedType) {
        this._placeType = placedType;
    }

    public String getPlacedDate() {
        return _placeDate;
    }

    public void setPlacedDate(String placedDate) {
        this._placeDate = placedDate;
    }

    public String getSportName() {
        return _sportName;
    }

    public void setSportName(String sportName) {
        this._sportName = sportName;
    }

    public String getEventId() {
        return _eventId;
    }

    public void setEventId(String eventId) {
        this._eventId = eventId;
    }

    public String getEventName() {
        return _eventName;
    }

    public void setEventName(String eventName) {
        this._eventName = eventName;
    }

    public String getMarketId() {
        return _marketId;
    }

    public void setMarketId(String marketId) {
        this._marketId = marketId;
    }

    public String getMarketName() {
        return _marketName;
    }

    public void setMarketName(String marketName) {
        this._marketName = marketName;
    }

    public String getSelectionId() {
        return _selectionId;
    }

    public void setSelectionId(String selectionId) {
        this._selectionId = selectionId;
    }

    public String getSelectionName() {
        return _selectionName;
    }

    public void setSelectionName(String selectionName) {
        this._selectionName = selectionName;
    }

    public String getBetId() {
        return _betId;
    }

    public void setBetId(String betId) {
        this._betId = betId;
    }

    public double getStake() {
        return _stake;
    }

    public void setStake(double stake) {
        this._stake = stake;
    }

    public String getOdd() {
        return _odd;
    }

    public void setOdd(String odd) {
        this._odd = odd;
    }
    /*   *//********
     * calculate liability of wager base on stake and odd
     * @return liability value
     *//*
    public double getOrderLiability(){
        double liabiliy = 0;
        try{
            if(this.getPlaceType().equalsIgnoreCase(BACK))
                liabiliy = this.getStake();
            else
                liabiliy = this.getStake()*(Double.parseDouble(this.getOdd())-1);
        }catch(Exception e){
           System.out.println(e.getMessage());
          
        }           
        return liabiliy;
    }
    */
    /****
     * Calculate win-loss result of the bet base on settled result, stake, and odds
     * @param settledResult: Win, LOST
     * @return win loss result
     */
   /* public double getOrderWinLossResult(String settledResult){
        double result = 0;
        try{
            // win + back : -> result = stake*(odd-1)
            // win + lay : -> result = stake
            // lose + back : -> result = stake
            // lose + lay : -> result = stake*(odd-1)
            if(settledResult.equalsIgnoreCase(LOST)){
                 if(this.getPlaceType().equalsIgnoreCase(BACK))
                    result = this.getStake();
                else
                    result = this.getStake()*(Double.parseDouble(this.getOdd())-1);
            }
            else
            {
                if(this.getPlaceType().equalsIgnoreCase(BACK))
                    result = this.getStake()*(Double.parseDouble(this.getOdd())-1);
                else
                    result = this.getStake();
            }
            
        }catch(Exception e){
            ActionCommon.ReportLog(e.getMessage());            
        }           
        return result;
    }
*/

}
