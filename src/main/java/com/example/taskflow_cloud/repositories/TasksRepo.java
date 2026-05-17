package com.example.taskflow_cloud.repositories;

import com.example.taskflow_cloud.enties.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepo extends JpaRepository<Tasks, Integer> {

}