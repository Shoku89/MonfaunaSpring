package com.monfauna.api.service;

import com.monfauna.api.exception.InvalidResourceException;
import com.monfauna.api.exception.NotFoundException;
import com.monfauna.api.model.Location;
import com.monfauna.api.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location findById(Integer id) {
        Optional<Location> location = locationRepository.findById(id);
        if (location.isEmpty()) {
            throw new NotFoundException("Location Not Found");
        } else {
            return location.get();
        }
    }

    public Location save(Location location) {
        if (location.getName() == null || location.getName().isBlank()) {
            throw new InvalidResourceException("Name cannot be empty");
        } else {
            return locationRepository.save(location);
        }
    }

    public Location update(Location locationUpdated, Integer id) {
        Location location = this.findById(id);
        if (locationUpdated.getName() != null ) {
            location.setName(locationUpdated.getName());
        }
        if (locationUpdated.getLatitude() != null ) {
            location.setLatitude(locationUpdated.getLatitude());
        }
        if (locationUpdated.getLongitude() != null ) {
            location.setLongitude(locationUpdated.getLongitude());
        }
        return locationRepository.save(location);
    }

    public void delete(Integer id) {
        Location location = this.findById(id);
        locationRepository.delete(location);
    }

}
