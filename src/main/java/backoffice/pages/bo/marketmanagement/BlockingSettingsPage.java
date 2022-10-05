package backoffice.pages.bo.marketmanagement;

import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;

import java.util.ArrayList;
import java.util.List;

public class BlockingSettingsPage extends HomePage {
    private int totalColumn = 7;
    public int colPOAccount = 1;
    public int colBrand= 2;
    public int colSport = 3;
    public int colMarketType = 4;
    public int colDefaultEvent = 5;
    public int colLevelControl = 6;
    public int colUnblockSchedule = 7;
    public Table tblBlockingSetting = Table.xpath("//app-blocking-setting//table[contains(@class,'my-table')]",totalColumn);

    public boolean verifyDataCorrect(List<ArrayList<String>> apiData){
        List<ArrayList<String>> uiData = tblBlockingSetting.getRowsWithoutHeader(apiData.size(),false);
        for(int i = 0; i< apiData.size(); i++){
            System.out.println(String.format("Verify data of PO account %s",apiData.get(i).get(colPOAccount-1)));
            if(!uiData.get(i).get(colPOAccount-1).equals(apiData.get(i).get(colPOAccount-1))){
                System.out.println(String.format("Expected PO Account is %s but found %s",apiData.get(i).get(colPOAccount-1),uiData.get(i).get(colPOAccount-1)));
                return false;
            }
            if(!uiData.get(i).get(colBrand - 1).equals(apiData.get(i).get(colBrand-1))){
                System.out.println(String.format("Expected Brand is \n %s but found \n %s",apiData.get(i).get(colBrand-1),uiData.get(i).get(colBrand-1)));
                return false;
            }
            if(!uiData.get(i).get(colSport -1).equals(apiData.get(i).get(colSport -1))){
                System.out.println(String.format("Expected Sport Column is %s but found %s",apiData.get(i).get(colSport-1),uiData.get(i).get(colSport-1)));
                return false;
        }
           /* if(!uiData.get(i).get(colMarketType -1).equals(apiData.get(i).get(colMarketType -1))){
                System.out.println(String.format("Expected Sport Column is %s but found %s",apiData.get(i).get(colMarketType-1),uiData.get(i).get(colMarketType-1)));
                return false;
            }*/
            String defaultEvent =apiData.get(i).get(colDefaultEvent -1).equals("false")?"All unblocked":"All blocked";
            if(!uiData.get(i).get(colDefaultEvent -1).equals(defaultEvent)){
                System.out.println(String.format("Expected Sport Column is %s but found %s",apiData.get(i).get(colDefaultEvent-1),uiData.get(i).get(colDefaultEvent-1)));
                return false;
            }
            if(!uiData.get(i).get(colLevelControl -1).equals(apiData.get(i).get(colLevelControl -1))){
                System.out.println(String.format("Expected Sport Column is %s but found %s",apiData.get(i).get(colLevelControl-1),uiData.get(i).get(colLevelControl-1)));
                return false;
            }
            String unblockSchedule =apiData.get(i).get(colUnblockSchedule -1).equals("-1")?"Yes":"No";
            if(!uiData.get(i).get(colUnblockSchedule -1 ).equals(unblockSchedule)){
                System.out.println(String.format("Expected Sport Column is %s but found %s",apiData.get(i).get(colUnblockSchedule-1),uiData.get(i).get(colUnblockSchedule-1)));
                return false;
            }
        }
        return true;
    }

}
