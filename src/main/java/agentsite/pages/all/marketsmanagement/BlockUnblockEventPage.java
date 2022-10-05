package agentsite.pages.all.marketsmanagement;

import com.paltech.element.common.*;
import com.paltech.utils.DateUtils;
import agentsite.common.AGConstant;
import agentsite.controls.DownlineControl;
import agentsite.controls.Table;
import membersite.objects.sat.Event;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.SkipException;
import agentsite.pages.all.components.LeftMenu;
import agentsite.pages.all.marketsmanagement.blockunblockevents.UnblockSchedulePopup;
import agentsite.ultils.account.ProfileUtils;
import agentsite.ultils.maketmanagement.BlockUnblockEventsUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static agentsite.common.AGConstant.MarketsManagement.BlockUnblockEvent.*;

public class BlockUnblockEventPage extends LeftMenu {

    public enum Actions {BLOCK, UNBLOCK_NOW, UNBLOCK_SCHEDULE, SUSPEND, UNSUSPEND}
    private int totalColEvent = 7;
    public DownlineControl dlcDownline = DownlineControl.xpath("//ul[@class='tree-container downline-list']//li");
    //public IFrame iFrame = IFrame.xpath("//iframe[@class='fixed-right-content']");
    public Table tblEvent = Table.xpath("//div[@class='main-container']//div[@class='col-right']/table", totalColEvent);
    public Table tblDownline = Table.xpath("//app-block-unblock-events//div[contains(@class,'col-left')]//table", 1);
    public TextBox txtSearchByUsernameLoginID = TextBox.xpath("//input[@placeholder='Search By Username/Nickname']");
    public TextBox txtSearchByEventIDName = TextBox.xpath("//input[@placeholder='Search By Event ID/Name']");
    public DropDownBox ddbSADList = DropDownBox.xpath("//label[contains(@class,'sad-list')]//parent::td/following-sibling::td/select");
    public Label lblSADList = Label.xpath("//label[contains(@class,'sad-list')]");
    public DropDownBox ddbSport = DropDownBox.xpath("//label[@translate='sport']//parent::td//following::td[1]/select");
    public CheckBox chkSelectAll = CheckBox.xpath("//li[@class='select-all-user']//i");
    public CheckBox chkEventAll = CheckBox.xpath("//div[@class='title-event']//i");
    public Tab tabActive = Tab.xpath("//ul[@role='tablist']//li[@class='active']//a");
    public String btnActionDynamic = "//div[contains(@class,'result container-fluid')]/div[2]//button[text()='%s']";
    public String btnActionDynamicBottom = "//div[contains(@class,'result container-fluid')]/div[4]//button[text()='%s']";
    public String tabDynamic = "//a[contains(text(),'%s')]";
    public Label lblNoEvent = Label.xpath("//div[@class='main-container']//div[@class='col-right']/table//td[@align='center']");
    public Button btnRefresh = Button.xpath("//span[contains(@class,'extension outstanding')]");
    public Label lblInfo = Label.xpath("//app-block-unblock-events//ul[@class='info']");
    // private String tblDownlinexPath = "//span[contains(text(),'%s')]";
    public String lblDonwlinexPath = "//table[contains(@class,'block-table')]//span[contains(text(),'%s')]";
    public String cbbDonwlinexPath = String.format("%s%s", lblDonwlinexPath, "/following::span[1]");
    public String cbSelectAllDownline = "//table[contains(@class,'block-table')]//span[contains(@class,'select-all')]/i";
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class,'la-ball-clip-rotate')]/div");
    private String yesIconxPath = "span[contains(@class,'psuccess')] ";
    private String noIconxPath = "span[contains(@class,'perror')]";
    private String lnkDetailXpath = "a[contains(@class,'detail-markets')]";
    private String suspendIconxPath = "span[contains(@class,'psuspend')]";
    public int colCompetition = 1;
    public int colEvent = 2;
    public int colStatusCurrent = 3;
    public int colStatusViewable = 4;
    public int colStatusBetable = 5;
    public int colTimeToOpen = 6;
    public int colTimeToBet = 7;
    public DropDownBox ddbSelectTime = DropDownBox.name("brandName");
    public Button btnSaveUnblockSchedule = Button.xpath("//button[@class='pbtn margin-right btn-width']");
    public Button btnCancelUnblockSchedule = Button.xpath("//button[@class='pbtn margin-right btn-width close-btn']");


    private Button defineButton(Actions action, int index) {
        switch (action) {
            case BLOCK:
                return Button.xpath(String.format(btnActionDynamic, AGConstant.MarketsManagement.BlockUnblockEvent.BTN_ACTIONS.get(0), index));
            case UNBLOCK_NOW:
                return Button.xpath(String.format(btnActionDynamic, AGConstant.MarketsManagement.BlockUnblockEvent.BTN_ACTIONS.get(1), index));
            case UNBLOCK_SCHEDULE:
                return Button.xpath(String.format(btnActionDynamic, AGConstant.MarketsManagement.BlockUnblockEvent.BTN_ACTIONS.get(2), index));
            case SUSPEND:
                return Button.xpath(String.format(btnActionDynamic, AGConstant.MarketsManagement.BlockUnblockEvent.BTN_ACTIONS.get(3), index));
            case UNSUSPEND:
                return Button.xpath(String.format(btnActionDynamic, AGConstant.MarketsManagement.BlockUnblockEvent.BTN_ACTIONS.get(4), index));
        }
        return null;
    }

    public boolean isButtonDisplay(Actions action, int index) {
        return defineButton(action, index).isDisplayed();
    }

    public boolean isPTLevelDisplay(String levelPT) {
        String convertLevelByBrand;
        if (!levelPT.equalsIgnoreCase("PO")) {
            convertLevelByBrand = ProfileUtils.convertDownlineByBrand(levelPT, ProfileUtils.getAppName());
            if (!lblSADList.isDisplayed()) {
                System.out.println("FAILED! Control Blocking label level not display");
                return false;
            }
            if (!ddbSADList.isDisplayed()) {
                System.out.println("FAILED! Control Blocking dropdown level not display");
                return false;
            }
            if (!lblSADList.getText().trim().equals(String.format("%s list", convertLevelByBrand))) {
                System.out.println("FAILED! Control Blocking level is incorrect displayed");
                return false;
            }
        } else {
            if (lblSADList.isDisplayed()) {
                System.out.println("FAILED! Control Blocking label level not display");
                return false;
            }
            if (ddbSADList.isDisplayed()) {
                System.out.println("FAILED! Control Blocking dropdown level not display");
                return false;
            }

        }
        return true;

    }

    /**
     * To click on the list actions buttons [Block, Unblock Now, Unblock Schedule, Suspend, Unsuspend]
     *
     * @param action the specific action
     * @param index  the index of the button, default value is 1
     * @return
     */
    public Object clickActions(Actions action, int index) {
        Button btnAction = defineButton(action, index);
        btnAction.click();
        switch (action) {
            case UNBLOCK_SCHEDULE:
                return new UnblockSchedulePopup();
            default:
                return null;
        }
    }

    public List<Event> getEventAvailable(String levelPT, String downline, String loginID,String controlBlocking, String sport,String status)
    {
        // left controlBlocking if login ID not PO level
        List<Event> event = new ArrayList<Event>();
        int n = TAB_DAYS.size();
        for( int i= 0; i < n; i++) {
            // get event in Today tab first, and Old Event Tab last
            if(i == n-1)
                i=-1;
            filter(controlBlocking, sport, TAB_DAYS.get(i+1));

            // get expected downline login ID
            String childID = BlockUnblockEventsUtils.getchildUserID(BlockUnblockEventsUtils.getAllChildPO(levelPT, loginID), downline);

            // get event list
            event = BlockUnblockEventsUtils.getEventList(sport, childID, TABs.get(TAB_DAYS.get(i+1)), status);
            if (!Objects.isNull(event.get(0).getCompetitionName())) {
                break;
            }
        }
        if (Objects.isNull(event)) {
            throw new SkipException(String .format("INFO: Skipping this test case as have no Competition in today for %s",sport));
        }
        return event;
    }

    public List<Event> getEventAvailable(String userID,String downline,String controlBlocking, String sport,String status)
    {
        // left controlBlocking if login ID not PO level
        List<Event> event = new ArrayList<Event>();
        int n = TAB_DAYS.size();
        for( int i= 0; i < n; i++) {
            // get event in Today tab first, and Old Event Tab last
            if(i == n-1)
                i=-1;
            filter(controlBlocking, sport, TAB_DAYS.get(i+1));

            // get expected downline login ID
            String childID = BlockUnblockEventsUtils.getchildUserID(userID, downline);

            // get event list
            event = BlockUnblockEventsUtils.getEventList(sport, childID, TABs.get(TAB_DAYS.get(i+1)), status);
            if (!Objects.isNull(event.get(0).getCompetitionName())) {
                break;
            }
        }
        if (Objects.isNull(event)) {
            throw new SkipException(String.format("INFO: Skipping this test case as have no Competition in today for %s", sport));
        }
        return event;
    }

    public String getActiveTab() {
        return tabActive.getText().trim();
    }

    public boolean isTabDisplay(String tab) {
        return defineTab(tab).isDisplayed();
    }

    private Tab defineTab(String tab) {
        switch (tab) {
            case "Today":
                return Tab.xpath(String.format(tabDynamic, TAB_DAYS.get(1)));
            case "Tomorrow":
                return Tab.xpath(String.format(tabDynamic, TAB_DAYS.get(2)));
            case "Future":
                return Tab.xpath(String.format(tabDynamic, TAB_DAYS.get(3)));
            default:
                return Tab.xpath(String.format(tabDynamic, TAB_DAYS.get(0)));
        }
    }

    /**
     * Click on tab
     *
     * @param action Tab name
     */
    public void clickTab(String action) {
        defineTab(action).click();
        waitingLoadingSpinner();
    }

    public void selectEvent(String event)
    {
        if(!event.isEmpty()){
            if(event.equalsIgnoreCase("all"))
            {
                chkEventAll.click();
                waitingLoadingSpinner();
                return;
            }
            List<String> lstEvent = tblEvent.getColumn(colEvent,true);
            CheckBox chk;
            boolean flag = false;
            for (int i = 0; i< lstEvent.size(); i++)
            {
                if(lstEvent.get(i).contains(event))
                {
                    chk = CheckBox.xpath(tblEvent.getxPathOfCell(1,colEvent,i+1,"span[contains(@class,'square-icon')]/i"));
                    chk.click();
                    flag = true;
                    break;
                }
            }
            if(!flag){
                System.out.println(String.format("There is NO event %s display in the list",event));
            }
        }
    }

    public void blockUnblockEvent(String downline, String event, String action){
        blockUnblockEvent(downline,event,action,"",1);
    }

    /**
     * Do action Block or Un-block an event
     * @param downline It is direct downline under login account, can input a username or login ID or all to select all driect downlines
     * @param event the even name, can input all to select all event
     * @param action input action: block, Unblock Now, Unblock Schedule, Suspend, Unsuspend
     * @param unblockScheduleTime in Action is Unblock Schedule, input the schedule value like: 25 minute, 2 days...
     * @param btnIndex 1 is use the button in the top, and 2 is use button in the bottom
     */
    public void blockUnblockEvent(String downline, String event, String action, String unblockScheduleTime, int btnIndex){
        //1. Select downline
        if(!downline.isEmpty())
            selectDownline(downline);

        //2. Select event
        selectEvent(event);

        //3 Click buttons Block|Unblock Now| Unblock Schedule | Suspend | Unsuspend
        if (!action.isEmpty()){
            Button button = Button.xpath(String.format(btnActionDynamic,action,btnIndex));
            button.isClickable(timeOutShortInSeconds);
            button.click();
            if(!unblockScheduleTime.equals(""))
            {
                ddbSelectTime.selectByVisibleText(unblockScheduleTime);
                btnSaveUnblockSchedule.click();
            }
            waitingLoadingSpinner();
        }
    }

    public boolean isButtonEnable(String actionButton, int index)
    {
        Button button = null;
        if(index ==1)
            button = Button.xpath(String.format(btnActionDynamic,actionButton));
        if(index ==2)
            button = Button.xpath(String.format(btnActionDynamicBottom,actionButton));
        return button.isEnabled();
    }

    public void filter(String controlBlocking, String sport, String todayTab)
    {
        // 1. Select control level blocking
        if(!ddbSport.isDisplayed())
        {
            ddbSport.waitForElementToBePresent(ddbSport.getLocator());
        }
        if(!controlBlocking.isEmpty()) {
            ddbSADList.selectByVisibleContainsText(controlBlocking);
            waitingLoadingSpinner();
        }
        //2. Select sport
        if(!sport.isEmpty()){
            waitingLoadingSpinner();
            ddbSport.selectByVisibleText(sport);
            waitingLoadingSpinner();
        }

        //3. Click on Tab (Ole Event, Today, Tomorrow, Future)
        if (!todayTab.isEmpty()){
            clickTab(todayTab);
        }
    }

    public void searchDownline(String downline)
    {
        if(!downline.isEmpty()){
            txtSearchByUsernameLoginID.sendKeys(downline);
        }
    }

    public void selectDownline(String downline)
    {
        selectDownline(downline,true);
    }
    public void selectDownline(String downline, boolean isClick)
    {
        if(isClick){
            clickDownline(downline);
        }
       // waitingLoadingSpinner();
        checkDownline(downline);
        waitingLoadingSpinner();
    }

    public void clickDownline(String downline){
        if(!downline.equalsIgnoreCase("all")) {
            Label lblDownlineName = Label.xpath(String.format("//table[contains(@class,'block-table')]//span[contains(text(),'%s')]",downline));
         //   lblDownlineName = Label.xpath(String.format("//table[contains(@class,'block-table')]//span[contains(text(),'%s')]",downline));
            lblDownlineName.isClickable(3);
            lblDownlineName.click();
            waitingLoadingSpinner();
        }
    }

    public void checkDownline(String downline){
        CheckBox checkbox;
        if(downline.equalsIgnoreCase("all")){
            checkbox =CheckBox.xpath(cbSelectAllDownline);
        }else
            checkbox =  CheckBox.xpath(String.format("//table[contains(@class,'block-table')]//span[contains(text(),'%s')]/following::span[1]",downline));

        checkbox.click();
        waitingLoadingSpinner();
    }
    public void verifyBlockUnblockEvent(String event,String currentStatus, boolean isViewable, boolean isBetable, String timeToOpen, String timeToBet)
    {
        verifyBlockUnblockEvent(event,currentStatus,isViewable,false,isBetable,timeToOpen,timeToBet);
    }
    public void verifyBlockUnblockEvent(String event,String currentStatus)
    {
        List<String> lstEvent = tblEvent.getColumn(colEvent,true);
        for (int i = 0; i< lstEvent.size(); i++) {
            if (lstEvent.get(i).contains(event)) {
                String _currentStatus = tblEvent.getControlOfCell(1, colStatusCurrent, i + 1, "span").getText();
                Assert.assertEquals(_currentStatus, currentStatus, String.format("FAILED! Status should be %s but display %s", currentStatus, _currentStatus));
            }
        }
    }

    public void verifyBlockUnblockEvent(String event,String currentStatus, boolean isViewable, boolean isSuspend, boolean isBetable, String timeToOpen, String timeToBet)
    {
        List<String> lstEvent = tblEvent.getColumn(colEvent,true);
        String viewableIconExpected ="";
        if(!isSuspend)
            viewableIconExpected = isViewable ? yesIconxPath : noIconxPath;
        else
            viewableIconExpected = suspendIconxPath;
        String betableIconExpected = isBetable?lnkDetailXpath:noIconxPath;
        for (int i = 0; i< lstEvent.size(); i++)
        {
            if(lstEvent.get(i).contains(event))
            {
                String _currentStatus = tblEvent.getControlOfCell(1, colStatusCurrent, i+1, "span").getText();
                Assert.assertEquals(_currentStatus,currentStatus,String.format("FAILED! Status should be %s but display %s",currentStatus,_currentStatus));
                Assert.assertTrue(tblEvent.getControlOfCell(1, colStatusViewable, i+1, viewableIconExpected).isDisplayed(),"FAILED! Viewable status not display as expected");
                Assert.assertTrue(tblEvent.getControlOfCell(1, colStatusBetable, i+1, betableIconExpected).isDisplayed(),"FAILED! Bet status not display as expected");
                Assert.assertEquals(tblEvent.getControlOfCell(1, colTimeToOpen, i+1, null).getText(),timeToOpen,"Failed! Time to open not display as expected");
                Assert.assertEquals(tblEvent.getControlOfCell(1, colTimeToBet,i+1, null).getText(),timeToBet,"Failed! Time to bet not display as expected");
                return;
            }
        }
    }

    private int timeToOpenConvert(String unblockSchedule)
    {
        //return the minute based on the
        switch (unblockSchedule){
            case "25 minutes":
                return 25;
            case "24 hours":
               return 1440;
            case "2 days":
               return 2880;
            case "3 days":
               return 4320;
            case "4 days":
                return 5760;
            case "5 days":
                return 7200;
            case "6 days":
                return 8640;
            case "7 days":
                return 10080;
            case"Now":
                return 0;
        }
        return -1;
    }

    public void verifyUnblockSchedule(Event event, String schedule)
    {
        List<String> lstEvent = tblEvent.getColumn(colEvent,true);
        Date date1 = DateUtils.convertToDate(DateUtils.convertMillisToDateTime(event.getStartTime(),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
        Date date2 = DateUtils.convertToDate(DateUtils.convertMillisToDateTime(Long.toString(DateUtils.getMilliSeconds()),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
        long timeDiff = DateUtils.getDateDiff(date2,date1, TimeUnit.MINUTES);
        String expectedStatus="Unblocked";
        String viewableIconExpected = yesIconxPath;
        String betableIconExpected =  lnkDetailXpath;
        String timeToOpenExpected = schedule;

        if(timeDiff > timeToOpenConvert(schedule))
        {
            expectedStatus="Blocked";
            viewableIconExpected = noIconxPath;
            betableIconExpected = noIconxPath;
        }

        for (int i = 0; i< lstEvent.size(); i++)
        {
            if(lstEvent.get(i).contains(event.getEventName()))
            {
                String _currentStatus = tblEvent.getControlOfCell(1, colStatusCurrent, i+1, "span").getText();
                Assert.assertEquals(_currentStatus,expectedStatus,String.format("FAILED! Status should be %s but display %s",expectedStatus,_currentStatus));
                Assert.assertTrue(tblEvent.getControlOfCell(1, colStatusViewable, i+1, viewableIconExpected).isDisplayed(),"FAILED! Viewable status not display as expected");
                Assert.assertTrue(tblEvent.getControlOfCell(1, colStatusBetable, i+1, betableIconExpected).isDisplayed(),"FAILED! Bet status not display as expected");
                Assert.assertEquals(tblEvent.getControlOfCell(1, colTimeToOpen, i+1, null).getText(),timeToOpenExpected,"Failed! Time to open not display as expected");
                Assert.assertEquals(tblEvent.getControlOfCell(1, colTimeToBet,i+1, null).getText(),TIME_TO_BET,"Failed! Time to bet not display as expected");
                return;
            }
        }
    }
    public boolean isCompetitionExist(List<Event> events, String competition)
    {
        if(!Objects.isNull(events))
        {
            for (Event e : events) {
               if(e.getCompetitionName().trim().equalsIgnoreCase(competition))
                   return true;
            }
        }
        return false;
    }

    public boolean validateBlockStatusAllEventViaAPI(List<ArrayList<String>> lsEvent, String schedule){
        Date date2 = DateUtils.convertToDate(DateUtils.convertMillisToDateTime(Long.toString(DateUtils.getMilliSeconds()),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
        // default status when events are blocked:
        String timeTotBet = "-1";
        String unblockBeforeStart = "-1";
        String viewable = "0";
        if(schedule.contains("Now")){
             timeTotBet = "0";
             unblockBeforeStart = "0";
             viewable = "1" ;
        }
        for(int i = 0;i <lsEvent.size(); i++){
            Date date1 = DateUtils.convertToDate(DateUtils.convertMillisToDateTime(lsEvent.get(i).get(13),"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
            long timeDiff = DateUtils.getDateDiff(date1,date2, TimeUnit.MINUTES);
            if(!schedule.contains("Now") && timeToOpenConvert(schedule) > 0 ){
                timeTotBet = "25";
                unblockBeforeStart = Integer.toString(timeToOpenConvert(schedule));
                if(timeDiff > timeToOpenConvert(schedule)){
                    viewable = "1" ;
                }
                else
                    viewable ="0";

            }
            if(!timeTotBet.equalsIgnoreCase(lsEvent.get(i).get(14)))
            {
                System.err.println(String.format("FAILED! Time to bet of event name %s with the schedule %s is %s but found %s",lsEvent.get(i).get(12), schedule,timeTotBet,lsEvent.get(i).get(14)));
                return false;
            }
            if(!unblockBeforeStart.equalsIgnoreCase(lsEvent.get(i).get(15)))
            {
                System.err.println(String.format("FAILED! Unblock Before stat duration time of event name %s with the schedule %s is %s but found %s",lsEvent.get(i).get(12), schedule,unblockBeforeStart,lsEvent.get(i).get(14)));
                return false;
            }
            if(!viewable.equalsIgnoreCase(lsEvent.get(i).get(18)))
            {
                System.err.println(String.format("FAILED! Viewable of event name %s with the schedule %s is %s but found %s. Viewable = 1 means unblocked and display on member site",lsEvent.get(i).get(12), schedule,viewable,lsEvent.get(i).get(14)));
                return false;
            }
        }
        return true;
    }

    public void searchEvent(String eventNameorID)
    {
        txtSearchByEventIDName.sendKeys(eventNameorID);
        txtSearchByEventIDName.type(false,Keys.ENTER);
    }
}
