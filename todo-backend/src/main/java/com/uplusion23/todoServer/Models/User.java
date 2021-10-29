package com.uplusion23.todoServer.Models;

import com.fasterxml.jackson.annotation.JsonView;
import com.sun.istack.NotNull;
import com.uplusion23.todoServer.Views.UserView;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(UserView.Public.class)
    private Long id;

    @NotNull
    @JsonView(UserView.Public.class)
    private String username;

    @NotNull
    @JsonView(UserView.Internal.class)
    private String password;

    @JsonView(UserView.Public.class)
    private LocalDate createdAt;

    @JsonView(UserView.Public.class)
    private LocalDate updatedAt;

    @JsonView(UserView.Admin.class)
    private Integer role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
