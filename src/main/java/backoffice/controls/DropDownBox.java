package backoffice.controls;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DropDownBox extends BaseElement {
    private final int timeOutInSeconds = 10;
    private By locatorItems;
    private Label lblUnSelectAll;
    private Label lblSelectAll;
    private Icon icClearAll;
    private Label lblSelectedList;
    private Label lblRemoveSelecteList;

    private DropDownBox(By locator, By locatorChildren, String xpathParent) {
        super(locator);
        locatorItems = locatorChildren;
        lblSelectAll = Label.xpath(xpathParent + "//following-sibling::div[contains(@class,'dropdown-list')]//div[contains(@class, 'select-all')]//span[1]");
        lblUnSelectAll = Label.xpath(xpathParent + "//following-sibling::div[contains(@class,'dropdown-list')]//div[contains(@class, 'select-all')]//span[contains(text(),'UnSelect')]");
        icClearAll = Icon.xpath(xpathParent + "//span[contains(@class,'clear-all')]");
        lblRemoveSelecteList = Label.xpath(xpathParent + "//div[contains(@class,'c-list')]//span[contains(@class,'c-remove')]");
        lblSelectedList = Label.xpath(xpathParent + "//div[contains(@class,'c-list')]//span[contains(@class,'c-label')]");

    }

    /**
     * @param xpathParent   is used for moving cursor of move and hover on this control to show the hidden items
     * @param xpathChildren to get text of items within ddb
     * @return DropDownBox
     */
    public static DropDownBox xpath(String xpathParent, String xpathChildren) {
        return new DropDownBox(By.xpath(xpathParent), By.xpath(String.format("%s%s", xpathParent, xpathChildren)), xpathParent);
    }

    /**
     * Allowing to select an item within this ddb by an item name
     *
     * @param name item name within ddb
     */
    public void selectByVisibleText(String name) {
        selectByVisibleText(name, false);
    }

    /**
     * Allowing to select an item within this ddb by an item name
     *
     * @param name item name within ddb
     */
    public void selectByVisibleText(String name, boolean isClicked) {
        logStartAction(String.format("select an item by '%s'", name));
        try {
            if (isClicked) {
                this.click();
            } else {
                this.moveAndHoverOnControl();
            }
            String text = selectItem(name);
            logEndAction(String.format("selected the item name '%s'", text));
        } catch (NullPointerException ex) {
            logEndAction(String.format("Exception: NullPointer exception is '%s'", ex.getMessage()));
        } catch (WebDriverException ex) {
            logEndAction(String.format("Error: WebDriverException exception is '%s'", ex.getMessage()));
        }
    }

    /**
     * Allowing to select an item within this ddb by index
     *
     * @param index begin is 0
     */
    public void selectByIndex(int index) {
        logStartAction(String.format("select an number item '%d'", index));
        try {
            this.moveAndHoverOnControl();
            List<WebElement> lstWebElements = DriverManager.getDriver().findElements(locatorItems);
            if (lstWebElements.size() < index) {
                logEndAction(String.format("Error: Index '%d' is greater than %d a number of items within this ddb", index, lstWebElements.size()));
                return;
            }
            String text = lstWebElements.get(index).getText();
            lstWebElements.get(index).click();
            logEndAction(String.format("selected the item at index %d with name: '%s'", index, text));
        } catch (NullPointerException ex) {
            logEndAction(String.format("Exception: NullPointer exception is '%s'", ex.getMessage()));
        } catch (WebDriverException ex) {
            logEndAction(String.format("Error: WebDriverException exception is '%s'", ex.getMessage()));
        }

    }

    /**
     * Getting item name selected in this ddb
     */
    public String getSelectedOption() {
        logStartAction("get text of the first selected item");
        try {
            String selectedItemText = getWebElement().getText();
            logEndAction(String.format("got text of the first selected option with locator '%s' is '%s'", getLocator(), selectedItemText));
            return selectedItemText;
        } catch (NullPointerException ex) {
            logEndAction(String.format("Exception: NullPointer exception is '%s'", ex.getMessage()));
            return "";
        } catch (WebDriverException ex) {
            logEndAction(String.format("Error: WebDriverException exception is '%s'", ex.getMessage()));
            return "";
        }
    }

    public List<String> getSelectedOptions() {
        List<String> lstSelectedOptions = new ArrayList<>();
        logStartAction("get text of the first selected item");
        List<WebElement> lstWebElements = lblSelectedList.getWebElements();
        if (lstWebElements == null) {
            System.out.println("Cannot find the selected list element");
            return null;
        }
        for (int i = 0; i < lstWebElements.size(); i++) {
            lstSelectedOptions.add(lstWebElements.get(i).getText());
        }
        return lstSelectedOptions;
    }

    public void removeSelectedOption(String selectedOption) {
        List<String> lstSelectedOptions = new ArrayList<>();
        logStartAction("get text of the first selected item");
        List<WebElement> lstWebElements = lblSelectedList.getWebElements();
        List<WebElement> lstRemoveWebElements = lblRemoveSelecteList.getWebElements();
        if (lstWebElements == null) {
            System.out.println("Cannot find the selected list element");
            return;
        }
        for (int i = 0; i < lstWebElements.size(); i++) {
            if (lstWebElements.get(i).getText().equalsIgnoreCase(selectedOption)) {
                lstRemoveWebElements.get(i).click();
                return;
            }
        }
    }

    /**
     * Checking items within ddb is what items you are expected or not
     *
     * @param items item names that you want to check
     * @return true or false
     */
    public boolean areOptionsMatched(List<String> items) {
        return areOptionsMatched(items, false);
    }

    /**
     * Checking items within ddb is what items you are expected or not
     *
     * @param items item names that you want to check
     * @return true or false
     */
    public boolean areOptionsMatched(List<String> items, boolean isClicked) {
        try {
            if (isClicked) {
                this.click();
            } else {
                this.moveAndHoverOnControl();
            }
            return areMatched(items);
        } catch (NullPointerException ex) {
            logEndAction(String.format("Exception: NullPointer exception is '%s'", ex.getMessage()));
            return false;
        } catch (WebDriverException ex) {
            logEndAction(String.format("Error: WebDriverException exception is '%s'", ex.getMessage()));
            return false;
        }
    }

    public int getNumberOfOptions(boolean isClicked) {
        try {
            logStartAction("get number of options within this dropdown list");
            if (isClicked) {
                this.click();
            } else {
                this.moveAndHoverOnControl();
            }
            List<WebElement> lstWebElements = DriverManager.getDriver().findElements(locatorItems);
            if (lstWebElements == null) {
                logEndAction("got number of options within this dropdown list is null");
                return -1;
            }
            int number = lstWebElements.size();
            logEndAction("got number of options within this dropdown list is " + number);
            return number;
        } catch (NullPointerException ex) {
            logEndAction(String.format("Exception: NullPointer exception is '%s'", ex.getMessage()));
            return -1;
        } catch (WebDriverException ex) {
            logEndAction(String.format("Exception: WebDriverException exception is '%s'", ex.getMessage()));
            return -1;
        }
    }

    public void selectAll(boolean isClicked) {
        if (isClicked) {
            this.click();
        } else {
            this.moveAndHoverOnControl();
        }
        if (lblSelectAll.isDisplayedShort(2)) {
            lblSelectAll.click();
        }
    }

    public void deSelectAll(boolean isClicked) {
        if (isClicked) {
            this.click();
        } else {
            this.moveAndHoverOnControl();
        }
        if (lblUnSelectAll.isDisplayedShort(4)) {
            lblUnSelectAll.click();
        }
    }

    public WebElement waitForElementToBePresent(By by) {
        return waitForElementToBePresent(by, timeOutInSeconds);
    }

    public WebElement waitForElementToBePresent(By by, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeOutInSeconds);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**********************
     * Private methods
     *********************/
    private boolean areMatched(List<String> items) {
        List<WebElement> lstWebElements = DriverManager.getDriver().findElements(locatorItems);
        if (lstWebElements == null) {
            System.out.println("get WebElements at areMatched method is null");
            return false;
        }
        if (items.size() != lstWebElements.size()) {
            System.out.println(String.format("A number of items within this ddb are %s != %s", items.size(), lstWebElements.size()));
            return false;
        }
        for (int i = 0; i < items.size(); i++) {
            String itemWithinDropDown = lstWebElements.get(i).getText();
            if (!itemWithinDropDown.equals(items.get(i))) {
                System.out.println(String.format("Error: Not match '%s' but found '%s' at index %s", itemWithinDropDown, items.get(i), i + 1));
                return false;
            }
        }
        return true;
    }

    private String selectItem(String name) {
        List<WebElement> lstWebElements = DriverManager.getDriver().findElements(locatorItems);
        String text = "";
        for (WebElement e : lstWebElements) {
            text = e.getText();
            if (text.toLowerCase().equals(name.toLowerCase())) {
                e.click();
                return text;
            }
        }
        return text;
    }
}
