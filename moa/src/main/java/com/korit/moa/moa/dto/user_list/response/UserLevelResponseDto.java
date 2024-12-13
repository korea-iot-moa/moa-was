package com.korit.moa.moa.dto.user_list.response;



import com.korit.moa.moa.entity.userList.UserLevel;

import com.korit.moa.moa.entity.userList.UserList;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserLevelResponseDto {
    //유저 레벨 변경
    @NotBlank
    private Long groupId;

    @NotBlank
    private String nickName;

    @NotBlank
    private UserLevel userLevel;


    public UserLevelResponseDto(UserList userList) {
        this.groupId = userList.getGroup().getGroupId();
        this.nickName = getNickName();
        this.userLevel = userList.getUserLevel();
    }
}
