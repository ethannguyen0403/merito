package agentsite.pages.marketsmanagement;

import agentsite.controls.Table;
import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.HomePage;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.marketsmanagement.liquiditythreshold.HistoryPopup;
import agentsite.ultils.agencymanagement.DownLineListingUtils;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.LBL_USERNAME;

public class LiquidityThresholdPage extends HomePage {
    public int colUsername = 2;
    public int colLoginID = 3;
    protected int colLevel = 4;
    public int colLiquidityThresholdStatus = 6;
    public int colHistory = 9;
    public Label lblUsername = Label.xpath("//table[@class='ptable info']//label[text()='Username']");
    public TextBox txtUsername = TextBox.id("username");
    public Button btnSubmit = Button.xpath("//button[@class='pbtn search']");
    public Label lblBreadcrumb = Label.xpath("//span[@class='my-breadcrumb']//span[@class='downline']");
    public Table tblLiquidity = Table.xpath("//table[@class='ptable report']", 9);
    public Label lblTitle = Label.xpath("//div[@class='title']//label");

    public LiquidityThresholdPage(String types) {
        super(types);
        _type = types;
    }

    public void setLiquidityThreshold(String username, boolean isActive) {
        boolean flag = isLiquidityStatusAsExpected(username, isActive);
        if (!flag) {
            Link lnlLiquidity = (Link) tblLiquidity.getControlBasedValueOfDifferentColumnOnRow(username, 1, colUsername, 1, null, colLiquidityThresholdStatus, "span[@class='slider round']", false, false);
            lnlLiquidity.click();
            waitingLoadingSpinner();
        }
    }
    public String getLiquidityThresholdTitle() {return lblTitle.getText().trim();}

    public void search(String username) {
        try {
            txtUsername.sendKeys(username);
            btnSubmit.click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void clickOnUserLink(String username) {
        Link lnkUser = (Link) tblLiquidity.getControlBasedValueOfDifferentColumnOnRow(username, 1, colUsername, 1, null, colUsername, "span[contains(@class,'downline')]", false, false);
        lnkUser.click();
    }

    private Link getHistoryLink(String username) {
        return (Link) tblLiquidity.getControlBasedValueOfDifferentColumnOnRow(username, 1, colUsername, 1, null, colHistory, "span[contains(@class,'downline')]", false, false);
    }

    public HistoryPopup openHistory(String username) {
        try {
            Link lnkView = getHistoryLink(username);
            if (Objects.nonNull(lnkView)) {
                if (!lnkView.isDisplayed()) {
                    return null;
                }
                lnkView.click();
                return new HistoryPopup();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public void verifyAllDownlineStatusCorrect(boolean isActivate) {
        int totalRow = tblLiquidity.getNumberOfRows(false, true);
        for (int i = 0; i < totalRow; i++) {
            String xpathCell = tblLiquidity.getxPathOfCell(1, colLiquidityThresholdStatus, i+1, "label");
            Label lblLiquidStatus = Label.xpath(xpathCell);
            if(isActivate) {
                Assert.assertFalse(lblLiquidStatus.getAttribute("innerHTML").contains("disabledstatus"), "FAILED! All downline is not activated when upline activated");
            } else {
                Assert.assertTrue(lblLiquidStatus.getAttribute("innerHTML").contains("disabledstatus"), "FAILED! All downline is not inactivated when upline inactivated");
            }
        }
    }
    public boolean isLiquidityStatusAsExpected(String username, boolean isActiveStatus) {
        System.out.println("Open History popup to get status");
        HistoryPopup popup = openHistory(username);
        if (Objects.isNull(popup)) {
            if (isActiveStatus) {
                return true;
            }
            return false;
        }

        List<ArrayList<String>> historyFirstRowList = popup.tblReport.getRowsWithoutHeader(1, false);
        System.out.println("Close History popup to get status");
        popup.closePopup();

        System.out.println("Get the status at the first row");
        String status = historyFirstRowList.get(0).get(popup.colAction - 1);
        if (status.contains("Activate Liquidity Threshold") && isActiveStatus == true)//Activate Liquidity Threshold
            return true;
        if (status.contains("Deactivate Liquidity Threshold") && isActiveStatus == false)
            return true;
        return false;
    }

    public void verifyUIDisplayCorrect() {
        Assert.assertEquals(lblUsername.getText(), LBL_USERNAME, "FAILED! Label Username does not match");
        Assert.assertTrue(txtUsername.isDisplayed(), "FAILED! Username textbox does not display");
        Assert.assertTrue(btnSubmit.isDisplayed(), "FAILED! Submit button does not display");
        Assert.assertTrue(lblBreadcrumb.isDisplayed(), "FAILED! Breadcrumb does not display");
        Assert.assertTrue(tblLiquidity.isDisplayed(), "FAILED! Table setting does not display");
    }

    public ArrayList<String> getUplineAgencies(String username) {
        List<AccountInfo> lstAccInfo = DownLineListingUtils.getUplineInfoOfUser(_type, username);
        ArrayList<String> lstUpline = new ArrayList<>();
        for (int i = 0; i < lstAccInfo.size(); i++) {
            lstUpline.add(lstAccInfo.get(i).getUserCode());
        }
        return lstUpline;
    }



}
