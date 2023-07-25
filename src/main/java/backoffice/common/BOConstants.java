package backoffice.common;

import java.util.*;

public class BOConstants {
    public final static String NO_RECORD_FOUND = "No record found";
    public final static String NO_RECORDS_FOUND = "No records found.";
    public final static String DASH_YYYY_MM_DD = "yyyy-MM-dd";
    public final static String DASH_DD_MM_YYYY = "dd-MM-yyyy";
    public final static String SLASH_DD_MM_YYYY = "dd/MM/yyyy";
    public final static String SLASH_YYYY_MM_DD = "yyyy/MM/dd";
    public final static String SLASH_MM_YYYY = "MM/yyyy";
    public final static String GMT_FOUR = "GMT-4";
    public final static String GMT_IST = "IST";

    public static class LoginPage {
        public final static String LOGIN_TO_YOUR_ACCOUNT = "LOGIN TO YOUR ACCOUNT";
    }

    public static class FraudDetection {
        public static class OddsMatchedHistory {
            public final static String TITLE = "Odds Matched History";
            public final static String EVENT_ID = "Event Id";
            public final static String MARKET_NAME = "Market Name";
            public final static String BETTING_ON = "Betting on";
            public final static String TIME_ORDER = "Time Order";
            public final static String SEARCH = "Search";
            public final static List<String> TABLE_HEADER = Arrays.asList("Date", "Price", "Size");
        }
    }

    public static class System {
        public static class ProductMaintenance {
            public final static String TITLE = "Product Maintenance";
            public final static List<String> TABLE_HEADER = Arrays.asList("No.", "Product Name", "Status", "Maintenance Message", "Action");
            public final static List<String> LST_PRODUCTS = Arrays.asList("Exchange", "Live Dealer European", "Lottery & Slots", "Live Dealer Asian", "Exchange Games");

            //Popup
            public final static String POPUP_TITLE = "Maintenance Details";
            public final static List<String> DDB_POPUP_STATUS = Arrays.asList("Active", "Maintenance");

        }

        public static class PrioritySetting {
            public final static String TITLE = "Priority Setting";
            public final static List<String> SPORT_TABLE_HEADER = Arrays.asList("", "Priority", "Sport Name", "Last Updated By", "Last Updated Time");
            public final static List<String> COMPETITION_TABLE_HEADER = Arrays.asList("", "Priority", "Competition Name", "Last Update By", "Last Update Time");
            public final static List<String> MARKET_TABLE_HEADER = Arrays.asList("", "Priority", "Market Type", "Last Update By", "Last Update Time");
            public final static List<String> COUNTRY_RACES_TABLE_HEADER = Arrays.asList("", "Priority", "Country Code", "Country Name", "Last Update By", "Last Update Time");
            public final static String NOTE = "You can drag and drop to create your preferred order or you can click on the icon next to Priority number to edit directly";
            public final static String SEARCH_SPORT = "Search sport";
            public final static String SEARCH_COMPETITION = "Search competition";
            public final static String SEARCH_MARKET = "Search Market Type";
            public final static String SEARCH_COUNTRY_RACES = "Search country";
            public final static List<String> DDB_SPORT_COUNTRY_RACES = Arrays.asList("Horse Racing", "Horse Racing - Antepost", "Greyhound Racing", "Greyhound Racing - Antepost");
        }

        public static class SmallBetConfiguration {
            public final static List<String> TABLE_HEADER = Arrays.asList("Agent","Status","Stake","Accept % of Pricing","Reject Back if Potential Winning","Reject Lay if Potential Liability","Action");
            public final static String MSG_ADD_SUCCEED = "Add Succeed";
            public final static String MSG_AGENT_ALREADY_ADDED = "Agent %s is already added!";
            public final static String MSG_SHOULD_NOT_BE_ADD = "Agent code under Fairenter, Funsport and Laystars should not be added.";
            public final static String MSG_UPDATE_SUCCESSFUL = "Update is successful!";
            public final static String MSG_UPDATE_STAKE = "Are you sure to update Min Bet of agent %s from %s INR to %s INR?";
            public final static String MSG_UPDATE_ACCEPT_OF_PRICING = "Are you sure to update Accept %% of Pricing of agent %s from %s %% to %s %%?";
            public final static String MSG_UPDATE_REJECT_BACK = "Are you sure to update Reject Back if Potential Winning of agent %s from %s times to %s times?";
            public final static String MSG_UPDATE_REJECT_LAY = "Are you sure to update Reject Lay if Potential Liability of agent %s from %s times to %s times?";
            public final static String MSG_AGENT_NOT_EXIST = "Agent does not exist in the system!";
        }
    }

