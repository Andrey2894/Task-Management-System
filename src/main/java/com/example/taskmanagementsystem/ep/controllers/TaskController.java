package com.example.taskmanagementsystem.ep.controllers;

import com.example.taskmanagementsystem.bll.TaskService;
import com.example.taskmanagementsystem.ep.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tasks")
@RestController
public class TaskController {
    @Autowired
    private TaskService service;

    @GetMapping
    public ResponseEntity<List<TaskDto>> listAll(@RequestParam(required = false) String sortOrder) {
        return ResponseEntity.ok(service.listAll(sortOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskDto> addTask(@RequestBody @NonNull TaskDto task) {
        return ResponseEntity.ok(service.createTask(task));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTaskById(@PathVariable("id") Long id, @RequestBody TaskDto taskDto) {
        return ResponseEntity.ok(service.updateTaskById(id,taskDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable("id") Long id) {
        service.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("nextStatus/{id}")
    public ResponseEntity<TaskDto> nextTaskStatusById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.nextTaskStatusById(id));
    }

}
