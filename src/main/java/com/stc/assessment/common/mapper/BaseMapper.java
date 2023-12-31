package com.stc.assessment.common.mapper;

import com.stc.assessment.common.model.dto.AuditBaseDTO;
import com.stc.assessment.common.model.entity.AuditBaseEntity;

import java.util.List;
import java.util.Set;

public interface BaseMapper<R, D extends AuditBaseDTO, E extends AuditBaseEntity> {

//    D toDTOFromRequest(R request);

    D toDTO(E entity);

    E toEntity(D dto);

    List<E> toListEntity(List<D> dto);

    Set<E> toSetEntity(Set<D> dto);

    List<D> toListDTO(List<E> entities);
    

    Set<D> toSetDTO(Set<E> entities);
}
