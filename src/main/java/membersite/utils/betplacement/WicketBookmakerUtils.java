package membersite.utils.betplacement;

public class WicketBookmakerUtils {

  /*  private static JSONArray getFancyJSONByProvider(String eventID, String provider){
        switch (provider){
            case "WICKET_BOOKMAKER":
                return  getWicketBookMarkerJSON(eventID);
            default:
                return getCentraltBookMarkerJSON(eventID);
        }
    }


    private static JSONArray getCentraltBookMarkerJSON(String eventId){
        String api = String.format("%s/member-market/api/event/fancy-markets.json?eventIds=%s&marketType=CENTRAL_FANCY", memberMarketServiceURL,eventId);
        return WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }

    public static List<BookmakerEvent> findEventHasBookmakerMarket(String sportid){
        List<String> lstEventID = getAllEventOfSport(sportid);
        List<BookmakerEvent> lstBMEvents =new ArrayList<>();
        BookmakerEvent bmEvent;
        if(Objects.nonNull(lstEventID)){
            for(int i = 0; i < lstEventID.size(); i++){
                bmEvent = getWicketBookmaker(lstEventID.get(i));
                if(Objects.nonNull(bmEvent))
                    lstBMEvents.add(bmEvent);
            }
            return lstBMEvents;
        }
        System.out.println("DEBUG: Cricket has NO event has WicketFancy");
        return null;
    }

    public static BookmakerEvent getEventHasBMAsStatus(List<BookmakerEvent> lstBMEvent,String status){
        for(int i=0; i< lstBMEvent.size(); i++){
            if(Objects.nonNull(lstBMEvent.get(i).getMarketStatus(status)))
                return lstBMEvent.get(i);
        }
        System.out.println(String.format("There is no event has Bookmaker market available as input status: %s",status));
        return null;
    }

    public static BookmakerEvent getEventHasBMNOTINStatus(List<BookmakerEvent> lstBMEvent,String status){
        List<BookmakerMarket1> bmMarket;
        for(int i=0; i< lstBMEvent.size(); i++){
            bmMarket = lstBMEvent.get(i).getBMMarket();
            for(int j =0; j<bmMarket.size(); j++){
                if(!bmMarket.get(j).getMarketStatus().equalsIgnoreCase(status))
                    return lstBMEvent.get(i);
            }

        }
        System.out.println(String.format("There is no event has Bookmaker market available as input status: %s",status));
        return null;
    }

    private static JSONObject getWicketBookmakerEvent(String eventID){
        String url = String.format(Configs.WICKET_BOOKMAKER_API_URL,eventID);
        JSONObject eventObj = WSUtils.getGETJSONResponse(url,null);
        if(Objects.nonNull(eventObj)){
            int status = eventObj.getInt("status");
            if(status == 1){
                return eventObj;
            }
        }
        System.out.println(String.format("DEBUG: Get Wicket Bookmaker of event %s api is null:" ,eventID, eventObj.toString()));
        return null;
    }


    public static BookmakerEvent getWicketBookmaker(String eventID){
        JSONObject eventObj = getWicketBookmakerEvent(eventID);
        if(Objects.nonNull(eventObj)) {
            return new BookmakerEvent.Builder()
                    .eventName(eventObj.getString("eventName"))
                    .competitionNAme(eventObj.getString("competition"))
                    .eventID(Integer.toString(eventObj.getInt("eventId")))
                    .bmMarket(getBookmakerMarket(eventObj))
                    .build();
        }
        return null;
    }

    private static List<BookmakerMarket1> getBookmakerMarket(JSONObject jObj){
        List<BookmakerMarket1> lstMarket= new ArrayList<>();
        JSONArray jArr = jObj.getJSONArray("marketList");
        if(Objects.nonNull(jArr)) {
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject marketObj = jArr.getJSONObject(i);
                lstMarket.add( new BookmakerMarket1.Builder()
                        .marketName(marketObj.getString("marketName"))
                        .marketStatus(marketObj.getString("statusName"))
                        .delayBetting(marketObj.getInt("delayBetting"))
                        .bmSelection(getBookmakerSelection(marketObj))
                        .rebateRatio(marketObj.getInt("rebateRatio"))
                        .minMaxSetting(marketObj.getString("minMaxSetting"))
                        .marketTime(marketObj.getString("marketTime"))
                        .ratingExposure(marketObj.getInt("ratingExposure"))
                        .marketID(marketObj.getInt("marketId"))
                        .build());
            }
            return lstMarket;
        }
        System.out.println("DEBUG: getGETJSONResponse is null" + jArr.toString());
        return null;
    }

    private static List<BookmakerSelection> getBookmakerSelection(JSONObject jObj){
        List<BookmakerSelection> lstSelection= new ArrayList<>();
        JSONArray jArr = jObj.getJSONArray("selectionList");
        if(Objects.nonNull(jArr)) {
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject marketObj = jArr.getJSONObject(i);
                lstSelection.add( new BookmakerSelection.Builder()
                        .selectionName(marketObj.getString("selectionName"))
                        .backOdds(marketObj.getInt("backOdds"))
                        .selectionID(marketObj.getInt("selectionId"))
                        .layOdds(marketObj.getInt("layOdds"))
                        .selectionStatus(marketObj.getString("selectionStatus"))
                        .build());
            }
            return lstSelection;
        }
        System.out.println("DEBUG: There Selection Array is null" + jArr.toString());
        return null;
    }
    *//**
     * This action get all fancy market from api with the corresponding event
     * @param eventID
     * @return
     *//*
    public static List<BookmakerMarket> getListFancyInEvent(String eventID){
        List<BookmakerMarket> lstMarket = new ArrayList<>();
        JSONArray marketJSONArray =  getCentraltBookMarkerJSON(eventID);
        if(marketJSONArray.length()==0){
            System.out.println("DEBUG: No data get fancy market api of event id" + eventID);
            return null;
        }
        if(Objects.nonNull(marketJSONArray)) {
            for (int i = 0; i < marketJSONArray.length(); i++) {
                JSONObject marketObj = marketJSONArray.getJSONObject(i);
                lstMarket.add( new BookmakerMarket.Builder()
                       .eventName(marketObj.getString("eventName"))
                        .marketID(Integer.toString(marketObj.getInt("marketId")))
                        .marketName(marketObj.getString("marketName"))
                       .eventID(eventID)
                      .status(marketObj.getString("status"))
                     .marketType(marketObj.getString("marketType"))
                   .minBet(marketObj.getInt("minBet"))
                     .maxBet(marketObj.getInt("maxBet"))
                        .build());
            }
            return lstMarket;
        }
        System.out.println("DEBUG: getGETJSONResponse is null" + marketJSONArray.toString());
        return null;
    }

 *//*   *//**//**
     * This action will get a Bookmaker Market with the expected status
     * @param eventID the list market get from api
     * @param status the expected status
     * @return the list with expected status
     *//**//*
    public static BookmakerMarket getBookmakerMarketHasExpectedStatusInEvent(String fancyProviderCode,String eventID, String status){
        List<BookmakerMarket> lstMarket = getListBookmakerInEvent(eventID, status,fancyProviderCode);
        if(Objects.nonNull(lstMarket)) {
            for (BookmakerMarket market : lstMarket) {
                if (market.getStatus().equalsIgnoreCase(status)) {
                    return market;
                }
            }
        }
        return null;
    }*//*
     *//**
     * This action get all fancy market from api with the corresponding event
     * @param eventID
     * @return
     *//*
    public static List<BookmakerMarket> getListBookmakerInEvent(String eventID,String provider_code){
        List<FancyMarket> lstMarket = new ArrayList<>();
        JSONArray marketJSONArray =  getFancyJSONByProvider(eventID,provider_code);
        if(marketJSONArray.length()==0){
            System.out.println("DEBUG: No data get fancy market api of event id" + eventID);
            return null;
        }
        String marketName ;
        String marketType ;
        if(Objects.nonNull(marketJSONArray)) {
            for (int i = 0; i < marketJSONArray.length(); i++) {
                JSONObject marketObj = marketJSONArray.getJSONObject(i);
                if(marketObj.has("marketName")){
                    marketName = marketObj.getString("marketName");
                }else
                    marketName = marketObj.getString("name");
                if(marketObj.has("marketName")){
                    marketType = marketObj.getString("marketType");
                }else
                    marketType = marketObj.getString("type");

                lstMarket.add( new FancyMarket.Builder()
                        .eventName(marketObj.getString("eventName"))
                        .marketID(Integer.toString(marketObj.getInt("marketId")))
                        .marketName(marketName)
                        .eventID(eventID)
                        .status(marketObj.getString("status"))
                        .marketType(marketType)
                        .minBet(marketObj.getInt("minBet"))
                        .maxBet(marketObj.getInt("maxBet"))
                        .build());
            }
            return lstMarket;
        }
        System.out.println("DEBUG: getGETJSONResponse is null" + marketJSONArray.toString());
        return null;
    }






*/


}
