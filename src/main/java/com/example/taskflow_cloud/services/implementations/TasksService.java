package com.example.taskflow_cloud.services.implementations;

import com.example.taskflow_cloud.dto.TasksDTO;
import com.example.taskflow_cloud.enties.Tasks;
import com.example.taskflow_cloud.mappers.TasksMapper;
import com.example.taskflow_cloud.repositories.TasksRepo;
import com.example.taskflow_cloud.services.interfaces.TasksInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasksService implements TasksInterface {

    private final TasksRepo tasksRepo;
    private final TasksMapper tasksMapper;

    public TasksService(TasksRepo tasksRepo, TasksMapper tasksMapper) {
        this.tasksRepo = tasksRepo;
        this.tasksMapper = tasksMapper;
    }

    @Override
    public TasksDTO createTask(TasksDTO tasksDTO) {

        Tasks task = tasksMapper.toEntity(tasksDTO);

        return tasksMapper.toDTO(tasksRepo.save(task));
    }

    @Override
    public List<TasksDTO> getAllTasks() {

        return tasksRepo.findAll()
                .stream()
                .map(tasksMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TasksDTO getTaskById(Integer id) {

        Tasks task = tasksRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return tasksMapper.toDTO(task);
    }

    @Override
    public TasksDTO updateTask(Integer id, TasksDTO tasksDTO) {

        Tasks task = tasksRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(tasksDTO.getTitle());
        task.setDescription(tasksDTO.getDescription());
        task.setStatus(tasksDTO.getStatus());

        return tasksMapper.toDTO(tasksRepo.save(task));
    }

    @Override
    public void deleteTask(Integer id) {

        tasksRepo.deleteById(id);
    }
}
