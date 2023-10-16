package com.stc.assessment.mapper;

import com.stc.assessment.common.mapper.BaseMapper;
import com.stc.assessment.model.dtos.PermissionDTO;
import com.stc.assessment.model.entities.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper extends BaseMapper<Object, PermissionDTO, Permission> {

    Permission toEntity(PermissionDTO permissionDTO);
    PermissionDTO toDTO(Permission permission);
}
