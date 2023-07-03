package membersite.controls;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DropDownMenu extends BaseElement {
    protected final int timeOutInSeconds = 10;
    protected String _xpathMenu = null;
    private String xpathLocatorItems;
    private String xpathSelectedControl;

    public DropDownMenu(By locator, String xpathMenu, String locatorSelectedItem, String locatorChildren) {
        super(locator);
        ;
        _xpathMenu = xpathMenu;
        xpathSelectedControl = _xpathMenu + locatorSelectedItem;
        xpathLocatorItems = locatorChildren;
    }

    /**
     * @param xpathParent   is used for moving cursor of move and hover on this control to show the hidden items
     * @param xpathChildren to get text of items within ddb
     * @return DropDownBox
     */
    public static DropDownMenu xpath(String xpathParent, String xpathSelectedItem, String xpathChildren) {
        return new DropDownMenu(By.xpath(xpathParent), xpathParent, xpathSelectedItem, xpathChildren);
    }

    public WebElement waitForElementToBePresent(By by) {
        return waitForElementToBePresent(by, timeOutInSeconds);
    }

    public WebElement waitForElementToBePresent(By by, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeOutInSeconds);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<String> getOptions() {
        return getOptions(true);
    }

    public List<String> getOptions(boolean isClick) {
        List<String> lstOption = new ArrayList<>();
        int index = 1;
        if (isClick)
            this.click();
        Label subMenu;
        while (true) {
            subMenu = Label.xpath(String.format("%s[%s]", this.xpathLocatorItems, index));
            if (!subMenu.isDisplayed(timeOutInSeconds)) {
                return lstOption;
            }
            lstOption.add(subMenu.getText().trim());
            index += 1;
        }
    }

    public String getSelectedItem() {
        Label selectControl = Label.xpath(this.xpathSelectedControl);
        return selectControl.getText();
    }

    public void clickSubMenu(String name) {
        clickSubMenu(name, true);
    }

    public void clickSubMenu(String name, boolean isClick) {
//		int index = findSubMenuIndex(name);
//		Label.xpath(String.format("%s[%s]", this.xpathLocatorItems,index)).click();
        Label subMenu = getMenu(name);
        if (!Objects.isNull(subMenu)) {
            subMenu.click();
        }
    }

/*	private int findSubMenuIndex(String subMenuName){
		int index = 1;
		this.click();

		Label subMenu ;

		while (true){
			subMenu = Label.xpath(String.format("%s[%s]", this.xpathLocatorItems,index));
			if(!subMenu.isDisplayed()){
				// To handle case element is hidden but the next still available
				if( Label.xpath(String.format("%s[%s]", this.xpathLocatorItems,index +1)).isDisplayed()){
					index +=1;
					continue;
				} else {
					System.out.println(String.format("Error: There is no sub menu %s in the list", subMenuName));
					return 0;
				}
			}
			if(subMenu.getText().contains(subMenuName)){
				return index;
			}
			index += 1;
		}
	}*/

    private Label getMenu(String name) {
        this.click();
        Label subMenu = Label.xpath(String.format("%s//a[text()='%s']", this.xpathLocatorItems, name));
        if (subMenu.isDisplayed())
            return subMenu;
        else {
            System.out.println(String.format("The menu %s does not exist"));
            return null;
        }
    }

    public boolean isContainSubmenu(String subMenu) {
        Label menuLbl = getMenu(subMenu);
        if (Objects.isNull(menuLbl))
            return false;
        return true;
        //int index = findSubMenuIndex(subMenu);
        //	if (index!=0)
//			return true;
//		return false;
    }
}
