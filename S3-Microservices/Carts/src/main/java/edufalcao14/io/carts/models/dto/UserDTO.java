package edufalcao14.io.carts.models.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDTO {
  private String pseudo;
  private String firstname;
  private String lastname;
}
