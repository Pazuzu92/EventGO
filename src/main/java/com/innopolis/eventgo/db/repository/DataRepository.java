package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Comment;
import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.db.entity.User;

import java.util.List;

public interface DataRepository {
    Role saveRole(Role role);
    User saveUser(User user);
    Comment saveComment(Comment comment);

    Role getRole(int code);
    List<User> getUser();
    List<Comment> getCommentsByUserId(Long idUser);
}
