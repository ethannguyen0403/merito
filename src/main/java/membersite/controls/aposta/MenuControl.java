package membersite.controls.aposta;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class MenuControl extends BaseElement {
    protected String _xpath = null;
    public MenuControl(By locator, String xpathExpression) {
        super(locator);
        this._xpath = xpathExpression;
    }

    /**
     *
     * @param xpathParent is used for moving cursor of move and hover on this control to show the hidden items
     * @return DropDownBox
     */
    public static MenuControl xpath(String xpathParent) {
        return new MenuControl(By.xpath(xpathParent),xpathParent);
    }
    public List<String> getMenuList(){
        List<String> lstOption = new ArrayList<>();
        int index = 1;
        this.click();
        Label subMenu;
        while (true){
            subMenu = Label.xpath(String.format("%s[%s]",this._xpath, index));
            if(!subMenu.isDisplayed()){
                return lstOption;
            }
            lstOption.add(subMenu.getText());
            index += 1;
        }
    }

    public void clickMenu(String name){
        clickMenu(name,true);
    }
    public void clickMenu(String name,boolean isClick){
        int index = 1;
        if(isClick)
            this.click();
        Label subMenu;
        while (true){
            subMenu = Label.xpath(String.format("%s[%s]", this._xpath,index));
            if(!subMenu.isDisplayed()){
                return;
            }
            if(subMenu.getText().contains(name)){
                subMenu.click();
                break;
            }
            index += 1;
        }
        System.out.println(String.format("Error: There is no sub menu %s in the list", name));
    }
    public boolean iskMenuActive(String name){
        int index = 1;
        this.click();
        Label subMenu;
        while (true){
            subMenu = Label.xpath(String.format("%s[%s]", this._xpath,index));
            if(!subMenu.isDisplayed()){
                System.out.println(String.format("Error: There is no sub menu %s in the list", name));
                return false;
            }
            if(subMenu.getText().contains(name)){
               String atribute =subMenu.getAttribute("class");
               return atribute.contains("selected-tab");
            }
            index += 1;
        }
    }
}
