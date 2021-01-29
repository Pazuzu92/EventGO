package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.*;
import com.innopolis.eventgo.db.repository.*;
import com.innopolis.eventgo.dto.UserRegistrationDto;
import com.innopolis.eventgo.dto.UserDto;
import com.innopolis.eventgo.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final CommentDAO commentDAO;
    private final GroupDAO groupDAO;
    private final ModelMapper modelMapper = new ModelMapper();
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, UserDAO userDAO, RoleDAO roleDAO, CommentDAO commentDAO, GroupDAO groupDAO, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.commentDAO = commentDAO;
        this.groupDAO = groupDAO;
        this.encoder = encoder;
    }

    public ResponseMessageEntity updateUser(long id, User userUpdate) throws NotFoundException {
        User user = userRepository.getUser(id);
        if (!isValidUser(user)) throw new NotFoundException("User not found");
        user.setName(userUpdate.getName());

        userRepository.updateName(id, userUpdate);
        return getResponseMessage();
    }

    public UserDto findUserDto(Long id) throws NotFoundException {
        User user = userRepository.getUser(id);
        if (user == null) throw new NotFoundException("User not found");
        return modelMapper.map(user, UserDto.class);
    }

    public User save(UserRegistrationDto userRegistrationDto) {

        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setLogin(userRegistrationDto.getLogin());
        user.setPassword(encoder.encode(userRegistrationDto.getPassword()));
        user.setEmail(userRegistrationDto.getEmail());
        user.setRole(roleDAO.getRoleByRoleCode(Role.USER));

        return userDAO.save(user);
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

    public List<UserDto> findAll() {
        List<User> users = userDAO.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        User user = userDAO.findById(id).orElse(null);
        Optional<List<Group>> groupsByUser = groupDAO.findAllByUser(user);
        Optional<List<Comment>> commentsByUser = commentDAO.findAllByUser(user);
        if (user != null && groupsByUser.isPresent() && commentsByUser.isPresent()) {
            userDAO.deleteById(id);
        }
    }

    public void update(User currentUser, User newUser) {

        currentUser.setName(newUser.getName());
        currentUser.setLogin(newUser.getLogin());
        currentUser.setEmail(newUser.getEmail());

        if (newUser.getRole() != null) {
            currentUser.setRole(newUser.getRole());
        }

        if (!Objects.equals(newUser.getPassword(), "") && newUser.getPassword() != null) {
            currentUser.setPassword(encoder.encode(newUser.getPassword()));
        }

        userDAO.save(currentUser);
    }

    public Optional<User> findByLogin(String login) {
        return userDAO.findByLogin(login);
    }

    public void updateByRole(User user, Long roleId) {
        Optional<Role> role = roleDAO.findById(roleId);
        if (role.isPresent() && !role.get().getId().equals(user.getRole().getId())){
            user.setRole(role.get());
            userDAO.save(user);
        }
    }

    public User findUser(Long id) throws NotFoundException {
        User user = userRepository.getUser(id);
        if (user == null) throw new NotFoundException("User not found");
        return user;
    }

    public List<User> findAllOrderById(){
        return userDAO.findAllByOrderById();
    }
}
