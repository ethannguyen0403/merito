package backoffice.pages.bo.marketmanagement.components;

import com.paltech.element.common.Button;
import com.paltech.element.common.DropDownBox;
import com.paltech.element.common.Label;
import com.paltech.element.common.TextBox;

public class BeforeLoginManagementPopup {
    public DropDownBox ddpSubMenu = DropDownBox.xpath("//p[text()='Sub-Menu']//following-sibling::select");
    private TextBox txtMenuName = TextBox.xpath("//p[text()='Menu Name']//following-sibling::input");
    private TextBox txtSequence = TextBox.xpath("//p[text()='Sequence']//following-sibling::input");
    private TextBox txtMenuLink = TextBox.xpath("//p[text()='Menu Link']//following-sibling::div//input");
    private DropDownBox ddpMenuStatus = DropDownBox.xpath("//p[text()='Menu Status']//following-sibling::select");
    private TextBox txtSubMenu1Name = TextBox.xpath("//p[text()='Sub-Menu 1 Name: ']//following-sibling::input");
    private TextBox txtSubMenu1Link = TextBox.xpath("//p[text()='Sub-Menu 1 Link: ']//following-sibling::input");
    private TextBox txtSubMenu1Sequence = TextBox.xpath("//p[text()='Sub-Menu 1 Sequence: ']//following-sibling::input");
    private DropDownBox ddpSubMenu1Status = DropDownBox.xpath("//p[text()='Sub-Menu 1 Status: ']//following-sibling::select");
    private TextBox txtMenuImage1 = TextBox.xpath("//p[text()='Sub-Menu 1']//..//following-sibling::div//p[text()='Menu Image']//following-sibling::div//input");
    private TextBox txtSubMenu2Name = TextBox.xpath("//p[text()='Sub-Menu 2 Name: ']//following-sibling::input");
    private TextBox txtSubMenu2Link = TextBox.xpath("//p[text()='Sub-Menu 2 Link: ']//following-sibling::input");
    private TextBox txtSubMenu2Sequence = TextBox.xpath("//p[text()='Sub-Menu 2 Sequence: ']//following-sibling::input");
    private DropDownBox ddpSubMenu2Status = DropDownBox.xpath("//p[text()='Sub-Menu 2 Status: ']//following-sibling::select");
    private TextBox txtMenuImage2 = TextBox.xpath("//p[text()='Sub-Menu 2']//..//following-sibling::div//p[text()='Menu Image']//following-sibling::div//input");
    private Button btnSaveCreateMenu = Button.xpath("//div[@class='row justify-content-end p-3']//button[text()='Save']");
    private Button btnCloseCreateMenu = Button.xpath("//div[@class='row justify-content-end p-3']//span[text()='Close']");
    private Label lblAddMoreSubMenu = Label.xpath("//p[text()='Add more Sub-menu']");

    public boolean isUICreateMenuDisplayCorrect(boolean isSubMenu) {
        if (!isSubMenu) {
            try {
                txtMenuName.isDisplayed();
                txtSequence.isDisplayed();
                txtMenuLink.isDisplayed();
                ddpMenuStatus.isDisplayed();
                ddpSubMenu.isDisplayed();
                btnSaveCreateMenu.isDisplayed();
                btnCloseCreateMenu.isDisplayed();
                return true;

            } catch (Exception e) {
                System.out.println("Control displays missing on page " + e.getMessage());
                return false;
            }
        } else {
            try {
                txtMenuName.isDisplayed();
                txtSequence.isDisplayed();
                ddpMenuStatus.isDisplayed();
                ddpSubMenu.isDisplayed();
                txtSubMenu1Name.isDisplayed();
                txtSubMenu1Link.isDisplayed();
                txtSubMenu1Sequence.isDisplayed();
                ddpSubMenu1Status.isDisplayed();
                txtMenuImage1.isDisplayed();
                txtSubMenu2Name.isDisplayed();
                txtSubMenu2Link.isDisplayed();
                txtSubMenu2Sequence.isDisplayed();
                ddpSubMenu2Status.isDisplayed();
                txtMenuImage2.isDisplayed();
                lblAddMoreSubMenu.isDisplayed();
                btnSaveCreateMenu.isDisplayed();
                btnCloseCreateMenu.isDisplayed();
                return true;

            } catch (Exception e) {
                System.out.println("Control displays missing on page " + e.getMessage());
                return false;
            }
        }

    }
}
