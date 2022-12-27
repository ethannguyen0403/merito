package membersite.pages.components.header;

import membersite.pages.all.tabexchange.AccountStatementPage;
import membersite.pages.all.tabexchange.MyBetsPage;
import membersite.pages.all.tabexchange.ProfitAndLossPage;
import membersite.pages.components.changepasswordpopup.ChangePasswordPopup;
import membersite.pages.components.underagegamblingpopup.UnderageGamblingPopup;

public class Header {
     public String getName(){
         return "This is header";
     }

    public void login(String username, String password,boolean skipByDefault){
    }
    public String loginInvalid(String username, String password){
       return "";
    }

    public UnderageGamblingPopup clickLogin(){
         return new UnderageGamblingPopup();
    }

    public boolean isMyAccountDisplay(){
         return true;
    }

    public boolean isMyAccountContains(String menu){
        return false;
    }

    public AccountStatementPage openAccountStatement(){
       return new AccountStatementPage();
    }

    public MyBetsPage openMyBets(){
        return new MyBetsPage();
    }

    public ProfitAndLossPage openProfitAndLoss(){
        return new ProfitAndLossPage();
    }

    public ChangePasswordPopup openChangePasswordPopup(){
       return new ChangePasswordPopup();
    }
}
