package org.example.repository;
import org.example.model.entities.AnimalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
@Repository
@Component
public interface AnimalRepository extends JpaRepository<AnimalEntity,Long>
{

}

