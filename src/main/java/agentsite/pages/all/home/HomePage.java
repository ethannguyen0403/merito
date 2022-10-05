package agentsite.pages.all.home;

import agentsite.controls.Table;
import agentsite.pages.all.components.LeftMenu;

public class HomePage extends LeftMenu {
    private int totalCol = 2;
    public int colName = 1;
    public int colValue = 2;
    Table tblSMAInfo = Table.xpath("//table[@class='ptable report ng-scope']", totalCol);


}
