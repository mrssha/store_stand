package stand.bean;

import org.apache.log4j.Logger;
import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ApplicationScoped
public class PushBean {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(PushBean.class);

    @Inject
    @Push
    private PushContext updateChanel;

    public void sendMessage(String message) {
        updateChanel.send(message);
        logger.info("Made push");
    }
}
