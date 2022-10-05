package membersite.controls.sat;

import com.paltech.constant.StopWatch;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import com.paltech.element.common.Tab;
import membersite.controls.Table;
import membersite.objects.sat.FancyMarket;
import org.openqa.selenium.By;

import java.util.Objects;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class FancyContainerControlOldUI extends BaseElement {
	private  String _xpath;
	private Label lblInPlayStauts;
	private Label lblMarketName;
	private Label lblMinValue;
	private  Label lblMaxValue;
	public Table tblOdds;
	//private static boolean isWicket;
	private int totalColumn = 7;
	private int colMarketName = 1;
	private int colMinMax = 2;
	private int colLayOdds = 4;
	private int colBackOdds = 5;
	private int colBook = 5;
	private String lblMarketNameXpath = "//div[@class='marketview-header']//h2/span[1]";
	private Label lblTitle  = Label.xpath(String.format("%s//div[@class='title']",_xpath));
	private String tblXpath ="(%s//wicket-fancy-odds)[%s]//table";
	private String tblCentralFancyXpath ="(%s//central-fancy-odds)[%s]//table";
	private String tblFancyXpath = "(%s//fancy-odds)[%s]//table";
	public FancyContainerControlOldUI(By locator, String xpath) {
		super(locator);
		_xpath = xpath;
		lblInPlayStauts = Label.xpath(String.format("%s//div[@class='marketview-header']//span[@class='market-status-icon market-inplay']",_xpath));
		lblMarketName = Label.xpath(String.format("%s//div[@class='marketview-header']//h2/span[1]",_xpath));
		lblMaxValue = Label.xpath(String.format("%s//span[contains(@class,'max-fbet')]",_xpath));
		lblMinValue =Label.xpath(String.format("%s//span[contains(@class,'min-fbet')]",_xpath));
		tblOdds = Table.xpath(String.format("%s//table[contains(@class,'runner-table')]",_xpath),7);

	}
	public static FancyContainerControlOldUI xpath(String xpathExpression) {

		return new FancyContainerControlOldUI(By.xpath(xpathExpression), xpathExpression);
	}

	public String getMarketName (){
		return lblMarketName.getText().trim();
	}

	public FancyMarket setFancyMarketInfo(FancyMarket fcMarket){
		FancyMarket newFancy = fcMarket;
		newFancy.setMin(Integer.parseInt(lblMinValue.getText().trim().replace(",","")));
		newFancy.setMmax(Integer.parseInt(lblMaxValue.getText().trim().replace(",","")));
		newFancy.setBtnYes((Link)tblOdds.getControlOfCell(1,colBackOdds,1,null));
		newFancy.setBtnNo((Link)tblOdds.getControlOfCell(1,colLayOdds,1,null));
		Link lblOdd = (Link) tblOdds.getControlOfCell(1,colBackOdds,1,"span[contains(@class,'odds-value')]");
		Link lblRate= (Link)tblOdds.getControlOfCell(1,colBackOdds,1,"span[contains(@class,'payout-value')]");
		String rate;
		if(lblOdd.isDisplayed())
			newFancy.setOddsYes(Double.parseDouble(lblOdd.getText().trim()));
		if(lblRate.isDisplayed()){
			rate = lblRate.getText().trim().replace(":","");
			newFancy.setRateYes(Integer.parseInt(rate));
		}
		Link lblOddLay = (Link) tblOdds.getControlOfCell(1,colLayOdds,1,"span[contains(@class,'odds-value')]");
		Link lblRateLay= (Link) tblOdds.getControlOfCell(1,colLayOdds,1,"span[contains(@class,'payout-value')]");
		if(lblOddLay.isDisplayed())
			newFancy.set_oddsNo(Double.parseDouble(lblOddLay.getText().trim()));
		if(lblRateLay.isDisplayed()) {
			rate = lblRateLay.getText().trim().replace(":", "");
			newFancy.setRateNo(Integer.parseInt(rate));
		}
		Link lnkLiability = (Link)tblOdds.getControlOfCell(1,colMarketName,1,"strong[contains(@class,'fair-27-fancy-liability negative')]");
 		if(lnkLiability.isDisplayed())
			newFancy.setMarketLiability(Double.parseDouble(lnkLiability.getText().trim()));
		return newFancy;
	}
}
