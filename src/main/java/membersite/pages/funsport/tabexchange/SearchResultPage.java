//package membersite.pages.funsport.tabexchange;
//
//import com.paltech.element.common.Label;
//import com.paltech.element.common.Link;
//import membersite.pages.all.tabexchange.EventPage;
//import membersite.pages.all.tabexchange.MarketPage;
//import membersite.pages.all.tabexchange.components.MainMenu;
//
//public class SearchResultPage extends MainMenu {
//    public Label lblSearchResult = Label.xpath("//div[@class='mod-searchresults-ebs-header']//h3");
//    public Label lblResultContains = Label.xpath("//div[@class='mod-searchresults-ebs-content']");
//
//    public MarketPage clickEventResult(String event){
//        String xPath = "//div[@class='mod-searchresults-ebs-content']//ul/li";
//        int totalResult =Label.xpath(xPath).getWebElements().size();
//        Link lnkEvent;
//       for(int i =1; i<totalResult ;i++){
//           lnkEvent =Link.xpath(String.format("%s[%s]/a",xPath,i));
//           if(lnkEvent.getText().contains(event)) {
//               lnkEvent.click();
//               return new MarketPage();
//           }
//       }
//       System.out.println("There is no result match with search criteria");
//       return null;
//    }
//}
