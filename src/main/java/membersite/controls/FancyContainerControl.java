package membersite.controls;

import com.paltech.constant.StopWatch;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import controls.Table;
import membersite.objects.sat.FancyMarket;
import org.openqa.selenium.By;

import java.util.Objects;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class FancyContainerControl extends BaseElement {
	private  String _xpath;
	//private static boolean isWicket;
	private int totalColumn = 5;
	private int colMarketName = 1;
	private int colMinMax = 2;
	private int colLayOdds = 3;
	private int colBackOdds = 4;
	private int colBook = 5;
	private String lblMarketNameXpath = "span[contains(@class,'market-name')][1]";
	private Label lblTitle  = Label.xpath(String.format("%s//div[@class='title']",_xpath));
	private String tblXpath ="(%s//wicket-fancy-odds)[%s]//table";
	private String tblCentralFancyXpath ="(%s//central-fancy-odds)[%s]//table";
	private String tblFancyXpath = "(%s//fancy-odds)[%s]//table";
	private Table tblFCMarket = Table.xpath(String.format("%s//table",_xpath),totalColumn);
	private String minMaxXPath = "//div[@class='value-mm-bet']";
	private String marketRow = "//tr[contains(@class,'%s')]";// market ID
	private String oddsNoCell ="//td[contains(@class,'cell-lay fancy-odds')]";
	private String oddsYesCell ="//td[contains(@class,'cell-back fancy-odds')]";
	private String lblMarketName = "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'fancy-liability')]//span[@class='market-name']";
	private String lblLiabilityValue = "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'fancy-liability')]//span[@class='fancy-liability-value']";;
	private String lblMinMaxValue= "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'fancy-odds-frist')]//div[@class='value-mm-bet']/div";
	private String btnLay ="cell-lay fancy-odds";
	private String btnBack ="cell-back fancy-odds";
	private String lblHandicap ="(//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'cell-lay fancy-odds')]//div[contains(@class,'pending-odds')]/span)[1]";
	private String lblPayout ="(//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'cell-lay fancy-odds')]//div[contains(@class,'pending-odds')]/span)[2]";
	private String bookIcon = "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'border-top border-bottom')]//i[contains(@class,'ladder-book')]";
	private String expandIcon = "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'border-top border-bottom')]//i[contains(@class,'fa-chevron-ups')]";
	private String colaspeIcon = "//div[contains(@class,'fancy-container')]//tr[contains(@class,'301042')]//td[contains(@class,'border-top border-bottom')]//i[contains(@class,'fa-chevron-down')]";

	public FancyContainerControl(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		// Define if that market is suspended" There are no markets available!"

	}
	public static FancyContainerControl xpath(String xpathExpression) {
		return new FancyContainerControl(By.xpath(xpathExpression), xpathExpression);
	}

	public String getTitle (){
		return lblTitle.getText();
	}


	private Table getFancyMarketRow(FancyMarket fcMarket){

		Table tblMarket;
		String xpathTable;
		xpathTable =defineFancyTableXpath(fcMarket.getMaketType());
		String marketName;
		int i = 1;
		while (true){
			tblMarket = Table.xpath(String.format(xpathTable,_xpath,i),totalColumn);
			if(!tblMarket.isDisplayed()) {
				System.out.println(String.format("Debug: NOT found fancy market : %s in the Fancy container",fcMarket.getMarketName()));
				return null;
			}
			marketName = tblMarket.getControlOfCell(1,colMarketName,1,lblMarketNameXpath).getText();
			if (marketName.trim().equalsIgnoreCase(fcMarket.getMarketName())) {
				System.out.println(String.format("Debug: found fancy market : %s at row %d", fcMarket.getMarketName(), i));
				return tblMarket;
			}
			i++;
		}
		/*for(int j=0; j< totalFCMarketRow; j++) {
			tblMarket = getMarket(j+1);
			marketName = tblMarket.getControlOfCell(1,colMarketName,1,lblMarketNameXpath).getText();
			if (marketName.trim().equalsIgnoreCase(fcMarket.getMarketName())) {
				System.out.println(String.format("Debug: found fancy market : %s at row %d", fcMarket.getMarketName(), j + 1));
				return tblMarket;
			}
		}
		System.out.println(String.format("Debug: NOT found fancy market : %s in the Fancy container",fcMarket.getMarketName()));
		return null;*/
	}

	public void clickFancyOdds(FancyMarket fcMarket, boolean isBack){

		Table tbl = getFancyMarketRow(fcMarket);
		Link lnk;
		if(Objects.isNull(tbl))
			return;
		if(isBack)
		{
			lnk = (Link) tbl.getControlOfCell(1,colBackOdds,1,null);
		}
		else
			lnk = (Link)tbl.getControlOfCell(1,colLayOdds,1,null);
		if(waitSuspendLabelDisapper(tbl)){
			lnk.click();
		}

	}

	private boolean waitSuspendLabelDisapper(Table tblMarket)
	{
		Label lblSuspend = Label.xpath(String.format("%s//div[contains(@class,'suspended')]/span",tblMarket.getLocator().toString().replace(" By.xpath: ","")));
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		// wait suspend lable display in 3minutes
		while(stopWatch.getElapsedTime() < 300000L) {
			if(!lblSuspend.isDisplayed()){
				return true;
			}
			System.out.println("Wait suspend status disappear in  "+ stopWatch.getElapsedTime());
		}
		return false;
	}

	public String getMinMaxOFFancyMarket (FancyMarket fcMarket){
		Table tbl = getFancyMarketRow(fcMarket);
		if(Objects.isNull(tbl)){
			return null;
		}
		return tbl.getControlOfCell(1,colMinMax,1,"div[@class='value-mm-bet']/div").getText();

	}

	private String defineFancyTableXpath(String marketType){
		switch (marketType){
			case "WICKET_FANCY":
				return tblXpath;
			case "CENTRAL_FANCY":
				return tblCentralFancyXpath;
			default:
				return tblFancyXpath;
		}
	}
	public FancyMarket getFancyMarketInfo(FancyMarket fcMarket){
			FancyMarket newFancy = fcMarket;
			Table tblMarket;
			String xpathTable;
			xpathTable = defineFancyTableXpath(fcMarket.getMaketType());
			String marketName;
			int i = 1;
			while (true){
				tblMarket = Table.xpath(String.format(xpathTable,_xpath,i),totalColumn);
				if(!tblMarket.isDisplayed()) {
					System.out.println(String.format("Debug: NOT found fancy market : %s in the Fancy container",fcMarket.getMarketName()));
					return newFancy;
				}
				marketName = tblMarket.getControlOfCell(1,colMarketName,1,lblMarketNameXpath).getText().trim();
				if (marketName.equalsIgnoreCase(fcMarket.getMarketName())) {
					System.out.println(String.format("Debug: found fancy market : %s at row %d", fcMarket.getMarketName(), i));
					String minMax = tblMarket.getControlOfCell(1,colMinMax,1,"div[contains(@class,'value-mm-bet')]/div" +
							"").getText();
					String[] minMaxArr =  minMax.split("/");
					newFancy.setMin(Integer.parseInt(minMaxArr[0].trim().replaceAll(",","")));
					newFancy.setMmax(Integer.parseInt(minMaxArr[1].trim().replaceAll(",","")));
					newFancy.setBtnYes((Link)tblMarket.getControlOfCell(1,colBackOdds,1,null));
					newFancy.setBtnYes((Link)tblMarket.getControlOfCell(1,colLayOdds,1,null));
					Link lblOdd = (Link) tblMarket.getControlOfCell(1,colBackOdds,1,"span[1]");
					Link lblRate= (Link)tblMarket.getControlOfCell(1,colBackOdds,1,"span[2]");
					Link lblOddLay = (Link) tblMarket.getControlOfCell(1,colLayOdds,1,"span[1]");
					Link lblRateLay= (Link) tblMarket.getControlOfCell(1,colLayOdds,1,"span[2]");
					Link lnkLiability = (Link)tblMarket.getControlOfCell(1,colMarketName,1,"div[contains(@class,'liability')]//span[@class='fancy-liability-value']");
					String rate;
					waitSuspendLabelDisapper(tblMarket);
					if(lblOdd.isDisplayed())
						newFancy.setOddsYes(Double.parseDouble(lblOdd.getText().trim()));
					if(lblRate.isDisplayed()){
						rate = lblRate.getText().trim().replace(":","");
						newFancy.setRateYes(Integer.parseInt(rate));
					}

					if(lblOddLay.isDisplayed())
						newFancy.set_oddsNo(Double.parseDouble(lblOddLay.getText().trim()));
					if(lblRateLay.isDisplayed()) {
						rate = lblRateLay.getText().trim().replace(":", "");
						newFancy.setRateNo(Integer.parseInt(rate));
					}

					if(lnkLiability.isDisplayed())
						newFancy.setMarketLiability(Double.parseDouble(lnkLiability.getText().trim()));
					return newFancy;
				}
				i++;
		}
	}
}
