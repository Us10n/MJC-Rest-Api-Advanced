package com.epam.esm.web.controller;

import com.epam.esm.service.dto.UserDto;
import com.epam.esm.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserDto> readAllTags(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                     @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        return userService.readAll(page, limit);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public UserDto readTadById(@PathVariable long id) {
        return userService.readById(id);
    }

}
