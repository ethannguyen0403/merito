package agentsite.pages.all.marketsmanagement.liquiditythreshold;

import com.paltech.element.common.*;
import agentsite.controls.Table;
import agentsite.pages.all.marketsmanagement.CurrentBlockingPage;

public class HistoryPopup extends CurrentBlockingPage {
    private Label lblTitle = Label.xpath("//app-show-history-dialog//div[contains(@class,'modal-header')]//div[@class='title']");
    private Button btnClose = Button.xpath("//app-show-history-dialog//div[contains(@class,'modal-header')]//button");
    private int totalCol = 4;
    public int colAction = 2;
    public int colLastUpdateDate = 3;
    public int colLastUpdateBy = 4;
    public Table tblReport = Table.xpath("//app-show-history-dialog//div[contains(@class,'modal-body')]//table[@class='ptable report']",4);


    public String getTitle(){
        return lblTitle.getText();
    }
    public void closePopup()
    {
        btnClose.click();
    }

}
