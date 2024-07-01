package agentsite.pages.agentmanagement.taxsettinglisting;


import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class OldUITaxSettingListing extends TaxSettingListing {
    private int otherCol = 13;
    private int updateStatusCol = 14;
    public int virtualCricketCol = 12;
    public int decimalCricketCol = 11;

    public List<ArrayList<String>> defineListTaxSetting(double inputValue) {
        List<ArrayList<String>> lstTaxSetting = tblTax.getRowsWithoutHeader(1, false);
        double soccerTax = Double.parseDouble(lstTaxSetting.get(0).get(soccerCol - 1).split("%")[0]) + inputValue;
        double cricketTax = Double.parseDouble(lstTaxSetting.get(0).get(cricketCol - 1).split("%")[0]) + inputValue;
        double tennisTax = Double.parseDouble(lstTaxSetting.get(0).get(tennisCol - 1).split("%")[0]) + inputValue;
        double basketballTax = Double.parseDouble(lstTaxSetting.get(0).get(basketballCol - 1).split("%")[0]) + inputValue;
        double virtualCricketTax = Double.parseDouble(lstTaxSetting.get(0).get(virtualCricketCol - 1).split("%")[0]) + inputValue;
        double decimalCricketTax = Double.parseDouble(lstTaxSetting.get(0).get(decimalCricketCol - 1).split("%")[0]) + inputValue;
        double otherTax = Double.parseDouble(lstTaxSetting.get(0).get(otherCol - 1).split("%")[0]) + inputValue;
        lstTaxSetting.get(0).set(soccerCol - 1, String.format("%.2f", soccerTax) + "%");
        lstTaxSetting.get(0).set(cricketCol - 1, String.format("%.2f", cricketTax) + "%");
        lstTaxSetting.get(0).set(tennisCol - 1, String.format("%.2f", tennisTax) + "%");
        lstTaxSetting.get(0).set(basketballCol - 1, String.format("%.2f", basketballTax) + "%");
        lstTaxSetting.get(0).set(virtualCricketCol - 1, String.format("%.2f", virtualCricketTax) + "%");
        lstTaxSetting.get(0).set(decimalCricketCol - 1, String.format("%.2f", decimalCricketTax) + "%");
        lstTaxSetting.get(0).set(otherCol - 1, String.format("%.2f", otherTax) + "%");
        return lstTaxSetting;
    }

    public void updateTaxSetting(String loginID, List<ArrayList<String>> lstData) {

        //input Min, Max, Max Liability per Market, Max Win Per Market
        inputValue(lstData);

        // Select the checkbox corresponding with login ID
        String chbDownlinexPath = tblTax.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, usernameCol, 1, null, chbCol, "input[@id='cItem']", false, false);
        CheckBox chb = CheckBox.xpath(chbDownlinexPath);
        chb.click();

        //Click update
        btnUpdate.click();
        waitingLoadingSpinner();
    }

    public void inputValue(List<ArrayList<String>> lstData) {
        double soccerValue = Double.valueOf(lstData.get(0).get(soccerCol-1).split("%")[0]);
        double cricketValue = Double.valueOf(lstData.get(0).get(cricketCol-1).split("%")[0]);
        double tennisValue = Double.valueOf(lstData.get(0).get(tennisCol-1).split("%")[0]);
        double basketballValue = Double.valueOf(lstData.get(0).get(basketballCol - 1).split("%")[0]);
        double decimalCricketValue = Double.valueOf(lstData.get(0).get(decimalCricketCol - 1).split("%")[0]);
        double virtualCricketValue = Double.valueOf(lstData.get(0).get(virtualCricketCol - 1).split("%")[0]);
        double otherTaxValue = Double.valueOf(lstData.get(0).get(otherCol - 1).split("%")[0]);

        txtSoccer.sendKeys(String.format("%.2f", soccerValue));
        txtCricket.sendKeys(String.format("%.2f", cricketValue));
        txtTennis.sendKeys(String.format("%.2f", tennisValue));
        txtBasketball.sendKeys(String.format("%.2f", basketballValue));
        txtDecimalCricket.sendKeys(String.format("%.2f", decimalCricketValue));
        txtVirtualCricket.sendKeys(String.format("%.2f", virtualCricketValue));
        txtOther.sendKeys(String.format("%.2f", otherTaxValue));
    }

    public boolean verifyUpdateStatus(List<ArrayList<String>> lstData, boolean isSuccess) {
        String cell_xpath;
        for (int i = 0; i < lstData.size(); i++) {
            if (i % 4 == 0) {
                cell_xpath = String.format("%s//tr[%s]//td[%s]", "//table[contains(@class,'ptable report')]", i + 1, updateStatusCol);
                Label lblIcon;
                if (isSuccess) {
                    lblIcon = Label.xpath(String.format("%s%s", cell_xpath, successIcon));
                } else {
                    lblIcon = Label.xpath(String.format("%s%s", cell_xpath, errorIcon));
                }
                if (!lblIcon.isDisplayed())
                    return false;
            }
        }
        return true;
    }

    public void verifyUITaxSetting(String userCode) {
        Assert.assertTrue(txtUsername.isDisplayed(), "FAILED Username textbox not display");
        Assert.assertTrue(ddbAccountStatus.isDisplayed(), "FAILED Account Status Dropdown not display");
        Assert.assertTrue(ddbProduct.isDisplayed(), "FAILED Product Dropdown not display");
        Assert.assertEquals(btnSearch.getText(), AGConstant.BTN_SUBMIT, "FAILED Search button text should be Submit");
        Assert.assertTrue(txtSoccer.isDisplayed(), "FAILED! Soccer textbox not display");
        Assert.assertTrue(txtCricket.isDisplayed(), "FAILED! Cricket textbox not display");
        Assert.assertTrue(txtTennis.isDisplayed(), "FAILED! Tennis textbox not display");
        Assert.assertTrue(txtBasketball.isDisplayed(), "FAILED! Basketball textbox not display");
        Assert.assertTrue(txtOther.isDisplayed(), "FAILED! Other textbox not display");
        Assert.assertTrue(lblBreadcrumb.getText().contains(userCode), "FAILED! Breadcrumb display incorrect value");
        Assert.assertEquals(tblTax.getHeaderNameOfRows(), AGConstant.AgencyManagement.TaxSettingListing.TABLE_TAX_SAT, "FAILED! Table header not match with the expected");
    }

    public List<String> getListLoginId() {
        waitingLoadingSpinner();
        List<String> lstMembers = tblTax.getColumn(loginIDCol, false);
        return lstMembers;
    }
}
