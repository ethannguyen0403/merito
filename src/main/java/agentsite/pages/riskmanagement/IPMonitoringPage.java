package agentsite.pages.riskmanagement;

import agentsite.controls.Cell;
import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.ultils.riskmanagement.IPMonitoringUtils;
import com.paltech.element.common.*;
import org.testng.Assert;
import org.testng.SkipException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static common.AGConstant.HomePage.IP_MONITORING;
import static common.AGConstant.RiskManagement.IPMonitoring.*;

public class IPMonitoringPage extends HomePage {
    private Label lblTitlePage = Label.xpath("//app-title-dashboard//div[@class='title']//label");
    private Label lblRefresh = Label.xpath("//app-title-dashboard//span[contains(@class,'extension outstandinglock')]");
    private  DropDownBox ddbLoginStatus =  com.paltech.element.common.DropDownBox.xpath("//div[contains(@class,'login-status')]//select");
    private TextBox txtIPAddress = TextBox.xpath("//div[contains(@class,'ip-address')]//input");
    private TextBox txtUsername = TextBox.xpath("//div[contains(@class,'user-name')]//input");
    private Button btnSubmit =  Button.xpath("//button[text()='Submit']");
    private Label lblInfoGuide = Label.xpath("//label[@class='info-guide']");
    private Table tblResult = Table.xpath("//table[contains(@class,'ip-monitoring-table')]", 8);
    private Label lblNoRecordFound = Label.xpath("//span[text()='No records found.']");
    private Button btnCloseAlert = Button.xpath("//app-alert-create//button[@class='close']");
    String rowUsernameXpath = "(//table[contains(@class,'ip-monitoring-table')]//tbody[1]//tr[%s]//td[(not(@class='text-center align-middle ng-star-inserted'))])[1]";
    String rowButtonLabelXpath = "(//table[contains(@class,'ip-monitoring-table')]//tbody[1]//tr[%s]//td[(not(@class='text-center align-middle ng-star-inserted'))])[6]//button[contains(@class,'submit %s')]//span";
    String rowButtonXpath = "(//table[contains(@class,'ip-monitoring-table')]//tbody[1]//tr[%s]//td[(not(@class='text-center align-middle ng-star-inserted'))])[6]//button[contains(@class,'submit %s')]";
    public IPMonitoringPage(String types) {
        super(types);
    }

    public void verifyUIDisplayCorrect() {
        Assert.assertEquals(lblTitlePage.getText(),IP_MONITORING, "FAILED! Title page does not display correct");
        Assert.assertTrue(lblRefresh.isDisplayed(),"FAILED! Refresh button does not display");
        Assert.assertEquals(ddbLoginStatus.getFirstSelectedOption(),"Live","FAILED! Default value is not correct");
        Assert.assertTrue(txtIPAddress.isDisplayed(), "FAILED! IP Address field does not display");
        Assert.assertTrue(txtUsername.isDisplayed(), "FAILED! Username field does not display");
        Assert.assertTrue(btnSubmit.isDisplayed(), "FAILED! Submit button does not display");
        Assert.assertEquals(lblInfoGuide.getText(),LBL_INFO_GUIDE, "FAILED! Info guide text does not display correct");
        List<String> lstTableHeader = tblResult.getHeaderList();
        Assert.assertEquals(lstTableHeader,TBL_HEADER_LIST, "FAILED! Table header does not display correct");
    }

    public void filter(String status, String ipAddress, String userName) {
        if(!status.isEmpty()) {
            ddbLoginStatus.selectByVisibleText(status);
        }
        if(!ipAddress.isEmpty()) {
            txtIPAddress.sendKeys(ipAddress);
        }
        if (!userName.isEmpty()) {
            txtUsername.sendKeys(userName);
        }
        btnSubmit.click();
        waitingLoadingSpinner();
        waitingLoadingSpinner();
    }

