package backoffice.pages.bo.marketmanagement;

import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;
import backoffice.controls.bo.StaticTable;
import org.openqa.selenium.Keys;
import java.util.List;

public class BeforeLoginManagementPage extends HomePage {
    public StaticTable tblSport = StaticTable.xpath("//app-before-login-management//div[@class='col-sm-6'][1]//div[contains(@class,'table-wrapper')]",
            "div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",5);
    public StaticTable tblMarketType = StaticTable.xpath("//app-before-login-management//div[@class='col-sm-6'][2]//div[contains(@class,'table-wrapper')]",
            "div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",5);
    public TextBox txtMarketType = TextBox.xpath("//input[@type='text']");
    public int colSport = 2;
    public int colMarketType = 2;
    public int colStatus = 5;

    public void activeSport(String sportName, boolean isActive){
        List<String> lstSport = tblSport.getColumn(colSport,true);
        for(int i = 0; i<lstSport.size(); i++){
            if(lstSport.get(i).equals(sportName)){
                System.out.println(String.format("The sport %s is found",sportName));
                tblSport.getControlOfCell(1,colSport,i+1,null).click();
                Link lnkStatus = (Link) tblSport.getControlOfCell(1,colStatus,i+1,"input[@role='switch']");
                Link lnkStatusss = (Link) tblSport.getControlOfCell(1,colStatus,i+1,"label[@class='mat-slide-toggle-label']");
                String status = lnkStatus.getAttribute("aria-checked");
                if(status.equals("false") && isActive){
                    lnkStatusss.click();
                }
                if(status.equals("true") && !isActive){
                    lnkStatusss.click();
                }
                return;
            }
        }
        System.out.println(String.format("CANNOT active as the sport %s is NOT found",sportName));
    }
    public void activeMarket(String marketTypeName,boolean isActive){
        List<String> lstMarketType = tblMarketType.getColumn(colMarketType,true);
        for(int i = 0; i<lstMarketType.size(); i++){
            if(lstMarketType.get(i).equals(marketTypeName)){
                System.out.println(String.format("The sport %s is found",marketTypeName));
                Link lnkStatus = (Link) tblMarketType.getControlOfCell(1,colStatus,i+1,"input[@role='switch']");
                Link lnkStatusss = (Link) tblMarketType.getControlOfCell(1,colStatus,i+1,"label[@class='mat-slide-toggle-label']");
                String status = lnkStatus.getAttribute("aria-checked");
                if(status.equals("false") && isActive){
                    lnkStatusss.click();
                }
                if(status.equals("true") && !isActive){
                    lnkStatusss.click();
                }
                return;
            }
        }
        System.out.println(String.format("CANNOT active as the sport %s is NOT found",marketTypeName));
    }

    public void searchMartyType(String marketType){
        txtMarketType.sendKeys(marketType);
        txtMarketType.type(false, Keys.ENTER);
        tabActive.isTextDisplayed(marketType,2);
    }
}
