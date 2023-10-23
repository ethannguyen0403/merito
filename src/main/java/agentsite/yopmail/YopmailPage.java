package agentsite.yopmail;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.Keys;

public class YopmailPage {
    public TextBox txtEmail = TextBox.xpath("//input[@class='ycptinput']");

    public static void openNewTab(String url) {
        DriverManager.getDriver().newTab();
        DriverManager.getDriver().switchToWindow();
        DriverManager.getDriver().get(url);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    protected static YopmailPage navigateToYopmail(String url){
        openNewTab(url);
        return new YopmailPage();
    }
    public YopmailMailBoxPage navigateMailBox(String emailAddress){
        txtEmail.waitForControlInvisible(5,1);
        txtEmail.sendKeys(emailAddress);
        txtEmail.type(false, Keys.ENTER);
        YopmailMailBoxPage yopmailMailBoxPage = new YopmailMailBoxPage();
        DriverManager.getDriver().switchToFrame("ifmail");
        return yopmailMailBoxPage;
    }

}
