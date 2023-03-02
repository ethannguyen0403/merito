package membersite.controls.sat;

import com.paltech.constant.StopWatch;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import membersite.objects.sat.BookmakerMarket;
import membersite.objects.sat.Market;
import org.openqa.selenium.By;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class WicketBookmakerContainerControl extends BaseElement {
	public static String _xpath;
	private Label lblTitle  = Label.xpath(String.format("%s//div[@class='title']",_xpath));
	private Label lblSuspend = Label.xpath(String.format("%s//div[contains(@class,'suspended-overlay-container')]",_xpath));
	private int totalColumn = 5;
	private int colMarketName = 1;
	private int colMinMax = 2;
	private int colLayOdds = 3;
	private int colBackOdds = 4;
	private int colBook = 5;
	private String lblMarketNameXpath = "span[@class='market-name']";
	private Label lblSuspendMarket;

	//private String tblXpath ;
	private String runnerRow = "%s/div[%d]";
	private String runnerNameXPath = "//div[contains(@class,'runner-name')]/span[1]";
	private String forecastXPath = "//div[contains(@class,'runner-name')]/span[2]";
	private String oddCellXpath = "//div[contains(@class,'cell-%s')]";
	private String pendingStakeXpath = "//div[contains(@class,'table-odds')]/div[2]//div[contains(@class,'cell-back')]//div[contains(@class,'pending-stake')]";
	private String layoddsXpath = "//div[contains(@class,'table-odds')]/div[2]//div[contains(@class,'cell-back')]//div[contains(@class,'pending-odds')]";
	private String Supendlabel = "//div[contains(@class,'table-odds')]/div[3]//div[contains(@class,'suspended ')]";
	private String marketSuspendlable ="//div[contains(@class,'table-odds')]/div[contains(@class,'suspended-overlay')]";

	public WicketBookmakerContainerControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;// Define if that market is suspended" There are no markets available!"
		lblSuspendMarket = Label.xpath(String.format("%s/div[contains(@class,'suspended-overlay')]",_xpath));
		//tblXpath = String.format("%s//div[contains(@class,'table-odds')]",_xpath);
	}
	public static WicketBookmakerContainerControl xpath(String xpathExpression) {
		return new WicketBookmakerContainerControl(By.xpath(xpathExpression), xpathExpression);
	}

	public Market getBookmakerMarketInfo(BookmakerMarket bookmakerMarket,boolean isBack){
		Label lblRow ;
		String runner="";
		Label bntOdd= null;
		int i = 2;
		while (true){
			if(isMarketSuspended()){
				System.out.println("--DEBUG : Market Suspended in 3 minutes!-----");
				return null;
			}
			String rowXpath = String.format(runnerRow,_xpath,i);
			lblRow = Label.xpath(rowXpath);
			if(!lblRow.isDisplayed())
			{
				return null;
			}
			if(isRunnerSuspended(i)){
				i = i+1;
			}else{
				runner = Label.xpath(String.format("%s%s", rowXpath, runnerNameXPath)).getText();
				String backOrLay = isBack ? "back" : "lay";
				String oddXPath = String.format(oddCellXpath, backOrLay);
				String odds ="";
				if(Label.xpath(String.format("%s%s//div[contains(@class,'pending-odds')]", rowXpath, oddXPath)).isClickable(1))
				{
				 odds = Label.xpath(String.format("%s%s//div[contains(@class,'pending-odds')]", rowXpath, oddXPath)).getText().trim();
				bntOdd = Label.xpath(String.format("%s%s", rowXpath, oddXPath));}
				else {
					if(!isMarketSuspended()) {
						 odds = Label.xpath(String.format("%s%s//div[contains(@class,'pending-odds')]", rowXpath, oddXPath)).getText().trim();
						bntOdd = Label.xpath(String.format("%s%s", rowXpath, oddXPath));
					}
				}
				return new Market.Builder()
						.eventName(bookmakerMarket.getEventName())
						.marketName(bookmakerMarket.getMarketName())
						.selectionName(runner)
						.odds(odds)
						.isBack(isBack)
						.btnOdds(bntOdd)
						.marketType(bookmakerMarket.getMaketType())
						.build();

			}
			/*String odd = Label.xpath(String.format("%s%s//div[contains(@class,'pending-odds')]",rowXpath, oddXPath)).getText();
			String matchStake = Label.xpath(String.format("%s%s//div[contains(@class,'pending-stake')]",rowXpath,oddXPath)).getText() ;*/
		}

	}


	private boolean isMarketSuspended(){
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		// wait suspend lable display in 3minutes
		while(stopWatch.getElapsedTime() < 90000L) {
			if(!lblSuspendMarket.isDisplayed(2)){
				return false;
			}
			System.out.println("--- Wait suspend status disappear in  "+ stopWatch.getElapsedTime());
		}
		return false;

	}

	private boolean isRunnerSuspended(int index){
		Label lblRunnerSuspended = Label.xpath(String.format("%s/div[%d]//div[contains(@class,'suspended')]",_xpath,index));
	//	StopWatch stopWatch = new StopWatch();
		//stopWatch.start();
		// wait suspend lable display in 3minutes
		//while(stopWatch.getElapsedTime() < 10000L) {
		return lblRunnerSuspended.isDisplayed();
	//		System.out.println("--- Wait suspend of the runner status disappear in  "+ stopWatch.getElapsedTime());
		//}


	}

}
