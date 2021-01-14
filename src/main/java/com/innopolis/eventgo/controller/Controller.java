package com.innopolis.eventgo.controller;

import com.innopolis.eventgo.db.entity.Comment;
import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.service.GetData;
import com.innopolis.eventgo.service.SaveData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private SaveData saveData;
    @Autowired
    private GetData getData;

    @GetMapping(value = "/save")
    public String save() {
        Role role = new Role();
        role.setRoleCode(1);
        role.setRoleName("user");

        Role rolePersist = saveData.saveRole(role);

        User user1 = new User(rolePersist);
        user1.setName("user1");
        user1.setEmail("u1@test.ru");
        user1.setLogin("login");
        user1.setPassword("pass");

        User user2 = new User(rolePersist);
        user2.setName("user2");
        user2.setEmail("u1@test.ru");
        user2.setLogin("login");
        user2.setPassword("pass");

        User userPersist = saveData.saveUser(user1);
        saveData.saveUser(user2);

        Comment comment = new Comment(userPersist);
        comment.setText("sfsdfsd");
        saveData.saveComment(comment);

        return "saved";
    }

    @GetMapping(value = "/getRole")
    public String getRole() {
        return getData.getRole().toString();
    }

    @GetMapping(value = "/getUsers")
    public String getUsers() {
        StringBuilder stringBuilder = new StringBuilder();
        List<User> users = getData.getUser();
        users.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }

    @GetMapping(value = "/getComments")
    public String getComment(){
        StringBuilder stringBuilder = new StringBuilder();
        List<Comment> comments = getData.getCommentsByUser();
        comments.forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
