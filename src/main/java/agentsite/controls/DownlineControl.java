package agentsite.controls;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.CheckBox;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

public class DownlineControl extends BaseElement {
	static String _xpath = null;
	protected String _chbxPath;
	protected String _lblxPath;

	public DownlineControl(By locator, String xpath) {
		super(locator);
		_xpath= xpath;
		_chbxPath = String.format("%s%s",xpath,"//span[2]");
		_lblxPath = String.format("%s%s",xpath,"//span[1]");
	}

	public static DownlineControl xpath(String xpath) {
		return new DownlineControl(By.xpath(xpath),xpath);
	}

	public int getDownlineItems()
	{
		return DriverManager.getDriver().findElements(locator).size();
	}

	public void selectDownline(String downline)
	{
		selectDownline(downline,true);
	}
	public void selectDownline(String downline, boolean isClick)
	{
		for(int i = 0, n = getDownlineItems(); i<n; i++){
			Label lblDownlineName = Label.xpath(String.format("(%s)[%d]",_lblxPath,i+1));
			if(lblDownlineName.getText().contains(downline)) {
				if(isClick){
					logEndAction(String.format("Click on downline %s",lblDownlineName.getText()));
					lblDownlineName.click();
				}
				CheckBox checkbox =CheckBox.xpath(String.format("(%s)[%d]",_chbxPath,i+1));
				logEndAction(String.format("check on check box corresponding downline %s",downline));
				checkbox.click();
				return;
			}
		}
	}


}
