package backoffice.objects.bo.marketmanagement;


public class Event {
    private String _eventId;
    private String _eventName;
    private String _eventStartTime;
    private String _eventStatus;

    private Event(Builder builder) {
        this._eventId = builder._eventId;
        this._eventName = builder._eventName;
        this._eventStartTime = builder._eventStartTime;
        this._eventStatus = builder._eventStatus;
    }

    public String getEventId() {
        return _eventId;
    }

    public String getEventName() {
        return _eventName;
    }

    public String getEventStartTime() {
        return _eventStartTime;
    }
    public String getEventStatus() {
        return _eventStatus;
    }
    public static class Builder {
        // Optional parameters
        private String _eventId = "";
        private String _eventName = "";
        private String _eventStartTime = "";
        private String _eventStatus = "";

        public Builder() {
        }

        public Builder eventId(String val) {
            _eventId = val;
            return this;
        }

        public Builder eventName(String val) {
            _eventName = val;
            return this;
        }

        public Builder eventStartTime(String val) {
            _eventStartTime = val;
            return this;
        }
        public Builder eventStatus(String val) {
            _eventStatus = val;
            return this;
        }
        public Event build() {
            return new Event(this);
        }

    }
}
