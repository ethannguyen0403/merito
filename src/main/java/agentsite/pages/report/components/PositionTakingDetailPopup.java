package agentsite.pages.report.components;

import agentsite.controls.Table;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;

import java.util.ArrayList;
import java.util.List;

public class PositionTakingDetailPopup {
    public int tblReportTotalCol = 9;
    public int colSettle = 1;
    public int colDescription = 2;
    public int colType = 3;
    public int colOdds = 4;
    public int colPTVolumn = 5;
    public int colStatus = 6;
    public int colDirectDownlineResult = 8;
    public int colDirectDownlineTax = 9;
    Button btnFullScreen = Button.xpath("//div[@class='modal-header']/button[@class='fullScreen']");
    Button btnClosePopup = Button.xpath("//div[@class='modal-header']/button[@class='close']");
    Button btnClose = Button.xpath("//button[contains(@class,'btn-cancel')]");
    Label lblTitle = Label.xpath("//div[@class='modal-header']/div[@class='ng-binding']");
    String tblReportXpath = "//div[contains(@class,'modal-content')]//table[contains(@class,'ptable report')]";
    Table tblReport = Table.xpath(tblReportXpath, tblReportTotalCol);
    private int staticColTotal = 7;

    public void closePopup() {
        btnClosePopup.click();
    }

    public void fullScreenPopup() {
        btnFullScreen.click();
    }

    public List<String> getTotalVolumnPnlTax() {
        //fullScreenPopup();
        List<String> returnData = new ArrayList<>();
        double ptVolumn = 0.0;
        double directDownlineResult = 0.0;
        double directDownlineTax = 0.0;
        List<ArrayList<String>> reportData = tblReport.getRowsWithoutHeader(false);
        for (int i = 0, n = reportData.size() - 1; i < n; i++) {
            String value = reportData.get(i).get(colPTVolumn - 1);
            if (value.contains("-"))
                directDownlineTax = directDownlineTax + Double.parseDouble(reportData.get(i).get(colDirectDownlineTax - 1));
            else {
                ptVolumn = ptVolumn + Double.parseDouble(reportData.get(i).get(colPTVolumn - 1));
                directDownlineResult = directDownlineResult + Double.parseDouble(reportData.get(i).get(colDirectDownlineResult - 1));
            }
        }
        returnData.add(String.format("%.2f", ptVolumn));
        returnData.add(String.format("%.2f", directDownlineResult));
        returnData.add(String.format("%.2f", directDownlineTax));
        return returnData;

    }
}
