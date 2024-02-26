package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import membersite.pages.LandingPage;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static common.MemberConstants.Casino.CASINO_PRODUCTS_SUFFIX_LINK;

public class CasinoHomePage extends LandingPage {
    private CasinoProduct product;

    public SupernowaCasino supernowa;
    public Pragmatic pragmatic;
    public LotterySlots lotterySlots;
    public Evolution evolution;
    public LiveDealerAsian dealerAsian;
    public EvolutionWhiteCliff evolutionWhiteCliff;

    public CasinoProduct getCasinoProduct(){
        return product;
    }

    public CasinoHomePage(String types, CasinoProduct product) {
        super(types);
        this.product = product;
        loadCasinoPageGame(product);
    }

    public static String getURLCasino(CasinoProduct product){
        String currentURL = DriverManager.getDriver().getCurrentUrl();
        Pattern pattern = Pattern.compile("(.*\\/home\\/)");
        Matcher matcher = pattern.matcher(currentURL);
        matcher.find();
        return matcher.group(0) + CASINO_PRODUCTS_SUFFIX_LINK.get(product.toString());
    }

    private void loadCasinoPageGame(CasinoProduct product) {
        switch (product) {
            case EVOLUTION:
                evolution = new Evolution();
                break;
            case ION:
            case VIVO:
            case QTECH:
            case GAME_HALL:
            case PRAGMATIC:
                pragmatic = new Pragmatic();
                break;
            case SUPERNOWA_CASINO:
                supernowa = new SupernowaCasino();
                break;
            case LOTTERY_SLOTS:
                lotterySlots = new LotterySlots();
                break;
            case LIVE_DEALER_ASIAN:
                dealerAsian = new LiveDealerAsian();
                break;
            case LIVE_DEALER_EUROPEAN:
            case EVOLUTION_WHITE_CLIFF:
                evolutionWhiteCliff = new EvolutionWhiteCliff();
                break;
            default:
                break;
        }
    }

    public boolean verifyConsoleLogNotContainValue(List<String> values) {
        List<LogEntry> entriesConsole = DriverManager.getDriver().manage().logs().get(LogType.BROWSER).getAll();
        for (String value : values) {
            for (LogEntry entry : entriesConsole) {
                // Get logs that are the errors(WARNING, SEVERE)
                if(entry.getLevel().equals(Level.WARNING) || entry.getLevel().equals(Level.SEVERE)){
                    if (entry.getMessage().contains(value)) {
                        System.out.println("Log console contains the value: " + value);
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public List<String> getConsoleLog(String value){
        List<String> consoleLog = new ArrayList<>();
        try{
            Thread.sleep(3000);
        }catch (Exception e){
        }
        List<LogEntry> entriesConsole = DriverManager.getDriver().manage().logs().get(LogType.BROWSER).getAll();
        for (LogEntry entry : entriesConsole) {
            if (entry.getMessage().contains(value)) {
                consoleLog.add(entry.getMessage());
            }
        }
        return consoleLog;
    }
}
