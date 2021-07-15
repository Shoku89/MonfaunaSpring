package com.monfauna.api.controller;

import com.monfauna.api.model.Location;
import com.monfauna.api.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;


    @GetMapping
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    public Location findById(@PathVariable Integer id) {
        return locationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location save(@RequestBody Location location) {
        return locationService.save(location);
    }

    @PutMapping("/{id}")
    public Location update(@RequestBody Location location, @PathVariable Integer id) {
        return locationService.update(location, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        locationService.delete(id);
    }

}
