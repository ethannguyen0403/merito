package agentsite.pages.marketsmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.marketsmanagement.liquiditythreshold.HistoryPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LiquidityThresholdPage extends HomePage {
    public TextBox txtUsername = TextBox.id("username");
    public Button btnSubmit = Button.xpath("//button[@class='pbtn search']");
    public Label lblBreadcrumb = Label.xpath("//span[@class='my-breadcrumb']//span[@class='downline']");
    public int tblLiquidityTotalCol = 9;
    public int colUsername = 2;
    public int colLoginID = 3;
    public int colLevel = 4;
    public int colAccountStatus = 5;
    public int colLiquidityThresholdStatus = 6;
    public int colLastUpdateTime = 7;
    public int colLastUpdateBy = 8;
    public int colHistory = 9;
    public Table tblLiquidity = Table.xpath("//table[@class='ptable report']", 9);
    private Label iconLoadSpinner = Label.xpath("//div[contains(@class,'la-ball-clip-rotate')]");

    ////span[@class='slider round']
    public LiquidityThresholdPage(String types) {
        super(types);
    }

    public void search(String username) {
        txtUsername.sendKeys(username);
        btnSubmit.click();
    }

    public String getCompanyUsernamebyBreadcrum(String username) {
        search(username);
        return lblBreadcrumb.getWebElements().get(1).getText();
    }

    public void navigateBreadcrumb(String upline) {
        List<WebElement> lstElement = lblBreadcrumb.getWebElements();
        for (WebElement e : lstElement) {
            if (e.getText().equalsIgnoreCase(upline))
                e.click();
        }
    }

    public void setLiquidityThreshold(String username, boolean isActive) {
        boolean flag = isLiquidityStatusAsExpected(username, isActive);
        if (!flag) {
            Link lnlLiquidity = (Link) tblLiquidity.getControlBasedValueOfDifferentColumnOnRow(username, 1, colUsername, 1, null, colLiquidityThresholdStatus, "span[@class='slider round']", false, false);
            lnlLiquidity.click();
            waitingLoadingSpinner();
        }
    }

    private Link getHistoryLink(String username) {
        return (Link) tblLiquidity.getControlBasedValueOfDifferentColumnOnRow(username, 1, colUsername, 1, null, colHistory, "span[contains(@class,'downline')]", false, false);
    }

    public HistoryPopup openHistory(String username) {
        Link lnkView = getHistoryLink(username);
        if (Objects.nonNull(lnkView)) {
            if (!lnkView.isDisplayed()) {
                return null;
            }
            lnkView.click();
            return new HistoryPopup();
        }

        return null;
    }

    public boolean isLiquidityStatusAsExpected(String username, boolean isActiveStatus) {
        System.out.println("Open History popup to get status");
        HistoryPopup popup = openHistory(username);
        if (Objects.isNull(popup)) {
            if (!isActiveStatus) {
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

    @Override
    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(1, 1);
    }

}
