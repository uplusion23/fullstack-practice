package com.uplusion23.todoServer.Controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.uplusion23.todoServer.Models.User;
import com.uplusion23.todoServer.Services.UserService;
import com.uplusion23.todoServer.Repositories.UserRepository;
import com.uplusion23.todoServer.Views.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Public.class)
    public Object getUser(@PathVariable("id") Long id) {
        return this.userService.getUserById(id);
    }

    @PostMapping("/register")
    @JsonView(UserView.Public.class)
    public Object registerUser(@Valid @RequestBody User user) {
        return this.userService.registerUser(user);
    }

    @PostMapping("/login")
    @JsonView(UserView.Public.class)
    public Object loginUser(@RequestBody User user) {
        return this.userService.authenticateUser(user.getUsername(), user.getPassword());
    }
}
