package common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberConstants {
    public static final String TODAY = "Today";
    public static final String TOMORROW = "Tomorrow";
    public static final String YESTERDAY = "Yesterday";
    public static final String THIS_WEEK = "This Week";
    public static final String THIS_MONTH = "This Month";
    public static final String STARTINGIN = "Starting in";
    public static final String STARTINGSOON = "Starting soon";
    public static final String TIMEZONE = "IST";
    public static final String GMT_5_30 = "GMT+5:30";
    public static final String GMT_LOCAL = "GMT+7:00";
    public static final String NO_RECORDs_FOUND = "No records found";
    public static final String NO_RECORD_FOUND = "No Record Found";
    public static final String WICKET_FANCY_TITILE = "Wicket Fancy";
    public static final String FANCY_TITILE = "Fancy";
    public static final String CENTRAL_FANCY_TITILE = "Central Fancy";
    public static final String WICKET_FANCY_CODE = "WICKET_FANCY";
    public static final String CENTRAL_FANCY_CODE = "CENTRAL_FANCY";
    public static final String FANCY_CODE = "FAIR_27FANCY";
    public static final String CENTRAL_BOOKMAKER_CODE = "MANUAL_ODDS";
    public static final String CENTRAL_BOOKMAKER_TITLE = "Manual Odds";
    public static final String WICKET_BOOKMAKER_CODE = "WICKET_BOOKMAKER";
    public static final String WICKET_BOOKMAKER_TITLE = "Bookmaker";
    public final static String NOTES_GMT4 = "Note : Date will be based on time zone GMT-04:00";
    public final static String EXCHANGE = "Exchange";

    public static final Map<String, String> TIMEZONE_BRAND = new HashMap<String, String>() {
        {
            put("satsport", "IST");
            put("funsport", "GMT-04:00");
            put("fairenter", "GMT-04:00");
            put("fairexchange", "GMT-04:00");
            put("laser365", "GMT-04:00");

        }
    };

    public static class LoginPage {
        public final static String MSG_INVALID_PASSWORD = "You have entered an incorrect Password. Please try again.";
        public final static String MSG_LOGIN_FAIL = "Invalid Username OR Password.";
        public final static String MSG_UNDERAGE_GAMELING = "Underage gambling is prohibited.\n" +
                "Please confirm if you are 18 years old\nand above as of today.";
        public final static String BTN_CONFIRM = "Confirm";
        public final static String BTN_EXIT = "Exit";
        public final static String MSG_LOGIN_INACTIVE_ACCOUNT = "Your account has been Inactive, please contact your Upline for help.";
        public final static String LBL_USERNAME = "Email/Username";
        public final static String LBL_USERNAME_ONLY = "Username";
        public final static String LBL_PASSWORD = "Password";
        public final static String LBL_LOGLOUT = "Logout";
        public final static String LBL_REMEMBER_PASSWORD = "Remember username";
        public final static String LBL_REMEMBER_Me = "Remember Me";
        public final static String LBL_SHOW_PASSWORD = "Show Password";
        public final static String BTN_LOGIN = "Login";
        public final static String LBL_UNDERGAMLING_TITLE = "";
        public final static String LBL_UNDERGAMLING_CONTENT = "Please confirm if you are 18 years old\n" +
                "and above as of today.";
        public final static String LBL_UNDERGAMLING_TITLE_F24 = "Underage gambling is prohibited.";
        public final static String LBL_UNDERGAMLING_CONTENT_F24 = "Please confirm if you are 18 years old and above as of today.";
        public final static String LBL_AGE_18 = "Underage gambling is an offence";
        public static final Map<String, String> LBL_UNDERGAMLING_TITLE_MAP = new HashMap<String, String>() {
            {
                put("satsport", "Underage gambling is prohibited.");
                put("fairenter", "Underage gambling is prohibited.");
                put("funsport", "Underage gambling is prohibited");
            }
        };
        public static final Map<String, String> LBL_UNDERGAMLING_CONTENT_MAP = new HashMap<String, String>() {
            {
                put("satsport", "Please confirm if you are 18 years old\n" +
                        "and above as of today.");
                put("fairenter", "Please confirm if you are 18 years old and above as of today.");
                put("funsport", "Please confirm if you are 18 years old and above as of today");
            }
        };

    }

    public static class APHomePage {
        public static final String URLHOME = "%s#/1/home/exchange/main/all";
    }

    public static class MyLastLogin {
        public static final String TITLE_PAGE = "My Last Logins";
        public final static List<String> TABLE_HEADES = Arrays.asList("Login Date & Time", "Login Status", "IP Address", "Device Info", "Country");
    }

    public static class HomePage {
        public static final String URL = "/home/exchange/sport/all";
        public static final Map<String, String> PRODUCTS = new HashMap<String, String>() {
            {
                put("EXCHANGE", "Exchange");
                put("SUPER_SPADE", "Live Dealer Asian");
                put("DIGIENT", "Lottery & Slots");
                put("EZUGI", "Live Dealer European");
                put("EXCH_GAMES", "Exchange Games");
                put("VERONICA", "Supernowa Casino");

            }
        };

        public static final Map<String, String> SPORT_ID = new HashMap<String, String>() {
            {
                put("Home", "all");
                put("In-Play", "in-play");
                put("Soccer", "1");
                put("Cricket", "4");
                put("Tennis", "2");
                put("Greyhound Racing", "4339");
                put("Greyhound - Today's Card", "4339");
                put("Basketball", "7522");
                put("American Football", "6423");
                put("Horse Racing", "7");
                put("Horse Race - Today's Card", "7");
                put("Golf", "3");
                put("Volleyball", "998917");
                put("Motor Sport", "8");
                put("Darts", "3503");
                put("Athletics", "3988");
                put("Rugby Union", "5");
                put("Snooker", "6422");
                put("Cycling", "11");
                put("Rugby League", "1477");
                put("Boxing", "6");
                put("Esports", "27454571");
                put("Gaelic Games", "2152880");
                put("Ice Hockey", "7524");
                put("Mixed Martial Arts", "26420387");
                put("Australian Rules", "61420");
                put("Politics", "2378961");
                put("Lottery Specials", "28361978");
                put("Special Bets", "10");
            }
        };

        public static final String SPORT_HIGHLIGHT = "Sport Highlights";
        public static final String SPORT_HIGHLIGHT_LABEL = "%s Highlights";
        public static final Map<String, String> DDB_MY_ACCOUNT = new HashMap<String, String>() {
            {
                put("Configure Nickname", "Configure Nickname");
                put("Configure Timezone", "Configure Timezone");
                put("Account Statement", "Account Statement");
                put("My Bets", "My Bets");
                put("Profit & Loss", "Profit & Loss");
                put("My Last Logins", "My Last Logins");
                put("Change Password", "Change Password");
                put("Logout", "Logout");
            }
        };
        public static final Map<String, String> DDB_MY_ACCOUNT_FS = new HashMap<String, String>() {
            {
                put("My Details", "My Details");
                put("My Last Logins", "My Last Logins");
                put("My Bets/History", "My Bets/History");
            }

            ;
        };
        public static final String NO_EVENT_AVAILABLE = "No Events Available";
        public final static String NO_RECORD_FOUNDS = "No records found";
    }

    public static class MarketContainer {
        public static final String RULE = "Rules";
        public static final String MATCHED = "Matched";
    }

    public static class AsianLiveDealer {
        public final static String URL = "%s#/2/home/live-dealer/super-spade";
    }

    public static class EuropeanRoom {
        public final static String URL = "%s#/2/home/live-dealer/ezugi";
    }

    public static class LotterySlot {
        public final static String URL = "%s#/2/home/lottery-slots";
        public final static List<String> GROUP_GAME = Arrays.asList("Slots games", "Table games", "Draw games", "Sports games");
    }

    public static class Header {
        public final static String BALANCE = "Main:";
        public final static String OUTSTANDING = "Liability:";
        public final static String MY_BETS = "My Bets";
        public final static String MY_MARKET = "My Markets";
        public final static String My_ACCOUNT = "My Account";
        public final static String LEFT_MENU_ICON_COLLAPSE = "images/skins/%s/collapse-menu.svg";
        public final static String LEFT_MENU_ICON_EXPAND = "images/skins/%s/menu.svg";
//        public final static String SAT_LEFT_MENU_ICON_COLLAPSE ="images/skins/%s/menu.svg";
//        public final static String SAT_LEFT_MENU_ICON_EXPAND ="images/skins/%s/collapse-menu.svg";

    }

    public static class HeaderSAT {
        public final static String BALANCE = "Balance:";
        public final static String OUTSTANDING = "Liability:";
        public final static String MY_BETS = "My Bets";
        public final static String MY_MARKET = "My Markets";
        public final static String My_ACCOUNT = "My Account";
        public final static String LEFT_MENU_ICON_COLLAPSE = "images/skins/%s/collapse-menu.svg";
        public final static String LEFT_MENU_ICON_EXPAND = "images/skins/%s/menu.svg";
        //        public final static String SAT_LEFT_MENU_ICON_COLLAPSE ="images/skins/%s/menu.svg";
//        public final static String SAT_LEFT_MENU_ICON_EXPAND ="images/skins/%s/collapse-menu.svg";
        public final static String LOGO_IMAGE = "assets/%s";
    }

    public static class APHeader {
        public final static String BALANCE = "Bal:";
    }

    public static class MyMarketsPopup {
        public final static String TITLE = "My Markets";
        public final static String NOTES = "Note : Date will be based on time zone GMT-04:00";
        public final static List<String> TABLE_MY_MARKETS_HEADER = Arrays.asList("Market ID", "Market Start Time", "Market Name", "Liability");
        public final static String NO_RECORD_FOUNDS = "No records found";
    }

    public static class AccountStatementPage {

        public final static String START_DATE = "Start Date";
        public final static String END_DATE = "End Date";
        public final static String NOTES = "Note : Date will be based on time zone %s";
        public final static List<String> TABLE_SUMMARY_HEADER = Arrays.asList("Event/Market ID", "Settled Date", "Narration", "Debit", "Credit", "Balance");
        public final static List<String> TABLE_DETAIL_HEADER = Arrays.asList("Bet ID", "Selection", "Type", "Odds", "Turnover", "Place Date", "Profit/Loss", "Status");
        public final static String LOAD_REPORT = "Load Report";
        public final static String OPENING_BALANCE = "OPENING BALANCE";
        public final static String MAIN_WALLET_STATEMENT = "Main Wallet Statement";
        public final static List<String> TABLE_SUMMARY_HEADER_FS = Arrays.asList("Sport/Games", "Remarks", "Turnover", "Profit & Loss", "Tax/Commission", "Cash Balance");
        public final static List<String> TABLE_DETAIL_SPORT_HEADER = Arrays.asList("Date", "Turnover", "Profit & Loss", "Tax", "Cash Balance");
        public static final Map<String, String> REQUEST_URL = new HashMap<String, String>() {
            {
                put("satsport", "/member-report-service/report/sat/account-statement");
                put("funsport", "/member-report-service/report/account-statement");
                put("fairenter", "/member-report-service/report/account-statement");
                put("fairexchange", "/member-report-service/report/sat/account-statement");
                put("laser365", "/member-report-service/report/sat/account-statement");

            }
        };

    }

    public static class MiniMyBet {
        public final static String GUIDE_TEXT_MESSAGE1 = "You have no bets on this market.";
        public final static String GUIDE_TEXT_MESSAGE2 = "You can view all your open bets in My Bets.";
    }

    public static class MyBetsPage {
        public final static String BUTTON_CURRENT = "Current";
        public final static String BUTTON_PAST = "Past";
        public final static String BUTTON_UNMATCH = "Unmatched";
        public final static String BUTTON_MATCH = "Matched";
        public final static String LABLE_ALL = "All";
        public final static String LABLE_OPTIONL = "Options";
        public final static List<String> TABLE_HEADER_FS = Arrays.asList("Placed", "Description", "Type", "Odds", "Stake", "Liability", "Potential Profit", "Status");
        public final static List<String> TABLE_SETTLED_HEADER_FS = Arrays.asList("Settled", "Description", "Type", "Odds", "Turnover", "Liability", "Profit/Loss", "Status");
        public final static String START_DATE = "Start Date";
        public final static String ORDER_TYPE = "Order Type";
        public final static String END_DATE = "End Date";
        public final static String NOTES = "Note : Date will be based on time zone %s";
        public final static List<String> TABLE_HEADER = Arrays.asList("Market Name", "Bet ID", "Event ID", "Selection", "Type", "Odds", "Turnover", "Profit/Loss", "Status", "Placed Date", "Settled Date", "IP Address");
        public final static List<String> TABLE_HEADER_EG = Arrays.asList("Game Name", "Bet ID", "Game ID", "Selection", "Type", "Odds", "Stake", "Profit/Loss", "Status", "Placed Date", "IP Address");
        public final static List<String> TABLE_HEADER_CASINO = Arrays.asList("Game Name", "Bet ID", "Round ID", "Selection", "Stake", "Profit/Loss", "Status", "Placed Date");
        public final static String LOAD_REPORT = "Load Report";

        public static final Map<String, String> DDB_PRODUCT_FILTER = new HashMap<String, String>() {
            {
                put("Exchange", "Exchange Bets");
                put("Live Dealer Asian", "Live Dealer Asian Bets");
                put("Lottery & Slots", "Lottery & Slots Bets");
                put("Live Dealer European", "Live Dealer European Bets");
                put("Exchange Games", "Exchange Games Bets");

                put("Supernowa Casino", "Supernowa Casino Bets");
            }
        };
        public static final Map<String, String> DDB_ORDER_TYPE_FILTER = new HashMap<String, String>() {
            {
                put("SETTLED", "Settled");
                put("MATCHED", "Matched");
                put("UNMATCHED", "Unmatched");
                put("CANCELLED", "Cancelled");
                put("VOIDED", "Voided");
                put("LAPSED", "Lapsed");
            }
        };
        public static final String NO_RECORD_FOUND = "No Record Found";
        public static final String YOU_HAVE_NO_BETS_PERIOD = "You have no bets in this time period.";

        public static class MyBetsPageNewView {
            public final static List<String> TABLE_HEADER_SETTLED = Arrays.asList("Market Name", "Selection", "Type", "Odds", "Turnover", "Profit/Loss", "Tax", "Status", "Placed Date", "Settled Date", "Bet ID", "Event ID", "IP Address");
            public final static List<String> TABLE_HEADER_MATCHED = Arrays.asList("Market Name", "Selection", "Type", "Odds", "Stake", "Potential Profit", "Status", "Placed Date", "Bet ID", "Event ID", "IP Address");
            public final static String BTNPLREPORT = "P&L report";
        }
    }

    public static class ProfitAndLossPage {
        public final static String START_DATE = "Start Date";
        public final static String END_DATE = "End Date";
        public final static String NOTES = "Note : Date will be based on time zone IST";
        public final static List<String> TABLE_SUMMARY_HEADER = Arrays.asList("Sport/Game", "Profit/Loss");
        public final static List<String> TABLE_MARKET_HEADER = Arrays.asList("Settled", "Market ID", "Market Name", "Profit/Loss");
        public final static List<String> TABLE_WAGER_HEADER = Arrays.asList("Bet ID", "Placed Date", "Selection", "Matched Odds", "Turnover", "Type", "Status", "Profit/Loss", "Tax", "Net");
        public final static String LOAD_REPORT = "Load Report";
    }

    public static class ChangePasswordPopup {
        public final static String TITLE = "Change Password";
        public final static String LBL_NEW_PASSWORD = "New Password";
        public final static String LBL_CURRENT_PASSWORD = "Current Password";
        public final static String LBL_NEW_PASSWORD_CONFIRMATION = "New Password Confirmation";
        public final static String MSG_FORMAT_PASSWORD = "Please use between 8 and 15 alphanumeric character. Must have at least 1 letter and 1 number";
        public final static String MSG_CONFIRM_PASSWORD_NOT_MATCH = "Password does not match the confirm password";
        public final static String MSG_INCORRECT_OLD_PASSWORD = "Password is not match with old password";
        public final static String MSG_SUCCESS = "Your password has been changed successfully";
        public static final Map<String, String> LBL_CURRENT_PASSWORD_ERROR_MSG = new HashMap<String, String>() {
            {
                put("satsport", "Password is not match with old password");
                put("fairenter", "The password is incorrect. You will be logged out after 4 failed attempts.");
                put("fairexchange", "Password is not match with old password");
                put("funsport", "The password is incorrect. You will be logged out after 4 failed attempts.");
            }
        };
        public static final Map<String, String> LBL_CONFIRM_PASSWORD_ERROR_MSG = new HashMap<String, String>() {
            {
                put("satsport", "Password does not match the confirm password");
                put("fairenter", "Confirm password is not correct");
                put("fairexchange", "Password does not match the confirm password");
                put("funsport", "Confirm password is not correct");
            }
        };
        public static final Map<String, String> LBL_NEW_PASSWORD_ERROR_MSG = new HashMap<String, String>() {
            {
                put("satsport", "Please use between 8 and 15 alphanumeric character. Must have at least 1 letter and 1 number");
                put("fairenter", " Please use between 8 and 15 alphanumeric character.\n" +
                        "Must have at least 1 letter and 1 number.");
                put("fairexchange", "Please use between 8 and 15 alphanumeric character. Must have at least 1 letter and 1 number");
                put("funsport", "The password is incorrect. You will be logged out after 4 failed attempts");
            }
        };
    }

    public static class BetSlip {
        public final static String GUIDE_TEXT_MESSAGE = "Click on the odds to add selections to the Bet Slip.";
        public final static String BET_SLIP = "Bet slip";
        public final static String EDIT_STAKE = "Edit Stake";
        public final static String DRAW = "The Draw";
        public final static String SMG_BET_SLIP_EMPTY = "Click on the odds to add selections to the Bet Slip.";
        public final static String ERROR_SMG_MARKET_NOT_AVAILABLE_TO_PLACE_BET = "Error :This market is not available to place bet!";
        public final static String VALIDATE_STAKE_NOT_VALID = "The stake must be from %s to %s. Current Stake is %s";
        public final static String ERROR_STAKE_NOT_VALID = "Error :Cannot place bet. The stake must be from %s to %s. Current Stake is %s";
        public final static String ERROR_EXCEED_LIABILITY = String.format("Error :Cannot place bet. The total liability exceeds max amount that is allowed per market. Total Exposure Per Market is %.2f. Max Allowed Per Market is %.2f.");
        public final static String ERROR_INSUFFICIENT_BALANCE = "Error :Cannot place bet. Your Main balance is insufficient.";
        public final static String ERROR_INSUFFICIENT_BALANCE_OLD_UI = "Error: Cannot place bet. Your Main balance is insufficient.";
        public final static String ERROR_SELECTION_UNAVAILABLE = "Error :This selection is not available.";
        public final static String ERROR_STAKE_NOT_VALID_MAX_BET = "The stake(s) you have entered are above the maximum.";
        public final static String ERROR_STAKE_MESSAGE_MIN_BET = "The stake(s) you have entered are above the minimum.";
    }

    public static class Footer {
        public final static String TITLE_ABOUT = "About Us";
        public final static String TITLE_PRIVACY_POLICY = "Privacy Policy";
        public final static String TITLE_RULE_REGULATION = "Rules & Regulations";
        public final static String TITLE_TERM_CONDITION = "Terms & Conditions";
        public final static String TITLE_GAMBLING = "Gambling can be addictive, please play responsibly";
        public final static String CONTACT_PHONE_NUM = "+44 7830 357771";
        public final static String CONTACT_PHONE_NUM2 = "+44 7862 140008";
        public final static String CONTACT_TELE = "Satsport_Betting_Website";
        public final static String CONTACT_EMAIL = "info@satsport.com";
        public final static String JIO_CONTACT_EMAIL = "info@jio.exchange";
        public final static String IMG_CONTACT_URL = "%sassets/templates/template2/images/skins/%s/contact.png";
        public final static String IMG_MAIL_URL = "icon-mail";
        public final static String IMG_INSTAGRAM_URL = "instagram_icon";
        public final static String IMG_FACEBOOK_URL = "facebook_icon";
        public final static String IMG_YOUTUBE_URL = "youtube_icon";
        public final static String IMG_TELE_URL = "telegraph_icon";
        public final static String IMG_PHONE_URL = "whatsapp";
        public final static String IMG_BETFAIR_URL = "ju21gofndjkcggoc";
        public final static String CONTACT_INSTA = "satsportofficial";

    }

    public static class CashManagement {
        public static final String DEPOSIT_TAB = "DEPOSIT";
        public static final String WITHDRAWAL_TAB = "WITHDRAWAL";
        public static final String TRANSACTION_HISTORY_TAB = "TRANSACTION HISTORY";

        public final static String LBL_BANK_TRANSFER = "BANK TRANSFER";
        public final static String LBL_PAYTM = "PAYTM";
        public final static String LBL_PHONEPE = "PHONEPE";
        public final static String LBL_GPAY = "GPAY";
        public final static String LBL_UPI = "UPI";
        public final static String LBL_QR_CODE = "QR CODE";

        public static final Map<String, String> DEPOSIT_SUCCESS_HEADER_BRAND = new HashMap<String, String>() {
            {
                put("satsport", "SAT Sport");
                put("fairexchange", "Fairexchange");

            }
        };

    }
}
