package stand.bean;

import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ApplicationScoped
public class PushBean {

    @Inject
    @Push
    private PushContext updateChanel;

    public void sendMessage(String message) {
        updateChanel.send(message);
    }
}
