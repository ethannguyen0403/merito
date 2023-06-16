package agentsite.pages.agentmanagement;

import common.AGConstant;
import agentsite.controls.Cell;
import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PositionTakingListingPage extends HomePage {
    public TextBox txtUsername = TextBox.xpath("//input[contains(@class,'user-name')]");
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("//td[text()='Account Status']//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//td[text()='Product']//following::select[1]");
    public DropDownBox ddbLevel = DropDownBox.xpath("//span[text()='Level']//following::select[1]");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    public int totalColum = 16;
    public int usernameCol = 2;
    public int loginIDCol = 3;
    public int levelCol = 6;
    public int chbCol = 5;
    public int soccerCol = 8;
    public int cricketCol = 9;
    public int fancytCol = 10;
    public int tennisCol = 11;
    public int basketballCol = 12;
    public int horseRacingCol = 13;
    public int greyhoundCol = 14;
    public int otherCol = 15;
    public int updateStatusCol = 16;
    public Table tblDownline = Table.xpath("//app-exchange-pt-table//table[contains(@class,'directDownline table-responsive')]", totalColum);
    public DropDownBox ddbLevelPreset = DropDownBox.xpath("//table[contains(@class,'selectionTable')]//td[contains(text(),'Preset')]/select");
    private Label iconLoadSpinner = Label.xpath("//div[contains(@class,'la-ball-clip-rotate')]");
    public Label lblUsername = Label.xpath(String.format("//td[text()='%s']", AGConstant.AgencyManagement.PositionTakingListing.LBL_USERNAME));
    public Label lblAccountStatus = Label.xpath(String.format("//td[text()='%s']", AGConstant.AgencyManagement.PositionTakingListing.LBL_ACCOUNTSTATUS));
    public Label lblLevel = Label.xpath(String.format("//span[text()='%s']", AGConstant.AgencyManagement.PositionTakingListing.LBL_LEVEL));
    public Label lblProduct = Label.xpath(String.format("//td[text()='%s']", AGConstant.AgencyManagement.PositionTakingListing.LBL_PRODUCT));
    public Label lblBreadcrumb = Label.xpath("//span[@class='downline']");
    public Button btnUpdate = Button.xpath("//div[contains(@class,'paction2')]//button[contains(@class,'pbtn')]");
    private String xPathSport = "//span[contains(text(),'%s')]/preceding::input[1]";
    private String successIcon = "//span[contains(@class,'psuccess')]";
    private String errorIcon = "//span[contains(@class,'perror')]";
    public Label lblNoRecord = Label.xpath("//td[@class='no-record']");
    public CheckBox cbAll = CheckBox.xpath("//span[contains(text(),'Select All')]/preceding::input[1]");
    public CheckBox cbSoccer = CheckBox.xpath("//input[@id='SOCCER']");
    public CheckBox cbCricket = CheckBox.xpath("//input[@id='CRICKET']");
    public CheckBox cbFancy = CheckBox.xpath("//input[@id='FANCY']");
    public CheckBox cbTennis = CheckBox.xpath("//input[@id='TENNIS']");
    public CheckBox cbBasketball = CheckBox.xpath("//input[@id='BASKETBALL']");
    public CheckBox cbHorseRacing = CheckBox.xpath("//input[@id='HORSE']");
    public CheckBox cbGreyhoundRacing = CheckBox.xpath("//input[@id='GREYHOUND']");
    public CheckBox cbOther = CheckBox.xpath("//input[@id='OTHER']");
    public PositionTakingListingPage(String types) {
        super(types);
    }
    private CheckBox defineCheckbox(String sport) {
        switch (sport) {
            case "Soccer":
                return cbSoccer;
            case "Cricket":
                return cbCricket;
            case "Tennis":
                return cbTennis;
            case "Fancy":
                return cbFancy;
            case "Baseketball":
                return cbBasketball;
            case "Horse Racing":
                return cbHorseRacing;
            case "Greyhound Racing":
                return cbFancy;
            case "Other":
                return cbOther;
            default:
                return cbAll;
        }
    }

    public void checkCheckbox(String sport) {
        CheckBox cb = defineCheckbox(sport);
        if (!cb.isSelected())
            cb.click();
    }

    public void unCheckCheckbox(String sport) {
        CheckBox cb = defineCheckbox(sport);
        if (cb.isSelected())
            cb.click();
    }

    public boolean isCheckboxChecked(String sport) {
        return defineCheckbox(sport).isSelected();
    }

    public void search(String username, String accountStatus, String product, String level) {
        if (!username.isEmpty())
            txtUsername.sendKeys(username);
        if (!accountStatus.isEmpty())
            ddbAccountStatus.selectByVisibleText(accountStatus);
        if (!product.isEmpty())
            ddbProduct.selectByVisibleText(product);
        if (!level.isEmpty())
            ddbLevel.selectByVisibleText(level);
        btnSearch.click();
        waitingLoadingSpinner();
    }

    public void updatePT(String loginID, int PT, HashMap<String, Boolean> map) {
        if (Objects.nonNull(map)) {
            // Select sport to update PT
            enableSport(map);
        }
        // Select the checkbox corresponding with login ID
        if (!loginID.isEmpty()) {
            String chbDownlinexPath = tblDownline.getControlxPathBasedValueOfDifferentColumnOnRow(loginID, 1, usernameCol, 1, null, chbCol, "input", false, false);
            CheckBox chb = CheckBox.xpath(chbDownlinexPath);
            chb.click();
        }

        // Select SAD Preset value
        ddbLevelPreset.isDisplayed(1);
        ddbLevelPreset.selectByVisibleText(Integer.toString(PT));

        //Click update
        btnUpdate.click();
        waitingLoadingSpinner();

    }

    public ArrayList<String> getPTofAccount(String loginID)
    {
        ArrayList<String> info =  new ArrayList<String>();
        int rowindex = getRowIndexofAccount(loginID);
        if(rowindex == -1) {
            System.out.println(String.format("There is no account % in the list", loginID));
            return info;
        }
        String cell_xpath = String.format("%s%s//td", tblDownline.getLocator().toString().replace("By.xpath: ",""),String.format("//tbody[%s]",rowindex + 1));

        for (int i=1; i <= totalColum + 1; i++){
            Cell cell = Cell.xpath(String.format("(%s)[%s]",cell_xpath, i));
            if(!cell.isDisplayed(timeOutShortInSeconds)){
                return info;
            }
            info.add(cell.getText(1));
        }
        return info;
    }

    public boolean verifyUpdateStatus(String loginID,boolean isSuccess)
    {
        int rowindex = getRowIndexofAccount(loginID);
        if(rowindex == -1) {
            System.out.println(String.format("There is no account % in the list", loginID));
            return false;
        }
        String cell_xpath = String.format("%s%s//td", tblDownline.getLocator().toString().replace("By.xpath: ", ""), String.format("//tbody[%s]", rowindex + 1));
        Label lblIcon;
        if (isSuccess) {
            lblIcon = Label.xpath(String.format("%s%s", cell_xpath, successIcon));
        } else
            lblIcon = Label.xpath(String.format("%s%s", cell_xpath, errorIcon));
        return lblIcon.isDisplayed();
    }

    public List<ArrayList<String>> getPTData() {
        String tableXpath = "//app-exchange-pt-table//table[contains(@class,'directDownline table-responsive')]/tbody[%s]";
        int i = 1;
        Table tbl = Table.xpath(String.format(tableXpath, i), totalColum);
        while (true) {
            if (!tbl.isDisplayed()) {
                return null;
            }
            tbl.getRows(false);
        }
    }


    public int getRowIndexofAccount(String loginID) {
        List<String> usernameLst = tblDownline.getColumn(usernameCol, false);
        for (int i = 0, n = usernameLst.size(); i < n; i++) {
            if (usernameLst.get(i).trim().equalsIgnoreCase(loginID))
                return i;
        }
        return -1;
    }
    public void enableSport(HashMap<String, Boolean> map)
    {
        CheckBox chb =CheckBox.xpath(String.format(xPathSport,"Soccer"));
        if(!map.get("Soccer"))
        {
            chb.click();
        }
        chb =CheckBox.xpath(String.format(xPathSport,"Cricket"));
        if(!map.get("Cricket"))
        {
            chb.click();
        }
        chb =CheckBox.xpath(String.format(xPathSport,"Fancy"));
        if(!map.get("Fancy"))
        {
            chb.click();
        }
        chb =CheckBox.xpath(String.format(xPathSport,"Tennis"));
        if(!map.get("Tennis"))
        {
            chb.click();
        }
        chb =CheckBox.xpath(String.format(xPathSport,"Basketball"));
        if(!map.get("Basketball"))
        {
            chb.click();
        }
        chb =CheckBox.xpath(String.format(xPathSport,"Horse Racing"));
        if(!map.get("Horse Racing"))
        {
            chb.click();
        }
        chb =CheckBox.xpath(String.format(xPathSport,"Other"));
        if(!map.get("Other"))
        {
            chb.click();
        }
    }

    public void waitingLoadingSpinner()
    {
        iconLoadSpinner.waitForControlInvisible(1,1);
    }
}
