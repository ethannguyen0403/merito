package backoffice.pages.bo.marketmanagement;

import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.*;
import com.paltech.utils.DateUtils;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    Label lblNoRecordFound = Label.xpath("//div[text()='No record found.']");
    Label lblDetails = Label.xpath("//table[@aria-describedby='block-unblock-table']//tbody[1]//tr[1]//span[text()='Details']");
    Label lblDetailsTitle = Label.xpath("//div[@class='blocking-event-log']//h6");
    Button btnOldEvent = Button.xpath("//button[text()='Old Events ']");
    Button btnToday = Button.xpath("//button[text()='Today ']");
    Button btnTomorrow = Button.xpath("//button[text()='Tomorrow ']");
    Button btnFuture = Button.xpath("//button[text()='Future ']");
    TextBox txtDownlineFilter = TextBox.xpath("//input[@placeholder='Search By Username/Nickname']");
    TextBox txtEventFilter = TextBox.xpath("//input[@placeholder='Search By Event ID/Name']");

    CheckBox cbSelectAllFavourite = CheckBox.xpath("//span[text()=' Select All ']//input");
    CheckBox cbSelectAllUnfavourite = CheckBox.xpath("//span[text() = 'Select All']//..//following-sibling::input");
    CheckBox cbDownlineChecked = CheckBox.xpath("//div[@class='child-item d-flex justify-content-between align-items-center ng-star-inserted active']//div[@class='pl-2']//following-sibling::input");

    CheckBox cbSeletAllEvent = CheckBox.xpath("//table[@aria-describedby='block-unblock-table']//span[text()='Event']//..//..//input");

    public void filter(String userUpline, String sport, String filterPeriod) {
        if (!userUpline.isEmpty()) {
            ddbUplineUser.selectByVisibleText(userUpline);
            waitSpinIcon();
        }
        if (!sport.isEmpty()) {
            ddbSport.selectByVisibleText(sport);
            waitSpinIcon();
        }
        if (!filterPeriod.isEmpty()) {
            switch (filterPeriod.toLowerCase()) {
                case "old events":
                    btnOldEvent.click();
                    break;
                case "tomorrow":
                    btnTomorrow.click();
                    break;
                case "future":
                    btnFuture.click();
                    break;
                default:
                    btnToday.click();
                    break;
            }
            waitSpinIcon();
        }
    }

    public String defineDownLineAlias(String level, String userCode, String loginId){
        if(!loginId.isEmpty())
            return String.format("%s:%s (%s)",level,userCode,loginId);
        return String.format("%s:%s",level,userCode);
    }

    /**
     * This method to get full downline list : [Level:Account]
     * @return the list downline full text
     */
    public List<String> getFullListDownlineUsers() {
        List<String> lstUsers = new ArrayList<>();
        for (int i = 1; i <= lblDownlineUsers.getWebElements().size(); i++) {
            String xpath = String.format("//table[@aria-describedby='block-unblock-table']//div[@class='ps-content']//div//div[%s]/div", i + 2);
            Label lblUserDownline = Label.xpath(xpath);
            lblUserDownline.scrollToThisControl(true);
            lstUsers.add(lblUserDownline.getText());
        }
        return lstUsers;
    }

    public ArrayList<String>[] getListCompetitionAndEvent() {
        ArrayList<String>[] arr = new ArrayList[2];
        ArrayList<String> lstCompetitions = new ArrayList<>();
        ArrayList<String> lstEvents = new ArrayList<>();
        for (int i = 1; i <= lblCompetitions.getWebElements().size(); i++) {
            String xpathCompetition = String.format("//table[@aria-describedby='block-unblock-table']//tr[%s]//div[@class='d-flex align-items-center competition-col']//span[2]", i);
            String xpathEvent = String.format("//table[@aria-describedby='block-unblock-table']//tr[%s]//div[@class='d-flex justify-content-between']//span[1]", i);
            Label lblEvent = Label.xpath(xpathEvent);
            Label lblCompetition = Label.xpath(xpathCompetition);
            lblCompetition.scrollToThisControl(true);
            lstCompetitions.add(lblCompetition.getText());
            lblEvent.scrollToThisControl(true);
            lstEvents.add(lblEvent.getText());
        }
        arr[0] = lstCompetitions;
        arr[1] = lstEvents;
        return arr;
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

    public void filterDownlineUser(String userName) {
        txtDownlineFilter.sendKeys(userName);
    }

    public void filterEvent(String eventName) {
        txtEventFilter.sendKeys(eventName);
        waitSpinIcon();
    }

    public boolean isFilterDownlineUserAppears(String userName) {
        Label lblUserDownline = Label.xpath("//table[@aria-describedby='block-unblock-table']//div[@class='ps-content']//div//div[3]//span[2]");
        lblUserDownline.scrollToThisControl(true);
        return userName.equalsIgnoreCase(lblUserDownline.getText());
    }

    public boolean isNoRecordFoundDisplays() {
        return lblNoRecordFound.isDisplayed();
    }

    public boolean isFilterEventAppears(String eventNameOrId) {
        Label lblEvent = Label.xpath("//table[@aria-describedby='block-unblock-table']//tbody[1]//tr[1]//td[2]");
        lblEvent.scrollToThisControl(true);
        return lblEvent.getText().contains(eventNameOrId);
    }

    public void clickFavouriteIcon(String userName) {
        for (int i = 1; i <= lblDownlineUsers.getWebElements().size(); i++) {
            String xpath = String.format("//table[@aria-describedby='block-unblock-table']//div[@class='ps-content']//div//div[%s]//span[2]", i + 2);
            Label lblUserDownline = Label.xpath(xpath);
            if (lblUserDownline.getText().equalsIgnoreCase(userName)) {
                String xpathFavorite = String.format("//table[@aria-describedby='block-unblock-table']//div[@class='ps-content']//div//div[%s]//i", i + 2);
                Icon favoriteIcon = Icon.xpath(xpathFavorite);
                favoriteIcon.click();
            }
        }
    }

    public void clickUnfavouriteIcon(String userName) {
        for (int i = 1; i <= lblDownlineUsers.getWebElements().size(); i++) {
            String xpath = String.format("//table[@aria-describedby='block-unblock-table']//div[@class='ps-content']//div//div[%s]//span[2]", i);
            Label lblUserDownline = Label.xpath(xpath);
            if (lblUserDownline.getText().equalsIgnoreCase(userName)) {
                String xpathFavorite = String.format("//table[@aria-describedby='block-unblock-table']//div[@class='ps-content']//div//div[%s]//i", i);
                Icon favoriteIcon = Icon.xpath(xpathFavorite);
                favoriteIcon.click();
            }
        }
    }

    public boolean isUserAddedToFavourites(String userName) {
        String xpath = String.format("//div[@class='border-bottom']//span[text()='%s']", userName);
        Label userLabel = Label.xpath(xpath);
        return userLabel.isDisplayed();
    }

    public void clickSelectAllFavourite() {
        cbSelectAllFavourite.click();
    }

    public void clickSelectAllUnfavourite() {
        cbSelectAllUnfavourite.click();
    }

    public void clickSelectAllEvent() {
        cbSeletAllEvent.click();
    }

    public boolean isDownlineCheckboxChecked() {
        List<WebElement> list = cbDownlineChecked.getWebElements();
        for (WebElement webElement : list) {
            String attribute = webElement.getAttribute("checked");
            if (!attribute.equalsIgnoreCase("true")) {
                return false;
            }
        }
        return true;
    }

    public boolean isEventCheckboxChecked() {
        for (int i = 1; i <= lblEvents.getWebElements().size(); i++) {
            String xpath = String.format("//table[@aria-describedby='block-unblock-table']//tr[%s]//div[@class='d-flex justify-content-between']//input", i);
            CheckBox cbEvent = CheckBox.xpath(xpath);
            cbEvent.scrollToThisControl(true);
            String attribute = cbEvent.getAttribute("checked");
            if (!attribute.equalsIgnoreCase("true")) {
                return false;
            }
        }
        return true;
    }

    public void openDetailsStatusLog() {
        lblDetails.click();
        waitSpinIcon();
    }

    public boolean isDetailsStatusLogTitleDisplayedCorrect() {
        Label lblFirstEventName = Label.xpath("//table[@aria-describedby='block-unblock-table']//tr[1]//div[@class='d-flex justify-content-between']//span[1]");
        String expectedTitle = "Block/Unblock Events Details - " + lblFirstEventName.getText();
        return lblDetailsTitle.getText().equals(expectedTitle);
    }

    public boolean isTheDateOfEventDisplayCorrect(List<String> lstEventDateTime) {
        for (int i = 0; i < lstEventDateTime.size(); i++) {
            String rangeDate = DateUtils.getDate(-4,"yyyy-MM-dd","GMT-4");
            String toDay = DateUtils.getDate(0,"yyyy-MM-dd","GMT-4");
            String[] parts = lstEventDateTime.get(i).split(" ");
            if (LocalDate.parse(parts[0]).isBefore(LocalDate.parse(rangeDate)) && LocalDate.parse(parts[0]).isAfter(LocalDate.parse(toDay))){
                System.err.println("FAILED! Event Date Time is not in today expected: from:" + rangeDate +" to "+toDay+"\nactual:"+parts[0]);
                return false;
            }
        }
        return true;
    }
}
