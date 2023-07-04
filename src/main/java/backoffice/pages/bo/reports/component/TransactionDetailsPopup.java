package backoffice.pages.bo.reports.component;

import com.paltech.element.common.Tab;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TransactionDetailsPopup {
    public Tab tabProduct = Tab.xpath("//ul[@class='nav nav-tabs']//li//span");

    public List<String> getProductTab() {
        List<String> lstProduct = new ArrayList<>();
        List<WebElement> tabs = tabProduct.getWebElements();
        for (WebElement element : tabs) {
            lstProduct.add(element.getText());
        }
        return lstProduct;
    }
}
