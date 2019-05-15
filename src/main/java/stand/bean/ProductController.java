package stand.bean;


import stand.dto.ProductDto;
import stand.rest.RestService;
import stand.rest.RestServiceImpl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;


@Named("products")
@ApplicationScoped
public class ProductController {

    @Inject
    private RestService restService;

    private List<ProductDto> topProducts;

    @PostConstruct
    public void init(){
        topProducts = restService.getTopProducts();
    }

    public List<ProductDto> getTopProducts(){
        return topProducts;
    }


    public void updateProducts(){
        topProducts = restService.getTopProducts();
    }

}
