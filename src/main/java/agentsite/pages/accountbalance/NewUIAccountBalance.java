package agentsite.pages.accountbalance;

import common.AGConstant;
import org.testng.Assert;

import java.util.List;

public class NewUIAccountBalance extends AccountBalance{
    public void verifyTitleDisplay() {
        Assert.assertEquals(defineBalanceInfo(true), AGConstant.HomePage.AccountBalance.TITLE_LST,"FAILED! Title List displays incorrect!");
    }
}
