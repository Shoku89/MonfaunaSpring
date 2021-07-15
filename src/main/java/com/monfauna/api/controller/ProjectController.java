package com.monfauna.api.controller;

import com.monfauna.api.dto.ProjectDTO;
import com.monfauna.api.dto.input.NewProjectDTO;
import com.monfauna.api.dto.input.UpdatedProjectDTO;
import com.monfauna.api.model.Project;
import com.monfauna.api.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/projects", produces = "application/json")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "Get all projects")
    @GetMapping
    public List<ProjectDTO> findAll() {
        List<Project> projects = projectService.findAll();
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        for (Project p : projects) {
            ProjectDTO projectDTO = new ProjectDTO(p);
            projectDTOS.add(projectDTO);
        }
        return projectDTOS;
    }

    @Operation(summary = "Get project", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @GetMapping("/{id}")
    public ProjectDTO findById(@PathVariable Integer id) {
        Project project = projectService.findById(id);
        ProjectDTO projectDTO = new ProjectDTO(project);
        return projectDTO;
    }

    @Operation(summary = "Save project", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201"),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDTO save(@RequestBody NewProjectDTO newProjectDTO) {
        Project project = projectService.save(newProjectDTO.toModel());
        return new ProjectDTO(project);
    }

    @Operation(summary = "Update project", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @PutMapping("/{id}")
    public ProjectDTO update(@RequestBody UpdatedProjectDTO updatedProjectDTO, @PathVariable Integer id) {
        Project project = projectService.update(updatedProjectDTO.toModel(), id);
        return new ProjectDTO(project);
    }

    @Operation(summary = "Delete project", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        projectService.delete(id);
    }
}
