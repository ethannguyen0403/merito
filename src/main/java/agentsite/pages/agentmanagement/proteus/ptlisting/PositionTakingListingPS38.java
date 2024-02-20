package agentsite.pages.agentmanagement.proteus.ptlisting;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import controls.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class PositionTakingListingPS38 {

    public Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public TextBox txtUsername = TextBox.xpath("//input[contains(@class,'user-name')]");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public Button btnUpdate = Button.xpath("//div[contains(@class,'paction2')]//button[contains(@class,'pbtn')]");
    public CheckBox chkCopyAll = CheckBox.xpath("//app-filter-table//label[contains(., 'Copy')]//input");
    private String successIcon = "//span[contains(@class,'psuccess')]";
    private String errorIcon = "//span[contains(@class,'perror')]";
    protected DropDownBox ddbAccountStatus = DropDownBox.xpath("//td[text()='Account Status']//following::select[1]");
    protected DropDownBox ddbProduct = DropDownBox.xpath("//td[text()='Product']//following::select[1]");
    protected DropDownBox ddbSport = DropDownBox.xpath("//span[text()='Sport']//following::select[1]");
    public DropDownBox ddbLeague = DropDownBox.xpath("//span[text()='League']//following::select[1]");
    public Table tblSelectPT = Table.xpath("//div[@id='position-taking']//table[contains(@class,'selectionTable')]", 4);
    public Table tblPt = Table.xpath("//app-proteus-pt-table//table", 20);
    private String xpathPtTable = "//app-proteus-pt-table//table";
    private int colSetting = 1;
    private int colCheckbox = 5;

    private void waitingLoadingSpinner() {
        while(iconLoadSpinner.isDisplayed()) {
            iconLoadSpinner.waitForControlInvisible(1,1);
        }
    }

    public void verifySelectPTDropdown(String level, List<String> ddbPtList) {
        for (String lblPT : ddbPtList) {
            if (lblPT.contains("Preset") || lblPT.contains("Extra")) {
                lblPT = String.format(lblPT, level);
            }
            BaseElement ddbSetting = defineSelectPTControl(lblPT, "select");
            String lblActualDropdown =
                    defineSelectPTControl(lblPT, null).getText().trim().replace(ddbSetting.getText().trim(), "").replace("\n", "");
            Assert.assertEquals(lblActualDropdown, lblPT, "FAILED! Label of setting dropdown is not correct");
            // Extra PT setting dropdown only enable when checkbox is ticked
            if(lblPT.contains("Extra")){
                Assert.assertTrue(!ddbSetting.isEnabled(), "FAILED! Dropdown is not disable at dropdown: " + lblActualDropdown);
            }else {
                Assert.assertTrue(ddbSetting.isEnabled(), "FAILED! Dropdown is not enable at dropdown: " + lblActualDropdown);
            }
        }
    }

    public List<String> getPTAccountListByRow(String loginID, String settingValue){
        List<String> ptList = new ArrayList<>();
        int tbodyOrder = defineTbodyOrderAccount(loginID);
        int rowAccount = getRowIndexBaseOnCellValue(settingValue, tbodyOrder, colSetting);
        BaseElement rowList = new BaseElement(By.xpath(String.format("%s/tbody[%s]/tr[%s]/td",xpathPtTable , tbodyOrder, rowAccount)));
        for (WebElement lbl: rowList.getWebElements()){
            String lblEle = lbl.getText().trim();
            if(!lblEle.isEmpty()){
                if(Character.isDigit(lblEle.charAt(0))){
                    ptList.add(lblEle);
                }
            }
        }
        return ptList;
    }

    public boolean verifyUpdateStatus(String loginID, boolean isSuccess) {
        String cell_xpath = String.format("%s%s", xpathPtTable,
                String.format("//td[contains(., '%s')]/following::td", loginID));
        Label lblIcon;
        if (isSuccess) {
            lblIcon = Label.xpath(String.format("(%s%s)[1]", cell_xpath, successIcon));
        } else {
            lblIcon = Label.xpath(String.format("(%s%s)[1]", cell_xpath, errorIcon));
        }
        return lblIcon.isDisplayed();
    }

    public void updatePT(String loginID, String ptType, String amount) {
        DropDownBox targetDropdown =
                DropDownBox.xpath(defineSelectPTControl(ptType, "select").getLocator().toString().replace("By.xpath: ", ""));
        // Select the checkbox corresponding with login ID
        if (!loginID.isEmpty()) {
            int tbodyOrder = defineTbodyOrderAccount(loginID);
            CheckBox chb = CheckBox.xpath(tblPt.getControlXPathOfCell(tbodyOrder, colCheckbox, 1, "input"));
            chb.click();
        }
        if (ptType.contains("Extra PT")) {
            defineSelectPTControl(ptType, "label").click();
            targetDropdown.selectByVisibleText(amount);
        }
        targetDropdown.selectByVisibleText(amount);
        btnUpdate.click();
        waitingLoadingSpinner();
    }

    private int defineTbodyOrderAccount(String loginID) {
        Label lblAccount;
        int index = 1;
        while (true) {
            lblAccount = Label.xpath(String.format("%s//tbody[%s]", xpathPtTable, index));
            if (lblAccount.isDisplayed()) {
                if (lblAccount.getText().contains(loginID)) {
                    System.out.println(String.format("FOUND account %s at tbody: %s", loginID, index));
                    return index;
                }
                index++;
                continue;
            }
            if (!lblAccount.isDisplayed()) {
                System.out.println("NOT Found tbody index");
                return -1;
            }
        }
    }

    public int getRowIndexBaseOnCellValue(String cellValue, int tbody, int colOrder) {
        Label lblAccount;
        int rowIndex = 2;
        while (true) {
            // col Order of setting column should be 1
            lblAccount = Label.xpath(String.format("%s/tbody[%s]/tr[%s]/td[%s]", xpathPtTable, tbody, rowIndex, colOrder));
            if (lblAccount.isDisplayed()) {
                if (lblAccount.getText().contains(cellValue)) {
                    System.out.println(String.format("FOUND value %s at index: %s", cellValue, rowIndex));
                    return rowIndex;
                }
                rowIndex++;
                continue;
            }
            if (!lblAccount.isDisplayed()) {
                System.out.println("NOT Found value index");
                return -1;
            }
        }
    }

    public BaseElement defineSelectPTControl(String ptType, String subtag) {
        if (ptType.contains("Min Pos")) {
            return tblSelectPT.getControlOfCell(1, 1, 1, subtag);
        } else if (ptType.contains("Max Pos")) {
            return tblSelectPT.getControlOfCell(1, 2, 1, subtag);
        } else if (ptType.contains("Preset")) {
            return tblSelectPT.getControlOfCell(1, 3, 1, subtag);
        } else if (ptType.contains("Extra PT")) {
            return tblSelectPT.getControlOfCell(1, 4, 1, subtag);
        } else {
            return null;
        }
    }

    public void search(String username, String accountStatus, String product, String sport, String league) {
        if (!username.isEmpty()) {
            txtUsername.sendKeys(username);
        }
        if (!product.isEmpty()) {
            ddbProduct.selectByVisibleText(product);
            waitingLoadingSpinner();
        }
        if (!accountStatus.isEmpty())
            ddbAccountStatus.selectByVisibleText(accountStatus);
        if (!sport.isEmpty()) {
            // handle for options list dropdown not load
            BaseElement dropdown = new BaseElement(ddbSport.getLocator());
            dropdown.click();
            dropdown.click();
            waitingLoadingSpinner();
            ddbSport.selectByVisibleText(sport);
        }
        if (!league.isEmpty()) {
            try {
                ddbLeague.selectByVisibleText(league);
                waitingLoadingSpinner();
            } catch (Exception e) {
                System.out.println("Select League by Index: " + league);
                ddbLeague.selectByIndex(Integer.valueOf(league));
            }
        }
        btnSearch.click();
        waitingLoadingSpinner();
    }
}
