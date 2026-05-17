package com.example.taskflow_cloud.controllers;

import com.example.taskflow_cloud.dto.UsersDTO;
import com.example.taskflow_cloud.services.interfaces.UsersInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersInterface usersInterface;

    public UsersController(UsersInterface usersInterface) {
        this.usersInterface = usersInterface;
    }

    @PostMapping
    public UsersDTO createUser(@RequestBody UsersDTO usersDTO) {

        return usersInterface.createUser(usersDTO);
    }

    @GetMapping
    public List<UsersDTO> getAllUsers() {

        return usersInterface.getAllUsers();
    }

    @GetMapping("/{id}")
    public UsersDTO getUserById(@PathVariable Integer id) {

        return usersInterface.getUserById(id);
    }

    @PutMapping("/{id}")
    public UsersDTO updateUser(@PathVariable Integer id,
                               @RequestBody UsersDTO usersDTO) {

        return usersInterface.updateUser(id, usersDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {

        usersInterface.deleteUser(id);
    }
}