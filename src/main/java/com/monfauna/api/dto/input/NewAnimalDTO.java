package com.monfauna.api.dto.input;

import com.monfauna.api.model.Animal;
import com.monfauna.api.model.AnimalMeasurement;
import com.monfauna.api.model.Specie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ofPattern;

public class NewAnimalDTO {
    private Integer specieId;
    private String tag;
    private Character sex;
    private String imageUrl;
    private String registerDate; //yyyy-MM-dd
    private NewLocationDTO location;
    private List<AnimalMeasurement> measurements = new ArrayList<>();


    public Integer getSpecieId() {
        return specieId;
    }

    public void setSpecieId(Integer specieId) {
        this.specieId = specieId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public NewLocationDTO getLocation() {
        return location;
    }

    public void setLocation(NewLocationDTO location) {
        this.location = location;
    }

    public List<AnimalMeasurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<AnimalMeasurement> measurements) {
        this.measurements = measurements;
    }

    public Animal toModel() {
        Animal animal = new Animal();
        Specie specie = new Specie();
        specie.setId(specieId);
        animal.setSpecie(specie);
        animal.setTag(tag);
        animal.setSex(sex);
        animal.setImageUrl(imageUrl);
        animal.setRegisterDate(LocalDate.parse(registerDate, ofPattern("yyyy-MM-dd")));
        animal.setLocation(location.toModel());
        animal.getMeasurements().addAll(measurements);

        return animal;
    }
}
