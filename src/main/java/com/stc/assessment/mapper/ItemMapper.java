package com.stc.assessment.mapper;

import com.stc.assessment.common.mapper.BaseMapper;
import com.stc.assessment.model.dtos.ItemDTO;
import com.stc.assessment.model.entities.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper extends BaseMapper<Object, ItemDTO, Item> {

    Item toEntity(ItemDTO itemDTO);
    ItemDTO toDTO(Item item);
}