    public void verifyFilterResultCorrect(String status, String ipAddress, String userName) {
        if(!ipAddress.isEmpty()) {
            List<String> lstIPs = getListIPResult();
            if(Objects.isNull(lstIPs)) {
                System.out.println("PASSED as default cause have no data for testing");
                return;
            } else {
                for (int i = 0; i < lstIPs.size(); i++) {
                    if(lstIPs.get(i).contains(ipAddress)) {
                        System.out.println(String.format("Found IP %s in list of IPs result %s", ipAddress, lstIPs.get(i)));
                        return;
                    }
                }
            }
            Assert.assertTrue(lstIPs.containsAll(Collections.singleton(ipAddress)),String.format("FAILED! IP %s does not show in list result", ipAddress));
        }
        if(!userName.isEmpty()) {
            List<String> lstUsers = getListUsernameResult();
            if(Objects.isNull(lstUsers)) {
                System.out.println("PASSED as default cause have no data for testing");
                return;
            } else {
                for (int i = 0; i < lstUsers.size(); i++) {
                    if(lstUsers.get(i).contains(userName)) {
                        System.out.println(String.format("Found username %s in list of users result %s", userName, lstUsers.get(i)));
                        return;
                    }
                }
                Assert.assertTrue(false, String.format("FAILED! Cannot find the username %s exists in list result", userName));
            }
        }
        ArrayList<List> lstRecords = IPMonitoringUtils.getListIPMonitoring(MAP_FILTER_STATUS.get(status), ipAddress, userName);
        if (lstRecords.size() == 0) {
            Assert.assertTrue(lblNoRecordFound.isDisplayed(),"FAILED! No record found does not appear although API result empty");
        } else {
            List<ArrayList<String>> lstRowArr = getListUserDetailsResult();
            int count = 0;
            for (int i = 0; i < lstRecords.size(); i++) {
                for (int j = 0; j < lstRecords.get(i).size(); j++) {
                    ArrayList<String> lstNew = (ArrayList<String>) lstRecords.get(i).get(j);
                    Assert.assertTrue(lstRowArr.get(count).get(0).contains(lstNew.get(2)), String.format("FAILED! Username %s does not display in result %s", lstNew.get(2), lstRowArr.get(count).get(0)));
                    Assert.assertEquals(lstRowArr.get(count).get(1), lstNew.get(3), "FAILED! Total bet does not display correct");
                    Assert.assertTrue(lstRowArr.get(count).get(2).contains(lstNew.get(4)), "FAILED! Member exposure bet does not display correct");
                    Assert.assertEquals(lstRowArr.get(count).get(3), String.format("%.2f",Float.valueOf(lstNew.get(5))), "FAILED! Member last 7 day does not display correct");
                    Assert.assertEquals(lstRowArr.get(count).get(4), String.format("%.2f",Float.valueOf(lstNew.get(6))), "FAILED! My total does not display correct");
                    count++;
                }
            }
        }
    }

    private List<ArrayList<String>> getListUserDetailsResult() {
        String rowXpath = "//table[contains(@class,'ip-monitoring-table')]//tbody[1]//tr[%s]//td[not(@class='text-center align-middle ng-star-inserted')]";
        String columnXpath = "(//table[contains(@class,'ip-monitoring-table')]//tbody[1]//tr[%s]//td[not(@class='text-center align-middle ng-star-inserted')])[%s]";
        int totalRow = tblResult.getNumberOfRows(false, true);
        List<ArrayList<String>> lstRowArr = new ArrayList<>();
        for (int i = 0; i < totalRow; i++) {
            ArrayList<String> lstRow = new ArrayList<>();
            Row row = Row.xpath(String.format(rowXpath, i + 1));
            int columnOnRow = row.getWebElements().size();
            //get rows result, ignore total row
            for (int j = 0; j < columnOnRow - 1; j++) {
                Cell cell = Cell.xpath(String.format(columnXpath, i + 1, j + 1));
                lstRow.add(j, cell.getText().trim());
            }
            lstRowArr.add(i, lstRow);
        }
        return lstRowArr;
    }

    private List<String> getListIPResult() {
        if(lblNoRecordFound.isDisplayed()) {
            return null;
        }
        List<String> lstIp = new ArrayList<>();
        Row rowIp = Row.xpath("(//table[contains(@class,'ip-monitoring-table')]//tbody[1]//tr//td[(@class='text-center align-middle ng-star-inserted')])[2]");
        for (int i = 0; i < rowIp.getWebElements().size(); i++) {
            lstIp.add(i, rowIp.getWebElements().get(i).getText());
        }
        return lstIp;
    }

    public List<String> getListUsernameResult() {
        if(lblNoRecordFound.isDisplayed()) {
            return null;
        }
        List<String> lstUsername = new ArrayList<>();
        int totalRow = tblResult.getNumberOfRows(false, true);
        for (int i = 0; i < totalRow; i++) {
            Row rowUsername = Row.xpath(String.format(rowUsernameXpath, i +1));
            lstUsername.add(i, rowUsername.getText().replaceAll("\\n",","));
        }
        return lstUsername;
    }

    public void actionOnUser(String userName, String action, boolean isCloseAlert) {
        int totalRow = tblResult.getNumberOfRows(false, false);
        String userNameSplit = userName.split(",")[0];
        for (int i = 0; i < totalRow; i++) {
            Row rowUsername = Row.xpath(String.format(rowUsernameXpath, i +1));
            if(rowUsername.getText().contains(userNameSplit)) {
                if(action.equalsIgnoreCase("Suspend")) {
                    Button btnAction = Button.xpath(String.format(rowButtonLabelXpath, i+1,"suspend"));
                    if(btnAction.getText().equalsIgnoreCase("suspend")) {
                        btnAction.click();
                    }
                } else if (action.equalsIgnoreCase("Unsuspend")) {
                    Button btnAction = Button.xpath(String.format(rowButtonLabelXpath, i+1,"suspend"));
                    if(btnAction.getText().equalsIgnoreCase("unsuspend")) {
                        btnAction.click();
                    }
                } else if (action.equalsIgnoreCase("Monitor")) {
                    Button btnAction = Button.xpath(String.format(rowButtonLabelXpath, i+1,"monitor"));
                    if(btnAction.getText().equalsIgnoreCase("monitor")) {
                        btnAction.click();
                    }
                } else if (action.equalsIgnoreCase("Un-monitor")) {
                    Button btnAction = Button.xpath(String.format(rowButtonLabelXpath, i+1,"monitor"));
                    if(btnAction.getText().equalsIgnoreCase("un-monitor")) {
                        btnAction.click();
                    }
                }
                waitingLoadingSpinner();
                if(isCloseAlert) {
                    btnCloseAlert.click();
                }
                break;
            }
        }
    }

