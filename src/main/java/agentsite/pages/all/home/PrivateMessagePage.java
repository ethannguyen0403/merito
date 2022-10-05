package agentsite.pages.all.home;

import com.paltech.element.common.Button;
import com.paltech.element.common.TextBox;
import agentsite.controls.Table;
import agentsite.pages.all.components.BasePage;

import java.util.ArrayList;
import java.util.List;

public class PrivateMessagePage extends BasePage {
    public TextBox txtFrom = TextBox.id("formDate");
    public TextBox txtTo= TextBox.id("toDate");
    public Button btnToday = Button.name("today");
    public Button btnYesterday = Button.name("yesterday");
    public Button btnLastBusinessWeek = Button.name("lastWeek");
    public Button btnSubmit = Button.name("search");
    public int colSubject = 2;
    public int colContent = 3;
    public int colCreatedDate =4;
    public Table tblPrivateMessage = Table.xpath("//table[@class='ptable report']",4);

    public boolean verifyMessage(List<String> message){
        List<ArrayList<String>> lstMessage = tblPrivateMessage.getRowsWithoutHeader(20,false);
        for(int i =1; i< lstMessage.size();i++){
            if(lstMessage.get(i).get(colSubject).equals(message.get(0))){
                System.out.println(String.format("The message with subject %5 is displayed",message.get(0)));
                if(lstMessage.get(i).get(colContent).equals(message.get(1)))
                {
                    System.err.println("Message Content is incorrect");
                    return false;
                }
                if(lstMessage.get(i).get(colCreatedDate).equals(message.get(2)))
                {
                    System.err.println("FAILED! Message Create date is incorrect");
                    return false;
                }
                return true;
            }
        }
        System.err.println("The message does not display int the list! pls recheck");
        return false;
    }

}
