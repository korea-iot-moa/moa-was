package com.korit.moa.moa.dto.black_list.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGetBlackListDto {
    @NotBlank
    private Long blackListId;

    @NotBlank
    private String userId;

    @NotBlank
    private String profileImage;

    @NotBlank
    private String nickName;





}
