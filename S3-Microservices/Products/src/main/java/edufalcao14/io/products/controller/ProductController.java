package edufalcao14.io.products.controller;


import edufalcao14.io.products.model.Product;
import edufalcao14.io.products.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ProductController {

  private final ProductService service;

  public ProductController(ProductService service) {
    this.service = service;
  }

  /**
   * Read all products
   *
   * @request GET /products
   * @response 200 : return all products
   */
  @GetMapping("/product")
  public Iterable<Product> readAllPRoducts() {
    return service.readAll();
  }

  /**
   * Read all products
   *
   * @request GET /products
   * @response 200 : return all products
   */
  @GetMapping("/product/{id}")
  public Product readOne(@PathVariable int id) {
    return service.readOne(id);
  }

  /**
   * Read all products
   *
   * @request GET /products
   * @response 200 : return all products
   */
  @PostMapping("/product")
  public ResponseEntity<Void> createOne(@RequestBody Product product) {
    if (product.invalid()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    if (service.createOne(product) == false) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    service.createOne(product);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @DeleteMapping("/product/{id}")
  public void deleteOne(@PathVariable int id) {

    if (service.deleteOne(id) == false) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    service.deleteOne(id);
  }

  @PutMapping("/product/{id}")
  public void updateOneProduct(@PathVariable int id, @RequestBody Product product) {
    if (product.invalid()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    if (!service.updateOneProduct(id, product)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    service.updateOneProduct(id, product);
  }
}
