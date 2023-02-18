package backoffice.pages.bo.operations;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import backoffice.controls.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import backoffice.pages.bo.home.HomePage;
import java.util.List;

public class AnnouncementManagementPage extends HomePage {
    public Label lblTitlePage = Label.xpath("//div[@id='header']//span");
    public Button btnSave = Button.xpath("//button[contains(@class,'btn-submit')]");
    public Button btnClear = Button.xpath("//button[contains(@class,'btn-clear')]");
    public TextBox txtEnglish = TextBox.xpath("//body[contains(@class,'cke_editable')]");
    public TextBox txtTranslate = TextBox.id("cke_2_contents");
    public DropDownBox ddbSeenBy = DropDownBox.xpath("//div[@class='action-editor']//div[contains(@class,'seen-by')]//select");
    public DropDownBox txtSequence = DropDownBox.xpath("//div[@class='action-editor']//div[contains(@class,'sequence')]//select");
    public int colInfo = 1;
    public int colEnglish = 2;
    public Table tblAnnouncementReport = Table.xpath("//div[@class='table-announcement']//table",2);
    public enum Actions {SHOW, HIDE, EDIT, DELETE}

    private void inputText(TextBox txt, String value, int iFrameIndex){
        WebElement e = DriverManager.getDriver().findElement(By.tagName("iframe"));
       DriverManager.getDriver().switchToFrame(e);
       // DriverManager.getDriver().findElements(By.tagName("iframe[contains(@class,'cke-wysiwyg-frame cke-reset')]"))
        txt.click();
        txt.sendKeys(value);
        com.paltech.driver.DriverManager.getDriver().switchToParentFrame();
    }

    private String getText(TextBox txt, int iFrameIndex){
        com.paltech.driver.DriverManager.getDriver().switchToFrame(iFrameIndex);
        String text = txt.getText();
        com.paltech.driver.DriverManager.getDriver().switchToParentFrame();
        return text;
    }

    public void add(String eMessage, String seenBy, String sequence){
        if(!eMessage.isEmpty()){
       inputText(txtEnglish,eMessage,0);}
        if(!seenBy.isEmpty())
            ddbSeenBy.selectByVisibleText(seenBy);
        if(!sequence.isEmpty())
            txtSequence.selectByVisibleContainsText(sequence);
        btnSave.click();

    }

    public boolean isShowButtonDisplayed (String announcementMsg){
        List<String> lstAnnouncementMsg = tblAnnouncementReport.getColumn(colEnglish,false);
        for(int i = 0; i<lstAnnouncementMsg.size(); i++) {
            if (lstAnnouncementMsg.get(i).equalsIgnoreCase(announcementMsg)) {
               return tblAnnouncementReport.getControlOfCell(1, colInfo, i + 1, "div[contains(@class,'btn-edit')]//i[contains(@class,'fa-eye-slash')]").isDisplayed();
               // return actualText.equals(BOConstants.Operations.AnnouncementManagement.BTN_SHOW);
            }
        }
        return false;
    }

