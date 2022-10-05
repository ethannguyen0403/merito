package membersite.pages.funsport.tabexchange;

import membersite.controls.funsport.OddPageControl;
import membersite.pages.all.tabexchange.SportPage;

public class BasketBallPage extends SportPage {
    public OddPageControl oddPageControl = OddPageControl.xpath("//div[@id='odds-content']", true);

}
