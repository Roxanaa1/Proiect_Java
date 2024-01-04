package org.example.repository;
import org.example.model.entities.AnimalEntity;
import org.example.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
@Component
public interface AnimalRepository extends JpaRepository<AnimalEntity,Long>
{
    Optional<AnimalEntity> findById(Long id);
//    @Query(value = "SELECT * FROM animals u WHERE u.id = :id")
//    Optional<AnimalEntity> findById(@Param("id") Long id);
}

