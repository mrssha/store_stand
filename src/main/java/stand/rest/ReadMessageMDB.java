package stand.rest;


import stand.controller.ProductController;
import stand.controller.PushBean;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;


@MessageDriven(name = "RateMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/productsQueue"),
        //@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class ReadMessageMDB implements MessageListener {

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
            productController.updateProducts();
            pushBean.sendMessage(message);
        }
        }
        catch (JMSException e){
            e.printStackTrace();
        }

    }
}