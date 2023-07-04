package backoffice.pages.bo.accountmanagement;

import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.accountmanagement.component.UplineStatusPopup;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;
import com.paltech.utils.DateUtils;
import com.paltech.utils.DoubleUtils;

import java.util.List;

public class PlayerInfoPage extends HomePage {
    public TextBox txtPlayer = TextBox.xpath("//input[@placeholder='User Code/Username/Login ID']");
    public Button btnView = Button.xpath("//button[contains(@class,'btn-default')]");
    public int colHeader = 1;
    public int colData = 2;
    private int totalColumn = 4;
    public StaticTable tblPlayerInfo = StaticTable.xpath("//div[contains(@class,'table-user-info')]", "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalColumn);
    // public Table tblPlayerInfo = Table.xpath("//div[contains(@class,'table-user-info')]",totalColumn);
    private int loginIDIndex = 0;
    private int userCodeIndex = 1;
    private int loginByEmailIndex = 2;
    private int liveDealerEropeanUsercode = 3;
    private int liveDealerAsianUsercode = 4;
    private int currency = 5;
    private int balance = 6;
    private int exposure = 7;
    private int retainTax = 8;
    private int status = 9;
    private int brand = 10;
    private int brandType = 11;
    private int uplineUserCode = 12;
    private int uplineLoginID = 13;
    private int userType = 14;
    private int uplineStatus = 15;
    private int userGroup = 16;
    private int shadowplayer = 17;
    private int ptPlayer = 18;
    private int cashout = 19;
    private int followbet = 20;
    private int oneClickEnabled = 21;
    private int oneClickStake = 22;
    private int betDelay = 23;
    private int requiredConfirmBet = 24;
    private int liquidityThreshold = 25;
    private int refreshOddsFrequency = 26;
    private int skipPT = 27;
    private int lastLoginIP = 28;
    private int lastLoginDate = 29;
    private int product = 30;
    private int createDate = 31;


    public void viewPlayer(String player) {
        txtPlayer.sendKeys(player);
        btnView.click();
    }

    public UplineStatusPopup showUpline(String player) {
        String xPathUplineStatus = tblPlayerInfo.getXPathControlBasedValueOfDifferentColumnOnRow("Upline Status", 1, 1, 1, null, 2, "i[contains@class='fa-info-circle']", false, false);
        Button viewUpline = Button.xpath(xPathUplineStatus);
        viewUpline.click();
        return new UplineStatusPopup();
    }

    public boolean verifyInfo(List<String> actualInfo, List<String> expectedInfo) {
        String iconFalse = "i[@class='fas fa-times text-danger']";
        String iconTrue = "i[@class='fas fa-check text-success']";
        if (!expectedInfo.get(loginIDIndex).equals(actualInfo.get(loginIDIndex))) {
            System.out.println(String.format("Expected Login ID is %s but found %s", expectedInfo.get(loginIDIndex), actualInfo.get(loginIDIndex)));
            return false;
        }

        if (!expectedInfo.get(userCodeIndex).equals(actualInfo.get(userCodeIndex))) {
            System.out.println(String.format("Expected User Code is %s but found %s", expectedInfo.get(userCodeIndex), actualInfo.get(userCodeIndex)));
            return false;
        }
        if (expectedInfo.get(loginByEmailIndex).equalsIgnoreCase("false")) {
            if (!tblPlayerInfo.getControlOfCell(1, 1, 3, iconFalse).isDisplayed()) {
                System.out.println(String.format("Expected Login By Email is %s but found %s", expectedInfo.get(loginByEmailIndex),
                        tblPlayerInfo.getControlOfCell(1, 1, 3, iconFalse).isDisplayed()));
                return false;
            }
        } else {
            if (!tblPlayerInfo.getControlOfCell(1, 1, 3, iconTrue).isDisplayed()) {
                System.out.println(String.format("Expected Login By Email is %s but found %s", expectedInfo.get(loginByEmailIndex),
                        tblPlayerInfo.getControlOfCell(1, 1, 3, iconTrue).isDisplayed()));
                return false;
            }
        }

        if (!expectedInfo.get(liveDealerEropeanUsercode).equals(actualInfo.get(liveDealerEropeanUsercode))) {
            System.out.println(String.format("Expected Live Dealer European  is %s but found %s", expectedInfo.get(liveDealerEropeanUsercode), actualInfo.get(liveDealerEropeanUsercode)));
            return false;
        }
        if (!expectedInfo.get(liveDealerAsianUsercode).equals(actualInfo.get(liveDealerAsianUsercode))) {
            System.out.println(String.format("Expected Live Dealer Asian is %s but found %s", expectedInfo.get(liveDealerAsianUsercode), actualInfo.get(liveDealerAsianUsercode)));
            return false;
        }
        if (!expectedInfo.get(currency).equals(actualInfo.get(currency))) {
            System.out.println(String.format("Expected Currency is %s but found %s", expectedInfo.get(currency), actualInfo.get(currency)));
            return false;
        }
        String expectedBalance = String.format("%.2f", DoubleUtils.roundUpWithTwoPlaces(Double.parseDouble(expectedInfo.get(balance))));
        if (!expectedBalance.equals(actualInfo.get(balance))) {
            System.out.println(String.format("Expected Balance is %s but found %s", expectedInfo.get(balance), actualInfo.get(balance)));
            return false;
        }
        if (!(Double.parseDouble(expectedInfo.get(exposure)) == Double.parseDouble(actualInfo.get(exposure)))) {
            System.out.println(String.format("Expected Exposure is %s but found %s", expectedInfo.get(exposure), actualInfo.get(exposure)));
            return false;
        }
        if (!(Double.parseDouble(expectedInfo.get(retainTax)) == Double.parseDouble(actualInfo.get(retainTax)))) {
            System.out.println(String.format("Expected Retain Tax is %s but found %s", expectedInfo.get(retainTax), actualInfo.get(retainTax)));
            return false;
        }
        if (!expectedInfo.get(status).equalsIgnoreCase(actualInfo.get(status))) {
            System.out.println(String.format("Expected Status is %s but found %s", expectedInfo.get(status), actualInfo.get(status)));
            return false;
        }
        if (!expectedInfo.get(brand).equals(actualInfo.get(brand))) {
            System.out.println(String.format("Expected Brand is %s but found %s", expectedInfo.get(brand), actualInfo.get(brand)));
            return false;
        }
        String expectedBrandType = expectedInfo.get(brandType).equals("NORMAL") ? "PT" : "NoPT";
        if (!expectedBrandType.equals(actualInfo.get(brandType))) {
            System.out.println(String.format("Expected Brand Type is %s but found %s", expectedInfo.get(brandType), actualInfo.get(brandType)));
            return false;
        }
        if (!expectedInfo.get(uplineUserCode).equals(actualInfo.get(uplineUserCode))) {
            System.out.println(String.format("Expected Upline User Code is %s but found %s", expectedInfo.get(uplineUserCode), actualInfo.get(uplineUserCode)));
            return false;
        }
        if (!expectedInfo.get(uplineLoginID).equals(actualInfo.get(uplineLoginID))) {
            System.out.println(String.format("Expected Upline Login ID is %s but found %s", expectedInfo.get(uplineLoginID), actualInfo.get(uplineLoginID)));
            return false;
        }
        String expectedUserType = expectedInfo.get(userType).equals("CREDIT") ? "Credit" : "Credit Cash";
        if (!expectedUserType.equals(actualInfo.get(userType))) {
            System.out.println(String.format("Expected User Type is %s but found %s", expectedInfo.get(userType), actualInfo.get(userType)));
            return false;
        }
        if (!expectedInfo.get(uplineStatus).equals(actualInfo.get(uplineStatus))) {
            System.out.println(String.format("Expected Upline Status is %s but found %s", expectedInfo.get(uplineStatus), actualInfo.get(uplineStatus)));
            return false;
        }
        if (!expectedInfo.get(userGroup).equals(actualInfo.get(userGroup))) {
            System.out.println(String.format("Expected User Group is %s but found %s", expectedInfo.get(userGroup), actualInfo.get(userGroup)));
            return false;
        }
        if (expectedInfo.get(shadowplayer).equalsIgnoreCase("false")) {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 2, iconFalse).isDisplayed()) {
                System.out.println(String.format("Expected Shadown Player is %s but found %s", expectedInfo.get(shadowplayer),
                        tblPlayerInfo.getControlOfCell(1, 2, 2, iconFalse).isDisplayed()));
                return false;
            }
        } else {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 2, iconTrue).isDisplayed()) {
                System.out.println(String.format("Expected Shadown Player is %s but found %s", expectedInfo.get(shadowplayer),
                        tblPlayerInfo.getControlOfCell(1, 2, 2, iconTrue).isDisplayed()));
                return false;
            }
        }
        if (expectedInfo.get(ptPlayer).equalsIgnoreCase("false")) {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 3, iconFalse).isDisplayed()) {
                System.out.println(String.format("Expected PT Player is %s but found %s", expectedInfo.get(ptPlayer),
                        tblPlayerInfo.getControlOfCell(1, 2, 3, iconFalse).isDisplayed()));
                return false;
            }
        } else {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 3, iconTrue).isDisplayed()) {
                System.out.println(String.format("Expected PT Player is %s but found %s", expectedInfo.get(ptPlayer),
                        tblPlayerInfo.getControlOfCell(1, 2, 3, iconTrue).isDisplayed()));
                return false;
            }
        }
        if (expectedInfo.get(cashout).equalsIgnoreCase("false")) {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 4, iconFalse).isDisplayed()) {
                System.out.println(String.format("Expected CashOut is %s but found %s", expectedInfo.get(cashout),
                        tblPlayerInfo.getControlOfCell(1, 2, 4, iconFalse).isDisplayed()));
                return false;
            }
        } else {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 4, iconTrue).isDisplayed()) {
                System.out.println(String.format("Expected Cash Out is %s but found %s", expectedInfo.get(cashout),
                        tblPlayerInfo.getControlOfCell(1, 2, 4, iconTrue).isDisplayed()));
                return false;
            }
        }
        if (expectedInfo.get(followbet).equalsIgnoreCase("false")) {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 5, iconFalse).isDisplayed()) {
                System.out.println(String.format("Expected Follow Bet is %s but found %s", expectedInfo.get(followbet),
                        tblPlayerInfo.getControlOfCell(1, 2, 5, iconFalse).isDisplayed()));
                return false;
            }
        } else {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 5, iconTrue).isDisplayed()) {
                System.out.println(String.format("Expected Follow Bet is %s but found %s", expectedInfo.get(followbet),
                        tblPlayerInfo.getControlOfCell(1, 2, 5, iconTrue).isDisplayed()));
                return false;
            }
        }
        if (expectedInfo.get(oneClickEnabled).equalsIgnoreCase("false")) {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 6, iconFalse).isDisplayed()) {
                System.out.println(String.format("Expected One Click Enabled is %s but found %s", expectedInfo.get(oneClickEnabled),
                        tblPlayerInfo.getControlOfCell(1, 2, 6, iconFalse).isDisplayed()));
                return false;
            }
        } else {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 6, iconTrue).isDisplayed()) {
                System.out.println(String.format("Expected One Click Enabled is %s but found %s", expectedInfo.get(oneClickEnabled),
                        tblPlayerInfo.getControlOfCell(1, 2, 6, iconTrue).isDisplayed()));
                return false;
            }
        }
        if (!String.format("%s %s", expectedInfo.get(oneClickStake), expectedInfo.get(currency)).equals(actualInfo.get(oneClickStake))) {
            System.out.println(String.format("Expected Product is %s but found %s", expectedInfo.get(oneClickStake), actualInfo.get(oneClickStake)));
            return false;
        }
        if (!String.format("%s %s", expectedInfo.get(betDelay), "second(s)").equals(actualInfo.get(betDelay))) {
            System.out.println(String.format("Expected Product is %s but found %s", expectedInfo.get(betDelay) + " second(s)", actualInfo.get(betDelay)));
            return false;
        }
        if (expectedInfo.get(requiredConfirmBet).equalsIgnoreCase("false")) {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 9, iconFalse).isDisplayed()) {
                System.out.println(String.format("Expected Required Confirm Bet is %s but found %s", expectedInfo.get(requiredConfirmBet),
                        tblPlayerInfo.getControlOfCell(1, 2, 9, iconFalse).isDisplayed()));
                return false;
            }
        } else {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 9, iconTrue).isDisplayed()) {
                System.out.println(String.format("Expected Required Confirm Bet is %s but found %s", expectedInfo.get(requiredConfirmBet),
                        tblPlayerInfo.getControlOfCell(1, 2, 9, iconTrue).isDisplayed()));
                return false;
            }
        }
        if (expectedInfo.get(liquidityThreshold).equalsIgnoreCase("false")) {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 10, iconFalse).isDisplayed()) {
                System.out.println(String.format("Expected Liquidity Threshold is %s but found %s", expectedInfo.get(liquidityThreshold),
                        tblPlayerInfo.getControlOfCell(1, 2, 10, iconFalse).isDisplayed()));
                return false;
            }
        } else {
            if (!tblPlayerInfo.getControlOfCell(1, 2, 10, iconTrue).isDisplayed()) {
                System.out.println(String.format("Expected Liquidity Threshold is %s but found %s", expectedInfo.get(liquidityThreshold),
                        tblPlayerInfo.getControlOfCell(1, 2, 10, iconTrue).isDisplayed()));
                return false;
            }
        }
        if (!String.format("%s %s", expectedInfo.get(refreshOddsFrequency), "request/ 1 second(s)").equals(actualInfo.get(refreshOddsFrequency))) {
            System.out.println(String.format("Expected Product is %s but found %s", String.format("%s %s", expectedInfo.get(refreshOddsFrequency), "request/ 1 second(s)"), actualInfo.get(refreshOddsFrequency)));
            return false;
        }
        if (!expectedInfo.get(skipPT).equals(actualInfo.get(skipPT))) {
            System.out.println(String.format("Expected Product is %s but found %s", expectedInfo.get(skipPT), actualInfo.get(skipPT)));
            return false;
        }
        if (!expectedInfo.get(lastLoginIP).equals(actualInfo.get(lastLoginIP))) {
            System.out.println(String.format("Expected Product is %s but found %s", expectedInfo.get(lastLoginIP), actualInfo.get(lastLoginIP)));
            return false;
        }
        String lastLoginDateValue = DateUtils.convertMillisToDateTime(expectedInfo.get(lastLoginDate), "yyyy-MM-dd HH:mm:ss");
        if (!lastLoginDateValue.equals(actualInfo.get(lastLoginDate))) {
            System.out.println(String.format("Expected Last Login Date is %s but found %s", lastLoginDateValue, actualInfo.get(lastLoginDate)));
            return false;
        }
        if (!expectedInfo.get(product).equals(actualInfo.get(product))) {
            System.out.println(String.format("Expected Product is %s but found %s", expectedInfo.get(product), actualInfo.get(product)));
            return false;
        }
        String createDateValue = DateUtils.convertMillisToDateTime(expectedInfo.get(createDate), "yyyy-MM-dd HH:mm:ss");
        if (!createDateValue.equals(actualInfo.get(createDate))) {
            System.out.println(String.format("Expected Create Date is %s but found %s", createDateValue, actualInfo.get(createDate)));
            return false;
        }
        System.out.println("Data is correctly displayed");
        return true;
    }

}
