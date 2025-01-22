package com.korit.moa.moa.dto.black_list.request;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBlackListDto {

    @NotBlank
    private String userId;

    @NotBlank
    private Long groupId;
}
