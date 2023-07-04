package backoffice.pages.bo.operations;

import backoffice.controls.DateTimePicker;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import backoffice.pages.bo.operations.component.AutoMappingPopup;
import backoffice.pages.bo.operations.component.ConfirmMapLiveStreamingPopup;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

import java.util.ArrayList;
import java.util.List;

public class LiveStreamingManagementPage extends HomePage {

    public TextBox txtDate = TextBox.name("dp");
    public DateTimePicker dtpDate = DateTimePicker.xpath(txtDate, "//bs-datepicker-container//bs-days-calendar-view");
    public DropDownBox ddbSport = DropDownBox.name("sports");
    public DropDownBox ddbFECompetition = DropDownBox.name("fa-competitions");
    public DropDownBox ddbProviderCompetition = DropDownBox.name("provider-competitions");
    public DropDownBox ddbFEEvent = DropDownBox.name("fa-events");
    public DropDownBox ddbProviderEvent = DropDownBox.name("provider-events");
    public Button btnSubmit = Button.xpath("//div[contains(@class,'submit-button')]/button[@name='submit']");
    public Button btnAutoMap = Button.xpath("//app-live-streaming-management//button[@name='auto-map']");
    public Button btnMap = Button.xpath("//app-live-streaming-management//button[text()='Map']");
    public int colFEEvent = 3;
    public int colFESelect = 4;
    //public ATable tblFairExchange =ATable.xpath("//div[contains(@class,'live-streaming')]//div[@class='row'][1]//div[contains(@class,'col')][1]//div[@class='custom-table'][1]",4);
    public StaticTable tblFairExchange = StaticTable.xpath("//div[contains(@class,'live-streaming')]//div[@class='row'][1]//div[contains(@class,'col')][1]//div[@class='custom-table'][1]",
            "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", 4);
    public int colProviderEvent = 4;
    public int colProviderSelect = 1;
    //public ATable tblProvider =ATable.xpath("//div[contains(@class,'live-streaming')]//div[@class='row'][1]//div[contains(@class,'col')][2]//div[@class='custom-table'][1]",4);
    public StaticTable tblProvider = StaticTable.xpath("//div[contains(@class,'live-streaming')]//div[@class='row'][1]//div[contains(@class,'col')][2]//div[@class='custom-table'][1]",
            "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", 4);
    public int colMappedListEventCol = 3;
    //  public ATable tblMappedList =ATable.xpath("//div[contains(@class,'live-streaming')]/div[contains(@class,'table-wrapper')]//div[@class='custom-table']",totalMappedListCol);
    public Label lblMappedListNoRecord = Label.xpath("//div[contains(@class,'live-streaming')]/div[contains(@class,'table-wrapper')]//div[@class='custom-table']//div[@class='text-center']");
    private int totalMappedListCol = 7;
    public StaticTable tblMappedList = StaticTable.xpath("//div[contains(@class,'live-streaming')]/div[contains(@class,'table-wrapper')]//div[@class='custom-table']",
            "div[contains(@class,'custom-table-body')]", "div[contains(@class,'custom-table-row')]", "div[contains(@class,'custom-table-cell')]", totalMappedListCol);

    public void filter(String date, String sport, String feCompetition, String feEvent, String providerCompetition, String providerEvent) {
        if (!date.isEmpty()) {
            dtpDate.selectDate(date, "yyyy-MM-dd");
        }
        if (!sport.isEmpty()) {
            ddbSport.selectByVisibleText(sport);
        }
        if (!feCompetition.isEmpty()) {
            ddbFECompetition.selectByVisibleText(feCompetition);
        }
        if (!feEvent.isEmpty()) {
            ddbFEEvent.selectByVisibleText(feEvent);
        }
        if (!providerCompetition.isEmpty()) {
            ddbProviderCompetition.selectByVisibleText(providerCompetition);
        }
        if (!providerEvent.isEmpty()) {
            ddbProviderEvent.selectByVisibleText(providerEvent);
        }
        btnSubmit.click();
        waitSpinIcon();
    }

    public AutoMappingPopup clickAutoMap() {
        btnAutoMap.click();
        return new AutoMappingPopup();
    }

    public void selectFEEvent(String event) {
        List<String> lstFEEvents = tblFairExchange.getColumn(colFEEvent, true);
        for (int i = 0; i < lstFEEvents.size(); i++) {
            if (lstFEEvents.get(i).equalsIgnoreCase(event)) {
                tblFairExchange.getControlOfCell(1, colFESelect, i + 1, "span[@class='checkmark']").click();
                return;
            }
        }
        System.out.println(String.format("The event %s does not display in the list", event));
    }

    public void selectProviderEvent(String event) {
        List<String> lstFEEvents = tblProvider.getColumn(colProviderEvent, true);
        for (int i = 0; i < lstFEEvents.size(); i++) {
            if (lstFEEvents.get(i).equalsIgnoreCase(event)) {
                tblProvider.getControlOfCell(1, colProviderSelect, i + 1, "span[@class='checkmark']").click();
                return;
            }
        }
        System.out.println(String.format("The event %s does not display in the list", event));
    }

    public ConfirmMapLiveStreamingPopup map(String feEvent, String lcEvent) {
        selectFEEvent(feEvent);
        selectProviderEvent(lcEvent);
        btnMap.click();
        return new ConfirmMapLiveStreamingPopup();
    }

    public List<String> getMappedEventList(int column) {
        List<String> lstInfo = new ArrayList<>();
        if (lblMappedListNoRecord.isDisplayed()) {
            lstInfo.add(lblMappedListNoRecord.getText());
            return lstInfo;
        }
        return tblMappedList.getColumn(column, true);

    }


}
