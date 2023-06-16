package agentsite.pages.marketsmanagement;

import common.AGConstant;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.marketsmanagement.currentblocking.BlockedUserPopup;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.Tab;

import java.util.ArrayList;
import java.util.List;

public class CurrentBlockingPage extends HomePage {
    public Label lblType = Label.xpath("//label[@translate='Type']");
    public Label lblSport = Label.xpath("//label[@translate='sport']");
    public DropDownBox ddbType = DropDownBox.xpath("//label[@translate='Type']//parent::td//following::select[1]");
    public DropDownBox ddbSport = DropDownBox.xpath("//label[@translate='sport']//parent::td//following::select");
    public Tab tabOldEvents = Tab.xpath(String.format("//a[contains(text(),'%s')]", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(0)));
    public Tab tabToday = Tab.xpath(String.format("//a[contains(text(),'%s')]", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(1)));
    public Tab tabTomorrow = Tab.xpath(String.format("//a[contains(text(),'%s')]", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(2)));
    public Tab tabFuture = Tab.xpath(String.format("//a[contains(text(),'%s')]", AGConstant.MarketsManagement.BlockUnblockEvent.TAB_DAYS.get(3)));
  //  public String tabDynamic = "//a[contains(text(),'%s')]";
    private int totalColEvent = 3;
    public int colCompetitionName = 1;
    public int colEventName = 2;
    public int colCurrent = 3;
    public int colCompetitionCurrent = 2;

    public Table tblEvent = Table.xpath("//div[@class='main-container']//table[contains(@class,'event-table')]",totalColEvent);
    private String lblNumberBlockingxPath = "span[@class='ribbon-text']";
    private String iconLockxPath = "//i[@class='fa fa-lock clock-icon']";
    public CurrentBlockingPage(String types) {
        super(types);
    }
    /**
     * Click on tab
     * @param action Tab name
     */
    public void clickTab(String action){
        switch (action){
            case "Old Events":
                tabOldEvents.click();
                return;
            case "Today":
                tabToday.click();
                return ;
            case "Tomorrow":
                tabTomorrow.click();
                return ;
            case "Future":
                tabFuture.click();
                return ;
        }
        waitingLoadingSpinner();
        return ;
    }

    public void filter(String type, String sport, String tabTime)
    {
        if(!type.isEmpty()) {
            ddbType.selectByVisibleText(type);
            waitingLoadingSpinner();
        }
        //2. Select sport
        if(!sport.isEmpty()){
            ddbSport.selectByVisibleText(sport);
            waitingLoadingSpinner();
        }
        //3. Click on Tab (Ole Event, Today, Tomorrow, Future)
        if (!tabTime.isEmpty()){
            clickTab(tabTime);
            waitingLoadingSpinner();
        }

    }

    public int getEventIndex(String event, boolean isEvent)
    {
        int row =0;
        List<String> lstEvent = new ArrayList<String>();
        if(isEvent)
            lstEvent = tblEvent.getColumn(colEventName,true);
        else
            lstEvent = tblEvent.getColumn(colCompetitionName,true);
        boolean flag = false;
        for (int i = 0; i< lstEvent.size(); i++)
        {
            if(lstEvent.get(i).contains(event))
            {
                flag = true;
                row = i+1;
               return row;
            }
        }
        if(!flag) {
            System.out.println("****There is no event display "+ event);
        }
        return row;
    }

    public int getEventIndex(String event)
    {
        return getEventIndex(event,true);
    }

    public BlockedUserPopup openBlockedUser(String event) {
        return openBlockedUser(event, true);
    }
    public BlockedUserPopup openBlockedUser(String name, boolean isEvent)
    {
        int row = getEventIndex(name,isEvent);
        clickCurrentCell(row, isEvent);
        return new BlockedUserPopup();
    }

    /***
     * Click on Current cell to open Blocked User popup
     * @param rowIndex
     * @param isEvent
     * @return
     */
    public BlockedUserPopup openBlockedUser(int rowIndex, boolean isEvent)
    {
        clickCurrentCell(rowIndex, isEvent);
        BlockedUserPopup popup = new BlockedUserPopup();
        return popup;
    }

    public BlockedUserPopup openBlockedUser(int rowindex) {
        return openBlockedUser(rowindex,true);
    }

    public String getBlockedUser(String event)
    {
        return getBlockedUser(event,true);
    }
    public String getBlockedUser(String event, boolean isEvent)
    {
        return getBlockedUser(event, isEvent,false);
    }

    public String getBlockedUser(String event, boolean isEvent, boolean isOpenBlockedUser)
    {
        int row = getEventIndex(event,isEvent);
        String blockedUserNumber ="";
        Link  lnkCurrent;
        if(isEvent)
            lnkCurrent = (Link) tblEvent.getControlOfCell(1,colCurrent,row,lblNumberBlockingxPath);
        else
            lnkCurrent = (Link) tblEvent.getControlOfCell(1,colCompetitionCurrent,row,lblNumberBlockingxPath);
        if(lnkCurrent.isPresent())
            blockedUserNumber = lnkCurrent.getText();
        if (isOpenBlockedUser)
            openBlockedUser(row,isEvent);
        return blockedUserNumber;
    }
    private void clickCurrentCell(int row, boolean isEvent)
    {
        if(isEvent)
            tblEvent.getControlOfCell(1, colCurrent, row,null).click();
        else
            tblEvent.getControlOfCell(1,colCompetitionCurrent,row,null).click();

    }
}
