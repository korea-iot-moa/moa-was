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

//    @NotBlank
//    private String userId;
//

    private int quarter; // 분기
    private Long userCount; // 유저 수
    private Double ratio;//백분율


    public MonthRatioResponseDto(int quarter, long userCount, double ratio) {
        this.quarter = quarter;
        this.userCount = userCount;
        this.ratio = ratio;
    }
}
