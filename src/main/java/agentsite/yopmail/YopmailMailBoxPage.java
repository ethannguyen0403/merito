package agentsite.yopmail;

import com.paltech.element.common.Label;
import controls.Table;

import java.util.ArrayList;
import java.util.List;

public class YopmailMailBoxPage extends YopmailPage {
    private Label lblMailTitle = Label.xpath("//body//div[contains(@class,'ellipsis nw b f18')]");
    private Label lblNameClient = Label.xpath("//body//div[@id='mail']/div[1]/div[1]");
    private Label lblPleaseFindEnclosed = Label.xpath("//body//div[@id='mail']/div[1]/div[2]");
    private Table tblReport = Table.xpath("//body//div[@id='mail']/div[1]//table[1]", 9);
    private Label lblTherefore = Label.xpath("//body//div[@id='mail']/div[1]//table[2]//tr[1]");
    private Label lblThisAmountShallbeKIVToTheNextPeriod = Label.xpath("//body//div[@id='mail']/div[1]//table[2]//tr[3]");

    public ArrayList<String> getInfo(){
        lblMailTitle.waitForControlInvisible(5,1);
        ArrayList<String> lstInfo = new ArrayList<>();
        lstInfo.add(0,lblMailTitle.getText());
        lstInfo.add(lblNameClient.getText());
        lstInfo.add(lblPleaseFindEnclosed.getText());
        lstInfo.add(lblTherefore.getText());
        lstInfo.add(lblThisAmountShallbeKIVToTheNextPeriod.getText());
        return lstInfo;
    }

    public ArrayList<String> getbetListHeader(){
        return tblReport.getHeaderNameOfRows();
    }

    public List<ArrayList<String>> getbetListInfo(){
        return tblReport.getRows(false);
    }

    public static List<ArrayList<String>> getFirstActiveMailBox(String url, String mailAddress){
        YopmailPage yopmailPage = navigateToYopmail(url);
        YopmailMailBoxPage yopmailMailBoxPage = yopmailPage.navigateMailBox(mailAddress);
        List<ArrayList<String>> contentInfoLst = new ArrayList<>();
        contentInfoLst.add(yopmailMailBoxPage.getInfo());
        contentInfoLst.add(yopmailMailBoxPage.getbetListHeader());
        for (ArrayList<String> row:yopmailMailBoxPage.getbetListInfo()
        ) {
            contentInfoLst.add(row);
        }
        return contentInfoLst;
    }


}
