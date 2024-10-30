package agentsite.pages.accountbalance;

import agentsite.ultils.account.ProfileUtils;
import common.AGConstant;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AccountBalance {
    private List<String> defineBalanceInfoCredit() {
        List<ArrayList<String>> lstDownlineInfo = ProfileUtils.getDownlineBalanceInfo();
        String level;
        List<String> lst = new LinkedList<String>(Arrays.asList(
                "Downline Balance",
                "Yesterday Downline Balance",
                "Total Balance",
                "Transferable Balance",
                "My Credit",
                "My Outstanding",
                "Total Outstanding",
                "Today Win Loss",
                "Yesterday Win Loss"
        ));
        // define downline Credit Used
        for (int i = 0; i < lstDownlineInfo.size(); i++) {
            level = lstDownlineInfo.get(i).get(0);
            level = ProfileUtils.convertDownlineByBrand(level, ProfileUtils.getAppName());
            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Credit Used"));
            } else {
                lst.add(String.format("Total %s Credit Used", level));
            }
        }
        // define downline Account Active/Closed/Suspended/Inactive/Blocked
        for (int i = 0; i < lstDownlineInfo.size(); i++) {
            level = lstDownlineInfo.get(i).get(0);
            level = ProfileUtils.convertDownlineByBrand(level, ProfileUtils.getAppName());
            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Active/Closed/Suspended/Inactive", level));
            } else
                lst.add(String.format("Total %s Active/Closed/Suspended/Inactive/Blocked", level));
        }
        return lst;
    }


    private List<String> defineBalanceInfoCreditCash() {
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
            level = ProfileUtils.convertDownlineByBrand(level, ProfileUtils.getAppName());
            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Available Balance"));
            } else {
                lst.add(String.format("Total %s Available Balance", level));
            }

        }
        // define downline Account Active/Closed/Suspended/Inactive/Blocked
        for (int i = 0; i < lstDownlineInfo.size(); i++) {
            level = lstDownlineInfo.get(i).get(0);
            level = ProfileUtils.convertDownlineByBrand(level, ProfileUtils.getAppName());
            if (level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Active/Closed/Suspended/Inactive", level));
            } else
                lst.add(String.format("Total %s Active/Closed/Suspended/Inactive/Blocked", level));
        }
        return lst;
    }


    public List<String> defineBalanceInfo(boolean isCredit) {
        if (isCredit)
            return defineBalanceInfoCredit();
        return defineBalanceInfoCreditCash();
    }
    public void verifyTitleDisplay() {
    }
}
