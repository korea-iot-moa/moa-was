package com.korit.moa.moa.dto.user.request;

import com.korit.moa.moa.entity.user.Hobby;
import com.korit.moa.moa.entity.user.Region;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {

    private String userName;

    private String nickName;

    private MultipartFile profileImage;

    private Region region;
}