package com.korit.moa.moa.dto.review.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequestDto {

    @NotBlank
    private String reviewContent;

    @NotBlank
    private String reviewImage;
}