package com.korit.moa.moa.dto.black_list.response;


import com.korit.moa.moa.entity.balckList.BlackList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBlackListDto {

    private Long blackListId;

    private String userId;

    private Long groupId;

        public ResponseBlackListDto(BlackList blackList) {
            this.blackListId = blackList.getBlackListId();
            this.userId = blackList.getUserId();
            this.groupId = blackList.getGroupId();
        }
}
