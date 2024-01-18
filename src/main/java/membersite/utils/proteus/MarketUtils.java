package membersite.utils.proteus;

import baseTest.BaseCaseTest;
import com.paltech.constant.Configs;
import com.paltech.driver.DriverManager;
import com.paltech.utils.WSUtils;
import membersite.objects.proteus.ProteusMarket;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Objects;

public class MarketUtils extends BaseCaseTest {

    public static ProteusMarket getMarketInfo(int eventId, String betType, Double hdpPoint) {
        JSONObject jsonObject = getMarketJSON(eventId, betType);
        if (Objects.nonNull(jsonObject)) {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            ProteusMarket proteusMarket = new ProteusMarket.Builder().build();
            proteusMarket.setEventId(eventId);
            proteusMarket.setBetType(betType);
            if (betType.equalsIgnoreCase("moneyline")) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    //get FULL-TIME MONEYLINE info
                    if (jsonObject1.getInt("periodId") == 0) {
                        double maxBet = jsonObject1.getDouble("max");
                        String oddsFormat = jsonObject1.getString("oddsFormat");
                        JSONArray jsonArray1 = jsonObject1.getJSONArray("odds");
                        proteusMarket.setMaxBet(maxBet);
                        proteusMarket.setOddsFormat(oddsFormat);
                        for (int j = 0; j < jsonArray1.length(); j++) {
                            JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                            //Order of odds is HOME/ DRAW/ AWAY
                            if (j == 0) {
                                proteusMarket.setFirstOdds(jsonObject2.getDouble("odds"));
                                proteusMarket.setFirstOriginalOdds(jsonObject2.getDouble("originalOdds"));
                                proteusMarket.setFirstSelectionName(jsonObject2.getString("team"));
                                proteusMarket.setFirstHDPPoint(jsonObject2.getDouble("hdp"));
                            } else if (j == 1) {
                                proteusMarket.setSecondOdds(jsonObject2.getDouble("odds"));
                                proteusMarket.setSecondOriginalOdds(jsonObject2.getDouble("originalOdds"));
                                proteusMarket.setSecondSelectionName(jsonObject2.getString("team"));
                                proteusMarket.setSecondHDPPoint(jsonObject2.getDouble("hdp"));
                            } else if (j == 2) {
                                proteusMarket.setThirdOdds(jsonObject2.getDouble("odds"));
                                proteusMarket.setThirdOriginalOdds(jsonObject2.getDouble("originalOdds"));
                                proteusMarket.setThirdSelectionName(jsonObject2.getString("team"));
                                proteusMarket.setThirdHDPPoint(jsonObject2.getDouble("hdp"));
                            }
                        }
                        return proteusMarket;
                    }
                }
            } else {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    double maxBet = jsonObject1.getDouble("max");
                    String oddsFormat = jsonObject1.getString("oddsFormat");
                    if (jsonObject1.getDouble("handicap") == hdpPoint) {
                        if (jsonObject1.getInt("altLineId") == -1) {
                            JSONArray jsonArray1 = jsonObject1.getJSONArray("odds");
                            proteusMarket.setEventId(eventId);
                            proteusMarket.setBetType(betType);
                            proteusMarket.setMaxBet(maxBet);
                            proteusMarket.setOddsFormat(oddsFormat);
                            for (int j = 0; j < jsonArray1.length(); j++) {
                                JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                                //Order of odds is HOME/AWAY for handicap and OVER/UNDER for overunder market
                                if (j == 0) {
                                    proteusMarket.setFirstOdds(jsonObject2.getDouble("odds"));
                                    proteusMarket.setFirstOriginalOdds(jsonObject2.getDouble("originalOdds"));
                                    proteusMarket.setFirstSelectionName(jsonObject2.getString("team"));
                                    proteusMarket.setFirstHDPPoint(jsonObject2.getDouble("hdp"));
                                } else if (j == 1) {
                                    proteusMarket.setSecondOdds(jsonObject2.getDouble("odds"));
                                    proteusMarket.setSecondOriginalOdds(jsonObject2.getDouble("originalOdds"));
                                    proteusMarket.setSecondSelectionName(jsonObject2.getString("team"));
                                    proteusMarket.setSecondHDPPoint(jsonObject2.getDouble("hdp"));
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
//        String url = "https://www.ps388win.com/proteus-member-service/odds/v3/decimal";
        String url = "https://prostg.beatus88.com/proteus-member-service/odds/v3/decimal";
        String jsn = String.format("{\n" +
                        "    \"eventId\": [\n" +
                        "        %s\n" +
                        "    ],\n" +
                        "    \"betType\": \"%s\"\n" +
                        "}"
                , eventId, betType);
        JSONObject jsonObject = WSUtils.getPOSTJSONObjectWithCookies(url, Configs.HEADER_JSON, jsn, DriverManager.getDriver().getCookies().toString(), Configs.HEADER_JSON);
        return jsonObject;
    }

    public static ProteusMarket getMatchTeamTotalMarketInfo(int eventId, String betType, Double hdpPoint, boolean isHome) {
        JSONObject jsonObject = getMarketJSON(eventId, betType);
        if (Objects.nonNull(jsonObject)) {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            ProteusMarket proteusMarket = new ProteusMarket.Builder().build();
            proteusMarket.setEventId(eventId);
            proteusMarket.setBetType(betType);
            if (betType.equalsIgnoreCase("team_total_points")) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    if (isHome) {
                        if (jsonObject1.getDouble("handicap") == hdpPoint && jsonObject1.getInt("periodId") == 0) {
                            //get HOME info
                            if (jsonObject1.getString("team").equalsIgnoreCase("HOME")) {
                                double maxBet = jsonObject1.getDouble("max");
                                String oddsFormat = jsonObject1.getString("oddsFormat");
                                JSONArray jsonArray1 = jsonObject1.getJSONArray("odds");
                                proteusMarket.setMaxBet(maxBet);
                                proteusMarket.setOddsFormat(oddsFormat);
                                for (int j = 0; j < jsonArray1.length(); j++) {
                                    JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                                    //Order of odds is HOME/ AWAY
                                    if (j == 0) {
                                        proteusMarket.setFirstOdds(jsonObject2.getDouble("odds"));
                                        proteusMarket.setFirstOriginalOdds(jsonObject2.getDouble("originalOdds"));
                                        proteusMarket.setFirstSelectionName(jsonObject2.getString("team"));
                                        proteusMarket.setFirstHDPPoint(jsonObject2.getDouble("hdp"));
                                    } else if (j == 1) {
                                        proteusMarket.setSecondOdds(jsonObject2.getDouble("odds"));
                                        proteusMarket.setSecondOriginalOdds(jsonObject2.getDouble("originalOdds"));
                                        proteusMarket.setSecondSelectionName(jsonObject2.getString("team"));
                                        proteusMarket.setSecondHDPPoint(jsonObject2.getDouble("hdp"));
                                    }
                                }
                                return proteusMarket;
                            }
                        }
                    } else {
                        if (jsonObject1.getDouble("handicap") == hdpPoint && jsonObject1.getInt("periodId") == 0) {
                            //get AWAY info
                            if (jsonObject1.getString("team").equalsIgnoreCase("AWAY")) {
                                double maxBet = jsonObject1.getDouble("max");
                                String oddsFormat = jsonObject1.getString("oddsFormat");
                                JSONArray jsonArray1 = jsonObject1.getJSONArray("odds");
                                proteusMarket.setMaxBet(maxBet);
                                proteusMarket.setOddsFormat(oddsFormat);
                                for (int j = 0; j < jsonArray1.length(); j++) {
                                    JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                                    //Order of odds is HOME/ AWAY
                                    if (j == 0) {
                                        proteusMarket.setFirstOdds(jsonObject2.getDouble("odds"));
                                        proteusMarket.setFirstOriginalOdds(jsonObject2.getDouble("originalOdds"));
                                        proteusMarket.setFirstSelectionName(jsonObject2.getString("team"));
                                        proteusMarket.setFirstHDPPoint(jsonObject2.getDouble("hdp"));
                                    } else if (j == 1) {
                                        proteusMarket.setSecondOdds(jsonObject2.getDouble("odds"));
                                        proteusMarket.setSecondOriginalOdds(jsonObject2.getDouble("originalOdds"));
                                        proteusMarket.setSecondSelectionName(jsonObject2.getString("team"));
                                        proteusMarket.setSecondHDPPoint(jsonObject2.getDouble("hdp"));
                                    }
                                }
                                return proteusMarket;
                            }
                        }
                    }
                }
            }
            return proteusMarket;
        }
        return null;
    }
}
