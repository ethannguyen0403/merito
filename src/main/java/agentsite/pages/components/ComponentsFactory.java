package agentsite.pages.components;

import agentsite.pages.agentmanagement.betsettinglisting.BetSettingListing;
import agentsite.pages.agentmanagement.betsettinglisting.NewUIBetSettingListing;
import agentsite.pages.agentmanagement.betsettinglisting.OldUIBetSettingListing;
import agentsite.pages.agentmanagement.createdownlineagent.accountinfosection.AccountInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.accountinfosection.NewUIAccountInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.accountinfosection.OldUIAccountInforSection;
import agentsite.pages.agentmanagement.createdownlineagent.betsettingsection.BetSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.betsettingsection.NewUIBetSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.betsettingsection.OldUIBetSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection.CashBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection.NewUICashBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.cashbalancesection.OldUICashBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection.CreditBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection.NewUICreditBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.creditbalancesection.OldUICreditBalanceSection;
import agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection.NewUIPositionTakingSection;
import agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection.OldUIPositionTakingSection;
import agentsite.pages.agentmanagement.createdownlineagent.positiontakingsection.PositionTakingSection;
import agentsite.pages.agentmanagement.createdownlineagent.productsettingsection.NewUIProductSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.productsettingsection.OldUIProductSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.productsettingsection.ProductSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection.RateSettingsSection;
import agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection.NewUIRateSettingsSection;
import agentsite.pages.agentmanagement.createdownlineagent.ratesettingsection.OldUIRateSettingsSection;
import agentsite.pages.agentmanagement.createdownlineagent.risksettingsection.NewUIRiskSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.risksettingsection.OldUIRiskSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.risksettingsection.RiskSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection.NewUITaxSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection.OldUITaxSettingSection;
import agentsite.pages.agentmanagement.createdownlineagent.taxsettingsection.TaxSettingSection;
import agentsite.pages.agentmanagement.creditbalancelisting.creditbalancelisting.CreditBalanceListing;
import agentsite.pages.agentmanagement.creditbalancelisting.creditbalancelisting.NewUICreditBalanceListing;
import agentsite.pages.agentmanagement.creditbalancelisting.creditbalancelisting.OldUICreditBalanceListing;
import agentsite.pages.agentmanagement.depositwithdrawal.DepositWithdraw;
import agentsite.pages.agentmanagement.depositwithdrawal.NewUIDepositWithdraw;
import agentsite.pages.agentmanagement.depositwithdrawal.OldUIDepositWithdraw;
import agentsite.pages.components.header.Header;
import agentsite.pages.components.header.NewUIHeader;
import agentsite.pages.components.header.OldUIHeader;
import agentsite.pages.components.leftmenu.LeftMenu;
import agentsite.pages.components.leftmenu.NewUILeftMenu;
import agentsite.pages.components.leftmenu.OldUILeftMenu;

public class ComponentsFactory {
    public static LeftMenu leftMenuObject(String types) {
        switch (types) {
            case "funsport":
                return new NewUILeftMenu();
            default:
                return new OldUILeftMenu();
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

    public static AccountInforSection accInfoObject(String types) {
        switch (types) {
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

    public static ProductSettingSection productSettingInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUIProductSettingSection();
            default:
                return new NewUIProductSettingSection();
        }
    }

    public static BetSettingSection betSettingInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUIBetSettingSection();
            default:
                return new NewUIBetSettingSection();
        }
    }

    public static TaxSettingSection taxSettingInfoObject(String types) {
        switch (types) {
            case "satsport":
                return new OldUITaxSettingSection();
            default:
                return new NewUITaxSettingSection();
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
}
