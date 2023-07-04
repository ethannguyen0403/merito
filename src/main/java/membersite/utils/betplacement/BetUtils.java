package membersite.utils.betplacement;

import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.DoubleUtils;
import com.paltech.utils.WSUtils;
import membersite.objects.AccountBalance;
import membersite.objects.Wager;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.Event;
import membersite.objects.sat.FancyMarket;
import membersite.objects.sat.Order;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

import static baseTest.BaseCaseTest.domainURL;
import static baseTest.BaseCaseTest.memberMarketServiceURL;

public class BetUtils {

    private static JSONObject getAppConfig() {
        String api = String.format("%s/member-service/app/sat/config", domainURL);
        return WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static String getAppName() {
        JSONObject jsonObject = getAppConfig();
        return jsonObject.getString("name");
    }


    private static JSONObject getEvent(String sportID) {
        //    String api = String.format("%s/member-service/market-service/whitelist/new/sport/%s?tzo=%s&_=%s",domainURL,"4","GMT%2B0700",DateUtils.getMilliSeconds());
        String api = String.format("%s/whitelist/new/sport/%s", memberMarketServiceURL, sportID);
        JSONObject sportObj = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(sportObj)) {
            return sportObj.getJSONObject("eventIds");

        }
        System.out.println("DEBUG: getGETJSONResponse is null");
        return null;
    }

    public static FancyMarket findOpenFancyMarket(String sportID, String providerFancyCode) {
        // Get all available event of a sport
        JSONObject sportObj = getEvent(sportID);
        JSONArray eventArr = sportObj.getJSONArray(sportID);
        if (Objects.isNull(eventArr)) {
            System.out.println("DEBUG: getGETJSONResponse is null");
            return null;
        }
        for (int i = 0; i < eventArr.length(); i++) {
            // Get the first Open Fancy markets according provider(27 Fancy, Wicket Fancy, Central Fancy of an event )
            FancyMarket fancyMaket = FancyUtils.getFancyHasExpectedStatusInEvent(providerFancyCode, Integer.toString(eventArr.getInt(i)), "OPEN");
            if (Objects.nonNull(fancyMaket)) {
                return fancyMaket;
            }
        }
        return null;
    }

    public static BookmakerMarket findOpenBookmakerMarket(String sportID, String providerFancyCode, String status) {
        // Get all available event of a sport
        JSONObject sportObj = getEvent(sportID);
        JSONArray eventArr = sportObj.getJSONArray(sportID);
        if (Objects.isNull(eventArr)) {
            System.out.println("DEBUG: getGETJSONResponse is null");
            return null;
        }
        for (int i = 0; i < eventArr.length(); i++) {
            // Get the first Open Bookmaker markets according provider(27 Fancy, Wicket Fancy, Central Fancy of an event )
            BookmakerMarket bmMaket = FancyUtils.getBookmakerMarketHasExpectedStatusInEvent(providerFancyCode, Integer.toString(eventArr.getInt(i)), status);
            if (Objects.nonNull(bmMaket)) {
                return bmMaket;
            }
        }
        return null;
    }

   /* public static FancyMarket findOpen27FancyMarket(String sportID){
        JSONObject sportObj = getEvent(sportID);
        JSONArray eventArr = sportObj.getJSONArray(sportID);
        if(Objects.isNull(eventArr)){
            System.out.println("DEBUG: getGETJSONResponse is null");
            return null;
        }
        for (int i = 0; i < eventArr.length(); i++) {
            List<FancyMarket> lstFancy = FancyUtils.get27FancyHasExpectedStatusInEvent(Integer.toString(eventArr.getInt(i)),"OPEN");
            if(Objects.nonNull(lstFancy))
            {
                return  lstFancy.get(0);
            }

        }
        return null;
    }
    public static FancyMarket findOpenWicketMarket(String sportID){
        JSONObject sportObj = getEvent(sportID);
        JSONArray eventArr = sportObj.getJSONArray(sportID);
        if(Objects.isNull(eventArr)){
            System.out.println("DEBUG: getGETJSONResponse is null");
            return null;
        }
        for (int i = 0; i < eventArr.length(); i++) {

            List<FancyMarket> lstFancy = FancyUtils.getFancyHasExpectedStatusInEvent(Integer.toString(eventArr.getInt(i)),"OPEN");
            if(Objects.nonNull(lstFancy))
            {
                return  lstFancy.get(0);
            }

        }
        return null;
    }
    public static FancyMarket findOpenCentralFancyMarket(String sportID){
        // Get all available event of a sport
        JSONObject sportObj = getEvent(sportID);
        JSONArray eventArr = sportObj.getJSONArray(sportID);
        if(Objects.isNull(eventArr)){
            System.out.println("DEBUG: getGETJSONResponse is null");
            return null;
        }
        for (int i = 0; i < eventArr.length(); i++) {
            // Get the first Open Fancy markets according provider(27 Fancy, Wicket Fancy, Central Fancy of an event )
            List<FancyMarket> lstFancy = FancyUtils.getFancyHasExpectedStatusInEvent(Integer.toString(eventArr.getInt(i)),"OPEN");
            if(Objects.nonNull(lstFancy))
            {
                return  lstFancy.get(0);
            }

        }
        return null;
    }*/

