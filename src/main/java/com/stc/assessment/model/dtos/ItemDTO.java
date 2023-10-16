package com.stc.assessment.model.dtos;

import com.stc.assessment.common.model.dto.AuditBaseDTO;
import com.stc.assessment.model.enums.ItemTypeEnum;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ItemDTO extends AuditBaseDTO {

    private Integer id;
    private ItemTypeEnum type;
    private String name;
    private PermissionGroupDTO permissionGroup;
}
