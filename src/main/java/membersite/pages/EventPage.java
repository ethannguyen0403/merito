package membersite.pages;


import com.paltech.element.common.Label;

public class EventPage extends HomePage {
   public Label lblTitleEvent = Label.xpath("//div[@class='market-info']//div[contains(@class, 'titles')]");

    public EventPage(String types) {
        super(types);
        lblTitleEvent.isDisplayed();
    }
}
