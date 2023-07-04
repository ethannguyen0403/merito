package membersite.pages;


import membersite.objects.sat.Event;
import membersite.utils.betplacement.BetUtils;

public class SportPage extends HomePage {
    public SportPage(String types) {
        super(types);
    }

    public Event getEvent(boolean isInplay, boolean isSuspend, int limit, int eventIndex) {
        String app = BetUtils.getAppName();
        if (app.contains("satsport"))
            return eventContainerControl.getEvent(isInplay, isSuspend, limit, eventIndex);
        return eventContainerControl.getEvent(isInplay, isSuspend, limit, eventIndex);
    }


}
