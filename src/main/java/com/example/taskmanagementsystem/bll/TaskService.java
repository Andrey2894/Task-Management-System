package com.example.taskmanagementsystem.bll;

import com.example.taskmanagementsystem.dal.dao.TaskRepository;
import com.example.taskmanagementsystem.ep.dto.TaskDto;
import com.example.taskmanagementsystem.dal.entity.Task;
import com.example.taskmanagementsystem.dal.enums.TaskStatusEnum;
import com.example.taskmanagementsystem.exception.IdNotFoundException;
import com.example.taskmanagementsystem.exception.TitleIsNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<TaskDto> listAll(String sortOrder) {
        Sort sort = "desc".equalsIgnoreCase(sortOrder)
                ? Sort.by(Sort.Direction.DESC, "createdAt")
                : Sort.by(Sort.Direction.ASC, "createdAt");
        return taskRepository.findAll(sort).stream().map(TaskMapper::toDto).collect(Collectors.toList());
    }

    public TaskDto findTaskById(Long id) {
        return TaskMapper.toDto(taskRepository.findById(id).orElseThrow(() -> new IdNotFoundException()));
    }

    public TaskDto createTask(TaskDto taskDto) {
        if(!StringUtils.hasLength(taskDto.getTitle())) throw new TitleIsNullException();
        Task task = TaskMapper.toEntity(taskDto);
        taskRepository.save(task);
        return TaskMapper.toDto(task);
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskDto updateTaskById(Long id, TaskDto taskDto) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IdNotFoundException());
        TaskMapper.applyChanges(task, taskDto);
        taskRepository.save(task);
        return TaskMapper.toDto(taskRepository.save(task));
    }

    public TaskDto nextTaskStatusById(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IdNotFoundException());
        if(task.getStatusEnum() == TaskStatusEnum.NEW) task.setStatusEnum(TaskStatusEnum.IN_PROGRESS);
        else if (task.getStatusEnum() == TaskStatusEnum.IN_PROGRESS) {
            task.setStatusEnum(TaskStatusEnum.COMPLETED);
            task.setClosedAt(LocalDateTime.now());
        }
        taskRepository.save(task);
        return TaskMapper.toDto(task);
    }
}
