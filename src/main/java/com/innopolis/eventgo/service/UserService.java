package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.ResponseMessageEntity;
import com.innopolis.eventgo.db.entity.User;
import com.innopolis.eventgo.db.repository.UserRepository;
import com.innopolis.eventgo.dto.UserDto;
import com.innopolis.eventgo.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private final ModelMapper modelMapper = new ModelMapper();

    public ResponseMessageEntity updateUser(long id, User userUpdate) throws NotFoundException {
        User user = userRepository.getUser(id);
        if (!isValidUser(user)) throw new NotFoundException("User not found");
        user.setName(userUpdate.getName());

        userRepository.updateName(id, userUpdate);
        return getResponseMessage();
    }

    public UserDto getUser(Long id) throws NotFoundException {
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

    private ResponseMessageEntity getResponseMessage() {
        ResponseMessageEntity rme = new ResponseMessageEntity();
        rme.setCode(200);
        rme.setMessage("Ok");
        return rme;
    }

}
