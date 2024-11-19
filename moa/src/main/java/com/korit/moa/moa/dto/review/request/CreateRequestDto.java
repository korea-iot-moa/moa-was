package com.korit.moa.moa.dto.review.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestDto {

    @NotBlank
    private Long groupId;

    @NotBlank
    private String reviewContent;

    private String reviewImage;

    private String reviewDate = String.valueOf(LocalDate.now());
}
