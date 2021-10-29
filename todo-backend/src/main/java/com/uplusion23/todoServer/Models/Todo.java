package com.uplusion23.todoserver.Models;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private String content;
    private boolean completed;
}
