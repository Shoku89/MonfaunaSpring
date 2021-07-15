package com.monfauna.api.service;

import com.monfauna.api.exception.InvalidResourceException;
import com.monfauna.api.exception.NotFoundException;
import com.monfauna.api.model.Project;
import com.monfauna.api.model.User;
import com.monfauna.api.repository.ProjectRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserService userService;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findById(Integer id) {
        Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new NotFoundException("Project Not Found");
        } else {
            return project.get();
        }
    }

    public Project save(Project project) {

        if (project.getName() == null || project.getName().isBlank()) {
            throw new InvalidResourceException("Name cannot be empty");
        }

        if (project.getOwner() == null || project.getOwner().getId() == null) {
            throw new InvalidResourceException("Owner is required");
        } else {
            User owner = userService.findById(project.getOwner().getId());
            project.setOwner(owner);
        }

        project = projectRepository.save(project);
        return this.findById(project.getId());
    }

    public Project update(Project projectUpdated, Integer id) {
        Project project = this.findById(id);
        if (projectUpdated.getName() != null) {
            project.setName(projectUpdated.getName());
        }

        project = projectRepository.save(project);
        return this.findById(project.getId());
    }

    public void delete(Integer id) {
        Project project = this.findById(id);
        projectRepository.delete(project);
    }
}
