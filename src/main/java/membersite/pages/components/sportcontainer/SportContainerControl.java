package membersite.pages.components.sportcontainer;

import membersite.objects.funsport.Odd;
import membersite.objects.sat.Event;
import membersite.objects.sat.Market;
import membersite.pages.MarketPage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by isabella.huynh
 * created on 10/3/2020
 */
public class SportContainerControl {
    public void clickOdd(Odd odd) {
    }

    public void addRemoveFavorite(int eventIndex, boolean isAdd){
    }
    public boolean isIconFavoriteDisplay(int eventIndex, boolean isAdd){return false;
    }
    public String getMaxLiabiliy(List<String> expectedForeCast) {
        return  "";
    }


    public String getEventIDHasProductData(String product){
        return "";
    }

    public String getEventIDHasProductData(String product,String status){
        return "";
    }

    public Event setEventLink(Event event){
        return null;
    }

    public void clickOdd(Market market){

    }
}
