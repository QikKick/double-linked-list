package com.example.antrasdarbas.controllers;

import com.example.antrasdarbas.exceptions.UserNotFound;
import com.example.antrasdarbas.model.Customer;
import com.example.antrasdarbas.model.Manager;
import com.example.antrasdarbas.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.antrasdarbas.repos.UserRep;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class UserRest {

    @Autowired
    private UserRep userRep;

    @GetMapping(value = "/getAllUsers")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRep.findAll();
    }

    @GetMapping(value = "/getUserById/{id}")
    public @ResponseBody User getUserById(@PathVariable int id) {
        return userRep.findById(id).orElseThrow(() -> new UserNotFound(id));
    }

    @PostMapping(value = "/addUser")
    public @ResponseBody User addUser(@RequestBody User user) {
        return userRep.saveAndFlush(user);
    }

    @PutMapping(value = "/updateUserById/{id}")
    public @ResponseBody User updateUser(@PathVariable int id, @RequestBody User userDetails) {
        User userToUpdate = userRep.findById(id).orElseThrow(() -> new UserNotFound(id));

        userToUpdate.setLogin(userDetails.getLogin());
        userToUpdate.setPassword(userDetails.getPassword());
        userToUpdate.setName(userDetails.getName());
        userToUpdate.setSurname(userDetails.getSurname());
        userToUpdate.setBirthDate(userDetails.getBirthDate());

        return userRep.saveAndFlush(userToUpdate);
    }

    @DeleteMapping(value = "/deleteUserById/{id}")
    public @ResponseBody void deleteUser(@PathVariable int id) {
        userRep.deleteById(id);
    }

    @PostMapping(value = "/user/getUserByCredentials")
    public ResponseEntity<Map<String, Object>> getUserByCredentials(@RequestBody Map<String, String> credentials) {

        String login = credentials.get("login");
        String password = credentials.get("password");

        Optional<? extends User> optionalUser = userRep.findByLoginAndPassword(login, password);

        Map<String, Object> response = new HashMap<>();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            response.put("result", "true");
            response.put("user", user);

            if (user instanceof Manager) {
                response.put("userType", "Manager");
            } else if (user instanceof Customer) {
                response.put("userType", "Customer");
            }
        } else {
            response.put("result", "false");
        }

        return ResponseEntity.ok(response);
    }
}
