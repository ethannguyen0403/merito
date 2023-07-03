package backoffice.pages.bo._components;


import com.paltech.driver.DriverManager;

public class BasePage {
    public void refresh() {
        DriverManager.getDriver().refresh();
    }
}
