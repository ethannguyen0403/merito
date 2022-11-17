package backoffice.testcases.marketmanagement;

import baseTest.BaseCaseMerito;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import backoffice.pages.bo._components.AlertMessageBox;
import backoffice.pages.bo.marketmanagement.BeforeLoginManagementPage;
import util.testraildemo.TestRails;

import java.util.List;

public class BeforeLoginManagementTest extends BaseCaseMerito {

    /**
     * @title: Validate sport is active/deactivate effect to member site before login page
     * @pre-condition:
     *           1. Log in BO
     * @steps: 1. Access Operations > Before Login Management
     *      2. Active/Deactive a sport
     * @expect: 1. Verify the sport display/not display in member site
     */
    @Test (groups = {"regression"})
    @Parameters({"username","password"})
    public void BO_Operations_Before_Login_Management_001(String username, String password) throws Exception {
        /*log("@title: Validate sport is active/deactivate effect to member site before login page");
        log("Step 1. Access Operations > Before Login Management");
        String sport = "Golf";
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Active a Sport");
        page.activeSport(sport,true);
        page.logout();

        log("Verify 1. Verify the sport display in before login when the sport is active");
        DriverManager.getDriver().get(environment.getSatDashboardURL());
        backofficeHomePage satbackofficeHomePage = new backofficeHomePage();
        satbackofficeHomePage.waitMenuLoading();
        List<String> lstSport = satbackofficeHomePage.getLeftMenuList();
        boolean isSportinLeftMenu = false;
        for(int i =0; i< lstSport.size(); i++)
        {
            if(lstSport.get(i).contains(sport)){
                isSportinLeftMenu = true;
             break;
            }
        }
        Assert.assertTrue(isSportinLeftMenu,"FAILED! Sport NOT display in before login when active");

        log("Step 3. Deactive a sport");
        Helper.loginBOIgnoreCaptcha(environment.getSosURL(),environment.getDashboardURL(),username,password,true);
       // DriverManager.getDriver().getToAvoidTimeOut(environment.getDashboardURL());
        //Helper.loginBOIgnoreCaptcha(environment.getSosURL(),environment.getDashboardURL(),username,password,true);
        page = backofficeHomePage.navigateBeforeLoginManagement();
        page.activeSport(sport,false);
        AlertMessageBox messageBox = new AlertMessageBox();
        String message = messageBox.getSuccessAlert();
        Assert.assertTrue(message.contains(String.format("The status of %s has been updated successfully.",sport)),"FAILED! The message alert is incorrect display after active sport");

        page.logout();
        log("Verify 2. Verify the sport not display in before login when the sport is Deactive");
        DriverManager.getDriver().get(environment.getSatDashboardURL());
        satbackofficeHomePage = new backofficeHomePage();
        satbackofficeHomePage.waitMenuLoading();
        lstSport = satbackofficeHomePage.getLeftMenuList();
        isSportinLeftMenu=false;
        for(int i =0; i< lstSport.size(); i++)
        {
            if(lstSport.get(i).contains(sport)){
                isSportinLeftMenu = true;
                break;
            }
        }
        Assert.assertFalse(isSportinLeftMenu,"FAILED! Sport display in before login when Inactive");
        log("INFO: Executed completely");*/
    }

