package com.stc.assessment.model.payload.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@ApiModel
@NoArgsConstructor
@AllArgsConstructor
public class FileRequest {

    private MultipartFile file;
}
