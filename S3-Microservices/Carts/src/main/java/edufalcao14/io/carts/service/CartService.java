package edufalcao14.io.carts.service;

import edufalcao14.io.carts.data.CartRepository;
import edufalcao14.io.carts.data.proxy.ProductProxy;
import edufalcao14.io.carts.data.proxy.UserProxy;
import edufalcao14.io.carts.models.CartItem;
import feign.FeignException;
import org.springframework.stereotype.Service;

@Service
public class CartService {
private final CartRepository cartRepository;
private final ProductProxy productProxy;
private final UserProxy userProxy;

  public CartService(CartRepository cartRepository, ProductProxy productProxy, UserProxy userProxy) {
    this.cartRepository = cartRepository;
    this.productProxy = productProxy;
    this.userProxy = userProxy;
  }

  /**
   *
   * Checks if a user exist in the users service
   * @param pseudo Pseudo of the user
   * @return true if the user does not exist , fase otherwise
   */
  public boolean userNotExist(String pseudo){
    try {
      userProxy.readOne(pseudo);
      return false;
    }catch (FeignException.FeignClientException e ){
      if(e.status() == 404) return true;
      else throw e;
    }
}
  /**
   *
   * Checks if a product exist in the users service
   * @param id Pseudo of the user
   * @return true if the product does not exist , fase otherwise
   */
  public boolean productNotExist(int id){
    try {
      productProxy.readOne(id);
      return false;
    }catch (FeignException.FeignClientException e ){
      if(e.status() == 404) return true;
      else throw e;
    }
  }
  /**
   * Add a product in user's ( pseudo) cart
   * @param pseudo the user's indentificator
   * @param productId the product's indentification
   * @return true if the producted was added to user's cart, false if not
   */
  public boolean addCartItem(String pseudo , int productId) {
    if(userNotExist(pseudo) || productNotExist(productId)) return false;
    CartItem cartItem = new CartItem();
    cartItem.setPseudo(pseudo);
    cartItem.setProductId(productId);
    cartRepository.save(cartItem);
    return true;
  }
  /**
   * remove a product from user's ( pseudo) cart
   * @param pseudo the user's indentificator
   * @param productId the product's indentification
   * @return true if the producted was removed to user's cart, false if not
   */
  public boolean removeCartItem(String pseudo , int productId) {
    if(!userNotExist(pseudo) || !productNotExist(productId)) return false;

    CartItem cartItem = cartRepository.findByPseudoAndProductId(pseudo, productId);
    if(cartItem==null) return false;

    cartRepository.delete(cartItem);
    return true;
  }

  /**
   * get all product from user's ( pseudo) cart
   * @param pseudo the user's indentificator
   * @return all the products from user's cart , or null if user doesn't exist
   */
  public Iterable<CartItem> getCart(String pseudo ) {
    if(userNotExist(pseudo)) return null;
    return cartRepository.findAllByPseudo(pseudo);
  }

  /**
   * remove  user's ( pseudo) cart
   * @param pseudo the user's indentificator
   * @return true if the cart was deleted , false if not
   */
  public boolean deleteUserCart(String pseudo ) {
    if(userNotExist(pseudo)) return false;
    cartRepository.deleteAllByPseudo(pseudo);
    return true;
  }

  /**
   * remove the product from all the user's cart
   * @param productId the product's indentificator
   * @return true if the product was deleted from cart, false if not
   */
  public boolean deleteProductFromAllCarts(int productId ) {
    if(productNotExist(productId)) return false;
    cartRepository.deleteAllByProductId(productId);
    return true;
  }

}
