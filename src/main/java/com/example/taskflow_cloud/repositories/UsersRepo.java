package com.example.taskflow_cloud.repositories;

import com.example.taskflow_cloud.enties.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

}