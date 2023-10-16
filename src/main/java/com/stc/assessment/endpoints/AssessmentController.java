package com.stc.assessment.endpoints;

import com.stc.assessment.common.model.payload.ApiResponse;
import com.stc.assessment.model.dtos.ItemDTO;
import com.stc.assessment.model.payload.request.FileRequest;
import com.stc.assessment.model.payload.request.ItemRequest;
import com.stc.assessment.service.AssessmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/stc/assessment")
@Api(description = " ", tags = "Assessment", value = " ")
@Validated
public class AssessmentController {

    private final AssessmentService assessmentService;


    @ApiOperation(value = "Item - Create Item Space", tags = "Create Item Space")
    @PostMapping(value = "/item/space", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<ItemDTO> createSpace(@Valid @ModelAttribute ItemRequest itemRequest,@RequestHeader(required = true) @Email String email) {

        long start = System.currentTimeMillis();
        ItemDTO savedEntity = assessmentService.createSpace(itemRequest,email);
        return ApiResponse.created(savedEntity, (System.currentTimeMillis() - start) + " ms");
    }

    @ApiOperation(value = "Item - Create Item Folder", tags = "Create Item Folder")
    @PostMapping(value = "/item/folder", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<ItemDTO> createFolder(@Valid @ModelAttribute ItemRequest itemRequest, @RequestHeader(required = true) @Email String email) {

        long start = System.currentTimeMillis();
        ItemDTO savedEntity = assessmentService.createFolder(itemRequest,email);
        return ApiResponse.created(savedEntity, (System.currentTimeMillis() - start) + " ms");
    }

    @ApiOperation(value = "Item - Create Item File", tags = "Create Item File")
    @PostMapping(value = "/item/file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<ItemDTO> createFile(@Valid @ModelAttribute FileRequest fileRequest, @RequestHeader(required = true) @Email String email) {

        long start = System.currentTimeMillis();
        ItemDTO savedEntity = assessmentService.createFile(fileRequest,email);
        return ApiResponse.created(savedEntity, (System.currentTimeMillis() - start) + " ms");
    }
}
