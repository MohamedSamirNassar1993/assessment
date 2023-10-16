package com.stc.assessment.mapper;

import com.stc.assessment.common.mapper.BaseMapper;
import com.stc.assessment.model.dtos.PermissionGroupDTO;
import com.stc.assessment.model.entities.PermissionGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionGroupMapper extends BaseMapper<Object, PermissionGroupDTO, PermissionGroup> {

    PermissionGroup toEntity(PermissionGroupDTO permissionGroupDTO);
    PermissionGroupDTO toDTO(PermissionGroup permissionGroup);
}
