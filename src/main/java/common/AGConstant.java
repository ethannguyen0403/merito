package common;

import java.util.*;

import static baseTest.BaseCaseTest._brandname;

public class AGConstant {
    public final static String SPORT_CRICKET = "Cricket";
    public final static String SPORT_SOCCER = "Soccer";
    public final static String SPORT_TENNIS = "Tennis";
    public final static String HORSE_RACING = "Horse Racing";
    public final static String NO_RECORD_FOUND = "No records found.";
    public final static String NO_RESULT_FOUND = "No results found";
    public final static String timeZone = "GMT-4";
    public final static String EXCHANGE = "Exchange";
    public final static String EXCHANGE_GAMES = "Exchange Games";
    public final static String PS38 = "PS38";
    public final static String CASHED_OUT = "Cashed out";
    public final static String SUPERNOWA_CASINO = "Supernowa Casino";
    public final static String EVOLUTION = "Evolution";
    public final static String LIVE_DEALER_ASIAN = "Live Dealer Asian";
    public final static String LIVE_DEALER_EUROPEAN = "Live Dealer European";
    public final static String LOTTERY_SLOT = "Lottery & Slots";
    public final static String GAME_HALL = "Game Hall";
    public final static String VIVO = "Vivo";
    public final static String ION = "ION";
    public final static String PRAGMATIC = "Pragmatic";
    public final static String CMD_SPORTSBOOK = "CMD Sportsbook";
    public final static String BTN_SUBMIT = "Submit";
    public final static String SETTINGS = "Settings";
    public final static String LOGIN = "Login";
    public final static String BALANCE = "Balance";
    public final static String BTN_CANCEL = "Cancel";
    public final static String BTN_UPDATE = "Update";
    public final static String LBL_USERNAME = "User Name";
    public final static String PASSWORD = "Password";
    public final static String SECURITY_CODE = "Security code";
    public final static String LOGIN_ID = "Login ID";
    public final static String ACCOUNT_STATUS = "Account Status";
    public final static String LEVEL = "Level";
    public final static String GENERAL = "General";
    public final static String LBL_USERNAME_PLACE_HOLDER = "Username or Nickname";
    public final static String LBL_USERNAME_PLACE_HOLDER_SAT = "Username or Login ID";
    public final static List<String> MENULIST = Arrays.asList("Nickname", "Password", "Security code", "OTP");
    public final static List<String> MENULISTSAT = Arrays.asList("Password", "Security code", "OTP");
    public final static String LBL_WITHOUT_PERMISSION_ACCESS = "You are not allowed to view this page!";
    public final static String CREDIT_LIMIT_ERROR_MSG = "Credit Limit is invalid.";
    public final static String ALLOW_CASH_OUT = "Allow Cash Out";
    public static final Map<String, String> LEVEL_TO_LEVEL_CODE = new HashMap<String, String>() {
        {
            put("Portal", "PO");
            put("Company", "CO");
            put("Partner", "PART");
            put("Senior Admin", "SAD");
            put("Admin", "AD");
            put("Senior Master Agent", "SMA");
            put("Master Agent", "MA");
            put("Member", "Member");
        }
    };

    public static final Map<String, String> AG_LEVEL_NAMING_BRANDS = new HashMap<String, String>() {
        {
            switch (_brandname) {
                case "satsport":
                    put("PO", "Portal");
                    put("CO", "Company");
                    put("PART", "Partner");
                    put("CORP", "Senior Admin");
                    put("SMA", "Admin");
                    put("MA", "Senior Master Agent");
                    put("AG", "Master Agent");
                    put("PL", "Member");
                    break;
                default:
                    put("PO", "Portal");
                    put("CO", "Company");
                    put("PART", "Partner");
                    put("CORP", "Corporator");
                    put("SMA", "Senior Master Agent");
                    put("MA", "Master Agent");
                    put("AG", "Agent");
                    put("PL", "Member");
            }

        }
    };
    public final static List<String> LST_EXCHANGE_SPORT_HEADER = Arrays.asList("Soccer", "Cricket", "Tennis", "Basketball", "Horse Racing", "Greyhound Racing", "Others");
    public static final String MEMBER = "Member";
    public static final String AGENT = "Agent";
    public static final String ALL = "All";

    public static class LoginPage {
        public final static String LOGIN = "LOGIN";

    }

    public static class SecurityCodePage {
        public final static String TITLE = "Update Security Code";
        public final static List<String> LST_LABELS = Arrays.asList("Old Security Code", "Security Code", "Confirm Security Code");
        public final static String LBL_GUIDE = "Security Code (SC) must be:\n" +
                "*Numeric digits only.\n" +
                "*6 digits from 0-9.\n" +
                "*At least 2 different digits in the SC.\n" +
                "*Consecutive are not allowed, for example: 123456, 456789.\n" +
                "*Back consecutive are not allowed, for example: 765432.";
    }

    public static class HomePage {
        public final static String WELCOME_BACK = "WELCOME BACK";
        public final static String CONFIGURE_OTP = "CONFIGURE OTP";
        public final static String CONFIGURE_NICKNAME = "CONFIGURE NICKNAME";
        public final static String MAIN_MENU = "Main Menu";
        public final static String QUICK_SEARCH = "Quick Search";
        public final static String SIGN_OUT = "Sign Out";
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



        public final static List<String> MENULIST = Arrays.asList("Password", "Security code", "OTP");
        public final static List<String> MENULIST_SMA_NEWUI = Arrays.asList("Login ID","Password", "Security code", "OTP");
        public final static String AGENCY_MANAGEMENT = "Agency Management";
        public final static String RISK_MANAGEMENT = "Risk Management";
        public final static String REPORT = "Report";
        public final static String MARKET_MANAGEMENT = "Bets/Markets Management";
        public final static String DOWNLINE_LISTING = "Downline Listing";
        public final static String GROUP_LIST = "Group Listing";
        public final static String CREATE_DOWNLINE_AGENT = "Create Downline Agent";
        public final static String CREATE_USER = "Create User";
        public final static String CREATE_COMPANY = "Create Company";
        public final static String PROFIT_LOSS = "Profit & Loss";
        public final static String PROFIT_AND_LOSS = "Profit And Loss";
        public final static String LIQUIDITY_THRESHOLD = "Liquidity Threshold";
        public final static String BF_VOIDED_DISCREAPANCY = "BF Voided Discrepancy";
        public final static String CLIENT_LEDGER = "Client Ledger";
        public final static String FOLLOW_BETS = "Follow Bets";
        public final static String DEPOSIT_WITHDRAW = "Deposit/Withdraw";
        public final static String TAX_SETTING_LISTING = "Tax Settings";
        public final static String RISK_SETTING_LISTING = "Risk Setting Listing";
        public final static String POSITION_TAKING_LISTING = "Position Taking Listing";
        public final static String ANNOUNCEMENT = "Announcement";
        public final static String FRAUD_DETECTION = "Fraud Detection";
        public final static String FRAUD_PERMISSION = "Fraud Permission";
        public final static String WAGER_ODDS_HISTORY = "Wager Odds History";
        public final static String BET_SETTING_LISTING = "Bet Setting Listing";
        public final static String COMMISSION_LISTING = "Commission Listing";
        public final static String CREDIT_BALANCE_LISTING = "Credit/Balance Listing";
        public final static String DEPOSIT_WITHDRAWAL = "Deposit/Withdraw";
        public final static String BLOCK_UNBLOCK_EVENT = "Block/Unblock Events";
        public final static String BLOCK_UNBLOCK_COMPETITION = "Block/Unblock Competitions";
        public final static String CURRENT_BLOCKING = "Current Blocking";
        public final static String BLOCKING_LOG = "Blocking Log";
        public final static String BLOCK_RACING = "Block Racing";
        public final static String SUSPEND_UNSUSPEND_MARKETS = "Suspend/Unsuspend Markets";
        public final static String VOLUME_MONITOR = "Volume Monitor";
        public final static String NET_EXPOSURE = "Net Exposure";
        public final static String AGENT_EXPOSURE_LIMIT = "Agent Exposure Limit";
        public final static String BIG_STAKE_CONFIGURATION = "Big Stake Configuration";
        public final static String CANCELLED_BETS = "Cancelled Bets";
        public final static String FOLLOW_BETS_PERFORMANCE = "Follow Bets Performance";
        public final static String PORTAL_SUMMARY = "Portal Summary";
        public final static String POSITION_TAKING_REPORT = "Position Taking Report";
        public final static String STATEMENT_REPORT = "Statement Report";
        public final static String UNSETTLED_BET = "Unsettled Bet";
        public final static String TRANSACTION_HISTORY = "Transaction History";
        public final static String TRANSFER_LOG = "Transfer Log";
        public final static String RESETTLEMENT_VOID_LOG = "Resettlement & Void Log";
        public final static String TRANSFER = "Transfer";
        public final static String TOP_GAINER_TOP_LOSER = "Top Gainers & Top Losers";
        public final static String VIEW_LOG = "View Log";
        public final static String WIN_LOSS_BY_EVENT_OLDUI = "Win Loss By Event";
        public final static String WIN_LOSS_BY_MARKET_TYPE_OLDUI = "Win Loss By Sport And Market Type";
        public final static String WIN_LOSS_BY_DETAIL_OLDUI = "%s Win Loss Detail";
        public final static String WIN_LOSS_SIMPLE_OLDUI = "Win Loss Simple";
        public final static String WIN_LOSS_BY_EVENT_NEWUI = "By Event";
        public final static String WIN_LOSS_BY_MARKET_TYPE_NEWUI = "By Market";
        public final static String WIN_LOSS_BY_DETAIL_NEWUI = "%s By Detail";
        public final static String WIN_LOSS_NEWUI = "Win Loss";
        public final static String WIN_LOSS_SIMPLE_NEWUI = "Simple";
        public final static String WIN_LOSS_ANALYSIS_NEWUI = "Win Loss Analysis";
        public final static String ANALYSIS_OF_RUNNING_MARKETS = "Analysis of Running Markets";
        public final static String IP_MONITORING = "IP Monitoring";
        public final static String MONITORED_ACCOUNT = "Monitored Accounts";
        public final static String SUB_USER_LISTING = "Sub User Listing";
        public final static String EVENT_BET_STIE_SETTINGS = "Event Bet Size Settings";
        public final static List<String> LST_QUICK_SEARCH_MENU_OLDUI = Arrays.asList("Balance", "Downline Listing", "Profit & Loss", "Client Ledger", "Settings", "Login");
        public final static List<String> LST_QUICK_SEARCH_MEMBER_MENU = Arrays.asList("Balance", "Unsettled Bet", "Client Ledger", "Settings", "Login");
        public final static List<String> LST_QUICK_SEARCH_MENU_NEWUI = Arrays.asList("Balance", "Unsettled Bet", "Client Ledger", "Settings", "Login");
        public final static List<String> LST_QUICK_SEARCH_LOGIN = Arrays.asList("Created Date", "Last Login Time", "Last Login IP");
        public final static List<String> LST_QUICK_SEARCH_SETTING_MENU = Arrays.asList("User Profile", "Product Status", "Position Taking", "Bet Settings", "Tax Settings", "Commission");
        public final static String PS38_SPORTS_RESULTS = "PS38 Sports Result";
        public final static List<String> PRODUCTS_LIST = Arrays.asList("Exchange", "Exchange Games", "Evolution", "Supernowa Casino","CMD Sportsbook","Live Dealer European", "Live Dealer Asian","Pragmatic", "Game Hall",
                "ViVo", "Ion","Sabong");

