package membersite.testcases.all.beforelogin;

import common.MemberConstants;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import membersite.pages.all.footer.AboutPage;
import membersite.pages.all.footer.PrivacyPolicyPage;
import membersite.pages.all.footer.RuleRegulationPage;
import membersite.pages.all.footer.TermConditionPage;
import baseTest.BaseCaseMerito;

public class FooterTest extends BaseCaseMerito {
    /**
     * @title: Validate that there is no http request error when clicking About page
     * @pre-condition
     *           1. Access SAT before Login page
     * @steps:   1. Navigate to About page on Footer
     * @expect:  1. There is no http request error
     */
    @Test (groups = {"http_request"})
    public void FE_Footer_001(){
        log("@title: Validate that there is no http request error when clicking About page");
        log("Pre-condition: Access SAT before Login page");
        log("Step 1: Navigate to About page on Footer");
        AboutPage page = memberHomePage.navigateAboutPage();

        boolean isError = hasHTTPRespondedOK();
        log("Verify: There is no http requests error");
        Assert.assertTrue(isError, "ERROR: There are some response request error returned");
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can navigate About page
     * @pre-condition
     *           1. Access SAT before Login page
     * @steps:   1. Navigate to About page
     * @expect:  1. Logo is displayed
     *           2. About title is displayed
     */
    @Test (groups = {"regression"})
    public void FE_Footer_002(){
        log("@title: Validate that user can navigate About page");
        log("Pre-condition: Access SAT before Login page");
        log("Step 1: Navigate to About page");
        AboutPage page = memberHomePage.navigateAboutPage();

        String title = page.lblTitle.getText();
        log("Verify 1: Logo is displayed");
        log("Verify 2: About title is displayed");
        Assert.assertTrue(page.iconLogo.isDisplayed(), "ERROR: iconLogo is not displayed");
        Assert.assertEquals(title, MemberConstants.Footer.TITLE_ABOUT, String.format("ERROR: The expected title is '%s' but found '%s'", MemberConstants.Footer.TITLE_ABOUT, title));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can navigate Privacy Policy page
     * @pre-condition
     *           1. Access SAT before Login page
     * @steps:   1. Navigate to Privacy Policy page
     * @expect:  1. Logo is displayed
     *           2. Privacy Policy title is displayed
     */
    @Test (groups = {"regression"})
    public void FE_Footer_003(){
        log("@title: Validate that user can navigate Privacy Policy page");
        log("Pre-condition: Access SAT before Login page");
        log("Step 1: Navigate to Privacy Policy page");
        PrivacyPolicyPage page = memberHomePage.navigatePrivacyPolicyPage();

        log("Verify 1: Logo is displayed");
        log("Verify 2: Privacy Policy title is displayed");
        Assert.assertTrue(page.iconLogo.isDisplayed(), "ERROR: iconLogo is not displayed");
        page.lblTitle.isTextDisplayed(MemberConstants.Footer.TITLE_PRIVACY_POLICY,3);
        String title = page.lblTitle.getText();
        Assert.assertEquals(title, MemberConstants.Footer.TITLE_PRIVACY_POLICY, String.format("ERROR: The expected title is '%s' but found '%s'", MemberConstants.Footer.TITLE_PRIVACY_POLICY, title));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can navigate Terms & Conditions page
     * @pre-condition
     *           1. Access SAT before Login page
     * @steps:   1. Navigate to Term & Condition page
     * @expect:  1. Logo is displayed
     *           2. Terms & Conditions title is displayed
     */
    @Test (groups = {"regression"})
    public void FE_Footer_004(){
        log("@title: Validate that user can navigate Terms & Conditions page");
        log("Pre-condition: Access SAT before Login page");
        log("Step 1: Navigate to Terms & Conditions page");
        TermConditionPage page = memberHomePage.navigateTermConditionPage();

        page.lblTitle.isTextDisplayed(MemberConstants.Footer.TITLE_TERM_CONDITION,3);
        String title = page.lblTitle.getText();
        log("Verify 1: Logo is displayed");
        log("Verify 2: Terms & Conditions title is displayed");
        Assert.assertTrue(page.iconLogo.isDisplayed(), "ERROR: iconLogo is not displayed");
        Assert.assertEquals(title, MemberConstants.Footer.TITLE_TERM_CONDITION, String.format("ERROR: The expected title is '%s' but found '%s'", MemberConstants.Footer.TITLE_TERM_CONDITION, title));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate that user can navigate Rules & Regulations page
     * @pre-condition
     *           1. Access SAT before Login page
     * @steps:   1. Navigate to Rules & Regulations page
     * @expect:  1. Logo is displayed
     *           2. Rules & Regulations title is displayed
     */
    @Test (groups = {"regression"})
    public void FE_Footer_005(){
        log("@title: Validate that user can navigate Term & Condition page");
        log("Pre-condition:  1. Access SAT before Login page");
        log("Step 1: Navigate to Rules & Regulations page");
        RuleRegulationPage page = memberHomePage.navigateRuleRegulationPage();

        page.lblTitle.isTextDisplayed(MemberConstants.Footer.TITLE_RULE_REGULATION,3);
        String title = page.lblTitle.getText();
        log("Verify 1: Logo is displayed");
        log("Verify 2: Rules & Regulations title is displayed");
        Assert.assertTrue(page.iconLogo.isDisplayed(), "ERROR: iconLogo is not displayed");
        Assert.assertEquals(title, MemberConstants.Footer.TITLE_RULE_REGULATION, String.format("ERROR: The expected title is '%s' but found '%s'", MemberConstants.Footer.TITLE_RULE_REGULATION, title));
        log("INFO: Executed completely");
    }

    /**
     * @title: Validate UI in Footer Section
     * @pre-condition
     *           1. Access SAT before Login page
     * @steps:   1. Observe Footer Section
     * @expect: 1. Footer include:
     *          - Privacy Policy
     *          - About UsRules & Regulations
     *          - Terms & Conditions
     *          - Gambling can be addictive, please play responsibly.
     *          - Contact icon
     *          - mail icon and label info@satsport.com
     *          - skype icon and lable: sat123
     *          - phone icon and phone number: +447448904678
     *          - Powered by betfair logo
     */
    @Test (groups = {"smoke"})
    public void FE_Footer_006(){
        log("@title:  Validate UI in Footer Section");
        log("Step 1:  Observe Footer Section");
        log("Verify 1: 1. Footer include: \n" +
                "- Privacy Policy\n" +
                "- About UsRules & Regulations\n" +
                "- Terms & Conditions\n" +
                "- Gambling can be addictive, please play responsibly.\n" +
                "- Contact icon\n" +
                "- mail icon and label info@satsport.com\n" +
                "- skype icon and lable: sat123\n" +
                "- phone icon and phone number: +447448904678\n" +
                "- Powered by betfair logo");
       landingPage.lblFooter.scrollToThisControl(false);
        Assert.assertEquals(landingPage.lnkPrivacyPolicy.getText(), MemberConstants.Footer.TITLE_PRIVACY_POLICY,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.TITLE_PRIVACY_POLICY,landingPage.lnkPrivacyPolicy.getText()));
        Assert.assertEquals(landingPage.lnkAbout.getText(), MemberConstants.Footer.TITLE_ABOUT,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.TITLE_ABOUT,landingPage.lnkAbout.getText()));
        Assert.assertEquals(landingPage.lnkRuleRegulation.getText(), MemberConstants.Footer.TITLE_RULE_REGULATION,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.TITLE_RULE_REGULATION,landingPage.lnkRuleRegulation.getText()));
        Assert.assertEquals(landingPage.lnkTermConditions.getText(), MemberConstants.Footer.TITLE_TERM_CONDITION,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.TITLE_TERM_CONDITION,landingPage.lnkTermConditions.getText()));
        Assert.assertEquals(landingPage.lnkGamblingCanBeAddictive.getText(), MemberConstants.Footer.TITLE_GAMBLING,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.TITLE_GAMBLING,landingPage.lnkGamblingCanBeAddictive.getText()));

