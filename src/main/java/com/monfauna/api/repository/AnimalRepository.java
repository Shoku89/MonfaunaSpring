package com.monfauna.api.repository;

import com.monfauna.api.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    List<Animal> findByProjectId(Integer idProject);

    Optional<Animal> findByIdAndProjectId(Integer idAnimal, Integer idProject);
}
