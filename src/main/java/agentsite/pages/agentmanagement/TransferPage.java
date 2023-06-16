package agentsite.pages.agentmanagement;

import agentsite.controls.Table;
import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.transfer.TransferPopup;
import agentsite.pages.components.ConfirmPopup;
import agentsite.pages.components.SecurityPopup;
import com.paltech.element.common.*;

public class TransferPage extends HomePage {
    public SecurityPopup securityPopup = SecurityPopup.xpath("//app-config-otp");
    public TextBox txtUserName = TextBox.id("userName");
    public DropDownBox ddpAccountStatus = DropDownBox.id("status");
    public DropDownBox ddpLevel = DropDownBox.id("level");
    public Button btnSubmit = Button.xpath("//button[@class='btn-submit']");
    public Button btnTransfer = Button.id("btn-transfer");
    public CheckBox cbAllYesterDayBalance = CheckBox.id("textBoxAllYesterday");
    public Label lblAllYesterDayBalance = Label.id("labelBalance");
    public Label lblYouAreAllowToTransferOnToday = Label.xpath("//div[@id='transferInfo']//div[contains(@class,'transfer-message')][1]");
    public Label lblTransferableBalanceIsCalculatedUpToYesterday = Label.xpath("//div[@id='transferInfo']//div[contains(@class,'transfer-message')][2]");
    public Table tblAccountList = Table.xpath("//div[@id='transferList']//table[contains(@class,'report')]",15);
    public CheckBox cbAll= CheckBox.xpath("//div[@id='transferList']//table[contains(@class,'report')]//th[5]//input");
    public Label lblNorecord = Label.xpath("//td[@class='no-record']");
    int colUsername = 2;
    int colNickname = 3;
    int colAccountStatus = 4;
    int colCheckBox= 5;
    int colLevel = 6;
    int colTransferBalance = 7;
    int colRetainAmount = 8;
    int colTotalBalance = 9;
    int colYesterdayDownlineBalance = 10;
    int colDownlineBalance = 11;
    int colTotalPlayersOutstanding = 12;
    int colCreditGiven = 13;
    int colCreditUse = 14;
    int colUpdateStatus = 15;
    public TransferPage(String types) {
        super(types);
    }
    public void filter (String username, String accountStatus, String level){
        if(!username.isEmpty())
            txtUserName.sendKeys(username);
        if(! accountStatus.isEmpty())
            ddpAccountStatus.selectByVisibleText(accountStatus);
        if(!level.isEmpty())
            ddpLevel.selectByVisibleText(level);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public double defineAmountBasedOnTransferableBalance (double amount, double transferableBalance){
        double _amount = amount;
        if(transferableBalance>0)
            _amount = transferableBalance * (-1);
        return _amount;
    }

    private int getRowIndexofUserName(String username){
        int i =1;
        String xpath =  tblAccountList.getxPathOfCell(1,colUsername,i,null);
        Label lblUserName = Label.xpath(xpath);
        while (true) {
            if(!lblUserName.isDisplayed()) {
                System.out.println("DEBUG! Username "+username+" does not exist in the table");
                return 0;
            }
            xpath =  tblAccountList.getxPathOfCell(1,colUsername,i,null);
            lblUserName = Label.xpath(xpath);
            if(lblUserName.getText().trim().equals(username)){
                return i;
            }
            i = i +1;
        }
    }

    private void selectCheckBox(int index){
      //  int i = getRowIndexofUserName(username);
        CheckBox cbCheckBox;
        if( index == 0){
            cbCheckBox = cbAll;
        }else {
            String xpath =  tblAccountList.getxPathOfCell(1,colCheckBox,index,"input");
            cbCheckBox = CheckBox.xpath(xpath);
        }
        cbCheckBox.click();
    }
    private TransferPopup clickTransferBalanceLine(int index){
        //  int i = getRowIndexofUserName(username);
        Link lnkTransferableBalance;
        String xpath =  tblAccountList.getxPathOfCell(1,colTransferBalance,index,null);
        lnkTransferableBalance = Link.xpath(xpath);
        lnkTransferableBalance.click();
        return new TransferPopup();
    }
    public ConfirmPopup clickUpdateStatusColumn(String username){
        int index = getRowIndexofUserName(username);
        String xpath =   tblAccountList.getxPathOfCell(1,colUpdateStatus,index,null);
        Link.xpath(xpath).click();
        return new ConfirmPopup();
    }

    public boolean isAccountTransferredSuccess (String userName){
        int index = getRowIndexofUserName(userName);
        String xpath =  tblAccountList.getxPathOfCell(1,colUsername,index,"span[@class='psuccess']");
        return Icon.xpath(xpath).isDisplayed();
    }
    public boolean isAccountTransferredError (String userName){
        int index = getRowIndexofUserName(userName);
        String xpath =  tblAccountList.getxPathOfCell(1,colUsername,index,"span[@class='perror']");
        return Icon.xpath(xpath).isDisplayed();
    }

    public String getFirstUsername(){
        if(lblNorecord.isDisplayed()){
            return "DEBUG: There is no data display in Transfer page";
        }
        return Label.xpath(tblAccountList.getxPathOfCell(1,colUsername,1,null)).getText().trim();
    }

    public void transfer(String userName,String amount){
        int index = getRowIndexofUserName(userName);
        clickTransferBalanceLine(index).transfer(amount);
    }
    public AccountInfo getTransferInfo(String username){
        int index = getRowIndexofUserName(username);
        String nickName = Label.xpath(tblAccountList.getxPathOfCell(1,colNickname,index,null)).getText();
        String accountStatus = Label.xpath(tblAccountList.getxPathOfCell(1,colAccountStatus,index,null)).getText();
        String level = Label.xpath(tblAccountList.getxPathOfCell(1,colLevel,index,null)).getText();
        String transferableBalance = Label.xpath(tblAccountList.getxPathOfCell(1,colTransferBalance,index,null)).getText();
        String retainAmount = Label.xpath(tblAccountList.getxPathOfCell(1,colRetainAmount,index,null)).getText();
        String totalBalance = Label.xpath(tblAccountList.getxPathOfCell(1,colTotalBalance,index,null)).getText();
        String yesterdayDownlineBalance = Label.xpath(tblAccountList.getxPathOfCell(1,colYesterdayDownlineBalance,index,null)).getText();
        String downlineBalance = Label.xpath(tblAccountList.getxPathOfCell(1,colDownlineBalance,index,null)).getText();
        String totalPlayerOustanding = Label.xpath(tblAccountList.getxPathOfCell(1,colTotalPlayersOutstanding,index,null)).getText();
        String creditGiven = Label.xpath(tblAccountList.getxPathOfCell(1,colCreditGiven,index,null)).getText();
        String creditUsed = Label.xpath(tblAccountList.getxPathOfCell(1,colCreditUse,index,null)).getText();
        return new AccountInfo.Builder()
                .userCode(username)
                .loginID(nickName)
                .level(level)
                .status(accountStatus)
                .transferableBalance(Double.parseDouble(transferableBalance.replaceAll(",", "")))
                .retainAmount(Double.parseDouble(retainAmount.replaceAll(",", "")))
                .totalBalance(Double.parseDouble(totalBalance.replaceAll(",", "")))
                .yesterdayDownlineBalance(Double.parseDouble(yesterdayDownlineBalance.replaceAll(",", "")))
                .downlineBalance(Double.parseDouble(downlineBalance.replaceAll(",", "")))
                .totalPlayerOutstanding(Double.parseDouble(totalPlayerOustanding.replaceAll(",", "")))
                .creditGiven(Integer.parseInt(creditGiven.replaceAll(",", "")))
                .creditUsed(Double.parseDouble(creditUsed.replaceAll(",", "")))
                .build();
    }

}
