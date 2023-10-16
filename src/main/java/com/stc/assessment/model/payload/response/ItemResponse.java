package com.stc.assessment.model.payload.response;

import com.stc.assessment.model.dtos.ItemDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class ItemResponse {

    private Long totalItems;
    private int totalPages;
    private List<ItemDTO> items;
}
