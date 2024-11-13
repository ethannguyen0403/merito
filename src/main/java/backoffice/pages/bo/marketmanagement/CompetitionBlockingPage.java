package backoffice.pages.bo.marketmanagement;

import backoffice.common.BOConstants;
import backoffice.controls.bo.StaticTable;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.List;

import static backoffice.common.BOConstants.NO_RECORDS_FOUND;

public class CompetitionBlockingPage extends HomePage {
    public Button btnBlock = Button.xpath("//div[@class='mt-1']/button[1]");
    public Button btnUnblock = Button.xpath("//div[@class='mt-1']/button[2]");
    public Label lblNote = Label.xpath("//span[@class='user-guide']");
    public TextBox txtSearchSport = TextBox.xpath("//div[@class='sport-container']//input");
    public TextBox txtSearchCompetition = TextBox.xpath("//div[@class='competition-container']//input[@placeholder='Search competition']");
    public TextBox txtSearchStatus = TextBox.xpath("//div[@class='competition-container']//input[@placeholder='Search status']");
    public TextBox txtSearchEventID = TextBox.xpath("//div[@class='event-container']//input[@placeholder='Search id']");
    public TextBox txtSearchEventName = TextBox.xpath("//div[@class='event-container']//input[@placeholder='Search name']");
    public int colSport = 1;
    public StaticTable tblSport = StaticTable.xpath("//div[@class='sport-container']", "div[contains(@class,'custom-scrollbar')]", "div[contains(@class,'tr-event')]", "div[contains(@class,'td')]", 1);
    public int colLastUpdateBy = 4;
    public int colCompetition = 1;
    public int colStatus = 2;
    public int colChb = 3;
    // "/div[contains(@class,'custom-scrollbar')]/")
    public StaticTable tblCompetition = StaticTable.xpath("//div[@class='competition-container']", "div[contains(@class,'custom-scrollbar')]", "div[contains(@class,'tr-event')]/div[contains(@class,'row')]", "div[contains(@class,'col')]", 5);
    public Label lblNoCompetition = Label.xpath("//div[@class='competition-container']//div[contains(@class,'no-record')]");
    int colEventId = 1;
    int colEventName = 2;
    public StaticTable tblEvent = StaticTable.xpath("//div[@class='event-container']", "div[contains(@class,'custom-scrollbar')]", "div[contains(@class,'row')]", "div[contains(@class,'col')]", 3);

    public void filter(String sportName, String competition,String status, String eventID, String eventName) {
        if(!sportName.isEmpty()) {
            searchSport(sportName);
            selectSport(sportName);
        }
        if(!competition.isEmpty())
        {
            txtSearchCompetition.sendKeys(competition);
            selectCompetition(competition);
        }
        if(!status.isEmpty()) {
            txtSearchStatus.sendKeys(status);
            txtSearchStatus.sendKeys(Keys.ENTER);
        }
        if(!eventID.isEmpty()) {
            txtSearchEventID.sendKeys(status);
        }
        if(!eventName.isEmpty()) {
            txtSearchEventName.sendKeys(status);
        }
    }
    public void selectSport(String sportName) {
        List<String> lstSport = tblSport.getColumn(colSport, true);
        Link lnk;
        for (int i = 0; i < lstSport.size(); i++) {
            if (lstSport.get(i).equals(sportName)) {
                selectSport(i + 1);
            }
        }
    }

    public void searchSport(String sportName) {
        txtSearchSport.sendKeys(sportName);
    }

    public void blockUnblockCompetition(String compName, boolean isBlock) {
        List<String> lstCompetition = tblCompetition.getColumn(colCompetition, false);
        for (int i = 0; i < lstCompetition.size(); i++) {
            if (lstCompetition.get(i).equals(compName)) {
                tblCompetition.getControlOfCell(1, colChb, 1, "span[contains(@class,'square-icon')]/i").click();
                if (isBlock && tblCompetition.getControlOfCell(1, colStatus, 1, null).getText().equals(BOConstants.Operations.CompetitionBlocking.UNBLOCK_STATUS)) {
                    btnBlock.click();
                } else
                    btnUnblock.click();
                waitSpinIcon();
                return;
            }
        }
    }

