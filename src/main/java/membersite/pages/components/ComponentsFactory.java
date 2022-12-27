package membersite.pages.components;

import membersite.pages.components.footer.Footer;
import membersite.pages.components.footer.FunFooter;
import membersite.pages.components.footer.SatFooter;
import membersite.pages.components.header.*;
import membersite.pages.components.loginform.FairenterLoginPopup;
import membersite.pages.components.loginform.FunLoginPopup;
import membersite.pages.components.loginform.LoginPopup;
import membersite.pages.components.loginform.SATLoginPopup;
import membersite.pages.components.underagegamblingpopup.FairenterUnderageGamblingPopup;
import membersite.pages.components.underagegamblingpopup.FunsportUnderageGamblingPopup;
import membersite.pages.components.underagegamblingpopup.SATUnderageGamblingPopup;
import membersite.pages.components.underagegamblingpopup.UnderageGamblingPopup;

public class ComponentsFactory {
    public static Footer footerObject(String types){
        switch (types){
            case "funsport":
                return new FunFooter();
            default:
                return new SatFooter();
        }
       }

    public static Header headerObject(String types){
        switch (types){
            case "funsport":
                return new FunHeader();
            case"fairenter":
                return new FairenterHeader();
              //  case "F"
            default:
                return new SatHeader();
        }
    }

    public static UnderageGamblingPopup underageGamblingPopupObject(String types){
        switch (types){
            case "funsport":
                return new FunsportUnderageGamblingPopup();
            case "fairenter":
                return new FairenterUnderageGamblingPopup();
            default:
                return new SATUnderageGamblingPopup();
        }
    }

    public static LoginPopup loginPopupObject(String types){
        switch (types){
            case "funsport":
                return new FunLoginPopup();
            case"fairenter":
                return new FairenterLoginPopup();
            default:
                return new SATLoginPopup();
        }
    }



}
