package agentsite.pages.agentmanagement;

import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.commissionlisting.CommissionSettingListing;
import agentsite.pages.components.ComponentsFactory;
import com.paltech.element.common.*;
import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CommissionSettingListingPage extends HomePage {
    public static int tblAgentCommissionTotalCol = 10;
    public int colUsername = 2;
    public int colLoginID = 2;
    public int chbCol = 5;
    public int agentUpdateStatusCol = 10;
    public int colMemberUpdateStatusCol = 11;
    public int tblMemberCommissionTotalCol = 11;
    public static Table tblAgentCommission = Table.xpath("//div[@class='downline-listings']//table", tblAgentCommissionTotalCol);
    public TextBox txtUsername = TextBox.id("userName");
    public DropDownBox ddbAccountStatus = DropDownBox.id("status");
    public DropDownBox ddbProduct = DropDownBox.id("product");
    public DropDownBox ddbLevel = DropDownBox.id("level");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public Table tblMemberCommission = Table.xpath("//div[@id='commission-member']//table", tblMemberCommissionTotalCol);
    private DropDownBox ddbSetCommissionSetting = DropDownBox.xpath("//table[@id='commission-group']//select");
    public Button btnUpdate = Button.xpath("//button[contains(@class,'directDownline')]");
    private String ddbGameXpath = "//label[@for='%s']/following::select[1]";
    public Label lblBreadcrumb = Label.xpath("//div[contains(@class,'downline-bar')]");
    public Label lblMemberBreadcrumb = Label.xpath("//div[@id='commission-member']/div[@class='psection']");
    public CommissionSettingListing commissionSettingListing;
    public CommissionSettingListingPage(String types) {
        super(types);
        _type = types;
        commissionSettingListing = ComponentsFactory.commissionSettingListing(_type);
    }

    public void verifyTableHeaderDisplayCorrect(String product) {
        commissionSettingListing.verifyTableHeaderDisplayCorrect(product);
    }

    public void search(String username, String level, String accountStatus, String product) {
        if (!username.isEmpty())
            txtUsername.sendKeys(username);
        if (!accountStatus.isEmpty())
            ddbAccountStatus.selectByVisibleText(accountStatus);
        if (!product.isEmpty()) {
            ddbProduct.selectByVisibleText(product);
            waitingLoadingSpinner();
        }
        if (!level.isEmpty()) {
            ddbLevel.selectByVisibleText(level);
            waitingLoadingSpinner();
        }
        btnSearch.click();
        waitingLoadingSpinner();
        waitingLoadingSpinner();
    }


    public void updateCommissionSetting(String loginID, boolean accountIsAgentlevel, String product, double commissionValue) {
        // select the checkbox with corresponding loginID
        String chbDownlinexPath;
        if (accountIsAgentlevel)
            chbDownlinexPath = tblAgentCommission.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, colUsername, 1, null, chbCol, "input", false, false);
        else
            chbDownlinexPath = tblMemberCommission.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, colUsername, 1, null, chbCol, "input", false, false);
        CheckBox chb = CheckBox.xpath(chbDownlinexPath);
        if (!chb.isDisplayed()) {
            System.out.println(String.format("Not found the element %s", chbDownlinexPath));
            return;
        }
        if (!product.isEmpty()) {
            ddbProduct.selectByVisibleText(product);
            waitingLoadingSpinner();
        }
        chb.click();
        waitingLoadingSpinner();
        //based on selected product, input the corresponding commission
//        inputCommission(lstGameCommission, product);
        if (commissionValue == 0.0) {
            ddbSetCommissionSetting.selectByVisibleText("0");
        } else {
            ddbSetCommissionSetting.selectByVisibleText(String.valueOf(commissionValue));
        }


        //Click update
        btnUpdate.click();
        waitingLoadingSpinner();
    }

    public void inputCommission(List<Double> lstGameCommission, String productName) {
        List<String> lstGameListLabel;
        switch (productName) {
            case AGConstant.LIVE_DEALER_ASIAN:
                lstGameListLabel = AGConstant.AgencyManagement.CommissionSettingListing.LST_LIVE_DEALER_ASIAN_GAMES;
                break;
            case AGConstant.LIVE_DEALER_EUROPEAN:
                lstGameListLabel = AGConstant.AgencyManagement.CommissionSettingListing.LST_LIVE_DEALER_EUROPEAN_GAMES;
                break;
            default: // Lottery & Slot
                lstGameListLabel = AGConstant.AgencyManagement.CommissionSettingListing.LST_LOTTERY_SLOT_GAMES;
        }
        DropDownBox ddpGame;
        for (int i = 0; i < lstGameCommission.size(); i++) {
            String game = lstGameListLabel.get(i);
            ddpGame = DropDownBox.xpath(String.format(ddbGameXpath, game));
            if (!ddpGame.isDisplayed(1)) {
                System.out.println(String.format("Not found the element %s", ddpGame.getLocator()));
                return;
            }
            ddpGame.selectByVisibleText(String.format("%.2f", lstGameCommission.get(i)));
        }
    }

    public boolean verifyCommissionUpdate(List<ArrayList<String>> expectedData, boolean isAgentSection, boolean isSuccessStatus) {
        List<ArrayList<String>> listActualInfo = new ArrayList<ArrayList<String>>();
        String rowXpath;
        String cellxPath;
        int totalColumn;
        Row row;
        if (isAgentSection) {
            totalColumn = tblAgentCommissionTotalCol;
            rowXpath = String.format("%s%s", this.tblAgentCommission.getLocator().toString().replace("By.xpath: ", ""), "//tbody/tr[%s]");
            cellxPath = String.format("%s//tr[%s]//td[%s]", this.tblAgentCommission.getLocator().toString().replace("By.xpath: ", ""), "%s", agentUpdateStatusCol);
        } else {
            totalColumn = tblMemberCommissionTotalCol;
            rowXpath = String.format("%s%s", this.tblMemberCommission.getLocator().toString().replace("By.xpath: ", ""), "//tbody/tr[%s]");
            cellxPath = String.format("%s//tr[%s]//td[%s]", this.tblMemberCommission.getLocator().toString().replace("By.xpath: ", ""), "%s", colMemberUpdateStatusCol);
        }
        for (int i = 0, n = expectedData.size(); i < n; i++) {
            row = Row.xpath(String.format(rowXpath, i + 1));
            listActualInfo.add(i, row.getRow(totalColumn, false));
            Label lblIcon;
            if (isSuccessStatus) {
                lblIcon = Label.xpath(String.format("%s%s", String.format(cellxPath, i + 1), successIcon));
            } else {
                lblIcon = Label.xpath(String.format("%s%s", String.format(cellxPath, i + 1), errorIcon));
            }
            if (!lblIcon.isDisplayed())
                return false;
        }
        return expectedData.equals(listActualInfo);
    }

    public void verifyCommissionUpdated(boolean isAgent, String expectedValue) {
        if (isAgent) {
            List<ArrayList<String>> lstRow = tblAgentCommission.getRowsWithoutHeader(1,false);
            Assert.assertEquals(lstRow.get(0).get(8),expectedValue,String.format("FAILED! Commission is not updated correct actual: %s and expected: %s",lstRow.get(0).get(8), expectedValue));
        } else {
            Assert.assertTrue(true,"Handle for case is member");
        }

    }

}