    public void updateAnnouncementInfo(String announcementMsg,Actions action,String newAnnouncementMsg, String seenBy, String sequence){
        List<String> lstAnnouncementMsg = tblAnnouncementReport.getColumn(2,false);
        BaseElement baseElement;
        for(int i = 0; i<lstAnnouncementMsg.size(); i++) {
            if (lstAnnouncementMsg.get(i).contains(announcementMsg)) {
                switch (action) {
                    case HIDE:
                        baseElement =tblAnnouncementReport.getControlOfCell(1, colInfo, i + 1, "div[contains(@class,'btn-edit')]//i[contains(@class,'fa-eye')]");
                        baseElement.scrollToThisControl(true);
                        baseElement.click();
                        //tblAnnouncementReport.getControlOfCell(1, colInfo, i + 1, "div[contains(@class,'btn-edit')]//i[contains(@class,'fa-eye')]").click();
                        break;
                    case EDIT:
                        baseElement =tblAnnouncementReport.getControlOfCell(1, colInfo, i + 1, "div[contains(@class,'btn-edit')]//i[contains(@class,'fa-pencil-alt')]");
                        baseElement.scrollToThisControl(true);
                        baseElement.click();
                       // tblAnnouncementReport.getControlOfCell(1, colInfo, i + 1, "div[contains(@class,'btn-edit')]//i[contains(@class,'fa-pencil-alt')]").click();
                        if(!newAnnouncementMsg.isEmpty()){
                            add(newAnnouncementMsg,seenBy,sequence);
                        }
                        break;
                    case DELETE:
                        baseElement =tblAnnouncementReport.getControlOfCell(1, colInfo, i + 1, "div[contains(@class,'btn-edit')]//i[contains(@class,'fa-trash-alt')]");
                        baseElement.scrollToThisControl(true);
                        baseElement.click();
                        //tblAnnouncementReport.getControlOfCell(1, colInfo, i + 1, "div[contains(@class,'btn-edit')]//i[contains(@class,'fa-trash-alt')]").click();
                        break;
                }
                if(!seenBy.isEmpty() || !sequence.isEmpty()){
                    String xPath;
                    DropDownBox ddp;
                    if(!seenBy.isEmpty()){
                        tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[contains(@class,'seen-by')]//select").scrollToThisControl(true);
                        xPath = tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[contains(@class,'seen-by')]//select").getLocator().toString().split("By.xpath: ")[1];
                        ddp =DropDownBox.xpath(xPath);

                        ddp.selectByVisibleText(seenBy);
                    }
                    if(!sequence.isEmpty()){
                        tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[contains(@class,'sequence')]//select").scrollToThisControl(true);
                        xPath = tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[contains(@class,'sequence')]//select").getLocator().toString().split("By.xpath: ")[1];
                        ddp =DropDownBox.xpath(xPath);
                        ddp.selectByVisibleText(sequence);
                    }

                    tblAnnouncementReport.getControlOfCell(1, colInfo, i + 1, "div[contains(@class,'btn-action')]//button").click();
                }
                break;
            }
        }

    }


    public boolean verifyAnnouncementInfo(String announcementMsg, String createDate, String seenBy, String sequence){
        List<String> lstAnnouncementMsg = tblAnnouncementReport.getColumn(2,false);
        for(int i = 0; i<lstAnnouncementMsg.size(); i++)
        {
            if(lstAnnouncementMsg.get(i).contains(announcementMsg)){
                System.out.println(String.format("---=>Announcement %s display in the report---",announcementMsg));
                tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[@class='title']//span").scrollToThisControl(true);
                String actualCreateDate = tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[@class='title']//span").getText();
                if(!createDate.isEmpty()){
                    if(!actualCreateDate.equals(createDate))
                    {
                        System.err.println("FAILED! Create date is incorrect");
                        return false;
                    }
                }

                String xPath;
                DropDownBox ddp;
                if(!seenBy.isEmpty())
                {
                    xPath = tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[contains(@class,'seen-by')]//select").getLocator().toString().split("By.xpath: ")[1];
                    ddp =DropDownBox.xpath(xPath);
                    String actualSeenBy = ddp.getFirstSelectedOption();
                    if(!actualSeenBy.equals(seenBy)){
                        System.err.println(String.format("FAILED! Seen By is incorrect. Expected is %s but found %s",seenBy,actualSeenBy));
                        return false;
                    }
                }
                if(!sequence.isEmpty())
                {
                    xPath = tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[contains(@class,'sequence')]//select").getLocator().toString().split("By.xpath: ")[1];
                    ddp =DropDownBox.xpath(xPath);
                    String actualSequence = ddp.getFirstSelectedOption();
                    if(!actualSequence.equals(sequence)){
                        System.err.println(String.format("FAILED! Seen By is incorrect. Expected is %s but found %s",sequence,actualSequence));
                        return false;
                    }
                }
                if(!tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[contains(@class,'btn-edit')]//i[contains(@class,'fa-eye')]").isDisplayed()){
                    System.err.println("FAILED! Hide button not display");
                    return false;
                }

                if(!tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[contains(@class,'btn-edit')]//i[contains(@class,'fa-pencil-alt')]").isDisplayed()){
                    System.err.println("FAILED! Edit button not display");
                    return false;
                }
                if(!tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[contains(@class,'btn-edit')]//i[contains(@class,'fa-trash-alt')]").isDisplayed()){
                    System.err.println("FAILED! Del button not display");
                    return false;
                }
                if(!tblAnnouncementReport.getControlOfCell(1,colInfo,i+1,"div[contains(@class,'btn-action')]//button").isDisplayed()){
                    System.err.println("FAILED! Set button not display");
                    return false;
                }
                return true;
            }
        }
        System.out.println(String.format("Announcement %s is not displayed in the report",announcementMsg));
        return false;
    }


}
