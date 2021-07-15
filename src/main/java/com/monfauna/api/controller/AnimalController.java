package com.monfauna.api.controller;

import com.monfauna.api.dto.input.NewAnimalDTO;
import com.monfauna.api.dto.input.UpdatedAnimalDTO;
import com.monfauna.api.model.Animal;
import com.monfauna.api.model.Project;
import com.monfauna.api.service.AnimalService;
import com.monfauna.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping("/{idProject}/animals")
    public List<Animal> findAllAnimalsByProject(@PathVariable Integer idProject) {

        return animalService.findAllAnimalsByProject(idProject);
    }

    @GetMapping("/{idProject}/animals/{idAnimal}")
    public Animal findById(@PathVariable(name = "idProject") Integer idProject, @PathVariable(name = "idAnimal") Integer idAnimal) {
        return animalService.findById(idProject, idAnimal);
    }

    @PostMapping("/{idProject}/animals")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal save(@RequestBody NewAnimalDTO newAnimalDTO, @PathVariable Integer idProject) {
        Animal animal = newAnimalDTO.toModel();
        return animalService.save(animal, idProject);
    }

    @PutMapping("/{idProject}/animals/{idAnimal}")
    public  Animal update(@RequestBody UpdatedAnimalDTO updatedAnimalDTO, @PathVariable(name = "idProject") Integer idProject, @PathVariable(name = "idAnimal") Integer idAnimal) {
        Animal animal = updatedAnimalDTO.toModel();
        return animalService.update(animal, idProject, idAnimal);
    }

    @DeleteMapping("/{idProject}/animals/{idAnimal}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "idProject") Integer idProject, @PathVariable(name = "idAnimal") Integer idAnimal) {
        animalService.delete(idProject, idAnimal);
    }


}
