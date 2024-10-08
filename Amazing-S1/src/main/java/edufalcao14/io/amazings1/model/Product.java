package edufalcao14.io.products.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "products")
public class Product {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String category;

  @Column(nullable = false)
  private double price;

  public boolean invalid() {
    return name == null || name.isBlank() ||
        category == null || category.isBlank() ||
        price < 0;
  }


}
