package com.example.taskmanagementsystem.ep.dto;


import com.example.taskmanagementsystem.dal.enums.TaskStatusEnum;

import java.time.LocalDateTime;

public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private String creator;
    private String assignee;

    private TaskStatusEnum statusEnum;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime closedAt;

    public TaskDto() {
        //default
    }

    public TaskDto(String title, String description, String creator, String assignee) {
        this.title = title;
        this.description = description;
        this.creator = creator;
        this.assignee = assignee;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(LocalDateTime closedAt) {
        this.closedAt = closedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public TaskStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(TaskStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }

    @Override
    public String toString() {
        return  "\nid задачи: " + id +
                "\nНазвание задачи: " + title +
                "\nОписание задачи: " + description +
                "\nСоздатель: " + creator +
                "\nНазначен выполнить: " + assignee +
                "\nСтатус задачи: " + statusEnum +
                "\nЗадача создана: " + createdAt +
                "\nЗадача завершена: " + closedAt;
    }
}