    public static String getMinBet(String sportName, String betType) {
        String api = String.format("%s/member-service/user/bdata?tzo=GMT+05:30", domainURL);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("betSettings")) {
                JSONArray arraySports = jsonObject.getJSONArray("betSettings");
                String betTypeName = betType.toUpperCase().equals("BACK") ? "backMinBet" : "layMinBet";
                for (int i = 0; i < arraySports.length(); i++) {
                    JSONObject jsonSportSetting = arraySports.getJSONObject(i);
                    if (jsonSportSetting.getString("sportGroup").equals(sportName.toUpperCase().toString())) {
                        return Integer.toString(jsonSportSetting.getInt(betTypeName));
                    }
                }
            }
        }
        System.out.println("DEBUG: getGETJSONResponse is null");
        return "-1";
    }

    public static String getMaxBet(String sportName, String betType) {
        String api = String.format("%s/member-service/user/bdata?tzo=GMT+05:30", domainURL);
        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("betSettings")) {
                JSONArray arraySports = jsonObject.getJSONArray("betSettings");
                String betTypeName = betType.toUpperCase().equalsIgnoreCase("BACK") ? "backMaxBet" : "layMaxBet";
                for (int i = 0; i < arraySports.length(); i++) {
                    JSONObject jsonSportSetting = arraySports.getJSONObject(i);
                    if (jsonSportSetting.getString("sportGroup").equals(sportName.toUpperCase().toString())) {
                        return Integer.toString(jsonSportSetting.getInt(betTypeName));
                    }
                }
            }
        }
        System.out.println("DEBUG: getGETJSONResponse is null");
        return "-1";
    }

