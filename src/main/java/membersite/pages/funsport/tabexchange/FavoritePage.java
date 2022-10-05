package membersite.pages.funsport.tabexchange;

import com.paltech.element.common.Label;
import membersite.controls.funsport.MiniMarketControl;
import membersite.pages.all.components.Header;

public class FavoritePage extends Header {
    public Label lblNoRecord = Label.xpath("//div[@class='empty']");

    /**
     * This function get all market - event added
     */
    public int getMarketAddIndex(String event, String market){
        String _xpath ="//div[@id='grid-view-odd']";
        int i = 1;
        MiniMarketControl miniMarketControl;
        while (true){
            miniMarketControl = MiniMarketControl.xpath(String.format("%s[%s]",_xpath,i));
            if(!miniMarketControl.isDisplayed())
                return 0;
            if (!miniMarketControl.isMarketDisplay()){
                i = i++;
                continue;
            }
            if(miniMarketControl.getEventTitle().equalsIgnoreCase(event)) {
                if(miniMarketControl.getMarketTitle().equalsIgnoreCase(market))
                    return i;
            }
            if(i>10)
                return 0;
            i=i++;
        }
    }

    public void removeMarket(String event, String market){
        int i = getMarketAddIndex(event,market);
        MiniMarketControl miniMarketControl = MiniMarketControl.xpath(String.format("//div[@id='grid-view-odd'][%s]",i));
        miniMarketControl.clickRemove();
        miniMarketControl.isInvisible(2);
    }
}
