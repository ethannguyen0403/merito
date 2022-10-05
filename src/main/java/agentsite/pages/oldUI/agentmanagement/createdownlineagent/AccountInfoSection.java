package agentsite.pages.oldUI.agentmanagement.createdownlineagent;

import com.paltech.element.BaseElement;
import com.paltech.element.common.*;
import agentsite.controls.Table;
import org.openqa.selenium.By;

import java.util.List;

public class AccountInfoSection extends BaseElement {
    private String _xPath ="//div[@id='account']//app-agency-account-ui";
    Table tblInfoAccountLabel = Table.xpath("//table[@class='ptable info account-table']",4);
    //tr[2]//td[3]
    String listLable = String.format("%s//div[contains(@class,'label')]",_xPath );
    String ddpUsernameCharXPath = String.format("//select[@name='userNameChar']");
    String  lblUsernameCharXpath = String.format("%s//td[contains(@class,'data')][1]/span/span",_xPath);
    public Label lblUsernamePrefix = Label.xpath(String.format("%s//span[@id='username-prefix']",_xPath));

    //public DropDownBox ddpUsernameChar = DropDownBox.xpath(String.format("%s//div[contains(@class,'column data')][1]/span/span",_xPath));
   // public Label lblUsernameChar = Label.xpath(String.format("%s//div[contains(@class,'column data')][1]/span/span",_xPath));
    public DropDownBox ddpUsernamesecondChar = DropDownBox.xpath(String.format("%s//div[contains(@class,'column data')][1]/span/span[2]//select[1]",_xPath));
    public Icon iconUserCodStatus = Icon.xpath(String.format("%s//input[@name='usercode-status']",_xPath));

    public TextBox txtPassword =TextBox.xpath(String.format("%s//input[@name='password']",_xPath));
    public DropDownBox ddrAccountStatus = DropDownBox.xpath(String.format("%s//select[@name='status']",_xPath));
    public TextBox txtFirstName = TextBox.xpath(String.format("%s//input[@name='firstName']",_xPath));
    public TextBox txtLastName = TextBox.xpath(String.format("%s//input[@name='lastName']",_xPath));
    public TextBox txtPhone = TextBox.xpath(String.format("%s//input[@name='phone']",_xPath));
    public TextBox txtMobile = TextBox.xpath(String.format("%s//input[@name='mobile']",_xPath));
    public DropDownBox ddpLevel =DropDownBox.xpath(String.format("%s//td[text()='Level']/following::select[1]",_xPath));
    public Label lblPasswordHint = Label.xpath(String.format("%s//span[@class='pinfo']",_xPath));
    public TextBox txtFax = TextBox.xpath(String.format("%s//input[@name='fax']",_xPath));
    public Label lblBaseCurrencyValue = Label.xpath(String.format("%s//td[text()='Base Currency']/following::td[1]",_xPath));
    public CheckBox cbAllowExtraPT = CheckBox.xpath(String.format("%s//input[@name='allowAutoPT']",_xPath));

    public AccountInfoSection(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
    }

    public static AccountInfoSection xpath(String xpathExpression) {
        return new AccountInfoSection(By.xpath(xpathExpression), xpathExpression);
    }
    public List<String> getListLabelInfo(){
        List<String> lstInfo = tblInfoAccountLabel.getColumn(1,false);
        lstInfo.addAll(tblInfoAccountLabel.getColumn(3,false));
        return lstInfo;
    }

    public String getUserName(){
        String username = lblUsernamePrefix.getText();
        Label lblUsernameChar = Label.xpath(lblUsernameCharXpath);
        DropDownBox ddpUsernameChar;
        int total = lblUsernameChar.getWebElements().size();
        if(total!=0){
            for(int i=0; i<total;i++){
                ddpUsernameChar = DropDownBox.xpath(String.format("%s[%s]%s",lblUsernameCharXpath,i+1,ddpUsernameCharXPath));
                if(ddpUsernameChar.isDisplayed()){
                    username = username + ddpUsernameChar.getFirstSelectedOption().trim();
                }
            }
            return username;
        }
        System.out.println("There is no Username dropdown display");
        return null;
    }

    public void selectUserName(String userName,String prefix){
        String bb = userName.split(prefix)[1];
        bb.split("0")   ;
    }

    public void inputInfo(String password, String accountStatus, String firstName, String lastName, String phone, String mobile, String fax)
    {
        if(!password.isEmpty()){
            txtPassword.sendKeys(password);
        }
        if(!accountStatus.isEmpty())
            ddrAccountStatus.selectByVisibleText(accountStatus);
        if(!firstName.isEmpty())
            txtFirstName.sendKeys(firstName);
        if(!phone.isEmpty())
            txtPhone.sendKeys(phone);
        if(!lastName.isEmpty())
            txtLastName.sendKeys(lastName);
        if(!mobile.isEmpty())
            txtMobile.sendKeys(mobile);
        if(!fax.isEmpty())
            txtFax.sendKeys(fax);
    }


}
