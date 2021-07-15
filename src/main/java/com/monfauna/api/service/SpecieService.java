package com.monfauna.api.service;

import com.monfauna.api.exception.InvalidResourceException;
import com.monfauna.api.exception.NotFoundException;
import com.monfauna.api.model.Specie;
import com.monfauna.api.model.SpecieType;
import com.monfauna.api.repository.SpecieRepository;
import com.monfauna.api.repository.SpecieTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SpecieService {

    @Autowired
    private SpecieRepository specieRepository;
    @Autowired
    private SpecieTypeRepository specieTypeRepository;

    public List<Specie> findAll() {
        return specieRepository.findAll();
    }

    public Specie findById(Integer id) {
        Optional<Specie> specie = specieRepository.findById(id);
        if (specie.isEmpty()) {
            throw new NotFoundException("Specie Not Found");
        } else {
            return specie.get();
        }
    }

    public Specie save(Specie specie) {

        if (specie.getSpecieType() == null || specie.getSpecieType().getId() == null) {
            throw new InvalidResourceException("Specie type is required");
        } else {
            SpecieType st = getSpecieType(specie.getSpecieType().getId());
            specie.setSpecieType(st);
        }

        if (specie.getCommonName() == null || specie.getCommonName().isBlank()) {
            throw new InvalidResourceException("Common name cannot be empty");
        }

        specie = specieRepository.save(specie);
        return this.findById(specie.getId());
    }

    public Specie update(Specie specieUpdated, Integer id) {
       Specie specie = this.findById(id);
       if (specieUpdated.getCommonName() != null) {
           specie.setCommonName(specieUpdated.getCommonName());
       }
       if (specieUpdated.getScientificName() != null) {
           specie.setScientificName(specieUpdated.getScientificName());
       }
       if (specieUpdated.getSpecieType() != null && specieUpdated.getSpecieType().getId() != null) {
           SpecieType st = getSpecieType(specieUpdated.getSpecieType().getId());
           specie.setSpecieType(st);
       }
       specie.setUpdatedAt(LocalDateTime.now());
        specie = specieRepository.save(specie);
        return this.findById(specie.getId());
    }

    public void delete(Integer id) {
        Specie specie = this.findById(id);
        specieRepository.delete(specie);
    }

    private SpecieType getSpecieType(Integer id) {
        Optional<SpecieType> specieType = specieTypeRepository.findById(id);
        if (specieType.isEmpty()) {
            throw new NotFoundException("Specie Type Not Found");
        } else {
            return specieType.get();
        }
    }
}
