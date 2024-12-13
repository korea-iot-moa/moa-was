package com.korit.moa.moa.dto.user_list.response;


import com.korit.moa.moa.entity.userList.UserList;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonthRatioResponseDto {

    @NotBlank
    private String userId;

    @NotBlank
    private Date joinDate;

    public MonthRatioResponseDto(UserList userlist) {
        this.userId = userlist.getUser().getUserId();
        this.joinDate = userlist.getJoinDate();

    }
}
