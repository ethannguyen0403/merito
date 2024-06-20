package backoffice.pages.bo.marketmanagement;

import backoffice.controls.Table;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.marketmanagement.components.BeforeLoginManagementPopup;
import com.paltech.element.common.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class BeforeLoginManagementPage extends HomePage {
    public StaticTable tblSport = StaticTable.xpath("(//app-before-login-management//div[@class='col-sm-5']//div[contains(@class,'table-wrapper')])[1]",
            "div[@class='custom-table-body custom-scroll-body']", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", 5);
    public StaticTable tblMarketType = StaticTable.xpath("(//app-before-login-management//div[@class='col-sm-5']//div[contains(@class,'table-wrapper')])[2]",
            "div[@class='custom-table-body custom-scroll-body']", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", 5);
    public TextBox txtMarketType = TextBox.xpath("//input[@type='text']");
    public int colSport = 2;
    public int colMarketType = 2;
    public int colStatus = 5;
    public DropDownBox ddpBrand = DropDownBox.xpath("//div[text()='Brand']/following-sibling::select");
    public DropDownBox ddpDomain = DropDownBox.xpath("//div[text()='Domain']/following-sibling::select");
    public DropDownBox ddpStatus = DropDownBox.xpath("//div[text()='Status']/following-sibling::select");
    public Button btnSubmit = Button.xpath("//button//span[text()='Submit']");
    public DropDownBox ddpCurrency = DropDownBox.xpath("//h5[text()='Currency']/parent::div/following-sibling::select");
    public DropDownBox ddpLanguage = DropDownBox.xpath("//h5[text()='Language']/parent::div/following-sibling::select");
    public Button btnSave = Button.xpath("//button[text()='Save']");
    public Button btnCreateMenu = Button.xpath("//button[text()='Create Menu']");
    public Table tblHeaderMenu = Table.xpath("//table[@class='table']", 8);
    DropDownBox ddpType = DropDownBox.xpath("//div[text()='Type']/following-sibling::select");

    public void activeSport(String sportName, boolean isActive) {
        List<String> lstSport = tblSport.getColumn(colSport, true);
        for (int i = 0; i < lstSport.size(); i++) {
            if (lstSport.get(i).equals(sportName)) {
                System.out.println(String.format("The sport %s is found", sportName));
                tblSport.getControlOfCell(1, colSport, i + 1, null).click();
                Link lnkStatus = (Link) tblSport.getControlOfCell(1, colStatus, i + 1, "button");
//                Link lnkStatusss = (Link) tblSport.getControlOfCell(1, colStatus, i + 1, "label[@class='mat-slide-toggle-label']");
                String status = lnkStatus.getAttribute("aria-checked");
                if (!status.equals(isActive)) {
                    lnkStatus.click();
                }
//                if (status.equals("true") && !isActive) {
//                    lnkStatus.click();
//                }
                return;
            }
        }
        System.out.println(String.format("CANNOT active as the sport %s is NOT found", sportName));
    }

    public void activeMarket(String marketTypeName, boolean isActive) {
        List<String> lstMarketType = tblMarketType.getColumn(colMarketType, true);
        for (int i = 0; i < lstMarketType.size(); i++) {
            if (lstMarketType.get(i).equals(marketTypeName)) {
                System.out.println(String.format("The sport %s is found", marketTypeName));
                Link lnkStatus = (Link) tblMarketType.getControlOfCell(1, colStatus, i + 1, "input[@role='switch']");
                Link lnkStatusss = (Link) tblMarketType.getControlOfCell(1, colStatus, i + 1, "label[@class='mat-slide-toggle-label']");
                String status = lnkStatus.getAttribute("aria-checked");
                if (status.equals("false") && isActive) {
                    lnkStatusss.click();
                }
                if (status.equals("true") && !isActive) {
                    lnkStatusss.click();
                }
                return;
            }
        }
        System.out.println(String.format("CANNOT active as the sport %s is NOT found", marketTypeName));
    }

    public void searchMarketType(String marketType) {
        txtMarketType.sendKeys(marketType);
        txtMarketType.type(false, Keys.ENTER);
        tabActive.isTextDisplayed(marketType, 2);
    }

    public void verifySearchByMarketType(String marketType) {
        List<String> lstMarket = tblMarketType.getColumn(colMarketType, false);
        lstMarket.remove(0);
        Assert.assertTrue(lstMarket.listIterator().next().equalsIgnoreCase(marketType), "The list market does not contains search key: " + marketType);
    }

    public void filter(String type, String brand, String domain, String status, boolean isSubmit) {
        waitSpinIcon();
        switch (type.toLowerCase()) {
            case "header menu":
                ddpType.selectByVisibleText(type);
                if (!brand.isEmpty()) {
                    ddpBrand.selectByVisibleText(brand);
                }
                if (!domain.isEmpty()) {
                    ddpDomain.selectByVisibleText(domain);
                }
                if (!status.isEmpty()) {
                    ddpStatus.selectByVisibleText(status);
                }
                if (isSubmit) {
                    btnSubmit.click();
                    tblHeaderMenu.isDisplayed();
                }
                break;
            default:
                ddpType.selectByVisibleText(type);
                if (!brand.isEmpty()) {
                    ddpBrand.selectByVisibleText(brand);
                }
                if (isSubmit) {
                    btnSubmit.click();
                    tblSport.isDisplayed();
                }
                break;
        }
    }

    public boolean isUIDisplayCorrect(String type) {
        switch (type.toLowerCase()) {
            case "header menu":
                try {
                    ddpBrand.isDisplayed();
                    ddpDomain.isDisplayed();
                    ddpStatus.isDisplayed();
                    btnSubmit.isDisplayed();
                    ddpCurrency.isDisplayed();
                    ddpLanguage.isDisplayed();
                    btnSave.isDisplayed();
                    tblHeaderMenu.isDisplayed();
                    btnCreateMenu.isDisplayed();
                    return true;

                } catch (Exception e) {
                    System.out.println("Control displays missing on page " + e.getMessage());
                    return false;
                }
            default:
                try {
                    ddpBrand.isDisplayed();
                    btnSubmit.isDisplayed();
                    tblSport.isDisplayed();
                    tblMarketType.isDisplayed();
                    return true;

                } catch (Exception e) {
                    System.out.println("Control displays missing on page " + e.getMessage());
                    return false;
                }
        }

    }

    public BeforeLoginManagementPopup openCreateHeaderMenu() {
        btnCreateMenu.click();
        return new BeforeLoginManagementPopup();
    }

    public List<List<String>> getListMenuSequence() {
        List<WebElement> lblMenu = Label.xpath("//table[@class='table']//tbody[1]//tr//td[2]").getWebElements();
        List<WebElement> lblSequence = Label.xpath("//table[@class='table']//tbody[1]//tr//td[3]").getWebElements();
        List<String> lstMenu = new ArrayList<>();
        List<String> lstSequence = new ArrayList<>();
        List<List<String>> lstMenuSequence = new ArrayList<>();
        for (WebElement element : lblMenu) {
            lstMenu.add(element.getText().trim());
        }
        for (WebElement element : lblSequence) {
            lstSequence.add(element.getText().trim());
        }
        lstMenuSequence.add(lstMenu);
        lstMenuSequence.add(lstSequence);
        return lstMenuSequence;
    }

    public void clickOnSport(String sportName) {
        List<String> lstSport = tblSport.getColumn(colSport, true);
        for (int i = 0; i < lstSport.size(); i++) {
            if (lstSport.get(i).equals(sportName)) {
                System.out.println(String.format("The sport %s is found", sportName));
                tblSport.getControlOfCell(1, colSport, i + 1, null).click();
                return;
            }
        }
        waitSpinIcon();
        System.out.println(String.format("CANNOT active as the sport %s is NOT found", sportName));
    }
}
