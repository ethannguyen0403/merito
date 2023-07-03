package backoffice.pages.bo.operations.component;

import backoffice.controls.DateTimePicker;
import com.paltech.element.common.*;

import java.util.List;

public class NewBannerPopup {
    public Popup popupNewBanner = Popup.xpath("//div[contains(@class,'modal-content dialog-page')]");
    public Label lblTitle = Label.xpath("//div[contains(@class,'modal-content dialog-page')]//h4");
    public DropDownBox ddbStatus = DropDownBox.xpath("//div[contains(@class,'modal-body')]//div[4]//select");
    public DropDownBox ddbSequence = DropDownBox.xpath("//div[contains(@class,'modal-body')]//div[6]//select");
    public TextBox txtImageLink = TextBox.xpath("//div[contains(@class,'modal-body')]//div[2]//input");
    public TextBox txtUploadImage = TextBox.xpath("//div[contains(@class,'modal-body')]//input[@type='file']");
    public TextBox txtValidFrom = TextBox.xpath("//div[contains(@class,'modal-body')]//input[@name='fromDate']");
    public TextBox txtValidTo = TextBox.xpath("//div[contains(@class,'modal-body')]//input[@name='toDate']");
    public DateTimePicker dbValidForm = DateTimePicker.xpath(txtValidFrom, "//bs-days-calendar-view/bs-calendar-layout");
    public DateTimePicker dbValidTo = DateTimePicker.xpath(txtValidTo, "//bs-days-calendar-view/bs-calendar-layout");
    public CheckBox cbSet = CheckBox.xpath("//span[contains(text(),'Set')]/input");
    public String cbBrandsXPath = "//label[contains(text(),'%s')]/input";
    private Icon iconX = Icon.xpath("//button[@class='close close-button']/span");
    private Button btnSave = Button.xpath("//div[contains(@class, 'modal-footer')]//button[@class='btn btn-sm btn-core']");
    private Button btnClose = Button.xpath("//div[contains(@class,'modal-footer')]/button[@data-dismiss='modal']");

    public void submitNewBannerInfo(String file, List<String> lstBrands, String status, String sequence, String validFrom, String validTo) throws InterruptedException {
        if (!file.isEmpty()) {
            uploadFile(file);
        }
        if (!status.isEmpty()) {
            ddbStatus.selectByVisibleText(status);
        }
        selectBrands(lstBrands);
        if (!sequence.isEmpty()) {
            ddbSequence.selectByVisibleText(sequence);
        }
        if (!validFrom.isEmpty()) {
            txtValidFrom.sendKeys(validFrom);
        }
        if (!validTo.isEmpty()) {
            String currentValue = txtValidTo.getAttribute("value");
            if (!currentValue.equalsIgnoreCase(validTo)) {
                txtValidTo.sendKeys(validTo);
            }
        } else
            cbSet.click();
        btnSave.click();
        // waiting for loading completely
        btnSave.isDisplayedShort(2);
    }

    private void selectBrands(List<String> lstBrand) {
        CheckBox cbBrand;
        for (String brand : lstBrand) {
            cbBrand = CheckBox.xpath(String.format(cbBrandsXPath, brand));
            cbBrand.click();
        }
    }

    private void uploadFile(String file) throws InterruptedException {
        String workingDir = System.getProperty("user.dir");
        String filePath = workingDir + "\\drivers\\" + file;
        txtUploadImage.sendKeys(filePath);
        // DriverManager.getDriver().get(filePath);
        // WebElement fileInput = DriverManager.getDriver().findElement(By.xpath("//input[@type='file']"));
        // fileInput.sendKeys(filePath);
        Thread.sleep(1000);
    }

    public void clickCloseBtn() {
        btnClose.click();
    }

    public void clickXIcon() {
        iconX.click();
    }
}
