package com.github.nitish_chandra_m.ad_campaign_tool.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/list")
    public List<UserDto> listUsers() {
        return userService.listUsers();
    }

    @PostMapping("/get/{id}")
    public UserDto getUserById(
            @PathVariable("id") String id
    ) {
        var user = userService.getUserById(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user;
    }

    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

//    @PostMapping("/update/{id}")
//
//
//    @PostMapping("/delete/{id}")
}