//    public static String getMinBet(String sportName, String betType) {
//        String api = String.format("%s/member-service/user/bdata?tzo=GMT+05:30", domainURL);
//        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
//        if (Objects.nonNull(jsonObject)) {
//            if (jsonObject.has("betSettings")) {
//                JSONArray arraySports = jsonObject.getJSONArray("betSettings");
//                String betTypeName = betType.equals(SportPage.BetType.BACK) ? "backMinBet" : "layMinBet";
//                for (int i=0; i < arraySports.length(); i++) {
//                    JSONObject jsonSportSetting = arraySports.getJSONObject(i);
//                    if (jsonSportSetting.getString("sportGroup").equals(sportName.toString())) {
//                        return Integer.toString(jsonSportSetting.getInt(betTypeName));
//                    }
//                }
//            }
//        }
//        System.out.println("DEBUG: getGETJSONResponse is null");
//        return "-1";
//    }
//    public static String getMaxBet(SportPage.Sports sportName, SportPage.BetType betType) {
//        String api = String.format("%s/member-service/user/bdata?tzo=GMT+05:30",domainURL);
//        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, null,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
//        if (Objects.nonNull(jsonObject)) {
//            if (jsonObject.has("betSettings")) {
//                JSONArray arraySports = jsonObject.getJSONArray("betSettings");
//                String betTypeName = betType.equals(SportPage.BetType.BACK) ? "backMaxBet" : "layMaxBet";
//                for (int i=0; i < arraySports.length(); i++) {
//                    JSONObject jsonSportSetting = arraySports.getJSONObject(i);
//                    if (jsonSportSetting.getString("sportGroup").equals(sportName.toString())) {
//                        return Integer.toString(jsonSportSetting.getInt(betTypeName));
//                    }
//                }
//            }
//        }
//        System.out.println("DEBUG: getGETJSONResponse is null");
//        return "-1";
//    }

    /**
     * To get placed order form api
     *
     * @author isabella.huynh
     * @created 10/3/2020
     */
    public static Order getOrder() {
        Order wagers = new Order();
        String api = String.format("%s/member-service/order/place-bets?ofs=-420", domainURL);
        String jsn = String.format("[{\"eachWayDivisor\":0,\"eventId\":29786610,\"handicap\":0,\"marketId\":\"1.170361615\",\"marketType\":\"MATCH_ODDS\",\n" +
                "\"numberOfActiveRunners\":3,\"numberOfWinner\":1,\"odds\":\"1.01\",\"orderType\":\"LIMIT\",\"payout\":0,\"period\":\"NON_LIVE\",\n" +
                "\"persistenceType\":\"LAPSE\",\"selectionId\":2224159,\"side\":\"LAY\",\"stake\":\"10\",\"orderId\":0,\"browserTime\":%s,\n" +
                "\"inplay\":false}]", DateUtils.getMilliSeconds());
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectResponse(api, Configs.HEADER_JSON, jsn, Configs.HEADER_JSON);
        //JSONObject json = WSUtils.getPos

        if (Objects.nonNull(jsonObject)) {
            if (jsonObject.has("selections")) {
                boolean isBack = jsonObject.getString("side").equalsIgnoreCase("BACK");
                return new Order.Builder()
                        .eventID(jsonObject.getString("eventId"))
                        .marketID(jsonObject.getString("marketId"))
                        .isBack(isBack)
                        .stake(jsonObject.getString("stake"))
                        .odds(jsonObject.getString("odds"))
                        .persistenceType(jsonObject.getString("persistenceType"))
                        .stakeMatched(jsonObject.getString("stakeMatched"))
                        .newOdds(jsonObject.getString("newOdds"))
                        .orderID(jsonObject.getString("orderId"))
                        .placeDate(jsonObject.getString("placedDate"))
                        .matchedDate(jsonObject.getString("matchedDate"))
                        .selectionId(jsonObject.getString("selectionId"))
                        .errorMessage(jsonObject.getString("errorMessage"))
                        .build();
            }
        }
        System.out.println("DEBUG: getGETJSONResponse is null");
        return null;
    }

    public static AccountBalance getUserBalance(String domainUrl) {
        String api = String.format("%s/member-service/sat/user/balance?_t=%s", domainUrl, DateUtils.getMilliSeconds());
        JSONArray jsonArray = WSUtils.getGETJSONArrayWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        Double exposure;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonBalance = jsonArray.getJSONObject(0);
            if (Objects.nonNull(jsonBalance)) {
               /* if (isCredit) {
                    if(jsonBalance.has("walletCode")){
                        if (jsonBalance.getString("walletCode").equalsIgnoreCase("Exchange")) {
                            exposure = DoubleUtils.roundUpWithTwoPlaces(jsonBalance.getDouble("outstanding"));
                            if(exposure > 0.00){
                                exposure = exposure * (-1);
                            }
                            return new AccountBalance.Builder()
                                    .balance(String.format(Locale.getDefault(), "%,.2f",DoubleUtils.roundUpWithTwoPlaces(jsonBalance.getDouble("betCredit"))))
                                    .exposure(String.format("%.2f",exposure))
                                    .creditRefer(String.format(Locale.getDefault(), "%,.2f",DoubleUtils.roundUpWithTwoPlaces(jsonBalance.getDouble("givenCredit"))))
                                    .build();
                        }
                    }

                }
                else{*/
                exposure = DoubleUtils.roundUpWithTwoPlaces(jsonBalance.getDouble("exposure"));
                if (exposure > 0.00) {
                    exposure = exposure * (-1);
                }
                return new AccountBalance.Builder()
                        .balance(String.format(Locale.getDefault(), "%,.2f", DoubleUtils.roundUpWithTwoPlaces(jsonBalance.getDouble("balance"))))
                        .exposure(String.format("%,.2f", exposure))
                        .creditRefer(String.format("%,.2f", jsonBalance.getDouble("creditRefer")))
                        .build();
            }


            // }
        }

        System.out.println("DEBUG: getGETJSONResponse is null");
        return null;
    }

    public static AccountBalance getUserBalance() {
        return getUserBalance(domainURL);
    }

    public static List<String> getProductInfo() {
        String api = String.format("%s/member-service/sat/user/product-info", domainURL);
        JSONObject jsonObj = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON_CHARSET, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        JSONArray jsonArray = jsonObj.getJSONArray("products");
        if (Objects.nonNull(jsonArray)) {
            List<String> lstProduct = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                lstProduct.add(jsonArray.get(i).toString());
            }
            return lstProduct;
        }
        System.out.println("DEBUG: getGETJSONResponse is null" + jsonArray.toString());
        return null;
    }

    public static double stakeMakeInsufficientBalance(String availableBalance, String odds, boolean isBack) {
        if (!isBack) {
            return
                    Double.valueOf(availableBalance.replaceAll(",", "")) / (Double.parseDouble(odds) - 1); //  String.format(Locale.getDefault(),"%,.2f",Double.valueOf(availableBalance.replaceAll(",", ""))  / (Double.parseDouble(odds)-1));
        } else
            return Double.valueOf(availableBalance.replaceAll(",", ""));
    }

    public static List<ArrayList<String>> getAccountStatement(String fromDate, String toDate, String tz) {
        List<ArrayList<String>> lstReport = new ArrayList<>();
        String jsn = String.format("{\"fromDate\":\"%s\",\"toDate\":\"%s\",\"tz\":\"%s\"}", fromDate, toDate, tz);
        String api = String.format("/member-report-service/report/sat/account-statement", domainURL);
        JSONArray jsonArray = WSUtils.getPOSTJSONArrayWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);

        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String eventID = Integer.toString(jsonObject.getInt("eventId"));
                String product = jsonObject.getString("product");
                String competitionName = jsonObject.getString("competitionName");
                String settlementDate = new Date(jsonObject.getLong("settlementDate")).toString();
                //String marketStartTime =Long.toString(jsonObject.getLong("marketStartTime"));
                String marketStartTime = new Date(jsonObject.getLong("marketStartTime")).toString();
                String pnl = Double.toString(jsonObject.getDouble("pnl"));
                String marketID = Integer.toString(jsonObject.getInt("marketId"));
                String sportID = Integer.toString(jsonObject.getInt("sportId"));
                String marketName = jsonObject.getString("marketName");
                String statementType = jsonObject.getString("statementType");
                String cashBalance = Double.toString(jsonObject.getDouble("cashBalance"));
                String eventName = jsonObject.getString("eventName");
                String sportName = jsonObject.getString("sportName");
                String debit = Double.toString(jsonObject.getDouble("debit"));
                String credit = Double.toString(jsonObject.getDouble("credit"));
                ArrayList<String> lst = new ArrayList<String>(Arrays.asList(eventID, product, competitionName, settlementDate, marketStartTime,
                        pnl, marketID, sportID, marketID, marketName, statementType, cashBalance, eventName, sportName, debit, credit));
                lstReport.add(lst);
            }
        }
        return lstReport;
    }

    public static List<ArrayList<String>> getMyBet(String product, String type, String fromDate, String toDate, String tz) {
        List<ArrayList<String>> lstReport = new ArrayList<>();
        String jsn = String.format("{\"type\":\"%s\",\"betStatus\":\"matched\",\"fromDate\":\"%s\",\"toDate\":\"%s\",\"actualDate\":1,\"sportId\":0,\"product\":\"%s\",\"pageRequest\":{\"pageNumber\":1,\"pageSize\":20},\"wallet\":\"ALL\",\"groupMarket\":false,\"tz\":\"%s\"}", type, fromDate, toDate, product, tz);
        String api = String.format("%s/member-service/member-report-service/sat/my-bet", domainURL);
        JSONObject myBetJSONObj = WSUtils.getPOSTJSONObjectResponse(api, Configs.HEADER_JSON_CHARSET, jsn, Configs.HEADER_JSON);
        if (Objects.nonNull(myBetJSONObj)) {
            JSONArray betArrayObj = myBetJSONObj.getJSONArray("content");
            JSONObject jsonObject = betArrayObj.getJSONObject(0);
            JSONArray jsonArray = jsonObject.getJSONArray("bets");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject betInfoObj = jsonArray.getJSONObject(i);
                String marketName = String.format("%s / %s / %s", betInfoObj.getString("sportName"), betInfoObj.getString("eventName"), betInfoObj.getString("marketName"));
                String betID = betInfoObj.getString("orderId");
                String eventID = Integer.toString(betInfoObj.getInt("eventId"));
                String selection = betInfoObj.getString("runnerName");
                String betType = betInfoObj.getString("side");
                String odds = String.format("%.2f", betInfoObj.getDouble("odds"));
                String stake = String.format("%.2f", betInfoObj.getDouble("stake"));
                String profitLoss = String.format("%.2f", betInfoObj.getDouble("profit"));
                String status = betInfoObj.getString("displayStatus");
                String placeDate = DateUtils.convertMillisToDateTime(Long.toString(betInfoObj.getLong("placedDate")), "dd/MM/yyyy HH:mm:ss");
                String settledDate = DateUtils.convertMillisToDateTime(Long.toString(betInfoObj.getLong("settledDate")), "dd/MM/yyyy HH:mm:ss");
                String ipAddress = betInfoObj.getString("userIpAddress");

                lstReport.add(new ArrayList<String>(Arrays.asList(marketName, betID, eventID, selection, betType, odds, stake, profitLoss, status, placeDate, settledDate, ipAddress)));
            }
        }
        return lstReport;
    }

    public static List<ArrayList<String>> getTopMenu(String fromDate, String toDate, String tz) {
        List<ArrayList<String>> lstReport = new ArrayList<>();
        String jsn = String.format("{\"fromDate\":\"%s\",\"toDate\":\"%s\",\"tz\":\"%s\"}", fromDate, toDate, tz);
        String api = String.format("/member-report-service/report/sat/account-statement", domainURL);
        JSONArray jsonArray = WSUtils.getPOSTJSONArrayResponse(api, Configs.HEADER_JSON, jsn, Configs.HEADER_JSON);

        if (Objects.nonNull(jsonArray)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                //{"eventId":29797877,"product":"EXCHANGE","competitionName":"South Korean K League Classic","settlementDate":1589601600000,"marketStartTime":1589605203000,"pnl":-10,"marketId":170421198,"sportId":1,"marketName":"Match Odds","statementType":"PNL","cashBalance":274.983616,"eventName":"Sangju Sangmu v Gangwon","sportName":"Soccer","debit":10,"credit":0}
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String eventID = Integer.toString(jsonObject.getInt("eventId"));
                String product = jsonObject.getString("product");
                String competitionName = jsonObject.getString("competitionName");
                String settlementDate = new Date(jsonObject.getLong("settlementDate")).toString();
                //String marketStartTime =Long.toString(jsonObject.getLong("marketStartTime"));
                String marketStartTime = new Date(jsonObject.getLong("marketStartTime")).toString();
                String pnl = Double.toString(jsonObject.getDouble("pnl"));
                String marketID = Integer.toString(jsonObject.getInt("marketId"));
                String sportID = Integer.toString(jsonObject.getInt("sportId"));
                String marketName = jsonObject.getString("marketName");
                String statementType = jsonObject.getString("statementType");
                String cashBalance = Double.toString(jsonObject.getDouble("cashBalance"));
                String eventName = jsonObject.getString("eventName");
                String sportName = jsonObject.getString("sportName");
                String debit = Integer.toString(jsonObject.getInt("debit"));
                String credit = Double.toString(jsonObject.getDouble("credit"));
                ArrayList<String> lst = new ArrayList<String>(Arrays.asList(eventID, product, competitionName, settlementDate, marketStartTime,
                        pnl, marketID, sportID, marketID, marketName, statementType, cashBalance, eventName, sportName, debit, credit));
                lstReport.add(lst);
            }
        }
        return lstReport;
    }

    public static List<String> getAllEventOfSport(String sportId) {
        List<String> lstEvent = new ArrayList<>();
        String api = String.format("%s/whitelist/new/sport/%s", memberMarketServiceURL, sportId);
        JSONObject eventObj = WSUtils.getGETJSONResponse(api, null, Configs.HEADER_JSON);

        JSONArray eventArr = eventObj.getJSONObject("eventIds").getJSONArray("4");
        for (int i = 0; i < eventArr.length(); i++) {
            lstEvent.add(eventArr.get(i).toString());
        }
        return lstEvent;
    }

    private static JSONObject getWagerRespone(String sportID, String eventID, String marketID, String marketType) {
        String api = String.format("%s/member-service/sat/open-bet/market", domainURL);
        String jsn = String.format("{\"eventId\":\"%s\",\"isAsiaView\":false,\"isFancy\":false,\"marketFancy\":[],\"marketId\":\"%s\",\"marketIds\":[],\"sportId\":\"%s\",\"sportName\":\"\",\"viewType\":\"market\",\"marketType\":\"%s\",\"averageOdds\":false,\"consolidate\":false}", eventID, marketID, sportID, marketType);
        return WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    private static JSONObject ge27FancytWagerResponeOldUI(String eventID, String marketID) {
        String api = String.format("%s/member-service/my-bet/open-bet/market", domainURL);
        String jsn = String.format(" {\"marketId\":\"1.%s\",\"sportName\":\"cricket\",\"eventId\":\"%s\",\"viewType\":\"market\",\"marketIds\":[],\"isFancy\":false,\"marketFancy\"" +
                ":[%s],\"averageOdds\":false,\"isAsiaView\":false,\"consolidate\":false}", marketID, eventID, marketID);
        return WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    private static JSONObject getNormalWagerByMarketOldUI(String sportName, String eventID, String marketID) {
        String api = String.format("%s/member-service/my-bet/open-bet/market", domainURL);
        String jsn = String.format("{\"marketId\":\"1.%s\",\"sportName\":\"%s\",\"eventId\":\"%s\",\"viewType\":\"market\",\"marketIds\":[],\"isFancy\":false,\"marketFancy\"" +
                ":[],\"averageOdds\":false,\"isAsiaView\":false,\"consolidate\":false}", marketID, sportName.toLowerCase(), eventID);
        return WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    private static Wager defineWager(JSONObject obj) {
        return new Wager.Builder()
                .betID(obj.getInt("betId"))
                .betType(obj.getString("betType"))
                .bettingType(obj.getString("bettingType"))
                .handicap(obj.getDouble("handicap"))
                .liablity(obj.getDouble("liability"))
                .marketID(obj.getString("marketId"))
                .marketName(obj.getString("marketName"))
                .marketType(obj.getString("marketType"))
                .odds(obj.getDouble("odds"))
                .orderID(obj.getInt("orderId"))
                .payout(obj.getDouble("payout"))
                .persistenceType(obj.getString("persistentType"))
                .profit(obj.getDouble("profit"))
                .runnerName(obj.getString("runnerName"))
                .selectionID(obj.getString("selectionId"))
                .stake(obj.getDouble("stake"))
                .build();
    }

    public static List<Wager> getListMatchedandUnmatchedWager(String sportName, String eventID, String marketID) {
        List<Wager> lstWager = getOrderByStatus(sportName, eventID, marketID, "unmatched");
        lstWager.addAll(getOrderByStatus(sportName, eventID, marketID, "matched"));
        return lstWager;
    }

    public static List<Wager> getOrderByStatus(String sportName, String eventID, String marketID, String status) {
        List<Wager> lstWager = new ArrayList<>();
        try {
            JSONObject jsonObject = getNormalWagerByMarketOldUI(sportName, eventID, marketID);
            if (Objects.nonNull(jsonObject)) {
                if (jsonObject.has(status)) {
                    JSONObject matchObj = jsonObject.getJSONObject(status);
                    if (Objects.nonNull(matchObj)) {
                        lstWager = getListOrderbyBetType(matchObj, "LAY");
                        lstWager.addAll(getListOrderbyBetType(matchObj, "BACK"));
                    }
                }
                return lstWager;
            }
            return null;
        } catch (NullPointerException ex) {
            System.out.println("DEBUG: getGETJSONResponse is null");
            return null;
        }
    }

    private static List<Wager> getListOrderbyBetType(JSONObject jObj, String betType) {
        List<Wager> lstWager = new ArrayList<>();
        // Bet type is BACK | LAY
        String bType = betType.toUpperCase();
        if (jObj.has(bType)) {
            JSONArray arLay = jObj.getJSONArray(bType);
            if (arLay.length() != 0) {
                for (int i = 0; i < arLay.length(); i++) {
                    JSONObject wagerObj = arLay.getJSONObject(i);
                    lstWager.add(defineWager(wagerObj));
                }
            }
        }
        return lstWager;
    }

    /**
     * To get matched wager of a event form api
     *
     * @author isabella.huynh
     * @created 10/3/2020
     */
    public static List<Wager> get27FanycMatchedOpenBet(String eventID, String marketID) {
        List<Wager> lstWager = new ArrayList<>();
        try {
            JSONObject jsonObject = ge27FancytWagerResponeOldUI(eventID, marketID);
            if (Objects.nonNull(jsonObject)) {
                JSONObject matchObj = jsonObject.getJSONObject("matched");
                if (Objects.nonNull(matchObj)) {
                    lstWager = getListOrderbyBetType(matchObj, "LAY");
                    lstWager.addAll(getListOrderbyBetType(matchObj, "BACK"));
                }
                return lstWager;
            }
            return null;
        } catch (NullPointerException ex) {
            System.out.println("DEBUG: getGETJSONResponse is null");
            return null;
        }
    }

    /**
     * To get matched wager of a event form api
     *
     * @author isabella.huynh
     * @created 10/3/2020
     */
    public static List<Wager> getMatchedOpenBet(String sportID, String eventID, String marketID, String marketType) {
        List<Wager> lstWager = new ArrayList<>();
        try {
            JSONObject jsonObject = getWagerRespone(sportID, eventID, marketID, marketType);
            if (Objects.nonNull(jsonObject)) {
                JSONArray arr = jsonObject.getJSONArray("matched");
                if (arr.length() != 0) {
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject wagerObj = arr.getJSONObject(i);
                        lstWager.add(defineWager(wagerObj));
                    }
                    return lstWager;
                }
            }
            return null;
        } catch (NullPointerException ex) {
            System.out.println("DEBUG: getGETJSONResponse is null");
            return null;
        }
    }


    /**
     * To get matched wager of a event form api
     *
     * @author isabella.huynh
     * @created 10/3/2020
     */
    public static List<Wager> getUnmatchedOpenBet(String sportID, String eventID, String marketID, String marketType) {
        List<Wager> lstWager = new ArrayList<>();
        JSONObject jsonObject = getWagerRespone(sportID, eventID, marketID, marketType);
        if (Objects.nonNull(jsonObject)) {
            JSONArray arr = jsonObject.getJSONArray("unmatched");
            if (Objects.nonNull(arr)) {
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject wagerObj = arr.getJSONObject(i);
                    lstWager.add(defineWager(wagerObj));
                }
                return lstWager;
            }
            System.out.println(String.format("DEBUG:The market %s has no wager", marketID));
            return null;
        }
        System.out.println("DEBUG: getGETJSONResponse is null");
        return null;
    }

    public static List<Wager> getWagersByMarketID(List<Wager> lstWager, String marketID) {
        List<Wager> lstWagerOutput = new ArrayList<>();
        for (Wager wr : lstWager) {
            if (wr.getMarketID().contains(marketID)) {
                lstWagerOutput.add(wr);
            }
        }
        return lstWagerOutput;
    }

    public static List<ArrayList<String>> getProfitandLiabilityBySelection(List<Wager> lstWagers, List<String> lstSelection) {
        if (Objects.nonNull(lstWagers)) {
            List<ArrayList<String>> runnerPnlList = new ArrayList<>();
            ArrayList<String> selectionPnl;
            for (String selection : lstSelection) {
                double profit = 0;
                double liability = 0;
                selectionPnl = new ArrayList<>();
                for (Wager wager : lstWagers) {
                    if (wager.getRunnerName().equals(selection)) {
                        if (wager.getBetType().equalsIgnoreCase("BACK")) {
                            profit = profit + wager.getProfit();
                            liability = liability + (wager.getLiability() * (-1));
                        } else {
                            profit = profit + (wager.getLiability() * (-1));
                            liability = liability + wager.getProfit();
                        }
                    }
                }
                selectionPnl.add(selection);
                selectionPnl.add(String.format(Locale.getDefault(), "%,.2f", profit));
                selectionPnl.add(String.format(Locale.getDefault(), "%,.2f", liability));
                runnerPnlList.add(selectionPnl);
            }
            return runnerPnlList;
        }
        return null;
    }

    public static List<ArrayList<String>> calculateForecast(List<ArrayList<String>> pnLAllSelectionsLst) {
        List<ArrayList<String>> lstSelectionForecast = new ArrayList<>();
        ArrayList<String> selectionForecast = new ArrayList<>();
        double totalforCaseASelection = 0;
        if (Objects.nonNull(pnLAllSelectionsLst)) {
            for (int i = 0; i < pnLAllSelectionsLst.size(); i++) {
                totalforCaseASelection = 0;
                // set selection Name
                selectionForecast = new ArrayList<>();
                totalforCaseASelection = totalforCaseASelection + Double.valueOf(pnLAllSelectionsLst.get(i).get(1).replaceAll(",", ""));
                for (int j = 0; j < pnLAllSelectionsLst.size(); j++) {
                    if (i != j) {
                        totalforCaseASelection = totalforCaseASelection + Double.valueOf(pnLAllSelectionsLst.get(j).get(2).replaceAll(",", ""));
                    }
                }
                selectionForecast.add(pnLAllSelectionsLst.get(i).get(0));
                selectionForecast.add(String.format(Locale.getDefault(), "%,.2f", DoubleUtils.roundUpWithTwoPlaces(totalforCaseASelection)));
                lstSelectionForecast.add(selectionForecast);
            }
            return lstSelectionForecast;
        }
        return null;
    }

    public static String getLiabiltyonMarkrt(List<ArrayList<String>> lstForcast) {
        double min = Double.parseDouble(lstForcast.get(0).get(1));
        for (int i = 0; i < lstForcast.size(); i++) {
            if (min <= Double.parseDouble(lstForcast.get(i).get(1)))
                min = Double.parseDouble(lstForcast.get(i).get(1));
        }
        return String.format("%.2f", min);

    }

    private static JSONArray getListEvenHighlightJSON(String currency, String sportID) {
        String api = String.format("%s/member-service/member-market/exchange-service/sat/events/highlight", domainURL);
        String jsn = String.format("{\"currencyCode\":\"%s\",\"sportId\":\"%s\",\"filterMarkets\":[]}", currency, sportID);
        return WSUtils.getPOSTJSONArrayWithCookies(api, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static List<Event> getListEventHighLight(String currency, String sportID) {
        List<Event> lstEvent = new ArrayList<>();
        JSONArray marketJSONArray = getListEvenHighlightJSON(currency, sportID);
        if (marketJSONArray.length() == 0) {
            System.out.println("DEBUG: No Event");
            return null;
        }
        if (Objects.nonNull(marketJSONArray)) {
            for (int i = 0; i < marketJSONArray.length(); i++) {
                JSONObject marketObj = marketJSONArray.getJSONObject(i);
                lstEvent.add(new Event.Builder()
                        .eventName(marketObj.getString("eventName"))
                        .id(marketObj.getString("eventId"))
                        .build());
            }
            return lstEvent;
        }
        System.out.println("DEBUG: getGETJSONResponse is null" + marketJSONArray.toString());
        return null;
    }

    private static JSONObject getDataAggregator(String eventID, String marketID) {
        String api = String.format("https://funstg.beatus88.com/exchange-service/events/event/%s/data-aggregator?igm=true&tzo=GMT+0700&marketId=1.%s&currencyCode=HKD&rollupLimit=221", eventID, marketID);
        return WSUtils.getPOSTJSONObjectWithCookies(api, Configs.HEADER_JSON, null, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
    }

    public static List<String> getListSelectionsofMarket(String eventID, String marketID) {
        List<String> lstRunner = new ArrayList<>();
        JSONObject objAggregator = getDataAggregator(eventID, marketID);
        if (objAggregator.has("runners")) {
            JSONArray runnerArr = objAggregator.getJSONArray("runners");
            for (int i = 0; i < runnerArr.length(); i++) {
                JSONObject objRunner = runnerArr.getJSONObject(i);
                lstRunner.add(objRunner.getString("runnerName"));
            }
        }
        return lstRunner;
    }
}
