package backoffice.pages.bo.operations;

import agentsite.controls.Cell;
import backoffice.controls.bo.ARow;
import backoffice.pages.bo.home.HomePage;
import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class MixedPTConfigurationPage extends HomePage {

    Label lblTitlePage = Label.id("bo-page-title");
    DropDownBox ddpBrand = DropDownBox.name("brands");
    TextBox txtUserName = TextBox.name("username");
    Button btnSearch = Button.name("search");
    private int totalCol = 10;
    private int userNameCol = 2;
    private int brandCol = 7;

    public List<String> getFirstRowData() {
        List<String> lstRow = new ArrayList<>();
        for (int i = 1; i < totalCol; i++) {
            String xpathLocator = String.format("//div[@class='custom-table-body perfect-scroll-support ng-star-inserted']//div[@class='ps-content']//div[1]//div[%s]", i);
            Cell cellValueLocator = Cell.xpath(xpathLocator);
            lstRow.add(cellValueLocator.getText());
        }
        return lstRow;
    }

    public void filter(String brandName, String userName) {
        ddpBrand.selectByVisibleText(brandName);
        txtUserName.sendKeys(userName);
        btnSearch.click();
        btnSearch.isInvisible(2);
    }

    public void verifyResultDisplaysCorrect(String brandName, String userName) {
        int numberRows = getNumberOfRows(true);
        for (int i = 1; i <= numberRows; i++) {
            String xpathLocatorUserName = String.format("//div[@class='custom-table-body perfect-scroll-support ng-star-inserted']//div[@class='ps-content']//div[%s]//div[%s]", i, userNameCol);
            String xpathLocatorBrandName = String.format("//div[@class='custom-table-body perfect-scroll-support ng-star-inserted']//div[@class='ps-content']//div[%s]//div[%s]", i, brandCol);
            Cell userNameCellLocator = Cell.xpath(xpathLocatorUserName);
            Cell brandNameCellLocator = Cell.xpath(xpathLocatorBrandName);
            Assert.assertEquals(userNameCellLocator.getText(), userName, "FAILED! Filter user name: " + userName + " but found: " + userNameCellLocator.getText());
            Assert.assertEquals(brandNameCellLocator.getText(), brandName, "FAILED! Filter brand name: " + brandName + " but found " + brandNameCellLocator.getText());
        }

    }

    private int getNumberOfRows(boolean isMoving) {
        int numberRows = 0;
        int i = 1;
        while (true) {
            String rowXpath = String.format("//div[@class='custom-table-body perfect-scroll-support ng-star-inserted']//div[contains(@class,'ps-content')]/div[%d]", i);
            ARow iRow = ARow.xpath(String.format(rowXpath, i));
            if (!iRow.isDisplayedShort(3)) {
                return numberRows;
            } else {
                numberRows += 1;
                i++;
                if (isMoving) {
                    iRow.scrollDownInDistance();
                }
            }
        }
    }

}