    public static class Settlement {
        public static class FancyResult {
            public final static String TITLE = "Fancy Result";
            public final static List<String> TABLE_HEADER = Arrays.asList("Market ID", "Market Name - Status", "Ref. Status", "Ref. Last Update", "Has Bet", "Section ID", "M. Status", "Min Bet (INR)", "Max Bet (INR)", "External Status", "External Result", "Result", "Action");
        }

        public static class WagerResettlement {
            public final static String TITLE = "Wager Resettlement";
            public final static List<String> TABLE_HEADER = Arrays.asList("Wager ID", "Selection Name", "External Account", "External ID", "Stake", "Back/Lay", "Odds", "Currency", "Created Date", "Handicap", "Status", "Result",
                    "Action", "Original", "Matched", "FairExchange", "Betfair", "FairExchange", "Betfair");
        }
    }

    public static class Operations {
        public static final Map<String, String> LANGUAGE = new HashMap<String, String>() {
            {
                put("Greek", "el_GR");
                put("English", "en_US");
                put("Indonesia", "id_ID");
                put("Thailand", "th_TH");
                put("Vietnamese", "vi_VN");
                put("China", "zh_CN");
            }
        };

        public static class CurrencyManagement {
            public final static String TITLE = "Currency Management";
            public final static List<String> TABLE_HEADER = Arrays.asList("From Currency", "Target Currency", "Currency Rate", "Pending Rate", "New Rate", "Note");
        }

        public static class CurrencyCountryMapping {
            public final static String TITLE = "Currency - Country Mapping";
            public final static String CURRENCIES_TITLE = "Currencies";
            public final static String COUNTRIES_TITLE = "Countries";
            public final static List<String> TABLE_HEADER_CURRENCIES = Arrays.asList("No.", "Currency Code", "Currency Name");
            public final static List<String> TABLE_HEADER_COUNTRIES = Arrays.asList("No.", "Country Code", "Country Name", "Status");
            public final static String SUCCESS_MSG = "Currency - Country mapping is saved successfully.";
        }

        public static class CountryIPMapping {
            public final static String TITLE = "Country - IP Mapping";
            public final static String COUNTRIES_TITLE = "Countries";
            public final static String IP_TITLE = "IPs: %s";
            public final static List<String> TABLE_HEADER_COUNTRIES = Arrays.asList("No.", "Country Code", "Country Name");
            public final static List<String> TABLE_IP_COUNTRIES = Arrays.asList("No.", "IP Address", "Action");
            public final static String CONFIRM_MSG = "Are you sure to add this IP %s to %s ?";
        }

        public static class AnnouncementManagement {
            public final static String TITLE = "Announcement Management";
            public final static String MSG_EN_TRANSLATION_CANNOT_BE_EMPTY = "English translation cannot be empty.";
            public final static String MSG_CONFIRM_DELETE_ANNOUNCEMENT = "Are you sure to delete this announcement?";
            public final static String BTN_SHOW = "Show";
        }

        public static class LiveStreamingManagement {
            public final static String TITLE = "Live Streaming Management";
            public final static String TITLE_AUTO_MAPPING = "Auto Mapping";
            public final static String TITLE_CONFIRM_MAPPING_POPUP = "Are you sure to map those events?";
            public final static String CONFIRM_MAPPING_NOTE_MESSAGE = "The 2 selected event names look like different match.\n" +
                    "The 2 selected events have different open dates.";
            public final static List<String> TABLE_AUTO_MAPPING = Arrays.asList("Start date", "Fair Competition", "Fair Event Name", "Provider Competition", "Provider Event Name", "% Matched >=", "");

        }

        public static class BannerManagement {
            public final static String TITLE = "Banner Management";
            public final static List<String> TABLE_HEADER = Arrays.asList("ID", "Banner", "Background Color", "Status", "Sequence", "Brands", "Valid From", "Valid Till", "Date Created", "Created By", "Action");
            public final static List<String> DDB_STATUS = Arrays.asList("All", "Active", "Inactive");

