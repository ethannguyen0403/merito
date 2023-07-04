package membersite.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import controls.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author isabella.huynh
 * created on 4/10/2020
 */
public class EditStakeControl extends BaseElement {
    static String _xpath = "//div[@class='edit-stakes-body']";
    private int colTotal = 6;
    private Table tblStakes = Table.xpath(String.format("%s/table", _xpath), colTotal);
    private Label lblErrorMsg = Label.id("fbtnerror");
    private Button btnSave = Button.id("update-edit-stakes");
    private Button btnCancel = Button.id("cancel-edit-stakes");
    private TextBox txtStakes = TextBox.xpath("//input[contains(@class,'input-fast-button-setting')]");

    private EditStakeControl(By locator, String xpath) {
        super(locator);
        _xpath = xpath;
    }

    public static EditStakeControl xpath(String xpathExpression) {
        return new EditStakeControl(By.xpath(xpathExpression), xpathExpression);
    }

    public void cancelEditStake() {
        btnCancel.click();
    }

    public String getErrorMessage() {
        return lblErrorMsg.getText();
    }

    public void updateStake(List<String> lst, boolean isSave) {
        for (int i = 0, n = lst.size(); i < n; i++) {
            WebElement element = txtStakes.getWebElements().get(i);
            element.click();
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(lst.get(i));
        }
        if (isSave) {
            btnSave.doubleClick();
            waitForControlInvisible();
        }
    }

    public List<String> getStakes() {
        List<String> list = new ArrayList<>();
        for (int i = 0, n = txtStakes.getWebElements().size(); i < n; i++) {
            list.add(txtStakes.getWebElements().get(i).getAttribute("value"));
        }
        return list;

    }


}
