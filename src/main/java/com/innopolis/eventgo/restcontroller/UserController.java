package com.innopolis.eventgo.restcontroller;

import com.innopolis.eventgo.db.entity.ResponseMessageEntity;
import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.dto.UserDto;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/account/{userId}")
    public UserDto getUser(@PathVariable("userId") long id) throws NotFoundException {
        return userService.findUserDto(id);
    }

    @PutMapping(value = "/account/{userId}")
    public ResponseMessageEntity updateUser(@PathVariable("userId") long id, @RequestBody User userUpdate) throws NotFoundException {
        return userService.updateUser(id, userUpdate);
    }
}
