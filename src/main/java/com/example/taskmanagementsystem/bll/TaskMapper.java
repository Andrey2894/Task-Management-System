package com.example.taskmanagementsystem.bll;

import com.example.taskmanagementsystem.ep.dto.TaskDto;
import com.example.taskmanagementsystem.dal.entity.Task;

public class TaskMapper {

    //из entity в dto
    public static TaskDto toDto(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCreator(task.getCreator());
        dto.setAssignee(task.getAssignee());
        dto.setStatusEnum(task.getStatusEnum());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setClosedAt(task.getClosedAt());
        return dto;
    }
    //из dto в entity
    public static Task toEntity(TaskDto taskDto) {
        Task task = new Task();
        applyChanges(task, taskDto);
        return task;
    }

    public static void applyChanges(Task task, TaskDto taskDto) {
        if (taskDto.getTitle() != null) task.setTitle(taskDto.getTitle());
        if (taskDto.getDescription() != null) task.setDescription(taskDto.getDescription());
        if (taskDto.getCreator() != null) task.setCreator(taskDto.getCreator());
        if (taskDto.getAssignee() != null) task.setAssignee(taskDto.getAssignee());
    }
}