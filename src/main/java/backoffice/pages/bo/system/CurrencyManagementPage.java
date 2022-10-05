package backoffice.pages.bo.system;

import com.paltech.element.common.Button;
import backoffice.controls.bo.ATable;
import backoffice.pages.bo.home.HomePage;

public class CurrencyManagementPage extends HomePage {
        public Button btnSave = Button.xpath("//button[contains(@class,'btn-core')]");
        private int totalColumn = 6;
        public ATable tblCurrencyManagement = ATable.xpath("//div[contains(@class,'currency-table')]",totalColumn);
}
