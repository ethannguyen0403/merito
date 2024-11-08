package membersite.pages.components;

import membersite.pages.components.accountstatement.AccountStatementContainer;
import membersite.pages.components.accountstatement.NewUIAccountStatementContainer;
import membersite.pages.components.accountstatement.NewViewAccountStatementContainer;
import membersite.pages.components.accountstatement.OldUIAccountStatementContainer;
import membersite.pages.components.betslipcontainer.BetsSlipContainer;
import membersite.pages.components.betslipcontainer.NewUIBetsSlipContainer;
import membersite.pages.components.deposit.deposit.DepositContainer;
import membersite.pages.components.deposit.deposit.NewUIDepositContainer;
import membersite.pages.components.deposit.deposit.NewViewDepositContainer;
import membersite.pages.components.deposit.transactionhistory.NewUITransactionHistoryContainer;
import membersite.pages.components.deposit.transactionhistory.NewViewTransactionHistoryContainer;
import membersite.pages.components.deposit.transactionhistory.TransactionHistoryContainer;
import membersite.pages.components.eventcontainer.EventContainerControl;
import membersite.pages.components.eventcontainer.Fair999EventContainerControl;
import membersite.pages.components.eventcontainer.NewViewEventContainerControl;
import membersite.pages.components.eventcontainer.SATEventContainerControl;
import membersite.pages.components.footer.Footer;
import membersite.pages.components.footer.FunFooter;
import membersite.pages.components.footer.SatFooter;
import membersite.pages.components.header.*;
import membersite.pages.components.highlightracecontainer.HighLightRaceContainer;
import membersite.pages.components.highlightracecontainer.OldUIHighLightRaceContainer;
import membersite.pages.components.leftmneu.FairenterUILeftMenu;
import membersite.pages.components.leftmneu.LeftMenu;
import membersite.pages.components.leftmneu.NewUILeftMenu;
import membersite.pages.components.leftmneu.SatLeftMenu;
import membersite.pages.components.loginform.FairenterLoginPopup;
import membersite.pages.components.loginform.FunLoginPopup;
import membersite.pages.components.loginform.LoginPopup;
import membersite.pages.components.loginform.SATLoginPopup;
import membersite.pages.components.marketcontainer.MarketContainerControl;
import membersite.pages.components.marketcontainer.NewUIMarketContainerControl;
import membersite.pages.components.minimybetcontainer.MiniMyBetsContainer;
import membersite.pages.components.minimybetcontainer.NewUIMiniMyBetsContainer;
import membersite.pages.components.mybet.MyBetsContainer;
import membersite.pages.components.mybet.NewUIMyBetsContainer;
import membersite.pages.components.mybet.NewViewMyBetsContainer;
import membersite.pages.components.mybet.OldUIMyBetsContainer;
import membersite.pages.components.nextupracingcontainer.NewUINextUpRacingContainer;
import membersite.pages.components.nextupracingcontainer.NextUpRacingContainer;
import membersite.pages.components.profitandloss.NewUIProfitAndLossContainer;
import membersite.pages.components.profitandloss.NewViewProfitAndLossContainer;
import membersite.pages.components.profitandloss.ProfitAndLossContainer;
import membersite.pages.components.racingcontainer.NewUIRacingContainer;
import membersite.pages.components.racingcontainer.RacingContainer;
import membersite.pages.components.racingmarketcontainer.NewUIRacingMarketControl;
import membersite.pages.components.racingmarketcontainer.RacingMarketContainer;
import membersite.pages.components.underagegamblingpopup.FairenterUnderageGamblingPopup;
import membersite.pages.components.underagegamblingpopup.FunsportUnderageGamblingPopup;
import membersite.pages.components.underagegamblingpopup.SATUnderageGamblingPopup;
import membersite.pages.components.underagegamblingpopup.UnderageGamblingPopup;
import membersite.pages.popup.mymarketpopup.Fair999MyMarketPopup;
import membersite.pages.popup.mymarketpopup.MyMarketPopupContainer;
import membersite.pages.popup.mymarketpopup.SATMyMarketPopup;

public class ComponentsFactory {
    public static Footer footerObject(String types) {
        switch (types) {
            case "funsport":
                return new FunFooter();
            default:
                return new SatFooter();
        }
    }

