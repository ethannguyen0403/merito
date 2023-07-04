package api.objects.meritoAPI.result;

import api.objects.meritoAPI.Competition;

import java.util.List;

public class CompetitionResult extends ResultObject {
    private List<Competition> _competitionList;

    public CompetitionResult(Builder builder) {
        this._isSuccess = builder._isSuccess;
        this._competitionList = builder._competitionList;
    }

    public List<Competition> getCompetitionList() {
        return _competitionList;
    }

    public void setCompetitionList(List<Competition> val) {
        _competitionList = val;
    }

    public static class Builder {
        private boolean _isSuccess;
        private List<Competition> _competitionList;

        public Builder() {
        }

        public Builder isSuccess(boolean val) {
            _isSuccess = val;
            return this;
        }

        public Builder competitionList(List<Competition> val) {
            _competitionList = val;
            return this;
        }

        public CompetitionResult build() {
            return new CompetitionResult(this);
        }
    }
}
