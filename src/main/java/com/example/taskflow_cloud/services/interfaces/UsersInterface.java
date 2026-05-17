package com.example.taskflow_cloud.services.interfaces;

import com.example.taskflow_cloud.dto.UsersDTO;

import java.util.List;

public interface UsersInterface {

    UsersDTO createUser(UsersDTO usersDTO);

    List<UsersDTO> getAllUsers();

    UsersDTO getUserById(Integer id);

    UsersDTO updateUser(Integer id, UsersDTO usersDTO);

    void deleteUser(Integer id);
}