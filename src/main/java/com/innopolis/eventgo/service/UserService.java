package com.innopolis.eventgo.service;

import org.modelmapper.ModelMapper;
import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.db.repository.UserRepository;
import com.innopolis.eventgo.dto.UserCreateDto;
import com.innopolis.eventgo.dto.UserDto;
import com.innopolis.eventgo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public User updateUser(long id, User userUpdate) throws NotFoundException {
        User user = userRepository.updateName(id, userUpdate);
        if (isValidUser(user)) throw new NotFoundException("User not found");
        return user;
    }

    public UserDto getUser(long id) throws NotFoundException {
        User user = userRepository.getUser(id);
        UserDto result = modelMapper.map(user, UserDto.class);
        if (user == null) throw new NotFoundException("User not found");
        return result;
    }

    private boolean isValidUser(User user) {
        if (user == null ||
                user.getName() == null ||
                user.getEmail() == null ||
                user.getLogin() == null ||
                user.getPassword() == null) return false;
        return true;
    }


}
