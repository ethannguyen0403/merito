package agentsite.pages.report.profitandloss;

import agentsite.ultils.account.ProfileUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;

import static common.AGConstant.BTN_SUBMIT;
import static common.AGConstant.Report.*;
import static common.AGConstant.Report.LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS_WITHOUT_DOT;
import static common.AGConstant.Report.ProfitAndLoss.*;

public class OldUIProfitAndLoss extends ProfitAndLoss {
    public void verifyUIDisplayCorrect(boolean isLevelLoginPO) {
        if(isLevelLoginPO) {
            String currency = ProfileUtils.getProfile().getCurrencyCode();
            Assert.assertEquals(lblTimeZone.getText(), "Timezone", "FAILED! Timezone label is incorrect");
            Assert.assertEquals(lblFrom.getText(), LBL_FROM, "FAILED! From label is incorrect");
            Assert.assertEquals(lblTo.getText(), "to", "FAILED! To label is incorrect");
            Assert.assertEquals(lblProduct.getText(), "Product", "FAILED! Product label is incorrect");
            Assert.assertEquals(btnToday.getText(), BTN_TODAY, "FAILED! Today button is incorrect");
            Assert.assertEquals(btnYesterday.getText(), BTN_YESTERDAY, "FAILED! Yesterday button is incorrect");
            Assert.assertEquals(btnLastWeek.getText(), LAST_WEEK, "FAILED! Last Week button is incorrect");
            Assert.assertEquals(btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit is incorrect");
            Assert.assertEquals(lblYouCanSeeReportData.getText(), LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS_WITHOUT_DOT, "FAILED Hint message is incorrect");
            Assert.assertEquals(lblForISTTimeZoneReportAvailable.getText(), LBL_REPORT_AVAILABLE_IST, "FAILED Hint message is incorrect");
            Assert.assertEquals(lblUplineProfitAndLoss.getText(), String.format(LBL_UPLINE_PROFIT_AND_LOST_PO, StringUtils.capitalize(currency.toLowerCase())), "FAILED Upline profit and lost message is incorrect");
            Assert.assertEquals(lblDownlineProfitAndLoss.getText(), String.format(LBL_DOWNLINE_PROFIT_AND_LOST_PO, StringUtils.capitalize(currency.toLowerCase())), "FAILED Downline profit and lost message is incorrect");
            Assert.assertEquals(tblUplineProfitAndLoss.getHeaderNameOfRows(), TBL_UPLINE_TABLE_NEWUI, "FAILED! Upline table is incorrect");
            Assert.assertEquals(tblDownLineProfitAndLoss.getHeaderNameOfRows(), TBL_DOWNLINE_TABLE_PO_OLDUI, "FAILED! Dowline tablie is incorrect");
        } else {
            Assert.assertEquals(lblTimeZone.getText(), "Timezone", "FAILED! Timezone label is incorrect");
            Assert.assertEquals(lblFrom.getText(), LBL_FROM, "FAILED! From label is incorrect");
            Assert.assertEquals(lblTo.getText(), "to", "FAILED! To label is incorrect");
            Assert.assertEquals(lblProduct.getText(), "Product", "FAILED! Product label is incorrect");
            Assert.assertEquals(btnToday.getText(), BTN_TODAY, "FAILED! Today button is incorrect");
            Assert.assertEquals(btnYesterday.getText(), BTN_YESTERDAY, "FAILED! Yesterday button is incorrect");
            Assert.assertEquals(btnLastWeek.getText(), LAST_WEEK, "FAILED! Last Week button is incorrect");
            Assert.assertEquals(btnSubmit.getText(), BTN_SUBMIT, "FAILED! Submit is incorrect");
            Assert.assertEquals(lblYouCanSeeReportData.getText(), LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS_WITHOUT_DOT, "FAILED Hint message is incorrect");
            Assert.assertEquals(lblForISTTimeZoneReportAvailable.getText(), LBL_REPORT_AVAILABLE_IST, "FAILED Hint message is incorrect");
            Assert.assertEquals(lblUplineProfitAndLoss.getText(), LBL_UPLINE_PROFIT_AND_LOST, "FAILED Upline profit and lost message is incorrect");
            Assert.assertEquals(lblDownlineProfitAndLoss.getText(), LBL_DOWNLINE_PROFIT_AND_LOST, "FAILED Downline profit and lost message is incorrect");
            Assert.assertEquals(tblUplineProfitAndLoss.getHeaderNameOfRows(), TBL_UPLINE_TABLE_OLDUI, "FAILED! Upline table is incorrect");
            Assert.assertEquals(tblDownLineProfitAndLoss.getHeaderNameOfRows(), TBL_DOWNLINE_TABLE_OLDUI, "FAILED! Dowline tablie is incorrect");
        }

    }
}
