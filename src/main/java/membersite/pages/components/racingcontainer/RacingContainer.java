package membersite.pages.components.racingcontainer;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import membersite.controls.sat.RacingMarketControl;
import membersite.pages.MarketPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class RacingContainer  {

    public String getCountry(int index)
    {
        return getAllCountry().get(index);
    }

    public List<String>  getAllCountry()
    {

        return null;
    }

    public void expandAllCountrry()
    {

    }
    public void collapseAllCountry(){
    }

    public List<String> getAllTrackName(String country)
    {
        return null;
    }

    public List<String> getAllRacingList(String country, String trackName){
              return null;
    }

    public MarketPage clickRacing(String country, String trackName, String race,String brand)
    {
        return new MarketPage(brand);
    }
    public boolean isNoRace(){
        return false;
    }


}
