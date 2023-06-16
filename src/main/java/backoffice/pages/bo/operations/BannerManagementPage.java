package backoffice.pages.bo.operations;

import backoffice.controls.Cell;
import com.paltech.element.common.*;
import com.paltech.utils.DateUtils;
import backoffice.common.BOConstants;
import backoffice.controls.Table;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.operations.component.NewBannerPopup;
import backoffice.pages.bo.operations.component.UpdateBannerPopup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BannerManagementPage extends HomePage {
    public enum Actions {UPDATE, DELETE, ACTIVE, INACTIVE}
    public enum Type {MOBILE, FLOATING, POPUP, HOME}
    public DropDownBox ddbType = DropDownBox.name("types");
    public DropDownBox ddbBrand = DropDownBox.name("brands");
    public DropDownBox ddbStatus = DropDownBox.xpath("//span[@class='title-label mb-1' and text()='Status']//..//..//select[@name='status']");
    public DropDownBox ddbDomain = DropDownBox.xpath("//span[@class='title-label mb-1' and text()='Domain']//..//..//select[@name='status']");
    public DropDownBox ddbTheme = DropDownBox.xpath("//span[@class='title-label mb-1' and text()='Theme']//..//..//select[@name='status']");
    Button btnSearch = Button.name("submit");
    Button btnCreateBanner = Button.name("create-banner");
    public Label lblOldView = Label.xpath("//ul[@class='nav nav-tabs']//a[text()='Old View']");
    public Label lblNewView = Label.xpath("//ul[@class='nav nav-tabs']//a[text()='New View']");
    public Label lblTitlePage = Label.xpath("//div[@id='header']//span");

    private int totalCols = 11;
    public int colNo = 1;
    public int colBanner=2;
    public int colStatus = 4;
    public int colTheme = 4;
    public int colSequence = 5;
    public int colBrands = 6;
    public int colValidFrom =7;
    public int colValidTill =8;
    public int colCreateBy =10;
    public int colAction =11;
    //public Table tblBannerManagement = Table.xpath("//table[contains(@class,'table-sm')]", totalCols);
    public StaticTable tblBannerManagement = StaticTable.xpath("//div[@class='custom-table']","div[contains(@class,'custom-table-body')]","div[contains(@class,'custom-table-row')]","div[contains(@class,'custom-table-cell')]",totalCols);
    //public Label lblNoRecord = Label.xpath("//table[contains(@class,'table-sm')]//div[contains(@class,'no-record')]");
    public Table tblResult = Table.xpath("//div[@class='container-fluid pb-4 table-responsive']", totalCols);

    public Object clickAction(Actions type, int row) {
        Link lnk;
        switch (type) {
            case UPDATE:
                lnk = (Link)tblBannerManagement.getControlOfCell(1, colAction, row, "i[contains(@class,'fa-pencil-alt')]");
                if (lnk == null) {
                    System.err.println("ERROR: Cannot get Update button");
                    return null;
                }
                lnk.click();
                return new NewBannerPopup();
            case ACTIVE:
                tblBannerManagement.getControlOfCell(1, colAction, row, "button[contains(@class, 'btn-ACTIVE')]").click();
                break;
            case INACTIVE:
                tblBannerManagement.getControlOfCell(1, colAction, row, "button[contains(@class, 'btn-INACTIVE')]").click();
                break;
            case DELETE:
                tblBannerManagement.getControlOfCell(1, colAction, row, "i[contains(@class,'fa-trash-alt')]").click();
                break;
        }

        return null;
    }

    public void selectType(String type){
        ddbType.selectByVisibleText(type);
    }

    public void filter(String type, String brand, String status) {
        selectType(type);
        if (!brand.isEmpty()) {
            ddbBrand.selectByVisibleText(brand);
        }
        if (!status.isEmpty()) {
            ddbStatus.selectByVisibleText(status);
        }
        btnSearch.click();
        // waiting for loading
        btnSearch.isInvisible(2);
    }

    public void filter(String type, String brand, String theme, String status) {
        selectType(type);
        if (!brand.isEmpty()) {
            ddbBrand.selectByVisibleText(brand);
        }
        if (!theme.isEmpty()) {
            ddbTheme.selectByVisibleText(theme);
        }
        if (!status.isEmpty()) {
            ddbStatus.selectByVisibleText(status);
        }
        btnSearch.click();
        // waiting for loading
        btnSearch.isInvisible(2);
    }

    public NewBannerPopup createNewBanner() {
        btnCreateBanner.click();
        return new NewBannerPopup();
    }

    public Object action (Actions type, String bannerName) {
        List<String> lstBanner = tblBannerManagement.getColumn(colNo,true);
        for(int i = 0; i <lstBanner.size(); i++){
            String imgSrc = tblBannerManagement.getControlOfCell(1,colBanner,i+1,"img[@class='banner-image']").getAttribute("src");
            if(imgSrc.contains(bannerName)) {
                return clickAction(type,i+1);
            }
        }
        return null;
    }

    public boolean verifyBannerInfo(String fileName, List<String> lstBrands, String status, String sequence, String validFrom, String validTo,String createBy ){
       /* if (lblNoRecord.isDisplayed())
            return false;*/
        List<String> lstBanner = tblBannerManagement.getColumn(colNo,true);
        String actualData;
        for(int i = 0; i <lstBanner.size(); i++){
            String imgSrc = tblBannerManagement.getControlOfCell(1,colBanner,i+1,"img[@class='banner-image']").getAttribute("src");
            if(imgSrc.contains(fileName)) {
                if(!status.isEmpty()){
                    actualData = tblBannerManagement.getControlOfCell(1,colStatus,i+1,null).getText();
                    if(!actualData.equalsIgnoreCase(status))
                    {
                        System.out.println(String.format("Expected status is %s and Actual Status: %s",status,actualData));
                        return false;
                    }
                }
                if(!sequence.isEmpty()){
                    actualData = tblBannerManagement.getControlOfCell(1,colSequence,i+1,null).getText();
                    if(!actualData.equals(sequence))
                    {
                        System.out.println(String.format("Expected Sequence is %s and Actual Sequence: %s",sequence,actualData));
                        return false;
                    }
                }
                if(!Objects.isNull(lstBrands)){
                    actualData = tblBannerManagement.getControlOfCell(1,colBrands,i+1,null).getText();
                    String expected = lstBrands.toString().replace("[","").replace("]","").replace(", ","\n");
                    if(!actualData.equals(expected))
                        System.out.println(String.format("Expected Brands: %s and Actual Brands: %s",expected,actualData));
                }
                if(!validFrom.isEmpty()){
                    actualData = tblBannerManagement.getControlOfCell(1,colValidFrom,i+1,null).getText();
                    if(!actualData.equals(validFrom)){
                        System.out.println(String.format("Expected Valid From: %s and Actual Valid From: %s",validFrom,actualData));
                        return false;
                    }
                }
                if(!validTo.isEmpty()){
                    actualData = tblBannerManagement.getControlOfCell(1,colValidTill,i+1,null).getText();
                    if(!actualData.equals(validTo)){
                        System.out.println(String.format("Expected Valid Till: %s and Actual Valid Till: %s",validTo,actualData));
                        return false;
                    }
                }
                if(!createBy.isEmpty())
                {
                    actualData = tblBannerManagement.getControlOfCell(1,colCreateBy,i+1,null).getText();
                    if(!actualData.equals(createBy)){
                        System.out.println(String.format("Expected Create By: %s and Actual Create By: %s",createBy,actualData));
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public List<String> getBanners(){
       /* if (lblNoRecord.isDisplayed())
            return null;*/
        List<String> lstBanner = tblBannerManagement.getColumn(colNo,true);
        List<String> lstBannerSrc = new ArrayList<>();
        for(int i = 0; i <lstBanner.size(); i++){
            String imgSrc = tblBannerManagement.getControlOfCell(1,colBanner,i+1,"img[@class='banner-image']").getAttribute("src");
            imgSrc = imgSrc.split("image")[1];
            String validTill = tblBannerManagement.getControlOfCell(1,colValidTill,i+1,null).getText();
            Date dateTill = DateUtils.convertToDate(validTill,"dd-MM-YYYY");
            Date today = DateUtils.convertToDate(DateUtils.getDate(0,"dd-MM-YYYY", BOConstants.GMT_FOUR),"dd-MM-YYYY");
            long timeDiff = DateUtils.getDateDiff(today,dateTill, TimeUnit.MINUTES);
            // The banner is active but Valid Till Today is invalid => The image does not display on member site
            // Just get the image valid till today or more than current date
            if(timeDiff > 0){
                lstBannerSrc.add(imgSrc);
            }
        }
        return lstBannerSrc;
    }

    public void switchView(String viewType) {
        switch (viewType) {
            case "Old View":
                lblOldView.click();
                ddbType.isDisplayed(2);
                break;
            case "New View":
                lblNewView.click();
                ddbType.isDisplayed(2);
                break;
        }

    }

    public List<String> getListData(int columnOrder, boolean isMoved){
        List<String> lst = new ArrayList<String>();
        String cellXpath;
        if (columnOrder < 1){
            System.out.println(String.format("Error: columnOrder %s is to be more than or equal to 1", columnOrder));
            return lst;
        }
        int i = 1;
        while(true) {
            cellXpath = String.format(tblResult.getxPathOfCell(1, columnOrder, i,null));
            Cell cell = Cell.xpath(cellXpath);
            if (!cell.isDisplayedShort(2)){
                return lst;
            }
            if (cell.getAttribute("textContent").equalsIgnoreCase(""))
            {
//                lst.add(cell.getAttribute("textContent"));
                lst.add(String.valueOf(i));

            }
            if (isMoved){
                if (!cell.isDisplayedShort(2)) {
                    cell.scrollToThisControl(true);
                }
            }
            i += 1;
        }
    }

    public List<String> getListBanners(int colBanner, int colValidTill) {
        List<String> lstBanner = getListData(colBanner,true);
        List<String> lstBannerSrc = new ArrayList<>();
        for(String imgItem:lstBanner) {
            String cellImgXpath = String.format(tblResult.getxPathOfCell(1, colBanner, Integer.parseInt(imgItem),"img"));
            String imgSrc = Cell.xpath(cellImgXpath).getAttribute("src");
            imgSrc = imgSrc.split("image")[1];
            String cellValidTillXpath = String.format(tblResult.getxPathOfCell(1, colValidTill, Integer.parseInt(imgItem),null));
            String validTill = Cell.xpath(cellValidTillXpath).getText();
            Date dateTill = DateUtils.convertToDate(validTill,"dd-MM-YYYY");
            Date today = DateUtils.convertToDate(DateUtils.getDate(0,"dd-MM-YYYY", BOConstants.GMT_FOUR),"dd-MM-YYYY");
            long timeDiff = DateUtils.getDateDiff(today,dateTill, TimeUnit.MINUTES);
            // The banner is active but Valid Till Today is invalid => The image does not display on member site
            // Just get the image valid till today or more than current date
            if(timeDiff > 0){
                lstBannerSrc.add(imgSrc);
            }
        }
        return lstBannerSrc;
    }
}
