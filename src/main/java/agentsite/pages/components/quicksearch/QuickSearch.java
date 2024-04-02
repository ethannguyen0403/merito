package agentsite.pages.components.quicksearch;

import agentsite.controls.MenuTree;
import agentsite.controls.Table;
import agentsite.pages.components.QuickSetting;
import agentsite.pages.components.SuccessPopup;
import agentsite.ultils.account.ProfileUtils;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static common.AGConstant.*;
import static common.AGConstant.HomePage.*;

public class QuickSearch {
    //Quick Search section
    public Button tabQuickSearch = Button.xpath("//app-left-menu//div[@class='menu-mode-container']//button[2]");
    public TextBox txtQuickSearch = TextBox.xpath("//app-left-menu//input[contains(@class,'searchinput')]");
    public Button btnSearchQuickSearch = Button.xpath("//app-left-menu//div[contains(@class,'quick-search-text')]//button[contains(@class,'my-account')]");
    public Label lblLevelQS = Label.xpath("//div[contains(@class,'quick-search-container')]//ul[contains(@class,'quick-search-users')]//li[1]//div[@class='part-left']");
    public Label lblAccountQS = Label.xpath("//div[contains(@class,'quick-search-container')]//ul[contains(@class,'quick-search-users')]//li[1]//div[@class='part-right']");
    public Label lblLevelIndrectQS = Label.xpath("//div[contains(@class,'quick-search-container')]//ul[contains(@class,'quick-search-users')]//li[2]//div[@class='part-left']");
    public Label lblAccountIndrectQS = Label.xpath("//div[contains(@class,'quick-search-container')]//ul[contains(@class,'quick-search-users')]//li[2]//div[@class='part-right']");
    public MenuTree mtQuickSearchMenu = MenuTree.xpath("//div[contains(@class,'quick-search-container')]//ul[contains(@class,'quick-search-menu')]", "//li");
    public Table tblAccountQuickSearch = Table.xpath("//app-quick-balance//table", 2);
    public Table tblLoginQuickSearch = Table.xpath("//app-quick-login//table", 2);

    public void quickSearch(String username) {
        txtQuickSearch.isDisplayedShort(2);
        txtQuickSearch.sendKeys(username);
        btnSearchQuickSearch.click();
        btnSearchQuickSearch.isInvisible(1);
    }
    public void clickDownLineListing() {
        mtQuickSearchMenu.clickMenu(DOWNLINE_LISTING);
    }

    public void clickProfitAndLoss() {
        mtQuickSearchMenu.clickMenu(PROFIT_LOSS);
    }

    public QuickSetting clickSetting() {
        mtQuickSearchMenu.clickMenu(SETTINGS);
        QuickSetting setting = new QuickSetting();
        setting.ddbAccStatus.isDisplayed();
        return setting;
    }

    public Table clickLogin() {
        mtQuickSearchMenu.clickMenu(LOGIN);
        return tblLoginQuickSearch;
    }

    public Table clickBalance() {
        mtQuickSearchMenu.clickMenu(BALANCE);
        return tblAccountQuickSearch;
    }

    public void clickClientLedger() {
        mtQuickSearchMenu.clickMenu(CLIENT_LEDGER);
    }

    public List<String> defineBalanceInfoQuickSearch(boolean isCredit) {
        if (isCredit)
            return defineBalanceInfoCreditQuickSearch();
        return defineBalanceInfoCreditCashQuickSearch();
    }

    private List<String> defineBalanceInfoCreditCashQuickSearch() {
        List<ArrayList<String>> lstDownlineInfo = ProfileUtils.getDownlineBalanceInfo();
        String level;
        List<String> lst = new LinkedList<String>(Arrays.asList(
                "Available Balance",
                "My Outstanding",
                "Total Outstanding",
                "Today Win Loss",
                "Yesterday Win Loss"
        ));
        // define downline Credit Used
        for (int i = 0; i < lstDownlineInfo.size(); i++) {
            level = lstDownlineInfo.get(i).get(0);
            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Available Balance"));
            } else {
                lst.add(String.format("Total %s Available Balance", level));
            }

        }
        // define downline Account Active/Closed/Suspended/Inactive/Blocked
        for (int i = 0; i < lstDownlineInfo.size(); i++) {
            level = lstDownlineInfo.get(i).get(0);

            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member A/C/S/I", level));
            } else
                lst.add(String.format("Total %s A/C/S/I/B", level));
        }
        return lst;
    }

    private List<String> defineBalanceInfoCreditQuickSearch() {
        List<ArrayList<String>> lstDownlineInfo = ProfileUtils.getDownlineBalanceInfo();
        String level;
        List<String> lst = new LinkedList<String>(Arrays.asList(
                "Downline Balance",
                "Yesterday Downline Balance",
                "Total Balance",
                "Transferable Balance",
                "My Outstanding",
                "Total Outstanding",
                "Today Win Loss",
                "Yesterday Win Loss",
                "My Credit"
        ));
        // define downline Credit Used
        for (int i = 0; i < lstDownlineInfo.size(); i++) {
            level = lstDownlineInfo.get(i).get(0);
            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Credit Used"));
            } else {
                lst.add(String.format("Total %s Credit Used", level));
            }
        }
        // define downline Account Active/Closed/Suspended/Inactive/Blocked
        for (int i = 0; i < lstDownlineInfo.size(); i++) {
            level = lstDownlineInfo.get(i).get(0);
            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member A/C/S/I", level));
            } else
                lst.add(String.format("Total %s A/C/S/I/B", level));
        }
        return lst;
    }

    public SuccessPopup updateStatus(String userCode, String status, boolean isClose) {
        quickSearch(userCode);
        SuccessPopup successPopup = clickSetting().updateStatus(status);
        if (isClose) {
            if (successPopup.isDisplayed())
                successPopup.close();
        }

        return successPopup;
    }

    public String updateUserProfile(String newPassword, String confirmPassword, String firstName, String lastName, String mobile, String phone, String fax, boolean isClose) {
        mtQuickSearchMenu.clickMenu(SETTINGS);
        QuickSetting setting = new QuickSetting();
        return setting.openUserProfile().updateProfile(newPassword, confirmPassword, firstName, lastName, mobile, phone, fax, isClose);
    }

    public boolean isListSubMenuDisplayCorrect() {
        List<String> lstQuickSearchMenu = mtQuickSearchMenu.getListSubMenu();
        if (!lstQuickSearchMenu.isEmpty()) {
            return lstQuickSearchMenu.equals(LST_QUICK_SEARCH_MEMBER_MENU);
        } else {
            return false;
        }
    }
}
