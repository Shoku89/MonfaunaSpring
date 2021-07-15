package com.monfauna.api.controller;


import com.monfauna.api.model.Specie;
import com.monfauna.api.service.SpecieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/species", produces = "application/json")
public class SpecieController {
    @Autowired
    private SpecieService specieService;

    @Operation(summary = "Get all species")
    @GetMapping
    public List<Specie> findAll() {
        return specieService.findAll();

    }

    @Operation(summary = "Get specie", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @GetMapping("/{id}")
    public Specie findById(@PathVariable Integer id) {
        return specieService.findById(id);
    }

    @Operation(summary = "Save specie", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201"),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Specie save(@RequestBody Specie specie) {
        return specieService.save(specie);
    }

    @Operation(summary = "Update specie", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @PutMapping("/{id}")
    public Specie update(@RequestBody Specie specieUpdated, @PathVariable Integer id) {
        return specieService.update(specieUpdated, id);
    }

    @Operation(summary = "Delete specie", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        specieService.delete(id);
    }
}
