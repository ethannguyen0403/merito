package agentsite.pages.agentmanagement.taxsettinglisting;

import agentsite.controls.Table;
import com.paltech.element.common.*;

import java.util.ArrayList;
import java.util.List;

public class TaxSettingListing {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    private int totalColum = 13;
    private int usernameCol = 2;
    private int chbCol = 5;
    private int updateStatusCol = 13;
    private String successIcon = "//span[contains(@class,'psuccess')]";
    private String errorIcon = "//span[contains(@class,'perror')]";
    public TextBox txtUsername = TextBox.id("memberCode");
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("//label[text()='Account Status']//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//label[text()='Product']//following::select[1]");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public TextBox txtSoccer = TextBox.xpath("//input[@id='backSOCCER']");
    public TextBox txtTennis = TextBox.xpath("//input[@id='backTENNIS']");
    public TextBox txtCricket = TextBox.xpath("//input[@id='backCRICKET']");
    public TextBox txtBasketball = TextBox.xpath("//input[@id='backBASKETBALL']");
    public TextBox txtOther = TextBox.xpath("//input[@id='backOTHER']");
    public Table tblTax = Table.xpath("//table[contains(@class,'ptable report')]", totalColum);
    public Button btnUpdate = Button.id("bntUpdate");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
    }

    public void search(String username, String accountStatus, String product) {
        if (!username.isEmpty())
            txtUsername.sendKeys(username);
        if (!accountStatus.isEmpty())
            ddbAccountStatus.selectByVisibleText(accountStatus);
        if (!product.isEmpty()) {
            ddbProduct.selectByVisibleText(product);
            waitingLoadingSpinner();
        }
        btnSearch.click();
        waitingLoadingSpinner();
    }

    public void updateTaxSetting(String loginID, double soccerTax, double cricketTax, double tennisTax, double basketballTax, double otherTax) {

        //input Min, Max, Max Liability per Market, Max Win Per Market
        inputValue(soccerTax, cricketTax, tennisTax, basketballTax, otherTax);

        // Select the checkbox corresponding with login ID
        String chbDownlinexPath = tblTax.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, usernameCol, 1, null, chbCol, "input[@id='cItem']", false, false);
        CheckBox chb = CheckBox.xpath(chbDownlinexPath);
        chb.click();

        //Click update
        btnUpdate.click();
        waitingLoadingSpinner();
    }

    public void inputValue(double soccerTax, double cricketTax, double tennisTax, double basketballTax, double otherTax) {
        if (soccerTax != -1)
            txtSoccer.sendKeys(String.format("%.2f", soccerTax));
        if (cricketTax != -1)
            txtCricket.sendKeys(String.format("%.2f", cricketTax));
        if (tennisTax != -1)
            txtTennis.sendKeys(String.format("%.2f", tennisTax));
        if (basketballTax != -1)
            txtBasketball.sendKeys(String.format("%.2f", basketballTax));
      /*  if(fancyTax != -1)
            txtFancy.sendKeys(String.format("%.2f",fancyTax));*/
        if (otherTax != -1)
            txtOther.sendKeys(String.format("%.2f", otherTax));
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
