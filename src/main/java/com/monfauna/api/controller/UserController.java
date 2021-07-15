package com.monfauna.api.controller;

import com.monfauna.api.dto.UserDTO;
import com.monfauna.api.dto.input.NewUserDTO;
import com.monfauna.api.dto.input.UpdatedUserDTO;
import com.monfauna.api.model.User;
import com.monfauna.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//springdoc: https://springdoc.org/#how-can-i-return-an-empty-content-as-response

@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {
    @Autowired
    private UserService userService;


    @Operation(summary = "Get all users")
    @GetMapping
    public List<UserDTO> findAll() {
        List<User> users = userService.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User u : users) {
            UserDTO userDTO = new UserDTO(u);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Operation(summary = "Get user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Integer id) {
        User user = userService.findById(id);
        UserDTO userDTO = new UserDTO(user);

       return userDTO;
    }

    @Operation(summary = "Save user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "201"),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody NewUserDTO newUserDTO) {
        User user = userService.save(newUserDTO.toModel());
        UserDTO userDTO = new UserDTO(user);
        return userDTO;

    }

    @Operation(summary = "Update user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200"),
            @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UpdatedUserDTO updatedUserDTO, @PathVariable Integer id) {
        User userUpdated = userService.update(updatedUserDTO.toModel(), id);
        UserDTO userDTO = new UserDTO(userUpdated);
        return userDTO;



    }

    @Operation(summary = "Delete user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "204"),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = com.monfauna.api.exception.ApiResponse.class)))
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

}
