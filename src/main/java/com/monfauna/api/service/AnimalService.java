package com.monfauna.api.service;

import com.monfauna.api.exception.InvalidResourceException;
import com.monfauna.api.exception.NotFoundException;
import com.monfauna.api.model.Animal;
import com.monfauna.api.model.Location;
import com.monfauna.api.model.Project;
import com.monfauna.api.model.Specie;
import com.monfauna.api.repository.AnimalRepository;
import com.monfauna.api.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private SpecieService specieService;
    @Autowired
    private LocationService locationService;

    public List<Animal> findAllAnimalsByProject(Integer idProject) {
        projectService.findById(idProject);
        return animalRepository.findByProjectId(idProject);
    }

    public Animal findById(Integer idProject, Integer idAnimal) {
        projectService.findById(idProject);
        Optional<Animal> animal = animalRepository.findByIdAndProjectId(idAnimal, idProject);
        if (animal.isEmpty()) {
            throw new NotFoundException("Animal Not Found");
        } else {
            return animal.get();
        }
    }

    public Animal save(Animal animal, Integer idProject) {
        Project project = projectService.findById(idProject);
        animal.setProject(project);

        if (animal.getSpecie() == null || animal.getSpecie().getId() == null ) {
            throw new InvalidResourceException("Specie cannot be null");
        } else {
            Specie specie = specieService.findById(animal.getSpecie().getId());
            animal.setSpecie(specie);
        }

        if (animal.getLocation() == null) {
            throw new InvalidResourceException("Location cannot be null");
        } else {
            Location location = locationService.save(animal.getLocation());
            animal.setLocation(location);
        }

        Animal animalSaved = animalRepository.save(animal);
        return animalRepository.findById(animalSaved.getId()).get();
    }

    public Animal update(Animal animalToUpdate, Integer idProject, Integer idAnimal) {
        Animal animal = this.findById(idProject, idAnimal);
        if (animalToUpdate.getTag() != null) {
            animal.setTag(animalToUpdate.getTag());
        }
        if (animalToUpdate.getSex() != null) {
            animal.setSex(animalToUpdate.getSex());
        }
        if (animalToUpdate.getImageUrl() != null) {
            animal.setImageUrl(animalToUpdate.getImageUrl());
        }
        if (animalToUpdate.getRegisterDate() != null) {
            animal.setRegisterDate(animalToUpdate.getRegisterDate());
        }
        if (animalToUpdate.getSpecie() != null && animalToUpdate.getSpecie().getId() != null && !animalToUpdate.getSpecie().equals(animal.getSpecie())) {
            Specie specie = specieService.findById(animalToUpdate.getSpecie().getId());
            animal.setSpecie(specie);
        }
        animal.setUpdatedAt(LocalDateTime.now());
        animalRepository.save(animal);
        return animalRepository.findById(animal.getId()).get();
    }

    public void delete(Integer idProject, Integer idAnimal) {
        Animal animal = this.findById(idProject, idAnimal);
        animalRepository.delete(animal);
    }
}
