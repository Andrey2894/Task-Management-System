package com.example.taskmanagementsystem.bll.service;

import com.example.taskmanagementsystem.bll.mapper.TaskMapper;
import com.example.taskmanagementsystem.dal.dao.TaskRepository;
import com.example.taskmanagementsystem.dal.entity.Task;
import com.example.taskmanagementsystem.dal.enums.TaskStatusEnum;
import com.example.taskmanagementsystem.dal.exception.IdNotFoundException;
import com.example.taskmanagementsystem.dal.exception.TitleIsNullException;
import com.example.taskmanagementsystem.ep.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskMapper taskMapper;

    public List<TaskDto> listAll(String sortOrder) {
        Sort sort = "desc".equalsIgnoreCase(sortOrder)
                ? Sort.by(Sort.Direction.DESC, "createdAt")
                : Sort.by(Sort.Direction.ASC, "createdAt");
        return taskRepository.findAll(sort).stream().map(taskMapper::toDto).collect(Collectors.toList());
    }

    public TaskDto findTaskById(Long id) {
        return taskMapper.toDto(taskRepository.findById(id).orElseThrow(() -> new IdNotFoundException()));
    }

    public TaskDto createTask(TaskDto taskDto) {
        if(taskDto.getTitle().isEmpty()) throw new TitleIsNullException();
        Task task = taskMapper.toEntity(taskDto);
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDto updateTaskById(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IdNotFoundException());
        taskMapper.applyChanges(task, taskDto);
        taskRepository.save(task);
        return taskMapper.toDto(taskRepository.save(task));
    }

    public TaskDto nextTaskStatusById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IdNotFoundException());
        if(task.getStatusEnum() == TaskStatusEnum.NEW) task.setStatusEnum(TaskStatusEnum.IN_PROGRESS);
        else if (task.getStatusEnum() == TaskStatusEnum.IN_PROGRESS) {
            task.setStatusEnum(TaskStatusEnum.COMPLETED);
            task.setClosedAt(LocalDateTime.now());
        }
        taskRepository.save(task);
        return taskMapper.toDto(task);
    }
}
