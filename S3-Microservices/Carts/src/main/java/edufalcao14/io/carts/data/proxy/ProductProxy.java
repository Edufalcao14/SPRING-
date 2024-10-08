package edufalcao14.io.carts.data.proxy;

import edufalcao14.io.carts.models.dto.ProductDTO;
import edufalcao14.io.carts.models.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
@FeignClient(name = "products")
public interface ProductProxy {

  @GetMapping("/products/{id}")
  ProductDTO readOne(int id);
}
