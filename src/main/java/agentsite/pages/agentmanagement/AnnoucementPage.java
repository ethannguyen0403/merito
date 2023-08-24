package agentsite.pages.agentmanagement;

import agentsite.controls.DateTimePickerOld;
import agentsite.controls.TableNoBody;
import agentsite.pages.HomePage;
import agentsite.pages.components.ConfirmPopup;
import com.paltech.element.common.*;

public class AnnoucementPage extends HomePage {
    public Button btnAddAnnouncement = Button.xpath("//app-agency-announcement//button[@class='hide pbtn']");
    public Label lblInfo = Label.xpath("//app-agency-announcement//span[@class='title']");
    public TextBox textArea = TextBox.xpath("//app-agency-announcement//textarea");
    public Button btnSave = Button.xpath("//app-agency-announcement//div[@class='submittion-area']//button[@class='pbtn']");
    public Button btnCancel = Button.xpath("//app-agency-announcement//div[@class='submittion-area']//button[@class='pbtn']");
    public TableNoBody tblAnnouncement = TableNoBody.xpath("//app-agency-announcement//table[@class='parent']", 3);
    int colMessage = 1;
    int colAction = 2;
    int colConfig = 3;

    public AnnoucementPage(String types) {
        super(types);
    }

    public void addAnnouncement(String message) {
        if (!textArea.isDisplayed(5)) {
            btnAddAnnouncement.click();

        }
        textArea.sendKeys(message);
        btnSave.click();
        waitingLoadingSpinner();
    }

    public boolean isAnnouncementDisplay(String message) {
        int index = getAnnouncementIndex(message);
        if (index == 0) {
            System.out.println("DEBUG: can NOT found the announcement " + message);
            return false;
        }
        System.out.println("DEBUG: found the announcement " + message);
        return true;
    }

    private int getAnnouncementIndex(String message) {
        int i = 1;
        Label lblMessage;
        while (true) {
            lblMessage = Label.xpath(tblAnnouncement.getxPathOfCell(1, colMessage, i, null));
            if (!lblMessage.isDisplayed()) {
                System.out.println("DEBUG: can NOT found the announcement " + message);
                return 0;
            }
            // String messageaa = lblMessage.getText().trim();
            if (lblMessage.getText().trim().equals(message))
                return i;
            i = i + 1;
        }
    }

    private void setSeenBy(int index, String specificPlayer) {
        RadioButton rbAll;
        RadioButton rbSpecificPlayers;
        TextBox txtSpecificPlayers;
        Icon icPlus;
        if (specificPlayer.equalsIgnoreCase("ALL")) {
            rbAll = RadioButton.xpath(tblAnnouncement.getxPathOfCell(1, colConfig, index, "span[1]/input[1]"));
            if (!rbAll.isSelected())
                rbAll.click();
        } else {
            rbSpecificPlayers = RadioButton.xpath(tblAnnouncement.getxPathOfCell(1, colConfig, index, "span[1]/input[2]"));
            txtSpecificPlayers = TextBox.xpath(tblAnnouncement.getxPathOfCell(1, colConfig, index, "div[@class='specificPlayer']//input"));
            icPlus = Icon.xpath(tblAnnouncement.getxPathOfCell(1, colConfig, index, "a[contains(@class,'addButton')]"));
            if (!rbSpecificPlayers.isSelected())
                rbSpecificPlayers.click();
            txtSpecificPlayers.sendKeys(specificPlayer);
            icPlus.click();

        }
    }

    public void updateAnnoucement(String announcement, boolean isActive, String newMessage, String from, String to, String seenBy) {
        int index = getAnnouncementIndex(announcement);
        if (index == 0) {
            System.out.println("DEBUG: Message does not display in the list");
            return;
        }
        setActiveStatus(index, isActive);
        if (!newMessage.isEmpty()) {
            editAnnoucement(index, newMessage);
        }
        setAnnouncementInRange(index, from, to, true);
        setSeenBy(index, seenBy);
    }

    private void setActiveStatus(int index, boolean isActive) {
        CheckBox control = CheckBox.xpath(tblAnnouncement.getxPathOfCell(1, colAction, index, "span[@class='custom-control custom-switch']"));
        if (isActive)
            control.click();
    }

    private void editAnnoucement(int index, String newMessage) {
        Button btnEdit = Button.xpath(String.format("//app-agency-announcement//table[@class='parent']//tr[%s]//td[%s]//p[1]/button[@class='pbtn']", index, colAction));
        btnEdit.click();
        if (!newMessage.isEmpty()) {
            textArea.sendKeys(newMessage);
        }
    }

    private String setAnnouncementInRange(int index, String from, String to, boolean isCloseMessage) {
        String messsage = "";
        Button btnSet;
        DateTimePickerOld dtpFrom;
        TextBox txtFrom;
        TextBox txtTo;
        DateTimePickerOld dtpTo;
        if (!from.isEmpty()) {
            txtFrom = TextBox.xpath(tblAnnouncement.getxPathOfCell(1, colConfig, index, "table//tr[1]//input"));
            dtpFrom = DateTimePickerOld.xpath(txtFrom, "//owl-date-time-container");
            dtpFrom.selectDate(from, "d/MMM/yyy hh:mm");
        }
        if (!to.isEmpty()) {
            txtTo = TextBox.xpath(tblAnnouncement.getxPathOfCell(1, colConfig, index, "table//tr[2]//input"));
            dtpTo = DateTimePickerOld.xpath(txtTo, "//owl-date-time-container");
            dtpTo.selectDate(to, "d/MMM/yyy hh:mm");
        }
        btnSet = Button.xpath(tblAnnouncement.getxPathOfCell(1, colConfig, index, "button[@class='pbtn']"));
        btnSet.click();
        ConfirmPopup ConfirmPopup = new ConfirmPopup();
        messsage = ConfirmPopup.getContentMessage();
        if (isCloseMessage)
            ConfirmPopup.confirm();
        return messsage;

    }

    public String deleteAnnouncement(String announcement, boolean isConfirmDelete) {
        int index = getAnnouncementIndex(announcement);
        Button btnDelete = Button.xpath(tblAnnouncement.getxPathOfCell(1, colAction, index, "p[2]/button[@class='pbtn']"));
        btnDelete.click();
        ConfirmPopup confirmPopup = new ConfirmPopup();
        String message = confirmPopup.getContentMessage();
        if (isConfirmDelete)
            confirmPopup.confirm();
        return message;
    }
}