    /**
     * @title: Validate active/deactivate market type of a sport will effect to member site before login page
     * @pre-condition:
     *           1. Log in BO
     * @steps: 1. Access Operations > Before Login Management
     *          2. Select a sport : Cricket
     *          3. Active and inactive a  market:  Tied Match
     * @expect: 1. Verify the market is active/inactive on before login member. In the left menu, select cricket and any competition, verify Tied Match market is display/disappear per setting
     */
    @Test (groups = {"regression"})
    @Parameters({"username","password"})
    public void BO_Operations_Before_Login_Management_002(String username, String password) throws Exception {
        log("@title: Validate sport is active/deactivate effect to member site before login page");
        log("Step 1. Access Operations > Before Login Management");
        String sport = "Cricket";
        String marketTye = "Tied Match";
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Select a sport : Cricket");
        log("Step 3. Active and inactive a  market:  Tied Match");
        page.activeSport(sport,true);
        page.searchMartyType(marketTye);
        page.activeMarket(marketTye,true);
        AlertMessageBox messageBox = new AlertMessageBox();
        String message = messageBox.getSuccessAlert();
        Assert.assertTrue(message.contains(String.format("The status of %s has been updated successfully.",marketTye)),"FAILED! The message alert is incorrect display after active sport");
      /*  page.logout();*/

/*
        log("Verify 1. Verify the market is active on before login member");
        DriverManager.getDriver().get(environment.getSatDashboardURL());
        backofficeHomePage satbackofficeHomePage = new backofficeHomePage();
        satbackofficeHomePage.waitMenuLoading();
        satbackofficeHomePage.clickMenu(sport);
        List<String> lstCompetitionLeftMenu = satbackofficeHomePage.getLeftMenuList();
        satbackofficeHomePage.clickMenu(lstCompetitionLeftMenu.get(lstCompetitionLeftMenu.size()-1));
        List<String> lstEvent = satbackofficeHomePage.getLeftMenuList();
        satbackofficeHomePage.clickMenu(lstEvent.get(lstEvent.size()-1));
        List<String> lstMarket = satbackofficeHomePage.getLeftMenuList();

        boolean lstMarketLeftMenu = false;
        for(int i =0; i< lstMarket.size(); i++)
        {
            if(lstMarket.get(i).contains(marketTye)){
                lstMarketLeftMenu = true;
                break;
            }
        }
        Assert.assertTrue(lstMarketLeftMenu,"FAILED! Market NOT display in before login when active");

        log("Step 4. inactive a  market: Tied Match");
        Helper.loginBOIgnoreCaptcha(environment.getSosURL(),environment.getDashboardURL(),username,password,true);*/
      //  page = backofficeHomePage.navigateBeforeLoginManagement();
        page.activeSport(sport,true);
        page.searchMartyType(marketTye);
        page.activeMarket(marketTye,false);
         messageBox = new AlertMessageBox();
         message = messageBox.getSuccessAlert();
        Assert.assertTrue(message.contains(String.format("The status of %s has been updated successfully.",marketTye)),"FAILED! The message alert is incorrect display after active sport");

        /*log("Verify 2. Verify the sport not display in before login when the sport is inactive");
        DriverManager.getDriver().get(environment.getSatDashboardURL());
        satbackofficeHomePage.waitMenuLoading();
        satbackofficeHomePage.clickMenu(sport);
       lstCompetitionLeftMenu = satbackofficeHomePage.getLeftMenuList();
        satbackofficeHomePage.clickMenu(lstCompetitionLeftMenu.get(lstCompetitionLeftMenu.size()-1));
        lstEvent = satbackofficeHomePage.getLeftMenuList();
        satbackofficeHomePage.clickMenu(lstEvent.get(lstEvent.size()-1));
        lstMarket = satbackofficeHomePage.getLeftMenuList();
        lstMarketLeftMenu=false;
        for(int i =0; i< lstMarket.size(); i++)
        {
            if(lstMarket.get(i).contains(sport)){
                lstMarketLeftMenu = true;
                break;
            }
        }
        Assert.assertFalse(lstMarketLeftMenu,"FAILED! Sport display in before login when Inactive");*/
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate can search market type
     * @pre-condition:
     *           1. Log in BO
     * @steps: 1. Access Operations > Before Login Management
     * 2. Select a sport : Cricket
     * 3. Input a market type and press enter : e.g: Fancy
     * @expect: 1. Verify the market list will contain all markets type contain search key */
    @TestRails(id = "632")
    @Test (groups = {"smoke"})
    public void BO_Operations_Before_Login_Management_003(){
        log("@title: Validate can search market type");
        log("Step 1. Access Operations > Before Login Management");
        String sport = "Cricket";
        String marketTye = "Fancy";
        BeforeLoginManagementPage page = backofficeHomePage.navigateBeforeLoginManagement();

        log("Step 2. Select a sport : Cricket");
        log("Step 3. Active and inactive a  market: Fancy");
        page.activeSport(sport,true);
        page.searchMartyType(marketTye);
        log("Step 1. Verify the market list will contain all markets type contain search key");
        List<String> lstMarket = page.tblMarketType.getColumn(page.colMarketType,false);
        Assert.assertTrue(lstMarket.contains("Fancy"),"The list market does not contains search key");

    }
}
