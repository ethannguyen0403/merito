package agentsite.pages.agentmanagement.taxsettinglisting;


import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;

import java.util.ArrayList;
import java.util.List;

public class NewUITaxSettingListing extends TaxSettingListing {
    private int updateStatusCol = 15;
    public int fancyCol = 11;
    public int decimalCricketCol = 12;
    public int virtualCricketCol = 13;
    public int otherCol = 14;
    public List<ArrayList<String>> defineListTaxSetting(double inputValue) {
        List<ArrayList<String>> lstTaxSetting = tblTax.getRowsWithoutHeader(1, false);
        double soccerTax = Double.parseDouble(lstTaxSetting.get(0).get(6).split("%")[0]) + inputValue;
        double cricketTax = Double.parseDouble(lstTaxSetting.get(0).get(7).split("%")[0]) + inputValue;
        double tennisTax = Double.parseDouble(lstTaxSetting.get(0).get(8).split("%")[0]) + inputValue;
        double basketballTax = Double.parseDouble(lstTaxSetting.get(0).get(9).split("%")[0]) + inputValue;
        double fancyTax = Double.parseDouble(lstTaxSetting.get(0).get(10).split("%")[0]) + inputValue;
        double decimalCricketTax = Double.parseDouble(lstTaxSetting.get(0).get(11).split("%")[0]) + inputValue;
        double virtualCricketTax = Double.parseDouble(lstTaxSetting.get(0).get(12).split("%")[0]) + inputValue;
        double otherTax = Double.parseDouble(lstTaxSetting.get(0).get(13).split("%")[0]) + inputValue;
        lstTaxSetting.get(0).set(soccerCol - 1, String.format("%.2f", soccerTax) + "%");
        lstTaxSetting.get(0).set(cricketCol - 1, String.format("%.2f", cricketTax) + "%");
        lstTaxSetting.get(0).set(tennisCol - 1, String.format("%.2f", tennisTax) + "%");
        lstTaxSetting.get(0).set(basketballCol - 1, String.format("%.2f", basketballTax) + "%");
        lstTaxSetting.get(0).set(fancyCol - 1, String.format("%.2f", fancyTax) + "%");
        lstTaxSetting.get(0).set(decimalCricketCol - 1, String.format("%.2f", decimalCricketTax) + "%");
        lstTaxSetting.get(0).set(virtualCricketCol - 1, String.format("%.2f", virtualCricketTax) + "%");
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
        double soccerValue = Double.valueOf(lstData.get(0).get(6).split("%")[0]);
        double cricketValue = Double.valueOf(lstData.get(0).get(7).split("%")[0]);
        double tennisValue = Double.valueOf(lstData.get(0).get(8).split("%")[0]);
        double basketballValue = Double.valueOf(lstData.get(0).get(9).split("%")[0]);
        double fancyValue = Double.valueOf(lstData.get(0).get(10).split("%")[0]);
        double virtualCricketValue = Double.valueOf(lstData.get(0).get(11).split("%")[0]);
        double otherTaxValue = Double.valueOf(lstData.get(0).get(12).split("%")[0]);

        txtSoccer.sendKeys(String.format("%.2f", soccerValue));
        txtCricket.sendKeys(String.format("%.2f", cricketValue));
        txtTennis.sendKeys(String.format("%.2f", tennisValue));
        txtBasketball.sendKeys(String.format("%.2f", basketballValue));
        txtFancy.sendKeys(String.format("%.2f", fancyValue));
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

}
