package com.innopolis.eventgo.service.impl;

import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.db.repository.RoleDAO;
import com.innopolis.eventgo.db.repository.UserDAO;
import com.innopolis.eventgo.dto.RoleDto;
import com.innopolis.eventgo.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;
    private final ModelMapper modelMapper = new ModelMapper();
    private final UserDAO userDAO;

    public RoleServiceImpl(RoleDAO roleDAO, UserDAO userDAO) {
        this.roleDAO = roleDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<RoleDto> findAll() {
        List<Role> roles = roleDAO.findAll();
        return roles.stream()
                .map(role -> modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        Optional<Role> role = roleDAO.findById(id);
        if (role.isPresent() && userDAO.countUserByRole(role.get()) == 0) {
            roleDAO.deleteById(id);
        }
    }

    @Override
    public void save(Role role) {
        int roleCode = role.getRoleCode();
        Role rl = roleDAO.getRoleByRoleCode(roleCode);
        if (rl == null) {
            role.setRoleCode(roleCode);
            roleDAO.save(role);
        }
    }
}
