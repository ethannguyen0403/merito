package agentsite.controls.NetExposure;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import org.openqa.selenium.By;

/**
 * @author by Isabell.Huynh
 */
public class SportTitleControl extends BaseElement {
	protected String _xPath = null;
	private Icon icColapse;


	public SportTitleControl(By locator, String xpathExpression) {
		super(locator);
		this._xPath = xpathExpression;
		icColapse = Icon.xpath(String.format("%s//i"));
	}
	public static SportTitleControl xpath(String xpathExpression) {
		return new SportTitleControl(By.xpath(xpathExpression), xpathExpression);
	}

	public boolean isCollapsed(){
		String attribute = icColapse.getAttribute("class");
		return attribute.contains("fa-plus");
	}
	public boolean isExpand(){
		String attribute = icColapse.getAttribute("class");
		return attribute.contains("fa-minus");
	}

	public void expandCollapse(boolean isExpand){
		String attribute = icColapse.getAttribute("class");
		if(isExpand && attribute.contains("fa-plus")){
			this.click();
		}
		if(!isExpand && attribute.contains("fa-minus")){
			this.click();
		}
	}
	public String getText(){
		return this.getText().trim();
	}
}
