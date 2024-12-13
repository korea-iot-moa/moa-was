package com.korit.moa.moa.entity.Report;

import lombok.Getter;

public enum ReportResult {
   처리중("처리중"),
    추방("추방"),
    유지("유지");

    @Getter
    private String status;

   private ReportResult(String status) {
       this.status = status;
   }

}

