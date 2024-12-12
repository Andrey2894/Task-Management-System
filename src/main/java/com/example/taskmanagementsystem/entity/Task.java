package com.example.taskmanagementsystem.entity;

import com.example.taskmanagementsystem.entity.base.BaseIdEntity;
import com.example.taskmanagementsystem.enums.TaskStatusEnum;
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
    @ManyToOne
    private User creator;
    @ManyToOne
    private User assignee;

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
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
