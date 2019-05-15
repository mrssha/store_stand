package stand.rest;

import stand.dto.ProductDto;

import java.util.List;

public interface RestService {

    List<ProductDto> getTopProducts();
}
