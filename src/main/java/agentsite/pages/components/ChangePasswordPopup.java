package agentsite.pages.components;

import agentsite.controls.Table;
import com.paltech.element.BaseElement;
import com.paltech.element.common.Button;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;
import org.openqa.selenium.By;

import java.util.List;

public class ChangePasswordPopup extends BaseElement {
     private String _xPath;
     public TextBox txtCurrentPassword ;
     public TextBox txtNewPassword;
     public TextBox txtConfirmPassword ;
     public Button btnSubmit ;
    public Button btnCancle ;
     public Label lblTitle;
     public Button btnClose;
     public Table tblForm;

    public ChangePasswordPopup(By locator, String xpathExpression) {
        super(locator);
        this._xPath = xpathExpression;
        lblTitle = Label.xpath(String.format("%s//div[@class='modal-header']",this._xPath));
        btnClose = Button.xpath(String.format("%s//div[@class='modal-header']//button[@class='close']",this._xPath));
        txtCurrentPassword = TextBox.xpath(String.format("%s//input[@id='oldPassword'or @name='password']",this._xPath));
        txtNewPassword = TextBox.xpath(String.format("%s//input[@id='newPassword'or @name='newPassword']",this._xPath));
        txtConfirmPassword = TextBox.xpath(String.format("%s//input[@id='confirmPassword' or @name='confirmPassword']",this._xPath));
        btnSubmit = Button.xpath(String.format("%s//button[@class='pbtn']",this._xPath));
        btnCancle = Button.xpath(String.format("%s//button[@class='cancel pbtn']",this._xPath));
        tblForm = Table.xpath(String.format("%s//div[@class='modal-body']//table",this._xPath),2);
    }

    public static ChangePasswordPopup xpath(String xpathExpression) {
        return new ChangePasswordPopup(By.xpath(xpathExpression), xpathExpression);
    }

    public static Table xpath(String xpathExpression, String url, int columnNumber, String header, String json) {
        return new Table(By.xpath(xpathExpression), xpathExpression, url, columnNumber, header, json);
    }
       public void changePassword(String oldPassword, String newPassword, String confirmPassword){
        txtCurrentPassword.isDisplayedShort(3);
        txtCurrentPassword.isInvisible(2);
        if(!oldPassword.isEmpty())
            txtCurrentPassword.sendKeys(oldPassword);
        if (!newPassword.isEmpty())
            txtNewPassword.sendKeys(newPassword);
        if(!confirmPassword.isEmpty())
            txtConfirmPassword.sendKeys(confirmPassword);
        btnSubmit.click();
    }


    public List<String> getLabels(){
        return tblForm.getColumn(1,false);
    }

}
