package com.stc.assessment.model.dtos;

import com.stc.assessment.common.model.dto.AuditBaseDTO;
import com.stc.assessment.model.enums.PermissionLevelEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class PermissionDTO extends AuditBaseDTO {

    private Integer id;
    private String userEmail;
    private PermissionLevelEnum permissionLevel;
    private PermissionGroupDTO permissionGroup;
}
