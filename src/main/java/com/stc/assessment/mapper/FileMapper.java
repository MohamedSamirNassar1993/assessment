package com.stc.assessment.mapper;

import com.stc.assessment.common.mapper.BaseMapper;
import com.stc.assessment.model.dtos.FileDTO;
import com.stc.assessment.model.entities.File;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper extends BaseMapper<Object, FileDTO, File> {

    File toEntity(FileDTO fileDTO);
    FileDTO toDTO(File file);
}
