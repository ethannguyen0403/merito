package membersite.pages;

import com.paltech.element.common.Label;
import controls.Table;
import membersite.pages.components.ComponentsFactory;

public class MyLastLoginPage extends HomePage {
    int totalCol = 5;
    public Table tblMyLastLogin = Table.xpath("//app-my-last-login//div[contains(@class,'d-none d-lg-block')]//table", totalCol);
    int colLoginAndDateTime = 1;
    int colLoginStatus = 2;
    int colIpAdress = 3;
    int colDeviceInfo = 4;
    int colCountry = 5;
    private Label lblPageTitle = Label.xpath("//app-my-last-login//div[@class='card-header']/h2");

    public MyLastLoginPage(String types) {
        super(types);
        eventContainerControl = ComponentsFactory.eventContainerControlObject(types);
        betsSlipContainer = ComponentsFactory.betsSlipContainerObject(types);
        myBetsContainer = ComponentsFactory.miniMyBetsContainerObject(types);
    }

    public String getTitle() {
        return lblPageTitle.getText();
    }


}
