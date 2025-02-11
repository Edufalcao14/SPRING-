package edufalcao14.io.carts.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ProductDTO {

  private int id;
  private String name;
  private String category;
  private double price;
}
