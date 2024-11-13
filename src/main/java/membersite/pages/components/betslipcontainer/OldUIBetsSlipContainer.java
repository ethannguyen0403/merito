package membersite.pages.components.betslipcontainer;

import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class OldUIBetsSlipContainer extends BetsSlipContainer {
    private String txtOddsXPath = "//input[contains(@class,'odds')]";
    private String txtStakeXPath = "//input[contains(@class,'stake')]";
    private Button btnPlaceBet = Button.xpath("//input[@id='place-bet']");
    private Label lblAccepting = Label.xpath("//div[@class='loading-text']");
    private TextBox txtOdds = TextBox.xpath(txtOddsXPath);
    private TextBox txtStake = TextBox.xpath(txtStakeXPath);

    public void placeBet(String odds, String stake) {
        if (!odds.isEmpty()) {
            txtOdds.sendKeys(odds);
        }
        inputStake(stake);
        if(btnPlaceBet.isClickable(2)){
            btnPlaceBet.click();
            // waiting accepting label disappear
            lblAccepting.waitForControlInvisible(5, 5);
        }
    }

    public void inputStake(String stake) {
        if(txtStake.isDisplayed(2)) {
            txtStake.click();
            txtStake.sendKeys(stake);
        }
        // waiting for loading completely
        btnPlaceBet.isInvisible(2);
    }
}
