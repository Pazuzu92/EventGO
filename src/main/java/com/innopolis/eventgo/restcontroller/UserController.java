package com.innopolis.eventgo.restcontroller;

import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/account/{userId}")
    public User getUser(@PathVariable("userId") long id) throws NotFoundException {
        return userService.getUser(id);
    }

    @PutMapping(value = "/account/{userId}")
    public User updateUser(@PathVariable("userId") long id, @RequestBody User userUpdate) throws NotFoundException {
        return userService.updateUser(id, userUpdate);
    }
}
