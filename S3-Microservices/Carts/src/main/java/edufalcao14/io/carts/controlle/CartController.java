package edufalcao14.io.carts.controlle;

import edufalcao14.io.carts.models.CartItem;
import edufalcao14.io.carts.service.CartService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CartController {
  private final CartService cartService;

  public CartController(CartService service) {
    this.cartService = service;
  }

  @DeleteMapping("/products/{productId}")
  public void deleteProductFromAllCarts(@PathVariable int productId) {
    boolean isDeleted = cartService.deleteProductFromAllCarts(productId);
    if( !isDeleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/carts/users/{pseudo} ")
  public Iterable<CartItem> readOne(@PathVariable String pseudo) {
    Iterable<CartItem> carts = cartService.getCart(pseudo);
    if (carts == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    else return carts;
  }

  @DeleteMapping("/carts/users/{pseudo} ")
  public void  deleteOne(@PathVariable String pseudo) {
   boolean deleted = cartService.deleteUserCart(pseudo);
    if (!deleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping("/carts/users/{pseudo}/products/{productId} ")
  public ResponseEntity<Void>  deleteProductFromCart(@PathVariable String pseudo , @PathVariable int productId) {
    boolean deleted = cartService.removeCartItem(pseudo,productId);
    if(!deleted) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/carts/users/{pseudo}/products/{productId} ")
  public ResponseEntity<Void>  addProductToCart(@PathVariable String pseudo , @PathVariable int productId) {
    boolean added = cartService.addCartItem(pseudo,productId);

    if(!added){
      System.out.println("NOT FOUND");
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
