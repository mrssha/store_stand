package stand.controller;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

import javax.inject.Inject;

public class PushBean {

    @Inject
    @Push
    private PushContext updateChanel;

    public void sendMessage(String message) {
        updateChanel.send(message);
    }
}
