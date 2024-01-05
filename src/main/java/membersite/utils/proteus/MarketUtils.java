package membersite.utils.proteus;

import baseTest.BaseCaseTest;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.DateUtils;
import com.paltech.utils.WSUtils;
import membersite.objects.proteus.ProteusMarket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.MemberConstants.GMT_MINUS_4_30;

public class MarketUtils extends BaseCaseTest {
//    public static JSONArray getEarlyListLeagueJSON() {
//        String date = DateUtils.getDate(1, "yyyy-MM-dd", GMT_MINUS_4_30);
//        String api = String.format("%s/proteus-member-service/before-login/odds/v3/events/odds-format/decimal/view-mode/ASIAN/sport-id/29/period-type/EARLY/country-ids/ALL/league-ids/ALL/period-id/-1/market-type/ALL/tz/-04%%3A00/from-date/%sT00%%3A00%%3A00/to-date/9999-01-01T00%%3A00%%3A00/sort-by/LEAGUE/page-no/1/page-size/3/locale/en-US?keySearch=", memberProteusServiceURL, date);
//        JSONObject jsonObject = WSUtils.getGETJSONObjectWithCookies(api, Configs.HEADER_JSON, DriverManager.getDriver().getCookies().toString());
//        if(Objects.nonNull(jsonObject)) {
//            JSONArray arrData = jsonObject.getJSONArray("data");
//            JSONArray arrData2 = arrData.getJSONArray(arrData.length()-1).getJSONArray(0);
//            JSONArray arrData3 = arrData2.getJSONArray(arrData2.length()-1);
//            for (int i = 0; i < arrData3.length(); i++) {
//                String periodTime = arrData3.getJSONArray(i).get(0).toString();
//                if(periodTime.equalsIgnoreCase("EARLY")) {
//                    return arrData3.getJSONArray(i);
//                }
//            }
//        } else {
//            System.out.println("Cannot get JSONObject of events from PS38");
//            return null;
//        }
//        return null;
//    }

//    public static ArrayList<JSONArray> getEarlyMoneylineInfo() {
//        JSONArray jsonArray = getEarlyListLeagueJSON().getJSONArray(1).getJSONArray(0);
//        JSONArray lstInfo2 = jsonArray.getJSONArray(2).getJSONArray(0).getJSONArray(5);
//        JSONArray lstInfo3 = lstInfo2.getJSONArray(0).getJSONArray(5);
//        ArrayList<JSONArray> lstMarkets = new ArrayList<>();
//        for (int i = 0; i < lstInfo3.length(); i++) {
//            String marketType = lstInfo3.getJSONArray(i).get(4).toString();
//            if(marketType.equalsIgnoreCase("MONEYLINE")) {
//                lstMarkets.add(lstInfo3.getJSONArray(i));
//            }
//        }
//        return lstMarkets;
//    }
//
//    public static ArrayList<String> getEarlyEventInfo() {
//        JSONArray jsonArray = getEarlyListLeagueJSON().getJSONArray(1).getJSONArray(0);
//        JSONArray lstInfo2 = jsonArray.getJSONArray(2).getJSONArray(0);
//        String league = jsonArray.getString(1);
//        String homeTeam = lstInfo2.getString(2);
//        String awayTeam = lstInfo2.getString(3);
//        ArrayList lst = new ArrayList<>();
//        lst.add(league);
//        lst.add(homeTeam);
//        lst.add(awayTeam);
//        return lst;
//    }

//    public static ArrayList<JSONArray> getEarlyMoneylineFullTimeInfo() {
//        ArrayList<JSONArray> jsonArray = getEarlyMoneylineInfo();
//        if(Objects.nonNull(jsonArray)) {
//            ArrayList lst = new ArrayList<>();
//            lst.add(jsonArray.get(1).get(12));
//            return lst;
//        } else {
//            System.out.println("Cannot find out MONEYLINE market is list of market types");
//            return null;
//        }
//    }
//
//    public static ArrayList<List> getEventMoneylineInfoProteus() {
//        ArrayList<JSONArray> lstOdds = getEarlyMoneylineFullTimeInfo();
//        JSONArray array = lstOdds.get(0);
//        ArrayList<Double> lstOddsReturn = new ArrayList<>();
//        ArrayList<List> lstInfo = new ArrayList<>();
//        for (int i = 0; i < array.length(); i++) {
//            //Odds order HOME/DRAW/AWAY
//            double oddsValue = array.getJSONArray(i).getDouble(0);
//            lstOddsReturn.add(oddsValue);
//        }
//        ArrayList<String> lstMarket = getEarlyEventInfo();
//        lstInfo.add(lstMarket);
//        lstInfo.add(lstOddsReturn);
//        return lstInfo;
//    }

