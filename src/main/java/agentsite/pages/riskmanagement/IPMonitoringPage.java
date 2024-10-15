package agentsite.pages.riskmanagement;

import agentsite.controls.Cell;
import agentsite.controls.Row;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.ultils.riskmanagement.IPMonitoringUtils;
import com.paltech.element.common.*;
import org.testng.Assert;

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
    }

    public void verifyFilterResultCorrect(String status, String ipAddress, String userName) {
        if(!ipAddress.isEmpty()) {
            List<String> lstIPs = getListIPResult();
            if(Objects.isNull(lstIPs)) {
                Assert.assertTrue(true,"FAILED! No record found does not appear although result empty");
            }
            Assert.assertTrue(lstIPs.containsAll(Collections.singleton(ipAddress)),"FAILED");
        }
        if(!userName.isEmpty()) {
            List<String> lstUsers = getListUsernameResult();
            if(Objects.isNull(lstUsers)) {
                Assert.assertTrue(true,"FAILED! No record found does not appear although result empty");
            }
            Assert.assertTrue(lstUsers.contains(userName),"FAILED");
        }
        ArrayList<List> lstRecords = IPMonitoringUtils.getListIPMonitoring(MAP_FILTER_STATUS.get(status), ipAddress, userName);
        if (lstRecords.size() == 0) {
            Assert.assertTrue(lblNoRecordFound.isDisplayed(),"FAILED! No record found does not appear although result empty");
        } else {
            List<ArrayList<String>> lstRowArr = getListUserDetailsResult();
            int count = 0;
            for (int i = 0; i < lstRecords.size(); i++) {
                for (int j = 0; j < lstRecords.get(i).size(); j++) {
                    ArrayList<String> lstNew = (ArrayList<String>) lstRecords.get(i).get(j);
                    Assert.assertTrue(lstRowArr.get(count).get(0).contains(lstNew.get(2)), "FAILED! Username does not display correct");
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

    private List<String> getListUsernameResult() {
        if(lblNoRecordFound.isDisplayed()) {
            return null;
        }
        List<String> lstUsername = new ArrayList<>();
        int totalRow = tblResult.getNumberOfRows(false, true);
        Row rowUsername = Row.xpath("(//table[contains(@class,'ip-monitoring-table')]//tbody[1]//tr[%s]//td[(not(@class='text-center align-middle ng-star-inserted'))])[1]");
        for (int i = 0; i < totalRow; i++) {
            lstUsername.add(i, rowUsername.getWebElements().get(i).getText());
        }
        return lstUsername;
    }

}
