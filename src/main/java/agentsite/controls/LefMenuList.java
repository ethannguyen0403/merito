package agentsite.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class LefMenuList extends BaseElement {
    static String _xPath;
    private String groupMenuXpath = "//div[contains(@class,'asia-menu-group')]";
    private String groupMenuTitleXpath = "//div[contains(@class,'asia-menu-title')]";
    private String subMenuXpath = "//div[@class='submenu']//div[contains(@class,'menu-item')]";
    private String collapseSubMenuXpath = "//div[@class='collapse-icon']";

    public LefMenuList(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
    }

    public static LefMenuList xpath(String xpathExpression) {
        return new LefMenuList(By.xpath(xpathExpression), xpathExpression);
    }

    private int getMenuIndex(String menu) {
        int i = 1;
        Label lblMenu;
        while (true) {
            lblMenu = Label.xpath(String.format("(%s%s)[%s]//span[2]", _xPath, groupMenuXpath, i));
            if (!lblMenu.isDisplayed()) {
                System.out.println("Debug: Not found the menu " + menu);
                return 0;
            }
            if (lblMenu.getText().equalsIgnoreCase(menu)) {

                System.out.println("Debug: Found the menu " + menu);
                return i;
            }
            i = i + 1;
        }
    }

    public boolean isMenuDisplay(String menu) {
        if (getMenuIndex(menu) == 0)
            return false;
        return true;
    }

    public Label getMenuControl(String menu) {
        int index = getMenuIndex(menu);
        return Label.xpath(String.format("%s%s[%s]", _xPath, groupMenuXpath, index));
    }

    public String getmenuAtribuite(String menu, String attributeName) {
        int index = getMenuIndex(menu);
        return Label.xpath(String.format("%s%s[%s]", _xPath, groupMenuXpath, index)).getAttribute(attributeName);
    }

    public List<String> getListSubMenu(String menu) {
        List<String> lstSubMenu = new ArrayList<>();
        // find
        int menuIndex = getMenuIndex(menu);
        String rootMenuXpath = String.format("(%s%s)[%s]", _xPath, groupMenuXpath, menuIndex);
        int i = 1;
        Label lblSubMenu;
        Label lblExpandSubMenu;
        // expand the root menu
        lblSubMenu = Label.xpath(String.format("(%s%s)[%s]", rootMenuXpath, subMenuXpath, i));
        if (!lblSubMenu.isDisplayed()) {
            Label.xpath(rootMenuXpath).click();
        }
        lblExpandSubMenu = Label.xpath(String.format("%s%s[%s]%s", rootMenuXpath, subMenuXpath, 1, collapseSubMenuXpath));
        if (lblExpandSubMenu.isDisplayed()) {
            lblExpandSubMenu.click();
        }
        while (true) {
            lblSubMenu = Label.xpath(String.format("(%s%s)[%s]", rootMenuXpath, subMenuXpath, i));
            if (!lblSubMenu.isDisplayed()) {
                return lstSubMenu;
            }
            lstSubMenu.add(lblSubMenu.getText().trim());
            i = i + 1;
        }
    }

    public void expandMenu(String menu) {
        int index = getMenuIndex(menu);
        Label lblMenu = Label.xpath(String.format("(%s%s)[%s]", _xPath, groupMenuXpath, index));
        String attribute = lblMenu.getAttribute("class");
        if (!attribute.contains("active")) {
            Label.xpath(String.format("(%s%s)[%s]//div[@class='icon']", _xPath, groupMenuXpath, index)).click();
        }
    }

    public void collapsedMenu(String menu) {
        int index = getMenuIndex(menu);
        Label lblMenu = Label.xpath(String.format("(%s%s)[%s]", _xPath, groupMenuXpath, index));
        String attribute = lblMenu.getAttribute("class");
        if (attribute.contains("active")) {
            Label.xpath(String.format("(%s%s)[%s]//div[@class='icon']", _xPath, groupMenuXpath, index)).click();
        }
    }

    public Label getSubMenuControl(String menu, String subMenu) {
        int menuIndex = getMenuIndex(menu);
        // expand menu
        expandMenu(menu);
        String rootMenuXpath = String.format("(%s%s)[%s]", _xPath, groupMenuXpath, menuIndex);
        int i = 1;
        Label lblSubMenu = Label.xpath(String.format("(%s%s)[%s]", rootMenuXpath, subMenuXpath, i));
        // expand the root menu when not found the sub list
        if (!lblSubMenu.isDisplayed()) {
            Label.xpath(rootMenuXpath).click();
        }
        Label lblExpandSubMenu;
        while (true) {
            lblSubMenu = Label.xpath(String.format("(%s%s)[%s]", rootMenuXpath, subMenuXpath, i));
            lblExpandSubMenu = Label.xpath(String.format("(%s%s)[%s]%s", rootMenuXpath, subMenuXpath, i, collapseSubMenuXpath));
            if (!lblSubMenu.isDisplayed()) {
                return null;
            }

            if (lblSubMenu.getText().trim().equalsIgnoreCase(subMenu)) {
                return lblSubMenu;
            }

            if (lblSubMenu.getText().trim().isEmpty()) {
                // Click + to expand to view all submenu
                if (lblExpandSubMenu.isDisplayed()) {
                    lblExpandSubMenu.click();
                    i = i + 1;
                    continue;
                }
            }
            i = i + 1;
        }
    }
    public Label getSubMenuControl(String menu, String subMenu1, String subMenu2) {
        int menuIndex = getMenuIndex(menu);
        // expand menu
        expandMenu(menu);
        String rootMenuXpath = String.format("(%s%s)[%s]", _xPath, groupMenuXpath, menuIndex);
        int i = 1;
        Label lblSubMenu1 = Label.xpath(String.format("(%s%s)[%s]", rootMenuXpath, subMenuXpath, i));
        Label lblSubMenu2;
        // expand the root menu when not found the sub list
        if (!lblSubMenu1.isDisplayed()) {
            Label.xpath(rootMenuXpath).click();
        }
        Label lblExpandSubMenu;
        while (true) {
            lblSubMenu1 = Label.xpath(String.format("(%s%s)[%s]", rootMenuXpath, subMenuXpath, i));
            lblSubMenu2 = Label.xpath(String.format("(%s%s)[%s]", rootMenuXpath, subMenuXpath, i+1));
            lblExpandSubMenu = Label.xpath(String.format("(%s%s)[%s]%s", rootMenuXpath, subMenuXpath, i, collapseSubMenuXpath));
            if (!lblSubMenu1.isDisplayed()) {
                return null;
            }

            if (lblSubMenu1.getText().trim().equalsIgnoreCase(subMenu1)) {
                lblSubMenu1.click();
                return lblSubMenu2;
            }

            if (lblSubMenu1.getText().trim().isEmpty()) {
                // Click + to expand to view all submenu
                if (lblExpandSubMenu.isDisplayed()) {
                    lblExpandSubMenu.click();
                    i = i + 1;
                    continue;
                }
            }
            i = i + 1;
        }
    }

    public void clickSubMenu(String menu, String submenu) {
        Label lblSubmenu = getSubMenuControl(menu, submenu);
        lblSubmenu.click();
    }
    public void clickSubMenu(String menu, String submenu1, String submenu2) {
        Label lblSubmenu = getSubMenuControl(menu, submenu1, submenu2);
        lblSubmenu.click();
    }

    public boolean isSubMenuDisplay(String menu, String submenu) {

        List<String> listSubmenu = getListSubMenu(menu);
        if (listSubmenu.contains(submenu)) {
            return true;
        }
        return false;
    }
}
