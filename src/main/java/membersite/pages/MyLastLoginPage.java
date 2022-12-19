package membersite.pages;

import com.paltech.element.common.Label;
import controls.Table;
import membersite.pages.all.components.Header;

public class MyLastLoginPage extends Header {
    private Label lblPageTitle = Label.xpath("//app-my-last-login//div[@class='card-header']/h2");
    int totalCol = 5;
    int colLoginAndDateTime = 1;
    int colLoginStatus = 2;
    int colIpAdress= 3;
    int colDeviceInfo = 4;
    int colCountry=5;
    public Table tblMyLastLogin = Table.xpath("//app-my-last-login//div[contains(@class,'d-none d-lg-block')]//table",totalCol);

    public String getTitle (){
        return lblPageTitle.getText();
    }



}
