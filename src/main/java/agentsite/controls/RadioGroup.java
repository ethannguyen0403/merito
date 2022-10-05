package agentsite.controls;

import com.paltech.element.BaseElement;
import com.paltech.element.common.Label;
import com.paltech.element.common.RadioButton;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;


public class RadioGroup extends BaseElement {
    String _xPath;//div[@name = 'check-box-bet-type' and @class='form-row last-bet-filter']/div[@class='form-filter']


    public RadioGroup(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
    }

    public static RadioGroup xpath(String xpathExpression) {
        return new RadioGroup(By.xpath(xpathExpression), xpathExpression);
    }

    private List<RadioControl> getAllRadioButton(){
        List<RadioControl> lstRbBtns = new ArrayList<>();
        int i = 1;
        String rbXpathIndex ;
        while (true){
            rbXpathIndex = String.format("%s[%s]",_xPath,i);
            if(!RadioControl.xpath(rbXpathIndex).isDisplayed()){
                return lstRbBtns;
            }
            lstRbBtns.add(RadioControl.xpath(rbXpathIndex));
            i = i+1;
        }
    }

    public List<String> getAllLables()
    {
        List<String> listLabel = new ArrayList<>();
        List<RadioControl> lstRadioButtons = getAllRadioButton();
        for(RadioControl rb : lstRadioButtons){
            listLabel.add(rb.getLabel());
        }
        return listLabel;
    }

    public void clickRadio (String text)
    {
        List<RadioControl> lstRadioButtons = getAllRadioButton();
        for(RadioControl rb : lstRadioButtons){
            if(rb.getLabel().equals(text)){
                rb.click();
                break;
            }
        }

    }




}
