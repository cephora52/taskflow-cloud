package com.example.taskflow_cloud.controllers;

import com.example.taskflow_cloud.dto.TasksDTO;
import com.example.taskflow_cloud.services.interfaces.TasksInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private final TasksInterface tasksInterface;

    public TasksController(TasksInterface tasksInterface) {
        this.tasksInterface = tasksInterface;
    }

    @PostMapping
    public TasksDTO createTask(@RequestBody TasksDTO tasksDTO) {

        return tasksInterface.createTask(tasksDTO);
    }

    @GetMapping
    public List<TasksDTO> getAllTasks() {

        return tasksInterface.getAllTasks();
    }

    @GetMapping("/{id}")
    public TasksDTO getTaskById(@PathVariable Integer id) {

        return tasksInterface.getTaskById(id);
    }

    @PutMapping("/{id}")
    public TasksDTO updateTask(@PathVariable Integer id,
                               @RequestBody TasksDTO tasksDTO) {

        return tasksInterface.updateTask(id, tasksDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Integer id) {

        tasksInterface.deleteTask(id);
    }
}