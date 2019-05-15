package stand.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import stand.dto.ProductDto;
import javax.ejb.Stateless;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.List;


@Stateless
public class RestServiceImpl implements RestService {

    private static final Logger logger = Logger.getLogger(RestServiceImpl.class);

    @Override
    public List<ProductDto> getTopProducts(){
        Client client = ClientBuilder.newClient();
        WebTarget wt = client.target("http://localhost:8080/store/product/top");
        Response response = wt.request(MediaType.APPLICATION_JSON_TYPE).get();

        if (response.getStatus() == 200) {
            String jsonResult = response.readEntity(String.class);
            Gson gson = new Gson();
            Type typeToken = new TypeToken<List<ProductDto>>() {}.getType();
            List<ProductDto> topProducts = gson.fromJson(jsonResult, typeToken);
            response.close();
            logger.info("Receive products successfully");
            return topProducts;
        }
        response.close();
        logger.warn("Couldn't receive products");
        return null;
    }
}
