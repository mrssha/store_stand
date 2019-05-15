package stand.bean;

import org.apache.log4j.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;


@MessageDriven(name = "RateMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/productsQueue")
})
public class ReadMessageMDB implements MessageListener {

    private static final Logger logger = Logger.getLogger(ReadMessageMDB.class);

    @Inject
    private PushBean pushBean;

    @Inject
    private ProductController productController;

    @Override
    public void onMessage(Message rcvMessage) {
        try{
        TextMessage msg =(TextMessage) rcvMessage;
        String message = msg.getText();

        if (message.equals("update list")){
            logger.info("JMS message: update list");
            productController.updateProducts();
            pushBean.sendMessage(message);
        }
        }
        catch (JMSException e){
            logger.warn("Connection fail");
            e.printStackTrace();
        }
    }
}