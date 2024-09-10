package agentsite.pages.components;

import agentsite.pages.agentmanagement.betsettinglisting.BetSettingListing;
import agentsite.pages.agentmanagement.betsettinglisting.NewUIBetSettingListing;
import agentsite.pages.agentmanagement.betsettinglisting.OldUIBetSettingListing;
import agentsite.pages.agentmanagement.commissionlisting.CommissionSettingListing;
import agentsite.pages.agentmanagement.commissionlisting.NewUIComissionSettingListing;
import agentsite.pages.agentmanagement.commissionlisting.OldUIComissionSettingListing;
import agentsite.pages.agentmanagement.createdownlineagent.accountbalancetransferconditionsection.AccountBalanceTransferConditionInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.accountbalancetransferconditionsection.NewUIAccountBalanceTransferConditionSection;
import agentsite.pages.agentmanagement.createdownlineagent.accountbalancetransferconditionsection.OldUIAccountBalanceTransferConditionSection;
import agentsite.pages.agentmanagement.createdownlineagent.accountinfosection.AccountInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.accountinfosection.FunUIAccountInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.accountinfosection.NewUIAccountInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.accountinfosection.OldUIAccountInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.betsettingsection.BetSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.betsettingsection.FunUIBetSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.betsettingsection.NewUIBetSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.betsettingsection.OldUIBetSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection.CashBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection.NewUICashBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection.OldUICashBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.commissionsettingsection.CommissionSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.commissionsettingsection.NewUICommissionSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.commissionsettingsection.OldUICommissionSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection.CreditBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection.NewUICreditBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection.OldUICreditBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection.NewUIPositionTakingSection;
import agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection.OldUIPositionTakingSection;
import agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection.PositionTakingSection;
import agentsite.pages.agentmanagement.createdownlineagent.productstatussettingsection.NewUIProductStatusSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.productstatussettingsection.OldUIProductStatusSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.productstatussettingsection.ProductStatusSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection.RateSettingsSection;
import agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection.NewUIRateSettingsSection;
import agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection.OldUIRateSettingsSection;
import agentsite.pages.agentmanagement.createdownlineagent.risksettingsection.NewUIRiskSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.risksettingsection.OldUIRiskSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.risksettingsection.RiskSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection.FunUITaxSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection.NewUITaxSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection.OldUITaxSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection.TaxSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.transfersettingsection.NewUITransferSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.transfersettingsection.OldUITransferSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.transfersettingsection.TransferSettingSection;
import agentsite.pages.agentmanagement.createuser.CreateUser;
import agentsite.pages.agentmanagement.createuser.NewUICreateUser;
import agentsite.pages.agentmanagement.createuser.OldUICreateUser;
import agentsite.pages.agentmanagement.creditbalancelisting.CreditBalanceListing;
import agentsite.pages.agentmanagement.creditbalancelisting.NewUICreditBalanceListing;
import agentsite.pages.agentmanagement.creditbalancelisting.OldUICreditBalanceListing;
import agentsite.pages.agentmanagement.depositwithdrawal.DepositWithdraw;
import agentsite.pages.agentmanagement.depositwithdrawal.NewUIDepositWithdraw;
import agentsite.pages.agentmanagement.depositwithdrawal.OldUIDepositWithdraw;
import agentsite.pages.agentmanagement.downlinelisting.DownlineListing;
import agentsite.pages.agentmanagement.downlinelisting.NewUIDownlineListing;
import agentsite.pages.agentmanagement.downlinelisting.OldUIDownlineListing;
import agentsite.pages.agentmanagement.editdownlinelisting.EditDownlineListing;
import agentsite.pages.agentmanagement.editdownlinelisting.NewUIEditDownlineListing;
import agentsite.pages.agentmanagement.editdownlinelisting.OldUIEditDownlineListing;
import agentsite.pages.agentmanagement.eventbetsizesetting.EventBetSizeSetting;
import agentsite.pages.agentmanagement.eventbetsizesetting.NewUIEventBetSizeSetting;
import agentsite.pages.agentmanagement.eventbetsizesetting.OldUIEventBetSizeSetting;
import agentsite.pages.agentmanagement.followbets.FollowBets;
import agentsite.pages.agentmanagement.followbets.NewUIFollowBets;
import agentsite.pages.agentmanagement.followbets.OldUIFollowBets;
import agentsite.pages.agentmanagement.ptlisting.FairenterPositionTakingListing;
import agentsite.pages.agentmanagement.ptlisting.NewUIPositionTakingListing;
import agentsite.pages.agentmanagement.ptlisting.OldUIPositionTakingListing;
import agentsite.pages.agentmanagement.ptlisting.PositionTakingListing;
import agentsite.pages.agentmanagement.subuserlisting.NewUISubUserListing;
import agentsite.pages.agentmanagement.subuserlisting.OldUISubUserListing;
import agentsite.pages.agentmanagement.subuserlisting.SubUserListing;
import agentsite.pages.agentmanagement.taxsettinglisting.NewUITaxSettingListing;
import agentsite.pages.agentmanagement.taxsettinglisting.OldUITaxSettingListing;
import agentsite.pages.agentmanagement.taxsettinglisting.TaxSettingListing;
import agentsite.pages.agentmanagement.transfer.NewUITransfer;
import agentsite.pages.agentmanagement.transfer.OldUITransfer;
import agentsite.pages.agentmanagement.transfer.Transfer;
import agentsite.pages.cashmanagement.depositwithdrawaltransaction.DepositWithdrawalTransaction;
import agentsite.pages.cashmanagement.depositwithdrawaltransaction.NewUIDepositWithdrawalTransaction;
import agentsite.pages.cashmanagement.depositwithdrawaltransaction.OldUIDepositWithdrawalTransaction;
import agentsite.pages.cashmanagement.paymentchannelmanagement.NewUIPaymentChannelManagement;
import agentsite.pages.cashmanagement.paymentchannelmanagement.OldUIPaymentChannelManagement;
import agentsite.pages.cashmanagement.paymentchannelmanagement.PaymentChannelManagement;
import agentsite.pages.cashmanagement.quickdepositconfiguration.NewUIQuickDepositConfiguration;
import agentsite.pages.cashmanagement.quickdepositconfiguration.OldUIQuickDepositConfiguration;
import agentsite.pages.cashmanagement.quickdepositconfiguration.QuickDepositConfiguration;
import agentsite.pages.components.header.Header;
import agentsite.pages.components.header.NewUIHeader;
import agentsite.pages.components.header.OldUIHeader;
import agentsite.pages.components.leftmenu.*;
import agentsite.pages.components.quicksearch.NewUIQuickSearch;
import agentsite.pages.components.quicksearch.OldUIQuickSearch;
import agentsite.pages.components.quicksearch.QuickSearch;
import agentsite.pages.report.components.transactiondetailspopup.NewUITransactionDetailsPopup;
import agentsite.pages.report.components.transactiondetailspopup.OldUITransactionDetailsPopup;
import agentsite.pages.report.components.transactiondetailspopup.TransactionDetailsPopup;
import agentsite.pages.report.profitandloss.NewUIProfitAndLoss;
import agentsite.pages.report.profitandloss.OldUIProfitAndLoss;
import agentsite.pages.report.profitandloss.ProfitAndLoss;
import agentsite.pages.report.topgainerstoplosers.NewUITopGainersTopLosers;
import agentsite.pages.report.topgainerstoplosers.OldUITopGainersTopLosers;
import agentsite.pages.report.topgainerstoplosers.TopGainersTopLosers;
import agentsite.pages.report.winlosssimple.NewUIWinLossSimple;
import agentsite.pages.report.winlosssimple.OldUIWinLossSimple;
import agentsite.pages.report.winlosssimple.WinLossSimple;
import agentsite.pages.report.statementreport.NewUIStatementReport;
import agentsite.pages.report.statementreport.OldUIStatementReport;
import agentsite.pages.report.statementreport.StatementReport;

