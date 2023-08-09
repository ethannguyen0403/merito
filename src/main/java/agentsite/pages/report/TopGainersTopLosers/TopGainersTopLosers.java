package agentsite.pages.report.TopGainersTopLosers;

import java.util.ArrayList;
import java.util.List;

public class TopGainersTopLosers {

    public boolean isTopGainersHeaderTable(){return false;}
    public boolean isTopLosersHeaderTable(){return false;}
    public boolean isBigStakeHeaderTable(){return false;}
    public boolean isCheckUserDisplayInTopGainersTableCorrect(List<ArrayList<String>> winner) {return false;}

    public boolean isCheckUserDisplayInBigStakeTableCorrect(String bigStake) {return false;}

    public boolean isCheckUserDisplayInTopLosersTableCorrect(List<ArrayList<String>> loser) {return false;}
}
