package uow.itpm.teamblue.model.repo;

import org.springframework.data.repository.CrudRepository;
import uow.itpm.teamblue.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
    public User findByEmail(String email);
    public User findByUsername(String username);

}
