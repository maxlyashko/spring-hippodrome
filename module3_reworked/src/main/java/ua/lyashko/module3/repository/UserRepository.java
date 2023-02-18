package ua.lyashko.module3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lyashko.module3.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
