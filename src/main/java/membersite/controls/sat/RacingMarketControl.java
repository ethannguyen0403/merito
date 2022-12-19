package membersite.controls.sat;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import controls.Table;
import membersite.objects.sat.Market;
import org.openqa.selenium.By;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class RacingMarketControl extends BaseElement {
	static String _xpath = "//app-racing-market";
	public Label lblRacingTitle = Label.xpath("//div[contains(@class,'racing-market-header')]//h5");
	public Label lblTotalSelections = Label.xpath("//app-racing-market//th[@class='selections-count']");
	public Label lblMarketStartTime = Label.xpath("//div[contains(@class,'racing-market-header')]//span[@class='date']");
	public int colOddTable = 7;
	private int colBackL = 4;
	private int colLayK = 5;
	public Table tblOdds = Table.xpath("//table[contains(@class,'table-odds')]",colOddTable);


	private RacingMarketControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;// Define if that market is suspended" There are no markets available!"

	}
	public static RacingMarketControl xpath(String xpathExpression) {
		return new RacingMarketControl(By.xpath(xpathExpression), xpathExpression);
	}

	public String getTitle(boolean isEventName)
	{
		String title =  getTitle();
		String[] titles = title.split("/");
		return isEventName? titles[0].trim() : titles[1].trim();
	}

	public String getTitle()
	{
		lblRacingTitle.isTextDisplayed("Market",2);
		return lblRacingTitle.getText();
	}

	public Market getRace(int selectionIndex, boolean isBack)
	{
		String marketName = getTitle(false);
		String event = getTitle(true);

		String selectionName  = tblOdds.getControlOfCell(1,1,1,"span[@class='runner']").getText();
		/*List<String> rowData = tblOdds.getRow(1);
		String selectionName =  rowData.get(0).trim();
		if(selectionName.contains(")"))
		{
			selectionName = rowData.get(0).split("\\)")[1].trim();
		}*/
		Label lblOdds;

		if(isBack)
		{
			lblOdds =  (Label)tblOdds.getLabelOfCell(1,colBackL,selectionIndex,"div[@class='pending-odds']");
		}
		else
		{
			lblOdds = (Label)tblOdds.getLabelOfCell(1,colLayK,selectionIndex,"div[@class='pending-odds']");
		}
		//return getMarket(event,marketName,selectionName,isBack,lblOdds);
		return new Market.Builder()
				.eventName(event)
				.marketName(marketName)
				.selectionName(selectionName)
				.isBack(isBack)
				.btnOdds(lblOdds)
				.build();

	}


}
