package membersite.objects.sat;

import java.util.List;

/**
 * @author isabella.huynh
 * @created 10/3/2020
 */
public class BookmakerEvent {
    private String _eventName;
    private String _eventId;
    private String _competitionName;
    private List<BookmakerMarket1> _bmMarket;

    public BookmakerEvent(Builder builder) {

        this._eventName = builder._eventName;
        this._eventId = builder._eventId;
        this._competitionName = builder._competitionName;
        this._bmMarket = builder._bmMarket;
    }

    public String getEventName() {
        return _eventName;
    }

    public String getEventID() {
        return _eventId;
    }

    public void setEventID(String val) {
        _eventId = val;
    }

    public String getCompetitionName() {
        return _competitionName;
    }

    public void setCompetitionName(String val) {
        _competitionName = val;
    }

    public List<BookmakerMarket1> getBMMarket() {
        return _bmMarket;
    }

    public void setBMMarket(List<BookmakerMarket1> val) {
        _bmMarket = val;
    }

    public void setEventNam(String val) {
        _eventName = val;
    }

    public BookmakerMarket1 getMarketStatus(String status) {
        List<BookmakerMarket1> lstBMMarket = this.getBMMarket();
        for (int i = 0; i < lstBMMarket.size(); i++) {
            if (lstBMMarket.get(i).getMarketStatus().equalsIgnoreCase(status))
                return lstBMMarket.get(i);
        }
        System.out.println(String.format("The event %s has no Bookmaker market available as status %s", this.getEventName(), status));
        return null;
    }

    public static class Builder {
        // Optional parameters
        private String _eventName = "";
        private String _eventId = "";
        private String _competitionName = "";
        private List<BookmakerMarket1> _bmMarket;

        public Builder() {
        }

        public Builder eventID(String val) {
            _eventId = val;
            return this;
        }

        public Builder competitionNAme(String val) {
            _competitionName = val;
            return this;
        }

        public Builder eventName(String val) {
            _eventName = val;
            return this;
        }

        public Builder bmMarket(List<BookmakerMarket1> val) {
            _bmMarket = val;
            return this;
        }

        public BookmakerEvent build() {
            return new BookmakerEvent(this);
        }
    }
}
