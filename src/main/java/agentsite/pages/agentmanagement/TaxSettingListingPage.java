package agentsite.pages.agentmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.taxsettinglisting.TaxSettingListing;
import agentsite.pages.components.ComponentsFactory;
import com.paltech.element.common.*;

import java.util.HashMap;

public class TaxSettingListingPage extends HomePage {
    public TextBox txtUsername = TextBox.id("memberCode");
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("//label[text()='Account Status']//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//label[text()='Product']//following::select[1]");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public int totalColum = 13;
    public int usernameCol = 2;
    public int loginIDCol = 3;
    public int accountStatusCol = 4;
    public int chbCol = 5;
    public int levelCol = 6;
    public int soccerCol = 7;
    public int cricketCol = 8;
    public int tennisCol = 9;
    public int basketballCol = 10;
     public int fancyCol = 11;
    public int otherCol = 12;
    public int updateStatusCol = 12;
    public Table tblTax = Table.xpath("//table[contains(@class,'ptable report')]", totalColum);
    public TextBox txtSoccer = TextBox.xpath("//input[@id='backSOCCER']");
    public TextBox txtTennis = TextBox.xpath("//input[@id='backTENNIS']");
    public TextBox txtCricket = TextBox.xpath("//input[@id='backCRICKET']");
    public TextBox txtBasketball = TextBox.xpath("//input[@id='backBASKETBALL']");
    //public TextBox txtFancy = TextBox.xpath("//input[@id='backFANCY']");
    public TextBox txtOther = TextBox.xpath("//input[@id='backOTHER']");
    public Label lblBreadcrumb = Label.xpath("//span[@class='my-breadcrumb']");
    public Button btnUpdate = Button.id("bntUpdate");
    private String xPathSport = "//label[contains(text(),'%s')]/preceding::input[1]";
    private Label iconLoadSpinner = Label.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
    private String successIcon = "//span[contains(@class,'psuccess')]";
    private String errorIcon = "//span[contains(@class,'perror')]";
    public TaxSettingListing taxSettingListing;
    public TaxSettingListingPage(String types) {
        super(types);
        _type = types;
        taxSettingListing = ComponentsFactory.taxSettingPage(_type);
    }

//    public void search(String username, String accountStatus, String product) {
//        if (!username.isEmpty())
//            txtUsername.sendKeys(username);
//        if (!accountStatus.isEmpty())
//            ddbAccountStatus.selectByVisibleText(accountStatus);
//        if (!product.isEmpty()) {
//            ddbProduct.selectByVisibleText(product);
//            waitingLoadingSpinner();
//        }
//        btnSearch.click();
//        waitingLoadingSpinner();
//    }

    public void enableSport(HashMap<String, Boolean> map) {
        CheckBox chb = CheckBox.xpath(String.format(xPathSport, "Soccer"));
        if (!map.get("Soccer")) {
            chb.click();
            waitingLoadingSpinner();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Cricket"));
        if (!map.get("Cricket")) {
            chb.click();
            waitingLoadingSpinner();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Tennis"));
        if (!map.get("Tennis")) {
            chb.click();
            waitingLoadingSpinner();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Basketball"));
        if (!map.get("Basketball")) {
            chb.click();
            waitingLoadingSpinner();
        }
       /* chb =CheckBox.xpath(String.format(xPathSport,"Fancy"));
        if(!map.get("Fancy"))
        {
            chb.click();
            waitingLoadingSpinner();
        }*/

        chb = CheckBox.xpath(String.format(xPathSport, "Other"));
        if (!map.get("Other")) {
            chb.click();
            waitingLoadingSpinner();
        }
    }

    public int getSpotColumn(HashMap<String, Boolean> map) {
        int i = 1;
        if (map.get("Soccer")) {
            soccerCol = (totalColum - 1) + i;
            i = i + 1;
        }

        if (map.get("Cricket")) {
            cricketCol = (totalColum - 1) + i;
            i = i + 1;
        }

        if (map.get("Tennis")) {
            tennisCol = (totalColum - 1) + i;
            i = i + 1;
        }

        if (map.get("Basketball")) {
            basketballCol = (totalColum - 1) + i;
            i = i + 1;
        }
      /*  if(map.get("Fancy")){
            fancyCol = (totalColum -1) + i;
            i = i + 1;
        }*/

        if (map.get("Other")) {
            otherCol = (totalColum - 1) + i;
            i = i + 1;
        }
        return i - 1;
    }

//    public void updateTaxSetting(String loginID, double soccerTax, double cricketTax, double tennisTax, double basketballTax, double otherTax) {
//
//        //input Min, Max, Max Liability per Market, Max Win Per Market
//        inputValue(soccerTax, cricketTax, tennisTax, basketballTax, otherTax);
//
//        // Select the checkbox corresponding with login ID
//        String chbDownlinexPath = tblTax.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, usernameCol, 1, null, chbCol, "input[@id='cItem']", false, false);
//        CheckBox chb = CheckBox.xpath(chbDownlinexPath);
//        chb.click();
//
//        //Click update
//        btnUpdate.click();
//        waitingLoadingSpinner();
//    }

//    public void inputValue(double soccerTax, double cricketTax, double tennisTax, double basketballTax, double otherTax) {
//        if (soccerTax != -1)
//            txtSoccer.sendKeys(String.format("%.2f", soccerTax));
//        if (cricketTax != -1)
//            txtCricket.sendKeys(String.format("%.2f", cricketTax));
//        if (tennisTax != -1)
//            txtTennis.sendKeys(String.format("%.2f", tennisTax));
//        if (basketballTax != -1)
//            txtBasketball.sendKeys(String.format("%.2f", basketballTax));
//      /*  if(fancyTax != -1)
//            txtFancy.sendKeys(String.format("%.2f",fancyTax));*/
//        if (otherTax != -1)
//            txtOther.sendKeys(String.format("%.2f", otherTax));
//    }

//    public boolean verifyUpdateStatus(List<ArrayList<String>> lstData, boolean isSuccess) {
//        String cell_xpath;
//        for (int i = 0; i < lstData.size(); i++) {
//            if (i % 4 == 0) {
//                cell_xpath = String.format("%s//tr[%s]//td[%s]", "//table[contains(@class,'ptable report')]", i + 1, updateStatusCol);
//                Label lblIcon;
//                if (isSuccess) {
//                    lblIcon = Label.xpath(String.format("%s%s", cell_xpath, successIcon));
//                } else {
//                    lblIcon = Label.xpath(String.format("%s%s", cell_xpath, errorIcon));
//                }
//                if (!lblIcon.isDisplayed())
//                    return false;
//            }
//        }
//        return true;
//    }


    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(1, 1);
    }
}
