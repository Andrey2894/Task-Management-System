package com.example.taskmanagementsystem.dal.entity;

import com.example.taskmanagementsystem.dal.entity.base.BaseIdEntity;
import com.example.taskmanagementsystem.dal.enums.TaskStatusEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Task extends BaseIdEntity {
    @Column(nullable = false)
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private TaskStatusEnum statusEnum = TaskStatusEnum.NEW;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime closedAt;
    private String creator;
    private String assignee;

    public Task() {
        //default
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

    public TaskStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(TaskStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
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
}
