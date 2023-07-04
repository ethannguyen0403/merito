package api.objects.meritoAPI.result;

import api.objects.meritoAPI.Event;

import java.util.List;

public class EventListResult extends ResultObject {
    private List<Event> _eventList;

    public EventListResult(Builder builder) {
        this._isSuccess = builder._isSuccess;
        this._eventList = builder._eventList;
    }

    public List<Event> getEventList() {
        return _eventList;
    }

    public void setEventList(List<Event> val) {
        _eventList = val;
    }

    public static class Builder {
        private boolean _isSuccess;
        private List<Event> _eventList;

        public Builder() {
        }

        public Builder isSuccess(boolean val) {
            _isSuccess = val;
            return this;
        }

        public Builder eventList(List<Event> val) {
            _eventList = val;
            return this;
        }

        public EventListResult build() {
            return new EventListResult(this);
        }
    }
}
