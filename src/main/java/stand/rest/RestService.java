package stand.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import stand.dto.ProductDto;

import javax.ejb.Stateless;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

@Stateless
public class RestService implements Serializable {


    public List<ProductDto> getTopProducts(){
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/store/product/top");
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();


        if (response.getStatus() == 200) {
            String jsonResult = response.readEntity(String.class);
            response.close();
            Gson gson = new Gson();
            Type typeToken = new TypeToken<List<ProductDto>>() {}.getType();
            List<ProductDto> topProducts = gson.fromJson(jsonResult, typeToken);
            return topProducts;
        }
        response.close();
        return null;

    }






}