package com.uplusion23.todoServer.Models;

import com.fasterxml.jackson.annotation.JsonView;
import com.uplusion23.todoServer.Views.TodoPublicView;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JsonView(TodoPublicView.class)
    private String title;

    @NotNull
    @JsonView(TodoPublicView.class)
    private String content;

    @JsonView(TodoPublicView.class)
    private boolean completed;

    @NotNull
    @JsonView(TodoPublicView.class)
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
