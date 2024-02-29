package membersite.pages.casino;

import com.paltech.element.common.Link;

import java.util.ArrayList;
import java.util.List;

public class IonPage extends CasinoHomePage{
    Link lnkProductList = Link.xpath("//div[contains(@class,'obbySelection')]//label[@data-vendor='name']");

    public List<String> getProductsList() {
        List<String> lblList = new ArrayList<>();
        new ArrayList<>(lnkProductList.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        return lblList;
    }
}
