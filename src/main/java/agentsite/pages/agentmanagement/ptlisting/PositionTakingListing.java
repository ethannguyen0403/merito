package agentsite.pages.agentmanagement.ptlisting;

import agentsite.controls.Cell;
import agentsite.controls.Table;
import com.paltech.element.common.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PositionTakingListing {
    public int totalColum = 32;
    public int usernameCol = 2;
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
    public Button btnUpdate = Button.xpath("//div[contains(@class,'paction2')]//button[contains(@class,'pbtn')]");
    public TextBox txtUsername = TextBox.xpath("//input[contains(@class,'user-name')]");
    public DropDownBox ddbAccountStatus = DropDownBox.xpath("//td[text()='Account Status']//following::select[1]");
    public DropDownBox ddbProduct = DropDownBox.xpath("//td[text()='Product']//following::select[1]");
    public DropDownBox ddbLevel = DropDownBox.xpath("//span[text()='Level']//following::select[1]");
    public Button btnSearch = Button.xpath("//button[@class='pbtn search']");
    private String xPathSport = "//span[contains(text(),'%s')]/preceding::input[1]";
    Icon iconLoadSpinner = Icon.xpath("//div[contains(@class, 'la-ball-clip-rotate')]");
    public Table tblDownline = Table.xpath("//app-agency-position-taking//table[contains(@class,'directDownline table-responsive')]", totalColum);
    private String successIcon = "//span[contains(@class,'psuccess')]";
    private String errorIcon = "//span[contains(@class,'perror')]";
    public CheckBox cbAll = CheckBox.xpath("//span[contains(text(),'Select All')]/preceding::input[1]");
    public CheckBox cbSoccer = CheckBox.xpath("//input[@id='SOCCER']");
    public CheckBox cbCricket = CheckBox.xpath("//input[@id='CRICKET']");
    public CheckBox cbFancy = CheckBox.xpath("//input[@id='FANCY']");
    public CheckBox cbTennis = CheckBox.xpath("//input[@id='TENNIS']");
    public CheckBox cbBasketball = CheckBox.xpath("//input[@id='BASKETBALL']");
    public CheckBox cbHorseRacing = CheckBox.xpath("//input[@id='HORSE']");
    public CheckBox cbGreyhoundRacing = CheckBox.xpath("//input[@id='GREYHOUND']");
    public CheckBox cbOther = CheckBox.xpath("//input[@id='OTHER']");

    public void waitingLoadingSpinner() {
        iconLoadSpinner.waitForControlInvisible(2, 2);
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
    public ArrayList<String> getPTofAccount(String loginID) {
        ArrayList<String> info = new ArrayList<String>();
        int rowindex = getRowIndexofAccount(loginID);
        if (rowindex == -1) {
            System.out.println(String.format("There is no account % in the list", loginID));
            return info;
        }
        String cell_xpath = String.format("%s%s//td", tblDownline.getLocator().toString().replace("By.xpath: ", ""), String.format("//tbody[%s]", rowindex + 1));

        for (int i = 1; i <= totalColum + 1; i++) {
            Cell cell = Cell.xpath(String.format("(%s)[%s]", cell_xpath, i));
            if (!cell.isDisplayed(3)) {
                return info;
            }
            info.add(cell.getText(1));
        }
        return info;
    }

    public boolean verifyUpdateStatus(String loginID, boolean isSuccess) {
        int rowindex = getRowIndexofAccount(loginID);
        if (rowindex == -1) {
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

    public int getRowIndexofAccount(String loginID) {
        List<String> usernameLst = tblDownline.getColumn(usernameCol, false);
        for (int i = 0, n = usernameLst.size(); i < n; i++) {
            if (usernameLst.get(i).trim().equalsIgnoreCase(loginID))
                return i;
        }
        return -1;
    }

    public void updatePT(String loginID, int PT, HashMap<String, Boolean> map) {
    }

    public void enableSport(HashMap<String, Boolean> map) {
        CheckBox chb = CheckBox.xpath(String.format(xPathSport, "Soccer"));
        if (!map.get("Soccer")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Cricket"));
        if (!map.get("Cricket")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Fancy"));
        if (!map.get("Fancy")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Tennis"));
        if (!map.get("Tennis")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Basketball"));
        if (!map.get("Basketball")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Horse Racing"));
        if (!map.get("Horse Racing")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Greyhound Racing"));
        if (!map.get("Greyhound Racing")) {
            chb.click();
        }
        chb = CheckBox.xpath(String.format(xPathSport, "Other"));
        if (!map.get("Other")) {
            chb.click();
        }
    }

    public List<String> definePTSettingList(String member, int inputValue) {
        List<String> lstPTSetting = getPTofAccount(member);
        lstPTSetting.set(soccerCol - 1, Integer.toString(inputValue));
        lstPTSetting.set(cricketCol - 1, Integer.toString(inputValue));
        lstPTSetting.set(fancytCol - 1, Integer.toString(inputValue));
        lstPTSetting.set(tennisCol - 1, Integer.toString(inputValue));
        lstPTSetting.set(basketballCol - 1, Integer.toString(inputValue));
        lstPTSetting.set(horseRacingCol - 1, Integer.toString(inputValue));
        lstPTSetting.set(greyhoundCol - 1, Integer.toString(inputValue));
        lstPTSetting.set(otherCol - 1, Integer.toString(inputValue));
        return lstPTSetting;
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
                return cbGreyhoundRacing;
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

    public boolean isTableHeaderProductDisplayCorrect(String product) {
        return false;
    }
}
