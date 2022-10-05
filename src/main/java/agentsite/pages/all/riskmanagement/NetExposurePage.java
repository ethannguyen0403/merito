package agentsite.pages.all.riskmanagement;

import com.paltech.element.common.*;

import agentsite.controls.NetExposure.EventTitleControl;
import agentsite.controls.NetExposure.SportTitleControl;
import agentsite.controls.Table;
import agentsite.pages.all.components.LeftMenu;

public class NetExposurePage extends LeftMenu {
   public Label lblTotalBook = Label.xpath("//div[@class='filter-condition']//label[@for='totalBookSelection']");
   public Label lblMyPT= Label.xpath("//div[@class='filter-condition']//label[@for='myPTSelection']");
   public Label lblInPlay= Label.xpath("//div[@class='filter-condition']//div[contains(@class,'d-inline-flex align-items-center')]/label[contains(@class,'form-check-label')]");
   public Label lblNoRecord = Label.xpath("//div[contains(@class,'no-record')]");
   public Label lblSportSelection = Label.xpath("//div[@class='filter-condition']//div[contains(@class,'sport-selection')][1]//span[contains(@class,'selection-label')]");
   public Label lblMultipleSportSelection = Label.xpath("//div[@class='filter-condition']//div[contains(@class,'sport-selection')][1]//span[contains(@class,'selection-label')]");
   public agentsite.controls.DropDownBox ddpSportSelection = agentsite.controls.DropDownBox.xpath("//div[@class='filter-condition']//div[contains(@class,'sport-selection')][1]//angular2-multiselect",
           "//div[contains(@class,'dropdown-list')]//div[@class='list-area']//ul[@class='lazyContainer']//label");
   public  agentsite.controls.DropDownBox ddpMultiSportSelection = agentsite.controls.DropDownBox.xpath("//div[@class='filter-condition']//div[contains(@class,'sport-selection')][2]//span[contains(@class,'selection-label')]",
           "//div[contains(@class,'dropdown-list')]//div[@class='list-area']//ul[@class='lazyContainer']//label");
   public Icon icRefresh = Icon.xpath("//div[@class='filter-condition']//i");
   public CheckBox togleInplay = CheckBox.xpath("//div[@class='filter-condition']//span[@class='slider round']");
   public Table tblResult = Table.xpath("//div[contains(@class,'tableFixHeader')]//table",11);

   public Label lblNetExposure = Label.xpath("//app-bet-list-net-exposure//div[contains(@class,'link-back-to-overview')]");
   public Label lblEventName = Label.xpath("//app-bet-list-net-exposure//div[contains(@class,'link-back-to-drill-down')]/span");
   public Table tblBetList = Table.xpath("//app-bet-list-net-exposure//table",21);

   private void selectOption(String option){
      String opt = option.toUpperCase();
      switch (opt){
         case "MY PT":
            lblMyPT.click();
            return;
         default:
            lblTotalBook.click();
            return;
      }
   }

   /**
    * This method use for filter data
    * @param option My PT/ Total Book
    * @param isInplay true/false
    * @param isNonMultiSelection true/false
    */
   public void filter(String option, boolean isInplay, boolean isNonMultiSelection ,String sport){
      // Select My PT or Total Book
      selectOption(option);
      // Select Inplay toggle button
      if(isInplay){
         if(!togleInplay.isSelected()){
            togleInplay.click();
         }
      }
      // select 2=3 Selection or multiple selection and sport accordingly
      if(isNonMultiSelection){
         lblSportSelection.click();
         if(!sport.isEmpty())
            ddpSportSelection.selectByVisibleText(sport,true);
      }
      else{
         lblMultipleSportSelection.click();
         if(!sport.isEmpty())
            ddpMultiSportSelection.selectByVisibleText(sport,true);
      }
      waitingLoadingSpinner();
   }

   private int getSportIndex(String sportName){
      String rowXPath = "//div[contains(@class,'tableFixHeader')]//table//tr";
      int i =0;
      while (true){
         if(!Label.xpath(String.format("%s[%s]",rowXPath,i+1)).isDisplayed()){
            return i;
         }
         SportTitleControl sportTitle  = SportTitleControl.xpath(String.format("%s[%s]//div[@class='sport-header']",rowXPath,i+1));
         if(sportTitle.isDisplayed()){
            if(sportTitle.getText().equalsIgnoreCase(sportName)){
               return i+1;
            }
         }
         i=i+1;
      }
   }

   private int getEventIndex(String sportName,String eventName){
      int sportIndex = getSportIndex(sportName);
      if(sportIndex==0){
         System.out.println("DEBUG: There is no sport "+sportName+" in the list");
         return 0;
      }
      String rowXPath = "//div[contains(@class,'tableFixHeader')]//table//tr";
      int i =sportIndex;
      boolean flag= true;
      while (true){
         if(!Label.xpath(String.format("%s[%s]",rowXPath,i+1)).isDisplayed()){
            if(flag){
               return sportIndex;
            }
            return i;
         }
         EventTitleControl eventTitle  = EventTitleControl.xpath(String.format("%s[%s]",rowXPath,i+1));
         String property = eventTitle.getAttribute("class");
         if(property.contains("header-title")){
            if(eventTitle.getEventName().equalsIgnoreCase(eventName)){
               return i+1;
            }
         }
         i=i+1;
      }

   }

   private int getMarketIndex(String sportName,String eventName,String marketName){
      int eventIndex = getEventIndex(sportName,eventName);
      String rowXPath = "//div[contains(@class,'tableFixHeader')]//table//tr";
      int i =eventIndex;
      boolean flag = true;
      while (true){
         if(!Label.xpath(String.format("%s[%s]",rowXPath,i+1)).isDisplayed()){
            if(flag)
               return eventIndex;
            return i;
         }
         EventTitleControl eventTitle  = EventTitleControl.xpath(String.format("%s[contains(@class,'market-detail')][%s]",rowXPath,i+1));
         String property = eventTitle.getAttribute("class");
         if(property.contains("header-title")){
            if(eventTitle.getEventName().equalsIgnoreCase(eventName)){
               return i+1;
            }
         }
         i=i+1;
      }

   }

   public void openBetList(String sport, String event, String market){
      int eventIndex = getEventIndex(sport,event);
      tblResult.getControlBasedValueOfDifferentColumnOnRow(market,1,1,eventIndex+1,null,2,"img[contains(@src,'Bet-List')]",true,false).click();
      waitingLoadingSpinner();

   }
   public void openWorkstation(String sport, String event, String market){
      int eventIndex = getEventIndex(sport,event);
      tblResult.getControlBasedValueOfDifferentColumnOnRow(market,1,1,eventIndex+1,null,2,"img[contains(@src,'workstation')]",true,false).click();
      waitingLoadingSpinner();

   }
   public void openDownline(String sport, String event, String market){
      int eventIndex = getEventIndex(sport,event);
      tblResult.getControlBasedValueOfDifferentColumnOnRow(market,1,1,eventIndex+1,null,2,"img[contains(@src,'downline')]",true,false).click();
      waitingLoadingSpinner();

   }


}