public class ComponentsFactory {
    public static LeftMenu leftMenuObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUILeftMenu();
            case "fairenter":
                return new FairenterUILeftMenu();
            case "funsport":
                return new FunsportUILeftMenu();
            default:
                return new NewUILeftMenu();

        }
    }

    public static Header headerObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUIHeader();
            default:
                return new NewUIHeader();
        }
    }

    public static QuickSearch quickSearchObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUIQuickSearch();
            default:
                return new NewUIQuickSearch();
        }
    }

    public static AccountInforSection accInfoObject(String types) {
        switch (types) {
            case "funsport":
                return new FunUIAccountInforSection();
            case "satsport":
                return new OldUIAccountInforSection();
            default:
                return new NewUIAccountInforSection();
        }
    }

    public static CreditBalanceSection creditBalanceInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUICreditBalanceSection();
            default:
                return new NewUICreditBalanceSection();
        }
    }

    public static RiskSettingSection riskSettingInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUIRiskSettingSection();
            default:
                return new NewUIRiskSettingSection();
        }
    }

    public static CashBalanceSection cashBalanceInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUICashBalanceSection();
            default:
                return new NewUICashBalanceSection();
        }
    }

    public static RateSettingsSection rateSettingInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUIRateSettingsSection();
            default:
                return new NewUIRateSettingsSection();
        }
    }


    public static ProductStatusSettingSection productStatusSettingInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUIProductStatusSettingSection();
            default:
                return new NewUIProductStatusSettingSection();
        }
    }

    public static BetSettingSection betSettingInfoObject(String types) {
        switch (types) {
            case "funsport":
                return new FunUIBetSettingSection();
            case "satsport":
                return new OldUIBetSettingSection();
            default:
                return new NewUIBetSettingSection();
        }
    }

    public static TaxSettingSection taxSettingInfoObject(String types) {
        switch (types) {
            case "funsport":
                return new FunUITaxSettingSection();
            case "satsport":
                return new OldUITaxSettingSection();
            default:
                return new NewUITaxSettingSection();
        }
    }

    public static CommissionSettingSection commissionSettingInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUICommissionSettingSection(types);
            default:
                return new NewUICommissionSettingSection(types);
        }
    }

    public static PositionTakingSection positionTakingInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUIPositionTakingSection();
            default:
                return new NewUIPositionTakingSection();
        }
    }

    public static TransferSettingSection transferSettingInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUITransferSettingSection();
            default:
                return new NewUITransferSettingSection();
        }
    }

    public static CreditBalanceListing creditBalanceListing(String types) {
        switch (types) {
            case "satsport":
                return new OldUICreditBalanceListing();
            default:
                return new NewUICreditBalanceListing();
        }
    }

    public static DepositWithdraw depositWithdraw(String types) {
        switch (types) {
            case "satsport":
                return new OldUIDepositWithdraw();
            default:
                return new NewUIDepositWithdraw();
        }
    }

    public static BetSettingListing betSettingPage(String types) {
        switch (types) {
            case "satsport":
                return new OldUIBetSettingListing();
            default:
                return new NewUIBetSettingListing();
        }
    }

    public static TaxSettingListing taxSettingPage(String types) {
        switch (types) {
            case "satsport":
                return new OldUITaxSettingListing();
            default:
                return new NewUITaxSettingListing();
        }
    }

    public static PositionTakingListing positionTakingListingPage(String types) {
        switch (types) {
            case "fairenter":
                return new FairenterPositionTakingListing();
            case "satsport":
                return new OldUIPositionTakingListing();
            default:
                return new NewUIPositionTakingListing();
        }
    }

    public static StatementReport statementReportPage(String types) {
        switch (types) {
            case "satsport":
                return new OldUIStatementReport();
            default:
                return new NewUIStatementReport();
        }
    }

    public static DownlineListing downlineListingPage(String types) {
        switch (types) {
            case "satsport":
                return new OldUIDownlineListing();
            default:
                return new NewUIDownlineListing();
        }
    }

    public static EditDownlineListing editDownlineListing(String types) {
        switch (types) {
            case "satsport":
                return new OldUIEditDownlineListing(types);
            default:
                return new NewUIEditDownlineListing(types);
        }
    }

    public static TopGainersTopLosers topGainersTopLosers(String types) {
        switch (types) {
            case "satsport":
                return new OldUITopGainersTopLosers();
            default:
                return new NewUITopGainersTopLosers();
        }
    }

    public static WinLossSimple winLossSimple(String types) {
        switch (types) {
            case "satsport":
                return new OldUIWinLossSimple();
            default:
                return new NewUIWinLossSimple();
        }
    }

    public static ProfitAndLoss profitAndLoss(String types) {
        switch (types) {
            case "satsport":
                return new OldUIProfitAndLoss();
            default:
                return new NewUIProfitAndLoss();
        }
    }

    public static TransactionDetailsPopup transactionDetailsPopup(String types) {
        switch (types) {
            case "satsport":
                return new OldUITransactionDetailsPopup();
            default:
                return new NewUITransactionDetailsPopup();
        }
    }

    public static FollowBets followBets(String types) {
        switch (types) {
            case "satsport":
                return new OldUIFollowBets(types);
            default:
                return new NewUIFollowBets(types);
        }
    }

    public static CommissionSettingListing commissionSettingListing(String types) {
        switch (types) {
            case "satsport":
                return new OldUIComissionSettingListing();
            default:
                return new NewUIComissionSettingListing();
        }
    }

    public static CreateUser createUserPage(String types) {
        switch (types) {
            case "satsport":
                return new OldUICreateUser(types);
            default:
                return new NewUICreateUser(types);
        }
    }

    public static AccountBalanceTransferConditionInforSection accountBalanceTransferConditionInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUIAccountBalanceTransferConditionSection();
            default:
                return new NewUIAccountBalanceTransferConditionSection();
        }
    }

    public static EventBetSizeSetting eventBetSizeSettingPage(String types) {
        switch (types) {
            case "satsport":
                return new OldUIEventBetSizeSetting(types);
            default:
                return new NewUIEventBetSizeSetting(types);
        }
    }

    public static SubUserListing subUserListingPage(String types) {
        switch (types) {
            case "satsport":
                return new OldUISubUserListing(types);
            default:
                return new NewUISubUserListing(types);
        }
    }

    public static Transfer transferPage(String types) {
        switch (types) {
            case "satsport":
                return new OldUITransfer(types);
            default:
                return new NewUITransfer(types);
        }
    }

    public static DepositWithdrawalTransaction depositWithdrawalTransaction(String types) {
        switch (types) {
            case "satsport":
                return new OldUIDepositWithdrawalTransaction();
            default:
                return new NewUIDepositWithdrawalTransaction();
        }
    }

    public static PaymentChannelManagement paymentChannelManagement(String types) {
        switch (types) {
            case "satsport":
                return new OldUIPaymentChannelManagement();
            default:
                return new NewUIPaymentChannelManagement();
        }
    }

    public static QuickDepositConfiguration quickDepositConfiguration(String types) {
        switch (types) {
            case "satsport":
                return new OldUIQuickDepositConfiguration();
            default:
                return new NewUIQuickDepositConfiguration();
        }
    }
}