    public static ProteusMarket getMarketInfo(int eventId, String betType, double hdpPoint) {
        JSONObject jsonObject = getMarketJSON(eventId, betType);
        if(Objects.nonNull(jsonObject)) {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            ProteusMarket proteusMarket = new ProteusMarket.Builder().build();
            proteusMarket.setEventId(eventId);
            proteusMarket.setBetType(betType);
            if(betType.equalsIgnoreCase("moneyline")) {
                //get FULL-TIME MONEYLINE info
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                double maxBet = jsonObject1.getDouble("max");
                String oddsFormat = jsonObject1.getString("oddsFormat");
                if(jsonObject1.getInt("altLineId") == -1) {
                    JSONArray jsonArray1 = jsonObject1.getJSONArray("odds");
                    proteusMarket.setMaxBet(maxBet);
                    proteusMarket.setOddsFormat(oddsFormat);
                    for (int j = 0; j < jsonArray1.length(); j++) {
                        JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                        //Order of odds is HOME/ DRAW/ AWAY
                        if(j == 0) {
                            proteusMarket.setFirstOdds(jsonObject2.getDouble("odds"));
                            proteusMarket.setFirstOriginalOdds(jsonObject2.getDouble("originalOdds"));
                            proteusMarket.setFirstSelectionName(jsonObject2.getString("team"));
                        } else if (j == 1) {
                            proteusMarket.setSecondOdds(jsonObject2.getDouble("odds"));
                            proteusMarket.setSecondOriginalOdds(jsonObject2.getDouble("originalOdds"));
                            proteusMarket.setSecondSelectionName(jsonObject2.getString("team"));
                        } else if (j == 2) {
                            proteusMarket.setThirdOdds(jsonObject2.getDouble("odds"));
                            proteusMarket.setThirdOriginalOdds(jsonObject2.getDouble("originalOdds"));
                            proteusMarket.setThirdSelectionName(jsonObject2.getString("team"));
                        }
                    }
                    return proteusMarket;
                }
            } else {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    double maxBet = jsonObject1.getDouble("max");
                    String oddsFormat = jsonObject1.getString("oddsFormat");
                    if(jsonObject1.getDouble("handicap") == hdpPoint) {
                        if(jsonObject1.getInt("altLineId") == -1) {
                            JSONArray jsonArray1 = jsonObject1.getJSONArray("odds");
                            proteusMarket.setEventId(eventId);
                            proteusMarket.setBetType(betType);
                            proteusMarket.setMaxBet(maxBet);
                            proteusMarket.setOddsFormat(oddsFormat);
                            for (int j = 0; j < jsonArray1.length(); j++) {
                                JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                                //Order of odds is HOME/AWAY for handicap and OVER/UNDER for overunder market
                                if(j == 0) {
                                    proteusMarket.setFirstOdds(jsonObject2.getDouble("odds"));
                                    proteusMarket.setFirstOriginalOdds(jsonObject2.getDouble("originalOdds"));
                                    proteusMarket.setFirstSelectionName(jsonObject2.getString("team"));
                                } else if (j == 1) {
                                    proteusMarket.setSecondOdds(jsonObject2.getDouble("odds"));
                                    proteusMarket.setSecondOriginalOdds(jsonObject2.getDouble("originalOdds"));
                                    proteusMarket.setSecondSelectionName(jsonObject2.getString("team"));
                                }
                            }
                            return proteusMarket;
                        }
                    }

                }
            }
        }
        return null;
    }

    public static JSONObject getMarketJSON(int eventId, String betType) {
        String url = "https://www.ps388win.com/proteus-member-service/odds/v3/decimal";
        String jsn = String.format("{\n" +
                        "    \"eventId\": [\n" +
                        "        %s\n" +
                        "    ],\n" +
                        "    \"betType\": \"%s\"\n" +
                        "}"
                , eventId, betType);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(url, Configs.HEADER_JSON, jsn,DriverManager.getDriver().getCookies().toString(),Configs.HEADER_JSON);
        return jsonObject;
    }
}
