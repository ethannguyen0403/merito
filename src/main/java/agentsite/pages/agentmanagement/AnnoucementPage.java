package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.agentmanagement.announcement.Announcement;
import agentsite.pages.components.ComponentsFactory;

public class AnnoucementPage extends HomePage {
    public Announcement announcementPage;
    public AnnoucementPage(String types) {
        super(types);
        _type = types;
        announcementPage = ComponentsFactory.announcementPage(_type);
    }
}
