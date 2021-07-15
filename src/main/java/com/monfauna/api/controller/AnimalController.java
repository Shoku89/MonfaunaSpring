package com.monfauna.api.controller;

import com.monfauna.api.dto.input.NewAnimalDTO;
import com.monfauna.api.dto.input.UpdatedAnimalDTO;
import com.monfauna.api.model.Animal;
import com.monfauna.api.model.Project;
import com.monfauna.api.service.AnimalService;
import com.monfauna.api.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/projects", produces = "application/json")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @Operation(summary = "Get all animals")
    @GetMapping( "/{idProject}/animals")
    public List<Animal> findAllAnimalsByProject(@PathVariable Integer idProject) {

        return animalService.findAllAnimalsByProject(idProject);
    }

    @Operation(summary = "Get animal", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @GetMapping("/{idProject}/animals/{idAnimal}")
    public Animal findById(@PathVariable(name = "idProject") Integer idProject, @PathVariable(name = "idAnimal") Integer idAnimal) {
        return animalService.findById(idProject, idAnimal);
    }

    @Operation(summary = "Save animal", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201"),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @PostMapping("/{idProject}/animals")
    @ResponseStatus(HttpStatus.CREATED)
    public Animal save(@RequestBody NewAnimalDTO newAnimalDTO, @PathVariable Integer idProject) {
        Animal animal = newAnimalDTO.toModel();
        return animalService.save(animal, idProject);
    }

    @Operation(summary = "Update animal", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @PutMapping("/{idProject}/animals/{idAnimal}")
    public  Animal update(@RequestBody UpdatedAnimalDTO updatedAnimalDTO, @PathVariable(name = "idProject") Integer idProject, @PathVariable(name = "idAnimal") Integer idAnimal) {
        Animal animal = updatedAnimalDTO.toModel();
        return animalService.update(animal, idProject, idAnimal);
    }

    @Operation(summary = "Delete animal", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @DeleteMapping("/{idProject}/animals/{idAnimal}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "idProject") Integer idProject, @PathVariable(name = "idAnimal") Integer idAnimal) {
        animalService.delete(idProject, idAnimal);
    }


}
