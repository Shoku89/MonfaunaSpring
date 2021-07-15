package com.monfauna.api.repository;

import com.monfauna.api.model.SpecieType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecieTypeRepository extends JpaRepository<SpecieType, Integer> {
}
