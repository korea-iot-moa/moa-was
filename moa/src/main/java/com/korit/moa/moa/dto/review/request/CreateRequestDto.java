package com.korit.moa.moa.dto.review.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestDto {

    @NotBlank
    private Long groupId;

    @NotBlank
    private String reviewContent;

    private String reviewImage;

    @NotBlank
    private String reviewDate;
}
