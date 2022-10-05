package membersite.utils.betplacement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.FancyMarket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static baseTest.BaseCaseMerito.domainURL;
import static baseTest.BaseCaseMerito.environment;
import static membersite.common.FEMemberConstants.WICKET_BOOKMAKER_CODE;

public class FancyUtils {

    private static JSONArray getWicketFancyJSON(String eventId){
        String api = String.format("%s/member-service/member-market/api/event/wicket-fancy-markets.json?eventIds=%s", domainURL,eventId);
        return WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }

    private static JSONArray getWicketBookMarkerJSON(String eventId){
        String api = String.format("https://apifancy.9wickets.com/api/apiFancyBet/FANCYBET/queryBookMakerMarkets?eventIds=%s0&cert=fZzuFTbgoKU5GC5l",eventId);
       // String api = String.format("https://www.9wickets.com/apiFancyBet/FANCYBET/queryBookMakerMarkets?eventIds=%s&cert=fZzuFTbgoKU5GC5lR",eventId);
       // String api = String.format("%s/member-service/member-market/api/event/bookmaker-markets.json?eventIds=%s&marketType=CENTRAL_BOOKMAKER", domainURL,eventId);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        return jsonObject.getJSONArray("events");
    }

    private static JSONArray getCentraltBookMarkerJSON(String eventId){
        String api = String.format("%s/member-service/member-market/api/event/bookmaker-markets.json?eventIds=%s&marketType=CENTRAL_BOOKMAKER", domainURL,eventId);
        return WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }

