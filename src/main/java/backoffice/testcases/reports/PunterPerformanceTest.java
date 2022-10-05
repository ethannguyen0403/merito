package backoffice.testcases.reports;

import com.paltech.utils.DateUtils;
import backoffice.common.BOConstants;
import org.testng.Assert;
import org.testng.annotations.Test;
import backoffice.pages.bo.reports.PunterPerformancePage;
import baseTest.BaseCaseMerito;

public class PunterPerformanceTest extends BaseCaseMerito{

    /**
     * @title: Validate can drill down to member level
     * @pre-condition:
     *           1. Log in successfully
     * @steps:1. Access Reports > Punter Performance
     *          2. Filter Today data
     *          3. Drill down to member level
     * @expect: 1. Can drill down to member level, at Member level there no hyperlink
     */
    @Test (groups = {"smoke"})
    public void BO_Report_Punter_Performance_001(){
        log("@title: Validate can drill down to member level");
        log("Step Access Reports > Punter Performance");
        String fromDate = DateUtils.getDate(-5,"dd/MM/yyyy",BOConstants.GMT_FOUR);
        String toDate = DateUtils.getDate(-1,"dd/MM/yyyy",BOConstants.GMT_FOUR);
        PunterPerformancePage page = backofficeHomePage.navigatePunterPerformance();

        log("Step 2. Filter Today data");
        page.filter(fromDate,toDate,"","","All");
       //List<String> lstPortal = page.tblReport.getColumn(page.colUsername,false);

        log("Step 3. Drill down to member level");
        page.drillDown("Member");

        log("Verify 1. Can drill down to member level, at Member level there no hyperlink");
        Assert.assertFalse(page.isUsernameAsHyperlink("Member"),"FAILED! Username displays as hyperlink when level is Member");
        log("INFO: Executed completely");
    }
}