            // Popup
            public final static String POPUP_UPDATE_TITLE = "Update Banner";
            public final static String POPUP_CREATE_TITLE = "New Banner";
            public final static List<String> POPUP_DDB_STATUS = Arrays.asList("Active", "Inactive");
            public final static List<String> POPUP_DDB_SEQUENCE = Arrays.asList("-", "1", "2", "3", "4", "5", "6");

            public final static String SUCCESS_MESSAGE_CREATE = "Banner Created Successfully";
            public final static String SUCCESS_MESSAGE_UPDATE = "Banner Updated";
            public final static String SUCCESS_MESSAGE_DELETE = "Banner Deleted";
        }

        public static class PersonalMessage {
            public final static String TITLE = "Personal Message";
            public final static String SUCCESS_SEND_MESSAGE = "Sent message successfully!";
        }

        public static class VoidUnvoidWager {
            public final static String TITLE = "Wager Void / Un-void";
            public final static String SUCCESS_MESSAGE = "%s Wager successful. Report might take 30 minutes to regenerate.";
        }

        public static class CompetitionBlocking {
            public final static String TITLE = "Competition Blocking";
            public final static String LBL_NOTE = "Unblock means all events under the competition will be displayed on Agent site - Block/Unblock Events page. You might need to unblock them on the Agent site page to make the events available on Member site.";
            public final static String LAST_UPDATE_BY_SYSTEM = "SYSTEM";
            public final static String UNBLOCK_STATUS = "Unblocked";
            public final static String BLOCK_STATUS = "Blocked";
        }

        public static class BlockUnblockEvent {
            public final static List<String> SPORTS = Arrays.asList("Soccer", "Tennis", "Horse Racing", "Greyhound Racing", "Esports", "Basketball");
            public final static List<String> FILTER_PERIOD = Arrays.asList("Old Events", "Today", "Tomorrow", "Future");
        }
    }
    public static class PaymentManagement {
        public static class PaymentConfiguration {
            public final static List<String> HEADER_TABLE = Arrays.asList("No.","Brand","Level","Username","Currency","Payment Method","Line","Updated By","Updated Date","Action");
            public final static List<String> OPTION_OF_PAYMENT_METHOD = Arrays.asList("All","KINGSPAY");
            public final static String MSG_AGENT_NOT_EXIST = "Agent %s does not exist in the System!";
            public final static String MSG_AGENT_ALREADY_ADDED = "Agent %s is already added";
            public final static String MSG_ONLY_1_LEVEL_IN_SINGLE_LINE = "Only 1 level in a single line is allowed to configure";
        }
        public static class DepositWithdrawalTransactions {
            public final static List<String> TAB_NAME = Arrays.asList("Deposit Transactions","Withdrawal Transactions");
            public static final List<String> HEADER_TABLE_OF_DEPOSIT_TAB = Arrays.asList("No.","Brand","Username","Transaction Date","Transaction ID","Payment Method",
                    "Currency","Deposit Amount","Status");
            public static final List<String> HEADER_TABLE_OF_WITHDRAWAL_TAB = Arrays.asList("No.","Brand","Username","Transaction Date","Transaction ID","Bank Account Name",
                    "Bank Name","IFSC Code","Bank Account No.","Currency","Withdrawal Amount","Status","Updated Date","Updated By");
            public static final List<String> STATUS_OF_DEPOSIT = Arrays.asList("All","Pending","Success","Failure");
            public static final List<String> STATUS_OF_WITHDRAWAL = Arrays.asList("All","Pending","Success","Failure","Approved","Rejected");
        }
    }

    public static class Reports {
        public static class WinLossDetail {
            public final static String TITLE = "Win Loss Detail";
            public final static List<String> DDB_PRODUCT = Arrays.asList("Exchange", "Live Dealer European", "Lottery & Slots", "Fancy", "Live Dealer Asian", "Exchange Games",
                    "Follow Bets", "Supernowa Casino", "Wicket Fancy", "Wicket Bookmaker", "Central Fancy", "Central Bookmaker", "Evolution", "Premium Cricket Sportsbook",
                    "Pinnacle Sportsbook", "Game Hall", "Vivo");
            public final static List<String> DDB_TYPE_CURRENCY = Arrays.asList("All", "Local (L)", "Foreign (F)");
            public final static String ERROR_PRODUCT_MSG = "Please select at least 1 product";
            public final static String ERROR_PORTAL_MSG = "Please select at least 1 portal";
        }

