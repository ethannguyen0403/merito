package backoffice.pages.bo.operations.component;

import backoffice.controls.bo.ATable;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;
import com.paltech.utils.DateUtils;
import membersite.objects.sat.Event;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ConfirmMapLiveStreamingPopup {
    public Popup popupAutoMapping = Popup.xpath("//app-dialog-confirm");
    public ATable tblConfirm = ATable.xpath("//app-dialog-confirm//div[@class='custom-table']", 3);
    public Label lblNote = Label.xpath("//app-dialog-confirm//ul[@class='text-msg']");
    private Label lblTitle = Label.xpath("//app-dialog-confirm//h4");
    private Button btnCancel = Button.xpath("//app-dialog-confirm//button[contains(@class,'btn-cancel')]");
    private Button btnOk = Button.xpath("//app-dialog-confirm//button[contains(@class,'btn-confirm')]");

    public String getTitle() {
        return lblTitle.getText();
    }

    public void clickCancelBtn() {
        btnCancel.click();
    }

    public ConfirmMapLiveStreamingPopup waitForPopUpAppear(){
        try {
            Thread.sleep(1500);
        }catch (Exception e){
        }
        return this;
    }

    public boolean isDisplayed() {
        popupAutoMapping.isInvisible(2);
        return popupAutoMapping.isDisplayed();
    }

    public void verifyEventInfoShowCorrect(Event fairEvent, Event providerEvent) {
        List<ArrayList<String>> mapEventInfoActual = tblConfirm.getRows(false);
        String feEventStartTime = DateUtils.convertMillisToDateTime(fairEvent.getStartTime(), "dd/MM/YYYY HH:mm:ss");
        String lcEventStartTime = DateUtils.convertMillisToDateTime(providerEvent.getStartTime(), "dd/MM/YYYY HH:mm:ss");
        Assert.assertEquals(mapEventInfoActual.get(0), new ArrayList<>(Arrays.asList("", "Start date", "Event name")), "FAILED! Header Table is incorrect");
        Assert.assertEquals(mapEventInfoActual.get(1), new ArrayList<>(Arrays.asList("Merito event", feEventStartTime, fairEvent.getEventName())), "FAILED! Merito event info is incorrect");
        Assert.assertEquals(mapEventInfoActual.get(2), new ArrayList<>(Arrays.asList("Provider event", lcEventStartTime, providerEvent.getEventName())), "FAILED! Provider event info is incorrect");
    }

}
