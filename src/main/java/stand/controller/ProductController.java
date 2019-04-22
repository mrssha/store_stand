package stand.controller;


import stand.dto.ProductDto;
import stand.rest.RestService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;


@Named("products")
@ApplicationScoped
public class ProductController {

    @Inject
    private RestService restService;

    private List<ProductDto> topProducts;

    public List<ProductDto> getTopProducts(){
        topProducts =  restService.getTopProducts();
        return topProducts;
    }



}
