package agentsite.testcase.proteus;

import agentsite.pages.agentmanagement.CreateUserPage;
import baseTest.BaseCaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.AGConstant.PS38;

public class CreateUserTest extends BaseCaseTest {
    @TestRails(id = "4131")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4131() {
        log("@title: Validate PS38 commission section UI in Create User at CO level ");
        log("Precondition: Login agent at CO level that active PS38 product");
        log("Step 1: Click on Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());
        log("Step 2: Select PS38 product and observe Commission section");
        page.selectProduct(PS38);
        page.commissionSectionPS38.expandCollapseCommissionSection(true);

        log("Step 3: Verify UI display correctly");

        log("Verify 1: 1/ Odds Group dropdown: default group C and contains values: A, B, C, D, E\n" +
                "2/ Checkbox\n" +
                ":Apply soccer games setting to other commission types\" and uncheck by default\n" +
                "3/There are 5 types of commission: Soccer Games, Verify high commission, High commission, Normal commission, Parlays, Teasers\n" +
                "The message (* Your current commission group is C)\n" +
                "Sport and league dropdown with Soccer and General by default\n" +
                "Add button");
        log("INFO: Executed completely");
    }

    @TestRails(id = "4132")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4132() {
        log("@title: Validate PS38 commission section UI in Create User at non CO level");
        log("Precondition: Login agent at level SMA that active PS38 product");
        log("Step 1: Click on Create User");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());
        log("Step 2: Select PS38 product and observe Commission section");
        page.selectProduct(PS38);

        log("Step 3: Verify UI display correctly");
        log("Verify 1: 1/ Odds Group dropdown: default group C and contains values: A, B, C, D, E\n" +
                "2/ Checkbox\n" +
                ":Apply soccer games setting to other commission types\" and uncheck by default\n" +
                "3/There are 5 types of commission: Soccer Games, Verify high commission, High commission, Normal commission, Parlays, Teasers\n" +
                "The message (* Your current commission group is C)\n" +
                "Sport and league dropdown with Soccer and General by default\n" +
                "Add button");


        log("INFO: Executed completely");
    }

    @TestRails(id = "4134")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4134() {
        log("@title: Validate default commission setting in Create User at CO level with group C by default ");
        log("Precondition: 1/ There is a CO level active PS38 product and was setting commission by PO For example CO is setting Soccer Game:" +
                " Group A: 0.2, Group B: 0.3, Group C: 0.4, Group D: 0.5, Group E: 0.6" +
                "Other commission apply setting as soccer game");
        log("Step 1: Login Agent Site at CO level and Active Create User  in left menu");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());
        log("Step 2:  Select PS38 product and observe Commission section");
        page.selectProduct(PS38);
        page.commissionSectionPS38.expandCollapseCommissionSection(true);

        log("Verify 1: Verify default commission in create user page with selected group C as below:\n" +
                "Soccer Game: 0.4\n" +
                "Very high commission: 0.4\n" +
                "High commission: 0.4\n" +
                "Normal commission: 0.4\n" +
                "Parlays: 0.4\n" +
                "Teaser: 0.4\n" +
                "\n");


        log("INFO: Executed completely");
    }
}
