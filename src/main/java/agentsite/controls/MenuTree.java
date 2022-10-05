package agentsite.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

// this control define the element kind of: <ul><li></li></ul?
public class MenuTree extends BaseElement {
    String _xPath;
    String _submenu;

    public MenuTree(By locator, String xpathExpression, String xpathSubmenu) {
        super(locator);
        this._xPath = xpathExpression;
        this._submenu = xpathSubmenu;

    }

    public static MenuTree xpath(String xpathExpression, String xpathSubmenu) {
        return new MenuTree(By.xpath(xpathExpression), xpathExpression, xpathSubmenu);
    }

    private int getMenuIndex(String menu){
        int i = 1;
        Label lblMenu;
        while (true){
            lblMenu = Label.xpath(String.format("%s%s[%s]",_xPath,_submenu,i));
            if(!lblMenu.isDisplayed()){
                System.out.println("Debug: Not found the menu "+menu);
                return 0;
            }
            if(lblMenu.getText().equalsIgnoreCase(menu)){
                System.out.println("Debug: Found the menu "+menu);
                return i;
            }
            i = i+1;
        }
    }

    public void clickMenu(String menu){
        int index = getMenuIndex(menu);
        Label.xpath(String.format("%s%s[%s]",_xPath,_submenu,index)).click();
    }

    public List<String> getListSubMenu(){
        List<String> lstSubMenu = new ArrayList<>();
        // find
        int i = 1;
        Label lblMenu;
        while (true){
            lblMenu = Label.xpath(String.format("%s%s[%s]",_xPath,_submenu,i));
            if(!lblMenu.isDisplayed()){
                return lstSubMenu;
            }
            lstSubMenu.add(lblMenu.getText().trim());
            i = i+1;
        }
    }


}
