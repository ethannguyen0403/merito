package membersite.pages.popup.mymarketpopup;

import common.MemberConstants;
import org.testng.Assert;

public class Fair999MyMarketPopup extends MyMarketPopupContainer{
    public void verifyMyMarketPopupUI(){
        Assert.assertEquals(lblTitle.getText(), MemberConstants.MyMarketsPopup.TITLE, "FAILED! My Markets pop up title is not correct");
        Assert.assertTrue(lblNote.getText().trim().contains(MemberConstants.MyMarketsPopup.NOTES), "FAILED! My Markets noted title is not correct");
        Assert.assertEquals(tbMyMarkets.getHeaderNameOfRows(), MemberConstants.MyMarketsPopup.TABLE_MY_MARKETS_HEADER, "FAILED! My Markets header is not correct");
    }
}
