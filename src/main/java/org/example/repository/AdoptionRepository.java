package org.example.repository;
import org.example.model.entities.Adoption;
import org.example.model.entities.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long>
{
    Optional<Adoption> findById(Long id);
//    @Query(value = "SELECT * FROM animals u WHERE u.id = :id")
//    Optional<Adoption> findById(@Param("id") Long id);
}
