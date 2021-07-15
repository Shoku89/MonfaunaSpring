package com.monfauna.api.repository;

import com.monfauna.api.model.Specie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecieRepository extends JpaRepository<Specie, Integer> {
}
