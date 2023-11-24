package org.example.repository;
import org.example.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstName(String firstName); //query method

//    @Query(value = "SELECT * FROM users u WHERE u.username = :username", nativeQuery = true)
//    List<User> findByUserName(@Param("username") String username);
//
//    @Query(value = "SELECT u FROM users u WHERE u.username = :username")
//    List<User> findByAge(@Param("username") String username);

}