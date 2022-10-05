package membersite.pages.all.components;

import com.paltech.driver.DriverManager;
import com.paltech.element.common.Image;
import com.paltech.element.common.Label;
import com.paltech.element.common.Link;
import membersite.pages.all.footer.AboutPage;
import membersite.pages.all.footer.PrivacyPolicyPage;
import membersite.pages.all.footer.RuleRegulationPage;
import membersite.pages.all.footer.TermConditionPage;

public class Footer {
    public Label lblFooter = Label.xpath("//div[contains(@class,'footer-wrapper')]//div[contains(@class,'links')]");
    //div[@id='ldsSpinner']
    public Link lnkPrivacyPolicy = Link.xpath("//div[contains(@class,'links')]//a[contains(text(),'Privacy Policy')]");
    public Link lnkAbout = Link.xpath("//div[contains(@class,'links')]//a[contains(text(),'About Us')]");
    public Link lnkRuleRegulation = Link.xpath("//div[contains(@class,'links')]//a[contains(text(),'Rules & Regulation')]");
    public Link lnkTermConditions = Link.xpath("//div[contains(@class,'links')]//a[contains(text(),'Terms & Conditions')]");
    public Link lnkGamblingCanBeAddictive = Link.xpath("//div[contains(@class,'links')]//a[contains(text(),'Gambling')]");
   // public Link lnkContactUs = Link.xpath("//div[@id='mod-footer']//a[@data-gal='contact']");
    public Image imgMail= Image.xpath("//span[@class='icon icon-mail']");
    public Image imgTele = Image.xpath("//span[@class='icon icon-telegraph']");
    public Image imgPhone = Image.xpath("//span[@class='icon icon-tel']");
    public Image imgFacebook = Image.xpath("//span[@class='icon icon-facebook']");
    public Image imgInstagram = Image.xpath("//span[@class='icon icon-instagram']");
    public Image imgYoutube = Image.xpath("//span[@class='icon icon-youtube']");
    public Image imgBetFair = Image.xpath("//footer//a[@class='p-0']//img");
    public Label lblMail = Label.xpath("//a[@title='mail']/span[@class='text']");
    public Label lblPhoneNumber = Label.xpath("(//a[@title='whatsapp']/span[@class='text'])[1]");
    public Label LblPhoneNumber2 = Label.xpath("(//a[@title='whatsapp']/span[@class='text'])[2]");
    public Label lblTeleGram = Label.xpath("//a[@title='telegram']/span[@class='text']");
    public Label lblInstagram = Label.xpath("//a[@title='instagram']/span[@class='text']");
    public Label lblFacebook = Label.xpath("//a[@title='facebook']/span[@class='text']");
    public Label lblYoutube = Label.xpath("//a[@title='youtube']/span[@class='text']");


    public PrivacyPolicyPage navigatePrivacyPolicyPage () {
        lnkPrivacyPolicy.click();
        DriverManager.getDriver().switchToWindow();
        PrivacyPolicyPage page = new PrivacyPolicyPage();
        page.iconLogo.waitForElementToBePresent(page.iconLogo.getLocator(),5);
        return page;
    }

    public AboutPage navigateAboutPage () {
        lnkAbout.click();
        DriverManager.getDriver().switchToWindow();
        AboutPage page = new AboutPage();
        page.iconLogo.waitForElementToBePresent(page.iconLogo.getLocator(),5);
        return page;
    }

    public RuleRegulationPage navigateRuleRegulationPage () {
        lnkRuleRegulation.click();
        DriverManager.getDriver().switchToWindow();
        RuleRegulationPage page = new RuleRegulationPage();
        page.iconLogo.waitForElementToBePresent(page.iconLogo.getLocator(),5);
        return page;
    }

    public TermConditionPage navigateTermConditionPage () {
        lnkTermConditions.click();
        DriverManager.getDriver().switchToWindow();
        TermConditionPage page = new TermConditionPage();
        page.iconLogo.waitForElementToBePresent(page.iconLogo.getLocator(),5);
        return page;
    }




}
