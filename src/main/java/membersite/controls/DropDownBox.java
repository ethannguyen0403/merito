package membersite.controls;

import com.paltech.driver.DriverManager;
import com.paltech.element.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DropDownBox extends BaseElement {
    protected final int timeOutInSeconds = 10;
    private By locatorItems;

    public DropDownBox(By locator, By locatorChildren) {
        super(locator);
        locatorItems = locatorChildren;
    }

    /**
     * @param xpathParent   is used for moving cursor of move and hover on this control to show the hidden items
     * @param xpathChildren to get text of items within ddb
     * @return DropDownBox
     */
    public static DropDownBox xpath(String xpathParent, String xpathChildren) {
        return new DropDownBox(By.xpath(xpathParent), By.xpath(xpathChildren));
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
                logEndAction(String.format("Waiting for child element present"));
                List<WebElement> lstWebElements = DriverManager.getDriver().findElements(locatorItems);

                if (!Objects.nonNull(lstWebElements)) {
                    logEndAction(String.format("Re-click on parent node"));
                    this.click();
                }
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
        selectByIndex(index, false);
    }

    /**
     * Allowing to select an item within this ddb by index
     *
     * @param index begin is 0
     */
    public void selectByIndex(int index, boolean isHover) {
        logStartAction(String.format("select an number item '%d'", index));
        try {
            if (isHover) {
                this.moveAndHoverOnControl();
            }
            List<WebElement> lstWebElements = DriverManager.getDriver().findElements(locatorItems);
            if (lstWebElements.size() < index) {
                logEndAction(String.format("Error: Index '%d' is greater than %d a number of items within this ddb", index, lstWebElements.size()));
                return;
            }
            String text = lstWebElements.get(index).getText();
            lstWebElements.get(index).click();

        } catch (NullPointerException ex) {
            logEndAction(String.format("Exception: NullPointer exception is '%s'", ex.getMessage()));
        } catch (WebDriverException ex) {
            logEndAction(String.format("Error: WebDriverException exception is '%s'", ex.getMessage()));
        }

    }

    /**
     * Getting item name selected in this ddb
     */
    public String getOptionByIndex(int index) {
        logStartAction("get text of index option");
        try {
            WebElement element = DriverManager.getDriver().findElements(locatorItems).get(index);
            logEndAction(String.format("selected the item at index %d with name: '%s'", index, element.getText().trim()));
            return element.getText().trim();
        } catch (NullPointerException ex) {
            logEndAction(String.format("Exception: NullPointer exception is '%s'", ex.getMessage()));
            return "";
        } catch (WebDriverException ex) {
            logEndAction(String.format("Error: WebDriverException exception is '%s'", ex.getMessage()));
            return "";
        } catch (ArrayIndexOutOfBoundsException ex) {
            logEndAction(String.format("Error: Index is out of range '%s'", ex.getMessage()));
            return "";
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

    public WebElement waitForElementToBePresent(By by) {
        return waitForElementToBePresent(by, timeOutInSeconds);
    }

    public WebElement waitForElementToBePresent(By by, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<String> getOptions(boolean isClick) {
        List<WebElement> lstWebElements = DriverManager.getDriver().findElements(locatorItems);
        List<String> lstOption = new ArrayList<>();
        if (lstWebElements == null) {
            System.out.println("get WebElements at areMatched method is null");
            return lstOption;
        }
        for (int i = 0; i < lstWebElements.size(); i++) {
            String text = lstWebElements.get(i).getText().trim();
            lstOption.add(i, text);
        }
        return lstOption;
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
            text = e.getText().trim();
            if (text.toLowerCase().equals(name.toLowerCase().trim())) {
                e.click();
                return text;
            }
        }
        return null;
    }
}
