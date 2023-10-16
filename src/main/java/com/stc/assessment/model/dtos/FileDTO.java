package com.stc.assessment.model.dtos;

import com.stc.assessment.common.model.dto.AuditBaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class FileDTO extends AuditBaseDTO {

    private Integer id;
    private byte[] fileBinary;
    private ItemDTO item;
}
