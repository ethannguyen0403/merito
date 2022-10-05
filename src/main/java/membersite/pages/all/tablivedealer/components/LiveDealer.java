package membersite.pages.all.tablivedealer.components;

import com.paltech.element.common.Tab;
import membersite.pages.all.components.Header;
import membersite.pages.all.tablivedealer.AsianRoomPage;
import membersite.pages.all.tablivedealer.EuropeanRoomPage;

public class LiveDealer extends Header {
    private Tab tabAsianRoom = Tab.xpath("//a[@routerlink='super-spade']");
    private Tab tabEuropeanRoom = Tab.xpath("//a[@routerlink='ezugi']");

    public AsianRoomPage switchAsianRoomTab(){
        tabAsianRoom.click();
        return new AsianRoomPage();
    }

    public EuropeanRoomPage switchEuropeansRoomTab(){
        tabEuropeanRoom.click();
        return new EuropeanRoomPage();
    }
}
