package agentsite.testcase.proteus;

import agentsite.pages.agentmanagement.CreateDownLineAgentPage;
import agentsite.pages.agentmanagement.CreateUserPage;
import baseTest.BaseCaseTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.testraildemo.TestRails;

import static common.AGConstant.PS38;

public class CreateDownlineAgentTest extends BaseCaseTest {
    @TestRails(id = "4133")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4133() {
        log("@title: Validate default commission setting in Create Downline Agent correctly at CO level");
        log("Precondition: 1/ There is a CO level active PS38 product and was setting commission by PO For example CO is setting Soccer Game:\n" +
                "Group A: 0.2, Group B: 0.3, Group C: 0.4, Group D: 0.5, Group E: 0.6 \n" +
                "Other commission apply setting as soccer game");
        log("Step 1:Login Agent Site at CO level and Active Create Downline Agent in left menu");
        CreateDownLineAgentPage page = agentHomePage.navigateCreateDownLineAgentPage(environment.getSecurityCode());

        log("Step 2: Select PS38 product and observe Commission section");
        page.selectProduct(PS38);

        log("Verify 1: Verify default commission in create downline agent display correctly as PO setting for logged CO\n" +
                "Soccer Game: Group A: 0.2, Group B: 0.3, Group C: 0.4, Group D: 0.5, Group E: 0.6\n" +
                "Other commission apply setting as soccer game");
        log("INFO: Executed completely");
    }

    @TestRails(id = "41393")
    @Test(groups = {"ps38_co", "Proteus.2024.V.1.0"})
    @Parameters({"currency"})
    public void PS38_Agent_TC4132() {
        log("@title: Validate PS38 commission section UI in Create Downline Agent when login at CO level ");
        log("Precondition: Login agent at CO level that active PS38 product");
        log("Step 1: Click on Create Downline Agent");
        CreateUserPage page = agentHomePage.navigateCreateUserPage(environment.getSecurityCode());
        log("Step 2: Select PS38 product and observe Commission section");
        page.selectProduct(PS38);

        log("Step 3: Verify UI display correctly");

        log("Verify 1: 1/ Odds Group dropdown: default group C and contains values: A, B, C, D, E\n" +
                "2/ Checkbox\n" +
                ":Apply soccer games setting to other commission types\" and uncheck by default\n" +
                "3/ Table with headers: Group, Commission on, Group A, Group B,  Group C, Group D, Group E\n" +
                "Row under Group column is: Soccer Games, Very high commission, High commission , Normal commission , Parlays, Teasers\n" +
                "All row under Commission on column are: Commission");


        log("INFO: Executed completely");
    }
}
