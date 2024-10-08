package edufalcao14.io.amazings1.data;

import edufalcao14.io.amazings1.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends CrudRepository<Product , Integer> {

}