        public final static String CASH_MANAGEMENT = "Cash Management";
        public final static String DEPOSIT_WITHDRAWAL_TRANSACTION = "Deposit/Withdrawal Transactions";
        public final static String PAYMENT_CHANNEL_MANAGEMENT = "Payment Channel Management";
        public final static String QUICK_DEPOSIT_CONFIG = "Quick Deposit Configuration";

        public static class AccountBalance {
            public final static List<String> TITLE_LST = Arrays.asList("Downline Balance","Yesterday Downline Balance","Total Balance","Transferable Balance","My Credit","My Outstanding","Total Outstanding","Today Win Loss","Yesterday Win Loss",
                    "Total MA Credit Used","Total AG Credit Used","Total Member Credit Used","Total MA Active/Closed/Suspended/Inactive/Blocked","Total AG Active/Closed/Suspended/Inactive/Blocked","Total Member Active/Closed/Suspended/Inactive");
            public final static List<String> OLDUI_TITLE_LST = Arrays.asList("Available Balance","My Outstanding","Total Outstanding","Today Win Loss","Yesterday Win Loss","Total AD Available Balance","Total SMA Available Balance","Total MA Available Balance","Total Member Available Balance",
                    "Total AD Active/Closed/Suspended/Inactive/Blocked","Total SMA Active/Closed/Suspended/Inactive/Blocked","Total MA Active/Closed/Suspended/Inactive/Blocked","Total Member Active/Closed/Suspended/Inactive");
        }
    }


    public static class CashManagement {
        public static class DepositWithdrawalTransaction {
            public final static String LBL_PAGE_TITLE = "Deposit/Withdrawal Transaction";
            public final static List<String> TBL_DEPOSIT_WITHDRAWAL_HEADER = Arrays.asList("No.", "Username", "Login ID", "Internal Ref No", "Payment Type", "Currency", "Deposit Amount", "Created Date", "Updated Date", "Status", "Action");
            public final static List<String> LST_TRANSACTION_STATUS = Arrays.asList("All", "Pending", "Approved", "Rejected");
            public final static List<String> LST_PAYMENT_TYPE = Arrays.asList("All", "BANK TRANSFER", "PAYTM", "PHONEPE", "GPAY", "UPI", "QR Code");
            public final static List<String> ACTION_LST = Arrays.asList("Approve", "Reject");
            public final static List<String> TRANSACTION_DETAIL_ACTION_LST = Arrays.asList("Review", "Details");
        }

        public static class PaymentChannelManagement {
            public final static List<String> TBL_HEADER_HISTORY_POPUP = Arrays.asList("No.", "Actions", "Last Updated Time", "Last Updated By");
        }
    }
    public static class SubUserListing {
        public final static String Create_Sub_User = "";
        public final static List<String> TBL_SUB_USER_TABLE_NONPO = Arrays.asList("No.", "Username", "Edit", "Status", "First Name", "Last Name", "Permissions", "Create Account", "Update Account", "View Account", "Report", "Transfer & Deposit/Withdraw", "Account Balance", "Markets Management");
        public final static List<String> PERMISSION_LIST = Arrays.asList("Create Account", "View Account", "Transfer & Deposit/Withdraw", "Markets Management", "Update Account",
                "Report", "Account Balance", "Fraud Detection");
        public final static List<String> PERMISSION_LIST_SAD = Arrays.asList("Create Account", "Update Account", "View Account", "Report", "Transfer & Deposit/Withdraw", "Account Balance", "Markets Management", "Fraud Detection");
        public final static List<String> CREATE_SUB_USER_FORM = Arrays.asList("Username", "Password", "Status", "First Name", "Last Name");
    }

    public static class Announcement {
        public final static String INFO = "From date and To date are displayed in IST (GMT+5:30)";
        public final static String ADD_ANNOUNCEMENT = "Add Announcement";
    }

    public static class ChangePasswordPopup {
        public final static List<String> LST_LABELS = Arrays.asList("Username", "Current Password:", "New Password:", "Confirm Password:");
    }

    public static class AgencyManagement {
        public final static String LBL_LOGINID = "Login ID";
        public final static String LBL_ACCOUNTSTATUS = "Account Status";
        public final static List<String>
                LIST_SUBMENU_AGENCY_MANAGEMENT_OLD = Arrays.asList("Create Downline Agent","Create User", "Downline Listing", "Event Bet Size Settings", "Position Taking Listing", "Deposit/Withdraw",
                "Commission Listing", "Bet Setting Listing", "Tax Settings", "Sub User Listing", "Announcement");
        public final static List<String>
                LIST_SUBMENU_AGENCY_MANAGEMENT_NEW = Arrays.asList("Create Downline Agent", "Create User", "Downline Listing", "Position Taking Listing", "Transfer", "Statement Report", "Client Ledger", "Credit/Balance Listing", "Commission Listing", "Bet Setting Listing", "Risk Setting Listing", "Tax Settings");
        public final static List<String>
                LIST_SUBMENU_AGENCY_MANAGEMENT_F24 = Arrays.asList("Create Downline Agent", "Create User", "Downline Listing", "Position Taking Listing", "Transfer", "Statement Report", "Client Ledger", "Credit/Balance Listing", "Follow Bets", "Commission Listing", "Bet Setting Listing", "Risk Setting Listing", "Tax Settings");
        public final static List<String>
                LIST_SUBMENU_AGENCY_MANAGEMENT_FUNSPORT = Arrays.asList("Create Downline Agent", "Create User", "Downline Listing", "Position Taking Listing", "Deposit/Withdraw", "Commission Listing", "Bet Setting Listing", "Tax Settings", "Sub User Listing");
        public final static String LBL_LEVEL = "Level";
        public final static String EDIT_DOWNLINE_AGENT_TITLE = "Edit Downline Agent";

        public static class CreateCompany {
            public final static String LBL_ALLOW_CO_EXTRA = "Allow CO Extra PT";
            public final static String LBL_ALLOW_CASHOUT = "Allow Cash Out";
            public final static String LBL_IS_CREDIT_CASH = "Credit Cash";
            public final static String LBL_CURRENCY = "Currency";
            public final static List<String>
                    LIST_SPORTS_PS38_BET_SETTING = Arrays.asList("Soccer", "Baseball", "Basketball", "Football", "E Sports", "Others", "Mix Parlay", "Teaser");
            public final static List<String>
                    LIST_SPORTS_PS38_PT = Arrays.asList("Soccer", "Baseball", "Basketball", "Football", "E Sports", "Others", "Mix Parlay", "Teasers");
            public final static List<String>
                    HEADER_BET_SETTING_PS38 = Arrays.asList("", "Min Bet", "Max Bet", "Max Per Match");
            public final static List<String>
                    HEADER_PREGAME_TABLE = Arrays.asList("Pregame", "Full time", "1st Half", "1X2", "HDP", "OU", "TT", "Others", "Outright", "1X2", "HDP", "OU");
            public final static List<String>
                    HEADER_INPLAY_TABLE = Arrays.asList("Inplay", "Full time", "1st Half", "1X2", "HDP", "OU", "1X2", "HDP", "OU");
            public final static String PREGAME_TAB_PS38 = "Pregame";
            public final static String FULL_TIME = "Full time";
            public final static String FIRST_HALF= "1st Half";
            public final static String INPLAY_TAB_PS38 = "Inplay";
            public final static String CHECKBOX_MESSAGE_PS38_BET_SETTING = "Copy all Limits for all Sports and Leagues for %s ONLY from the sport below";
            public final static String CHECKBOX_MESSAGE_PS38_PT = "Copy all Position Taking from the first betting market for the sport below.";
            public final static String AMOUNT_MIN_BET_PS38_HKD = "1";
            public final static String AMOUNT_MAX_BET_PS38_HKD = "100,000,000";
            public final static String AMOUNT_MAX_PER_MATCH_PS38_HKD = "100,000,000";
            public final static String AMOUNT_MIN_BET_PS38_INR = "9";
            public final static String AMOUNT_MAX_BET_PS38_INR = "881,849,768";
            public final static String AMOUNT_MAX_PER_MATCH_PS38_INR = "881,849,768";
            public final static String SWITCH_TAB_MESSAGE_PS38 = "You have unsaved changes on this tab. Switching tabs will result in losing your changes. Do you wish to switch tabs?";
        }
        public static class CreateAccount {
            public final static String LBL_LOGIN_ID = "Login ID";
            public final static String LBL_PASSWORD = "Password";
            public final static String LBL_ACCOUNT_STATUS = "Account Status";
            public final static String LBL_LEVEL = "Level";
            public final static String LBL_FIRST_NAME = "First Name";
            public final static String LBL_LAST_NAME = "Last Name";
            public final static String LBL_PHONE = "Phone";
            public final static String LBL_MOBILE = "Mobile";
            public final static String LBL_EMAIL = "Email";
            public final static String LBL_FAX = "Fax";
            public final static String LBL_BASE_CURRENCY = "Base Currency";
            public final static String LBL_ALLOW_PARTIAL_TRANSFER = "Allow Partial Transfer";
            public final static String LBL_ALLOW_AG_EXTRA = "Allow AG Extra PT";
            public final static String LBL_ALLOW_PL_EXTRA = "Allow PL Extra PT";
            public final static String LBL_INITIATION_DEPOSIT = "Initiation Deposit";
            public final static String LBL_CASH_BALANCE = "Cash Balance";
            public final static String LBL_CREDIT_BALANCE = "Credit Balance";
            public final static String LBL_CREDIT_LIMIT = "Credit Limit";
            public final static String LBL_SMA_BALANCE = "SMA Max Credit";
            public final static String LBL_MEMBER_MAX_CREDIT = "Member Max Credit";
            public final static String LBL_RISK_SETTING = "Risk Setting";
            public final static String LBL_MAX_EXPOSURE = "Max Exposure";
            public final static String LBL_MAX_EXPOSURE_HINT = "0 means unlimited.";
            public final static String LBL_CREDIT_INITIATION = "Credit Initiation";
            public final static String LBL_FIRST_TIME_DEPOSIT = "First Time Deposit";
            public final static String LBL_MAX_PLAYER_CREDIT = "Max Player Credit";
            public final static String LBL_RATE_SETTING = "Rate Setting";
            public final static String LBL_RATE = "Rate";
            public final static String LBL_PRODUCT_SETTING = "Product Settings";
            public final static String LBL_BET_SETTING = "Bet Settings";
            public final static String LBL_TAX_SETTING = "Tax Settings";
            public final static String LBL_COMMISSION_SETTING = "Commission";
            public final static String LBL_POSITION_TAKING = "Position Taking";
            public final static String LBL_ACCOUNT_BALANCE_TRANSFER_CONDITION = "Account Balance Transfer Condition";

