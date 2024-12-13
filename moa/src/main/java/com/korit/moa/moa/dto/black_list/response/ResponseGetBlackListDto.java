package com.korit.moa.moa.dto.black_list.response;

import com.korit.moa.moa.entity.balckList.BlackList;
import com.korit.moa.moa.entity.user.User;
import com.korit.moa.moa.entity.userList.UserList;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGetBlackListDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String nickName;

    @NotBlank
    private String userLevel;



}
