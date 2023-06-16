package agentsite.pages.marketsmanagement;

import agentsite.controls.Table;
import agentsite.pages.HomePage;
import com.paltech.element.common.*;
import com.paltech.utils.DateUtils;
import org.testng.Assert;

import java.util.List;

public class BlockUnblockCompetitionPage extends HomePage {

    public Label lblSport = Label.xpath("//label[@translate='sport']");

    public DropDownBox ddbSport = DropDownBox.xpath("//div[@class='selection']/div[@class='select-container'][1]//table//select");
    public Label lblInfo = Label.xpath("//ul[@class='info']");
    public Button btnBlock = Button.xpath("//div[contains(@class,'result container-fluid')]/button[1]");
    public Button btnUnBlock = Button.xpath("//div[contains(@class,'result container-fluid')]/button[2]");
    public TextBox txtSearchByUsernameLoginID = TextBox.xpath("//table[contains(@class,'block-table')]//div[@class='filter-event']//input");
    public TextBox txtSearchByCompetition = TextBox.xpath("//table[contains(@class,'event-table')]//div[@class='filter-event']//input");
    //private String tblDownlinexPath = "//ul[@class='tree-container downline-list']/li";

    private String tblDownlinexPath = "//ul[contains(@class,'downline-list')]/li[@class='ng-star-inserted']";
    private String cbbDonwlinexPath = String.format("%s%s",tblDownlinexPath,"//span[2]");
    private String lblDonwlinexPath = String.format("%s%s",tblDownlinexPath,"//span[1]");
    public CheckBox chkDownlineAll = CheckBox.xpath("//li[@class='select-all-user']//i");
    public CheckBox chkCompetitionAll = CheckBox.xpath("//div[@class='title-event']//i");
    private Icon iconLoadSpinner = Icon.xpath("//div[contains(@class,'la-ball-clip-rotate')]/div");

    private int totalCompetitionCol = 4;
    public int colCompetition = 1;
    public int colStatus = 2;
    public int colLastUpdateBy = 3;
    public int colLastUpdateTime = 4;
    public Table tblCompetition = Table.xpath("//div[@class='main-container']//div[@class='col-right']/table",totalCompetitionCol);
    public Table tblDownline = Table.xpath("//div[@class='main-container']//div[@class='col-left']/table",1);
    public BlockUnblockCompetitionPage(String types) {
        super(types);
    }

    public void filter( String sport)
    {
        if(!sport.isEmpty()){
            System.out.println(String.format("***Debug Step: Select sport: %s",sport));
            ddbSport.selectByVisibleText(sport);
        }
        waitingLoadingSpinner();
    }

    public void selectDownline(String downline)
    {
        selectDownline(downline,true);
    }

    public void selectDownline(String downline, boolean isClick)
    {
        if(downline.isEmpty())
            return;
        Label downlineList= Label.xpath(tblDownlinexPath);
        int n = downlineList.getWebElements().size();
        for(int i = 0; i<n; i++){
            Label lblDownlineName = Label.xpath(String.format("(%s)[%d]",lblDonwlinexPath,i+1));
            if(lblDownlineName.getText().contains(downline)) {
                if(isClick){
                    System.out.println(String.format("***Debug Step: Click downline: %s",downline));
                    lblDownlineName.click();
                    waitingLoadingSpinner();
                }
                CheckBox checkbox =CheckBox.xpath(String.format("(%s)[%d]",cbbDonwlinexPath,i+1));
                System.out.println(String.format("***Debug Step: Click checkbox of downline: %s",downline));
                checkbox.click();
                return;
            }
        }
    }

    public int getCompetitionRowIndex(String competition)
    {
        List<String> lstCompetition = tblCompetition.getColumn(colCompetition,true);
        for (int i = 0; i< lstCompetition.size(); i++)
        {
            if(lstCompetition.get(i).contains(competition))
            {
                return i +1;
            }
        }
        return 0;
    }
    public void selectCompetition(String competition)
    {
        if(!competition.isEmpty()){
            if(competition.equalsIgnoreCase("all"))
            {
                System.out.println("***Debug Step: Click checkbox All");
                chkCompetitionAll.click();
                return;
            }
            int i = getCompetitionRowIndex(competition);
            if(i == 0)
            {
                System.out.println(String.format("There is NO competition %s display in the list",competition));
                return;
            }
            System.out.println("Competition %s display in the list");
            CheckBox chk = CheckBox.xpath(tblCompetition.getxPathOfCell(1,colCompetition,i,"span[contains(@class,'square-icon')]/i"));
            System.out.println(String.format("***Debug Step: Click checkbox of competition %s",competition));
            chk.click();
        }
    }

    public String blockUblockCompetition(String sport, String downline, String competitionName, boolean isBlock)
    {
        filter(sport);
        selectDownline(downline,true);
        txtSearchByCompetition.sendKeys(competitionName);
        selectCompetition(competitionName);
        if (isBlock) {
            System.out.println("***Debug Step: Click Block button");
            if(btnBlock.isEnabled()){
                btnBlock.click();
            }

        }
        else{
            System.out.println("***Debug Step: Click Unblock button");
            btnUnBlock.click();}

        return DateUtils.convertMillisToDateTime(Long.toString(DateUtils.getMilliSeconds()),"YYYY-MM-dd HH:mm");

    }

    public void verifyCompetitionStatus(String competitionName, String status, String blockBy, String blockTime)
    {
        int i = getCompetitionRowIndex(competitionName);
        System.out.println(String.format("Verify status of the competition: %s", competitionName));
        Assert.assertEquals(tblCompetition.getControlOfCell(1, colStatus, i,null).getText(),status,"FAILED! Status not match with the expected");
        Assert.assertEquals(tblCompetition.getControlOfCell(1, colLastUpdateBy, i,null).getText(),blockBy,"FAILED! Last Update By not match with the expected");
        Assert.assertTrue(tblCompetition.getControlOfCell(1, colLastUpdateTime, i,null).getText().contains(blockTime),"FAILED! Last Update Time not match with the expected");
    }

}