            public final static List<String> LST_TAX_SETTING_HEADER_NEWUI = Arrays.asList("", "Soccer", "Cricket", "Tennis", "Basketball",  "Fancy", "Virtual Cricket", "Decimal Cricket", "Other");
            public final static List<String> LST_TAX_SETTING_HEADER_OLDUI = Arrays.asList("", "Soccer", "Cricket", "Tennis", "Basketball", "Virtual Cricket", "Decimal Cricket", "Other");
            public final static List<String> LST_BET_SETTING_HEADER = Arrays.asList("", "Soccer", "Cricket","Fancy", "Virtual Cricket", "Bookmaker", "Decimal Cricket", "Tennis", "Basketball", "Other");
            public final static List<String> LST_BET_SETTING_OPTION = Arrays.asList("Min Bet", "Max Bet", "Max Liability Per Market", "Max Win Per Market");
            public final static List<String> LST_TAX_SETTING_OPTION = Arrays.asList("Upline", "Tax");
            public final static List<String> LST_POSITION_TAKING_SPORT_HEADER = Arrays.asList("Soccer", "Cricket", "Line Market", "Fancy", "Virtual Cricket", "Bookmaker", "Decimal Cricket", "Tennis", "Basketball", "Horse Racing", "Greyhound Racing", "Other");
            //            public final static List<String> LST_POSITION_TAKING_HEADER_OLD_UI = Arrays.asList("", "Soccer", "Cricket", "Fancy", "Tennis", "Basketball", "Racing", "Other");
            public final static List<String> LST_EG_GAME_GROUP_HEADER = Arrays.asList("", "Exchange Baccarat", "Exchange BlackJack", "Exchange Card Racer", "Exchange Hi Lo", "Exchange Texas Hold'em", "Exchange Omaha Hi", "Other");
            public final static String LBL_LOGINID_HINT = "Login ID must be unique and at least a minimum of 6 characters and maximum of 15 characters";
            public final static String LBL_PASSWORD_HINT = "New Password: \n" +
                    " 1. Should be between 8 to 15 characters.  \n" +
                    " 2. Alphanumeric characters and special characters are allowed.  \n" +
                    " 3. Should contains at least 1 letter and 1 number.";
            public final static List<String> LST_ACCOUNTS_STATUS_CREATE = Arrays.asList("Active", "Inactive");
        }

        public static class CreateDownlineAgent {
            public final static String TITLE_PAGE = "Create Downline Agent";

            public final static String CREATE_USER_SUCCEESS_MESSAGE = "Downline was created successfully";
            public final static String LBL_LOGINID_INVALID = "Login ID is invalid.";
            public final static String LBL_LOGINID_EXIST = "Login ID already exist.";
            public final static String LBL_PASSWORD_INVALID = "Password is invalid";
            public final static String LBL_MIN_INVALID = "Min Bet is invalid.";
            public final static String LBL_MAX_INVALID = "Max Bet is invalid.";
            public final static String LBL_BALANCE_DEPOSIT_INVALID = "Max Bet is invalid.";
            public final static String LBL_ACCOUNT_TRANSFER_WEEKLY_INVALID = "Please select at least one day when Account balance transfer is Weekly.";

        }

        public static class DownlineListing {
            public final static String EDIT_AGENT_TITLE = "Edit Downline Agent";
            public final static String EDIT_USER_TITLE = "Edit User";
            public final static String EDIT_MARKET_TITLE = "Edit market for %s";
            public final static List<String> LST_ACCOUNT_STATUS = Arrays.asList("All", "Active", "Inactive", "Suspended", "Closed", "Blocked");
            public final static List<String> LST_DOWLINE_LISTING_TABLE_HEADER_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Client Name", "Mobile", "Credit Initiation", "Account Status", "Edit", "Change Password", "Level", "Delay Bet", "Downline", "Create Date", "Last Login Time", "Last Login IP");
            public final static List<String> LST_DOWLINE_LISTING_TABLE_HEADER_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "Edit", "Change Password", "Level", "Delay Bet", "Exchange", "Live Dealer European", "Live Dealer Asian", "Exchange Games", "Supernowa Casino", "Evolution", "RWB Sports", "Game Hall", "Vivo", "ION");
            public final static String MSG_CHANGE_PASSWORD_SUCCESS = "Update is successful!";
            public final static String MSG_INVALID_MAX_PLAYER_CREDIT_FAIR = "Member Max Credit is invalid.";
            public final static String MSG_INVALID_MAX_PLAYER_CREDIT_SAT = "Player Max Credit Invalid.";
            public final static String MSG_EDIT_DOWNLINE_SUCCESS = "Downline was updated successfully";
            public final static String MSG_EDIT_MEMBER_SUCCESS = "Member was updated successfully";
            public final static String MSG_ALL_PRODUCT_NOT_SELECT = "There has to be at least one active product.";
        }

        public static class CreateUser {
            public final static String TITLE_PAGE = "Create User";
            public final static String CREATE_USER_SUCCEESS_MESSAGE = "Downline was created successfully";
            public final static String MEMBER_CREATE_SUCCEESS_MESSAGE = "Member was created successfully";
            public final static String LBL_LOGINID_INVALID = "Login ID is invalid.";
            public final static String LBL_LOGINID_EXIST = "Login ID already exist.";
            public final static String LBL_PASSWORD_INVALID = "Password is invalid";
            public final static String LBL_PASSWORD_REQUIRED = "Password is required.";
            public final static String LBL_LOGINID_REQUIRED = "Login ID is required.";
            public final static String LBL_MIN_INVALID = "Min Bet is invalid.";
            public final static String LBL_MAX_INVALID = "Max Bet is invalid.";
            public final static String LBL_MAX_LIABILITY_INVALID = "Max Liability Per Market is invalid.";
            public final static String LBL_MAX_WIN_INVALID = "Max Win Per Market is invalid.";
            public final static String LBL_BALANCE_DEPOSIT_INVALID = "Balance Deposit is invalid.";
            public final static String LBL_MIN_GREATER_MAX_INVALID = "Min bet shall not be greater than max bet.";
            public final static String CASH_BALANCE = "Cash Balance";
            public final static String RATE_SETTING = "Rate Setting";
            public final static String PRODUCT_SETTINGS = "Product Settings";
            public final static String BET_SETTING = "Bet Settings";
            public final static String TAX_SETTING = "Tax Settings";
            public final static String POSITION_TAKING_SETTING = "Position Taking";
            public final static List<String> LST_PRODUCTS = Arrays.asList("Exchange", "CMD Sportsbook", "Supernowa Casino", "Exchange Games", "Live Dealer European", "Live Dealer Asian", "Evolution", "Pragmatic", "Game Hall", "ViVo", "Ion", "Sabong");
            public final static List<String> EX_BET_SETTING_HEADER = Arrays.asList("", "Soccer", "Cricket", "Fancy" ,"Virtual Cricket", "Bookmaker", "Decimal Cricket","Tennis", "Basketball", "Other");
            public final static List<String> EX_TAX_SETTING_HEADER_OLDUI = Arrays.asList("", "Soccer", "Cricket", "Tennis", "Basketball", "Virtual Cricket","Decimal Cricket","Other");
            public final static List<String> EX_POSITION_SETTING_HEADER = Arrays.asList("", "Soccer", "Cricket", "Fancy", "Tennis", "Basketball", "Horse Racing", "Greyhound Racing", "Other");
            public final static List<String> BET_SETTING_COLUMN = Arrays.asList("Min Bet", "Max Bet", "Max Liability Per Market", "Max Win Per Market");
            public final static List<String> EG_BET_TAX_PT_SETTING_HEADER_OLDUI = Arrays.asList("", "Exchange Baccarat", "Exchange BlackJack", "Exchange Card Racer", "Exchange Hi Lo", "Exchange Texas Hold'em", "Exchange Omaha Hi", "Other");
            public final static List<String> EG_BET_TAX_PT_SETTING_HEADER_NEWUI = Arrays.asList("", "Baccarat", "Blackjack", "Racing", "Hilo", "Hold'em", "Omaha Hi", "Other");
            public final static List<String> EVOLUTION_BET_PT_SETTING_HEADER = Arrays.asList("", "Premium games", "Other games");
            public final static List<String> GAME_HALL_PT_SETTING_HEADER = Arrays.asList("", "AWS", "BG", "E1SPORT", "FASTSPIN", "FC", "HORSEBOOK", "JDB", "JDBFISH", "JILI", "KINGMAKER", "LUCKYPOKER", "LUDO",
                    "PG", "PP", "PT%", "RT", "SABA", "SEXYBCRT", "SPADE", "SV388", "VENUS", "VRLOTTO", "YESBINGO", "YL");
            public final static List<String> TAX_SETTING_COLUMN = Arrays.asList("Upline", "Tax");


        }

