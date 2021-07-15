package com.monfauna.api.controller;

import com.monfauna.api.model.Location;
import com.monfauna.api.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/locations", produces = "application/json")
public class LocationController {
    @Autowired
    private LocationService locationService;


    @Operation(summary = "Get all locations")
    @GetMapping
    public List<Location> findAll() {
        return locationService.findAll();
    }

    @Operation(summary = "Get location", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @GetMapping("/{id}")
    public Location findById(@PathVariable Integer id) {
        return locationService.findById(id);
    }

    @Operation(summary = "Save location", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201"),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Location save(@RequestBody Location location) {
        return locationService.save(location);
    }

    @Operation(summary = "Update location", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @PutMapping("/{id}")
    public Location update(@RequestBody Location location, @PathVariable Integer id) {
        return locationService.update(location, id);
    }

    @Operation(summary = "Delete location", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        locationService.delete(id);
    }

}
