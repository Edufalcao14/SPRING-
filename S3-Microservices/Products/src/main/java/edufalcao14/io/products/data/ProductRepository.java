package edufalcao14.io.products.data;


import edufalcao14.io.products.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends CrudRepository<Product, Integer> {

}
