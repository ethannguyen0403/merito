package backoffice.pages.bo.temp;

import com.paltech.element.common.*;
import backoffice.controls.DateTimePicker;
import backoffice.controls.Table;
import backoffice.pages.bo.home.HomePage;

import java.util.ArrayList;
import java.util.List;

public class PersonalMessagePage extends HomePage {
    public Link tabSendaMessage = Link.id("leaveMsgTab");
    public TextBox txtUsername= TextBox.id("userName");
    public TextBox txtSubject= TextBox.id("commentTitle");
    public TextBox txtMessage= TextBox.id("commentArea");
    public Button tbnSend= Button.id("btnSubmit");

    public Link tabViewingSentMessage = Link.id("listMsgTab");
    public TextBox txtFrom = TextBox.name("fromDate");
    public DateTimePicker dtpFrom = DateTimePicker.xpath(txtFrom,"//div[contains(@class,'datepicker-days')]");
    public TextBox txtTo = TextBox.name("toDate");
    public DateTimePicker dtpTo = DateTimePicker.xpath(txtTo,"//div[contains(@class,'datepicker-days')]");
    public Button btnView = Button.name("search");
    private int totalCol = 3;
    public int colContent = 1;
    public int colSendTo = 2;
    public int colDateTime = 3;
    public Table tblSentMessage = Table.xpath("//table[@class='lstmsg']",3);

    public void sendMessage(String username, String subject, String message){
        // select tab: Send a message
        tabSendaMessage.click();
        if(!username.isEmpty())
        {
            txtUsername.isDisplayed(3);
            txtUsername.sendKeys(username);
        }
        if(!subject.isEmpty()){
            txtSubject.sendKeys(subject);
        }
        if(!message.isEmpty()){
            txtMessage.sendKeys(message);
        }
        tbnSend.click();
    }

    public void activeViewingSentMessage()
    {
        tabViewingSentMessage.click();
    }
    public boolean veifyMessageSent(String message, String username){
        List<String> lstMsg = tblSentMessage.getColumn(colContent,false);
        for( int i =0 ;i< lstMsg.size(); i++)
        {
            if(lstMsg.get(i).equalsIgnoreCase(message)){
                String actualUsernmae = tblSentMessage.getControlOfCell(1,colSendTo,i+1,null).getText();
                if (!actualUsernmae.contains(username))
                {
                    System.err.println("Send to value does not match with the expected");
                    return false;
                }
                return true;
            }
        }
        System.out.println(String.format("The message %s dose not exist in the list", message));
        return false;
    }

    public List<String> getSentMessageInfo(String message){
        List<String> lstMsg = tblSentMessage.getColumn(colContent,false);
        List<String> data = new ArrayList<>();
        for( int i =0 ;i< lstMsg.size(); i++)
        {
            if(lstMsg.get(i).equalsIgnoreCase(message)){
                data.add(message);
                data.add(tblSentMessage.getControlOfCell(1,colSendTo,i+1,null).getText());
                data.add(tblSentMessage.getControlOfCell(1,colDateTime,i+1,null).getText());
                return data;
            }
        }
        System.out.println(String.format("The message %s dose not exist in the list", message));
        return null;
    }



}
