package agentsite.pages.components.header;

import common.AGConstant;
import org.testng.Assert;

public class OldUIHeader extends Header {
    @Override
    public void verifyListSubMenuDisplay() {
        Assert.assertEquals(ddlMenu.getMenuList(), AGConstant.HomePage.MENULIST,"FAILED! List sub menu display incorrect");
    }
}
