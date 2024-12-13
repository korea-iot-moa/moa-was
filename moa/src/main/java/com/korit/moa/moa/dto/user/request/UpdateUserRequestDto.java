package com.korit.moa.moa.dto.user.request;

import com.korit.moa.moa.entity.user.Hobby;
import com.korit.moa.moa.entity.user.Region;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {

    @NotBlank
    private String userName;
    @NotBlank
    private String nickName;

    private Set<String> hobbies;

    private String profileImage;

    private Region region;
}

