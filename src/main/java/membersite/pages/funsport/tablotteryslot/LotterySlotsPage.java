package membersite.pages.funsport.tablotteryslot;

import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.Menu;
import membersite.controls.DropDownBox;
import membersite.pages.all.components.Header;

public class LotterySlotsPage extends Header {
    public Image imgLogo = Image.xpath("//a[@class='logo ml-2']/img");
    public DropDownBox ddbMyAccount = DropDownBox.xpath("//div[@class='account d-block']", "//ul[@class='dropdown-menu']//a");
    public Label lblCurrentCreditTitle = Label.xpath("(//span[@class='title'])[1]");
    public Label lblCurrentCredit = Label.xpath("(//span[@class='bal-val'])[1]");
    public Menu menuGameGroup = Menu.xpath("//div[@class='lottery-game-menu']//ul/li");
}
