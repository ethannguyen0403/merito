package membersite.pages.components.betslipcontainer;

import com.paltech.element.common.Label;
import membersite.controls.EditStakeControl;
import membersite.objects.sat.Order;

import java.util.List;

public class BetsSlipContainer {
    public Label lblSuspendedErrorMessage = Label.xpath("//div[@class='modal-body' and contains(text(), 'has been Suspended')]");
    public Label lblMinMaxStakeErrorMessage = Label.xpath("//div[contains(@class,'bet-info error')]");
    public void cancelAllSelections() {

    }
    public String getBetSlipErrorMessage(){return "";}
    public void inputStake (String stake) {

    }

    /**
     * Place a  bet input odds or stake
     * @param odds
     * @param stake
     */
    public void placeBet(String odds, String stake) {
    }

    // Just input stake after click on odds then place bet
    public void placeBet(String stake) {
        placeBet("",stake);
    }

    /**
     * Get all bets added to bet slips
     * @return the list of bet
     */
    public List<Order> getBet(){
        return null;
    }

    /**
     * Get bet added in bet slip  by expected index
     * @param index
     * @return Bet info (Selection name, odds, stake, profit&Loss)
     */
    public Order  getBet(int index){
       return null;

    }

    public void clearAll(){

    }

    public String getEmptyBetMessage()
    {
        return "";
    }

    public EditStakeControl openEditStake(){return null;
    }

    public List<String> getListFastButton()
    {
        return null;
    }

    public boolean isEditStakeControlDisplay() {
      return false;
    }

    public boolean isStakeDisplayAsClickingOnFastButton()
    {
        return false;
    }

    public boolean isErrorDisplayed(Label label, String errorMessage) {
        return false;
    }
}
