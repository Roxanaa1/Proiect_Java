package org.example.repository;
import org.example.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstName(String firstName); //query method
    Optional<User> findById(Long id);
    List<User> findByFirstNameAndLastName(String firstName, String lastName);

//    @Query(value = "SELECT * FROM users u WHERE u.username = :username", nativeQuery = true)
//    List<User> findByUserName(@Param("username") String username);
//
//    @Query(value = "SELECT u FROM users u WHERE u.username = :username")
//    List<User> findByAge(@Param("username") String username);

}