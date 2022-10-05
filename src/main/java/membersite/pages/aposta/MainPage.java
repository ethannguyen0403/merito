package membersite.pages.aposta;
import com.paltech.driver.DriverManager;
import com.paltech.element.common.IFrame;
import com.paltech.element.common.Image;
import org.openqa.selenium.support.PageFactory;
import membersite.pages.all.components.Header;

public class MainPage extends Header {
    public Image bannerImg = Image.xpath("//div[@class='carousel-inner']");
    public Image imgCricket = Image.xpath("//app-banner-sport//div[contains(@class,'row justify-content-center')]/div[1]//img");
    public Image imgExchangeGame = Image.xpath("//app-banner-sport//div[contains(@class,'row justify-content-center')]/div[2]//img");
    public Image imgCasio = Image.xpath("//app-banner-sport//div[contains(@class,'row justify-content-center')]/div[3]//img");
    public Image imgTeenpati = Image.xpath("//app-banner-sport//div[contains(@class,'row justify-content-center')]/div[4]//img");
    public Image imgSlot = Image.xpath("//app-banner-sport//div[contains(@class,'row justify-content-center')]/div[5]//img");
    public Image imgFootBall = Image.xpath("//app-banner-sport//div[contains(@class,'row justify-content-center')]/div[6]//img");
    public Image imgSuperNowa = Image.xpath("//app-banner-sport//div[contains(@class,'row justify-content-center')]/div[7]//img");
    public Image imgLiveDealer = Image.xpath("//app-banner-sport//div[contains(@class,'row justify-content-center')]/div[8]//img");

    public <T> T clickImage(String imageName, Class<T> expectedPage) {
        switch (imageName){
            case "Cricket":
                imgCricket.click();
                break;
            case "Slot":
                imgSlot.click();
                break;
            case "Exchange Games":
                imgExchangeGame.click();
                IFrame iFrame = IFrame.xpath("//iframe");
                iFrame.waitForControlInvisible();
                DriverManager.getDriver().switchToFrame(0);
                break;
            case "Casino":
                imgCasio.click();
                break;
            case "Football":
                imgFootBall.click();
                break;
            case "Live Dealer":
                imgLiveDealer.click();
                break;
            case "Teenpati":
                imgTeenpati.click();
                break;
            case "Supernowa":
                imgSuperNowa.click();
                break;
        }
        return PageFactory.initElements(DriverManager.getDriver(),expectedPage);
    }
}
