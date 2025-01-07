package com.korit.moa.moa.dto.user_list.response;

import com.korit.moa.moa.entity.userList.UserList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGetUserIdDto {

    private Long groupId;

    private String userId;



    public ResponseGetUserIdDto(UserList userList) {
        this.groupId = userList.getGroup().getGroupId();
        this.userId = userList.getUser().getUserId();
    }
}
