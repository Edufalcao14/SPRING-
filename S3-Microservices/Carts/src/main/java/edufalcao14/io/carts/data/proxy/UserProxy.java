package edufalcao14.io.carts.data.proxy;

import edufalcao14.io.carts.models.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

@Repository
@FeignClient(name = "users")
public interface UserProxy {

  @GetMapping("/users/{pseudo}")
  UserDTO readOne(String pseudo);
}
