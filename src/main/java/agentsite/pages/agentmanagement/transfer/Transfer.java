package agentsite.pages.agentmanagement.transfer;

import agentsite.controls.Table;
import agentsite.objects.agent.account.AccountInfo;
import agentsite.pages.HomePage;
import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.*;
import org.testng.Assert;

public class Transfer extends HomePage {
    public TextBox txtUserName = TextBox.id("userName");
    public DropDownBox ddpAccountStatus = DropDownBox.id("status");
    public DropDownBox ddpLevel = DropDownBox.id("level");
    public Button btnSubmit = Button.xpath("//button[@class='btn-submit']");
    public Button btnTransfer = Button.id("btn-transfer");
    public Label lblAllYesterDayBalance = Label.id("labelBalance");
    public Label lblYouAreAllowToTransferOnToday = Label.xpath("//div[@id='transferInfo']//div[contains(@class,'transfer-message')][1]");
    public Label lblTransferableBalanceIsCalculatedUpToYesterday = Label.xpath("//div[@id='transferInfo']//div[contains(@class,'transfer-message')][2]");
    public Table tblAccountList = Table.xpath("//div[@id='transferList']//table[contains(@class,'report')]", 15);
    int colUsername = 2;
    int colNickname = 3;
    int colAccountStatus = 4;
    int colCheckBox = 5;
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
    public Transfer(String types) {
        super(types);
    }

    public void filter(String username, String accountStatus, String level) {
        if (!username.isEmpty())
            txtUserName.sendKeys(username);
        if (!accountStatus.isEmpty())
            ddpAccountStatus.selectByVisibleText(accountStatus);
        if (!level.isEmpty())
            ddpLevel.selectByVisibleText(level);
        btnSubmit.click();
        waitingLoadingSpinner();
    }

    public double defineAmountBasedOnTransferableBalance(double amount, double transferableBalance) {
        double _amount = amount;
        if (transferableBalance > 0)
            _amount = transferableBalance * (-1);
        return _amount;
    }

    private int getRowIndexofUserName(String username) {
        int i = 1;
        String xpath = tblAccountList.getxPathOfCell(1, colUsername, i, null);
        Label lblUserName = Label.xpath(xpath);
        while (true) {
            if (!lblUserName.isDisplayed()) {
                System.out.println("DEBUG! Username " + username + " does not exist in the table");
                return 0;
            }
            xpath = tblAccountList.getxPathOfCell(1, colUsername, i, null);
            lblUserName = Label.xpath(xpath);
            if (lblUserName.getText().trim().equals(username)) {
                return i;
            }
            i = i + 1;
        }
    }

    private TransferPopup clickTransferBalanceLine(int index) {
        //  int i = getRowIndexofUserName(username);
        Link lnkTransferableBalance;
        String xpath = tblAccountList.getxPathOfCell(1, colTransferBalance, index, null);
        lnkTransferableBalance = Link.xpath(xpath);
        lnkTransferableBalance.click();
        return new TransferPopup();
    }

    public ConfirmPopup clickTransferButton(){
        btnTransfer.click();
        return new ConfirmPopup();
    }

    public ConfirmPopup clickUpdateStatusColumn(String username) {
        int index = getRowIndexofUserName(username);
        String xpath = tblAccountList.getxPathOfCell(1, colUpdateStatus, index, null);
        Link.xpath(xpath).click();
        return new ConfirmPopup();
    }

    public boolean isAccountTransferredSuccess(String userName) {
        int index = getRowIndexofUserName(userName);
        String xpath = tblAccountList.getxPathOfCell(1, colUpdateStatus, index, "span[@class='psuccess']");
        return Icon.xpath(xpath).isDisplayed();
    }

    public boolean isAccountTransferredError(String userName) {
        int index = getRowIndexofUserName(userName);
        String xpath = tblAccountList.getxPathOfCell(1, colUpdateStatus, index, "span[@class='perror']");
        return Icon.xpath(xpath).isDisplayed();
    }

    public void selectUser(String username){
        int index = getRowIndexofUserName(username);
        Link lnkTransferableBalance = Link.xpath(tblAccountList.getxPathOfCell(1, colCheckBox, index, null));
        lnkTransferableBalance.click();
    }
    public void transfer(String userName, String amount) {
        int index = getRowIndexofUserName(userName);
        clickTransferBalanceLine(index).transfer(amount);
        waitingLoadingSpinner();
    }

    public AccountInfo getTransferInfo(String username) {
        int index = getRowIndexofUserName(username);
        String nickName = Label.xpath(tblAccountList.getxPathOfCell(1, colNickname, index, null)).getText();
        String accountStatus = Label.xpath(tblAccountList.getxPathOfCell(1, colAccountStatus, index, null)).getText();
        String level = Label.xpath(tblAccountList.getxPathOfCell(1, colLevel, index, null)).getText();
        String transferableBalance = Label.xpath(tblAccountList.getxPathOfCell(1, colTransferBalance, index, null)).getText();
        String retainAmount = Label.xpath(tblAccountList.getxPathOfCell(1, colRetainAmount, index, null)).getText();
        String totalBalance = Label.xpath(tblAccountList.getxPathOfCell(1, colTotalBalance, index, null)).getText();
        String yesterdayDownlineBalance = Label.xpath(tblAccountList.getxPathOfCell(1, colYesterdayDownlineBalance, index, null)).getText();
        String downlineBalance = Label.xpath(tblAccountList.getxPathOfCell(1, colDownlineBalance, index, null)).getText();
        String totalPlayerOustanding = Label.xpath(tblAccountList.getxPathOfCell(1, colTotalPlayersOutstanding, index, null)).getText();
        String creditGiven = Label.xpath(tblAccountList.getxPathOfCell(1, colCreditGiven, index, null)).getText();
        String creditUsed = Label.xpath(tblAccountList.getxPathOfCell(1, colCreditUse, index, null)).getText();
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

    public void verifyTransferInfo(String username, AccountInfo beforeTransfer, double amount){
        AccountInfo afterTransfer = getTransferInfo(username);
        Assert.assertTrue(isAccountTransferredSuccess(username),"Failed! Update Status column should be green check ");
        Assert.assertEquals(beforeTransfer.getTransferableBalance() + amount, afterTransfer.getTransferableBalance(), "Failed! Transferable balance is incorrect");
        Assert.assertEquals(beforeTransfer.getRetainAmount(), afterTransfer.getRetainAmount(), "Failed! Transferable balance is incorrect");
        Assert.assertEquals(beforeTransfer.getTotalBalance() + amount, afterTransfer.getTotalBalance(), "Failed! Total Balance is incorrect");
        Assert.assertEquals(beforeTransfer.getYesterdayDownlineBalance(), afterTransfer.getYesterdayDownlineBalance(), "Failed! Yesterday Downline Balance is incorrect");
        Assert.assertEquals(beforeTransfer.getDownlineBalance(), afterTransfer.getDownlineBalance(), "Failed!  Downline Balance is incorrect");
        Assert.assertEquals(beforeTransfer.getTotalPlayerOutstanding(), afterTransfer.getTotalPlayerOutstanding(), "Failed!Total Players Outstanding is incorrect");
        Assert.assertEquals(beforeTransfer.getCreditGiven(), afterTransfer.getCreditGiven(), "Failed! Credit Given incorrect");
        Assert.assertEquals(beforeTransfer.getCreditUsed(), afterTransfer.getCreditUsed(), "Failed! Credit Used incorrect");
        Assert.assertTrue(isAccountTransferredSuccess(username),"Failed! Update Status column should be green check ");
    }

}
