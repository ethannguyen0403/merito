package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.eventbetsizesetting.EventBetSizeSetting;
import agentsite.pages.components.ComponentsFactory;

public class EventBetSizeSettingsPage extends HomePage {

    public EventBetSizeSetting eventBetSizeSetting;
    public EventBetSizeSettingsPage(String types) {
        super(types);
        _type = types;
        eventBetSizeSetting = ComponentsFactory.eventBetSizeSettingPage(_type);
    }

}
