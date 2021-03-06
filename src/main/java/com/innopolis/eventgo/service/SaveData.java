package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.db.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveData {

    @Autowired
    private DataRepository dataRepository;

    public Role saveRole(Role role) {
        return dataRepository.saveRole(role);
    }

    public User saveUser(User user) {
        return dataRepository.saveUser(user);
    }

    public String init() {
        return dataRepository.init();
    }
}