    public void selectSport(int index) {
        Link lnk;
        lnk = (Link) tblSport.getControlOfCell(1, colSport, index, null);
        lnk.scrollToThisControl(false);
        lnk.click();
        waitSpinIcon();
        return;
    }

    public void selectCompetition(String competitionName) {
        List<String> lstCompetition = tblCompetition.getColumn(colCompetition, true);
        for (int i = 0; i < lstCompetition.size(); i++) {
            if (lstCompetition.get(i).equals(competitionName)) {
                tblSport.getControlOfCell(1, colCompetition, i + 1, null);
                return;
            }
        }
    }

    public boolean verifyCompetition(String competitionName, String expectedStatus, String lastUpdateBy) {
        List<String> lstCompetition = tblCompetition.getColumn(colCompetition, true);
        for (int i = 0; i < lstCompetition.size(); i++) {
            if (lstCompetition.get(i).equals(competitionName)) {
                String actualStatus = tblCompetition.getControlOfCell(1, colStatus, i + 1, null).getText();
                String lastUpdateByActual = tblCompetition.getControlOfCell(1, colLastUpdateBy, i + 1, null).getText();
                if (!actualStatus.equals(expectedStatus)) {
                    System.err.println(String.format("Expected status of competition: %s is %s but found %s", competitionName, expectedStatus, actualStatus));
                    return false;
                }
                if (!lastUpdateByActual.equals(lastUpdateBy)) {
                    System.err.println(String.format("Expected Last update by of competition: %s is %s but found %s", competitionName, lastUpdateBy, lastUpdateByActual));
                    return false;
                }
                return true;
            }
        }
        System.out.println(String.format("The competition: %s does not display in the list", competitionName));
        return false;
    }

    public boolean isCompetitionUnblockByDefault(String sportName) {
        List<ArrayList<String>> lstComp = tblCompetition.getRows(false);
        for (int i = 1; i < lstComp.size() - 1; i++) {
            if (lstComp.get(i).get(colLastUpdateBy).equals(BOConstants.Operations.CompetitionBlocking.LAST_UPDATE_BY_SYSTEM)) {
                if (!lstComp.get(i).get(colStatus).equals(BOConstants.Operations.CompetitionBlocking.UNBLOCK_STATUS)) {
                    System.err.println(String.format("The competition %s not unblocked by default", lstComp.get(i).get(colCompetition)));
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isCompetitionUnblockByDefault(List<ArrayList<String>> lstSport) {
        // hard code to check the fist 5 sport that competition is unblocked by default
        for (int iSport = 0; iSport < 5; iSport++) {
            selectSport(iSport + 1);
            if (!isCompetitionUnblockByDefault(lstSport.get(iSport).get(1))) {
                System.err.println(String.format("Competition list of the sport %s does not unblock by default", lstSport.get(iSport).get(1)));
                return false;
            }
        }
        return true;
    }

    /**
     * This method is to click return on the fist competition name. In case data show "No records found." we return this message
     * @return competiton name or 'No records found.'"
     */
    public String clickAndGetFirstCompetition(){
        if(tblCompetition.isNoRecordFound())
            return NO_RECORDS_FOUND;
        tblCompetition.getControlOfCell(1,colCompetition,1, null).click();
        return tblCompetition.getControlOfCell(1,colCompetition,1, null).getText();
    }
    /**
     * This method is to click return on the fist competition name. In case data show "No records found." we return this message
     * @return competiton name or 'No records found.'"
     */
    public String getFirstEventIDofTheFirstCompetition(){
        clickAndGetFirstCompetition();
        waitSpinIcon();
        if(tblEvent.isNoRecordFound())
            return NO_RECORDS_FOUND;
        return tblEvent.getControlOfCell(1,colEventId,1, null).getText();
    }
}
