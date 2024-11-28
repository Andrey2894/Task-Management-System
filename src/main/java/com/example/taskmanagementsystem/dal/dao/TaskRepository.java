package com.example.taskmanagementsystem.dal.dao;

import com.example.taskmanagementsystem.dal.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {

}
