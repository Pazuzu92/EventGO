package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    public RoleDto() {}

    public RoleDto(Long id, int roleCode) {
        this.id = id;
        this.roleCode = roleCode;
    }

    private Long id;
    private int roleCode;
}
