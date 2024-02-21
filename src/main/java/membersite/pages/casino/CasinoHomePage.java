package membersite.pages.casino;

import com.paltech.driver.DriverManager;
import membersite.pages.LandingPage;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class CasinoHomePage extends LandingPage {
    private CasinoProduct product;

    public SupernowaCasino supernowa;
    public Pragmatic pragmatic;
    public LotterySlots lotterySlots;
    public Evolution evolution;
    public LiveDealerAsian dealerAsian;

    public CasinoProduct getCasinoProduct(){
        return product;
    }

    public CasinoHomePage(String types, CasinoProduct product) {
        super(types);
        this.product = product;
        loadCasinoPageGame(product);
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
            default:
                break;
        }
    }

    public boolean verifyConsoleLogNotContainValue(List<String> values) {
        List<LogEntry> entriesConsole = DriverManager.getDriver().manage().logs().get(LogType.BROWSER).getAll();
        for (String value : values) {
            for (LogEntry entry : entriesConsole) {
                if (entry.getMessage().contains(value)) {
                    System.out.println("Log console contains the value: " + value);
                    return false;
                }
            }
        }
        return true;
    }

}
