package com.example.taskmanagementsystem.entity;

import com.example.taskmanagementsystem.entity.base.BaseIdEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class User extends BaseIdEntity {
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    public User() {
        //default
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
