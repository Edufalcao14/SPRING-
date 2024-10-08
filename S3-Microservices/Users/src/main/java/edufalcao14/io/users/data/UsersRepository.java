package edufalcao14.io.users.data;

import edufalcao14.io.users.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, String> {
}
