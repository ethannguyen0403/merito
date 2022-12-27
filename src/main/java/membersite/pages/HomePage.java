package membersite.pages;

import membersite.pages.popup.BannerPopup;

public class HomePage extends LandingPage{
    BannerPopup bannerPopup = BannerPopup.xpath("//div[@class='main-banner-popup-content']");

    public HomePage(String types) {
        super(types);
    }

    public void closeBannerPopup(){
        if(bannerPopup.isDisplayed()){
            System.out.println("---Step Close banner popup");
            bannerPopup.closeBannerPopup();
        }
    }
    public boolean isMyAccountDisplay(){
       return header.isMyAccountDisplay();
    }
    public boolean isMyAccountContains(String menu){
        return header.isMyAccountContains(menu);
    }



}
