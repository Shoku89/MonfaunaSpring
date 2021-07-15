package com.monfauna.api.service;

import com.monfauna.api.dto.input.UpdatedUserDTO;
import com.monfauna.api.exception.BusinessRulesException;
import com.monfauna.api.exception.InvalidResourceException;
import com.monfauna.api.exception.NotFoundException;
import com.monfauna.api.model.User;
import com.monfauna.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();

    }

    public User findById(Integer id) {
         Optional<User> user = userRepository.findById(id);
         if (user.isEmpty()) {
             throw new NotFoundException("User Not Found");
         } else {
             return user.get();
         }

    }


    public User save(User user) {
        user.validate();
        Optional<User> userRegistered = userRepository.findByEmail(user.getEmail());
        if (userRegistered.isPresent()) {
            throw new BusinessRulesException("E-mail already exists");
        } else {
            return userRepository.save(user);
        }
    }

    public User update(User userUpdated, Integer id) {
        User user = this.findById(id);

        if (userUpdated.getPassword() == null || userUpdated.getPassword().isBlank()) {
            throw new InvalidResourceException("Password cannot be empty");
        } else {
            user.setPassword(userUpdated.getPassword());
        }
        if (userUpdated.getName() == null || userUpdated.getName().isBlank()) {
            throw new InvalidResourceException("Name cannot be empty");
        } else {
            user.setName(userUpdated.getName());
        }

        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }


    public void delete(Integer id) {
        User user = this.findById(id);
        userRepository.delete(user);
    }
}
