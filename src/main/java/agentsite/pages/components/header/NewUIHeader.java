package agentsite.pages.components.header;


import common.AGConstant;
import org.testng.Assert;

public class NewUIHeader extends Header {
    @Override
    public void verifyListSubMenuDisplay() {
        Assert.assertEquals(ddlMenu.getMenuList(), AGConstant.HomePage.MENULIST_SMA_NEWUI,"FAILED! List sub menu display incorrect");
    }
}
