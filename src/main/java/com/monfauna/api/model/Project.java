package com.monfauna.api.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name ="owner_user_id")
    private User owner;
    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "collaborator_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private final List<User> collaborators = new ArrayList<>();
    @OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
    private final List<Animal> animals = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getCollaborators() {
        return collaborators;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
}
