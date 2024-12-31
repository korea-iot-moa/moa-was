package com.korit.moa.moa.dto.review.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestDto {

    @NotBlank
    private Long groupId;

    @NotBlank
    private String groupName;

    @NotBlank
    private String reviewContent;

    @NotBlank
    private String reviewDate;

    private MultipartFile reviewImage;
}
