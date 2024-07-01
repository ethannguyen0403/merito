package agentsite.pages.agentmanagement.taxsettinglisting;

import agentsite.controls.Table;
import com.paltech.element.common.*;
import java.util.ArrayList;
import java.util.List;

public class TaxSettingListing {
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    protected int totalColum = 15;
    public int usernameCol = 2;
    public int loginIDCol = 3;
    protected int chbCol = 5;
    public int soccerCol = 7;
    public int cricketCol = 8;
    public int tennisCol = 9;
    public int basketballCol = 10;
    protected String successIcon = "//span[contains(@class,'psuccess')]";
    protected String errorIcon = "//span[contains(@class,'perror')]";
    public Label lblBreadcrumb = Label.xpath("//span[@class='my-breadcrumb']");
    public TextBox txtUsername = TextBox.id("memberCode");
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("//label[text()='Account Status']//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//label[text()='Product']//following::select[1]");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public TextBox txtSoccer = TextBox.xpath("//input[@id='backSOCCER']");
    public TextBox txtTennis = TextBox.xpath("//input[@id='backTENNIS']");
    public TextBox txtCricket = TextBox.xpath("//input[@id='backCRICKET']");
    public TextBox txtBasketball = TextBox.xpath("//input[@id='backBASKETBALL']");
    public TextBox txtFancy = TextBox.xpath("//input[@id='backFANCY']");
    public TextBox txtDecimalCricket = TextBox.xpath("//input[@id='backDECIMAL']");
    public TextBox txtVirtualCricket = TextBox.xpath("//input[@id='backKIRON']");
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

    public void updateTaxSetting(String loginID, List<ArrayList<String>> lstData) {

    }

    public void inputValue(List<ArrayList<String>> lstData) {
    }

    public boolean verifyUpdateStatus(List<ArrayList<String>> lstData, boolean isSuccess) {
        return false;
    }

    public List<ArrayList<String>> defineListTaxSetting(double inputValue) { return null;}

    public void verifyUITaxSetting(String userCode) {}

    public List<String> getListLoginId() {
        return null;
    }
}
