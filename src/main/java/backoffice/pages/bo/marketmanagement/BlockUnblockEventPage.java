package backoffice.pages.bo.marketmanagement;

import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.*;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BlockUnblockEventPage extends HomePage {

    public DropDownBox ddbUplineUser = DropDownBox.xpath("//div[@class='row mx-0']/div[1]//select[1]");
    public DropDownBox ddbSport = DropDownBox.xpath("//div[@class='row mx-0']/div[2]//select[1]");
    public Label lblUplineUser = Label.xpath("//div[@class='row mx-0']/div[1]//select[1]");
    public Label lblSport = Label.xpath("//div[@class='row mx-0']/div[2]//select[1]");
    Label lblDownlineUsers = Label.xpath("//table[@aria-describedby='block-unblock-table']//div[@class='ps-content']//div[contains(@class,'child-item')]//span[2]");
    Label lblCompetitions = Label.xpath("//table[@aria-describedby='block-unblock-table']//div[@class='d-flex align-items-center competition-col']//span[2]");
    Label lblEvents = Label.xpath("//table[@aria-describedby='block-unblock-table']//div[@class='d-flex justify-content-between']//span[1]");
    Label lblEventDateTime = Label.xpath("//table[@aria-describedby='block-unblock-table']//div[@class='d-flex justify-content-between mt-2']//div[2]//span");
    Button btnOldEvent = Button.xpath("//button[text()='Old Events ']");
    Button btnToday = Button.xpath("//button[text()='Today ']");
    Button btnTomorrow = Button.xpath("//button[text()='Tomorrow ']");
    Button btnFuture = Button.xpath("//button[text()='Future ']");

    public void filter(String userUpline, String sport, String filterPeriod) {
        if (!userUpline.isEmpty()) {
            ddbUplineUser.selectByVisibleText(userUpline);
        }
        if (!sport.isEmpty()) {
            ddbSport.selectByVisibleText(sport);
        }
        if (!filterPeriod.isEmpty()) {
            switch (filterPeriod.toLowerCase()) {
                case "old events":
                    btnOldEvent.click();
                case "tomorrow":
                    btnTomorrow.click();
                case "future":
                    btnFuture.click();
                default:
                    btnToday.click();
            }
        }
    }

    public List<String> getListDownlineUsers() {
        List<String> lstUsers = new ArrayList<>();
        for (int i = 1; i <= lblDownlineUsers.getWebElements().size(); i++) {
            String xpath = String.format("//table[@aria-describedby='block-unblock-table']//div[@class='ps-content']//div//div[%s]//span[2]", i+2);
            Label lblUserDownline = Label.xpath(xpath);
            lblUserDownline.scrollToThisControl(true);
            lstUsers.add(lblUserDownline.getText());
        }
        return lstUsers;
    }

    public List<String> getListCompetition() {
        List<String> lstCompetitions = new ArrayList<>();
        for (int i = 1; i <= lblCompetitions.getWebElements().size(); i++) {
            String xpath = String.format("//table[@aria-describedby='block-unblock-table']//tr[%s]//div[@class='d-flex align-items-center competition-col']//span[2]", i);
            Label lblCompetition = Label.xpath(xpath);
            lblCompetition.scrollToThisControl(true);
            lstCompetitions.add(lblCompetition.getText());
        }
        return lstCompetitions;
    }

    public List<String> getListEvent() {
        List<String> lstEvents = new ArrayList<>();
        for (int i = 1; i <= lblEvents.getWebElements().size(); i++) {
            String xpath = String.format("//table[@aria-describedby='block-unblock-table']//tr[%s]//div[@class='d-flex justify-content-between']//span[1]", i);
            Label lblEvent = Label.xpath(xpath);
            lblEvent.scrollToThisControl(true);
            lstEvents.add(lblEvent.getText());
        }
        return lstEvents;
    }

    public List<String> getListEventDateTime() {
        List<String> lstEventDateTime = new ArrayList<>();
        for (int i = 1; i <= lblEventDateTime.getWebElements().size(); i++) {
            String xpath = String.format("//table[@aria-describedby='block-unblock-table']//tr[%s]//div[@class='d-flex justify-content-between mt-2']//div[2]//span", i);
            Label lblEventDateTime = Label.xpath(xpath);
            lblEventDateTime.scrollToThisControl(true);
            lstEventDateTime.add(lblEventDateTime.getText());
        }
        return lstEventDateTime;
    }
}
