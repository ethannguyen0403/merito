package membersite.pages.components.betslipcontainer;

import com.paltech.element.common.Button;
import com.paltech.element.common.Icon;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import membersite.controls.EditStakeControl;
import membersite.objects.sat.Market;
import membersite.objects.sat.Order;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class NewUIBetsSlipContainer extends BetsSlipContainer {
    // e.g//div[contains(@class,'container-bet-slip')]
    static String _xpath = "";
    public Label lblNoBetInBetSlipMessage = Label.xpath("//div[contains(@class,'message-empty')]");
    public EditStakeControl editStakeControl = EditStakeControl.xpath("//div[contains(@class,'edit-stakes-body')]");
    public Label lblMinMaxStakeErrorMessage = Label.xpath("//div[contains(@class,'bet-info error')]");
    private Label lblSelectionHeader = Label.xpath("//div[contains(@class,'betslip-title')]//span[1]");
    Label lblBetSlipErrorMessage = Label.xpath("//div[contains(@class,'betslip-error')]");
    private Button btnPlaceBet = Button.xpath("//button[contains(@class,'button-primary')]");
    private Button btnClearAll = Button.xpath("//button[contains(@class,'btn-cancel')]");
    private String lblSelectionNameXPath = "//span[(@class='runner-market')]";
    private Label lblSelection = Label.xpath(lblSelectionNameXPath);
    private Label lblClickOnOdds;
    private Label lblAccepting = Label.xpath("//div[@class='loading-text']");
    private String iconRemoveBetXpath = "//span[(@class='remove-bet')]";
    private String txtOddsXPath = "//input[contains(@class,'odds')]";
    private String txtStakeXPath = "//input[contains(@class,'stake')]";
    private TextBox txtOdds = TextBox.xpath(txtOddsXPath);
    private TextBox txtStake = TextBox.xpath(txtStakeXPath);
    private String lblProfitLiabilityXPath = "//span[contains(@class,'bet-slip-profit')]";
    private Button btnEditStake = Button.xpath("//div[contains(@class,'edit-stake-header')]");
    private Label lblBetSlipTitle = Label.xpath("//ul[@class='nav nav-tabs row nav-bet-slip']");
    private Button btnQuickStakes = Button.xpath("//button[contains(@class,'fastbtn')]");
    private Label lblProfitLiability = Label.xpath("//span[contains(@class,'bet-slip-profit')]");

    @Override
    public boolean isPlacBetButtonEnable(){
        return btnPlaceBet.isEnabled();
    }

    @Override
    public void verifyInfoBetSlipAndOddsPage(Market market, Order order) {
    String actualLiability = Label.xpath(lblProfitLiabilityXPath).getText().trim();
    String expectedLiability = String.format("%.2f", order.getLiablity(order.getIsBack(), Double.valueOf(order.getOdds()) , Double.valueOf(order.getStake())));
        Assert.assertEquals(actualLiability, expectedLiability, "FAILED! Liability of selection is not correct");
        Assert.assertEquals(market.getSelectionName(), order.getSelectionName(), "FAILED! Selection name is not correct");
        Assert.assertEquals(market.getBtnOdd().getText(), order.getOdds(), "FAILED! Odds is not corrct");
    }

    public void cancelAllSelections() {
        btnClearAll.click();
    }

    public void inputStake(String stake) {
        if(txtStake.isDisplayed(2)) {
            txtStake.click();
            txtStake.sendKeys(stake);
        }
        lblSelectionHeader.click();
        // waiting for loading completely
        btnPlaceBet.isInvisible(2);
    }

    @Override
    public void removeBetByBinIcon(Market market) {
    Icon iconBin = Icon.xpath(String.format("//div[contains(@title,'%s')]/../preceding-sibling::span", market.getSelectionName()));
    iconBin.click();
    }

    public String getBetSlipErrorMessage() {
        return lblBetSlipErrorMessage.getText();
    }

    /**
     * Place a  bet input odds or stake
     *
     * @param odds
     * @param stake
     */
    public void placeBet(String odds, String stake) {
        if (!odds.isEmpty()) {
            txtOdds.sendKeys(odds);
        }
        inputStake(stake);
      //  txtOdds.click();
        if(btnPlaceBet.isClickable(2)){
            btnPlaceBet.click();
            // waiting accepting label disappear
            lblAccepting.waitForControlInvisible(10, 10);
        }
    }

    // Just input stake after click on odds then place bet
    public void placeBet(String stake) {
        placeBet("", stake);
    }

    /**
     * Get all bets added to bet slips
     *
     * @return the list of bet
     */
    public List<Order> getBet() {
        List<Order> lstOrders = new ArrayList<>();
        int items = txtStake.getWebElements().size();
        Order order = new Order();
        for (int i = 0; i < items; i++) {
            order = getBet(i);
            lstOrders.add(order);
        }
        return lstOrders;
    }

    /**
     * Get bet added in bet slip  by expected index
     *
     * @param index
     * @return Bet info (Selection name, odds, stake, profit&Loss)
     */
    public Order getBet(int index) {
        String selectionName = lblSelection.getWebElements().get(index).getText();
        String odds = txtOdds.getWebElements().get(index).getAttribute("value");
        String stake = txtStake.getWebElements().get(index).getAttribute("value");
        String profit = lblProfitLiability.getWebElements().get(index).getText();
        String liability = lblProfitLiability.getWebElements().get(index).getText();
        return new Order.Builder()
                .selectionName(selectionName)
                .odds(odds)
                .stake(stake)
                .profit(profit)
                .liablity(liability)
                .build();

    }

    public void clearAll() {
        btnClearAll.click();
    }

    public String getEmptyBetMessage() {
        return lblNoBetInBetSlipMessage.getText();
    }

    public EditStakeControl openEditStake() {
        btnEditStake.click();
        return editStakeControl;
    }

    public List<String> getListFastButton() {
        List<String> fastButtonValue = new ArrayList<>();
        for (int i = 0, n = btnQuickStakes.getWebElements().size(); i < n; i++) {
            fastButtonValue.add(btnQuickStakes.getWebElements().get(i).getAttribute("value"));
        }
        return fastButtonValue;
    }

    public boolean isEditStakeControlDisplay() {
      /*  editStakeControl.waitForElementToBePresent(this.locator);
        return editStakeControl.isDisplayed(5);*/
        return editStakeControl.isDisplayed(5);

    }

    public boolean isStakeDisplayAsClickingOnFastButton() {
        WebElement element = null;
        for (int i = 0, n = btnQuickStakes.getWebElements().size(); i < n; i++) {
            element = btnQuickStakes.getWebElements().get(i);
            element.click();
            String actual = element.getAttribute("value");
            if (!actual.equals(txtStake.getAttribute("value"))) {
                System.out.println(String.format("ERROR: Expected fast button is %s but found %s ", element.getAttribute("value"), txtStake.getAttribute("value")));
                return false;
            }
        }
        return true;
    }

    public boolean isErrorDisplayed(Label label, String errorMessage) {
        return label.getText().contains(errorMessage);
    }

    public void clickSubmit() {
        if(btnPlaceBet.isClickable(2)){
            btnPlaceBet.click();
            // waiting accepting label disappear
            lblAccepting.waitForControlInvisible(10, 10);
        }
    }
}
