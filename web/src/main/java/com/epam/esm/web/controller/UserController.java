package com.epam.esm.web.controller;

import com.epam.esm.service.dto.UserDto;
import com.epam.esm.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@Validated
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDto> readAllUsers(@RequestParam(name = "page", defaultValue = "1") @Positive  Integer page,
                                     @RequestParam(name = "limit", defaultValue = "10") @Positive Integer limit) {
        return userService.readAll(page, limit);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDto readUserById(@PathVariable long id) {
        return userService.readById(id);
    }
}
