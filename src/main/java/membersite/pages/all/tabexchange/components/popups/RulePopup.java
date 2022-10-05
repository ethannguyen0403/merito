package membersite.pages.all.tabexchange.components.popups;

import com.paltech.element.common.Label;
import com.paltech.element.common.Popup;

public class RulePopup {
   private Popup poupRule = Popup.xpath("//div[@class='market-rule-content']");
   private Label lblTitle = Label.xpath("//div[@class='modal-header']");

   public String getTitle(){
       return lblTitle.getText();
   }


}
