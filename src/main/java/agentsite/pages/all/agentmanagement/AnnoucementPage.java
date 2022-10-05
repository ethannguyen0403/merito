package agentsite.pages.all.agentmanagement;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;
import agentsite.controls.Table;
import agentsite.pages.all.components.ConfirmPopup;
import agentsite.pages.all.components.LeftMenu;

import java.util.List;

public class AnnoucementPage extends LeftMenu {
    public Button btnAddAnnouncement = Button.xpath("//app-agency-announcement//button[@class='hide pbtn']");
    public Label lblInfo = Label.xpath("//app-agency-announcement//span[@class='title']");
    public TextBox textArea = TextBox.xpath("//app-agency-announcement//textarea");
    public Button btnSave = Button.xpath("//app-agency-announcement//div[@class='submittion-area']//button[@class='pbtn']");
    public Button btnCancel = Button.xpath("//app-agency-announcement//div[@class='submittion-area']//button[@class='pbtn']");
    public Table tblAnnouncement = Table.xpath("//app-agency-announcement//table[@class='parent']", 3);

    public void addAnnouncement(String message) {
        if (!textArea.isDisplayed()) {
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
        List<String> lstAnnoucement = tblAnnouncement.getColumn(1, true);
        for (int i = 0; i < lstAnnoucement.size(); i++) {
            if (lstAnnoucement.get(i).equals(message)) {
                System.out.println("DEBUG: found the announcement" + message);
                return i;
            }
        }
        System.out.println("DEBUG: can NOT found the announcement" + message);
        return 0;
    }

    public void activeAnnouncement(String announcement) {
        int index = getAnnouncementIndex(announcement);
        Link control = (Link) tblAnnouncement.getControlBasedValueOfDifferentColumnOnRow(announcement, 1, 1, 1,
                null, 2, "span[@class='custom-control custom-switch']//input", true, false);
        boolean status = control.isSelected();
        control.click();
    }

    public void updateAnnouncement(String announcement, String newMessage, String from, String to) {
        // int index = getAnnouncementIndex(announcement);

        String btnEditXpath = tblAnnouncement.getControlxPathBasedValueOfDifferentColumnOnRow(announcement, 1, 1, 1,
                null, 2, "p//button[@class='pbtn']", true, false);
        Button btnEdit = Button.xpath(btnEditXpath);
        btnEdit.click();
        if (!newMessage.isEmpty()) {
            textArea.sendKeys(newMessage);
        }
        if (!from.isEmpty()) {
            TextBox txtFrom = TextBox.xpath(tblAnnouncement.getControlxPathBasedValueOfDifferentColumnOnRow(announcement, 1, 1, 1,
                    null, 2, "p[1]/button[@class='pbtn']", true, false));
            txtFrom.click();
        }
        if (!to.isEmpty()) {

        }

    }

    public ConfirmPopup deleteAnnouncement(String announcement) {
        int index = getAnnouncementIndex(announcement);
        String btnEditXpath = tblAnnouncement.getControlxPathBasedValueOfDifferentColumnOnRow(announcement, 1, 1, 1,
                null, 2, "p[2]/button[@class='pbtn']", true, false);
        Button btnEdit = Button.xpath(btnEditXpath);
        btnEdit.click();
        return new ConfirmPopup();
    }
}
