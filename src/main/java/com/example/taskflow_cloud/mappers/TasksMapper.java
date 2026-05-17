package com.example.taskflow_cloud.mappers;

import com.example.taskflow_cloud.dto.TasksDTO;
import com.example.taskflow_cloud.enties.Tasks;
import com.example.taskflow_cloud.enties.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TasksMapper {

    @Mapping(source = "userId.id", target = "userId")
    TasksDTO toDTO(Tasks task);

    @Mapping(source = "userId", target = "userId")
    Tasks toEntity(TasksDTO dto);

    default Users map(Integer value) {

        if (value == null) {
            return null;
        }

        Users user = new Users();
        user.setId(value);

        return user;
    }
}