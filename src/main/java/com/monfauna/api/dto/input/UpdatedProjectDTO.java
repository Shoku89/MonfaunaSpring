package com.monfauna.api.dto.input;

import com.monfauna.api.model.Project;

public class UpdatedProjectDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Project toModel() {
        Project project = new Project();
        project.setName(name);
        return project;
    }
}