        public static class Performance {
            public final static String TITLE = "Performance";
            /*************
             * Header Section Data
             *************/
            public final static String BTN_CREATE_MANAGE_LINE = "+Create/Manage Lines";
            public final static String FROM = "From";
            public final static String TO = "To";
            public final static String BTN_LAST_WEEK = "Last Week";
            public final static String BTN_LAST_30_DAYS = "Last 30 Days";
            public final static String BTN_LAST_90_DAYS = "Last 90 Days";
            public final static String BTN_LAST_365_DAYS = "Last 365 Days";
            public final static String BTN_SUBMIT = "Submit";

            /*************
             * PT SETTING section Data
             *************/
            public final static String PT_SETTING = "PT SETTING";
            public final static String LBL_NO_OF_BET = "No. of bets";
            public final static String LBL_MEMBER_WIN_LOSS = "Member Win/Loss:";
            public final static String CURRENCY_TYPE = "Currency Type";
            public final static List<String> TBL_HEADER_PT_SETTING = Arrays.asList("", "Username", "Currency", "Total Wager", "Turnover (L)", "Member", "AG", "MA", "SMA", "CORP", "PART", "Total Downline PT",
                    "Current PT/ Max Available PT", "Update Time", "Update Status", "Log", "", "", "", "", "", "Win/Loss (L)", "Win/Loss (L)", "%", "Win/Loss (L)", "%", "Win/Loss (L)", "%", "Win/Loss (L)", "%", "Win/Loss (L)", "%");

            /*************
             * LINE OVERVIEW section Data
             *************/
            public final static String LINE_OVERVIEW = "LINE OVERVIEW";
            public final static String MEMBER_TREE = "Member Tree";
            public final static String NOTE = "Report does not support today data.";

            public final static String GENERAL_INFORMATION = "General Information";
            public final static String TURNOVER = "Turnover";
            public final static String AVERAGE_STAKE = "Average Stake";
            public final static String PT_BY_TURNOVER = "PT by Turnover";
            public final static String WIN_LOSS = "Win/Loss";
            public final static String PERFORMANCE = "Performance";
            public final static List<String> TBL_HEADER_GENERAL_INFORMATION_1 = Arrays.asList("Lost Members", "Won Members");
            public final static List<String> TBL_HEADER_GENERAL_INFORMATION_2 = Arrays.asList("Lost Bets", "Won Bets", "Draw Bets");
            public final static List<String> TBL_HEADER_TURNOVER = Arrays.asList("Lost", "Won", "Draw");
            public final static List<String> TBL_HEADER_PT_BY_TURNOVER = Arrays.asList("Member Turnover", "BF Turnover", "CO Turnover", "Line Turnover");
            public final static List<String> TBL_HEADER_WIN_LOSS = Arrays.asList("Member Win/Loss", "BF Win/Loss", "CO Win/Loss", "Line Win/Loss");
            public final static List<String> TBL_HEADER_PERFORMANCE = Arrays.asList("BF Perf", "CO Perf", "Line Perf", "Member Perf");

            /*************
             * TOP PERFORMERS section Data
             *************/
            public final static String TOP_PERFORMERS = "TOP PERFORMERS";
            public final static String PERFORMANCE_PERCENT = "Performance %";

            public final static String MEMBER = "Member";
            public final static String LINE = "Line";
            public final static List<String> TBL_HEADER_TOP_PERFORMERS = Arrays.asList("Member", "Line", "CO", "BF");

            /*************
             * Create New Line section Data
             *************/
            public final static List<String> TBL_HEADER = Arrays.asList("Brand", "Level", "Line", "Upline ID", "Mapped Account ID", "");
            public final static String CREATE_NEW_LINE = "CREATE NEW LINE";
            public final static String BACK = "BACK";
            public final static String BRAND = "Brand";
            public final static String LEVEL = "Level";
            public final static String UPLINE_ID = "Upline ID";
            public final static String LINE_NAME = "Line Name";
            public final static String MAPPED_ACCOUNT_ID = "Mapped Account ID";
            public final static String CREATE = "Create";
            public final static String SEARCH_BRAND = "Search Brand";
            public final static String SEARCH_LEVEL = "Search Level";
            public final static String SEARCH_LINE = "Search Line";
            public final static String SEARCH_UPLINE_ID = "Search Upline ID";
            public final static String SEARCH_MAPPED_ACCOUNT_ID = "Search ID";
        }

