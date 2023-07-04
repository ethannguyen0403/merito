package agentsite.pages.agentmanagement.depositwithdrawal;

import agentsite.controls.Table;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewLogPopup {
    public Popup popupWithdrawal = Popup.id("transfer-log-dialog");
    public int colDateTime = 1;
    public int colAction = 2;
    public int colAmount = 3;
    public int colRemark = 4;
    public int colPerformBy = 5;
    public Button btnClose = Button.xpath("//button[contains(@class,'btn btn-cancel')]");
    private Label lblPopupTitle = Label.xpath("//div[contains(@class,'modal-header')]//div[@class='title']");
    private int colTotal = 5;
    public Table tblLog = Table.xpath("//div[@id='transfer-log-dialog']//table[@class='ptable report']", colTotal);

    public String getTitle() {
        return lblPopupTitle.getText();
    }

    public List<String> getHeaderTable() {
        return tblLog.getColumnNamesOfTable();
    }

    public List<ArrayList<String>> defineTransferLogbyViewLogData(List<ArrayList<String>> viewLogData) {
        String auditType = "";
        String action = "";
        String performBy = "";
        String remark = "";
        String dateTime = "";
        String newValue = "";
        String username = "";
        String toUserName = "";
        List<ArrayList<String>> expectedData = new ArrayList<>();
        for (int i = 0; i < viewLogData.size() * 2; ) {
            action = viewLogData.get(i).get(colAction - 1);

            performBy = viewLogData.get(i).get(colPerformBy - 1);
            remark = viewLogData.get(i).get(colRemark - 1);
            dateTime = viewLogData.get(i).get(colDateTime - 1);
            newValue = viewLogData.get(i).get(colAmount - 1);
            String[] words;
            if (action.contains("deposits")) {
                auditType = "Deposit";
                words = action.split("deposits");
                username = words[0];
                toUserName = words[1].split("as")[0].substring(3);
                expectedData.add(i, new ArrayList<String>(Arrays.asList(Integer.toString(i + 1), dateTime, username, username, auditType, String.format("Deposit Amount To %s", toUserName.trim()), "-", String.format("-%s", newValue), performBy, remark)));
                expectedData.add(i + 1, new ArrayList<String>(Arrays.asList(Integer.toString(i + 2), dateTime, toUserName, toUserName, auditType, String.format("Deposit Amount From %s", username.trim()), "-", newValue, performBy, remark)));
            } else {
                auditType = "Withdraw";
                words = action.split("withdraws");
                username = words[0];
                toUserName = words[1].split("as")[0].substring(3);
                expectedData.add(i, new ArrayList<String>(Arrays.asList(Integer.toString(i + 1), dateTime, username, username, auditType, String.format("Withdraw Amount From %s", toUserName), "-", newValue, performBy, remark)));
                expectedData.add(i + 1, new ArrayList<String>(Arrays.asList(Integer.toString(i + 2), dateTime, toUserName, toUserName, auditType, String.format("Withdraw Amount To %s", username), "-", String.format("-%s", newValue), performBy, remark)));
            }
            i = i + 2;
        }
        return expectedData;
    }

    public void closePopup() {
        btnClose.click();
    }


}
