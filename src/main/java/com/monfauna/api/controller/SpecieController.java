package com.monfauna.api.controller;


import com.monfauna.api.model.Specie;
import com.monfauna.api.service.SpecieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/species")
public class SpecieController {
    @Autowired
    private SpecieService specieService;

    @GetMapping
    public List<Specie> findAll() {
        return specieService.findAll();

    }

    @GetMapping("/{id}")
    public Specie findById(@PathVariable Integer id) {
        return specieService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Specie save(@RequestBody Specie specie) {
        return specieService.save(specie);
    }

    @PutMapping("/{id}")
    public Specie update(@RequestBody Specie specieUpdated, @PathVariable Integer id) {
        return specieService.update(specieUpdated, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        specieService.delete(id);
    }
}
