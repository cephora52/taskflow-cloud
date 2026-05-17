package com.example.taskflow_cloud.mappers;

import com.example.taskflow_cloud.dto.UsersDTO;
import com.example.taskflow_cloud.enties.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersDTO toDTO(Users user);

    Users toEntity(UsersDTO dto);
}