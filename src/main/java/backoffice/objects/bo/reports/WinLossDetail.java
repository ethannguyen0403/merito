package backoffice.objects.bo.reports;

/**
 * @author Liam.Ho
 * @created Dec/31/2019
 */
public class WinLossDetail {
    private int _totalWager;
    private double _localTurnover;
    private double _turnOver;

    public int getTotalWager() {
        return _totalWager;
    }

    public double getLocalTurnover() {
        return _localTurnover;
    }

    public double getTurnOver() {
        return _turnOver;
    }

    public static class Builder {
        // Optional parameters
        private int _totalWager = 0;
        private double _localTurnover = 0;;
        private double _turnOver = 0;;

        public Builder(){}

        public Builder totalWager(int val){
            _totalWager = val;
            return this;
        }

        public Builder localTurnover(double val){
            _localTurnover = val;
            return this;
        }

        public Builder turnOver(double val){
            _turnOver = val;
            return this;
        }

        public WinLossDetail build() { return new WinLossDetail (this); }

    }

    private WinLossDetail(Builder builder){
        this._totalWager = builder._totalWager;
        this._localTurnover = builder._localTurnover;
        this._turnOver = builder._turnOver;
    }
}
