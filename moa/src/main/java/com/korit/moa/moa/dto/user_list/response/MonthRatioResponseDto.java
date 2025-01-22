package com.korit.moa.moa.dto.user_list.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MonthRatioResponseDto {

    private int quarter;

    private Long userCount;

    private Double ratio;

    public MonthRatioResponseDto(int quarter, long userCount, double ratio) {
        this.quarter = quarter;
        this.userCount = userCount;
        this.ratio = ratio;
    }
}
