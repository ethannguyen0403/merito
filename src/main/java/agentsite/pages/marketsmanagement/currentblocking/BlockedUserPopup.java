package agentsite.pages.marketsmanagement.currentblocking;

import agentsite.controls.Table;
import agentsite.pages.marketsmanagement.blockunblockevents.UnblockSchedulePopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import common.AGConstant;

import java.util.List;

public class BlockedUserPopup {
    public Label lblTitle = Label.xpath("//span[@class='title']");
    public Label lblCompetitionName = Label.xpath("//div[contains(@class,'modal-body')]//div[contains(@class,'extra-padding')][1]");
    // public Label lblEventName = Label.xpath("//div[contains(@class,'modal-body')]//div[contains(@class,'extra-padding')][2]");
    public Label lblEvent = Label.xpath("//div[contains(@class,'modal-body')]//div[contains(@class,'extra-padding')][2]");
    //    public Label lblEventName = Label.xpath("//div[contains(@class,'extra-padding')][2]//span");
//    public Label lblEvent = Label.xpath("//div[contains(@class,'extra-padding')][2]");
    public Button btnUnBlockNow = Button.xpath("//div[@class='unblock-actions']//button[contains(@class,'pbtn')][1]");
    public Button btnUnBlockNowCompetition = Button.xpath("//button[contains(@class,'pbtn mr-1')]");
    public Button btnUnblockSchedule = Button.xpath("//div[@class='unblock-actions']//button[contains(@class,'pbtn')][2]");
    public TextBox txtMarketName = TextBox.xpath("//input[contains(@class,'input-market-name')]");
    public Button btnClose = Button.xpath("//button[@class='pbtn close-btn']");
    public int colLoginID = 1;
    public int colLevel = 2;
    public int colUpline = 3;
    public int colBlockedBy = 4;
    public int colBlockedDate = 5;
    public int colSelect = 6;
    private int totalCol = 6;
    public Table tblBlockedUser = Table.xpath("//table[@class='table ptable report table-fixed']", totalCol);
    public Table tblHeader = Table.xpath("//table[@class='table ptable report table-fixed mb-0']", totalCol);

    public boolean unblockUser(String loginId) {
        //1. Select login ID
        if (selectLoginID(loginId)) {
            //2 Click Unblock Now
            btnUnBlockNow.click();
            return true;
        }
        return false;
    }

    public boolean unblockCompeition(String loginId) {
        //1. Select login ID
        if (selectLoginID(loginId)) {
            //2 Click Unblock Now
            btnUnBlockNowCompetition.click();

            return true;
        }
        return false;
    }

    public boolean selectLoginID(String loginID) {
        List<String> lstEvent = tblBlockedUser.getColumn(colLoginID, true);
        CheckBox chk;
        boolean flag = false;
        for (int i = 0; i < lstEvent.size(); i++) {
            if (lstEvent.get(i).contains(loginID)) {
                flag = true;
                String blockedBy = tblBlockedUser.getControlOfCell(1, colBlockedBy, i + 1, null).getText();
                if (!blockedBy.equalsIgnoreCase("COMPETITION")) {
                    System.out.println(String.format("The event is blocked by %s", blockedBy));
                    chk = CheckBox.xpath(tblBlockedUser.getxPathOfCell(1, colSelect, i + 1, "i[contains(@class,'far fa-square')]"));
                    chk.click();
                    return true;
                } else {
                    System.out.println(String.format(" Cannot Unblocked the event as it is blocked by %s", blockedBy));
                    return false;
                }
            }
        }
        if (flag) {
            System.out.println(String.format("Found Login ID %s", loginID));
            return true;
        }
        System.out.println(String.format("Login ID: %s not found", loginID));
        return false;
    }

    public boolean unblockSchedule(String loginId, String time) {
        //1. Select login ID
        if (selectLoginID(loginId)) {
            //2 Click Unblock Schedule
            btnUnblockSchedule.click();
            UnblockSchedulePopup popup = new UnblockSchedulePopup();
            popup.unblockSchedule(time);
            return true;
        }
        System.out.println(String.format("Cannot select the user %s", loginId));
        return false;
    }

    public boolean verifyUserIsUnblock(String loginID) {
        List<String> lstBlocked = tblBlockedUser.getColumn(colLoginID, false);
        for (int i = 0, n = lstBlocked.size(); i < n; i++) {
            if (lstBlocked.get(i).equalsIgnoreCase(loginID)) {
                System.out.println(String.format("Login ID %s display in the Blocked list", loginID));
                return false;
            }

        }
        System.out.println(String.format("Login ID %s NOT display in the Blocked list", loginID));
        return true;
    }

    public String getCurrentBlockingNumber() {
        /*int r = Row.xpath("//table[@class='table ptable report table-fixed']//tbody/tr").getWebElements().size();
        return Integer.toString(r);*/
        int items = Label.xpath("//table[@class='table ptable report table-fixed']//tbody/tr").getWebElements().size();
        if (items == 1) {
            List<String> lstBlocked = tblBlockedUser.getColumn(colLoginID, true);
            if (lstBlocked.get(0).equalsIgnoreCase(AGConstant.MarketsManagement.BlockedUserPopup.LBL_NO_USER_BLOCKED))
                return "";
            return Integer.toString(lstBlocked.size());
        }
//        List<String> lstBlocked = tblBlockedUser.getColumn(colLoginID, true);
//        if (lstBlocked.size() == 1) {
//            if (lstBlocked.get(0).equalsIgnoreCase(AGConstant.MarketsManagement.BlockedUserPopup.LBL_NO_USER_BLOCKED))
//                return "";
//            return Integer.toString(lstBlocked.size());
//        }
        return Integer.toString(items);
    }

    public void close() {
        btnClose.click();
    }

}