        public static class DepositWithdrawal {
            public final static String TITLE_PAGE = "Deposit/Withdraw";
            public final static String LBL_AVAILABLE_BALANCE = "Available Balance";
            public final static List<String> DDB_ACCOUNT_STATUS = Arrays.asList("All", "Active", "Inactive", "Suspended", "Closed", "Blocked");
            public final static List<String> TABLE_ACCOUNT_BALANCE_HEADER = Arrays.asList("My Credit", "Total Balance", "Sub Balance", "Available Balance");
            public final static List<String> DDB_LEVEL = Arrays.asList("All", "Agent", "Member");
            public final static Map<String, List<String>> TABLE_HEADER_MAP = new HashMap() {
                {
                    put("fairexchange", Arrays.asList("No.", "User Name", "Nickname", "Client Name", "", "Account Status\n" +
                                    "All\n" +
                                    "Active\n" +
                                    "Inactive\n" +
                                    "Suspended\n" +
                                    "Closed\n" +
                                    "Blocked"
                            , "Level\n" +
                                    "All\n" +
                                    "Agent\n" +
                                    "Member", "Currency", "Total Players Outstanding", "Retain Amount", "Available Balance",
                            "Transfer", "Update Status"));
                    put("satsport", Arrays.asList("No.", "User Name", "Login ID", "Client Name", "", "Account Status\n" +
                                    "All\n" +
                                    "Active\n" +
                                    "Inactive\n" +
                                    "Suspended\n" +
                                    "Closed\n" +
                                    "Blocked"
                            , "Level\n" +
                                    "All\n" +
                                    "Agent\n" +
                                    "Member", "Credit Initiation", "Balance", "Winloss", "Exposure", "Transfer", "Log"));
                }
            };
            public final static List<String> TABLE_HEADER_DOWNLINE = Arrays.asList("No.", "User Name", "Nickname", "Client Name", "Account Status",
                    "Level", "Currency", "Total Players Outstanding", "Retain Amount", "Available Balance");

            public final static String USERNAME_NICKNAME = "Username or Login ID";
            public final static String LBL_ACCOUNT_STATUS = "Account Status\n" +
                    "All\n" +
                    "Active\n" +
                    "Inactive\n" +
                    "Suspended\n" +
                    "Closed\n" +
                    "Blocked";
            public final static String LBL_LEVEL = "Level\n" +
                    "All\n" +
                    "Agent\n" +
                    "Member";
            public final static List<String> TABLE_HEADER_SAT =
                    Arrays.asList("No.", "User Name", "Login ID", "Client Name", "", "Account Status\n" +
                                    "All\n" +
                                    "Active\n" +
                                    "Inactive\n" +
                                    "Suspended\n" +
                                    "Closed\n" +
                                    "Blocked"
                            , "Level\n" +
                                    "All\n" +
                                    "Agent\n" +
                                    "Member", "Credit Initiation", "Balance", "Winloss", "Exposure",
                            "Transfer", "Log", "Update Status");
            public final static List<String> TABLE_HEADER_SAT_DRILLDOWN =
                    Arrays.asList("No.", "User Name", "Login ID", "Client Name", "", "Account Status\n" +
                                    "All\n" +
                                    "Active\n" +
                                    "Inactive\n" +
                                    "Suspended\n" +
                                    "Closed\n" +
                                    "Blocked"
                            , "Level\n" +
                                    "All\n" +
                                    "Agent\n" +
                                    "Member", "Credit Initiation", "Balance", "Winloss", "Exposure",
                            "Transfer", "Log");
            // Withdrawal popup
            public final static String WITHDRAWAL_TITLE = "Withdraw from %s";
            public final static String WITHDRAWAL_SUCCESSFUL = "Withdraw is successful";
            public final static String WITHDRAWAL = "Withdraw";

            // Deposit popup
            public final static String DEPOSIT = "Deposit";
            public final static String DEPOSIT_TITLE = "Deposit to %s";
            public final static String DEPOSIT_ERROR_AMOUNT = "Amount must be positive decimal with maximum two places and greater than zero";
            public final static String DEPOSIT_GREATER_MAX_PLAYER_CREDIT = "New Player Credit Balance has to be less than or equal to Max Player Credit. Current Player Credit Balance is %s";
            public final static String DEPOSIT_ERROR_INSUFFICIENT = "%s is insufficient";
            public final static String DEPOSIT_SUCCESSFUL = "Deposit is successful";

            // View Log popup
            public final static String VIEWLOG = "View Log";
            public final static String VIEWLOG_TITLE = "Deposit Withdrawal Log - %s";
            public final static List<String> TABLE_VIEW_LOG_HEADER = Arrays.asList("Date Time", "Actions", "Amount", "Remark", "Perform By");
            public final static String BTL_CLOSE = "Close";
        }