        public static class NetProfit {
            public final static String TITLE = "Net Profit report";
            public final static String YEAR = "Year";
            public final static String BRAND = "Brand";
            public final static String SEARCH = "Search";
            public final static String NOTE = "Report data is available since January 2020.";
            public final static List<String> LST_HEADER = Arrays.asList("Month", "Total Wager", "Turnover(HKD)", "Member", "CO", "PO", "Follow Bets", "Pay To Provider", "Profit", ""
                    , "Win/Loss (HKD)", "Tax (HKD)", "Win/Loss (HKD)", "Win/Loss (HKD)", "Tax (HKD)", "Total (HKD)");
        }

        public static class NetProfitConfiguration {
            public final static String TITLE = "Net Profit Configuration";
            public final static String INR_HKD_RATE = "INR to HKD Rate";
            public final static String AA_RATE = "AA Rate";
            public final static String SAT_RATE = "SAT Rate";
            public final static String CASINO_RATE = "Casino Virtual Currency Provider Rate";
            public final static String EXCLUDE_AG = "Exclude Agent";
            public final static String SPECIAL_SETTING = "Special Setting";
            public final static List<String> LST_INRtoHKD__HEADER = Arrays.asList("Rate", "Last Update Date", "Last Update By");
            public final static List<String> LST_AA_RATE__HEADER = Arrays.asList("Username", "Client", "Currency", "Rate", "Operator", "Player", "Portal", "Profit");
            public final static List<String> LST_SAT_RATE__HEADER = Arrays.asList("Username", "Currency", "Deal Rate (INR)", "Operator");
            public final static List<String> LST_EXCLUDE_AGENT__HEADER = Arrays.asList("Brand", "UserName", "Login ID", "Currency", "Upline", "Last Update By", "Last Update Date", "Operator");
        }
        public static class PunterPerformance {
            public final static List<String> LST_HEADER = Arrays.asList("User Name", "Brand","Level","Currency","Total Wager","Win","Lose","Draw","Avg Stake\n(HKD)","(HKD)","(F)","Win%","Turnover","Win/Loss","Tax/Comm","Total","Turnover","Win/Loss","Tax/Comm","Total");
        }
    }

    public static class Tools {
        public static class BetFairAccountInfo {
            public final static String TITLE = "BetFair Account Info";
            public final static List<String> LST_HEADER = Arrays.asList("No.", "Account Name", "Currency", "Exchange", "Exchange Games", "", "Available Balance", "Exposure Limit", "Current Exposure", "Change Log", "Available Balance", "Exposure Limit", "Current Exposure", "Change Log", "Force Login");
        }

        public static class APIPlayer {
            public final static String TITLE = "API Player";
            public final static String MSG_SUCCESS = "User %s has been %s successful";
        }

        public static class PositionTakingConfiguration {
            public final static String TITLE = "Position Taking Configuration";
            public final static String LBL_NOTE = "This page is to display the accounts whose downlines are disabled PT. The Downline PT Enabled value is not editable";
            public final static String TXT_USERNAME_PLACEHOLDER = "Username / Login ID";
            public final static String BTN_SEARCH = "Search";
            public final static List<String> TBL_HEADER = Arrays.asList("No.", "User Name", "Login ID", "Level", "Account Status", "Downline PT Enabled", "Brand", "Line", "Update By", "Update Date");
        }
    }

    public static class AdminManagement {
        public static class AdminUserManagement {
            public final static String TITLE = "Admin User Management";
            public final static String SUCCESS_MSG = "The user with login id %s has been processed successfully.";
        }

        public static class CryptoAccessManagement {
            public final static String TITLE = "Crypto Access Management";
            public final static String FAILED_MSG = "User %s is not in Funsport brand";
        }

        public static class AtlanticAccessManagement {
            public final static String TITLE = "Atlantic Access Management";
            public final static String FAILED_MSG = "User %s is not in Fairenter brand";
        }
    }

}
