package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.Role;
import com.innopolis.eventgo.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findAll();

    void delete(Long id);

    void save(Role role);
}
