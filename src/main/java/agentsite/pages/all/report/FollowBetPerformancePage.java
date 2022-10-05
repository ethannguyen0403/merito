package agentsite.pages.all.report;

import com.paltech.element.common.*;
import agentsite.controls.Table;
import agentsite.pages.all.components.LeftMenu;

public class FollowBetPerformancePage extends LeftMenu {
   public RadioButton rbFollowBets = RadioButton.xpath("//app-follow-small-bets//input[@id='ragroup']");
   public RadioButton rbSmallBets = RadioButton.xpath("//app-follow-small-bets//input[@id='ragroup']");
   public Label lblFollowBets = Label.xpath("//input[@value='FOLLOWBET']/following::label[1]");
    public Label lblSmallBets = Label.xpath("//input[@value='SMALLBET']/following::label[1]");
   public TextBox txtUsername = TextBox.name("username");
   public Label lblUserName = Label.xpath("//input[@name='username']/preceding::label[1]");
   public DropDownBox ddbAccountToBet  = DropDownBox.xpath("//select[contains(@class,'custom-select')]");
   public TextBox txtFrom = TextBox.xpath("//label[contains(text(),'From')]/following::input[@class='form-control'][1]");
   public TextBox txtTo = TextBox.xpath("//label[text()='To ']/following::input[@class='form-control'][1]");
   public Label lblInfo = Label.xpath("//label[@class='label']");
   public DropDownBox ddbCurrencyType = DropDownBox.xpath("//label[text()='Currency Type']/following::select[1]");
   public Button btnSearch = Button.name("search");
   public int tblFollowBetTotalCol = 8;
   public Table tblFollowBet = Table.xpath("//table[contains(@class,'followbetReportTable')]",tblFollowBetTotalCol);
   public Table tblSmallBet = Table.xpath("//table[contains(@class,'ptable report hasDownline')]",7);
   public Button btnYesterday = Button.name("yesterday");
    public Button btnLastWeek = Button.name("lastWeek");


   public void searchFollowBet(String username, String accountToBet, String from, String to){
       if(!rbFollowBets.getAttribute("checked").equalsIgnoreCase("true"))
       {
           rbFollowBets.click();
       }
       if(!username.isEmpty())
           txtUsername.sendKeys(username);
       if(!accountToBet.isEmpty())
           ddbAccountToBet.selectByVisibleText(accountToBet);
       if(!from.isEmpty())
           txtFrom.sendKeys(from);
       if(!to.isEmpty())
           txtTo.sendKeys(to);
       btnSearch.click();

   }
    public void searchSmallBet(String from, String to, String currencyType){
        if(!rbSmallBets.getAttribute("checked").equalsIgnoreCase("true"))
        {
            rbSmallBets.click();
        }
        if(!from.isEmpty())
            txtFrom.sendKeys(from);
        if(!to.isEmpty())
            txtTo.sendKeys(to);
        if(!currencyType.isEmpty())
            ddbCurrencyType.selectByVisibleText(currencyType);
        btnSearch.click();
    }


}
