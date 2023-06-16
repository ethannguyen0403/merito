package agentsite.pages.report;

import agentsite.controls.Table;
import agentsite.pages.HomePage;

import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.*;

import java.util.List;

public class ReportConfigurationPage extends HomePage {
    public Button btnAddNew = Button.xpath("//button[contains(@ng-click,'configuration')]");
    public int tblReportTotalCoumn = 4;
    public int colReportName = 2;
    public int colAgentName = 3;
    public int colAction = 4;
    public Table tblReport = Table.xpath("//table[contains(@class,'ptable report')]",tblReportTotalCoumn);
    public Icon icExpandPO = Icon.xpath("");
    public TextBox txtGroupName= TextBox.xpath("//input[contains(@class,'group-name')]");
    public Button btnSubmit= Button.xpath("//button[contains(@class,'ok')]");
    public ReportConfigurationPage(String types){
        super(types);
    }
    public void addReport(String groupName, List<String> downlineListing)
    {
        btnAddNew.click();
        txtGroupName.isInvisible(1);
        txtGroupName.sendKeys(groupName);
        selectDownline(downlineListing);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public void editReport(String groupName,String newGroupName)
    {
        // Click on Edit of old groupName
        Link lnk = Link.xpath(tblReport.getControlxPathBasedValueOfDifferentColumnOnRow(groupName,1,colReportName,1,null,colAction,"span[contains(@ng-click,'edit')]",false,false));
        lnk.click();
        waitingLoadingSpinner();
        if(!newGroupName.isEmpty())
            txtGroupName.sendKeys(newGroupName);
        btnSubmit.click();
        waitingLoadingSpinner();
    }
    public String deleteReport(String groupName, boolean isConfirmDeleted)
    {
        // Click on Delete of old groupName
        Link lnk = Link.xpath(tblReport.getControlxPathBasedValueOfDifferentColumnOnRow(groupName,1,colReportName,1,null,colAction,"span[contains(@ng-click,'deleteConfig')]",false,false));
        lnk.click();
        ConfirmPopup confirmPopup = new ConfirmPopup();
        confirmPopup.isPopupDisplay();
        String confirmMessage = confirmPopup.getContentMessage();
        if(isConfirmDeleted)
            confirmPopup.confirm();
        waitingLoadingSpinner();
        return confirmMessage;
    }
    public void selectDownline(List<String> downline)
    {
        // Click expand Portal node +
        Icon iconPortal = Icon.xpath("//tree[@branch='vm.data']/ul/li/a/div/span[contains(@ng-show,'branch.isExpanded')][1]");
        iconPortal.click();
        String expandChildXpath ="/span[contains(@ng-show,'branch.isExpanded')][1]";
        String cbChildXpath ="/span[contains(@ng-show,'isShowCheckIcon')][1]";
        String rootXpath = "//tree[@branch='vm.data']/ul/li/a/div";
        String childLevelXpath = "/div/ul/li[%s]/tree[@branch='child']/ul/li/a/div";
        String xPath;
        String temp;
        int j = 1;
        for(int i = 0; i<downline.size(); i++)
        {
            Label lbl;
            // The last node click on the checkbox to include the account
            while(true)
            {
                // get text of the CO list, if match with the input will click on expand
                xPath = String.format(childLevelXpath,j);
                lbl = Label.xpath(String.format("%s%s",rootXpath,xPath));
                lbl.scrollDownInDistance();
                if(!lbl.isDisplayed())
                {
                    System.err.println(String.format("Error: the child not exist after expand node %s",downline.get(i)));
                    return;
                }
                // if the child node contain the child in the list
                temp = String.format("%s%s",rootXpath, String.format(xPath,j));
                j = j+1;
                if(lbl.getText().contains(downline.get(i)))
                {
                    // Click on Checkbox to include the account into Report grouping if it is the last node
                    if(i == downline.size()-1){
                        lbl = Label.xpath(String.format("%s%s",temp,cbChildXpath));
                        lbl.click();
                        return;
                    }
                    // Expand the node if it is NOT the last node
                    lbl = Label.xpath(String.format("%s%s",temp,expandChildXpath));
                    lbl.click();
                    childLevelXpath = String.format("%s%s",xPath,childLevelXpath);
                    j = 1;
                    break;
                }
            }

        }
    }

}
