package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.db.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetData {

    @Autowired
    private DataRepository dataRepository;

    public Role getRole() {
        return dataRepository.getRole(1);
    }

    public List<User> getUser() {
        return dataRepository.getUser();
    }
}
