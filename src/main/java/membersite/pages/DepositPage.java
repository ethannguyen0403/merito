package membersite.pages;

import com.paltech.element.common.Label;
import membersite.pages.components.ComponentsFactory;
import membersite.pages.components.deposit.DepositContainer;
import membersite.pages.components.mybet.MyBetsContainer;

import java.util.ArrayList;
import java.util.List;

public class DepositPage extends HomePage {

    public Label lblTitle = Label.xpath("//app-deposit//h5");
    public DepositContainer depositContainer;

    public DepositPage(String types) {
        super(types);
        depositContainer = ComponentsFactory.depositContainerObject(types);
    }



}
