package membersite.pages.funsport.tablivedealer.components;

import com.paltech.element.common.Tab;
import membersite.pages.components.header.Header;
import membersite.pages.funsport.tablivedealer.AsianRoomPage;
import membersite.pages.funsport.tablivedealer.EuropeanRoomPage;

public class LiveDealer extends Header {
    private Tab tabAsianRoom = Tab.id("super-spade");
    private Tab tabEuropeanRoom = Tab.xpath("//div[@id='nav-menu']//a[@id='live-dealer']/span");

    public AsianRoomPage switchAsianRoomTab(){
        tabAsianRoom.click();
        return new AsianRoomPage();
    }

    public EuropeanRoomPage switchEuropeansRoomTab(){
        tabEuropeanRoom.click();
        return new EuropeanRoomPage();
    }
}
