package membersite.objects.proteus;

import com.paltech.element.common.Label;

public class ProteusEvent {
    private int _leagueId;
    private int _eventId;
    private String _leagueName;
    private String _homeName;
    private String _awayName;
    private String _hdpPoint;
    private Label _btnFirstSelection;
    private Label _btnSecondSelection;
    private Label _btnThirdSelection;
    public ProteusEvent(Builder builder) {
        this._leagueId = builder._leagueId;
        this._eventId = builder._eventId;
        this._leagueName = builder._leagueName;
        this._homeName = builder._homeName;
        this._awayName = builder._awayName;
        this._hdpPoint = builder._hdpPoint;
        this._btnFirstSelection = builder._btnFirstSelection;
        this._btnSecondSelection = builder._btnSecondSelection;
        this._btnThirdSelection = builder._btnThirdSelection;
    }
    public int getEventId() {
        return _eventId;
    }

    public void setEventId(int val) {
        _eventId = val;
    }

    public int getLeagueId() {
        return _leagueId;
    }

    public void setLeagueId(int val) {
        _leagueId = val;
    }

    public void setLeagueName(String val) {
        _leagueName = val;
    }

    public String getLeagueName() {
        return _leagueName;
    }
    public void setHomeName(String val) {
        _homeName = val;
    }

    public String getHomeName() {
        return _homeName;
    }
    public void setAwayName(String val) {
        _awayName = val;
    }

    public String getAwayName() {
        return _awayName;
    }
    public void setHDPPoint(String val) {
        _hdpPoint = val;
    }

    public String getHDPPoint() {
        return _hdpPoint;
    }
    public void setBtnFirstSelection(Label val) {
        _btnFirstSelection = val;
    }

    public Label getBtnFirstSelection() {
        return _btnFirstSelection;
    }
    public void setBtnSecondSelection(Label val) {
        _btnSecondSelection = val;
    }

    public Label getBtnSecondSelection() {
        return _btnSecondSelection;
    }
    public void setBtnThirdSelection(Label val) {
        _btnThirdSelection = val;
    }

    public Label getBtnThirdSelection() {
        return _btnThirdSelection;
    }

    public static class Builder {
        // Optional parameters
        private int _eventId;
        private int _leagueId;
        private String _leagueName;
        private String _homeName;
        private String _awayName;
        private String _hdpPoint;
        private Label _btnFirstSelection;
        private Label _btnSecondSelection;
        private Label _btnThirdSelection;

        public Builder() {
        }

        public Builder eventId(int val) {
            _eventId = val;
            return this;
        }

        public Builder leagueId(int val) {
            _leagueId = val;
            return this;
        }

        public Builder leagueName(String val) {
            _leagueName = val;
            return this;
        }

        public Builder homeName(String val) {
            _homeName = val;
            return this;
        }

        public Builder awayName(String val) {
            _awayName = val;
            return this;
        }

        public Builder hdpPoint(String val) {
            _hdpPoint = val;
            return this;
        }

        public Builder btnFirstSelection(Label val) {
            _btnFirstSelection = val;
            return this;
        }

        public Builder btnSecondSelection(Label val) {
            _btnSecondSelection = val;
            return this;
        }

        public Builder btnThirdSelection(Label val) {
            _btnThirdSelection = val;
            return this;
        }

        public ProteusEvent build() {
            return new ProteusEvent(this);
        }

    }
}
