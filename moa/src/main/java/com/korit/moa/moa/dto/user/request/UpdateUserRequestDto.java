package com.korit.moa.moa.dto.user.request;
import com.korit.moa.moa.entity.user.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequestDto {

    private String userName;

    private String nickName;

    private MultipartFile profileImage;

    private Region region;

    private String phoneNumber;

    private String email;
}