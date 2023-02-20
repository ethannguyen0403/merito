package membersite.pages.components.mybet;

import java.util.ArrayList;
import java.util.List;

public class MyBetsContainer {
    public boolean verifyWagerDisplayInReport(String wagerID){

        return false;
    }


    public void filter(String productName, String orderType){
        filter(productName, orderType,"", "");
    }

    public void filter(String productName, String orderType, String startDate, String endDate)
    {
    }

    public boolean validateFilterStatus(String status)
    {
        return true;
    }

    public List<String> translateProductsActive(List<String> productCode)
    {return  null;
    }

    public void waitLoadReport(){}

    public void selectProduct(String product){}

    public String getNote(){
        return "";
    }
    public String getProduct(){
        return "";
    }
    public String getOrderType(){
        return "";
    }
    public String getStartDate(){
        return "";
    }
    public String getEndDate(){
        return  "";
    }

    public String getLoadReport(){
        return "";
    }

    public  List<String> getTableHeaders (){return null;}

    public List<ArrayList<String>> getReportIndex(int index, boolean isMove){
        return null;
    }

    public String getNoRecord(){return "";}


}
