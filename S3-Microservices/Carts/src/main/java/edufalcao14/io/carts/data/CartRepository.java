package edufalcao14.io.carts.data;

import edufalcao14.io.carts.models.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository  extends CrudRepository<CartItem,Integer> {
  CartItem findByPseudoAndProductId(String pseudo, int productId);
  CartItem findByPseudo(String pseudo);
  Iterable<CartItem> findAllByPseudo(String pseudo);
  void deleteAllByPseudo(String pseudo);
  void deleteAllByProductId(int productId);
}
