package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Long> {
    Role getRoleByRoleCode(String roleCode);
}
