package org.example.repository;
import org.example.model.entities.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long>
{
    Optional<Adoption> findById(Long id);
}
