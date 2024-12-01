package com.example.taskmanagementsystem.bll.mapper;

import com.example.taskmanagementsystem.bll.service.UserService;
import com.example.taskmanagementsystem.dal.entity.Task;
import com.example.taskmanagementsystem.ep.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskMapper {
    @Autowired
    private UserService userService;

    public TaskDto toDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatusEnum(task.getStatusEnum());
        taskDto.setCreatedAt(task.getCreatedAt());
        taskDto.setClosedAt(task.getClosedAt());
        if(task.getCreator() != null) taskDto.setCreator(task.getCreator().getUsername());
        if(task.getAssignee() != null) taskDto.setAssignee(task.getAssignee().getUsername());
        return taskDto;
    }

    public Task toEntity(TaskDto taskDto) {
        Task task = new Task();
        applyChanges(task, taskDto);
        return task;
    }

    public void applyChanges(Task task, TaskDto taskDto) {
        if(taskDto.getTitle() != null) task.setTitle(taskDto.getTitle());
        if(taskDto.getDescription() != null) task.setDescription(taskDto.getDescription());
        task.setCreator(userService.findByUsername(taskDto.getCreator()));
        task.setAssignee(userService.findByUsername(taskDto.getAssignee()));
    }
}