    private static JSONArray getFancyJSON(String eventId){
        String api = String.format("%sf27f-gateway/event/%s/market-by-event", domainURL,eventId);
        // Workarround to special cookies to get 27 fancy
        String coookie =  DriverManager.getDriver().getCookies().toString();
        int index = coookie.indexOf("MESESSION");
        String aa = coookie.substring(index,coookie.length());
        coookie = aa.split(";")[0];
        //end workarround
        return WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON, coookie,Configs.HEADER_JSON_CHARSET);
    }
    private static JSONArray getCentralFancyJSON(String eventId){
        String api = String.format("%s/member-service/member-market/api/event/fancy-markets.json?eventIds=%s&marketType=CENTRAL_FANCY", domainURL,eventId);
        return WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
    }

    public static boolean isEventHasMarketType(String eventId, String marketType){
        JSONArray eventObj;
        switch (marketType)
        {
            case "FAIR_27FANCY":
                eventObj= getFancyJSON(eventId);
                break;
            case "WICKET_FANCY":
                eventObj = getWicketFancyJSON(eventId);
                break;
            case "WICKET_BOOKMAKER":
               // eventObj = getWicketBookMarkerJSON(eventId);
                List<BookmakerMarket> bookmaker = getListWicketBookmakerInEvent(eventId);
               if(Objects.nonNull(bookmaker)){
                   if(!bookmaker.get(0).getStatus().equalsIgnoreCase("OFFLINE")){
                       return true;
                   }
               }
                return false;

            case "MANUAL_ODDS":
                eventObj = getCentraltBookMarkerJSON(eventId);
                break;
            default:
                eventObj = getCentralFancyJSON(eventId);
                break;
        }
        if(eventObj.length()==0){
            return false;
        }
        return true;
    }

 /*
    public static List<FancyMarket> findEventHasFancyMarket(String sportid, String fancyStatus){
        List<String> lstEventID = getAllEventOfSport(sportid);
        String eventID;
        List<FancyMarket> lsFc;
        if(Objects.nonNull(lstEventID)){
            for(int i = 0; i < lstEventID.size(); i++){
                eventID = lstEventID.get(i);
                lsFc = getFancyHasExpectedStatusInEvent(eventID,fancyStatus);
                if(Objects.nonNull(lsFc) )
                    if(lsFc.size()!=0)
                        return lsFc;
            }
        }
        System.out.println("DEBUG: Cricket has NO event has WicketFancy");
        return null;
    }

    private static JSONObject getWicketFancyEvent(String eventID){
        String url = String.format(Configs.WICKET_FANCY_API_URL,eventID);
        JSONObject eventObj = WSUtils.getGETJSONResponse(url,null);
        if(Objects.nonNull(eventObj)){
            return eventObj;
        }
        System.out.println("DEBUG: Get Wicket Fancy api is null" + eventObj.toString());
        return null;
    }

   public static List<FancyMarket> findEventHasBookmakerMarket(String sportid, String fancyStatus){
        List<String> lstEventID = getAllEventOfSport(sportid);
        String eventID;
        List<FancyMarket> lsFc;
        if(Objects.nonNull(lstEventID)){
            for(int i = 0; i < lstEventID.size(); i++){
                eventID = lstEventID.get(i);
                lsFc = getWicketBookmakerEvent(eventID);
                if(Objects.nonNull(lsFc) )
                    if(lsFc.size()!=0)
                        return lsFc;
            }
        }
        System.out.println("DEBUG: Cricket has NO event has WicketFancy");
        return null;
    }*/

    /**
     * This action get all fancy market from api with the corresponding event
     * @param eventID
     * @return
     */
    public static List<FancyMarket> getListFancyInEvent(String eventID){
        List<FancyMarket> lstMarket = new ArrayList<>();
        JSONArray marketJSONArray =  getWicketFancyJSON(eventID);
        if(marketJSONArray.length()==0){
            System.out.println("DEBUG: No data get fancy market api of event id" + eventID);
            return null;
        }
        if(Objects.nonNull(marketJSONArray)) {
            for (int i = 0; i < marketJSONArray.length(); i++) {
                JSONObject marketObj = marketJSONArray.getJSONObject(i);
                lstMarket.add( new FancyMarket.Builder()
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

    /**
     * This action get all fancy market from api with the corresponding event
     * @param eventID
     * @return
     */
    public static List<FancyMarket> getList27FancyInEvent(String eventID){
        List<FancyMarket> lstMarket = new ArrayList<>();
        JSONArray marketJSONArray =  getFancyJSON(eventID);
        if(marketJSONArray.length()==0){
            System.out.println("DEBUG: No data get fancy market api of event id" + eventID);
            return null;
        }
        if(Objects.nonNull(marketJSONArray)) {
            for (int i = 0; i < marketJSONArray.length(); i++) {
                JSONObject marketObj = marketJSONArray.getJSONObject(i);
                lstMarket.add( new FancyMarket.Builder()
                        .eventName(marketObj.getString("eventName"))
                        .marketID(Integer.toString(marketObj.getInt("marketId")))
                        .marketName(marketObj.getString("name"))
                        .eventID(eventID)
                        .status(marketObj.getString("status"))
                        .marketType(marketObj.getString("type"))
                        .minBet(marketObj.getInt("minBet"))
                        .maxBet(marketObj.getInt("maxBet"))
                        .build());
            }
            return lstMarket;
        }
        System.out.println("DEBUG: getGETJSONResponse is null" + marketJSONArray.toString());
        return null;
    }

    /**
     * This action will get the list Wicket Fancy with the expected status
     * @param eventID the list market get from api
     * @param status the expected status
     * @return the list with expected status
     */
    public static List<FancyMarket> get27FancyHasExpectedStatusInEvent(String eventID, String status){
        List<FancyMarket> lstOutput = new ArrayList<>();
        List<FancyMarket> lstMarket = getList27FancyInEvent(eventID);
        if(Objects.nonNull(lstMarket)) {
            for (FancyMarket market : lstMarket) {
                if (market.getStatus().equalsIgnoreCase(status)) {
                    lstOutput.add(market);
                }
            }
            return lstOutput;
        }
        System.out.println(String.format("DEBUG: There is no wicket fancy display in the event %s", eventID ));
        return null;
    }
/*

    */
/**
     * This action will get the list Wicket Fancy with the expected status
     * @param eventID the list market get from api
     * @param status the expected status
     * @return the list with expected status
     *//*

    public static List<FancyMarket> getWicketFancyHasExpectedStatusInEvent(String eventID, String status){
        List<FancyMarket> lstOutput = new ArrayList<>();
        List<FancyMarket> lstMarket = getWicketFancyJSON(eventID);
        if(Objects.nonNull(lstMarket)) {
            for (FancyMarket market : lstMarket) {
                if (market.getStatus().equalsIgnoreCase(status)) {
                    lstOutput.add(market);
                }
            }
            return lstOutput;
        }
        System.out.println(String.format("DEBUG: There is no wicket fancy display in the event %s", eventID ));
        return null;
    }
*/

    /**
     * This action will get the list Wicket Fancy with the expected status
     * @param eventID the list market get from api
     * @param status the expected status
     * @return the list with expected status
     */
    public static List<FancyMarket> getFancyHasExpectedStatusInEvent(String eventID, String status){
        List<FancyMarket> lstOutput = new ArrayList<>();
        List<FancyMarket> lstMarket = getListFancyInEvent(eventID);
        if(Objects.nonNull(lstMarket)) {
            for (FancyMarket market : lstMarket) {
                if (market.getStatus().equalsIgnoreCase(status)) {
                    lstOutput.add(market);
                }
            }
            return lstOutput;
        }
        System.out.println(String.format("DEBUG: There is no wicket fancy display in the event %s", eventID ));
        return null;
    }
   /* private static List<Double> getMinMaxHandicap(List<Wager> lstWager){
        List<Double> minMax = new ArrayList<>();
      List<Double> handicap = new ArrayList<>();
        for(int i = 0; i<lstWager.size();i++){
            handicap.add(lstWager.get(i).getHandicap());
        }
        minMax.add(0,Collections.min(handicap));
        minMax.add(1, Collections.max(handicap));
        return minMax;
    }
    private static double getLiabilityAtHandicapPoint(List<Wager>listWager, int handicap){
        double profitLiability = 0.0;
        for(int i =0; i< listWager.size(); i++){
            profitLiability = profitLiability + listWager.get(i).getPnlatHandicapPoint(handicap);
        }
        return profitLiability;
    }
    public static double getMarketLiability(List<Wager> lstWager){
        List<Double> lstPnl = new ArrayList<>();
        List<Double> minMaxHandicap = getMinMaxHandicap(lstWager);
        int min = minMaxHandicap.get(0).intValue();
        int max = minMaxHandicap.get(1).intValue();
        for(int i =min; i <= max; i++){
           lstPnl.add(getLiabilityAtHandicapPoint(lstWager,i));
        }
        return  Collections.min(lstPnl);
    }*/

    /**
     * This action will get the list Wicket Fancy with the expected status
     * @param eventID the list market get from api
     * @param status the expected status
     * @return the list with expected status
     */
    public static List<BookmakerMarket> getMarketHasExpectedStatusInEvent(String eventID, String status){
        List<BookmakerMarket> lstOutput = new ArrayList<>();
        List<BookmakerMarket> lstMarket = getListCentralBookmakerInEvent(eventID);
        if(Objects.nonNull(lstMarket)) {
            for (BookmakerMarket market : lstMarket) {
                if (market.getStatus().equalsIgnoreCase(status)) {
                    lstOutput.add(market);
                }
            }
            return lstOutput;
        }
        System.out.println(String.format("DEBUG: There is no wicket fancy display in the event %s", eventID ));
        return null;
    }
    /**
     * This action get all bookmaker market from api with the corresponding event
     * @param eventID
     * @return
     */
    public static List<BookmakerMarket> getListCentralBookmakerInEvent(String eventID){
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
    /**
     * This action get all bookmaker market from api with the corresponding event
     * @param eventID
     * @return
     */
    public static List<BookmakerMarket> getListWicketBookmakerInEvent(String eventID){
        List<BookmakerMarket> lstMarket = new ArrayList<>();
        JSONArray marketJSONArray =  getWicketBookMarkerJSON(eventID);
        if(marketJSONArray.length()==0){
            System.out.println("DEBUG: No data get fancy market api of event id" + eventID);
            return null;
        }
        if(Objects.nonNull(marketJSONArray)) {
            for (int i = 0; i < marketJSONArray.length(); i++) {
                JSONObject eventObj = marketJSONArray.getJSONObject(i);
                JSONArray marketArr = eventObj.getJSONArray("marketList");
                JSONObject marketObj = marketArr.getJSONObject(0);
                lstMarket.add( new BookmakerMarket.Builder()
                        .eventName(eventObj.getString("eventName"))
                        .marketID(Integer.toString(marketObj.getInt("marketId")))
                        .marketName(marketObj.getString("marketName"))
                        .eventID(eventID)
                        .status(marketObj.getString("statusName"))
                        .marketType(WICKET_BOOKMAKER_CODE)
                        .marketTime(marketObj.getString("marketTime"))
                        .build());
            }
            return lstMarket;
        }
        System.out.println("DEBUG: getGETJSONResponse is null" + marketJSONArray.toString());
        return null;
    }
}
