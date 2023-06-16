package agentsite.pages;


import agentsite.controls.Table;
import agentsite.objects.agent.account.AccountInfo;
import agentsite.ultils.account.ProfileUtils;
import com.paltech.element.common.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AccountBalancePage extends HomePage {
    public Table tblInfo = Table.xpath("//app-account-balance//table",2);
    public AccountBalancePage(String types) {
        super(types);
    }
    private List<String> defineBalanceInfoCredit(){
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
        for(int i = 0; i < lstDownlineInfo.size(); i++)
        {
            level = lstDownlineInfo.get(i).get(0);
            level = ProfileUtils.convertDownlineByBrand(level,ProfileUtils.getAppName());
            if(level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Credit Used"));
            }else {
                lst.add(String.format("Total %s Credit Used", level));
            }
        }
        // define downline Account Active/Closed/Suspended/Inactive/Blocked
        for(int i = 0; i < lstDownlineInfo.size(); i++)
        {
            level = lstDownlineInfo.get(i).get(0);
            level = ProfileUtils.convertDownlineByBrand(level,ProfileUtils.getAppName());
            if(level.equalsIgnoreCase("PL")){
                lst.add(String.format("Total Member Active/Closed/Suspended/Inactive", level));
            }else
                lst.add(String.format("Total %s Active/Closed/Suspended/Inactive/Blocked", level));
        }
        return lst;
    }



    private List<String> defineBalanceInfoCreditCash(){
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
        for(int i = 0; i < lstDownlineInfo.size(); i++)
        {
            level = lstDownlineInfo.get(i).get(0);
            level = ProfileUtils.convertDownlineByBrand(level,ProfileUtils.getAppName());
            if(level.equalsIgnoreCase("PL")) {
                lst.add(String.format("Total Member Available Balance"));
            }else {
                lst.add(String.format("Total %s Available Balance", level));
            }

        }
        // define downline Account Active/Closed/Suspended/Inactive/Blocked
        for(int i = 0; i < lstDownlineInfo.size(); i++)
        {
            level = lstDownlineInfo.get(i).get(0);
            level = ProfileUtils.convertDownlineByBrand(level,ProfileUtils.getAppName());
            if(level.equalsIgnoreCase("PL")){
                lst.add(String.format("Total Member Active/Closed/Suspended/Inactive", level));
            }else
                lst.add(String.format("Total %s Active/Closed/Suspended/Inactive/Blocked", level));
        }
        return lst;
    }



    public List<String> defineBalanceInfo(boolean isCredit){
        if(isCredit)
            return defineBalanceInfoCredit();
        return defineBalanceInfoCreditCash();
    }

    public AccountInfo getInfoCreditLoginBalance(String currency){
        String downlineBalance = Label.xpath(tblInfo.getxPathOfCell(1,2,1,null)).getText().replaceAll(currency,"").trim();
        String yesterdayDownlineBalance = Label.xpath(tblInfo.getxPathOfCell(1,2,2,null)).getText().replaceAll(currency,"").trim();
        String totalBalance = Label.xpath(tblInfo.getxPathOfCell(1,2,3,null)).getText().replaceAll(currency,"").trim();
        String transferableBalance = Label.xpath(tblInfo.getxPathOfCell(1,2,4,null)).getText().replaceAll(currency,"").trim();
        String myOutstanding = Label.xpath(tblInfo.getxPathOfCell(1,2,5,null)).getText().replaceAll(currency,"").trim();
        String totalOutstanding = Label.xpath(tblInfo.getxPathOfCell(1,2,6,null)).getText().replaceAll(currency,"").trim();
        String todayWinLoss = Label.xpath(tblInfo.getxPathOfCell(1,2,7,null)).getText().replaceAll(currency,"").trim();
        String yesterdayWinLoss = Label.xpath(tblInfo.getxPathOfCell(1,2,8,null)).getText().replaceAll(currency,"").trim();
        String myCredit = Label.xpath(tblInfo.getxPathOfCell(1,2,9,null)).getText().replaceAll(currency,"").trim();
        return new AccountInfo.Builder()
                .downlineBalance(Double.parseDouble(downlineBalance.replaceAll(",", "")))
                .yesterdayDownlineBalance(Double.parseDouble(yesterdayDownlineBalance.replaceAll(",", "")))
                .totalBalance(Double.parseDouble(totalBalance.replaceAll(",", "")))
                .transferableBalance(Double.parseDouble(transferableBalance.replaceAll(",", "")))
                .myOutstanding(Double.parseDouble(myOutstanding.replaceAll(",", "")))
                .totalOustanding(Double.parseDouble(totalOutstanding.replaceAll(",", "")))
                .todayWinLoss(Double.parseDouble(todayWinLoss.replaceAll(",", "")))
                .yesterdayWinLoss(Double.parseDouble(yesterdayWinLoss.replaceAll(",", "")))
                .creditGiven((int)(Double.parseDouble(myCredit.replaceAll(",", ""))))
                .build();
    }



}