        public static class PositionTakingListing {
            public final static String TITLE_PAGE = "Position Taking Listing";
            public final static List<String> TABLE_PT_EXCHANGE_HEADER_FAIRENTER = Arrays.asList("No.", "Username", "Login ID", "Account Status", "VAM", "", "Level", "Customised PT", "Settings", "Soccer", "Cricket", "Line Market", "Fancy", "Virtual Cricket", "Bookmaker", "Decimal Cricket", "Tennis", "Basketball", "Horse Racing", "Greyhound Racing", "Other", "Update Status");
            public final static List<String> TABLE_PT_EXCHANGE_HEADER_NEWUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Customised PT", "Settings", "Soccer", "Cricket", "Line Market", "Fancy", "Virtual Cricket", "Bookmaker", "Decimal Cricket", "Tennis", "Basketball", "Horse Racing", "Greyhound Racing", "Other", "Update Status");
            public final static List<String> TABLE_PT_EXCHANGE_HEADER_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Customised PT", "Settings", "Soccer", "Cricket", "Line Market", "Fancy", "Virtual Cricket", "Bookmaker", "Decimal Cricket", "Tennis", "Basketball", "Horse Racing", "Greyhound Racing", "Other", "Update Status");
            public final static List<String> TABLE_PT_EXCHANGE_GAME_HEADER_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "Baccarat", "Blackjack", "Racing", "Hilo", "Hold'em", "Omaha Hi", "Other", "Update Status");
            public final static List<String> TABLE_PT_EXCHANGE_GAME_HEADER_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "Baccarat", "Blackjack", "Racing", "Hilo", "Hold'em", "Omaha Hi", "Other", "Update Status");
            public final static List<String> TABLE_PT_LOTTERY_SLOT_HEADER_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "Lottery & Slots", "Update Status");
            public final static List<String> TABLE_PT_LOTTERY_SLOT_HEADER_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "Lottery & Slots", "Update Status");
            public final static List<String> TABLE_PT_LIVE_DEALER_ASIA_HEADER_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "Live Dealer Asian", "Update Status");
            public final static List<String> TABLE_PT_LIVE_DEALER_ASIA_HEADER_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "Live Dealer Asian", "Update Status");
            public final static List<String> TABLE_PT_LIVE_DEALER_EUROPEAN_HEADER_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "Live Dealer European", "Update Status");
            public final static List<String> TABLE_PT_LIVE_DEALER_EUROPEAN_HEADER_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "Live Dealer European", "Update Status");
            public final static List<String> TABLE_PT_EVOLUTION_HEADER_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "Premium games", "Other games", "Update Status");
            public final static List<String> TABLE_PT_EVOLUTION_HEADER_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "Premium games", "Other games", "Update Status");
            public final static List<String> TABLE_PT_SUPERNOWA_HEADER_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "Supernowa Casino", "Update Status");
            public final static List<String> TABLE_PT_SUPERNOWA_HEADER_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "Supernowa Casino", "Update Status");
            public final static List<String> TABLE_PT_GAME_HALL_HEADER_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "AWS", "BG", "E1SPORT", "FASTSPIN", "FC", "HORSEBOOK", "JDB", "JDBFISH", "JILI", "KINGMAKER", "LUCKYPOKER", "LUDO",
                    "PG", "PP", "PT%", "RT", "SABA", "SEXYBCRT", "SPADE", "SV388", "VENUS", "VRLOTTO", "YESBINGO", "YL", "Update Status");
            public final static List<String> TABLE_PT_GAME_HALL_HEADER_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "AWS", "BG", "E1SPORT", "FASTSPIN", "FC", "HORSEBOOK", "JDB", "JDBFISH", "JILI", "KINGMAKER", "LUCKYPOKER", "LUDO",
                    "PG", "PP", "PT%", "RT", "SABA", "SEXYBCRT", "SPADE", "SV388", "VENUS", "VRLOTTO", "YESBINGO", "YL", "Update Status");
            public final static List<String> TABLE_PT_VIVO_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "Vivo", "Update Status");
            public final static List<String> TABLE_PT_VIVO_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "Vivo", "Update Status");
            public final static List<String> TABLE_PT_ION_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "ION", "Update Status");
            public final static List<String> TABLE_PT_ION_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "ION", "Update Status");
            public final static List<String> TABLE_PT_PRAGMATIC_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "Pragmatic", "Update Status");
            public final static List<String> TABLE_PT_PRAGMATIC_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "Pragmatic", "Update Status");
            public final static List<String> TABLE_PT_CMD_SPORTSBOOK_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "CMD Sportsbook", "Update Status");
            public final static List<String> TABLE_PT_CMD_SPORTSBOOK_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "CMD Sportsbook", "Update Status");

            public final static List<String> TABLE_PT_RWB_SPORTSBOOK_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "RWB Sports", "Update Status");
            public final static List<String> TABLE_PT_RWB_SPORTSBOOK_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "RWB Sports", "Update Status");
            public final static List<String> TABLE_PT_PS38_SPORTSBOOK_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "Esports", "Non-esports", "Update Status");
            public final static List<String> TABLE_PT_PS38_SPORTSBOOK_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "Esports", "Non-esports", "Update Status");
            public final static List<String> TABLE_PT_PS38_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Customised PT", "Settings", "Soccer General", "Soccer General (InPlay)", "Baseball General","Baseball General (InPlay)",
                    "Basketball General","Basketball General (InPlay)","Football General","Football General (InPlay)","E Sports General","E Sports General (InPlay)","Others General","Others General (InPlay)","Mix Parlay General","Teasers General","Update Status",
                    "OU","ML","HDP","OU","TT","ML","HDP","OU","ML");
            public final static List<String> TABLE_PT_PS38_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Customised PT", "Settings", "Soccer General", "Soccer General (InPlay)", "Baseball General","Baseball General (InPlay)",
                    "Basketball General","Basketball General (InPlay)","Football General","Football General (InPlay)","E Sports General","E Sports General (InPlay)","Others General","Others General (InPlay)","Mix Parlay General","Teasers General","Update Status",
                    "OU","ML","HDP","OU","TT","ML","HDP","OU","ML");
            public final static List<String> TABLE_SELECTION_PT = Arrays.asList("Min Pos", "Max Pos", "%s Preset", "%s Extra PT");
            public final static List<String> TABLE_PT_QTECH_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "Q-tech", "Update Status");
            public final static List<String> TABLE_PT_QTECH_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "Q-tech",  "Update Status");
            public final static List<String> TABLE_PT_EVOLUTION_WHITECLIFF_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Account Status", "", "Level", "Settings", "Premium games", "Other games", "Update Status");
            public final static List<String> TABLE_PT_EVOLUTION_WHITECLIFF_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Settings", "Premium games", "Other games", "Update Status");

            public final static String LBL_USERNAME = "Username";
            public final static String LBL_ACCOUNTSTATUS = "Account Status";
            public final static String LBL_PRODUCT = "Product";
            public final static String LBL_LEVEL = "Level";

            public static final HashMap<String, Boolean> SPORT_COLUMN_TRUE = new HashMap<String, Boolean>() {
                {
                    put("Soccer", true);
                    put("Cricket", true);
                    put("Line Market", true);
                    put("Fancy", true);
                    put("Virtual Cricket", true);
                    put("Bookmakers", true);
                    put("Decimal Cricket", true);
                    put("Tennis", true);
                    put("Basketball", true);
                    put("Horse Racing", true);
                    put("Greyhound Racing", true);
                    put("Other", true);
                }
            };

            public static final HashMap<String, Boolean> SPORT_COLUMN_FALSE = new HashMap<String, Boolean>() {
                {
                    put("Soccer", false);
                    put("Cricket", false);
                    put("Line Market", false);
                    put("Fancy", false);
                    put("Virtual Cricket", false);
                    put("Bookmakers", false);
                    put("Decimal Cricket", false);
                    put("Tennis", false);
                    put("Basketball", false);
                    put("Horse Racing", false);
                    put("Greyhound Racing", false);
                    put("Other", false);
                }
            };
        }

        public static class CommissionSettingListing {
            public final static String TITLE_PAGE = "Commission Setting Listing";
            public final static String ODDS_GROUP = "Odds Group";
            public final static List<String> LST_LIVE_DEALER_ASIAN_GAMES = Arrays.asList("Andar Bahar", "Baccarat", "Casinowar", "Dragon Tiger", "Roulette", "Teen Patti", "Teenpatti 20-20", "Three card poker");
            public final static List<String> LST_LIVE_DEALER_EUROPEAN_GAMES = Arrays.asList("Auto roulette", "Baccarat", "Bet on Numbers", "Blackjack", "Holdem", "Keno", "Roulette", "Sic Bo", "Lucky 7", "Other");
            public final static List<String> LST_LOTTERY_SLOT_GAMES = Arrays.asList("Lottery & Slots");
            public final static String CHECKBOX_APPLY_SOCCER_PS38 = "Apply soccer games setting to other commission types";
            public final static String HEADERS_COMMISSION_PS38 = "[Soccer games (*), Very high commission (*), High commission (*), Normal commission (*), Parlays (*), Teasers (*)]";
            public final static List<String> TABLE_HEADER_COMMISSION_SECTION = Arrays.asList("Group", "Commission on", "Group A", "Group B", "Group C", "Group D", "Group E");
            public final static List<String>
                    TABLE_COLUMN_GROUP_COMMISSION_SECTION = Arrays.asList("Soccer games", "Very high commission", "High commission", "Normal commission", "Parlays", "Teasers");
            public final static ArrayList<String> TABLE_AGENT_HEADER_LIVE_DEALER_ASIAN_NEWUI = new ArrayList<String>() {
                {
                    add("No.");
                    add("Username");
                    add("Login ID");
                    add("Account Status");
                    add("");
                    add("Level");
                    add("First Name");
                    add("Last Name");
                    add("Live Dealer Asian");
                    add("Update Status");
                }
            };
            public final static ArrayList<String> TABLE_AGENT_HEADER_LIVE_DEALER_ASIAN_OLDUI = new ArrayList<String>() {
                {
                    add("No.");
                    add("Username");
                    add("Login ID");
                    add("Account Status");
                    add("");
                    add("Level");
                    add("First Name");
                    add("Last Name");
                    add("Live Dealer Asian");
                    add("Update Status");
                }
            };
            public static final Map<String, String> PRODUCT_CODE_TO_NAME = new HashMap<String, String>() {
                {
                    put("SUPER_SPADE", "Live Dealer Asian");
                    put("EZUGI", "Live Dealer European");
                    put("VERONICA", "Supernowa Casino");
                    put("EVOLUTION", "Evolution");
                    put("GAME_HALL", "Game Hall");
                    put("VIVO", "ViVo");
                    put("ION", "Ion");
                    put("PRAGMATIC", "Pragmatic");
                    put("CMD_SPORTSBOOK", "CMD Sportsbook");
                }
            };

            public static final Map<String, String> PRODUCT_NAME_TO_CODE = new HashMap<String, String>() {
                {
                    put("Live Dealer Asian", "SUPER_SPADE");
                    put("Live Dealer European", "EZUGI");
                    put("Supernowa Casino", "VERONICA");
                    put("Evolution", "EVOLUTION");
                    put("Game Hall", "GAME_HALL");
                    put("ViVo", "VIVO");
                    put("Ion", "ION");
                    put("Pragmatic", "PRAGMATIC");
                    put("CMD Sportsbook", "CMD_SPORTSBOOK");
                    put("Exchange", "EXCHANGE");
                    put("Exchange Games", "EXCH_GAMES");
                    put("Lottery & Slots", "DIGIENT");
                }
            };
        }
        public static class StatementReport {

            public final static List<String> TABLE_HEADER_NEWUI = Arrays.asList("Username", "Nickname");
            public final static List<String> TABLE_HEADER_OLDUI = Arrays.asList("Username", "Login ID");
            public final static List<String> TABLE_DETAIL_STATEMENT_HEADER_CREDIT_CASH = Arrays.asList("Sport/Game", "Credit", "Profit & Loss", "Tax/Commission", "Available Balance");
            public final static List<String> TABLE_DETAIL_STATEMENT_HEADER_CREDIT = Arrays.asList("Sport/Game", "Profit & Loss", "Tax/Commission", "Cash Balance");
            public final static String LBL_STATEMENT_REPORT_SEARCH_TITLE = "Statement Report from %s to %s";
        }

        public static class BetSettingListing {
            public static final HashMap<String, Boolean> SPORT_COLUMN_FALSE = new HashMap<String, Boolean>() {
                {
                    put("Soccer", false);
                    put("Cricket", false);
                    put("Fancy", false);
                    put("Virtual Cricket", false);
                    put("Bookmaker", false);
                    put("Decimal Cricket", false);
                    put("Tennis", false);
                    put("Basketball", false);
                    put("Other", false);
                }
            };
            public static final HashMap<String, Boolean> SPORT_COLUMN_TRUE = new HashMap<String, Boolean>() {
                {
                    put("Soccer", true);
                    put("Cricket", true);
                    put("Fancy", true);
                    put("Virtual Cricket", true);
                    put("Bookmaker", true);
                    put("Decimal Cricket", true);
                    put("Tennis", true);
                    put("Basketball", true);
                    put("Other", true);
                }
            };
            public static final String PS38_COPY_SETTING_TO_ALL_SPECIFIC_SPORT_LEAGUES ="Copy settings to all Specific Sports and Leagues";
            public final static List<String> PS38_DEFAULT_SPORT_LIST = Arrays.asList("Soccer","Baseball","Basketball","Football","E Sports","Others","Mix Parlay","Teaser");
        }

        public static class TaxSettingListing {
            public final static String TITLE_PAGE = "Tax Setting Listing";
            public final static List<String> TABLE_TAX = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Soccer", "Cricket", "Tennis", "Basketball", "Fancy", "Virtual Cricket", "Decimal Cricket", "Other", "Update Status");
            public final static List<String> TABLE_TAX_SAT = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level", "Soccer", "Cricket", "Tennis", "Basketball", "Virtual Cricket", "Decimal Cricket", "Other", "Update Status");
        }

        public static class SubUserListing {
            public final static String TITLE_PAGE = "Sub User Listing";
            public final static List<String> PERMISSIONS_ALL = Arrays.asList("Create Account", "Update Account", "View Account", "Report", "Transfer & Deposit/Withdraw", "Account Balance", "Markets Management", "Deposit/Withdrawal Transactions", "Payment Channel Management", "Quick Deposit Configuration");
            public final static List<String> PERMISSIONSNONPO = Arrays.asList("Create Account", "Update Account", "View Account", "Report", "Transfer & Deposit/Withdraw", "Account Balance");
        }

        public static class EventBetSiteSetting {
            public final static String ERROR_MAX_LESS_THAN_MIN = "Min must be less than or equal to Max";
            public final static List<String> TAB_DAYS = Arrays.asList("Old Events", "Today", "Tomorrow", "Future");
            public final static List<String> TABLE_HEADER = Arrays.asList("Competition Name", "Event Name", "Event ID", "Min - Max", "Fancy Min - Max", "Bookmaker Min - Max");
            public final static List<String> TABLE_HEADER_PO = Arrays.asList("Competition Name", "Event Name", "Event ID", "Min - Max (F)", "Fancy Min - Max", "Bookmaker Min - Max (F)");
        }

        public static class FollowBets {
            public final static List<String> GROUP_LIST_TABLE_HEADER = Arrays.asList("Group List", "Group", "Follow Status", "Exchange %", "Fancy %", "Additional Follow", "Account To Bet", "Last Update By", "Last Update Date", "Actions", "Stake %", "Odds Range %");
            public final static List<String> PLAYER_AGENT_LIST_TABLE_HEADER_NEWUI = Arrays.asList("Player/Agent List", "Username", "Nickname", "Level", "Upline", "Actions");
            public final static List<String> PLAYER_AGENT_LIST_TABLE_HEADER_OLDUI = Arrays.asList("Player/Agent List", "Username", "Login ID", "Level", "Upline", "Actions");
            public final static List<String> PLAYER_TABLE_HEADER_NEWUI = Arrays.asList("Username", "Nickname", "Follow Status", "Exchange %", "Fancy %", "Additional Follow", "Account To Bet", "Last Update By", "Last Update Date", "Actions", "Stake %", "Odds Range %");
            public final static List<String> PLAYER_TABLE_HEADER_OLDUI = Arrays.asList("Username", "Login ID", "Follow Status", "Exchange %", "Fancy %", "Additional Follow", "Account To Bet", "Last Update By", "Last Update Date", "Actions", "Stake %", "Odds Range %");
            public final static List<String> FOLLOW_DETAIL_TABLE_HEADER = Arrays.asList("Soccer\nAdd\nFollow All", "Tennis\nAdd\nFollow All", "Cricket\nAdd\nFollow All", "Basketball\nAdd\nFollow All", "Horse Racing\nAdd\nFollow All", "Others\nAdd\nFollow All");
        }
        public static class Transfer {
            public final static String FULL_TRANSFER_CONFIRM_MSG = "Full transfer will be applied to all selected users. Would you like to proceed with the transfer?";
            public final static String ALL_YESTERDAY_BALANCE = "All Yesterday Balance";
            public final static String YOU_ARE_ALLOW_TO_TRANSFER_ON_TODAY_MSG = "You are allowed to transfer on today";
            public final static String TRANSFERABLE_BALANCE_IS_CALCULATED_TO_YESTERDAY_MSG = "Transferable Balance is calculated up to Yesterday";
            public final static List<String> TABLE_HEADER = Arrays.asList("No.", "Username", "Login ID", "Account Status", "", "Level","Transferable Balance","Retain Amount","Total Balance","Yesterday Downline Balance","Downline Balance","Total Players Outstanding","Credit Given","Credit Used","Update Status");
            public final static List<String> LST_ACCOUNT_STATUS = Arrays.asList("All", "Active", "Inactive", "Suspended", "Closed", "Blocked");
            public final static List<String> LST_LEVEL = Arrays.asList("All", "Agent", "Member");
        }
    }

    public static class RiskManagement {
        public final static List<String>
                LIST_SUBMENU_RISK_MANAGEMENT_F24 = Arrays.asList("Analysis of Running Markets","Net Exposure", "IP Monitoring","Volume Monitor","Agent Exposure Limit","Monitored Accounts");
        public final static List<String>
                LIST_SUBMENU_RISK_MANAGEMENT_NEWUI = Arrays.asList("Analysis of Running Markets","IP Monitoring","Agent Exposure Limit","Monitored Accounts");
        public static class VolumeMonitor {
            public final static String LBL_FROM = "From";
            public final static String LBL_TO = "To";
            public final static String LBL_LEVEL = "Level";
            public final static String LBL_SPORT = "Sport";
            public final static String BTN_TODAY = "Today";
            public final static String BTN_YESTERDAY = "Yesterday";
            public final static String BTN_LAST_WEEK = "Last 7 Days";
            public final static String BTN_SUBMIT = "Submit";
            public final static String NOTE = "You can see report data in range of 1 month";
            public final static List<String> LIST_BET_TYPES = Arrays.asList("Select All", "Matched", "Un-matched", "Settled", "Voided", "Lapsed");
            public final static List<String> TABLE_HEADER = Arrays.asList("No.", "Username", "Nickname", "First Name", "Level", "Currency", "Matched Volume", "Unmatched Volume", "Liability", "Back", "Lay", "Tax", "Total", "Bet History", "Settled Turnover", "Settled P&L", "Settled Turnover", "Settled P&L");
            public final static List<String> BET_HISTORY_TABLE_HEADER = Arrays.asList("No.", "Username", "Nickname", "Bet ID", "Order ID", "Time", "Description", "Type", "Odds", "Stake", "Status", "IP Address");
        }

        public static class NetExposure {
            public final static String LBL_MYPT = "My PT";
            public final static String LBL_TOTAL_BOOK = "Total Book";
            public final static String LBL_IN_PLAY = "In-Play";
            public final static String LBL_SPORT_SELECTION = "2-3 Selection:Sport:";
            public final static String LBL_MULTI_SPORT_SELECTION = "Multiple Selection:Sport:\n";
            public final static String LBL_NO_RECORD = "The bets under selected sport(s) are being settled or market exposure is not greater than min exposure limit.";
            public final static List<String> RESULT_TABLE_HEADER = Arrays.asList("", "Unmatched Stake", "Matched Stake", "1", "X", "2", "Unmatched Stake", "Matched Stake", "Over/Yes", "Under/No");
            public final static List<String> BET_LIST_HEADER_TBL = Arrays.asList("Member", "Currency", "Placed", "Selection", "Bet ID", "In Play", "Type", "Odds", "Stake [L]", "Stake [F]", "Potential Profit[L]", "Potential Profit[F]", "Liabilit [L]", "Liability [f]"
                    , "AG PT", "MA PT", "SMA PT", "CORP PT", "CO PT", "PO PT");

        }

        public static class AgentExposureLimit {
            public final static List<String> DOWNLINE_TABLE_HEADER = Arrays.asList("No.", "Username", "Nickname", "Level", "Currency", "Max Exposure", "Exposure", "Exposure Available");
        }

        public static class IPMonitoring {
            public final static List<String> FILTER_LIST = Arrays.asList("Live", "Last 7 days");
            public final static String LBL_INFO_GUIDE = "Multiple accounts are sharing the same IP. Please check and beware of VIP players.";
            public final static List<String> TBL_HEADER_LIST = Arrays.asList("No","IP","Username","Bet Count","Member Exposure","Member Last 7 Days","My Total", "Actions");
            public final static String ACTION_SUSPEND = "Suspend";
            public final static String ACTION_UNSUSPEND = "Unsuspend";
            public final static String ACTION_MONITOR = "Monitor";
            public final static String ACTION_UNMONITOR = "Un-monitor";
            public final static HashMap<String, String> MAP_FILTER_STATUS = new HashMap<String, String>() {
                {
                    put("Live", "LIVE");
                    put("Last 7 days", "LAST_7_DAYS");
                }
            };
        }
    }

    public static class Report {
        public final static String LBL_FROM = "From";
        public final static String LBL_TO = "to";
        public final static String BTN_TODAY = "Today";
        public final static String BTN_YESTERDAY = "Yesterday";
        public final static String LAST_WEEK = "Last Week";
        public final static String LAST_BUSINESS_WEEK = "Last Business Week";
        public final static String ERROR_PRODUCT = "Please select at least 1 product.";
        public final static String ERROR_DATE_RANGE = "End date cannot earlier than start date. Please redefine the search criteria";
        public final static List<String> LIST_EXTRA_RPODUCTS_PO = Arrays.asList("Fancy", "Wicket Fancy", "Wicket Bookmaker", "Central Fancy", "Central Bookmaker", "Follow Bets");
        public final static List<String> LIST_EXTRA_PRODUCTS_NEWUI = Arrays.asList("Fancy", "Wicket Fancy", "Wicket Bookmaker", "Artemis fancy", "Artemis bookmaker", "Premium Cricket", "Virtual Cricket", "Decimal Cricket");
        public final static List<String> LIST_EXTRA_PRODUCTS_OLDUI = Arrays.asList("Fancy", "Wicket Fancy", "Wicket Bookmaker", "Artemis fancy", "Artemis bookmaker", "Central Fancy", "Central Bookmaker", "Premium Cricket", "Virtual Cricket", "Decimal Cricket");
        public final static String LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS = "You can see report data up to 6 months.";
        public final static String LBL_YOU_CAN_SEE_REPORT_UP_TO_6_MONTHS_WITHOUT_DOT = "You can see report data up to 6 months";
        public final static String LBL_YOU_CAN_SEE_REPORT_UP_1_TO_6 = "You can see report data up to 1 month and back to previous 6 months.";
        public final static String LBL_PRODUCT = "Product";
        public final static List<String> LIST_SUB_MENU_CONTROL_BLOCKING_OLDUI = Arrays.asList("Unsettled Bet", "Profit And Loss", "Win Loss Simple", "AD Win Loss Detail", "Position Taking Report", "Cancelled Bets",
                "Statement Report", "Transfer Log", "Resettlement & Void Log",  "Client Ledger", "Win Loss By Sport And Market Type", "Win Loss By Event", "Analysis of Running Markets", "IP Monitoring", "Monitored Accounts", "Transaction History",
                "Top Gainers & Top Losers", "View Log");
        public final static List<String> LIST_SUB_MENU_CONTROL_BLOCKING_NEWUI = Arrays.asList("Profit And Loss", "Win Loss", "Position Taking Report", "Unsettled Bet", "Cancelled Bets", "Top Gainers & Top Losers", "View Log", "Transfer Log", "Resettlement & Void Log");
        public final static List<String> LIST_SUB_MENU_CONTROL_BLOCKING_FUNSPORT = Arrays.asList("Unsettled Bet", "Profit And Loss", "Win Loss Simple", "MA Win Loss Detail", "Position Taking Report", "Cancelled Bets",
                "Statement Report", "Transfer Log", "Resettlement & Void Log",  "Client Ledger", "Win Loss By Sport And Market Type", "Win Loss By Event", "Analysis of Running Markets", "IP Monitoring", "Monitored Accounts", "Transaction History",
                "Top Gainers & Top Losers", "View Log");
        public static class WinLossSimple {
            public final static String HOME_TITLE = "Senior Master Agent:";
            public final static List<String> DDB_PRODUCT = Arrays.asList("Exchange", "Live Dealer European", "Fancy", "Live Dealer Asian", "Exchange Games");
            public final static List<String> TABLE_HEADER_NEWUI = Arrays.asList("No.", "Username", "Nickname", "Upline", "Currency", "Win/Loss", "Total Tax/Comm");
            public final static List<String> TABLE_HEADER_OLDUI = Arrays.asList("No.", "Username", "Login ID", "Upline", "Win/Loss", "Total Tax/Comm");

        }

        public static class UnsettleBet {
            public final static List<String> MODE_LIST = Arrays.asList("Last Bets Mode", "Sport Mode", "Hierarchy Mode");
            public final static String LAST_BETS_MODE = "Last Bets Mode";
            public final static String SPORT_MODE = "Sport Mode";
            public final static String HIERARCHY_MODE = "Hierarchy Mode";
            public final static List<String> LIST_BET_TYPE_RADIO_BUTTON = Arrays.asList("All", "Matched", "Un-matched", "Cancelled", "Voided", "Lapsed");
            public final static String HINT_MESSAGE = "You can see report data in range of 1 month";
            public final static List<String> LAST_BETS_MODE_TABLE_HEADER = Arrays.asList("No.", "UserName", "Login ID", "Bet ID", "Time", "Description", "Type", "Odds", "Stake", "Status", "IP Address", "Browser Detail");
            public final static List<String> HIERARCHY_MODE_PO_TABLE_HEADER = Arrays.asList("No.", "UserName", "Login ID", "Level", "Member Total", "AG Liability", "MA Liability", "SMA Liability", "CORP Liability", "PART Liability", "CO Liability");
        }

        public static class WinLossDetails {
            public final static String TITLE = "%s Win Loss Details";
            public final static String LBL_SHOW_TOTAL = "Show total only";
            public final static String LBL_INFO = "You can see report data up to 6 months";
            public final static List<String> TABLE = Arrays.asList("Exchange", "Live Dealer European", "Fancy", "Live Dealer Asian", "Exchange Games");
            public final static List<String> TABLE_HEADER = Arrays.asList("No.", "Username", "Nickname", "Upline", "Currency", "Win/Loss", "Total Tax/Comm");
        }

        public static class ProfitAndLoss {
            public final static String TITLE = "Profit And Loss";
            public final static String LBL_TIMEZONE = "Timezone";
            public final static String LBL_REPORT_AVAILABLE_IST = "For IST timezone, report data is only available from June 27, 2019";
            public final static String LBL_UPLINE_PROFIT_AND_LOST = "Upline Profit And Loss";
            public final static String LBL_DOWNLINE_PROFIT_AND_LOST = "Downline Profit And Loss";
            public final static String LBL_UPLINE_PROFIT_AND_LOST_PO = "Upline Profit And Loss Currency %s";
            public final static String LBL_DOWNLINE_PROFIT_AND_LOST_PO = "Downline Profit And Loss Currency %s";
            public final static String LBL_PLEASE_SELECT_PRODUCT = "Please select at least 1 product.";
            public final static List<String> TIMEZONE_LIST = Arrays.asList("ET (GMT -4:00)", "IST(GMT + 5:30)");
            public final static List<String> TBL_UPLINE_TABLE_OLDUI = Arrays.asList("Name", "Back", "Lay", "Total Tax", "Total Pay", "Turnover", "Pnl", "Turnover", "Pnl");
            public final static List<String> TBL_DOWNLINE_TABLE_OLDUI = Arrays.asList("No.", "Username", "Login ID", "First Name", "Level", "Back", "Lay", "Total Tax", "Balance", "Turnover", "Pnl", "Turnover", "Pnl");
            public final static List<String> TBL_DOWNLINE_TABLE_PO_OLDUI = Arrays.asList("No.", "Username", "Login ID", "First Name", "Level", "Currency","Back", "Lay", "Total Tax", "Balance", "Turnover", "Pnl", "Turnover", "Pnl");
            public final static List<String> TBL_UPLINE_TABLE_NEWUI = Arrays.asList("Name", "Currency","Back", "Lay", "Total Tax", "Total Pay", "Turnover", "Pnl", "Turnover", "Pnl");
            public final static List<String> TBL_DOWNLINE_TABLE_NEWUI = Arrays.asList("No.", "Username", "Nickname", "First Name", "Level", "Currency","Back", "Lay", "Total Tax", "Balance", "Turnover", "Pnl", "Turnover", "Pnl");
            public final static List<String> LST_PRODUCT_GROUP = Arrays.asList("Exchange group", "Casino group", "Exchange Games");
            public final static List<String> HEADER_CASH_OUT_HISTORY_DIALOG = Arrays.asList("Risk Amount\n" +
                    "(F)", "Cash Out Amount\n" +
                    "(F)", "Date/Time");
            public final static List<String> HEADER_CASH_OUT_HISTORY_DIALOG_BO = Arrays.asList("Risk Amount\n" +
                    "(F)", "Cash Out Amount\n" +
                    "(L)", "Cash Out Amount\n" +
                    "(F)", "Date/Time");
        }

        public static class PositionTakingReport {
            public final static List<String> TABLE_HEADER = Arrays.asList("No.", "Competition Name", "Volume", "PnL", "Tax","");

        }

        public static class FollowAndSmallBetsPerformance {
            public final static String TITLE = "Follow Bets Performance";
            public final static String INFO = "You can see report data up to 3 months and back to previous 1 year.";
            public final static List<String> TABLE_FOLLOW_BETS_HEADER = Arrays.asList("Group/Player", "Exchange", "Fancy", "Additional Stake", "Additional Odds Range", "Account To Bet", "Total Follow Wager", "Total Follow Win/Loss");

        }

        public static class CancelledBets {
            public final static String LBL_INFO = "You can see report data up to 3 months";
            public final static List<String> TABLE_HEADER = Arrays.asList("No.", "Username", "Nickname", "Voided Date", "Description", "Type", "Odds", "Stake", "Status", "Remark");
            public final static List<String> TABLE_HEADED_SAT = Arrays.asList("No.", "Username", "Login ID", "Voided Date", "Description", "Type", "Odds", "Stake", "Status", "Remark");
        }

        public static class ClientLedger {
            public final static List<String> TABLE_HEADER_BETTING_PO = Arrays.asList("Username", "Currency", "Login ID", "Credit", "Debit", "Balance");
            public final static List<String> TABLE_HEADER_ALL_PO = Arrays.asList("Username", "Currency", "Login ID", "Credit", "Profit & Loss", "Transfer", "Outstanding", "Available Balance");
            public final static List<String> TABLE_HEADER_BETTING = Arrays.asList("Username", "Login ID", "Credit", "Debit", "Balance", "");
            public final static List<String> TABLE_HEADER_ALL = Arrays.asList("Username", "Login ID", "Profit And Loss", "Transfer", "Outstanding", "Available Balance", "");
            public final static String LBL_STATEMENT_REPORT_SEARCH_TITLE = "Client Ledger %s - %s";
        }


        public static class TransactionHistory {
            public final static List<String> TABLE_HEADER = Arrays.asList("No.", "Competition Name", "Volume", "PnL", "Tax", "");
        }

        public static class WinLossBySportAndMarketType {
            public final static String LBL_TITLE = "Win Loss By Sport And Market Type";
            public final static String LBL_INFO = "You can see report data up to 6 months and back to previous 3 years";
            public final static String LBL_SHOW_TOTAL_ONLY = "Show total only";
            public final static List<String> TABLE_HEADER = Arrays.asList("Market", "Turnover");
            public final static String LBL_SEARCH_TITLE = "Win Loss By Sport And Market Type %s ~ %s";
        }

        public static class WinLossByEvent {
            public final static String LBL_TITLE = "Win Loss By Event";
            public final static String LBL_INFO = "You can see report data up to 1 month and back to previous 1 year.";
            public final static String LBL_SHOW_TOTAL_ONLY = "Show total only";
            public final static List<String> TABLE_HEADER = Arrays.asList("Event", "Turnover");
            public final static String LBL_SEARCH_TITLE = "Win Loss By Event %s ~ %s";
        }

        public static class TopGainersTopLosers {
            public final static String BTN_SEARCH = "Search";
            public final static String LBL_INFO_REPORT_VALID = "You can see report data up to 3 months and back to previous 1 year.";
            public final static String LBL_INFO_SUPPORT_YESTERDAY_DATA = "Top Gainers & Top Losers only support yesterday data. Big Stake supports today data..";
            public final static String LBL_INFO_PLACE_TIME = "Big Stake wagers are based on placed time.";
            public final static List<String> AGENT_SMA_TABLE_TOP_GAINERS_HEADER_NEWUI = Arrays.asList("Top Gainers", "20\n50\n100\n200", "No.", "Usercode", "Total Wager", "Total Turnover", "Total Win/Loss", "Total Tax/Comm");
            public final static List<String> AGENT_SAD_TABLE_TOP_GAINERS_HEADER_OLDUI = Arrays.asList("Top Gainers", "20\n50\n100\n200", "Login ID", "Total Wager", "Total Turnover", "Total Win/Loss", "Total Tax/Comm");
            public final static List<String> AGENT_SMA_TABLE_TOP_LOSERS_HEADER_NEWUI = Arrays.asList("Top Losers", "20\n50\n100\n200", "No.", "Usercode", "Total Wager", "Total Turnover", "Total Win/Loss", "Total Tax/Comm");
            public final static List<String> AGENT_SAD_TABLE_TOP_LOSERS_HEADER_OLDUI = Arrays.asList("Top Losers", "20\n50\n100\n200", "Login ID", "Total Wager", "Total Turnover", "Total Win/Loss", "Total Tax/Comm");
            public final static List<String> TABLE_BIG_STAKE_HEADER_NEWUI = Arrays.asList("Big Stake", "20\n50\n100\n200", "No.", "Usercode", "Stake", "Status", "Win/Loss", "Wager ID");
            public final static List<String> TABLE_BIG_STAKE_HEADER_OLDUI = Arrays.asList("Big Stake" ,"Login ID", "Description", "Wager Details", "Status", "Win/Loss", "Wager ID");
            public final static String LBL_BIG_STAKE_TABLE_HEADER = "Big Stake (>=%s )";
        }

        public static class BigStakeConfiguration {
            public final static String LBL_BIG_STAKE = "Big Stake";
            public final static String LBL_INFO = "Setting 0 means Big Stake checking is turned off.";
            public final static String LBL_SUCCESS_MESSAGE = "Big stake configuration is saved successfully.";
            public final static String LBL_ERROR_VALUE_SET_MESSAGE = "Your value is the current value. Please try another one!";
            public final static String LBL_ERROR_EMPTY_MESSAGE = "Please input stake config!";
            public final static List<String> TABLE_BIG_STAKE_HEADER = Arrays.asList("Date Time", "Big Stake Value", "Set By");
        }

        public static class BFVoidedDiscrepancy {
            public final static String LBL_INFO = "You can see report data up to 6 months and back to previous 3 years";
            public final static String LBL_SEARCH_RANGE_TITLE = "Betfair Voided Discrepancy %s ~ %s";
            public final static List<String> TABLE_HEADER = Arrays.asList(LBL_SEARCH_RANGE_TITLE, "No.", "Wager Id", "User Code", "Nickname", "Description", "Type", "Odds", "Stake", "Status", "Betfair", "BF Wager Id", "BF Status", "BF Amount", "BF Win/Loss");
        }

        public static class TransferLog {
            public final static String LBL_INFO = "You can see report data up to 6 months";
            public final static List<String> TABLE_HEADER = Arrays.asList("No.", "Audit Date", "UserName", "Login ID", "Audit Type", "Attribute", "Old Value", "New Value", "Perform By", "Remark");
        }
    }

    public static class FraudDetection{
        public final static List<String>
                LIST_SUBMENU_FRAUD_DETECTION = Arrays.asList("Fraud Detection", "Wager Odds History");
    }

    public static class MarketsManagement {
        public final static List<String>
                LIST_SUBMENU_MARKETS_MANAGEMENT = Arrays.asList("Block Racing", "Block/Unblock Events", "Block/Unblock Competitions", "Current Blocking", "Blocking Log");
        public final static List<String>
                LIST_SUBMENU_HAS_SUSPEND_MARKETS_MANAGEMENT = Arrays.asList("Block Racing", "Suspend/Unsuspend Markets","Block/Unblock Events", "Block/Unblock Competitions", "Current Blocking", "Blocking Log");
        public static class SuspendUnsuspendMarket {
            public final static String TITLE_PAGE = "Suspend/Unsuspend Markets";
            public final static List<String> TABLE_EVENT = Arrays.asList("Competition Name", "Event Name", "Market");
            public final static String MARKET_DETAIL = "Market Details";
            public final static String MARKET_NAME = "Market Name";
            public final static String COMPETITION_NAME = "Competition Name";
            public final static String EVENT_NAME = "Event Name";
            public final static String NOTE = "Only line markets are shown to suspend/unsuspend";
            public final static String SUSPEND = "Suspend";
            public final static String UNSUSPEND = "Unsuspend";
            public final static List<String> MARKET_TABLE_HEADER = Arrays.asList("Market Name", "Status", "Last Update By", "Last Update Date", "");
        }

        public static class BlockUnblockEvent {
            public final static String UNBLOCK_NOW = "Unblock Now";
            public final static String TITLE_PAGE = "Block/Unblock Events";
            public final static String TIME_TO_BET = "25 minutes";
            public final static List<String> TAB_DAYS = Arrays.asList("Old Events", "Today", "Tomorrow", "Future");
            // public final static List<String> TAB_DAYSAPI = Arrays.asList("OLD", "TODAY", "TMR", "FUTURE");
            public final static List<String> BTN_ACTIONS = Arrays.asList("Block", "Unblock Now", "Unblock Schedule", "Suspend", "Unsuspend");
            public final static List<String> TABLE_DOWNLINE = Arrays.asList("Downline");
            public final static List<String> TABLE_EVENT = Arrays.asList("Competition", "Event", "Status", "Unblock Schedule Setting\n" +
                    "(Before Market Start Time)", "Current", "Viewable", "Betable", "Time to open", "Time to bet");
            public final static List<String> UNBLOCKTYPE = Arrays.asList("Now", "25 minutes", "24 hours", "2 days", "3 days", "4 days", "5 days", "6 days", "7 days", "None");
            public final static String NO_DOWNLINE_AVAILABLE = "No downline available";
            public static final Map<String, String> TABs = new HashMap<String, String>() {
                {
                    put("Old Events", "OLD");
                    put("Today", "TODAY");
                    put("Tomorrow", "TMR");
                    put("Future", "FUTURE");
                }
            };
            public final static String HINT_MSG = "Block is only available for Unblocked events\n" +
                    "Unblock or Unblock Schedule is only available for Blocked events\n" +
                    "Suspend is only available for Unblocked events\n" +
                    "Unsuspend is only available for Suspended events\n" +
                    "In order to remove Unblock Schedule for Blocked events, please select the None option in Schedule list";
        }

        public static class CurrentBlocking {
            public final static String LBL_TYPE = "Type";
            public final static String LBL_SPORT = "Sport";
            public final static List<String> TABLE_COMPETITION = Arrays.asList("Competition Name", "Current");
            public final static List<String> TABLE_EVENT = Arrays.asList("Competition Name", "Event Name", "Current");
            public final static List<String> TAB_DAYS = Arrays.asList("Old Events", "Today", "Tomorrow", "Future");
        }
        public static class BlockRacing {
            public final static String LBL_UPDATE_SUCCESS_MSG = "Your settings updated successfully!";
        }

        public static class BlockedUserPopup {
            public final static String TITLE_PAGE = "Blocked User";
            public final static String LBL_COMPETITION = "Competition Name";
            public final static String LBL_EVENT = "Event Name";
            public final static String BTN_UNBLOCK_NOW = "Unblock Now";
            public final static String BTN_UNBLOCK_SCHEDULE = "Unblock Schedule";
            public final static List<String> TABLE_EVENT = Arrays.asList("Login ID", "Level", "Upline", "Blocked By", "Blocked Date", "");
            public final static String BTN_CLOSE = "Close";
            public final static String LBL_NO_USER_BLOCKED = "There is no user blocked";
        }

        public static class BlockUnblockCompetition {
            public final static String BTN_BLOCK = "Block";
            public final static String BTN_UNBLOCK = "Unblock";
            public final static String LBL_INFO = "Block means all events under competition will disappear on Member site and Block/Unblock Events page.\n" +
                    "Unblock means the events will reappear on Block/Unblock Events page. Events availability on Member site is controlled by Block/Unblock Events settings.";
            public final static String LBL_SPORT = "Sport";
            public final static String TXT_SEARCH_LOGINID = "Search By Username/Login ID";
            public final static String TXT_SEARCH_COMPETITION = "Search By Competition";
            public final static List<String> TABLE_DOWNLINE = Arrays.asList("Downline");
            public final static List<String> TABLE_COMPETITON = Arrays.asList("Competition", "Status", "Last Update By", "Last Update Time");


        }

        public static class BlockingLog {
            public final static String TITLE_PAGE = "Blocking Log";
            public final static String EVENT_DATE = "Event Date:";
            public final static String SPORT = "Sport";
            public final static String COMPETITON = "Competition";
            public final static String EVENT_ID = "Event ID";
            public final static String EVENT_NAME = "Event Name";
            public final static String EVENT_STATUS = "Status";
            public final static String SEARCH_SPORT = "Search sport";

            public final static String SEARCH_COMPETITION = "Search competition";
            public final static String SEARCH_EVENT_ID = "Search ID";
            public final static String SEARCH_EVENT_NAME = "Search name";
            public final static String SEARCH_EVETS_STATUS = "Status";

        }
    }
}
