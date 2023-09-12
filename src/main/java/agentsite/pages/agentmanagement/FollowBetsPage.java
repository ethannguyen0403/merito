package agentsite.pages.agentmanagement;

import agentsite.pages.HomePage;
import agentsite.pages.components.ComponentsFactory;
import agentsite.pages.agentmanagement.followbets.FollowBets;

public class FollowBetsPage extends HomePage {
    public FollowBets followBets;
    public FollowBetsPage(String types) {
        super(types);
        followBets = ComponentsFactory.followBets(types);
    }

}
