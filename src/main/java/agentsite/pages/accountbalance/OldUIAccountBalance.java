package agentsite.pages.accountbalance;

import common.AGConstant;
import org.testng.Assert;

import java.util.List;

public class OldUIAccountBalance extends AccountBalance{
    public void verifyTitleDisplay() {
        Assert.assertEquals(defineBalanceInfo(false), AGConstant.HomePage.AccountBalance.OLDUI_TITLE_LST,"FAILED! Title List displays incorrect!");
    }
}
