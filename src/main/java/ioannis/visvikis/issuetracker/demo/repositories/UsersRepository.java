package ioannis.visvikis.issuetracker.demo.repositories;

import ioannis.visvikis.issuetracker.demo.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {

}
