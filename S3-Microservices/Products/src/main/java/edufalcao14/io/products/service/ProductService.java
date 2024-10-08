package edufalcao14.io.products.service;


import edufalcao14.io.products.data.ProductRepository;
import edufalcao14.io.products.model.Product;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductService {

  private final ProductRepository repository;

  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }
  public Iterable<Product> readAll() {
    return repository.findAll();
  }

  public Product readOne(int id) {
    Optional<Product> product = repository.findById(id);
    if (product.isEmpty()) return null;
    return product.get();
  }

  public boolean createOne(Product product) {
    if(repository.existsById(product.getId())) return false;
    repository.save(product);
    return true;
  }

  public boolean deleteOne(int id) {
    if (!repository.existsById(id)) {
      return false;
    }

    repository.deleteById(id);
    return true;
  }

  public boolean updateOneProduct(int id, Product product) {
    if (!repository.existsById(id)) {
      return false;
    }
    repository.deleteById(id);
    product.setId(id);
    repository.save(product);

    return true;
  }
}
