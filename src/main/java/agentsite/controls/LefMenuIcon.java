package agentsite.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import org.openqa.selenium.By;

public class LefMenuIcon extends BaseElement {
    static String _xPath;
    public Icon icAgencyManagemnt;
    public Icon icReport;
    public Icon icMarketManageent;
    public Icon icFraudDetection;
    public LefMenuIcon(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        icAgencyManagemnt = Icon.xpath(String.format("%s//following::div[@id='actionMenu']//span[contains(@class,'user')]",_xPath));
        icReport = Icon.xpath(String.format("%s//following::div[@id='actionMenu']//span[contains(@class,'log')]",_xPath));
        icMarketManageent = Icon.xpath(String.format("%s//following::div[@id='actionMenu']//span[contains(@class,'security')]",_xPath));
        icFraudDetection = Icon.xpath(String.format("%s//following::div[@id='actionMenu']//span[contains(@class,'fraud')]",_xPath));
    }

    public static LefMenuIcon xpath(String xpathExpression) {
        return new LefMenuIcon(By.xpath(xpathExpression), xpathExpression);
    }

    public void expandLeftMenu(){
        String classAtri = this.getAttribute("class");
        if(classAtri.contains("expand")){
            this.click();
        }
    }

    public void collapseLeftMenu(){
        String classAtri = this.getAttribute("class");
        if(!classAtri.contains("expand")){
            this.click();
            this.isInvisible(1);
        }
    }


}