        log("INFO: Executed completely");
    }
    @Test (groups = {"sat_smoke1"})
    @Parameters("skinName")
    public void FE_Footer_007(String skinName){
        log("@title:  Validate UI in Footer Section");
        log("Step 1:  Observe Footer Section");
        log("Verify 1: Footer include: "+
                "- Contact icon\n" +
                "- mail icon and label info@satsport.com\n" +
                "- skype icon and lable: sat123\n" +
                "- phone icon and phone number: +447448904678\n" +
                "- Powered by betfair logo");
        String logoBFURL = landingPage.imgBetFair.getAttribute("src");
        String iconMailUrl = landingPage.imgMail.getColour("background");
        String iconTeleUrl = landingPage.imgTele.getColour("background");
        String iconPhoneUrl = landingPage.imgPhone.getColour("background");
        String iconInstagram = landingPage.imgInstagram.getColour("background");
        String iconFacebook = landingPage.imgFacebook.getColour("background");
        String iconYoutube = landingPage.imgYoutube.getColour("background");

        String expectedMailURL = MemberConstants.Footer.IMG_MAIL_URL;
        Assert.assertTrue(iconMailUrl.contains(expectedMailURL),String.format("ERROR: Expected %s but found %s",expectedMailURL, iconMailUrl));
        Assert.assertEquals(landingPage.lblMail.getText(), MemberConstants.Footer.CONTACT_EMAIL,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_EMAIL, landingPage.lblMail.getText()));
        Assert.assertTrue(iconTeleUrl.contains(MemberConstants.Footer.IMG_TELE_URL),String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.IMG_TELE_URL,iconTeleUrl));
        Assert.assertEquals(landingPage.lblTeleGram.getText(), MemberConstants.Footer.CONTACT_TELE,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_TELE, landingPage.lblTeleGram.getText()));
        Assert.assertTrue(iconInstagram.contains(MemberConstants.Footer.IMG_INSTAGRAM_URL),String.format("ERROR: Expected Instagram image is incorrect, actual is %s",iconInstagram));
        Assert.assertEquals(landingPage.lblInstagram.getText(), MemberConstants.Footer.CONTACT_INSTA,String.format("ERROR: Contact Instagram incorrect %s but found %s", MemberConstants.Footer.CONTACT_INSTA, landingPage.lblInstagram.getText()));
        Assert.assertTrue(iconFacebook.contains(MemberConstants.Footer.IMG_FACEBOOK_URL),String.format("ERROR: Expected Facebook image is incorrect, actual is %s ",iconFacebook));
        Assert.assertEquals(landingPage.lblFacebook.getText(), MemberConstants.Footer.CONTACT_INSTA,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_INSTA, landingPage.lblFacebook.getText()));
        Assert.assertTrue(iconYoutube.contains(MemberConstants.Footer.IMG_YOUTUBE_URL),String.format("ERROR: Expected Youtube image image is incorrect, actual is %s",iconYoutube));
        Assert.assertEquals(landingPage.lblYoutube.getText(), MemberConstants.Footer.CONTACT_INSTA,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_INSTA, landingPage.lblYoutube.getText()));

        Assert.assertTrue(iconPhoneUrl.contains(MemberConstants.Footer.IMG_PHONE_URL),String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.IMG_PHONE_URL, iconPhoneUrl));
        Assert.assertEquals(landingPage.lblPhoneNumber.getText(), MemberConstants.Footer.CONTACT_PHONE_NUM,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_PHONE_NUM, landingPage.lblPhoneNumber.getText()));
        Assert.assertEquals(landingPage.LblPhoneNumber2.getText(), MemberConstants.Footer.CONTACT_PHONE_NUM2,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_PHONE_NUM2, landingPage.LblPhoneNumber2.getText()));
        Assert.assertTrue(logoBFURL.contains(MemberConstants.Footer.IMG_BETFAIR_URL),String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.IMG_BETFAIR_URL,logoBFURL));
    }
    @Test (groups = {"jio_smoke"})
    @Parameters("skinName")
    public void FE_Footer_008(String skinName){
        log("@title:  Validate UI in Footer Section");
        log("Step 1:  Observe Footer Section");
        log("Verify 1: Footer include: "+
                "- mail icon and label info@jio.exchange\n\n" +
                "- skype icon and lable: sat123\n" +
                "- phone icon and phone number: +447448904678\n" +
                "- Powered by betfair logo");
        String logoBFURL = memberHomePage.imgBetFair.getAttribute("src");
        String iconMailUrl = memberHomePage.imgMail.getColour("background");
        String iconTeleUrl = memberHomePage.imgTele.getColour("background");
        String iconPhoneUrl = memberHomePage.imgPhone.getColour("background");
        String iconInstagram = memberHomePage.imgInstagram.getColour("background");
        String iconFacebook = memberHomePage.imgFacebook.getColour("background");
        String iconYoutube = memberHomePage.imgYoutube.getColour("background");

        String expectedTelelURL = String.format(MemberConstants.Footer.IMG_TELE_URL,domainURL,skinName);
        String expectedPhoneURL = String.format(MemberConstants.Footer.IMG_PHONE_URL,domainURL,skinName);
        String expectedBFLogoURL = String.format(MemberConstants.Footer.IMG_BETFAIR_URL,domainURL,skinName);
        String expectedMailURL = String.format(MemberConstants.Footer.IMG_MAIL_URL,domainURL,skinName);
        String expectedFacebookImgURL = String.format(MemberConstants.Footer.IMG_FACEBOOK_URL,domainURL,skinName);
        String expectedInstagramImgURL = String.format(MemberConstants.Footer.IMG_INSTAGRAM_URL,domainURL,skinName);
        String expectedYoutubeImgURL = String.format(MemberConstants.Footer.IMG_YOUTUBE_URL,domainURL,skinName);


        Assert.assertTrue(iconMailUrl.contains(expectedMailURL),String.format("ERROR: Expected %s but found %s",expectedMailURL, iconMailUrl));
        Assert.assertEquals(memberHomePage.lblMail.getText(), MemberConstants.Footer.JIO_CONTACT_EMAIL,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.JIO_CONTACT_EMAIL, memberHomePage.lblMail.getText()));
        Assert.assertTrue(iconTeleUrl.contains(expectedTelelURL),String.format("ERROR: Expected %s but found %s",expectedTelelURL,iconTeleUrl));
        Assert.assertEquals(memberHomePage.lblTeleGram.getText(), MemberConstants.Footer.CONTACT_TELE,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_TELE, memberHomePage.lblTeleGram.getText()));
        Assert.assertTrue(iconInstagram.contains(expectedInstagramImgURL),String.format("ERROR: Expected Instagram image is %s but found %s",expectedInstagramImgURL,iconInstagram));
        Assert.assertEquals(memberHomePage.lblInstagram.getText(), MemberConstants.Footer.CONTACT_TELE,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_TELE, memberHomePage.lblInstagram.getText()));
        Assert.assertTrue(iconFacebook.contains(expectedFacebookImgURL),String.format("ERROR: Expected Facebook image is %s but found %s",expectedFacebookImgURL,iconFacebook));
        Assert.assertEquals(memberHomePage.lblFacebook.getText(), MemberConstants.Footer.CONTACT_TELE,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_TELE, memberHomePage.lblFacebook.getText()));
        Assert.assertTrue(iconYoutube.contains(expectedYoutubeImgURL),String.format("ERROR: Expected Youtube image is %s but found %s",expectedYoutubeImgURL,iconYoutube));
        Assert.assertEquals(memberHomePage.lblYoutube.getText(), MemberConstants.Footer.CONTACT_TELE,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_TELE, memberHomePage.lblYoutube.getText()));

        Assert.assertTrue(iconPhoneUrl.contains(expectedPhoneURL),String.format("ERROR: Expected %s but found %s",expectedPhoneURL, iconPhoneUrl));
        Assert.assertEquals(memberHomePage.lblPhoneNumber.getText(), MemberConstants.Footer.CONTACT_PHONE_NUM,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_PHONE_NUM, memberHomePage.lblPhoneNumber.getText()));
        Assert.assertEquals(memberHomePage.LblPhoneNumber2.getText(), MemberConstants.Footer.CONTACT_PHONE_NUM2,String.format("ERROR: Expected %s but found %s", MemberConstants.Footer.CONTACT_PHONE_NUM2, memberHomePage.LblPhoneNumber2.getText()));
        Assert.assertEquals(logoBFURL,expectedBFLogoURL,String.format("ERROR: Expected %s but found %s",expectedBFLogoURL,logoBFURL));
    }

    @Test (groups = {"whitelabels"})
    @Parameters("skinName")
    public void FE_Footer_009(String skinName){
        log("@title:  Validate UI in Footer Section");
        log("Step 1:  Observe Footer Section");
        log("Verify 1: Footer NOT display: "+
                "- Facebook, instagram, youtube and \n" +
                "- mail icon and label info@satsport.com\n" +
                "- skype icon and lable: sat123\n" +
                "- phone icon and phone number: +447448904678\n" +
                "- Powered by betfair logo");

        Assert.assertTrue(landingPage.imgBetFair.isDisplayed(),"FAILED! White labels also display BF logo in footer");
        Assert.assertFalse(landingPage.imgYoutube.isDisplayed(),"White labels not display Youtube icon in footer");
        Assert.assertFalse(landingPage.imgFacebook.isDisplayed(),"White labels not display Facebook icon in footer");
        Assert.assertFalse(landingPage.imgInstagram.isDisplayed(),"White labels not display Instagram icon in footer");
        Assert.assertFalse(landingPage.imgMail.isDisplayed(),"White labels not display Mail icon in footer");
        Assert.assertFalse(landingPage.imgPhone.isDisplayed(),"White labels not display Phone icon in footer");
        Assert.assertFalse(landingPage.imgTele.isDisplayed(),"White labels not display Tele icon in footer");

        Assert.assertFalse(landingPage.lblYoutube.isDisplayed(),"White labels not display Youtube icon in footer");
        Assert.assertFalse(landingPage.lblFacebook.isDisplayed(),"White labels not display Facebook icon in footer\");\n" +
                "        Assert.assertFalse(landingPage.lblInstagram.isDisplayed(),\"White labels not display Instagram icon in footer\");\n" +
                "        Assert.assertFalse(landingPage.lblMail.isDisplayed(),\"White labels not ldisplay Mai icon in footer\");\n" +
                "        Assert.assertFalse(landingPage.lblPhoneNumber.isDisplayed(),\"White labels not display Phone icon in footer");
        Assert.assertFalse(landingPage.lblTeleGram.isDisplayed(),"White labels not display Tele icon in footer");
    }

}

