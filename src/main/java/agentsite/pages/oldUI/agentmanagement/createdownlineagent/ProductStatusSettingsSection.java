package agentsite.pages.oldUI.agentmanagement.createdownlineagent;

import com.paltech.element.BaseElement;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Link;
import agentsite.controls.Table;
import org.openqa.selenium.By;
import agentsite.pages.all.agentmanagement.edituser.EditMarketPopup;

import java.util.ArrayList;
import java.util.Objects;

public class ProductStatusSettingsSection extends BaseElement {
    private String _xPath ;
    private String cbSportXPath;
    public CheckBox chbLive ;
    public CheckBox  chbNoneLive  ;
    public Table tblSportTable;
 public EditMarketPopup editMarketPopup;
    public ProductStatusSettingsSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        int totalSportCol = 40;
        tblSportTable = Table.xpath(String.format("%s//div[contains(@class,'marketSettingWrapper')]//table[contains(@class,'sportTable')]",xpathExpression),totalSportCol);
        cbSportXPath ="//td[contains(@class,'betTitle back')]//label[@title='%s']/input";
        chbLive = CheckBox.id("live");
        chbNoneLive = CheckBox.id("nonlive");
        editMarketPopup = EditMarketPopup.xpath(String.format("//app-blocking-setting"));
    }

    public static ProductStatusSettingsSection xpath(String xpathExpression) {
        return new ProductStatusSettingsSection(By.xpath(xpathExpression), xpathExpression);
    }

    public void updateLiveNonLive(boolean isLive,boolean isNonLive)
    {
        boolean isLiveActive = true;
        if(Objects.isNull(chbLive.getAttribute("checked")) )
            isLiveActive = false;
        boolean isNoneLiveActive = true;
        if(Objects.isNull(chbNoneLive.getAttribute("checked")) )
            isNoneLiveActive = false;
        if(isLiveActive != isLive)
            chbLive.click();
        if(isNoneLiveActive != isNonLive)
            chbNoneLive.click();
    }

    public boolean isLiveCbChecked()
    {
        boolean isLiveActive = true;
        if(Objects.isNull(chbLive.getAttribute("checked")) )
            isLiveActive = false;
        return isLiveActive;
    }
    public boolean isNoneLiveCbChecked()
    {
        boolean isLiveActive = true;
        if(Objects.isNull(chbNoneLive.getAttribute("checked")) )
            isLiveActive = false;
        return isLiveActive;
    }

    public void updateSport(String sportName, boolean isActive)
    {
        CheckBox chbSport = CheckBox.xpath(String.format(cbSportXPath,sportName));
        boolean isCheck = chbSport.isSelected();
        if(!isCheck == isActive)
            chbSport.click();
    }

    public boolean isSportActive(String sportName){
        CheckBox chbSport = CheckBox.xpath(String.format(cbSportXPath,sportName));
        return chbSport.isSelected();
    }

    public void openEditMarketPopup(String sportName){
        ArrayList<String> headerList = tblSportTable.getHeaderNameOfRows();
        int i = 1;
        for(String sport: headerList){
            if (sport.equalsIgnoreCase(sportName))
            {
                System.out.println(String.format("Step: Product Setting - Click on Edit icon of %s",sportName));
                Link lblEdit  = (Link) tblSportTable.getControlOfCell(1,i,1,"span[@class='editmarket']");
                //  Label lblEdit = Label.xpath(String.format("//div[contains(@class,'marketSettingWrapper')]//table[contains(@class,'sportTable')]//tbody//tr[1]//td[%d]//span[@class='editmarket']",i));
                lblEdit.click();
                break;
            }
            i=i+1;
        }
    }

    public void updateMarket(String sportName, String marketName, boolean isActive)
    {
       openEditMarketPopup(sportName);
        editMarketPopup.txtSearchMarket.isDisplayed();
        editMarketPopup.searchMarket(marketName);
        editMarketPopup.activeMarket(marketName,isActive);
    }

    public boolean isMarketActive(String marketName)
    {
        return editMarketPopup.isMarketActive(marketName);

    }
    public void closeEditMarket()
    {
        editMarketPopup.btnCancle.click();
    }
}
