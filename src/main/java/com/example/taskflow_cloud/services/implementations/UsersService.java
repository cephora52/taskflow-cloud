package com.example.taskflow_cloud.services.implementations;

import com.example.taskflow_cloud.dto.UsersDTO;
import com.example.taskflow_cloud.enties.Users;
import com.example.taskflow_cloud.mappers.UsersMapper;
import com.example.taskflow_cloud.repositories.UsersRepo;
import com.example.taskflow_cloud.services.interfaces.UsersInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsersService implements UsersInterface {

    private final UsersRepo usersRepo;
    private final UsersMapper usersMapper;

    public UsersService(UsersRepo usersRepo, UsersMapper usersMapper) {
        this.usersRepo = usersRepo;
        this.usersMapper = usersMapper;
    }

    @Override
    public UsersDTO createUser(UsersDTO usersDTO) {

        Users user = usersMapper.toEntity(usersDTO);

        return usersMapper.toDTO(usersRepo.save(user));
    }

    @Override
    public List<UsersDTO> getAllUsers() {

        return usersRepo.findAll()
                .stream()
                .map(usersMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsersDTO getUserById(Integer id) {

        Users user = usersRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return usersMapper.toDTO(user);
    }

    @Override
    public UsersDTO updateUser(Integer id, UsersDTO usersDTO) {

        Users user = usersRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(usersDTO.getFullName());
        user.setEmail(usersDTO.getEmail());
        user.setPassword(usersDTO.getPassword());

        return usersMapper.toDTO(usersRepo.save(user));
    }

    @Override
    public void deleteUser(Integer id) {

        usersRepo.deleteById(id);
    }
}