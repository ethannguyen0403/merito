package agentsite.pages.marketsmanagement.blockunblockevents;

import agentsite.controls.Table;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import com.paltech.utils.DateUtils;
import org.testng.Assert;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MarketDetailsPopup {
    public TextBox txtMarketName = TextBox.xpath("//input[contains(@class,'input-market-name')]");
    private int totalCol = 2;
    private int colMarketName = 1;
    private int colStatus = 2;
    private int colBetable = 3;
    private Table tblMarket = Table.xpath("//perfect-scrollbar//table[@class='tables']", totalCol);

    public void assertBetStatusOfAllMarketForUnblockedEvent( int scheduleInMinute){
        //The start time of event when from api is a long we need to convert to format yyyy-MM-DD hh:mm:ss
        String formatDate = "yyyy-MM-DD hh:mm:ss";
        int i = 1;
        Label lblMarketStartTime;
        String marketStartTime;
        Icon icBetableGreen ;
        Icon icBetableRed ;
        String marketName;
        long timeDiff;
        while (true){

            lblMarketStartTime = Label.xpath(tblMarket.getxPathOfCell(1,colMarketName,i,"span[1]/p[1]"));
            if(!lblMarketStartTime.isDisplayed()){
                return;
            }
            marketName =Label.xpath(tblMarket.getxPathOfCell(1,colMarketName,i,"span[1]/p[1]")).getText();
            marketStartTime = lblMarketStartTime.getText().trim();
            timeDiff = getDateDiffOfMarketStarTimeAndCurrentTime(marketStartTime,formatDate);
            icBetableGreen = Icon.xpath(tblMarket.getxPathOfCell(1,colBetable,1,"span[@class='psuccess']"));
            icBetableRed = Icon.xpath(tblMarket.getxPathOfCell(1,colBetable,1,"span[@class='perror']"));
            if (scheduleInMinute <=0)
            {
                Assert.assertTrue(icBetableGreen.isDisplayed(),"FAILED! The Betable status of market "+ marketName +" is not betable (green check icon) as time to bet is "+ scheduleInMinute);
            }else if(timeDiff > scheduleInMinute)
            {
                Assert.assertTrue(icBetableRed.isDisplayed(), "FAILED! The Betable status of market "+ marketName +" should be red cross sign as start time is greater than "+ scheduleInMinute);
            }else{
                Assert.assertTrue(icBetableGreen.isDisplayed(),"FAILED! The Betable status of market "+ marketName +" should be green sign as start time in "+ scheduleInMinute);
            }
            i = i++;
        }

    }

    private long getDateDiffOfMarketStarTimeAndCurrentTime(String marketStartTime,String formatDate){
        Date marketStartDate = DateUtils.convertToDate(DateUtils.convertMillisToDateTime(marketStartTime,formatDate),formatDate);
        Date currentDate = DateUtils.convertToDate(DateUtils.convertMillisToDateTime(Long.toString(DateUtils.getMilliSeconds()),formatDate),formatDate);
        return  DateUtils.getDateDiff(marketStartDate,currentDate, TimeUnit.MINUTES);
    }

    public boolean isMarketBetAble(String marketName){
       int marketIndex = getMaketIndex(marketName);
        Icon icBetableGreen = Icon.xpath(tblMarket.getxPathOfCell(1,colBetable,marketIndex,"span[@class='psuccess']"));
        if(icBetableGreen.isDisplayed())
            return true;
        return false;
    }

    private int getMaketIndex(String marketNamORID){
        int i = 1;
        Label lblMarketStartTime;
        while (true){
            lblMarketStartTime = Label.xpath(tblMarket.getxPathOfCell(1,colMarketName,i,"span[1]/p[1]"));
            if(!lblMarketStartTime.isDisplayed()){
                System.out.println("DEBUG! Market "+marketNamORID+" does not display in the list");
                return 0;
            }
            if(lblMarketStartTime.getText().trim().contains(marketNamORID)){
                System.out.println("DEBUG! Found Market "+marketNamORID+" at row "+i);
                return i;
            }
            i = i++;
        }
    }

}
