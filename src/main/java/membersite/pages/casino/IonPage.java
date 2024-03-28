package membersite.pages.casino;

import com.paltech.element.common.Link;

import java.util.ArrayList;
import java.util.List;

public class IonPage extends CasinoHomePage{
    Link lnkProductList = Link.xpath("//div[contains(@class,'obbySelection')]//label[@data-vendor='name']");

    @Override
    public List<String> getListProductsMenu() {
        List<String> lblList = new ArrayList<>();
        try {
            new ArrayList<>(lnkProductList.getWebElements()).stream().forEach(s -> lblList.add(s.getText().trim()));
        }catch (Exception e){
            System.out.println("DEBUG! Can not get list product");
        }
        return lblList;
    }
}