    public static NextUpRacingContainer nextUpRacingContainerObject(String types) {
        switch (types) {
            default:
                return new NewUINextUpRacingContainer();
        }
    }

    public static Header1 headerObject(String types) {
        switch (types) {
            case "funsport":
                return new FunHeader();
            case "fairenter":
                return new FairenterHeader();
            case "fairexchange":
                return new Fair999Header();
            case "fairexchange_plus":
            case "eu1010":
                return new PlusHeader();
            default:
                return new SatHeader();
        }
    }

    public static UnderageGamblingPopup underageGamblingPopupObject(String types) {
        switch (types) {
            case "funsport":
                return new FunsportUnderageGamblingPopup();
            case "fairenter":
                return new FairenterUnderageGamblingPopup();
            default:
                return new SATUnderageGamblingPopup();
        }
    }

    public static AccountStatementContainer accountStatementContainerObject(String types) {
        switch (types) {
            case "funsport":
                return new OldUIAccountStatementContainer();
            case "fairenter":
                return new OldUIAccountStatementContainer();
            case "satsport":
                return new NewUIAccountStatementContainer();
            case "fairexchange":
                return new NewUIAccountStatementContainer();
            default:
                return new NewViewAccountStatementContainer();
        }
    }

    public static MyBetsContainer myBetsContainerObject(String types) {
        switch (types) {
            case "funsport":
                return new OldUIMyBetsContainer();
            case "fairenter":
                return new OldUIMyBetsContainer();
            case "satsport":
                return new NewUIMyBetsContainer();
            case "fairexchange":
                return new NewUIMyBetsContainer();
            default:
                return new NewViewMyBetsContainer();
        }
    }

    public static ProfitAndLossContainer profitAndLossContainerObject(String types) {
        switch (types) {
            case "satsport":
                return new NewUIProfitAndLossContainer();
            case "fairexchange":
                return new NewUIProfitAndLossContainer();
            default:
                return new NewViewProfitAndLossContainer();
        }
    }

    public static LoginPopup loginPopupObject(String types) {
        switch (types) {
            case "funsport":
                return new FunLoginPopup();
            case "fairenter":
                return new FairenterLoginPopup();
            default:
                return new SATLoginPopup();
        }
    }

    public static EventContainerControl eventContainerControlObject(String types) {
        switch (types) {
            case "fairexchange":
                return new Fair999EventContainerControl();
            case "satsport":
                return new SATEventContainerControl();
            default:
                return new NewViewEventContainerControl();
        }
    }

    public static LeftMenu lefMenuObject(String types) {
        switch (types) {
            case "fairenter":
                return new FairenterUILeftMenu();
            case "satsport":
                return new SatLeftMenu();
            default:
                return new NewUILeftMenu();
        }
    }

    public static MarketContainerControl marketOddControlObject(String types) {
        switch (types) {
            default:
                return new NewUIMarketContainerControl();
        }
    }

    public static BetsSlipContainer betsSlipContainerObject(String types) {
        switch (types) {
            default:
                return new NewUIBetsSlipContainer();
        }
    }

    public static MiniMyBetsContainer miniMyBetsContainerObject(String types) {
        switch (types) {
            default:
                return new NewUIMiniMyBetsContainer();
        }
    }

    public static RacingMarketContainer racingMarketContainerObject(String types) {
        switch (types) {
            default:
                return new NewUIRacingMarketControl();
        }
    }

    public static HighLightRaceContainer highLightRaceContainerObject(String types) {
        switch (types) {
            default:
                return new OldUIHighLightRaceContainer();
        }
    }

    public static RacingContainer racingContainerObject(String types) {
        switch (types) {
            default:
                return new NewUIRacingContainer();
        }
    }

    public static DepositContainer depositContainerObject(String types) {
        switch (types) {
            case "satsport":
                return new NewUIDepositContainer();
            default:
                return new NewViewDepositContainer();
        }
    }

    public static TransactionHistoryContainer transactionHistoryContainerObject(String types) {
        switch (types) {
            case "satsport":
                return new NewUITransactionHistoryContainer();
            default:
                return new NewViewTransactionHistoryContainer();
        }
    }

    public static MyMarketPopupContainer myMarketPopupObject(String types) {
        switch (types) {
            case "satsport":
                return new SATMyMarketPopup();
            default:
                return new Fair999MyMarketPopup();
        }
    }
}