    public void verifyActionButtonShowCorrect(String userName, String expectedAction) {
        int totalRow = tblResult.getNumberOfRows(false, true);
        for (int i = 0; i < totalRow; i++) {
            Row rowUsername = Row.xpath(String.format(rowUsernameXpath, i +1));
            if(rowUsername.getText().contains(userName)) {
                if(expectedAction.equalsIgnoreCase("Suspend") || expectedAction.equalsIgnoreCase("Unsuspend")) {
                    Button btnAction = Button.xpath(String.format(rowButtonLabelXpath, i+1,"suspend"));
                    Assert.assertEquals(btnAction.getText(), expectedAction,"FAILED! Button text does not display correct");
                    break;
                } else if (expectedAction.equalsIgnoreCase("Monitor") || expectedAction.equalsIgnoreCase("Un-monitor")) {
                    Button btnAction = Button.xpath(String.format(rowButtonLabelXpath, i+1,"monitor"));
                    Assert.assertEquals(btnAction.getText(), expectedAction,"FAILED! Button text does not display correct");
                    break;
                }
            }
        }
    }

    public void verifySuspendButtonState(String userName, boolean isEnable) {
        if(lblNoRecordFound.isDisplayed()) {
            throw new SkipException(String.format("SKIPPED! The suspended account %s is not belong to current login agent", userName));
        }
        int totalRow = tblResult.getNumberOfRows(false, true);
        String userNameSplit = userName.split(",")[0];
        for (int i = 0; i < totalRow; i++) {
            Row rowUsername = Row.xpath(String.format(rowUsernameXpath, i + 1));
            if (rowUsername.getText().contains(userNameSplit)) {
                Button rowButton = Button.xpath(String.format(rowButtonXpath, i + 1, "suspend"));
                if (isEnable) {
                    Assert.assertTrue(rowButton.isEnabled(),"FAILED! Suspend button is not enabled");
                    break;
                } else {
                    Assert.assertFalse(rowButton.isEnabled(),"FAILED! Suspend button is not disabled");
                    break;
                }
            }
        }
    }

    public void verifyUserDetailInfo(List<String> userInfoDetail) {
        //get info on UI
        List<ArrayList<String>> lstRowArr = getListUserDetailsResult();
        //process info array from param (handle case 0.00, negative, positive amount)
        String [] info = userInfoDetail.get(0).split(",");
        String nickName = info[2].trim();
        String userCode = info[3].trim();
        String totalBet = info[4].trim();
        String exposure = "0.00";
        if(Float.valueOf(info[5].trim()) > 0) {
            exposure = String.format("-%s",  info[5].trim());
        }
        String winLoss = info[6].trim();
        String loginWinLoss = info[10].replace("]","").trim();
        for (int i = 0; i < lstRowArr.size(); i++) {
            if(lstRowArr.get(i).get(0).equalsIgnoreCase(userCode)) {
                Assert.assertTrue(lstRowArr.get(i).get(0).contains(userCode), String.format("FAILED! Result %s does not contain usercode %s", lstRowArr.get(i).get(0), userCode));
                if(!nickName.isEmpty()) {
                    Assert.assertTrue(lstRowArr.get(i).get(0).contains(nickName), String.format("FAILED! Result %s does not contain nickname %s", lstRowArr.get(i).get(0), nickName));
                }
                Assert.assertEquals(lstRowArr.get(i).get(1), totalBet, "FAILED! Total does not display correct");
                if(!exposure.equalsIgnoreCase("0.00")) {
                    Assert.assertEquals(Double.valueOf(lstRowArr.get(i).get(2)), Double.valueOf(exposure), 0.01, "FAILED! Exposure does not display correct");
                } else {
                    Assert.assertEquals(lstRowArr.get(i).get(2), exposure, "FAILED! Exposure does not display correct");
                }
                Assert.assertEquals(Double.valueOf(lstRowArr.get(i).get(3)), Double.valueOf(winLoss), 0.01,"FAILED! Member 7 day win loss does not display correct");
                Assert.assertEquals(Double.valueOf(lstRowArr.get(i).get(4)), Double.valueOf(loginWinLoss), 0.01, "FAILED! My total does not display correct");
                break;
            }
        }
    }
}
