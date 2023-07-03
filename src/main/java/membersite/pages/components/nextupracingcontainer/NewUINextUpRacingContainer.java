package membersite.pages.components.nextupracingcontainer;


import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPublicKeyParameters;

public class NewUINextUpRacingContainer extends NextUpRacingContainer {
    private Label lblNextUpRacing = Label.xpath("//div[contains(@class,'mod-comingup')]//div[@class='mod-header']//span");
    private Link lnkFristHR = Link.xpath("//span[contains(text(),'Horse Racing')]/following::ul[1]/li[1]");
    private Link lnkFristGH = Link.xpath("//span[contains(text(),'Greyhound Racing')]/following::ul[1]/li[1]");

    public void clickFristHR(){
            lnkFristHR.click();

    }
    public boolean hasNextUpHR(){
        return lnkFristHR.isDisplayed();
    }
    public void clickFristGH(){
        lnkFristGH.click();
    }
    public boolean hasNextUpGR(){
        return lnkFristGH.isDisplayed();
    }
}
