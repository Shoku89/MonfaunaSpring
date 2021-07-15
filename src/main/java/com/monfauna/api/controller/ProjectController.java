package com.monfauna.api.controller;

import com.monfauna.api.dto.ProjectDTO;
import com.monfauna.api.dto.input.NewProjectDTO;
import com.monfauna.api.dto.input.UpdatedProjectDTO;
import com.monfauna.api.model.Project;
import com.monfauna.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

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

    @GetMapping("/{id}")
    public ProjectDTO findById(@PathVariable Integer id) {
        Project project = projectService.findById(id);
        ProjectDTO projectDTO = new ProjectDTO(project);
        return projectDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectDTO save(@RequestBody NewProjectDTO newProjectDTO) {
        Project project = projectService.save(newProjectDTO.toModel());
        return new ProjectDTO(project);
    }

    @PutMapping("/{id}")
    public ProjectDTO update(@RequestBody UpdatedProjectDTO updatedProjectDTO, @PathVariable Integer id) {
        Project project = projectService.update(updatedProjectDTO.toModel(), id);
        return new ProjectDTO(project);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        projectService.delete(id);
    }
}
