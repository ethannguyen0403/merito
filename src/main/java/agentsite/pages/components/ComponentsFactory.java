package agentsite.pages.components;

import agentsite.pages.components.header.Header;
import agentsite.pages.components.header.NewUIHeader;
import agentsite.pages.components.header.OldUIHeader;
import agentsite.pages.components.leftmenu.LeftMenu;
import agentsite.pages.components.leftmenu.NewUILeftMenu;
import agentsite.pages.components.leftmenu.OldUILeftMenu;

public class ComponentsFactory {
    public static LeftMenu leftMenuObject(String types){
        switch (types){
            case "funsport":
                return new NewUILeftMenu();
            default:
                return new OldUILeftMenu();
        }
       }
    public static Header headerObject(String types){
        switch (types){
            case "satsport":
                return new OldUIHeader();
            default:
                return new NewUIHeader();
        }
    }
}
