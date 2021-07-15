package com.monfauna.api.controller;

import com.monfauna.api.dto.UserDTO;
import com.monfauna.api.dto.input.NewUserDTO;
import com.monfauna.api.dto.input.UpdatedUserDTO;
import com.monfauna.api.model.User;
import com.monfauna.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;



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

    @GetMapping("/{id}")
    public UserDTO findById(@PathVariable Integer id) {
        User user = userService.findById(id);
        UserDTO userDTO = new UserDTO(user);

       return userDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO save(@RequestBody NewUserDTO newUserDTO) {
        User user = userService.save(newUserDTO.toModel());
        UserDTO userDTO = new UserDTO(user);
        return userDTO;

    }

    @PutMapping("/{id}")
    public UserDTO update(@RequestBody UpdatedUserDTO updatedUserDTO, @PathVariable Integer id) {
        User userUpdated = userService.update(updatedUserDTO.toModel(), id);
        UserDTO userDTO = new UserDTO(userUpdated);
        return userDTO;



    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

}
